"""Tests for courier.store: url_hash, read_status, write_status, read_cached_html, write_cached_html."""

from __future__ import annotations

from pathlib import Path

from courier.store import (
    read_cached_html,
    read_status,
    url_hash,
    write_cached_html,
    write_status,
)


def test_url_hash_deterministic():
    h1 = url_hash("https://example.com/article")
    h2 = url_hash("https://example.com/article")
    assert h1 == h2
    assert len(h1) == 64


def test_read_status_missing_file(tmp_path: Path):
    assert read_status(tmp_path / "nonexistent") == {}


def test_write_then_read_status(tmp_path: Path):
    status = {"url": "https://example.com", "fetched": True}
    write_status(tmp_path, status)
    assert read_status(tmp_path) == status


def test_write_status_no_tmp_file_remains(tmp_path: Path):
    write_status(tmp_path, {"key": "value"})
    assert list(tmp_path.glob("*.tmp")) == []


def test_read_cached_html_missing(tmp_path: Path):
    assert read_cached_html(tmp_path, "https://example.com/article", "raw") is None


def test_write_then_read_cached_html_raw(tmp_path: Path):
    url  = "https://example.com/article"
    html = "<html><body>Raw content</body></html>"
    write_cached_html(tmp_path, url, "raw", html)
    assert read_cached_html(tmp_path, url, "raw") == html


def test_write_then_read_cached_html_extracted(tmp_path: Path):
    url  = "https://example.com/article"
    html = "<html><body>Extracted content</body></html>"
    write_cached_html(tmp_path, url, "extracted", html)
    assert read_cached_html(tmp_path, url, "extracted") == html
