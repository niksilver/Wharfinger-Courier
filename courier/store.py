"""Filesystem store for cache and status management."""

from __future__ import annotations

import hashlib
import json
import logging
from pathlib import Path
from typing import Any

logger = logging.getLogger(__name__)


def url_hash(url: str) -> str:
    """Return the SHA-256 hex digest of a URL, used as the cache directory name."""
    return hashlib.sha256(url.encode("utf-8")).hexdigest()


def read_status(cache_dir: Path) -> dict[str, Any]:
    """Read status.json from the cache directory. Returns empty dict if not found."""
    status_path = cache_dir / "status.json"
    if not status_path.exists():
        return {}
    return json.loads(status_path.read_text(encoding="utf-8"))


def write_status(cache_dir: Path, status: dict[str, Any]) -> None:
    """Write status.json atomically to the cache directory."""
    cache_dir.mkdir(parents=True, exist_ok=True)
    status_path = cache_dir / "status.json"
    tmp_path = status_path.with_suffix(".json.tmp")
    tmp_path.write_text(json.dumps(status, indent=2), encoding="utf-8")
    tmp_path.replace(status_path)
    logger.info("Status written to %s", status_path)


def read_cached_html(cache_dir: Path, article_url: str, stage: str) -> str | None:
    """Read cached HTML for an article. Stage is 'raw' or 'extracted'."""
    path = cache_dir / "cache" / url_hash(article_url) / f"{stage}.html"
    if path.exists():
        return path.read_text(encoding="utf-8")
    return None


def write_cached_html(cache_dir: Path, article_url: str, stage: str, content: str) -> None:
    """Write HTML to the article cache. Stage is 'raw' or 'extracted'."""
    article_dir = cache_dir / "cache" / url_hash(article_url)
    article_dir.mkdir(parents=True, exist_ok=True)
    path = article_dir / f"{stage}.html"
    path.write_text(content, encoding="utf-8")
