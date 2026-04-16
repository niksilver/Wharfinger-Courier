"""HTTP fetching for Pinboard feed and article URLs."""

from __future__ import annotations

import logging
from typing import Any

import requests

logger = logging.getLogger(__name__)

DEFAULT_TIMEOUT = 30


def fetch_feed(feed_url: str, timeout: int = DEFAULT_TIMEOUT) -> list[dict[str, Any]]:
    """Fetch the Pinboard JSON feed and return the list of bookmark objects."""
    logger.info("Fetching Pinboard feed: %s", feed_url)
    response = requests.get(feed_url, timeout=timeout)
    response.raise_for_status()
    return response.json()


def fetch_article(url: str, timeout: int = DEFAULT_TIMEOUT) -> str:
    """Fetch a single article URL and return its HTML content."""
    logger.info("Fetching article: %s", url)
    response = requests.get(url, timeout=timeout)
    response.raise_for_status()
    return response.text
