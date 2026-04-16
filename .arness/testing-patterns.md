# Testing Patterns

## Test Framework
- **Runner:** pytest
- **Configuration:** `pyproject.toml` — testpaths set to `["tests"]`; pytest-cov available for coverage

## Test Organization

### Pattern: tests/ directory for all automated tests
**Description:** All automated tests live in the top-level `tests/` directory — smoke tests, unit tests, and integration tests alike. The directory has an `__init__.py` to make it a package. Test files follow the `test_*.py` naming convention.
**Reference:** `tests/`
**Example:**
```
tests/
├── __init__.py
├── test_smoke.py      # Import sanity and basic dataclass checks
├── test_config.py     # Unit tests for config loading (to be added)
├── test_fetcher.py    # Unit tests for HTTP fetching (to be added)
├── test_extractor.py  # Unit tests for content extraction (to be added)
└── test_compiler.py   # Unit tests for XHTML compilation (to be added)
```
**How to apply:** Place all new test files directly in `tests/`. Do not create subdirectories. Name files `test_<module>.py` to mirror the module under test.

### Pattern: Smoke tests for project skeleton
**Description:** `test_smoke.py` verifies that the package imports correctly, core dataclasses instantiate with expected defaults, and utility functions produce deterministic results. These are the fastest-running tests and catch packaging or import breakage early.
**Reference:** `tests/test_smoke.py`
**Example:**
```python
import courier
from courier.config import Config
from courier.store import url_hash


def test_version():
    assert courier.__version__ == "0.1.0"


def test_config_dataclass():
    from pathlib import Path

    config = Config(
        pinboard_feed_url="https://example.com/feed",
        cache_dir=Path("/tmp/test-cache"),
        output_dir=Path("/tmp/test-output"),
    )
    assert config.max_fetch_attempts == 10
    assert config.max_articles_per_run == 30


def test_url_hash_deterministic():
    h1 = url_hash("https://example.com/article")
    h2 = url_hash("https://example.com/article")
    assert h1 == h2
    assert len(h1) == 64  # SHA-256 hex digest
```

### Pattern: Plain assertions without fixtures
**Description:** Tests use plain `assert` statements and construct test data directly in each test function. No shared fixtures or conftest.py yet.
**Reference:** `tests/test_smoke.py`
**How to apply:** When adding tests for fetcher, extractor, and compiler, consider adding a `conftest.py` with fixtures for temporary directories (`tmp_path`), sample HTML content, and mock Config objects as complexity grows.
