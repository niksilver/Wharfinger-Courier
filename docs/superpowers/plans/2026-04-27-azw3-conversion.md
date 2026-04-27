# AZW3 Conversion Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add a post-compilation step that converts the XHTML output to AZW3 using `ebook-convert`, keeping both files.

**Architecture:** A new `courier/converter.py` module exposes `convert_to_azw3(xhtml_path, title)`, which the orchestrator calls after `compile_document()`. Conversion failure is fatal: the orchestrator logs the error and returns `False`.

**Tech Stack:** Python `subprocess.run` (stdlib); `ebook-convert` CLI (Calibre, external); `unittest.mock.patch` for tests.

---

## File Map

| Action | Path | Purpose |
|--------|------|---------|
| Create | `courier/converter.py` | Runs `ebook-convert` subprocess; raises on failure |
| Create | `tests/test_converter.py` | Unit tests for converter (3 cases) |
| Modify | `courier/orchestrator.py` | Import converter; call after compile; handle failure |
| Modify | `tests/test_orchestrator.py` | 1 new test; patch 4 existing tests to mock converter |

---

## Task 1: Create `courier/converter.py` with tests

**Files:**
- Create: `courier/converter.py`
- Create: `tests/test_converter.py`

### Step 1.1: Write three failing tests

Create `tests/test_converter.py` with these three tests:

```python
"""Tests for courier.converter."""

from __future__ import annotations

from pathlib import Path
from unittest.mock import MagicMock, patch

import pytest

from courier.converter import convert_to_azw3


def test_success_returns_azw3_path(tmp_path):
    xhtml = tmp_path / "doc.xhtml"
    mock_result = MagicMock()
    mock_result.returncode = 0
    with patch("courier.converter.subprocess.run", return_value=mock_result) as mock_run:
        result = convert_to_azw3(xhtml, "Wharfinger Courier, 30 April 2026")
    assert result == xhtml.with_suffix(".azw3")
    cmd = mock_run.call_args[0][0]
    assert cmd[0] == "ebook-convert"
    assert str(xhtml) in cmd
    assert str(xhtml.with_suffix(".azw3")) in cmd
    assert "--title" in cmd
    assert "Wharfinger Courier, 30 April 2026" in cmd


def test_tool_not_found_raises_descriptive_error(tmp_path):
    xhtml = tmp_path / "doc.xhtml"
    with patch("courier.converter.subprocess.run", side_effect=FileNotFoundError):
        with pytest.raises(FileNotFoundError, match="Calibre"):
            convert_to_azw3(xhtml, "Wharfinger Courier, 30 April 2026")


def test_nonzero_exit_raises_with_output(tmp_path):
    xhtml = tmp_path / "doc.xhtml"
    mock_result = MagicMock()
    mock_result.returncode = 1
    mock_result.stdout = "some stdout"
    mock_result.stderr = "some error detail"
    with patch("courier.converter.subprocess.run", return_value=mock_result):
        with pytest.raises(RuntimeError, match="some error detail"):
            convert_to_azw3(xhtml, "Wharfinger Courier, 30 April 2026")
```

- [ ] Write this file.

### Step 1.2: Run tests to confirm they fail

```bash
pytest tests/test_converter.py -v
```

Expected: 3 failures, all with `ModuleNotFoundError` or `ImportError` (module doesn't exist yet).

- [ ] Run and confirm failures.

### Step 1.3: Implement `courier/converter.py`

Create `courier/converter.py`:

```python
"""Convert XHTML documents to AZW3 using ebook-convert (Calibre)."""

from __future__ import annotations

import subprocess
from pathlib import Path


def convert_to_azw3(xhtml_path: Path, title: str) -> Path:
    """Convert xhtml_path to AZW3; return the AZW3 path.

    Raises FileNotFoundError if ebook-convert is not on PATH.
    Raises RuntimeError with captured output if ebook-convert exits non-zero.
    """
    azw3_path = xhtml_path.with_suffix(".azw3")
    try:
        result = subprocess.run(
            ["ebook-convert", str(xhtml_path), str(azw3_path), "--title", title],
            capture_output=True,
            text=True,
        )
    except FileNotFoundError:
        raise FileNotFoundError(
            "ebook-convert not found — is Calibre installed and on PATH?"
        )
    if result.returncode != 0:
        output = (result.stdout + "\n" + result.stderr).strip()
        raise RuntimeError(
            f"ebook-convert failed with exit code {result.returncode}:\n{output}"
        )
    return azw3_path
```

- [ ] Write this file.

### Step 1.4: Run tests to confirm they pass

```bash
pytest tests/test_converter.py -v
```

Expected: 3 passed.

- [ ] Run and confirm.

### Step 1.5: Commit

```bash
git add courier/converter.py tests/test_converter.py
git commit -m "feat: add converter module for XHTML→AZW3 via ebook-convert"
```

- [ ] Commit.

---

## Task 2: Wire converter into orchestrator

**Files:**
- Modify: `courier/orchestrator.py`
- Modify: `tests/test_orchestrator.py`

### Step 2.1: Write a failing test for converter failure

Add this test to `tests/test_orchestrator.py`, grouped with the other pipeline-failure tests. Note the decorator order: mock arguments are injected bottom-up (innermost decorator = first argument after `self`/fixtures).

```python
@patch("courier.orchestrator.converter")
@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_converter_failure_returns_false(
    mock_fetcher, mock_extractor, mock_compiler, mock_store, mock_converter, config, tmp_path
):
    """AZW3 conversion failure causes run_pipeline to return False."""
    mock_fetcher.fetch_feed.return_value = [_item("https://example.com/a1")]
    mock_fetcher.fetch_article.return_value = "<html>content</html>"
    mock_extractor.extract_article.return_value = ("Article Title", "<p>content</p>")
    mock_compiler.compile_document.return_value = tmp_path / "output" / "doc.xhtml"
    mock_store.read_status.return_value = {}
    mock_store.read_cached_html.return_value = None
    mock_converter.convert_to_azw3.side_effect = RuntimeError("ebook-convert failed")

    result = run_pipeline(config)

    assert result is False
```

- [ ] Add this test.

### Step 2.2: Run the new test to confirm it fails

```bash
pytest tests/test_orchestrator.py::test_converter_failure_returns_false -v
```

Expected: FAIL (courier.orchestrator has no `converter` attribute yet).

- [ ] Run and confirm failure.

### Step 2.3: Update `courier/orchestrator.py`

Make three changes:

**a) Add `date` to the datetime import and import `converter`:**

```python
from datetime import date, datetime, timedelta, timezone
# ...
from courier import compiler, converter, extractor, fetcher, store
```

**b) In `run_pipeline`, after `compile_document`, add the conversion step:**

Replace this block (currently lines 56–58):
```python
    output_path: Path | None = None
    if compiled_articles:
        output_path = compiler.compile_document(compiled_articles, config.output_dir)
```

With:
```python
    output_path: Path | None = None
    if compiled_articles:
        output_path = compiler.compile_document(compiled_articles, config.output_dir)
        today       = date.today()
        title       = f"Wharfinger Courier, {today.day} {today.strftime('%B %Y')}"
        try:
            converter.convert_to_azw3(output_path, title)
        except Exception as exc:
            logger.error("AZW3 conversion failed: %s", exc)
            return False
```

- [ ] Make both edits.

### Step 2.4: Run the new test to confirm it passes

```bash
pytest tests/test_orchestrator.py::test_converter_failure_returns_false -v
```

Expected: PASS.

- [ ] Run and confirm.

### Step 2.5: Fix the four existing orchestrator tests that now call the converter

The following tests call `run_pipeline` with articles that successfully compile, so they will now also call `converter.convert_to_azw3`. Each needs `@patch("courier.orchestrator.converter")` added and a mock return value set. Add the decorator **above** `@patch("courier.orchestrator.fetcher")` (i.e. outermost), and add `mock_converter` as the **last** parameter before the fixtures.

The four tests to update:

1. `test_happy_path`
2. `test_multiple_feed_urls_combined`
3. `test_article_cap_enforced`
4. `test_status_written_after_each_article`

For each, add:
- The decorator: `@patch("courier.orchestrator.converter")`
- The parameter: `mock_converter` (last before fixtures)
- In the body: `mock_converter.convert_to_azw3.return_value = tmp_path / "output" / "doc.azw3"`

Example — `test_happy_path` before and after:

**Before:**
```python
@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_happy_path(mock_fetcher, mock_extractor, mock_compiler, mock_store, config, tmp_path):
    ...
    mock_compiler.compile_document.return_value = tmp_path / "output" / "doc.xhtml"
    ...
```

**After:**
```python
@patch("courier.orchestrator.converter")
@patch("courier.orchestrator.store")
@patch("courier.orchestrator.compiler")
@patch("courier.orchestrator.extractor")
@patch("courier.orchestrator.fetcher")
def test_happy_path(mock_fetcher, mock_extractor, mock_compiler, mock_store, mock_converter, config, tmp_path):
    ...
    mock_compiler.compile_document.return_value = tmp_path / "output" / "doc.xhtml"
    mock_converter.convert_to_azw3.return_value  = tmp_path / "output" / "doc.azw3"
    ...
```

Apply this pattern to all four tests. Note: `test_multiple_feed_urls_combined` uses `make_config` instead of `config` as a fixture — the parameter order stays the same otherwise.

- [ ] Update the four tests.

### Step 2.6: Run the full test suite

```bash
pytest -v
```

Expected: all tests pass.

- [ ] Run and confirm.

### Step 2.7: Commit

```bash
git add courier/orchestrator.py tests/test_orchestrator.py
git commit -m "feat: wire AZW3 conversion into pipeline after XHTML compile"
```

- [ ] Commit.
