"""Configuration loading from TOML files."""

from __future__ import annotations

import logging
import sys
from dataclasses import dataclass
from pathlib import Path

if sys.version_info >= (3, 11):
    import tomllib
else:
    import tomli as tomllib

logger = logging.getLogger(__name__)


@dataclass
class Config:
    pinboard_feed_url: str
    cache_dir: Path
    output_dir: Path
    max_fetch_attempts: int = 10
    max_articles_per_run: int = 30


def load_config(path: str | None = None) -> Config:
    """Load configuration from a TOML file.

    Searches for config.toml in the current directory if no path is given.
    """
    config_path = Path(path) if path is not None else Path("config.toml")

    if not config_path.exists():
        raise FileNotFoundError(f"Config file not found: {config_path}")

    with open(config_path, "rb") as f:
        data = tomllib.load(f)

    url = data["pinboard_feed_url"]
    if not url or not url.startswith(("http://", "https://")):
        raise ValueError(
            f"pinboard_feed_url must be a URL starting with http:// or https://, got: {url!r}"
        )

    return Config(
        pinboard_feed_url=url,
        cache_dir=Path(data["cache_dir"]).expanduser(),
        output_dir=Path(data["output_dir"]).expanduser(),
        max_fetch_attempts=data.get("max_fetch_attempts", 10),
        max_articles_per_run=data.get("max_articles_per_run", 30),
    )
