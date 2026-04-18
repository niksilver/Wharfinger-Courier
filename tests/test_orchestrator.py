"""Tests for courier.orchestrator."""

from __future__ import annotations

from datetime import datetime, timezone
from pathlib import Path
from unittest.mock import MagicMock, patch

import logging

import pytest

from tests.conftest import config, make_config

from courier.compiler import Article
from courier.config import load_config
from courier.orchestrator import filter_articles, run_pipeline

# Fixed "now" used in since_days tests: 2026-04-16 12:00 UTC
_NOW = datetime(2026, 4, 16, 12, 0, 0, tzinfo=timezone.utc)


def _item(url: str, title: str = "Article Title") -> dict:
    return {"url": url, "title": title, "timestamp": "2024-01-15T10:00:00Z"}


# ---------------------------------------------------------------------------
# Happy path
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_happy_path(mock_fetcher, mock_extractor, mock_compiler, mock_store, config, tmp_path):
    """Feed -> extract -> compile -> output file created; run_pipeline returns True."""
    mock_fetcher.fetch_feed.return_value = [_item("https://example.com/a1")]
    mock_fetcher.fetch_article.return_value = "<html>content</html>"
    mock_extractor.extract_article.return_value = ("Article Title", "<p>content</p>")
    mock_compiler.compile_document.return_value = tmp_path / "output" / "doc.xhtml"
    mock_store.read_status.return_value = {}
    mock_store.read_cached_html.return_value = None

    result = run_pipeline(config)

    assert result is True
    mock_compiler.compile_document.assert_called_once()
    articles_arg = mock_compiler.compile_document.call_args[0][0]
    assert len(articles_arg) == 1
    assert articles_arg[0].url == "https://example.com/a1"


# ---------------------------------------------------------------------------
# Article cap
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_article_cap_enforced(mock_fetcher, mock_extractor, mock_compiler, mock_store, make_config, tmp_path):
    """Only max_articles_per_run articles are processed, regardless of feed length."""
    cfg = make_config(max_articles_per_run=2)
    mock_fetcher.fetch_feed.return_value = [_item(f"https://example.com/{i}") for i in range(5)]
    mock_fetcher.fetch_article.return_value = "<html>x</html>"
    mock_extractor.extract_article.return_value = ("T", "<p>x</p>")
    mock_compiler.compile_document.return_value = Path("/out/doc.xhtml")
    mock_store.read_status.return_value = {}
    mock_store.read_cached_html.return_value = None

    run_pipeline(cfg)

    assert mock_fetcher.fetch_article.call_count == 2


# ---------------------------------------------------------------------------
# Filtering
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_compiled_articles_skipped(mock_fetcher, mock_extractor, mock_compiler, mock_store, config):
    """Articles already in COMPILED state are filtered before processing."""
    mock_fetcher.fetch_feed.return_value = [_item("https://example.com/a1")]
    mock_store.read_status.return_value = {
        "articles": {"https://example.com/a1": {"status": "COMPILED"}}
    }
    mock_store.read_cached_html.return_value = None

    run_pipeline(config)

    mock_fetcher.fetch_article.assert_not_called()
    mock_compiler.compile_document.assert_not_called()


@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_permanently_skipped_articles_skipped(mock_fetcher, mock_extractor, mock_compiler, mock_store, config):
    """Articles in PERMANENTLY_SKIPPED state are filtered before processing."""
    mock_fetcher.fetch_feed.return_value = [_item("https://example.com/a1")]
    mock_store.read_status.return_value = {
        "articles": {"https://example.com/a1": {"status": "PERMANENTLY_SKIPPED"}}
    }
    mock_store.read_cached_html.return_value = None

    run_pipeline(config)

    mock_fetcher.fetch_article.assert_not_called()


# ---------------------------------------------------------------------------
# Fetch failure and retry
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_fetch_failure_increments_fail_count(mock_fetcher, mock_extractor, mock_compiler, mock_store, config):
    """Fetch failure increments fetch_fail_count and sets FETCH_FAILED status."""
    mock_fetcher.fetch_feed.return_value = [_item("https://example.com/a1")]
    mock_fetcher.fetch_article.side_effect = Exception("Connection error")
    mock_store.read_status.return_value = {}
    mock_store.read_cached_html.return_value = None

    run_pipeline(config)

    written_status = mock_store.write_status.call_args[0][1]
    entry = written_status["articles"]["https://example.com/a1"]
    assert entry["fetch_fail_count"] == 1
    assert entry["status"] == "FETCH_FAILED"


@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_permanent_skip_after_max_attempts(mock_fetcher, mock_extractor, mock_compiler, mock_store, config):
    """Article is permanently skipped once fetch_fail_count reaches max_fetch_attempts."""
    mock_fetcher.fetch_feed.return_value = [_item("https://example.com/a1")]
    mock_fetcher.fetch_article.side_effect = Exception("Timeout")
    mock_store.read_cached_html.return_value = None
    mock_store.read_status.return_value = {
        "articles": {
            "https://example.com/a1": {
                "status": "FETCH_FAILED",
                "fetch_fail_count": config.max_fetch_attempts - 1,
                "last_updated": "",
                "last_error": "",
                "word_count": 0,
                "title": "",
            }
        }
    }

    run_pipeline(config)

    written_status = mock_store.write_status.call_args[0][1]
    entry = written_status["articles"]["https://example.com/a1"]
    assert entry["status"] == "PERMANENTLY_SKIPPED"
    assert entry["fetch_fail_count"] == config.max_fetch_attempts


# ---------------------------------------------------------------------------
# Dry run
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_dry_run_prints_list_without_writing(mock_fetcher, mock_extractor, mock_compiler, mock_store, config, caplog):
    """dry_run=True logs article list without fetching articles, compiling, or writing."""
    mock_fetcher.fetch_feed.return_value = [
        _item("https://example.com/a1"),
        _item("https://example.com/a2"),
    ]
    mock_store.read_status.return_value = {}
    mock_store.read_cached_html.return_value = None

    with caplog.at_level(logging.INFO, logger="courier.orchestrator"):
        result = run_pipeline(config, dry_run=True)

    assert result is True
    mock_fetcher.fetch_article.assert_not_called()
    mock_compiler.compile_document.assert_not_called()
    mock_store.write_status.assert_not_called()
    assert "https://example.com/a1" in caplog.text
    assert "https://example.com/a2" in caplog.text


# ---------------------------------------------------------------------------
# Atomic writes
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_status_written_after_each_article(mock_fetcher, mock_extractor, mock_compiler, mock_store, config, tmp_path):
    """store.write_status is called once per article processed."""
    mock_fetcher.fetch_feed.return_value = [
        _item("https://example.com/a1"),
        _item("https://example.com/a2"),
    ]
    mock_fetcher.fetch_article.return_value = "<html>x</html>"
    mock_extractor.extract_article.return_value = ("T", "<p>x</p>")
    mock_compiler.compile_document.return_value = tmp_path / "doc.xhtml"
    mock_store.read_status.return_value = {}
    mock_store.read_cached_html.return_value = None

    run_pipeline(config)

    # 1 write per article = 2 writes for 2 articles
    assert mock_store.write_status.call_count >= 2


# ---------------------------------------------------------------------------
# Extraction failure
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_extraction_failure_sets_status(mock_fetcher, mock_extractor, mock_compiler, mock_store, config):
    """Extraction failure records EXTRACTION_FAILED without calling compile_document."""
    mock_fetcher.fetch_feed.return_value = [_item("https://example.com/a1")]
    mock_fetcher.fetch_article.return_value = "<html>content</html>"
    mock_extractor.extract_article.side_effect = Exception("Extraction error")
    mock_store.read_status.return_value = {}
    mock_store.read_cached_html.return_value = None

    run_pipeline(config)

    written_status = mock_store.write_status.call_args[0][1]
    entry = written_status["articles"]["https://example.com/a1"]
    assert entry["status"] == "EXTRACTION_FAILED"
    mock_compiler.compile_document.assert_not_called()


# ---------------------------------------------------------------------------
# Feed fetch failure
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_feed_fetch_failure_exits_gracefully(mock_fetcher, mock_extractor, mock_compiler, mock_store, config):
    """Feed fetch failure returns False without touching store or compiler."""
    mock_fetcher.fetch_feed.side_effect = Exception("Network error")
    mock_store.read_cached_html.return_value = None

    result = run_pipeline(config)

    assert result is False
    mock_store.read_status.assert_not_called()
    mock_compiler.compile_document.assert_not_called()


# ---------------------------------------------------------------------------
# since_days filtering (UC-002 / US-005)
# All tests use filter_articles directly with a fixed _now for determinism.
# ---------------------------------------------------------------------------


def test_since_days_excludes_old_articles(make_config, tmp_path):
    """Articles outside the N-day window are excluded."""
    # _NOW = 2026-04-16; since_days=7 → cutoff date = 2026-04-10
    # recent (2026-04-14) is within window; old (2026-03-01) is not
    feed = [
        {"url": "https://example.com/recent", "title": "R", "timestamp": "2026-04-14T10:00:00+00:00"},
        {"url": "https://example.com/old",    "title": "O", "timestamp": "2026-03-01T10:00:00+00:00"},
    ]
    result = filter_articles(feed, {}, make_config(), since_days=7, _now=_NOW)
    urls = [i["url"] for i in result]
    assert "https://example.com/recent" in urls
    assert "https://example.com/old"    not in urls


def test_since_days_includes_compiled_articles(make_config, tmp_path):
    """Articles with COMPILED status are included in archive mode (unlike normal mode)."""
    feed = [{"url": "https://example.com/a1", "title": "T", "timestamp": "2026-04-14T10:00:00+00:00"}]
    status_data = {"articles": {"https://example.com/a1": {"status": "COMPILED"}}}
    result = filter_articles(feed, status_data, make_config(), since_days=7, _now=_NOW)
    assert len(result) == 1


def test_since_days_still_excludes_permanently_skipped(make_config, tmp_path):
    """PERMANENTLY_SKIPPED articles are excluded even in archive mode."""
    feed = [{"url": "https://example.com/a1", "title": "T", "timestamp": "2026-04-14T10:00:00+00:00"}]
    status_data = {"articles": {"https://example.com/a1": {"status": "PERMANENTLY_SKIPPED"}}}
    result = filter_articles(feed, status_data, make_config(), since_days=7, _now=_NOW)
    assert len(result) == 0


def test_since_days_sorts_most_recent_first(make_config, tmp_path):
    """Results are ordered most-recently-bookmarked first."""
    feed = [
        {"url": "https://example.com/older",  "title": "A", "timestamp": "2026-04-12T08:00:00+00:00"},
        {"url": "https://example.com/newest", "title": "B", "timestamp": "2026-04-15T20:00:00+00:00"},
        {"url": "https://example.com/middle", "title": "C", "timestamp": "2026-04-13T14:00:00+00:00"},
    ]
    result = filter_articles(feed, {}, make_config(), since_days=7, _now=_NOW)
    urls = [i["url"] for i in result]
    assert urls == [
        "https://example.com/newest",
        "https://example.com/middle",
        "https://example.com/older",
    ]


def test_since_days_cap_message_when_articles_excluded(make_config, tmp_path, caplog):
    """When the cap is reached, a message states how many articles were excluded."""
    feed = [
        {"url": f"https://example.com/{i}", "title": f"T{i}", "timestamp": "2026-04-14T10:00:00+00:00"}
        for i in range(5)
    ]
    with caplog.at_level(logging.INFO, logger="courier.orchestrator"):
        result = filter_articles(feed, {}, make_config(max_articles_per_run=3), since_days=7, _now=_NOW)
    assert len(result) == 3
    assert "2 articles excluded" in caplog.text
    assert "excluded" in caplog.text.lower()


def test_since_days_no_timestamp_article_included(make_config, tmp_path):
    """Articles without a parseable timestamp are included (err on the side of inclusion)."""
    feed = [{"url": "https://example.com/notimestamp", "title": "T", "timestamp": ""}]
    result = filter_articles(feed, {}, make_config(), since_days=7, _now=_NOW)
    assert len(result) == 1


# ---------------------------------------------------------------------------
# Config validation (Phase 2)
# ---------------------------------------------------------------------------

def test_load_config_rejects_empty_url(tmp_path):
    """load_config() raises ValueError when feed_urls is empty."""
    config_file = tmp_path / "config.toml"
    config_file.write_text(
        'feed_urls = ""\ncache_dir = "/tmp/c"\noutput_dir = "/tmp/o"\n'
    )
    with pytest.raises(ValueError, match="feed_urls"):
        load_config(str(config_file))


def test_load_config_rejects_non_http_url(tmp_path):
    """load_config() raises ValueError for a non-HTTP URL."""
    config_file = tmp_path / "config.toml"
    config_file.write_text(
        'feed_urls = "ftp://example.com/feed"\ncache_dir = "/tmp/c"\noutput_dir = "/tmp/o"\n'
    )
    with pytest.raises(ValueError, match="feed_urls"):
        load_config(str(config_file))
