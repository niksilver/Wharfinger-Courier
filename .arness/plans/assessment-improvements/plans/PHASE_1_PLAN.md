# Phase 1: Code Hygiene

**Project:** assessment-improvements
**Phase:** 1 of 3
**Prerequisites:** None

---

## Implementation

### Directives

- DO NOT write full test suites — only simple validation tests
- Generate JSON report: use template at `.arness/templates/IMPLEMENTATION_REPORT_TEMPLATE.json`
- Save to: `.arness/plans/assessment-improvements/reports/IMPLEMENTATION_REPORT_PHASE_1.json`
- If report exists, create new with timestamp suffix
- Follow codebase patterns from INTRODUCTION.md strictly

### Objectives

1. Add `from __future__ import annotations` to `courier/config.py` and `courier/__main__.py`
2. Add `logger = logging.getLogger(__name__)` to `courier/config.py` and `courier/__main__.py`
3. Rename `_filter_articles` to `filter_articles` in `courier/orchestrator.py` and update the import in `tests/test_orchestrator.py`

### Deliverables

| Deliverable | Path | Action |
|-------------|------|--------|
| config.py hygiene | `courier/config.py` | Modify |
| \_\_main\_\_.py hygiene | `courier/__main__.py` | Modify |
| filter_articles rename | `courier/orchestrator.py` | Modify |
| orchestrator test import | `tests/test_orchestrator.py` | Modify |

### Tasks

#### IMPL-P1-001: Add `from __future__ import annotations` and logger to `courier/config.py`

**What:** Add `from __future__ import annotations` as the first import line. Add `import logging` to the imports block. Add `logger = logging.getLogger(__name__)` after the last import line, before the `Config` dataclass definition.

**Follow pattern from** `courier/extractor.py:1-13` (INTRODUCTION.md Pattern 1):
- First line: `from __future__ import annotations`
- After imports: `logger = logging.getLogger(__name__)`
- Place logger before the first class/function definition

**Files:**
- Modify: `courier/config.py` — add future import at line 1, add `import logging` to imports, add logger after imports

**Details:**
- Current first line of `config.py` is `from __future__ import annotations` — check before adding (it may already be there based on ASSESS-MAINT-003; if already present, skip that part)
- Add `import logging` alongside the existing stdlib imports
- Add `logger = logging.getLogger(__name__)` on a new line after all imports, before the `@dataclass` decorator for `Config`

---

#### IMPL-P1-002: Add `from __future__ import annotations` and logger to `courier/__main__.py`

**What:** Add `from __future__ import annotations` as the first import line. Add `logger = logging.getLogger(__name__)` after the existing `import logging` line (already present in `__main__.py`).

**Follow pattern from** `courier/extractor.py:1-13` (INTRODUCTION.md Pattern 1):
- First line: `from __future__ import annotations`
- Logger placed after all imports, before the first function definition

**Files:**
- Modify: `courier/__main__.py` — add future import at line 1, add logger after imports

**Details:**
- `import logging` is already present in `__main__.py`
- Add `from __future__ import annotations` as the first import
- Add `logger = logging.getLogger(__name__)` after all imports, before `def main()`

---

#### IMPL-P1-003: Rename `_filter_articles` to `filter_articles` in `courier/orchestrator.py`

**What:** Remove the underscore prefix from `_filter_articles` — making it a public function — and update the direct import in `tests/test_orchestrator.py`.

**Follow pattern from** INTRODUCTION.md Pattern 3 (public functions have no underscore prefix when they have a stable, tested contract).

**Files:**
- Modify: `courier/orchestrator.py` — rename function definition at approximately line 74
- Modify: `tests/test_orchestrator.py` — update the import at line 13 from `_filter_articles` to `filter_articles`

**Details:**
- In `courier/orchestrator.py`: change `def _filter_articles(` to `def filter_articles(`
- In `tests/test_orchestrator.py`: change `from courier.orchestrator import _filter_articles` to `from courier.orchestrator import filter_articles`
- Search for any other references to `_filter_articles` in the codebase and update them (e.g., the call site inside `run_pipeline`)

### Acceptance Criteria

- [ ] `courier/config.py` has `from __future__ import annotations` and `logger = logging.getLogger(__name__)`
- [ ] `courier/__main__.py` has `from __future__ import annotations` and `logger = logging.getLogger(__name__)`
- [ ] `courier/orchestrator.py` defines `filter_articles` (not `_filter_articles`)
- [ ] `tests/test_orchestrator.py` imports `filter_articles` (not `_filter_articles`)
- [ ] No other references to `_filter_articles` remain in the codebase
- [ ] Codebase patterns followed (module-level logger, import ordering)
- [ ] Implementation report generated

---

## Testing

### Directives

- Investigate implementation carefully before writing tests
- Generate JSON report: use template at `.arness/templates/TESTING_REPORT_TEMPLATE.json`
- Save to: `.arness/plans/assessment-improvements/reports/TESTING_REPORT_PHASE_1.json`
- If report exists, create new with timestamp suffix
- DO NOT override existing test files
- If tests reveal implementation bugs: fix, document in report (prefix "FIXED:"), re-run
- Only mark complete when ALL tests pass

### Read Before Writing Tests

- Read `tests/test_smoke.py` and `tests/test_orchestrator.py` for existing patterns
- Review `pyproject.toml` for pytest configuration (`testpaths = ["tests"]`)

### Test Patterns

**Follow** `tests/test_smoke.py` and `tests/test_orchestrator.py`:
- Plain `assert` statements
- No fixtures needed for these hygiene checks

### Test Cases

#### TEST-P1-001: Verify logger exists in config.py module

**Type:** unit
**What:** Import `courier.config` and verify the module has a `logger` attribute.

**Structure:**
```python
import courier.config as config_module

def test_config_module_has_logger():
    import logging
    assert hasattr(config_module, "logger")
    assert isinstance(config_module.logger, logging.Logger)
```

**Pass criteria:** `courier.config` has a `Logger` instance named `logger`.

#### TEST-P1-002: Verify logger exists in \_\_main\_\_ module

**Type:** unit
**What:** Import `courier.__main__` and verify the module has a `logger` attribute.

**Structure:**
```python
import courier.__main__ as main_module

def test_main_module_has_logger():
    import logging
    assert hasattr(main_module, "logger")
    assert isinstance(main_module.logger, logging.Logger)
```

**Pass criteria:** `courier.__main__` has a `Logger` instance named `logger`.

#### TEST-P1-003: Run full existing test suite (regression check)

**Type:** integration
**What:** Run `pytest` to confirm all existing tests pass with no regressions from the hygiene changes.

**Structure:** Run `pytest` from the project root.

**Pass criteria:** All existing tests pass (0 failures, 0 errors).

### Acceptance Criteria

- [ ] All tests passing (including full regression suite)
- [ ] Logger presence verified for `config.py` and `__main__.py`
- [ ] `filter_articles` rename verified in orchestrator tests
- [ ] Testing report generated
