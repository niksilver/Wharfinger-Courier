"""CLI entry point for the Courier pipeline."""

from __future__ import annotations

import argparse
import logging
import sys

from courier.config import load_config
from courier.orchestrator import run_pipeline

logger = logging.getLogger(__name__)


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(
        prog="courier",
        description="Fetch Pinboard toread articles and compile into Kindle XHTML.",
    )
    parser.add_argument(
        "--since",
        type=int,
        metavar="N",
        help="Include all articles bookmarked within the last N days (archive mode).",
    )
    parser.add_argument(
        "--dry-run",
        action="store_true",
        help="Show what would be fetched and compiled without making changes.",
    )
    parser.add_argument(
        "--config",
        metavar="PATH",
        help="Path to config file (default: config.toml in current directory).",
    )
    parser.add_argument(
        "--verbose",
        action="store_true",
        help="Enable verbose logging to stderr.",
    )

    args = parser.parse_args(argv)

    if args.since is not None and args.since < 1:
        print("Error: --since requires a positive integer.", file=sys.stderr)
        return 1

    log_level = logging.DEBUG if args.verbose else logging.INFO
    logging.basicConfig(
        level=log_level,
        format="%(asctime)s %(levelname)s %(name)s: %(message)s",
        stream=sys.stderr,
    )

    try:
        config = load_config(args.config)
    except FileNotFoundError as exc:
        print(f"Error: {exc}", file=sys.stderr)
        return 1
    except KeyError as exc:
        print(f"Configuration error: missing required key {exc}", file=sys.stderr)
        return 1
    except ValueError as exc:
        print(f"Configuration error: {exc}", file=sys.stderr)
        return 1

    result = run_pipeline(config, since_days=args.since, dry_run=args.dry_run)

    return 0 if result else 1


if __name__ == "__main__":
    sys.exit(main())
