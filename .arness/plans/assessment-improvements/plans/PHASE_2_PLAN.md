# Phase 2: Behavioral Improvements

**Project:** assessment-improvements
**Phase:** 2 of 3
**Prerequisites:** Phase 1

---

## Implementation

### Directives

- DO NOT write full test suites — only simple validation tests
- Generate JSON report: use template at `.arness/templates/IMPLEMENTATION_REPORT_TEMPLATE.json`
- Save to: `.arness/plans/assessment-improvements/reports/IMPLEMENTATION_REPORT_PHASE_2.json`
- If report exists, create new with timestamp suffix
- Follow codebase patterns from INTRODUCTION.md strictly

### Objectives

1. Add URL validation to `load_config()` in `courier/config.py`
2. Wrap `load_config()` call in `courier/__main__.py` with try/except for graceful CLI error handling
3. Replace all `print()` calls in `courier/orchestrator.py` with `logger.info()` and update the two affected tests
4. Move Jinja2 `Environment` and template to module-level constants in `courier/compiler.py`
5. Add trust boundary comments in `courier/compiler.py` and `courier/templates/document.xhtml`
6. Wire `store.read_cached_html()` / `store.write_cached_html()` into `_process_article()` and update all affected orchestrator tests

### Deliverables

| Deliverable | Path | Action |
|-------------|------|--------|
| Config validation | `courier/config.py` | Modify |
| CLI error handling | `courier/__main__.py` | Modify |
| Orchestrator print→logger | `courier/orchestrator.py` | Modify |
| Orchestrator tests (capsys→caplog) | `tests/test_orchestrator.py` | Modify |
| Module-level Jinja2 env | `courier/compiler.py` | Modify |
| Trust boundary comment (template) | `courier/templates/document.xhtml` | Modify |
| Cache wiring | `courier/orchestrator.py` | Modify |
| Orchestrator tests (mock_store fix) | `tests/test_orchestrator.py` | Modify |

### Tasks

#### IMPL-P2-001: Add URL validation to `load_config()`

**What:** In `load_config()` in `courier/config.py`, validate `pinboard_feed_url` before constructing the `Config` object. Raise `ValueError` with a clear message if the value is empty or does not start with `http://` or `https://`.

**Follow pattern from** `courier/config.py:32-33` (INTRODUCTION.md Pattern 2):
- Use standard exceptions (`ValueError`) with descriptive messages
- Validate before constructing the object

**Files:**
- Modify: `courier/config.py` — add validation logic between reading `data` and the `return Config(...)` call

**Details:**
Extract and validate the URL from `data` before constructing `Config`:
```python
url = data["pinboard_feed_url"]
if not url or not url.startswith(("http://", "https://")):
    raise ValueError(
        f"pinboard_feed_url must be a URL starting with http:// or https://, got: {url!r}"
    )
```
Place this immediately after reading `data` (around line 38), before the `return Config(...)` statement. The existing `KeyError` from `data["pinboard_feed_url"]` still handles the missing-key case.

---

#### IMPL-P2-002: Wrap `load_config()` in try/except in `courier/__main__.py`

**What:** Wrap the `load_config(args.config)` call with a try/except block that catches `FileNotFoundError`, `KeyError`, and `ValueError`, prints a user-friendly message to `sys.stderr`, and returns exit code 1.

**Follow pattern from** INTRODUCTION.md Pattern 2 (stdlib exceptions, CLI converts to exit codes):

**Files:**
- Modify: `courier/__main__.py` — wrap the `load_config()` call at approximately line 51

**Details:**
Replace the bare `config = load_config(args.config)` call with:
```python
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
```
Ensure `import sys` is present (it should already be). The `run_pipeline()` call remains outside the try/except (pipeline errors are handled separately).

---

#### IMPL-P2-003: Replace `print()` with `logger.info()` in `courier/orchestrator.py`

**What:** Replace all five `print()` calls across three call sites in `orchestrator.py` with `logger.info()` calls. Use `%`-style formatting (consistent with Python logging convention).

**Follow pattern from** INTRODUCTION.md Pattern 1 (module-level logger, logging over print).

**Files:**
- Modify: `courier/orchestrator.py` — lines 35-37, line ~131, lines ~209-210

**Details:** Apply these five replacements:

1. Dry-run first line (~line 35):
   `print(f"Dry run — {len(articles_to_process)} articles would be processed:")`
   → `logger.info("Dry run -- %d articles would be processed:", len(articles_to_process))`

2. Dry-run URL line (~line 37):
   `print(f"  {item['url']}")`
   → `logger.info("  %s", item["url"])`

3. Archive cap message (~line 131):
   `print(f"{excluded} {noun} excluded by the article cap.")`
   → `logger.info("%d %s excluded by the article cap.", excluded, noun)`

4. Summary compiled line (~line 209):
   `print(f"Compiled: {compiled}  Failed: {failed}  Skipped: {skipped}")`
   → `logger.info("Compiled: %d  Failed: %d  Skipped: %d", compiled, failed, skipped)`

5. Summary output path (~line 210):
   `print(f"Output: {output_path}")`
   → `logger.info("Output: %s", output_path)`

Read the current file first to confirm exact line numbers and content before editing.

---

#### IMPL-P2-004: Update orchestrator tests for print→logger change

**What:** Update the two tests in `tests/test_orchestrator.py` that use `capsys` to capture stdout. Switch them to use `caplog` to capture log output instead.

**Files:**
- Modify: `tests/test_orchestrator.py` — two test functions

**Details:**

**Test 1 — `test_dry_run_prints_list_without_writing`:**
- Replace `capsys` parameter with `caplog`
- Add `caplog` fixture usage: the test will need `with caplog.at_level(logging.INFO, logger="courier.orchestrator"):` around the `run_pipeline()` call, or use `@pytest.mark.usefixtures` — follow what the existing test does for other `caplog` tests if any exist
- Replace `out = capsys.readouterr().out` + string assertions with assertions on `caplog.text` containing the expected URL strings

**Test 2 — `test_since_days_cap_message_when_articles_excluded`:**
- Replace `capsys` parameter with `caplog`
- Replace stdout assertions with assertions on `caplog.text` containing `"excluded"` and the expected count

Read the current test file to confirm exact test names and structure before editing.

---

#### IMPL-P2-005: Move Jinja2 Environment to module level in `courier/compiler.py`

**What:** Move the `Environment(...)` instantiation and `env.get_template(...)` call from inside `compile_document()` to module-level constants `_env` and `_template`.

**Follow pattern from** INTRODUCTION.md Pattern 3 (underscore prefix for private module-level state).

**Files:**
- Modify: `courier/compiler.py` — lines ~27-31 (inside `compile_document`) and after the logger line (~line 12)

**Details:**
After the `logger = logging.getLogger(__name__)` line (and any existing module-level constants), add:
```python
_env = Environment(
    loader=PackageLoader("courier", "templates"),
    autoescape=True,
)
_template = _env.get_template("document.xhtml")
```
Inside `compile_document()`, remove the `Environment(...)`, `PackageLoader(...)`, and `get_template(...)` calls. Replace `template.render(...)` with `_template.render(...)`.

Trust boundary comment (for IMPL-P2-006) goes above the `_env = Environment(...)` block.

---

#### IMPL-P2-006: Add trust boundary comments

**What:** Add a code comment in `courier/compiler.py` explaining why `autoescape=True` coexists with `| safe` in the template. Add a Jinja2 comment in `courier/templates/document.xhtml` documenting the same trust boundary.

**Files:**
- Modify: `courier/compiler.py` — above the `_env = Environment(...)` block (added in IMPL-P2-005)
- Modify: `courier/templates/document.xhtml` — above the line containing `{{ article.content | safe }}`

**Details:**

In `compiler.py`, add above `_env = Environment(...)`:
```python
# autoescape=True is the safety default and protects title and url fields.
# Article content uses ``| safe`` in the template because it has already been
# sanitised to XHTML by extractor._sanitise_to_xhtml(). This is a known trust
# boundary: if the extraction pipeline changes, review whether | safe is still safe.
```

In `document.xhtml`, add a Jinja2 comment on the line before `{{ article.content | safe }}`:
```
{# Content is pre-sanitised XHTML from extractor._sanitise_to_xhtml() -- | safe is intentional #}
```
Use a Jinja2 comment (`{# ... #}`) so it does not appear in the rendered output.

---

#### IMPL-P2-007: Wire cache into `_process_article()` in `courier/orchestrator.py`

**What:** Wire `store.read_cached_html()` and `store.write_cached_html()` into `_process_article()` so that raw HTML is checked in cache before fetching, written to cache after a successful fetch, and extracted content is written to cache after successful extraction.

**Files:**
- Modify: `courier/orchestrator.py` — function `_process_article()` at approximately lines 145-204

**Details:**
Read the current `_process_article()` implementation carefully first.

**Cache read before fetch:** Before the `fetcher.fetch_article(url)` call, check for a cached raw page:
```python
raw_html = store.read_cached_html(config.cache_dir, url, "raw")
if raw_html is None:
    raw_html = fetcher.fetch_article(url)
    store.write_cached_html(config.cache_dir, url, "raw", raw_html)
```

**Cache write after extraction:** After `extractor.extract_article(raw_html, url)` produces `(title, content)`:
```python
store.write_cached_html(config.cache_dir, url, "extracted", content)
```

**Title handling:** When serving from cache (raw HTML cache hit), `extract_article()` still runs on the cached HTML and returns a title — no special title handling needed. The feed item title (`item.get("title", "")`) remains a fallback only if extraction fails.

**Error handling:** The cache read/write calls should not be wrapped in additional try/except — let any filesystem errors propagate naturally (consistent with the existing error handling pattern).

---

#### IMPL-P2-008: Update orchestrator tests for cache wiring

**What:** Add `mock_store.read_cached_html.return_value = None` to all tests in `tests/test_orchestrator.py` that mock `store`, so they continue to test the full fetch-from-network path.

**Files:**
- Modify: `tests/test_orchestrator.py` — all tests that use `@patch("courier.orchestrator.store")`

**Details:**
Find every test function that receives a `mock_store` parameter (from `@patch("courier.orchestrator.store")`). In each test's setup (before the call to `run_pipeline()` or `_process_article()`), add:
```python
mock_store.read_cached_html.return_value = None
```

Read the full test file to identify all affected tests. The tests that never reach `_process_article()` (e.g., `test_feed_fetch_failure_exits_gracefully`, `test_filter_articles_*`) do not strictly need this line, but adding it is harmless and defensive.

### Acceptance Criteria

- [ ] `load_config()` raises `ValueError` for empty or non-HTTP URLs
- [ ] `__main__.py` catches `FileNotFoundError`, `KeyError`, `ValueError` from `load_config()` and returns exit code 1
- [ ] All five `print()` calls in `orchestrator.py` replaced with `logger.info()`
- [ ] `_print_summary` renamed or updated (if it no longer contains print calls, consider renaming to `_log_summary`)
- [ ] Two orchestrator tests updated from `capsys` to `caplog`
- [ ] `courier/compiler.py` has module-level `_env` and `_template`; no `Environment` construction inside `compile_document()`
- [ ] Trust boundary comments present in `compiler.py` and `document.xhtml`
- [ ] `_process_article()` checks and writes raw HTML cache before/after `fetcher.fetch_article()`
- [ ] `_process_article()` writes extracted content cache after `extractor.extract_article()`
- [ ] All orchestrator tests with `mock_store` have `mock_store.read_cached_html.return_value = None`
- [ ] Codebase patterns followed
- [ ] Implementation report generated

---

## Testing

### Directives

- Investigate implementation carefully before writing tests
- Generate JSON report: use template at `.arness/templates/TESTING_REPORT_TEMPLATE.json`
- Save to: `.arness/plans/assessment-improvements/reports/TESTING_REPORT_PHASE_2.json`
- If report exists, create new with timestamp suffix
- DO NOT override existing test files
- If tests reveal implementation bugs: fix, document in report (prefix "FIXED:"), re-run
- Only mark complete when ALL tests pass

### Read Before Writing Tests

- Read `tests/test_orchestrator.py` in full — especially the mock patterns and any existing `caplog` usage
- Review `pyproject.toml` for pytest configuration

### Test Patterns

**Follow** `tests/test_orchestrator.py`:
- `@patch` for mocking pipeline modules
- `caplog` (not `capsys`) for log output assertions after this phase
- Plain `assert` statements

### Test Cases

#### TEST-P2-001: Config validation rejects empty URL

**Type:** unit
**What:** Verify `load_config()` raises `ValueError` when `pinboard_feed_url` is empty.

**Structure:**
```python
import tempfile, os, pytest
from courier.config import load_config

def test_load_config_rejects_empty_url(tmp_path):
    config_file = tmp_path / "config.toml"
    config_file.write_text(
        '[courier]\npinboard_feed_url = ""\ncache_dir = "/tmp/c"\noutput_dir = "/tmp/o"\n'
    )
    with pytest.raises(ValueError, match="pinboard_feed_url"):
        load_config(str(config_file))
```

**Pass criteria:** `ValueError` raised with a message mentioning `pinboard_feed_url`.

#### TEST-P2-002: Config validation rejects non-HTTP URL

**Type:** unit
**What:** Verify `load_config()` raises `ValueError` for a URL not starting with `http`.

**Pass criteria:** `ValueError` raised for a value like `"ftp://example.com/feed"`.

#### TEST-P2-003: Run full existing test suite (regression check)

**Type:** integration
**What:** Run `pytest` to confirm all existing tests pass. This validates the print→logger change (two tests now use `caplog`), the cache wiring (orchestrator tests with updated mocks), and all other Phase 2 changes.

**Pass criteria:** All tests pass (0 failures, 0 errors). The two previously-`capsys` tests now assert on `caplog.text` successfully.

### Acceptance Criteria

- [ ] Config validation tests pass
- [ ] Full regression suite passes
- [ ] Testing report generated
- [ ] Any bugs found and fixed (documented with "FIXED:" prefix in report)
