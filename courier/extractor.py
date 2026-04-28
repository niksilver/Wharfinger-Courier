"""Content extraction from raw HTML using readability-lxml and trafilatura."""

from __future__ import annotations

import logging

import lxml
from lxml.html import fromstring as html_fromstring
from lxml.html import tostring as html_tostring
from readability import Document

logger = logging.getLogger(__name__)

MIN_CONTENT_LENGTH = 200


def extract_article(raw_html: str, url: str = "") -> tuple[str, str]:
    """Extract article content from raw HTML.

    Returns a (title, xhtml_content) tuple. Uses readability-lxml as primary
    extractor with trafilatura as fallback.
    """
    title, content = _extract_with_readability(raw_html)

    if len(content) < MIN_CONTENT_LENGTH:
        logger.info("Readability output too short (%d chars), trying trafilatura", len(content))
        fallback_content = _extract_with_trafilatura(raw_html, url)
        if fallback_content and len(fallback_content) > len(content):
            content = fallback_content

    xhtml_content = _sanitise_to_xhtml(content)
    return title, xhtml_content


def _extract_with_readability(raw_html: str) -> tuple[str, str]:
    """Extract using readability-lxml. Returns (title, html_content).

    If it encounters a parsing error it just return empty strings.
    """
    try:
        doc = Document(raw_html)
        return doc.title(), doc.summary()
    except lxml.etree.ParserError:
        return "", ""


def _extract_with_trafilatura(raw_html: str, url: str = "") -> str:
    """Extract using trafilatura as fallback. Returns HTML content."""
    import trafilatura

    result = trafilatura.extract(raw_html, url=url, output_format="html", include_links=True)
    return result or ""


def _sanitise_to_xhtml(html_content: str) -> str:
    """Sanitise HTML content to valid XHTML, returning only body contents."""
    if not html_content.strip():
        return ""
    tree = html_fromstring(html_content)
    bodies = tree.xpath(".//body")
    if bodies:
        body = bodies[0]
        return (body.text or "") + "".join(
            html_tostring(child, method="xml", encoding="unicode") for child in body
        )
    return html_tostring(tree, method="xml", encoding="unicode")
