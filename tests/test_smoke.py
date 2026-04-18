"""Smoke tests to verify the project skeleton builds and imports correctly."""

import courier
from courier.config import Config
from courier.store import url_hash


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
