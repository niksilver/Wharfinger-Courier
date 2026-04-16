"""Pipeline orchestrator — coordinates fetch, extract, and compile stages."""

from __future__ import annotations

import logging

from courier.config import Config

logger = logging.getLogger(__name__)


def run_pipeline(
    config: Config,
    since_days: int | None = None,
    dry_run: bool = False,
) -> bool:
    """Run the full courier pipeline. Returns True on success."""
    logger.info("Starting courier pipeline (dry_run=%s, since_days=%s)", dry_run, since_days)

    # Placeholder — feature code will be added by task executor
    logger.info("Pipeline not yet implemented")
    return True
