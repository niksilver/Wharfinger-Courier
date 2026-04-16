"""CLI entry point for the Courier pipeline."""

import argparse
import logging
import sys

from courier.config import load_config
from courier.orchestrator import run_pipeline


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

    log_level = logging.DEBUG if args.verbose else logging.INFO
    logging.basicConfig(
        level=log_level,
        format="%(asctime)s %(levelname)s %(name)s: %(message)s",
        stream=sys.stderr,
    )

    config = load_config(args.config)
    result = run_pipeline(config, since_days=args.since, dry_run=args.dry_run)

    return 0 if result else 1


if __name__ == "__main__":
    sys.exit(main())
