# Implementation Plan: Assessment Improvements

Spec: .arness/specs/FEATURE_assessment-improvements.md

## Overview

This plan addresses 11 codebase quality findings from a full-codebase assessment. The work is organised into three phases: mechanical code hygiene changes with no behavioral impact (Phase 1), behavioral improvements applied in dependency order (Phase 2), and new test infrastructure plus three new test modules (Phase 3). Each phase produces a testable increment and existing tests must continue to pass throughout.

## Phase 1: Code Hygiene

**Objective:** Add `from __future__ import annotations` and `logger = logging.getLogger(__name__)` to the two modules that lack them (`config.py`, `__main__.py`). These are zero-risk, no-behaviour-change additions.

**Dependencies:** None

**Deliverables:**

| File | Action | Description |
|------|--------|-------------|
| `courier/config.py` | Modify | Add `from __future__ import annotations` and `logger = logging.getLogger(__name__)` |
| `courier/__main__.py` | Modify | Add `from __future__ import annotations` and `logger = logging.getLogger(__name__)` |

**Tasks:**

1. IMPL-P1-001: Add `from __future__ import annotations` as the first import in `courier/config.py`. Add `import logging` to the imports block and `logger = logging.getLogger(__name__)` after the imports, before the `Config` dataclass. Follow the module-level logger pattern from code-patterns.md.

2. IMPL-P1-002: Add `from __future__ import annotations` as the first import in `courier/__main__.py`. Add `logger = logging.getLogger(__name__)` after the existing imports (the `import logging` is already present). Follow the module-level logger pattern from code-patterns.md.

**Testing:**
- Run `pytest` to confirm all existing tests pass with no regressions.

## Phase 2: Behavioral Improvements

**Objective:** Apply six behavioral fixes in dependency order: config validation, CLI error handling, print-to-logger replacement, module-level Jinja2 environment, trust boundary comments, and cache wiring. The order matters because config validation must exist before CLI error handling can catch `ValueError`, and print-to-logger must happen before cache wiring to avoid mixing concerns.

**Dependencies:** Phase 1

**Deliverables:**

| File | Action | Description |
|------|--------|-------------|
| `courier/config.py` | Modify | Add `ValueError` validation for `pinboard_feed_url` in `load_config()` |
| `courier/__main__.py` | Modify | Wrap `load_config()` in try/except for `FileNotFoundError`, `KeyError`, `ValueError` |
| `courier/orchestrator.py` | Modify | Replace all `print()` calls with `logger.info()` calls; wire cache reads/writes into `_process_article()` |
| `courier/compiler.py` | Modify | Move `Environment` and template to module-level constants `_env` and `_template`; add trust boundary comment |
| `courier/templates/document.xhtml` | Modify | Add Jinja2 comment documenting the trust boundary for `| safe` |
| `tests/test_orchestrator.py` | Modify | Switch two `capsys` tests to `caplog`; add `mock_store.read_cached_html.return_value = None` to all relevant tests |

**Tasks:**

1. IMPL-P2-001: Add config validation to `courier/config.py`

   **What:** In `load_config()`, after constructing the `Config` object (or just before the `return`), validate that `pinboard_feed_url` is not empty and starts with `http://` or `https://`. Raise `ValueError` with a clear message on failure.

   **Details:** Insert validation after line 38 (after reading `data["pinboard_feed_url"]`) or just before the return statement. Check the value of `data["pinboard_feed_url"]` before constructing Config. Example message: `"pinboard_feed_url must be a URL starting with http:// or https://"`.

2. IMPL-P2-002: Add error handling to `courier/__main__.py`

   **What:** Wrap the `load_config(args.config)` call (line 51) in a try/except block catching `FileNotFoundError`, `KeyError`, and `ValueError`. Print a user-friendly message to stderr and return exit code 1.

   **Details:** The try/except should wrap only the `load_config()` call, not the entire `run_pipeline()` call. Use `print(f"Error: {exc}", file=sys.stderr)` for the user-facing message. Use the `logger` (added in Phase 1) to log the full exception at debug level.

3. IMPL-P2-003: Replace `print()` with `logger.info()` in `courier/orchestrator.py`

   **What:** Replace all five `print()` calls across three call sites in `orchestrator.py` with `logger.info()` calls. The three call sites are: the dry-run block (lines 35-37), the archive cap message (line 131), and `_print_summary()` (lines 209-210).

   **Details:**
   - Line 35: `print(f"Dry run — {len(articles_to_process)} articles would be processed:")` becomes `logger.info("Dry run -- %d articles would be processed:", len(articles_to_process))`
   - Line 37: `print(f"  {item['url']}")` becomes `logger.info("  %s", item["url"])`
   - Line 131: `print(f"{excluded} {noun} excluded by the article cap.")` becomes `logger.info("%d %s excluded by the article cap.", excluded, noun)`
   - Line 209: `print(f"Compiled: {compiled}  Failed: {failed}  Skipped: {skipped}")` becomes `logger.info("Compiled: %d  Failed: %d  Skipped: %d", compiled, failed, skipped)`
   - Line 210: `print(f"Output: {output_path}")` becomes `logger.info("Output: %s", output_path)`

4. IMPL-P2-004: Update orchestrator tests for print-to-logger change

   **What:** Update the two tests in `tests/test_orchestrator.py` that use `capsys` to assert on stdout output. Switch them to use `caplog` instead.

   **Details:**
   - `test_dry_run_prints_list_without_writing` (line 183): Replace `capsys` parameter with `caplog`. Replace `capsys.readouterr().out` assertions with checks against `caplog.text`. The test should verify that the two URLs appear in `caplog.text`.
   - `test_since_days_cap_message_when_articles_excluded` (line 330): Replace `capsys` parameter with `caplog`. Replace `capsys.readouterr().out` assertions with checks against `caplog.text`. The test should verify that "2" and "excluded" appear in `caplog.text`.

5. IMPL-P2-005: Move Jinja2 Environment to module level in `courier/compiler.py`

   **What:** Move the `Environment(...)` and `env.get_template(...)` calls from inside `compile_document()` to module-level constants `_env` and `_template`. This avoids repeated initialisation on every call.

   **Details:** After the `logger` line (line 12), add:
   ```python
   _env = Environment(
       loader=PackageLoader("courier", "templates"),
       autoescape=True,
   )
   _template = _env.get_template("document.xhtml")
   ```
   Remove the corresponding lines (27-31) from inside `compile_document()` and replace `template.render(...)` with `_template.render(...)`.

6. IMPL-P2-006: Add trust boundary comments

   **What:** Add a code comment in `compiler.py` explaining why `autoescape=True` coexists with `| safe` in the template, and add a Jinja2 comment in `document.xhtml` documenting the trust boundary.

   **Details:**
   - In `compiler.py`, add a comment above the `_env = Environment(...)` block (after IMPL-P2-005) explaining: autoescape is enabled as a safety default, but article content uses `| safe` in the template because it has already been sanitised to XHTML by the extractor. This is a known trust boundary.
   - In `courier/templates/document.xhtml`, add a Jinja2 comment (`{# ... #}`) on the line before `{{ article.content | safe }}` (line 21) documenting: "Content is pre-sanitised XHTML from extractor -- safe filter bypasses autoescape intentionally."

7. IMPL-P2-007: Wire cache into `_process_article()` in `courier/orchestrator.py`

   **What:** Wire `store.read_cached_html()` and `store.write_cached_html()` into `_process_article()` so that raw HTML is read from cache before fetching, written to cache after fetching, and extracted content is cached after extraction.

   **Details:** In `_process_article()`:
   - Before the `fetcher.fetch_article(url)` call (line 169), check `store.read_cached_html(config.cache_dir, url, "raw")`. If it returns a non-None value, use that as `raw_html` and skip the fetch. Otherwise fetch as before.
   - After a successful fetch (after line 169, in the non-cached path), call `store.write_cached_html(config.cache_dir, url, "raw", raw_html)`.
   - After successful extraction (after line 189), call `store.write_cached_html(config.cache_dir, url, "extracted", content)`.
   - When serving from cache (raw HTML cache hit), use the title from the feed item (`item.get("title", "")`) rather than relying on extraction to provide a title, since extraction still runs on cached HTML and will produce a title.

8. IMPL-P2-008: Update orchestrator tests for cache wiring

   **What:** Update all orchestrator tests that mock `store` to set `mock_store.read_cached_html.return_value = None` so they continue to test the fetch-from-network path.

   **Details:** Add `mock_store.read_cached_html.return_value = None` in every test that uses `@patch("courier.orchestrator.store")`. This affects: `test_happy_path`, `test_article_cap_enforced`, `test_compiled_articles_skipped`, `test_permanently_skipped_articles_skipped`, `test_fetch_failure_increments_fail_count`, `test_permanent_skip_after_max_attempts`, `test_dry_run_prints_list_without_writing`, `test_status_written_after_each_article`, `test_extraction_failure_sets_status`. Note: tests that never reach `_process_article()` (e.g., `test_feed_fetch_failure_exits_gracefully`, filtering tests) do not need this change but adding it is harmless.

**Testing:**
- Run `pytest` after each task to confirm no regressions. In particular, run the full suite after IMPL-P2-003/004 (print-to-logger is a breaking change for two tests) and after IMPL-P2-007/008 (cache wiring is a breaking change for orchestrator tests).

## Phase 3: Test Infrastructure and New Test Modules

**Objective:** Create shared test fixtures in `conftest.py`, then create three new test modules for the untested pipeline modules (extractor, store, compiler). Remove the duplicate config fixture and helper from `test_orchestrator.py`.

**Dependencies:** Phase 2

**Deliverables:**

| File | Action | Description |
|------|--------|-------------|
| `tests/conftest.py` | Create | Shared fixtures: `config`/`make_config` (using `tmp_path`), `sample_html`, `sample_feed_xml` |
| `tests/test_extractor.py` | Create | Tests for readability-trafilatura fallback, XHTML sanitisation, edge cases |
| `tests/test_store.py` | Create | Tests for status read/write round-trips, cached HTML read/write, atomic writes, missing files |
| `tests/test_compiler.py` | Create | Tests for document rendering from Article list, empty list, valid XHTML output |
| `tests/test_orchestrator.py` | Modify | Remove duplicate `config` fixture and `_make_config` helper; rely on conftest.py fixtures |

**Tasks:**

1. IMPL-P3-001: Create `tests/conftest.py` with shared fixtures

   **What:** Create `tests/conftest.py` with fixtures that can be shared across all test modules.

   **Details:**
   - `config` fixture: returns a `Config` instance using `tmp_path` for `cache_dir` and `output_dir`. Matches the pattern used in `test_orchestrator.py` line 20-27.
   - `make_config` fixture: a factory fixture that accepts optional overrides (especially `max_articles_per_run`). Replaces the `_make_config` helper in `test_orchestrator.py` line 274-281.
   - `sample_html` fixture: returns a minimal but valid HTML string suitable for extraction testing.
   - `sample_feed_xml` fixture: returns a minimal RSS/XML feed string for feed parsing tests.

2. IMPL-P3-002: Refactor `tests/test_orchestrator.py` to use conftest fixtures

   **What:** Remove the local `config` fixture (lines 19-27) and `_make_config` helper (lines 274-281) from `test_orchestrator.py`. Replace `_make_config(tmp_path)` calls with the `make_config` fixture from conftest.

   **Details:**
   - Delete the `config` fixture definition (lines 19-27). The conftest `config` fixture will be picked up automatically by pytest.
   - Delete the `_make_config` function (lines 274-281).
   - In `test_since_days_*` tests that call `_make_config(tmp_path)`, replace with `make_config()`. For `_make_config(tmp_path, max_articles=3)`, replace with `make_config(max_articles_per_run=3)`.
   - Update function signatures to accept `make_config` instead of `tmp_path` where `_make_config` was the only use of `tmp_path`.

3. IMPL-P3-003: Create `tests/test_extractor.py`

   **What:** Test the extractor module's public API: `extract_article()`.

   **Details:** Test cases to cover:
   - Readability primary extraction produces (title, content) from well-formed HTML.
   - Trafilatura fallback triggers when readability output is below `MIN_CONTENT_LENGTH` (200 chars). Use a minimal HTML that readability returns very little from, and mock trafilatura to return longer content.
   - XHTML sanitisation: output of `extract_article()` is valid XML (parse with `lxml.etree.fromstring`).
   - Empty input: `extract_article("", "")` returns a title and empty-string content without raising.
   - Short content: HTML that produces content shorter than `MIN_CONTENT_LENGTH` but trafilatura also returns short/empty content — readability result is kept.

4. IMPL-P3-004: Create `tests/test_store.py`

   **What:** Test the store module's public API: `read_status`, `write_status`, `read_cached_html`, `write_cached_html`, `url_hash`.

   **Details:** Test cases to cover:
   - `read_status` returns empty dict when `status.json` does not exist.
   - `write_status` then `read_status` round-trip: written data is read back identically.
   - `write_status` creates the cache directory if it does not exist.
   - `write_status` uses atomic write (tmp file then rename) -- verify by checking no `.tmp` file remains after write.
   - `read_cached_html` returns `None` when no cache file exists.
   - `write_cached_html` then `read_cached_html` round-trip for both `"raw"` and `"extracted"` stages.
   - `url_hash` is deterministic and returns a 64-char hex string (already tested in smoke tests, but include for completeness in the store test module).

5. IMPL-P3-005: Create `tests/test_compiler.py`

   **What:** Test the compiler module's public API: `compile_document()`.

   **Details:** Test cases to cover:
   - Compiling a list of one or more `Article` objects produces an XHTML file at the expected output path.
   - The output file contains each article's title and content.
   - The output is valid XHTML (parse with `lxml.etree.fromstring` to verify well-formedness).
   - Empty article list: `compile_document([], output_dir)` produces a valid XHTML file with no article content (the template should handle an empty list gracefully).
   - Output directory is created if it does not exist.

**Testing:**
- Run `pytest` after IMPL-P3-001/002 to confirm the fixture refactoring does not break existing tests.
- Run `pytest` after all tasks to confirm the full suite passes, including all new test modules.

## Next Steps

After this plan is approved:
1. Run `/arn-code-save-plan` to convert this plan into a structured project with phased implementation and testing plans
2. Optionally run `/arn-code-review-plan` to validate the structured plan
3. Run `/arn-code-taskify` to create a task list
4. Execute the tasks:
   - `/arn-code-execute-plan` -- sequential execution with review gates
   - `/arn-code-execute-plan-teams` -- parallel execution with Agent Teams (higher cost)
   - `/arn-code-execute-task` -- execute a single task at a time
5. Run `/arn-code-review-implementation` to validate the implementation
6. Run `/arn-code-document-project` to generate developer documentation
7. Run `/arn-code-ship` to commit, push, and create a pull request
