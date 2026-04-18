"""Tests for courier.fetcher."""

from __future__ import annotations

import xml.etree.ElementTree as ET
from unittest.mock import MagicMock, patch

import pytest
import requests

from courier import fetcher

_VALID_RDF = b"""<?xml version="1.0" encoding="utf-8"?>
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
         xmlns="http://purl.org/rss/1.0/"
         xmlns:dc="http://purl.org/dc/elements/1.1/">
  <item rdf:about="https://example.com/article-1">
    <title>Test Article</title>
    <link>https://example.com/article-1</link>
    <dc:date>2024-01-15T10:00:00Z</dc:date>
  </item>
  <item rdf:about="https://example.com/article-2">
    <title>Second Article</title>
    <link>https://example.com/article-2</link>
    <dc:date>2024-01-16T12:00:00Z</dc:date>
  </item>
</rdf:RDF>"""


def test_fetch_feed_parses_rdf_xml():
    """Valid RDF/RSS 1.0 XML is parsed into a list of dicts with url, title, timestamp."""
    mock_response = MagicMock()
    mock_response.content = _VALID_RDF
    mock_response.raise_for_status = MagicMock()

    with patch("requests.get", return_value=mock_response):
        result = fetcher.fetch_feed("https://example.com/feed")

    assert len(result) == 2
    assert result[0]["url"] == "https://example.com/article-1"
    assert result[0]["title"] == "Test Article"
    assert result[0]["timestamp"] == "2024-01-15T10:00:00Z"
    assert result[1]["url"] == "https://example.com/article-2"
    assert result[1]["title"] == "Second Article"


def test_fetch_feed_raises_on_http_error():
    """HTTP 4xx response raises requests.HTTPError."""
    mock_response = MagicMock()
    mock_response.raise_for_status.side_effect = requests.HTTPError("404 Not Found")

    with patch("requests.get", return_value=mock_response):
        with pytest.raises(requests.HTTPError):
            fetcher.fetch_feed("https://example.com/feed")


def test_fetch_feed_parses_rss2_xml(sample_rss2_feed: bytes):
    """Valid RSS 2.0 XML is parsed into a list of dicts with url, title, timestamp."""
    mock_response = MagicMock()
    mock_response.content = sample_rss2_feed
    mock_response.raise_for_status = MagicMock()

    with patch("requests.get", return_value=mock_response):
        result = fetcher.fetch_feed("https://spacebiff.com/rss")

    assert len(result) == 2
    assert result[0]["url"] == "https://spacebiff.com/article-1"
    assert result[0]["title"] == "Test Article"
    assert result[0]["timestamp"] == "2024-01-15T10:00:00+00:00"
    assert result[1]["url"] == "https://spacebiff.com/article-2"
    assert result[1]["title"] == "Second Article"
    assert result[1]["timestamp"] == "2024-01-16T12:00:00+00:00"


def test_fetch_feed_raises_on_malformed_xml():
    """Malformed XML content raises ET.ParseError."""
    mock_response = MagicMock()
    mock_response.content = b"this is not xml at all <<<"
    mock_response.raise_for_status = MagicMock()

    with patch("requests.get", return_value=mock_response):
        with pytest.raises(ET.ParseError):
            fetcher.fetch_feed("https://example.com/feed")
