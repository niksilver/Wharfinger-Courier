"""Configuration loading from TOML files."""

import sys
from dataclasses import dataclass
from pathlib import Path

if sys.version_info >= (3, 11):
    import tomllib
else:
    import tomli as tomllib


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
    if path is not None:
        config_path = Path(path)
    else:
        config_path = Path("config.toml")

    if not config_path.exists():
        raise FileNotFoundError(f"Config file not found: {config_path}")

    with open(config_path, "rb") as f:
        data = tomllib.load(f)

    return Config(
        pinboard_feed_url=data["pinboard_feed_url"],
        cache_dir=Path(data["cache_dir"]).expanduser(),
        output_dir=Path(data["output_dir"]).expanduser(),
        max_fetch_attempts=data.get("max_fetch_attempts", 10),
        max_articles_per_run=data.get("max_articles_per_run", 30),
    )
