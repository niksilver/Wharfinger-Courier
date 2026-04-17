# Assessment: Full Codebase

**Date:** 2026-04-17
**Agents:** arn-code-architect
**Scope:** Entire codebase

## Summary

- **Categories assessed:** architecture, performance, maintainability, testing
- **Total findings:** 12 (1 high, 5 medium, 6 low)

## Findings

### Architecture

#### ASSESS-ARCH-001 [medium]
- **Title:** Config module does not validate URLs or paths
- **Description:** `load_config()` passes through whatever strings are in the TOML file without validation. A missing or empty `pinboard_feed_url`, a `cache_dir` pointing to a read-only location, or a non-URL string would only surface as a confusing error much later in the pipeline. The error handling pattern says "raise standard exceptions for configuration errors," but there is no validation to trigger them.
- **Affected files:** `courier/config.py:38-44`
- **Suggested approach:** Add basic validation in `load_config()` — at minimum check that `pinboard_feed_url` is non-empty and starts with `http`. A `ValueError` with a clear message follows the existing pattern of using standard exceptions.

#### ASSESS-ARCH-002 [medium]
- **Title:** Orchestrator uses `print()` for user-facing output
- **Description:** The orchestrator calls `print()` directly in three places: `_print_summary()`, dry-run output, and the archive cap message. This mixes I/O concerns into the pipeline logic, making it harder to test output (requires `capsys`) and impossible to redirect programmatically. The documented architecture positions the orchestrator as a coordinator, not a UI layer.
- **Affected files:** `courier/orchestrator.py:35-38`, `courier/orchestrator.py:129-130`, `courier/orchestrator.py:209-211`
- **Suggested approach:** Use `logger.info()` for all user-facing messages (the CLI already routes logging to stderr), or return structured results from `run_pipeline()` and let `__main__.py` handle all printing.

#### ASSESS-ARCH-003 [medium]
- **Title:** XHTML template uses `| safe` despite autoescape=True
- **Description:** The compiler creates the Jinja2 environment with `autoescape=True`, but the template uses `{{ article.content | safe }}` to render extracted HTML content unescaped. While the risk is limited for an offline Kindle document generator, any bug in the extraction pipeline or malicious content would pass through directly into the output document. The autoescape setting creates a false sense of security.
- **Affected files:** `courier/templates/document.xhtml:21`, `courier/compiler.py:29`
- **Suggested approach:** This is acceptable for the current use case. Document it as a known design choice with a comment in compiler.py explaining why `| safe` is used and the assumed trust boundary.

#### ASSESS-ARCH-004 [medium]
- **Title:** `__main__.py` does not catch `load_config` exceptions
- **Description:** The error handling pattern states "the CLI entry point converts the pipeline result to an exit code" and "configuration errors raise standard Python exceptions." However, `main()` calls `load_config(args.config)` without a try/except. If the config file is missing or has a missing key, the exception propagates as an unhandled traceback to the user instead of a clean error message and exit code 1.
- **Affected files:** `courier/__main__.py:51`
- **Suggested approach:** Wrap `load_config()` in a try/except for `FileNotFoundError` and `KeyError`, print a user-friendly message to stderr, and return exit code 1. This completes the error handling pattern as documented.

### Performance

#### ASSESS-PERF-001 [low]
- **Title:** `compile_document` recreates Jinja2 Environment on every call
- **Description:** Each call to `compile_document()` creates a new `Environment` and `PackageLoader`, then calls `get_template()`. In the current single-document-per-run architecture this is negligible, but it is an anti-pattern if the function is ever called in a loop or from tests. The template and environment are stateless and could be module-level.
- **Affected files:** `courier/compiler.py:27-31`
- **Suggested approach:** Move the `Environment` and template loading to module-level constants, or use a lazy-initialized module-level variable.

### Maintainability

#### ASSESS-MAINT-001 [low]
- **Title:** Missing logger in `config.py`
- **Description:** The documented pattern states each module should create its own logger via `logging.getLogger(__name__)`. The `config.py` module does not define a logger, while all other modules (`store.py`, `extractor.py`, `compiler.py`, `fetcher.py`, `orchestrator.py`) do. This is a minor consistency deviation.
- **Affected files:** `courier/config.py` (missing logger after imports)
- **Suggested approach:** Add `import logging` and `logger = logging.getLogger(__name__)` after imports, before the `Config` dataclass definition.

#### ASSESS-MAINT-002 [low]
- **Title:** Missing logger in `__main__.py`
- **Description:** Same pattern deviation as config.py — the `__main__.py` module configures logging but never creates its own module-level logger. While it currently has no log statements, the pattern requires one per module for consistency.
- **Affected files:** `courier/__main__.py` (missing logger)
- **Suggested approach:** Add `logger = logging.getLogger(__name__)` for consistency. Lower priority since the CLI entry point mostly delegates to the orchestrator.

#### ASSESS-MAINT-003 [low]
- **Title:** Missing `from __future__ import annotations` in two modules
- **Description:** Five modules (`store.py`, `extractor.py`, `compiler.py`, `fetcher.py`, `orchestrator.py`) all use `from __future__ import annotations`, but `config.py` and `__main__.py` do not. Both use the `str | None` union syntax which works on Python 3.10+ at runtime, but the inconsistency is a maintenance concern.
- **Affected files:** `courier/config.py:1`, `courier/__main__.py:1`
- **Suggested approach:** Add `from __future__ import annotations` to both files for consistency with all other modules.

#### ASSESS-MAINT-004 [medium]
- **Title:** HTML cache functions exist but are never called (dead code)
- **Description:** The `store` module provides `read_cached_html()` and `write_cached_html()` functions, but the orchestrator never calls them. Articles are fetched and extracted every run with no caching of intermediate results. This contradicts the architecture doc which lists "Caching strategy: Filesystem-based with SHA-256 URL hashing" as a key architectural decision.
- **Affected files:** `courier/store.py:37-50`, `courier/orchestrator.py:168-169`
- **Suggested approach:** Wire the cache into `_process_article()`: check `store.read_cached_html()` before calling `fetcher.fetch_article()`; write the result with `store.write_cached_html()` after extraction. Or remove the functions if caching is intentionally deferred.

#### ASSESS-MAINT-005 [low]
- **Title:** Private `_filter_articles` function tested directly
- **Description:** The naming pattern says underscore-prefixed functions are internal implementation details. However, `test_orchestrator.py` imports `_filter_articles` directly and tests it as a unit. Either the function should be public (its contract is being tested) or tests should exercise it through `run_pipeline`.
- **Affected files:** `tests/test_orchestrator.py:13`, `courier/orchestrator.py:74`
- **Suggested approach:** Rename `_filter_articles` to `filter_articles` (no underscore) to signal it is a tested public function of the module with a stable contract.

### Testing

#### ASSESS-TEST-001 [high]
- **Title:** No tests for extractor, compiler, or store modules
- **Description:** Three of the six pipeline modules have zero test coverage: `extractor.py`, `compiler.py`, and `store.py`. The extractor is a critical pipeline stage with fallback logic (readability to trafilatura) that should be verified. The store handles caching and status persistence. The testing patterns doc itself notes these modules need tests (`test_extractor.py`, `test_compiler.py`, `test_store.py` listed as "to be added").
- **Affected files:** No `test_extractor.py`, `test_compiler.py`, or `test_store.py` in `tests/`
- **Suggested approach:** Add test files for each module. Prioritize `test_extractor.py` (fallback logic, XHTML sanitisation) and `test_store.py` (read/write round-trips, atomic writes). Add a `conftest.py` with shared fixtures (see ASSESS-TEST-002).

#### ASSESS-TEST-002 [low]
- **Title:** No `conftest.py` despite growing test complexity
- **Description:** The testing patterns doc notes a `conftest.py` should be added "as complexity grows." The test suite now has multiple test files, with `test_orchestrator.py` defining its own `config` fixture and helper functions that would be useful across other test files. This duplication will grow as extractor, compiler, and store tests are added.
- **Affected files:** `tests/test_orchestrator.py:19-31,274-281` (duplicated fixture helpers)
- **Suggested approach:** Create `tests/conftest.py` with shared fixtures: a `config` fixture using `tmp_path`, and sample HTML/feed content that multiple test files will need.
