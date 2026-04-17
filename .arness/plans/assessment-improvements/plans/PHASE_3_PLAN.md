# Phase 3: Test Infrastructure & New Test Modules

**Project:** assessment-improvements
**Phase:** 3 of 3
**Prerequisites:** Phase 2

---

## Implementation

### Directives

- DO NOT write full test suites — only simple validation tests for this implementation section
- Generate JSON report: use template at `.arness/templates/IMPLEMENTATION_REPORT_TEMPLATE.json`
- Save to: `.arness/plans/assessment-improvements/reports/IMPLEMENTATION_REPORT_PHASE_3.json`
- If report exists, create new with timestamp suffix
- Follow codebase patterns from INTRODUCTION.md strictly

### Objectives

1. Create `tests/conftest.py` with shared fixtures (`config`/`make_config`, `sample_html`, `sample_feed_xml`)
2. Refactor `tests/test_orchestrator.py` to use conftest fixtures (remove duplicate `config` fixture and `_make_config` helper)
3. Create `tests/test_extractor.py` with tests for `extract_article()`
4. Create `tests/test_store.py` with tests for `read_status`, `write_status`, `read_cached_html`, `write_cached_html`
5. Create `tests/test_compiler.py` with tests for `compile_document()`

### Deliverables

| Deliverable | Path | Action |
|-------------|------|--------|
| Shared test fixtures | `tests/conftest.py` | Create |
| Orchestrator test refactor | `tests/test_orchestrator.py` | Modify |
| Extractor tests | `tests/test_extractor.py` | Create |
| Store tests | `tests/test_store.py` | Create |
| Compiler tests | `tests/test_compiler.py` | Create |

### Tasks

#### IMPL-P3-001: Create `tests/conftest.py` with shared fixtures

**What:** Create `tests/conftest.py` with pytest fixtures used across multiple test modules.

**Follow pattern from** `tests/test_orchestrator.py:19-27` (existing `config` fixture) and `tests/test_orchestrator.py:274-281` (existing `_make_config` helper).

**Files:**
- Create: `tests/conftest.py`

**Details:**
Read `tests/test_orchestrator.py` first to understand the exact shape of the existing `config` fixture and `_make_config` helper before writing `conftest.py`.

The conftest should define:

```python
from __future__ import annotations

from pathlib import Path

import pytest

from courier.config import Config


@pytest.fixture()
def config(tmp_path: Path) -> Config:
    """Default Config for tests using tmp_path directories."""
    return Config(
        pinboard_feed_url="https://feeds.pinboard.in/rss/secret/u:user/",
        cache_dir=tmp_path / "cache",
        output_dir=tmp_path / "output",
        max_fetch_attempts=3,
        max_articles_per_run=10,
    )


@pytest.fixture()
def make_config(tmp_path: Path):
    """Factory fixture for Config — accepts keyword overrides."""
    def _make(**overrides) -> Config:
        defaults = dict(
            pinboard_feed_url="https://feeds.pinboard.in/rss/secret/u:user/",
            cache_dir=tmp_path / "cache",
            output_dir=tmp_path / "output",
            max_fetch_attempts=3,
            max_articles_per_run=10,
        )
        defaults.update(overrides)
        return Config(**defaults)
    return _make


@pytest.fixture()
def sample_html() -> str:
    """Minimal valid HTML with enough content for extraction tests."""
    return """
    <html><head><title>Test Article</title></head>
    <body>
      <article>
        <h1>Test Article Title</h1>
        <p>""" + ("This is a test paragraph with enough content to pass the readability threshold. " * 10) + """</p>
      </article>
    </body></html>
    """


@pytest.fixture()
def sample_feed_xml() -> bytes:
    """Minimal RSS 1.0 (RDF) feed bytes for feed parsing tests."""
    return b"""<?xml version="1.0" encoding="UTF-8"?>
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
         xmlns="http://purl.org/rss/1.0/">
  <channel>
    <title>Test Feed</title>
    <link>https://example.com</link>
    <description>Test</description>
  </channel>
  <item>
    <title>Test Item</title>
    <link>https://example.com/article-1</link>
    <description>Test description</description>
  </item>
</rdf:RDF>"""
```

Match the exact field values used in the existing `config` fixture in `test_orchestrator.py` to avoid breaking tests that rely on specific values.

---

#### IMPL-P3-002: Refactor `tests/test_orchestrator.py` to use conftest fixtures

**What:** Remove the local `config` fixture (lines ~19-27) and `_make_config` helper (lines ~274-281) from `test_orchestrator.py`. Replace all `_make_config(tmp_path)` calls with `make_config()` calls using the conftest factory fixture.

**Files:**
- Modify: `tests/test_orchestrator.py`

**Details:**
Read the full current `test_orchestrator.py` before editing.

1. **Delete the `config` fixture** (the `@pytest.fixture` function named `config` at the top of the file). Pytest will automatically pick up the `config` fixture from `conftest.py`.

2. **Delete the `_make_config` helper function** (the module-level helper near the bottom, used by `test_since_days_*` tests).

3. **Update all `_make_config(tmp_path, ...)` call sites:**
   - Replace `_make_config(tmp_path)` with `make_config()` (inject `make_config` as a fixture parameter)
   - Replace `_make_config(tmp_path, max_articles=3)` with `make_config(max_articles_per_run=3)`
   - Update test function signatures to accept `make_config` instead of `tmp_path` where `tmp_path` was only used by `_make_config`

4. **Remove `tmp_path` from function signatures** in tests that no longer need it directly (only needed it for `_make_config`).

Run `pytest tests/test_orchestrator.py` after this step to confirm no regressions before proceeding.

---

#### IMPL-P3-003: Create `tests/test_extractor.py`

**What:** Create a test module for `courier.extractor` covering `extract_article()` and the fallback logic.

**Follow pattern from** `tests/test_smoke.py` (plain assertions, no fixtures for simple cases; `tmp_path` or `sample_html` from conftest for cases needing content).

**Files:**
- Create: `tests/test_extractor.py`

**Details:**
Read `courier/extractor.py` in full before writing tests to understand the actual function signatures, constants, and internal structure.

Test cases to implement:

1. **`test_extract_article_returns_tuple`** — call `extract_article(sample_html)` and assert the result is a `(str, str)` tuple with non-empty title.

2. **`test_extract_article_xhtml_is_valid_xml`** — assert the content from `extract_article(sample_html)` parses as valid XML using `lxml.etree.fromstring`.

3. **`test_readability_fallback_to_trafilatura`** — mock `courier.extractor._extract_with_readability` to return `("", "short")` (fewer than `MIN_CONTENT_LENGTH` chars), and mock `_extract_with_trafilatura` to return a longer string. Assert the longer string is used in the output.

4. **`test_extract_article_empty_input`** — call `extract_article("", "")` and assert it returns a tuple without raising.

5. **`test_no_fallback_when_readability_sufficient`** — mock `_extract_with_readability` to return content longer than `MIN_CONTENT_LENGTH`. Assert `_extract_with_trafilatura` is NOT called (use `mock.assert_not_called()`).

Import `MIN_CONTENT_LENGTH` from `courier.extractor` in the test to avoid hardcoding 200.

---

#### IMPL-P3-004: Create `tests/test_store.py`

**What:** Create a test module for `courier.store` covering all public functions: `url_hash`, `read_status`, `write_status`, `read_cached_html`, `write_cached_html`.

**Follow pattern from** `tests/test_smoke.py:test_url_hash_deterministic` (plain assertions, `tmp_path` for filesystem operations).

**Files:**
- Create: `tests/test_store.py`

**Details:**
Read `courier/store.py` in full before writing tests to understand exact function signatures and the cache directory structure.

Test cases to implement:

1. **`test_url_hash_deterministic`** — same URL produces same hash twice; hash is 64 hex chars. (Can skip if already adequately covered in `test_smoke.py`, or keep for completeness in the store module.)

2. **`test_read_status_missing_file`** — `read_status(tmp_path / "nonexistent")` returns `{}`.

3. **`test_write_then_read_status`** — write a status dict, read it back, assert equality.

4. **`test_write_status_no_tmp_file_remains`** — after `write_status(cache_dir, url, data)`, assert no `.tmp` files remain in the cache dir (atomic write).

5. **`test_read_cached_html_missing`** — `read_cached_html(tmp_path, url, "raw")` returns `None` when no cache file exists.

6. **`test_write_then_read_cached_html_raw`** — write raw HTML cache, read it back, assert equality.

7. **`test_write_then_read_cached_html_extracted`** — same for `"extracted"` stage.

Use `tmp_path` directly (or the `config` fixture from conftest for `cache_dir`).

---

#### IMPL-P3-005: Create `tests/test_compiler.py`

**What:** Create a test module for `courier.compiler` covering `compile_document()`.

**Follow pattern from** `tests/test_smoke.py` (plain assertions, `tmp_path` for output directory).

**Files:**
- Create: `tests/test_compiler.py`

**Details:**
Read `courier/compiler.py` and `courier/templates/document.xhtml` in full before writing tests. Read the `Article` dataclass definition to understand required fields.

Test cases to implement:

1. **`test_compile_document_creates_file`** — pass a list with one `Article` to `compile_document(articles, output_dir)`. Assert the returned path exists and ends with `.xhtml`.

2. **`test_compile_document_contains_article_title`** — assert the article's title appears in the output file content.

3. **`test_compile_document_output_is_valid_xhtml`** — parse the output file with `lxml.etree.parse()` to assert it is well-formed XML.

4. **`test_compile_document_empty_list`** — `compile_document([], output_dir)` produces a valid file without raising.

5. **`test_compile_document_creates_output_dir`** — pass a `output_dir` that does not yet exist; assert the function creates it and the file.

For `Article` construction, read the dataclass fields from `compiler.py` (or `orchestrator.py` where it may be defined). Construct minimal `Article` instances with realistic string values.

Note: since Phase 2 moved `_env`/`_template` to module level, `courier.compiler` is loaded at import time. Ensure the package is installed in editable mode (`pip install -e .`) before running these tests.

### Acceptance Criteria

- [ ] `tests/conftest.py` created with `config`, `make_config`, `sample_html`, `sample_feed_xml` fixtures
- [ ] `tests/test_orchestrator.py` no longer defines a local `config` fixture or `_make_config` helper
- [ ] All existing orchestrator tests pass with the conftest fixtures
- [ ] `tests/test_extractor.py` created and all its tests pass
- [ ] `tests/test_store.py` created and all its tests pass
- [ ] `tests/test_compiler.py` created and all its tests pass
- [ ] Codebase patterns followed (test_<module>.py naming, plain assertions, `tmp_path`)
- [ ] Implementation report generated

---

## Testing

### Directives

- Investigate implementation carefully before writing tests
- Generate JSON report: use template at `.arness/templates/TESTING_REPORT_TEMPLATE.json`
- Save to: `.arness/plans/assessment-improvements/reports/TESTING_REPORT_PHASE_3.json`
- If report exists, create new with timestamp suffix
- DO NOT override existing test files
- If tests reveal implementation bugs: fix, document in report (prefix "FIXED:"), re-run
- Only mark complete when ALL tests pass

### Read Before Writing Tests

- Read `tests/conftest.py` (created in this phase) for available fixtures
- Read `tests/test_extractor.py`, `tests/test_store.py`, `tests/test_compiler.py` (created in this phase)
- Review `pyproject.toml` for pytest configuration

### Test Patterns

**Follow** `tests/conftest.py` and the new test modules created in the implementation section:
- `config` and `make_config` fixtures from conftest
- `tmp_path` for filesystem operations
- Plain `assert` statements

### Test Cases

#### TEST-P3-001: Run full test suite including new modules

**Type:** integration
**What:** Run `pytest` to execute all tests including the three new test modules and the refactored orchestrator tests.

**Structure:** Run `pytest` from the project root with verbose output: `pytest -v`

**Pass criteria:** All tests pass (0 failures, 0 errors). The new test modules (`test_extractor.py`, `test_store.py`, `test_compiler.py`) all appear in the output with passing status. The refactored orchestrator tests also pass.

#### TEST-P3-002: Verify conftest fixtures are available to all test modules

**Type:** integration
**What:** Confirm that `config` and `make_config` fixtures from conftest resolve correctly in `test_extractor.py`, `test_store.py`, and `test_compiler.py` (if used).

**Pass criteria:** `pytest --collect-only` shows no fixture-not-found errors. Tests that use `config` or `make_config` from conftest run without `fixture 'X' not found` errors.

### Acceptance Criteria

- [ ] All tests passing (full suite: smoke, orchestrator, extractor, store, compiler)
- [ ] No fixture resolution errors
- [ ] Testing report generated
- [ ] Any bugs found and fixed (documented with "FIXED:" prefix in report)
