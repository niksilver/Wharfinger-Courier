from __future__ import annotations

from pathlib import Path

import pytest

from courier.config import Config


@pytest.fixture()
def config(tmp_path: Path) -> Config:
    """Default Config for tests using tmp_path directories."""
    return Config(
        feed_urls=["https://feeds.pinboard.in/rss/secret/u:user/"],
        cache_dir=tmp_path / "cache",
        output_dir=tmp_path / "output",
        max_fetch_attempts=3,
        max_articles_per_run=10,
    )


@pytest.fixture()
def make_config(tmp_path: Path):
    """Factory fixture for Config — accepts keyword overrides."""
    def _make(**overrides) -> Config:
        defaults = dict(
            feed_urls=["https://feeds.pinboard.in/rss/secret/u:user/"],
            cache_dir=tmp_path / "cache",
            output_dir=tmp_path / "output",
            max_fetch_attempts=3,
            max_articles_per_run=10,
        )
        defaults.update(overrides)
        return Config(**defaults)
    return _make


@pytest.fixture()
def sample_html() -> str:
    """Minimal valid HTML with enough content for extraction tests."""
    return """
    <html><head><title>Test Article</title></head>
    <body>
      <article>
        <h1>Test Article Title</h1>
        <p>""" + ("This is a test paragraph with enough content to pass the readability threshold. " * 10) + """</p>
      </article>
    </body></html>
    """


@pytest.fixture()
def sample_feed_xml() -> bytes:
    """Minimal RSS 1.0 (RDF) feed bytes for feed parsing tests."""
    return b"""<?xml version="1.0" encoding="UTF-8"?>
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
         xmlns="http://purl.org/rss/1.0/">
  <channel>
    <title>Test Feed</title>
    <link>https://example.com</link>
    <description>Test</description>
  </channel>
  <item>
    <title>Test Item</title>
    <link>https://example.com/article-1</link>
    <description>Test description</description>
  </item>
</rdf:RDF>"""
