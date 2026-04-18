"""HTTP fetching for RSS feed and article URLs."""

from __future__ import annotations

import email.utils
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
    """Fetch an RSS feed and return bookmark dicts with url, title, timestamp."""
    logger.info("Fetching RSS feed")
    response = requests.get(feed_url, timeout=timeout)
    response.raise_for_status()
    return _parse_feed(response.content)


def fetch_article(url: str, timeout: int = DEFAULT_TIMEOUT) -> str:
    """Fetch a single article URL and return its HTML content."""
    logger.info("Fetching article: %s", url)
    response = requests.get(url, timeout=timeout)
    response.raise_for_status()
    return response.text


def _parse_feed(xml_content: bytes) -> list[dict[str, Any]]:
    """Parse an RSS feed (1.0 RDF or 2.0) and return dicts with url, title, timestamp."""
    root = ET.fromstring(xml_content)
    if root.tag == "rss":
        return _parse_rss2_feed(root)
    return _parse_rdf10_feed(root)


def _parse_rdf10_feed(root: ET.Element) -> list[dict[str, Any]]:
    """Parse RDF/RSS 1.0 XML and return a list of dicts with url, title, timestamp."""
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


def _parse_rss2_feed(root: ET.Element) -> list[dict[str, Any]]:
    """Parse RSS 2.0 XML and return a list of dicts with url, title, timestamp."""
    result = []
    for item in root.findall("channel/item"):
        link_el = item.find("link")
        url = link_el.text.strip() if link_el is not None and link_el.text else ""

        title_el = item.find("title")
        title = title_el.text.strip() if title_el is not None and title_el.text else ""

        pub_date_el = item.find("pubDate")
        timestamp = ""
        if pub_date_el is not None and pub_date_el.text:
            timestamp = _rfc822_to_iso(pub_date_el.text.strip())

        result.append({"url": url, "title": title, "timestamp": timestamp})
    return result


def _rfc822_to_iso(rfc822: str) -> str:
    """Convert an RFC 822 date string to ISO 8601. Returns empty string on failure."""
    try:
        dt = email.utils.parsedate_to_datetime(rfc822)
        return dt.isoformat()
    except Exception:
        return ""
