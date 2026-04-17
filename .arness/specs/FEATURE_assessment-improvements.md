# Assessment Improvements — Specification

## Problem Statement

**What:** Address 11 codebase quality findings from the full-codebase assessment: add missing test coverage for three pipeline modules, fix error handling in the CLI entry point, replace `print()` calls with structured logging, wire in the documented but unused HTML caching layer, and apply a set of small consistency fixes (loggers, `from __future__ import annotations`, module-level Jinja2 environment, config validation, and a code comment documenting a known trust boundary).

**Why:** Three core pipeline modules (extractor, compiler, store) have zero test coverage despite being critical to correct output. The CLI does not gracefully handle config errors. `print()` in the orchestrator mixes I/O concerns into pipeline logic, making output harder to test and redirect. The caching layer is documented as a key architectural decision but is dead code.

**Target project:** wharfinger-courier-arness — `/home/nik/dev/wharfinger-courier-arness`

---

## Requirements

### Functional Requirements

1. `tests/test_extractor.py` exists and tests the readability→trafilatura fallback logic, XHTML sanitisation, and edge cases (empty input, short content).
2. `tests/test_store.py` exists and tests read/write round-trips for status and cached HTML, atomic writes, and missing-file behaviour.
3. `tests/test_compiler.py` exists and tests document rendering from an Article list, empty list, and valid XHTML output.
4. `tests/conftest.py` exists with shared fixtures: a `config`/`make_config` fixture (using `tmp_path`), `sample_html`, and `sample_feed_xml`. The duplicate `config` fixture and `_make_config` helper in `test_orchestrator.py` are removed.
5. `load_config()` raises `ValueError` with a clear message if `pinboard_feed_url` is empty or does not start with `http://` or `https://`.
6. `__main__.py` wraps `load_config()` in a try/except for `FileNotFoundError`, `KeyError`, and `ValueError`; prints a user-friendly message to stderr and returns exit code 1 on error.
7. All three `print()` calls in `orchestrator.py` are replaced with `logger.info()` calls. The two existing tests that assert on stdout output are updated to use `caplog` instead of `capsys`.
8. The Jinja2 `Environment` and template loading in `compiler.py` are moved to module-level constants (`_env`, `_template`).
9. A comment in `compiler.py` explains why `autoescape=True` coexists with `| safe` in the template, and a Jinja2 comment in `document.xhtml` documents the trust boundary.
10. `read_cached_html()` and `write_cached_html()` in `store.py` are wired into `_process_article()` in `orchestrator.py`: check cache before fetching, write to cache after fetching and after extraction. Existing orchestrator tests are updated to set `mock_store.read_cached_html.return_value = None`.
11. `from __future__ import annotations` is added to `config.py` and `__main__.py`.
12. `logger = logging.getLogger(__name__)` is added to `config.py` and `__main__.py`.
13. `_filter_articles` in `courier/orchestrator.py` is renamed to `filter_articles` (public, no underscore). The import in `tests/test_orchestrator.py` is updated accordingly.

### Non-Functional Requirements

- **Reliability:** All existing tests must continue to pass after the changes. Two tests switching from `capsys` to `caplog` is an expected update, not a regression.
- **Maintainability:** No new abstractions or patterns introduced beyond what is documented in `code-patterns.md` and `testing-patterns.md`. Each fix is isolated to the affected module.
- **Performance:** Module-level Jinja2 environment avoids repeated initialisation cost (minor improvement, zero regression risk).

---

## Architectural Assessment

### Proposed Approach

Apply fixes in three phases ordered by risk and dependency. Phase 1 covers mechanical one-liner hygiene changes with no behavioral impact. Phase 2 covers behavioral improvements in dependency order (logger before validation, validation before error handling). Phase 3 adds test infrastructure first (conftest.py), then the three new test modules. All changes follow existing patterns documented in `code-patterns.md` and `testing-patterns.md`.

### Key Decisions

| Decision | Choice | Rationale | Status |
|----------|--------|-----------|--------|
| Dead cache functions (ASSESS-MAINT-004) | Wire into pipeline | Architecture doc lists filesystem caching as a key architectural decision; functions are well-implemented and wiring prevents redundant fetches on retry runs | Decided |
| Extracted title when serving from cache | Use feed title from item/status_data | Title already persisted in status_data on first extraction; no new cache format needed | Decided |
| Replace print() with logger.info() | logger.info() | Keeps I/O out of pipeline logic; CLI already routes logging to stderr via basicConfig | Decided |
| Jinja2 Environment | Move to module level | Template is package data, always present; fail-fast at import time is better behaviour | Decided |

### Components

| Component | Action | File(s) | Pattern Reference |
|-----------|--------|---------|-------------------|
| `config.py` logger + annotations | Modify | `courier/config.py` | Module-level logger pattern |
| `__main__.py` logger + annotations + error handling | Modify | `courier/__main__.py` | Module-level logger pattern; stdlib exceptions pattern |
| `load_config()` validation | Modify | `courier/config.py:38-44` | Standard exceptions for config errors |
| `compile_document()` Jinja2 env | Modify | `courier/compiler.py:27-31` | Module-level constants pattern |
| `document.xhtml` trust comment | Modify | `courier/templates/document.xhtml:21` | — |
| Orchestrator print→logger | Modify | `courier/orchestrator.py:35-38,129-130,209-211` | Module-level logger pattern |
| `_process_article()` cache wiring | Modify | `courier/orchestrator.py:145-204` | Caching strategy (architecture.md) |
| `tests/conftest.py` | Create | `tests/conftest.py` | Shared fixtures pattern (testing-patterns.md) |
| `tests/test_extractor.py` | Create | `tests/test_extractor.py` | test_<module>.py pattern |
| `tests/test_store.py` | Create | `tests/test_store.py` | test_<module>.py pattern |
| `tests/test_compiler.py` | Create | `tests/test_compiler.py` | test_<module>.py pattern |
| Existing orchestrator tests (capsys→caplog, mock update) | Modify | `tests/test_orchestrator.py` | — |

### Integration Points

- `orchestrator._process_article()` calls `store.read_cached_html()` / `store.write_cached_html()` — new dependency on store at the article-processing level (store was already imported)
- `__main__.main()` catches exceptions from `load_config()` — tightens the existing boundary between config loading and CLI output
- Module-level `_template` in `compiler.py` is loaded at import time — if `courier/templates/document.xhtml` is absent, import fails rather than function call fails (acceptable)

---

## Scope & Boundaries

**In Scope:**
- All 12 assessment findings listed in Requirements (ASSESS-TEST-001, ASSESS-TEST-002, ASSESS-ARCH-001 through ASSESS-ARCH-004, ASSESS-MAINT-001 through ASSESS-MAINT-005, ASSESS-PERF-001)
- Updating existing orchestrator tests affected by the print→logger, cache wiring, and `_filter_articles` rename changes

**Out of Scope:**
- Adding a test for `fetcher.py` (not in the assessment selection)
- Changing the cache storage format or adding a title sidecar file
- Any new features or behavioral changes beyond what the assessment findings specify

---

## Feasibility & Risks

- **Fix #4 (print→logger) breaks two existing tests** — `test_dry_run_prints_list_without_writing` and `test_since_days_cap_message_when_articles_excluded` use `capsys.readouterr().out`. Must switch to `caplog` at `logging.INFO` level in the same commit. Risk: medium, mitigation: update tests alongside the change.
- **Fix #7 (cache wiring) breaks existing orchestrator tests** — all tests that mock `store` will get a truthy `MagicMock` from `read_cached_html`, causing cache-hit path to be taken. Must add `mock_store.read_cached_html.return_value = None` to all affected tests. Risk: medium, mitigation: run full test suite after wiring and update mocks.
- **Fix #8 (module-level Jinja2) — import-time failure if template missing** — Low risk; template is package data and always present in an editable install. Fail-fast behaviour is acceptable.
- **conftest.py refactor may break orchestrator tests if fixture names change** — Medium risk. Run full test suite immediately after extracting shared fixtures.

---

## Decisions Log

1. Include ASSESS-MAINT-005 (`_filter_articles` → `filter_articles` rename) — user revised decision: the fix is trivial (make public) so include it in Phase 1 hygiene.
2. Wire cache functions rather than remove them — architecture doc lists caching as a key decision; functions are well-implemented and wiring provides real value (skip re-fetch on retry runs).
3. Use feed title from `item`/`status_data` when serving extracted content from cache — avoids changing the cache storage format.
4. Replace `print()` with `logger.info()` (not return structured results from `run_pipeline`) — simpler change; CLI already routes logging to stderr.

---

## Open Items

- None — all 11 fixes have clear implementation paths from the architect analysis.

---

## Recommendation

Ready for planning. All fixes are well-understood with concrete implementation notes. The highest-risk items (print→logger test updates, cache wiring mock updates) are identified and mitigated in the requirements.

To create a plan: Run `/arn-code-plan FEATURE_assessment-improvements`
