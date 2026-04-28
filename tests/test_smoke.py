"""Smoke tests to verify the project skeleton builds and imports correctly."""

import courier
from courier.config import Config
from courier.store import url_cache_path, url_hash


def test_version():
    assert courier.__version__ == "0.1.0"


def test_config_dataclass():
    from pathlib import Path

    config = Config(
        feed_urls=["https://example.com/feed"],
        cache_dir=Path("/tmp/test-cache"),
        output_dir=Path("/tmp/test-output"),
    )
    assert config.max_fetch_attempts == 10
    assert config.max_articles_per_run == 30


def test_url_hash_deterministic():
    h1 = url_hash("https://example.com/article")
    h2 = url_hash("https://example.com/article")
    assert h1 == h2
    assert len(h1) == 64  # SHA-256 hex digest


def test_write_cached_html_uses_readable_path(tmp_path):
    from courier.store import read_cached_html, write_cached_html

    url = "https://example.com/articles/foo-bar"
    write_cached_html(tmp_path, url, "raw", "<html>hello</html>")
    expected = url_cache_path(tmp_path, url) / "raw.html"
    assert expected.exists()
    assert expected.read_text() == "<html>hello</html>"


def test_read_cached_html_uses_readable_path(tmp_path):
    from courier.store import read_cached_html, write_cached_html

    url = "https://example.com/articles/foo-bar"
    write_cached_html(tmp_path, url, "raw", "<html>hello</html>")
    result = read_cached_html(tmp_path, url, "raw")
    assert result == "<html>hello</html>"


def test_url_cache_path_structure():
    from pathlib import Path

    cache_dir = Path("/tmp/cache")
    url = "https://www.arstechnica.com/science/2024/new-study/?ref=rss"
    result = url_cache_path(cache_dir, url)

    assert result.parts[-2] == "arstechnica.com"
    assert result.parts[-1].startswith("science_2024_new_study_ref_rss--")
    expected_hash8 = url_hash(url)[:8]
    assert result.parts[-1].endswith(expected_hash8)
    assert result == cache_dir / "cache" / "arstechnica.com" / result.parts[-1]
