"""Tests covering article extraction."""

import lxml

from unittest.mock import patch

from tests.conftest import sample_html

import courier
from courier.extractor import MIN_CONTENT_LENGTH, extract_article


# ---------------------------------------------------------------------------
# Main tests
# ---------------------------------------------------------------------------


def test_extract_article_returns_tuple(sample_html):
    (title, xhtml) = extract_article(sample_html)
    assert len(title) > 0
    assert len(xhtml) > 0


def test_extract_article_xhtml_is_valid_xml(sample_html):
    (title, xhtml) = extract_article(sample_html)
    # Should not throw an error
    lxml.etree.fromstring(xhtml)


@patch("courier.extractor._extract_with_trafilatura")
@patch("courier.extractor._extract_with_readability")
def test_readability_fallback_to_trafilatura(mock_extract_with_readability, mock_extract_with_trafilatura):
    mock_extract_with_readability.return_value = ("", "too short")
    mock_extract_with_trafilatura.return_value = "long" * 100
    (title, xhtml) = extract_article(sample_html)

    mock_extract_with_readability.assert_called_once()
    mock_extract_with_trafilatura.assert_called_once()
    assert len(xhtml) > MIN_CONTENT_LENGTH
