"""HTTP fetching for RDF/RSS feed and article URLs."""

from __future__ import annotations

import logging
import xml.etree.ElementTree as ET
from typing import Any

import requests

logger = logging.getLogger(__name__)

DEFAULT_TIMEOUT = 30

_RDF_NS   = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
_RSS10_NS = "http://purl.org/rss/1.0/"
_DC_NS    = "http://purl.org/dc/elements/1.1/"


def fetch_feed(feed_url: str, timeout: int = DEFAULT_TIMEOUT) -> list[dict[str, Any]]:
    """Fetch the RDF/RSS 1.0 feed and return bookmark dicts with url, title, timestamp."""
    logger.info("Fetching RDF/RSS feed")
    response = requests.get(feed_url, timeout=timeout)
    response.raise_for_status()
    return _parse_rdf_feed(response.content)


def fetch_article(url: str, timeout: int = DEFAULT_TIMEOUT) -> str:
    """Fetch a single article URL and return its HTML content."""
    logger.info("Fetching article: %s", url)
    response = requests.get(url, timeout=timeout)
    response.raise_for_status()
    return response.text


def _parse_rdf_feed(xml_content: bytes) -> list[dict[str, Any]]:
    """Parse RDF/RSS 1.0 XML and return a list of dicts with url, title, timestamp."""
    root = ET.fromstring(xml_content)
    items = root.findall(f"{{{_RSS10_NS}}}item")
    result = []
    for item in items:
        url = item.get(f"{{{_RDF_NS}}}about") or ""
        if not url:
            link_el = item.find(f"{{{_RSS10_NS}}}link")
            if link_el is not None and link_el.text:
                url = link_el.text.strip()

        title_el = item.find(f"{{{_RSS10_NS}}}title")
        title = title_el.text.strip() if title_el is not None and title_el.text else ""

        dc_date_el = item.find(f"{{{_DC_NS}}}date")
        timestamp = dc_date_el.text.strip() if dc_date_el is not None and dc_date_el.text else ""

        result.append({"url": url, "title": title, "timestamp": timestamp})
    return result
