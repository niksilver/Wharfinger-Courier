"""Tests covering article extraction."""

from tests.conftest import sample_html

import courier
from courier.extractor import extract_article


def test_extract_article_returns_tuple(sample_html):
    (title, xhtml) = extract_article(sample_html)
    assert len(title) > 0
    assert len(xhtml) > 0
