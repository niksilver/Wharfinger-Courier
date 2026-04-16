"""Tests for courier.orchestrator."""

from __future__ import annotations

from pathlib import Path
from unittest.mock import MagicMock, patch

import pytest

from courier.compiler import Article
from courier.config import Config
from courier.orchestrator import run_pipeline


@pytest.fixture()
def config(tmp_path):
    return Config(
        pinboard_feed_url="https://feeds.pinboard.in/rss/secret/u:user/",
        cache_dir=tmp_path / "cache",
        output_dir=tmp_path / "output",
        max_fetch_attempts=3,
        max_articles_per_run=10,
    )


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
def test_article_cap_enforced(mock_fetcher, mock_extractor, mock_compiler, mock_store, tmp_path):
    """Only max_articles_per_run articles are processed, regardless of feed length."""
    cfg = Config(
        pinboard_feed_url="https://feeds.pinboard.in/rss/secret/",
        cache_dir=tmp_path / "cache",
        output_dir=tmp_path / "output",
        max_fetch_attempts=3,
        max_articles_per_run=2,
    )
    mock_fetcher.fetch_feed.return_value = [_item(f"https://example.com/{i}") for i in range(5)]
    mock_fetcher.fetch_article.return_value = "<html>x</html>"
    mock_extractor.extract_article.return_value = ("T", "<p>x</p>")
    mock_compiler.compile_document.return_value = Path("/out/doc.xhtml")
    mock_store.read_status.return_value = {}

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
def test_dry_run_prints_list_without_writing(mock_fetcher, mock_extractor, mock_compiler, mock_store, config, capsys):
    """dry_run=True prints article list without fetching articles, compiling, or writing."""
    mock_fetcher.fetch_feed.return_value = [
        _item("https://example.com/a1"),
        _item("https://example.com/a2"),
    ]
    mock_store.read_status.return_value = {}

    result = run_pipeline(config, dry_run=True)

    assert result is True
    mock_fetcher.fetch_article.assert_not_called()
    mock_compiler.compile_document.assert_not_called()
    mock_store.write_status.assert_not_called()
    out = capsys.readouterr().out
    assert "https://example.com/a1" in out
    assert "https://example.com/a2" in out


# ---------------------------------------------------------------------------
# Atomic writes
# ---------------------------------------------------------------------------

@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_status_written_after_each_article(mock_fetcher, mock_extractor, mock_compiler, mock_store, config, tmp_path):
    """store.write_status is called once per article processed plus once at run end."""
    mock_fetcher.fetch_feed.return_value = [
        _item("https://example.com/a1"),
        _item("https://example.com/a2"),
    ]
    mock_fetcher.fetch_article.return_value = "<html>x</html>"
    mock_extractor.extract_article.return_value = ("T", "<p>x</p>")
    mock_compiler.compile_document.return_value = tmp_path / "doc.xhtml"
    mock_store.read_status.return_value = {}

    run_pipeline(config)

    # At minimum: 1 write per article + 1 final write = 3
    assert mock_store.write_status.call_count >= 3


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

    result = run_pipeline(config)

    assert result is False
    mock_store.read_status.assert_not_called()
    mock_compiler.compile_document.assert_not_called()
