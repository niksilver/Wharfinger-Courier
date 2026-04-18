"""Pipeline orchestrator — coordinates fetch, extract, and compile stages."""

from __future__ import annotations

import logging
from datetime import datetime, timedelta, timezone
from pathlib import Path

from courier import compiler, extractor, fetcher, store
from courier.compiler import Article
from courier.config import Config

logger = logging.getLogger(__name__)

_SKIP_STATUSES         = frozenset({"COMPILED", "PERMANENTLY_SKIPPED", "UNSUPPORTED_CONTENT_TYPE"})
_ARCHIVE_SKIP_STATUSES = frozenset({"PERMANENTLY_SKIPPED", "UNSUPPORTED_CONTENT_TYPE"})


def run_pipeline(
    config: Config,
    since_days: int | None = None,
    dry_run: bool = False,
) -> bool:
    """Run the full courier pipeline. Returns True on success."""
    logger.info("Starting courier pipeline (dry_run=%s, since_days=%s)", dry_run, since_days)

    feed_items = _fetch_and_parse_feed(config)
    if feed_items is None:
        return False

    status_data = store.read_status(config.cache_dir)
    articles_to_process = filter_articles(feed_items, status_data, config, since_days)

    if dry_run:
        logger.info("Dry run -- %d articles would be processed:", len(articles_to_process))
        for item in articles_to_process:
            logger.info("  %s", item["url"])
        return True

    compiled_articles: list[Article] = []
    failed_count = 0
    skipped_count = 0

    for item in articles_to_process:
        url = item["url"]
        article = _process_article(item, config, status_data)
        if article is not None:
            compiled_articles.append(article)
        else:
            article_status = status_data["articles"][url].get("status", "")
            if article_status == "PERMANENTLY_SKIPPED":
                skipped_count += 1
            else:
                failed_count += 1

    output_path: Path | None = None
    if compiled_articles:
        output_path = compiler.compile_document(compiled_articles, config.output_dir)

    _log_summary(len(compiled_articles), failed_count, skipped_count, output_path)
    return True


def _fetch_and_parse_feed(config: Config) -> list[dict] | None:
    """Fetch and parse all configured RSS feeds. Returns None on failure."""
    items = []
    for url in config.feed_urls:
        try:
            items.extend(fetcher.fetch_feed(url))
        except Exception:
            logger.exception("Failed to fetch feed %s", url)
            return None
    return items


def filter_articles(
    feed_items: list[dict],
    status_data: dict,
    config: Config,
    since_days: int | None = None,
    _now: datetime | None = None,
) -> list[dict]:
    """Exclude already-processed articles and cap at max_articles_per_run.

    When since_days is set (archive mode): includes COMPILED articles, filters
    by bookmark date, and returns results sorted most-recently-bookmarked first.
    """
    if since_days is not None:
        return _filter_archive(feed_items, status_data, config, since_days, _now)

    articles = status_data.get("articles", {})
    result = []
    for item in feed_items:
        url = item.get("url", "")
        if articles.get(url, {}).get("status") in _SKIP_STATUSES:
            continue
        result.append(item)
        if len(result) >= config.max_articles_per_run:
            break
    return result


def _filter_archive(
    feed_items: list[dict],
    status_data: dict,
    config: Config,
    since_days: int,
    _now: datetime | None,
) -> list[dict]:
    """Archive-mode filter: date window, most-recent-first sort, cap with message."""
    now         = _now or datetime.now(timezone.utc)
    cutoff_date = (now.date() - timedelta(days=since_days - 1))

    articles   = status_data.get("articles", {})
    candidates = []
    for item in feed_items:
        url = item.get("url", "")
        if articles.get(url, {}).get("status") in _ARCHIVE_SKIP_STATUSES:
            continue
        dt = _parse_timestamp(item.get("timestamp", ""))
        if dt is not None and dt.date() < cutoff_date:
            continue
        sort_key = dt if dt is not None else datetime.min.replace(tzinfo=timezone.utc)
        candidates.append((sort_key, item))

    candidates.sort(key=lambda x: x[0], reverse=True)

    result   = [item for _, item in candidates[: config.max_articles_per_run]]
    excluded = len(candidates) - len(result)
    if excluded > 0:
        noun = "article" if excluded == 1 else "articles"
        logger.info("%d %s excluded by the article cap.", excluded, noun)

    return result


def _parse_timestamp(timestamp: str) -> datetime | None:
    """Parse an ISO 8601 timestamp string. Returns None if absent or unparseable."""
    if not timestamp:
        return None
    try:
        return datetime.fromisoformat(timestamp.replace("Z", "+00:00"))
    except ValueError:
        return None


def _fetch_raw_html(
    url: str,
    config: Config,
    entry: dict,
    status_data: dict,
    now_iso: str,
) -> str | None:
    """Return raw HTML for url, checking the cache first.

    On cache hit: returns cached HTML. On cache miss: fetches from network,
    writes to cache, and returns HTML. On fetch failure: updates entry status,
    writes status_data, and returns None.
    """
    raw_html = store.read_cached_html(config.cache_dir, url, "raw")
    if raw_html is not None:
        logger.info("Cache hit for %s (raw)", url)
        return raw_html

    try:
        raw_html = fetcher.fetch_article(url)
    except Exception as exc:
        entry["fetch_fail_count"] = entry.get("fetch_fail_count", 0) + 1
        entry["last_error"]       = str(exc)
        entry["last_updated"]     = now_iso
        if entry["fetch_fail_count"] >= config.max_fetch_attempts:
            logger.warning(
                "Permanently skipping %s after %d fetch failures", url, entry["fetch_fail_count"]
            )
            entry["status"] = "PERMANENTLY_SKIPPED"
        else:
            logger.warning(
                "Fetch failed for %s (%d/%d): %s",
                url, entry["fetch_fail_count"], config.max_fetch_attempts, exc,
            )
            entry["status"] = "FETCH_FAILED"
        store.write_status(config.cache_dir, status_data)
        return None

    store.write_cached_html(config.cache_dir, url, "raw", raw_html)
    return raw_html


def _process_article(
    item: dict,
    config: Config,
    status_data: dict,
) -> Article | None:
    """Fetch, extract, and update status for a single article.

    Updates status_data in-place. Writes status atomically after each article.
    Returns an Article on success, None on fetch/extraction failure.
    """
    url     = item["url"]
    title   = item.get("title", "")
    now_iso = _now_iso()

    articles = status_data.setdefault("articles", {})
    entry    = articles.setdefault(url, {
        "title":           title,
        "status":          "PENDING",
        "fetch_fail_count": 0,
        "last_updated":    "",
        "word_count":      0,
        "last_error":      "",
    })

    raw_html = _fetch_raw_html(url, config, entry, status_data, now_iso)
    if raw_html is None:
        return None

    try:
        extracted_title, content = extractor.extract_article(raw_html, url)
    except Exception as exc:
        logger.warning("Extraction failed for %s: %s", url, exc)
        entry["status"]       = "EXTRACTION_FAILED"
        entry["last_error"]   = str(exc)
        entry["last_updated"] = now_iso
        store.write_status(config.cache_dir, status_data)
        return None

    store.write_cached_html(config.cache_dir, url, "extracted", content)

    entry["title"]        = extracted_title or title
    entry["status"]       = "COMPILED"
    entry["word_count"]   = len(content.split())
    entry["last_updated"] = now_iso
    entry["last_error"]   = ""
    store.write_status(config.cache_dir, status_data)
    return Article(title=entry["title"], content=content, url=url)


def _log_summary(compiled: int, failed: int, skipped: int, output_path: Path | None) -> None:
    """Log the run summary."""
    logger.info("Compiled: %d  Failed: %d  Skipped: %d", compiled, failed, skipped)
    if output_path:
        logger.info("Output: %s", output_path)


def _now_iso() -> str:
    """Return the current UTC time as an ISO 8601 string."""
    return datetime.now(timezone.utc).isoformat()
