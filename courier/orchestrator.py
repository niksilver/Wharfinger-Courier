"""Pipeline orchestrator — coordinates fetch, extract, and compile stages."""

from __future__ import annotations

import logging
from datetime import datetime, timezone
from pathlib import Path

from courier import compiler, extractor, fetcher, store
from courier.compiler import Article
from courier.config import Config

logger = logging.getLogger(__name__)

_SKIP_STATUSES = frozenset({"COMPILED", "PERMANENTLY_SKIPPED", "UNSUPPORTED_CONTENT_TYPE"})


def run_pipeline(
    config: Config,
    since_days: int | None = None,
    dry_run: bool = False,
) -> bool:
    """Run the full courier pipeline. Returns True on success."""
    logger.info("Starting courier pipeline (dry_run=%s, since_days=%s)", dry_run, since_days)

    if since_days is not None:
        logger.warning("since_days parameter received but filtering is not yet implemented")
        # TODO: US-002

    feed_items = _fetch_and_parse_feed(config)
    if feed_items is None:
        return False

    status_data = store.read_status(config.cache_dir)
    articles_to_process = _filter_articles(feed_items, status_data, config)

    if dry_run:
        print(f"Dry run — {len(articles_to_process)} articles would be processed:")
        for item in articles_to_process:
            print(f"  {item['url']}")
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
            article_status = status_data.get("articles", {}).get(url, {}).get("status", "")
            if article_status == "PERMANENTLY_SKIPPED":
                skipped_count += 1
            else:
                failed_count += 1

    output_path: Path | None = None
    if compiled_articles:
        output_path = compiler.compile_document(compiled_articles, config.output_dir)

    store.write_status(config.cache_dir, status_data)
    _print_summary(len(compiled_articles), failed_count, skipped_count, output_path)
    return True


def _fetch_and_parse_feed(config: Config) -> list[dict] | None:
    """Fetch and parse the Pinboard RSS feed. Returns None on failure."""
    try:
        return fetcher.fetch_feed(config.pinboard_feed_url)
    except Exception:
        logger.exception("Failed to fetch feed")
        return None


def _filter_articles(
    feed_items: list[dict],
    status_data: dict,
    config: Config,
) -> list[dict]:
    """Exclude already-processed articles and cap at max_articles_per_run."""
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


def _process_article(
    item: dict,
    config: Config,
    status_data: dict,
) -> Article | None:
    """Fetch, extract, and update status for a single article.

    Updates status_data in-place. Writes status atomically after each article.
    Returns an Article on success, None on fetch/extraction failure.
    """
    url = item["url"]
    title = item.get("title", "")

    articles = status_data.setdefault("articles", {})
    entry = articles.setdefault(url, {
        "title": title,
        "status": "PENDING",
        "fetch_fail_count": 0,
        "last_updated": "",
        "word_count": 0,
        "last_error": "",
    })

    try:
        raw_html = fetcher.fetch_article(url)
    except Exception as exc:
        entry["fetch_fail_count"] = entry.get("fetch_fail_count", 0) + 1
        entry["last_error"] = str(exc)
        entry["last_updated"] = _now_iso()
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

    try:
        extracted_title, content = extractor.extract_article(raw_html, url)
    except Exception as exc:
        logger.warning("Extraction failed for %s: %s", url, exc)
        entry["status"] = "EXTRACTION_FAILED"
        entry["last_error"] = str(exc)
        entry["last_updated"] = _now_iso()
        store.write_status(config.cache_dir, status_data)
        return None

    entry["title"] = extracted_title or title
    entry["status"] = "COMPILED"
    entry["word_count"] = len(content.split())
    entry["last_updated"] = _now_iso()
    entry["last_error"] = ""
    store.write_status(config.cache_dir, status_data)
    return Article(title=entry["title"], content=content, url=url)


def _print_summary(compiled: int, failed: int, skipped: int, output_path: Path | None) -> None:
    """Print the run summary to stdout."""
    print(f"Compiled: {compiled}  Failed: {failed}  Skipped: {skipped}")
    if output_path:
        print(f"Output: {output_path}")


def _now_iso() -> str:
    """Return the current UTC time as an ISO 8601 string."""
    return datetime.now(timezone.utc).isoformat()
