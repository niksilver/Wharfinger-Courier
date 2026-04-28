# Human-Readable Cache Directory Structure Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace opaque SHA-256 cache directories with human-readable `{domain}/{slug}--{hash8}` paths so a user who knows an article URL can find its cached files by browsing the filesystem.

**Architecture:** Add a `url_cache_path()` helper to `store.py` that derives a readable path from a URL, then update `read_cached_html` and `write_cached_html` to use it instead of the raw hash. The existing `url_hash()` function is kept unchanged and reused for the 8-char suffix.

**Tech Stack:** Python stdlib (`re`, `urllib.parse`), pytest

---

### Task 1: Add `url_cache_path` helper and tests

**Files:**
- Modify: `courier/store.py`
- Modify: `tests/test_smoke.py`

- [ ] **Step 1: Write a failing test for `url_cache_path`**

  In `tests/test_smoke.py`, add an import of `url_cache_path` from `courier.store` and add this test:

  ```python
  def test_url_cache_path_structure():
      from pathlib import Path
      cache_dir = Path("/tmp/cache")
      url = "https://www.arstechnica.com/science/2024/new-study/?ref=rss"
      result = url_cache_path(cache_dir, url)

      # domain: www. stripped
      assert result.parts[-3] == "arstechnica.com"
      # slug: lowercase, non-alphanumeric replaced with _, truncated to 60, stripped
      assert result.parts[-2].startswith("science_2024_new_study_ref_rss--")
      # hash suffix: first 8 chars of sha-256 of the full url
      expected_hash8 = url_hash(url)[:8]
      assert result.parts[-2].endswith(expected_hash8)
      # full path structure
      assert result == cache_dir / "cache" / "arstechnica.com" / result.parts[-2]
  ```

- [ ] **Step 2: Run the test to confirm it fails**

  ```bash
  pytest tests/test_smoke.py::test_url_cache_path_structure -v
  ```
  Expected: `ImportError` or `NameError` — `url_cache_path` does not exist yet.

- [ ] **Step 3: Implement `url_cache_path` in `courier/store.py`**

  Add `import re` and `from urllib.parse import urlparse` at the top of `store.py` (alongside existing imports).

  Add this function after `url_hash`:

  ```python
  def url_cache_path(cache_dir: Path, url: str) -> Path:
      parsed = urlparse(url)
      domain = parsed.netloc
      if domain.startswith("www."):
          domain = domain[4:]
      raw = parsed.path
      if parsed.query:
          raw += "_" + parsed.query
      slug = re.sub(r"[^a-z0-9]+", "_", raw.lower()).strip("_")[:60]
      hash8 = url_hash(url)[:8]
      return cache_dir / "cache" / domain / f"{slug}--{hash8}"
  ```

- [ ] **Step 4: Run the test to confirm it passes**

  ```bash
  pytest tests/test_smoke.py::test_url_cache_path_structure -v
  ```
  Expected: PASS.

- [ ] **Step 5: Run the full test suite**

  ```bash
  pytest -v
  ```
  Expected: all tests pass.

- [ ] **Step 6: Commit**

  ```bash
  git add courier/store.py tests/test_smoke.py
  git commit -m "feat: add url_cache_path helper for human-readable cache dirs"
  ```

---

### Task 2: Wire `url_cache_path` into `read_cached_html` and `write_cached_html`

**Files:**
- Modify: `courier/store.py`

- [ ] **Step 1: Write a failing test for the new path structure in read/write**

  In `tests/test_smoke.py`, add this test:

  ```python
  def test_write_cached_html_uses_readable_path(tmp_path):
      from courier.store import write_cached_html, url_cache_path
      url = "https://example.com/articles/foo-bar"
      write_cached_html(tmp_path, url, "raw", "<html>hello</html>")
      expected = url_cache_path(tmp_path, url) / "raw.html"
      assert expected.exists()
      assert expected.read_text() == "<html>hello</html>"

  def test_read_cached_html_uses_readable_path(tmp_path):
      from courier.store import write_cached_html, read_cached_html
      url = "https://example.com/articles/foo-bar"
      write_cached_html(tmp_path, url, "raw", "<html>hello</html>")
      result = read_cached_html(tmp_path, url, "raw")
      assert result == "<html>hello</html>"
  ```

- [ ] **Step 2: Run the new tests to confirm they fail**

  ```bash
  pytest tests/test_smoke.py::test_write_cached_html_uses_readable_path tests/test_smoke.py::test_read_cached_html_uses_readable_path -v
  ```
  Expected: FAIL — files are written to the old SHA-256 path, not the new readable path.

- [ ] **Step 3: Update `read_cached_html` to use `url_cache_path`**

  Change the path construction in `read_cached_html` from:
  ```python
  path = cache_dir / "cache" / url_hash(article_url) / f"{stage}.html"
  ```
  to:
  ```python
  path = url_cache_path(cache_dir, article_url) / f"{stage}.html"
  ```

- [ ] **Step 4: Update `write_cached_html` to use `url_cache_path`**

  Change the directory construction in `write_cached_html` from:
  ```python
  article_dir = cache_dir / "cache" / url_hash(article_url)
  ```
  to:
  ```python
  article_dir = url_cache_path(cache_dir, article_url)
  ```

- [ ] **Step 5: Run the new tests to confirm they pass**

  ```bash
  pytest tests/test_smoke.py::test_write_cached_html_uses_readable_path tests/test_smoke.py::test_read_cached_html_uses_readable_path -v
  ```
  Expected: PASS.

- [ ] **Step 6: Run the full test suite**

  ```bash
  pytest -v
  ```
  Expected: all tests pass.

- [ ] **Step 7: Commit**

  ```bash
  git add courier/store.py tests/test_smoke.py
  git commit -m "feat: use human-readable cache dirs in read/write_cached_html"
  ```
