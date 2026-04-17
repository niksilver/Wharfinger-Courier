# Assessment Improvements

## Project Overview

**Project Name:** assessment-improvements
**Description:** Address 12 codebase quality findings from a full-codebase assessment: add test coverage for three untested pipeline modules, fix CLI error handling, replace `print()` with structured logging, wire in the documented-but-dead HTML caching layer, rename `_filter_articles` to `filter_articles`, and apply consistency fixes (loggers, `from __future__ import annotations`, module-level Jinja2 environment, config validation, trust boundary comment).
**Rationale:** Three core pipeline modules (extractor, compiler, store) have zero test coverage. The CLI exposes raw tracebacks on config errors. The orchestrator mixes I/O into pipeline logic. The filesystem caching strategy is documented as a key architectural decision but is dead code. Several small consistency deviations accumulate maintenance burden.

**Goals:**
1. Achieve test coverage for extractor, compiler, and store modules with shared pytest fixtures in `conftest.py`
2. Make the CLI fail gracefully on bad config with user-friendly messages and exit code 1
3. Remove all `print()` calls from the orchestrator in favour of structured logging
4. Wire the HTML cache into `_process_article()` so the documented caching strategy is actually used
5. Apply six small consistency fixes: loggers, future annotations, Jinja2 env, trust comment, config validation, `_filter_articles` rename

---

## Architectural Definition

### High-Level Architecture

```
config.toml
    │
    ▼
courier/__main__.py  ──[try/except]──►  stderr + exit 1
    │
    ▼
courier/config.py    ──[validates URL]──► Config dataclass
    │
    ▼
courier/orchestrator.py  run_pipeline()
    ├── courier/fetcher.py      fetch_feed() → items
    │   └── filter_articles()   ← renamed (was _filter_articles)
    └── _process_article(item)
          ├── store.read_cached_html()  ← NEW (cache check before fetch)
          ├── fetcher.fetch_article()   (only if cache miss)
          ├── store.write_cached_html() ← NEW (write raw after fetch)
          ├── extractor.extract_article()
          ├── store.write_cached_html() ← NEW (write extracted after extract)
          └── compiler.compile_document()
                └── _template.render()  ← module-level Jinja2 env (NEW)

tests/
  conftest.py           ← NEW: make_config, sample_html, sample_feed_xml
  test_smoke.py
  test_orchestrator.py  ← UPDATED: capsys→caplog, mock_store fix, filter_articles import
  test_extractor.py     ← NEW
  test_store.py         ← NEW
  test_compiler.py      ← NEW
```

### Key Architectural Decisions

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Dead cache functions | Wire into `_process_article()` | Architecture doc lists filesystem caching as a key decision; prevents re-fetch/re-extract on retry runs |
| Extracted title when serving from cache | Use feed item title from `item["title"]` | Title already in item and persisted in status_data; avoids cache format changes |
| Replace `print()` | `logger.info()` | Keeps I/O out of pipeline logic; CLI already routes logging to stderr via `basicConfig` |
| Jinja2 Environment | Module-level `_env`/`_template` | Template is package data, always present; fail-fast at import time; avoids per-call overhead |
| `_filter_articles` rename | `filter_articles` (public) | Function has a tested, stable contract and is directly imported by tests; underscore prefix signals private but it is intentionally exposed |

---

## Codebase Patterns

### Pattern 1: Module-level logger and constants

**Description:** Every module creates its own logger via `logging.getLogger(__name__)` immediately after imports, followed by UPPER_CASE module-level constants. The import block order is: `from __future__ import annotations`, stdlib, third-party, local.
**Reference Files:**
- `courier/extractor.py:1-13`

**Code Example (from codebase):**
```python
"""Content extraction from raw HTML using readability-lxml and trafilatura."""

from __future__ import annotations

import logging

from lxml.html import fromstring as html_fromstring
from lxml.html import tostring as html_tostring
from readability import Document

logger = logging.getLogger(__name__)

MIN_CONTENT_LENGTH = 200
```

**How to apply:** When adding logger to `config.py` and `__main__.py`, place `import logging` in the imports block and `logger = logging.getLogger(__name__)` after the last import, before the first class or function definition. Place `from __future__ import annotations` as the first import.

---

### Pattern 2: Standard exceptions for configuration errors

**Description:** Configuration errors raise standard Python exceptions (`FileNotFoundError`, `KeyError`, `ValueError`) with clear messages. The CLI entry point catches them and converts to exit codes.
**Reference Files:**
- `courier/config.py:32-33`

**Code Example (from codebase):**
```python
if not config_path.exists():
    raise FileNotFoundError(f"Config file not found: {config_path}")
```

**How to apply:** In `load_config()`, validate `pinboard_feed_url` before constructing `Config`. Raise `ValueError` with a descriptive message. In `__main__.py`, wrap `load_config()` in try/except catching `FileNotFoundError`, `KeyError`, and `ValueError`; print to `sys.stderr` and return 1.

---

### Pattern 3: Private helper functions with underscore prefix

**Description:** Internal helper functions use a leading underscore. Public functions with a stable contract have no prefix.
**Reference Files:**
- `courier/extractor.py:34-53`
- `courier/orchestrator.py:74`

**Code Example (from codebase):**
```python
def _extract_with_readability(raw_html: str) -> tuple[str, str]:
    """Internal — not part of public API."""
    ...

def extract_article(raw_html: str, url: str = "") -> tuple[str, str]:
    """Public API — no underscore."""
    ...
```

**How to apply:** `_filter_articles` is renamed to `filter_articles` because it has a stable, tested contract and is directly imported by tests. Module-level constants like `_env` and `_template` in `compiler.py` follow the underscore convention for private module-level state.

---

### Pattern 4: Fallback strategy in extraction

**Description:** The extractor uses readability-lxml as the primary engine and falls back to trafilatura when content is shorter than `MIN_CONTENT_LENGTH` (200 chars), logging the decision.
**Reference Files:**
- `courier/extractor.py:16-31`

**Code Example (from codebase):**
```python
def extract_article(raw_html: str, url: str = "") -> tuple[str, str]:
    title, content = _extract_with_readability(raw_html)

    if len(content) < MIN_CONTENT_LENGTH:
        logger.info("Readability output too short (%d chars), trying trafilatura", len(content))
        fallback_content = _extract_with_trafilatura(raw_html, url)
        if fallback_content and len(fallback_content) > len(content):
            content = fallback_content

    xhtml_content = _sanitise_to_xhtml(content)
    return title, xhtml_content
```

**How to apply:** When testing the extractor, use `unittest.mock.patch` on `courier.extractor._extract_with_readability` to return short content and trigger the fallback path.

---

### Pattern 5: TOML config with dataclass mapping

**Description:** Configuration is loaded from a TOML file into a typed `Config` dataclass. Optional fields use `dict.get()` with defaults. Path fields use `.expanduser()`.
**Reference Files:**
- `courier/config.py:13-19`, `courier/config.py:22-44`

**Code Example (from codebase):**
```python
@dataclass
class Config:
    pinboard_feed_url: str
    cache_dir: Path
    output_dir: Path
    max_fetch_attempts: int = 10
    max_articles_per_run: int = 30
```

**How to apply:** The `Config` dataclass is the shared test fixture type. Construct it directly in tests using `tmp_path` for `cache_dir` and `output_dir`.

---

### Testing Pattern 1: pytest with plain assertions

**Description:** Tests use plain `assert` statements. All test files live in `tests/` and follow `test_<module>.py` naming. After Phase 3, `conftest.py` provides shared fixtures.
**Reference Files:**
- `tests/test_smoke.py`
- `tests/test_orchestrator.py`

**Code Example (from tests):**
```python
from courier.config import Config
from courier.store import url_hash

def test_url_hash_deterministic():
    h1 = url_hash("https://example.com/article")
    h2 = url_hash("https://example.com/article")
    assert h1 == h2
    assert len(h1) == 64  # SHA-256 hex digest
```

**Test infrastructure available:**
- `tmp_path` — pytest built-in for temporary directories
- `caplog` — pytest built-in for asserting on log output (used after Phase 2's print→logger change)
- `unittest.mock.patch` — for mocking pipeline modules in orchestrator tests
- After Phase 3: `conftest.py` provides `config`, `make_config`, `sample_html`, `sample_feed_xml`

---

### Testing Pattern 2: Mocking pipeline modules in orchestrator tests

**Description:** Orchestrator tests mock `fetcher`, `store`, `extractor`, and `compiler` at module level using `@patch`. After Phase 2's cache wiring, all tests that mock `store` must set `mock_store.read_cached_html.return_value = None` to force the fetch-from-network path.
**Reference Files:**
- `tests/test_orchestrator.py:1-50`

**Code Example (from tests):**
```python
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.store")
@patch("courier.orchestrator.fetcher")
def test_happy_path(mock_fetcher, mock_store, mock_extractor, mock_compiler, tmp_path):
    mock_store.read_cached_html.return_value = None  # Required after Phase 2 cache wiring
    ...
```

**Test infrastructure available:** After Phase 3, the `make_config` fixture from `conftest.py` replaces the inline `_make_config` helper. The import of `_filter_articles` (Phase 1) becomes `filter_articles`.

---

## Codebase References

| Area | File Path | Purpose |
|------|-----------|---------|
| CLI entry point | `courier/__main__.py` | Argument parsing, logging setup, pipeline invocation |
| Configuration | `courier/config.py` | TOML loading into `Config` dataclass |
| HTTP layer | `courier/fetcher.py` | Fetches Pinboard feed and article HTML |
| Extraction | `courier/extractor.py` | Dual-engine extraction (readability + trafilatura fallback) |
| Compilation | `courier/compiler.py` | Jinja2 XHTML document compilation |
| Cache/store | `courier/store.py` | Filesystem cache + status (SHA-256 URL hashing) |
| Orchestrator | `courier/orchestrator.py` | Coordinates fetch-extract-compile pipeline |
| XHTML template | `courier/templates/document.xhtml` | Kindle-compatible XHTML output template |
| Package config | `pyproject.toml` | Dependencies, pytest config (`testpaths = ["tests"]`) |
| Smoke tests | `tests/test_smoke.py` | Import sanity, Config dataclass, url_hash |
| Orchestrator tests | `tests/test_orchestrator.py` | Pipeline integration tests with mocked modules |

---

## Scope & Boundaries

**In Scope:**
- All 12 assessment findings: ASSESS-TEST-001, ASSESS-TEST-002, ASSESS-ARCH-001 through ASSESS-ARCH-004, ASSESS-MAINT-001 through ASSESS-MAINT-005, ASSESS-PERF-001
- Updating existing orchestrator tests broken by print→logger, cache wiring, and `filter_articles` rename

**Out of Scope:**
- Adding tests for `fetcher.py`
- Changing the cache storage format or adding a title sidecar
- Any new features or behavioral changes beyond the assessment findings

---

## Dependencies

**External:**
- `pytest` — test runner (already a dev dependency)
- `lxml` — used in extractor tests (transitive dependency via readability-lxml)
- `jinja2` — `Environment`, `PackageLoader` (already a direct dependency)

**Internal:**
- `courier.store` — `read_cached_html`, `write_cached_html`, `url_hash`, `read_status`, `write_status`
- `courier.extractor` — `extract_article`, `_extract_with_readability`, `_sanitise_to_xhtml`
- `courier.compiler` — `compile_document`, `Article` dataclass
- `courier.config` — `Config` dataclass, `load_config`

---

## Phase Overview

| Phase | Title | Dependencies | Test Deps (sequential chain) |
|-------|-------|--------------|------------------------------|
| 1 | Code Hygiene | None | Task 4 depends on Task 1 |
| 2 | Behavioral Improvements | Phase 1 | Task 5 depends on Task 2, Task 4 |
| 3 | Test Infrastructure & New Test Modules | Phase 2 | Task 6 depends on Task 3, Task 5 |

---

## Metadata

- **Created:** 2026-04-17
- **Source plan:** SOURCE_PLAN.md
- **Specification:** .arness/specs/FEATURE_assessment-improvements.md
