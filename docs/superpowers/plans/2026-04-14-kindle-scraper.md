# Kindle Scraper Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

> **Working style:** One piece of functionality at a time. After each numbered task below, stop and check with the user before continuing to the next task.

**Goal:** Build a Python CLI that fetches recent Pinboard bookmarks, scrapes the linked pages, and compiles them into a single Kindle-compatible XHTML file.

**Architecture:** A single entry point (`main.py`) orchestrates four modules: `pinboard` fetches bookmarks from the public RSS feed, `scraper` downloads pages and extracts article content, `store` handles all file I/O to the `work/` directory, and `compiler` assembles the Kindle XHTML. Each module is independently testable.

**Tech Stack:** Python 3.10+, `feedparser` (RSS), `requests` + `trafilatura` (scraping/extraction), `pytest` (tests).

---

## File Structure

**New files:**
- `requirements.txt` — pinned dependencies
- `courier/__init__.py` — package marker (empty)
- `courier/store.py` — sole owner of `work/` directory read/write
- `courier/pinboard.py` — fetch bookmarks from Pinboard RSS feed
- `courier/scraper.py` — fetch URL and extract main article content
- `courier/compiler.py` — assemble articles into Kindle XHTML
- `main.py` — CLI entry point and orchestration
- `tests/__init__.py` — package marker (empty)
- `tests/conftest.py` — shared pytest fixtures (`work_dir`)
- `tests/test_store.py` — unit tests for store.py
- `tests/test_pinboard.py` — unit tests for pinboard.py
- `tests/test_scraper.py` — unit tests for scraper.py
- `tests/test_compiler.py` — unit tests for compiler.py
- `tests/test_integration.py` — end-to-end pipeline tests

---

## Task 1: Project setup

**Files:**
- Create: `requirements.txt`
- Create: `courier/__init__.py`
- Create: `tests/__init__.py`
- Create: `tests/conftest.py`

- [ ] **Step 1: Create `requirements.txt`**

```
feedparser>=6.0
requests>=2.28
trafilatura>=1.6
pytest>=7.0
```

- [ ] **Step 2: Create `courier/__init__.py`**

Empty file.

- [ ] **Step 3: Create `tests/__init__.py`**

Empty file.

- [ ] **Step 4: Create `tests/conftest.py`**

```python
from pathlib import Path

import pytest
import courier.store as store


@pytest.fixture
def work_dir(monkeypatch: pytest.MonkeyPatch, tmp_path: Path) -> Path:
    """Redirect store.WORK_DIR to a temporary directory."""
    work = tmp_path / "work"
    monkeypatch.setattr(store, "WORK_DIR", work)
    return work
```

- [ ] **Step 5: Install dependencies**

Run: `pip install -r requirements.txt`

Expected: all packages install without error.

- [ ] **Step 6: Verify pytest collects nothing yet**

Run: `pytest --collect-only`

Expected: "no tests ran", no import errors.

- [ ] **Step 7: Commit**

```bash
git add requirements.txt courier/__init__.py tests/__init__.py tests/conftest.py
git commit -m "feat: project setup — package structure and dependencies"
```

> **→ Check with user before continuing.**

---

## Task 2.1: store — `clear()` creates the articles directory

**Files:**
- Create: `courier/store.py`
- Create: `tests/test_store.py`

- [ ] **Step 1: Write the failing test**

```python
# tests/test_store.py
from pathlib import Path

import courier.store as store


def test_clear_creates_articles_dir(work_dir: Path) -> None:
    store.clear()
    assert (work_dir / "articles").is_dir()
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_store.py::test_clear_creates_articles_dir -v`

Expected: ERROR — `ModuleNotFoundError: No module named 'courier.store'`

- [ ] **Step 3: Implement `courier/store.py` with `clear()`**

```python
import shutil
from pathlib import Path

WORK_DIR = Path("work")


def _articles_dir() -> Path:
    return WORK_DIR / "articles"


def clear() -> None:
    """Clear the work/ directory and recreate the articles subdirectory."""
    if WORK_DIR.exists():
        shutil.rmtree(WORK_DIR)
    _articles_dir().mkdir(parents=True)
```

- [ ] **Step 4: Run to verify it passes**

Run: `pytest tests/test_store.py::test_clear_creates_articles_dir -v`

Expected: PASSED.

> **→ Check with user before continuing.**

---

## Task 2.2: store — `save_article()` writes a file with a zero-padded index name

**Files:**
- Modify: `courier/store.py`
- Modify: `tests/test_store.py`

- [ ] **Step 1: Write the failing test**

Add to `tests/test_store.py`:

```python
def test_save_article_creates_file_with_padded_index(work_dir: Path) -> None:
    store.clear()
    store.save_article({
        "index": 3,
        "title": "My Title",
        "url": "https://example.com",
        "fetched_at": "2026-04-14T10:00:00+00:00",
        "status": "ok",
        "content_html": "<p>x</p>",
    })
    files = list((work_dir / "articles").iterdir())
    assert len(files) == 1
    assert files[0].name.startswith("003-")
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_store.py::test_save_article_creates_file_with_padded_index -v`

Expected: FAILED — `AttributeError: module 'courier.store' has no attribute 'save_article'`

- [ ] **Step 3: Add `save_article()` to `courier/store.py`**

Add these imports at the top:

```python
import json
import re
```

Add these functions:

```python
def _slug(title: str) -> str:
    s = title.lower()
    s = re.sub(r"[^a-z0-9]+", "-", s)
    s = s.strip("-")
    return (s or "untitled")[:40]


def save_article(article: dict) -> None:
    """Save an article dict to work/articles/NNN-slug.json."""
    _articles_dir().mkdir(parents=True, exist_ok=True)
    filename = f"{article['index']:03d}-{_slug(article['title'])}.json"
    with open(_articles_dir() / filename, "w", encoding="utf-8") as f:
        json.dump(article, f, ensure_ascii=False, indent=2)
```

- [ ] **Step 4: Run to verify it passes**

Run: `pytest tests/test_store.py -v`

Expected: 2 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 2.3: store — `load_articles()` returns a saved article unchanged

**Files:**
- Modify: `courier/store.py`
- Modify: `tests/test_store.py`

- [ ] **Step 1: Write the failing test**

Add to `tests/test_store.py`:

```python
def test_load_articles_returns_saved_article(work_dir: Path) -> None:
    store.clear()
    article = {
        "index": 1,
        "title": "Test Article",
        "url": "https://example.com",
        "fetched_at": "2026-04-14T10:00:00+00:00",
        "status": "ok",
        "content_html": "<p>Hello</p>",
    }
    store.save_article(article)
    loaded = store.load_articles()
    assert len(loaded) == 1
    assert loaded[0] == article
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_store.py::test_load_articles_returns_saved_article -v`

Expected: FAILED — `AttributeError: module 'courier.store' has no attribute 'load_articles'`

- [ ] **Step 3: Add `load_articles()` to `courier/store.py`**

```python
def load_articles() -> list[dict]:
    """Load all article dicts from work/articles/, sorted by filename (index order)."""
    articles_dir = _articles_dir()
    if not articles_dir.exists():
        return []
    paths = sorted(articles_dir.glob("*.json"))
    articles = []
    for path in paths:
        with open(path, encoding="utf-8") as f:
            articles.append(json.load(f))
    return articles
```

- [ ] **Step 4: Run to verify it passes**

Run: `pytest tests/test_store.py -v`

Expected: 3 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 2.4: store — `load_articles()` returns articles in index order

**Files:**
- Modify: `tests/test_store.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_store.py`:

```python
def test_load_articles_returns_in_index_order(work_dir: Path) -> None:
    store.clear()
    for i in [3, 1, 2]:
        store.save_article({
            "index": i,
            "title": f"Article {i}",
            "url": f"https://example.com/{i}",
            "fetched_at": "2026-04-14T10:00:00+00:00",
            "status": "ok",
            "content_html": "<p>x</p>",
        })
    loaded = store.load_articles()
    assert [a["index"] for a in loaded] == [1, 2, 3]
```

- [ ] **Step 2: Run all store tests**

Run: `pytest tests/test_store.py -v`

Expected: 4 tests PASSED. (The sort-by-filename approach from Task 2.3 already handles ordering.)

> **→ Check with user before continuing.**

---

## Task 2.5: store — `save_progress()` and `load_progress()` round trip

**Files:**
- Modify: `courier/store.py`
- Modify: `tests/test_store.py`

- [ ] **Step 1: Write the failing test**

Add to `tests/test_store.py`:

```python
def test_save_and_load_progress_round_trip(work_dir: Path) -> None:
    store.clear()
    progress = {
        "run_started_at": "2026-04-14T10:00:00+00:00",
        "days": 7,
        "bookmarks": [{"title": "A", "url": "https://a.com", "pinboard_date": "2026-04-13T00:00:00+00:00"}],
        "last_fetched_index": 1,
    }
    store.save_progress(progress)
    loaded = store.load_progress()
    assert loaded == progress
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_store.py::test_save_and_load_progress_round_trip -v`

Expected: FAILED — `AttributeError: module 'courier.store' has no attribute 'save_progress'`

- [ ] **Step 3: Add `save_progress()` and `load_progress()` to `courier/store.py`**

```python
def _progress_file() -> Path:
    return WORK_DIR / "progress.json"


def save_progress(progress: dict) -> None:
    """Write progress.json."""
    WORK_DIR.mkdir(parents=True, exist_ok=True)
    with open(_progress_file(), "w", encoding="utf-8") as f:
        json.dump(progress, f, ensure_ascii=False, indent=2)


def load_progress() -> dict:
    """Read progress.json."""
    with open(_progress_file(), encoding="utf-8") as f:
        return json.load(f)
```

- [ ] **Step 4: Run to verify all store tests pass**

Run: `pytest tests/test_store.py -v`

Expected: 5 tests PASSED.

- [ ] **Step 5: Commit**

```bash
git add courier/store.py tests/test_store.py
git commit -m "feat: store module — work directory read/write"
```

> **→ Check with user before continuing.**

---

## Task 3.1: pinboard — returns bookmarks found within the date range

**Files:**
- Create: `courier/pinboard.py`
- Create: `tests/test_pinboard.py`

- [ ] **Step 1: Write the failing test**

```python
# tests/test_pinboard.py
from datetime import datetime, timezone, timedelta
from unittest.mock import patch, MagicMock

from courier.pinboard import fetch_bookmarks


def _make_entry(title: str, url: str, days_ago: float) -> MagicMock:
    t = datetime.now(timezone.utc) - timedelta(days=days_ago)
    entry = MagicMock()
    entry.title = title
    entry.link = url
    entry.published_parsed = t.timetuple()
    return entry


def _make_feed(entries: list[MagicMock], bozo: bool = False) -> MagicMock:
    feed = MagicMock()
    feed.entries = entries
    feed.bozo = bozo
    feed.bozo_exception = None
    return feed


def test_returns_recent_bookmarks_with_correct_fields() -> None:
    feed = _make_feed([_make_entry("Recent", "https://recent.com", days_ago=2)])
    with patch("courier.pinboard.feedparser.parse", return_value=feed):
        bookmarks = fetch_bookmarks(days=7)
    assert len(bookmarks) == 1
    assert bookmarks[0]["title"] == "Recent"
    assert bookmarks[0]["url"] == "https://recent.com"
    assert "pinboard_date" in bookmarks[0]
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_pinboard.py::test_returns_recent_bookmarks_with_correct_fields -v`

Expected: ERROR — `ModuleNotFoundError: No module named 'courier.pinboard'`

- [ ] **Step 3: Implement `courier/pinboard.py`**

```python
from datetime import datetime, timezone, timedelta

import feedparser

FEED_URL = "https://feeds.pinboard.in/rss/u:niksilver/t:toread/"


def fetch_bookmarks(days: int) -> list[dict]:
    """Fetch bookmarks tagged toread on Pinboard added within the last `days` days.

    Returns a list of dicts with keys: title, url, pinboard_date.
    Raises RuntimeError if the feed cannot be fetched.
    """
    feed = feedparser.parse(FEED_URL)
    if feed.bozo and not feed.entries:
        raise RuntimeError(f"Could not fetch Pinboard feed: {feed.bozo_exception}")

    cutoff = datetime.now(timezone.utc) - timedelta(days=days)
    bookmarks = []
    for entry in feed.entries:
        if not hasattr(entry, "published_parsed") or entry.published_parsed is None:
            continue
        published = datetime(*entry.published_parsed[:6], tzinfo=timezone.utc)
        if published >= cutoff:
            bookmarks.append({
                "title": entry.title,
                "url": entry.link,
                "pinboard_date": published.isoformat(),
            })
    return bookmarks
```

- [ ] **Step 4: Run to verify it passes**

Run: `pytest tests/test_pinboard.py::test_returns_recent_bookmarks_with_correct_fields -v`

Expected: PASSED.

> **→ Check with user before continuing.**

---

## Task 3.2: pinboard — excludes bookmarks outside the date range

**Files:**
- Modify: `tests/test_pinboard.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_pinboard.py`:

```python
def test_excludes_bookmarks_outside_date_range() -> None:
    feed = _make_feed([
        _make_entry("Recent", "https://recent.com", days_ago=2),
        _make_entry("Old", "https://old.com", days_ago=10),
    ])
    with patch("courier.pinboard.feedparser.parse", return_value=feed):
        bookmarks = fetch_bookmarks(days=7)
    assert len(bookmarks) == 1
    assert bookmarks[0]["url"] == "https://recent.com"
```

- [ ] **Step 2: Run all pinboard tests**

Run: `pytest tests/test_pinboard.py -v`

Expected: 2 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 3.3: pinboard — raises `RuntimeError` when the feed is unreachable

**Files:**
- Modify: `tests/test_pinboard.py`

- [ ] **Step 1: Write the failing test**

Add to `tests/test_pinboard.py`:

```python
import pytest


def test_raises_on_unreachable_feed() -> None:
    feed = _make_feed(entries=[], bozo=True)
    feed.bozo_exception = Exception("Connection refused")
    with patch("courier.pinboard.feedparser.parse", return_value=feed):
        with pytest.raises(RuntimeError, match="Could not fetch Pinboard feed"):
            fetch_bookmarks(days=7)
```

- [ ] **Step 2: Run all pinboard tests**

Run: `pytest tests/test_pinboard.py -v`

Expected: 3 tests PASSED.

- [ ] **Step 3: Commit**

```bash
git add courier/pinboard.py tests/test_pinboard.py
git commit -m "feat: pinboard module — fetch bookmarks from RSS feed"
```

> **→ Check with user before continuing.**

---

## Task 4.1: scraper — returns `ok` with extracted content on success

**Files:**
- Create: `courier/scraper.py`
- Create: `tests/test_scraper.py`

- [ ] **Step 1: Write the failing test**

```python
# tests/test_scraper.py
from unittest.mock import patch, MagicMock

from courier.scraper import fetch_article


def _mock_response(text: str) -> MagicMock:
    response = MagicMock()
    response.text = text
    response.raise_for_status.return_value = None
    return response


def test_returns_ok_with_content_on_success() -> None:
    with patch("courier.scraper.requests.get", return_value=_mock_response("<html><body><p>Body</p></body></html>")):
        with patch("courier.scraper.trafilatura.extract", return_value="<p>Body</p>"):
            result = fetch_article("https://example.com/article")
    assert result["status"] == "ok"
    assert result["content_html"] == "<p>Body</p>"
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_scraper.py::test_returns_ok_with_content_on_success -v`

Expected: ERROR — `ModuleNotFoundError: No module named 'courier.scraper'`

- [ ] **Step 3: Implement `courier/scraper.py`**

```python
import re

import requests
import trafilatura

_HEADERS = {"User-Agent": "wharfinger-courier/1.0"}
_OUTER_TAGS = re.compile(r"</?(?:html|head|body)[^>]*>", re.IGNORECASE)


def fetch_article(url: str) -> dict:
    """Fetch URL and extract main article content.

    Returns {"status": "ok", "content_html": str} on success,
    or {"status": "failed"} if the page cannot be fetched or extracted.
    """
    try:
        response = requests.get(url, timeout=30, headers=_HEADERS)
        response.raise_for_status()
        raw = trafilatura.extract(response.text, include_formatting=True, output_format="html")
        if raw is None:
            return {"status": "failed"}
        content = _OUTER_TAGS.sub("", raw).strip()
        return {"status": "ok", "content_html": content}
    except Exception:
        return {"status": "failed"}
```

- [ ] **Step 4: Run to verify it passes**

Run: `pytest tests/test_scraper.py::test_returns_ok_with_content_on_success -v`

Expected: PASSED.

> **→ Check with user before continuing.**

---

## Task 4.2: scraper — returns `failed` when `trafilatura` extracts nothing

**Files:**
- Modify: `tests/test_scraper.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_scraper.py`:

```python
def test_returns_failed_when_trafilatura_returns_none() -> None:
    with patch("courier.scraper.requests.get", return_value=_mock_response("<html></html>")):
        with patch("courier.scraper.trafilatura.extract", return_value=None):
            result = fetch_article("https://example.com/article")
    assert result["status"] == "failed"
    assert "content_html" not in result
```

- [ ] **Step 2: Run all scraper tests**

Run: `pytest tests/test_scraper.py -v`

Expected: 2 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 4.3: scraper — returns `failed` on HTTP or network errors

**Files:**
- Modify: `tests/test_scraper.py`

- [ ] **Step 1: Write the tests**

Add to `tests/test_scraper.py`:

```python
def test_returns_failed_on_http_error() -> None:
    bad_response = MagicMock()
    bad_response.raise_for_status.side_effect = Exception("404 Not Found")
    with patch("courier.scraper.requests.get", return_value=bad_response):
        result = fetch_article("https://example.com/article")
    assert result["status"] == "failed"
    assert "content_html" not in result


def test_returns_failed_on_network_exception() -> None:
    with patch("courier.scraper.requests.get", side_effect=Exception("Connection timeout")):
        result = fetch_article("https://example.com/article")
    assert result["status"] == "failed"
    assert "content_html" not in result
```

- [ ] **Step 2: Run all scraper tests**

Run: `pytest tests/test_scraper.py -v`

Expected: 4 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 4.4: scraper — strips outer `<html>`/`<body>` tags from extracted content

**Files:**
- Modify: `tests/test_scraper.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_scraper.py`:

```python
def test_strips_outer_document_tags_from_content() -> None:
    raw = "<html><body><p>Clean content</p></body></html>"
    with patch("courier.scraper.requests.get", return_value=_mock_response("<html><p>x</p></html>")):
        with patch("courier.scraper.trafilatura.extract", return_value=raw):
            result = fetch_article("https://example.com/article")
    assert result["status"] == "ok"
    assert "<html>" not in result["content_html"]
    assert "<body>" not in result["content_html"]
    assert "<p>Clean content</p>" in result["content_html"]
```

- [ ] **Step 2: Run all scraper tests**

Run: `pytest tests/test_scraper.py -v`

Expected: 5 tests PASSED.

- [ ] **Step 3: Commit**

```bash
git add courier/scraper.py tests/test_scraper.py
git commit -m "feat: scraper module — fetch and extract article content"
```

> **→ Check with user before continuing.**

---

## Task 5.1: compiler — produces a valid XHTML skeleton

**Files:**
- Create: `courier/compiler.py`
- Create: `tests/test_compiler.py`

- [ ] **Step 1: Write the failing test**

```python
# tests/test_compiler.py
from courier.compiler import compile_xhtml


def _ok(index: int, title: str, url: str = "https://example.com", content: str = "<p>Body</p>") -> dict:
    return {"index": index, "title": title, "url": url, "status": "ok", "content_html": content}


def _failed(index: int, title: str, url: str = "https://example.com") -> dict:
    return {"index": index, "title": title, "url": url, "status": "failed"}


def test_output_is_valid_xhtml_skeleton() -> None:
    xhtml = compile_xhtml([_ok(1, "A")], days=7)
    assert xhtml.startswith("<?xml")
    assert "<html" in xhtml
    assert "</body>" in xhtml
    assert "</html>" in xhtml
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_compiler.py::test_output_is_valid_xhtml_skeleton -v`

Expected: ERROR — `ModuleNotFoundError: No module named 'courier.compiler'`

- [ ] **Step 3: Implement `courier/compiler.py`**

```python
import html
from datetime import date

_XHTML_HEADER = """\
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8"/>
<title>{title}</title>
</head>
<body>"""

_PAGE_BREAK = "<MBP:PAGEBREAK/>"


def compile_xhtml(articles: list[dict], days: int) -> str:
    """Assemble a Kindle-compatible XHTML document from a list of article dicts.

    Failed articles (status != 'ok') are excluded from the TOC and body.
    """
    good = [a for a in articles if a["status"] == "ok"]
    title = f"Reading \u2014 last {days} days"
    today = date.today().isoformat()

    parts = [_XHTML_HEADER.format(title=html.escape(title))]

    # Title page
    parts.append(f"<h1>{html.escape(title)}</h1>")
    parts.append(f"<p><i>{today}</i></p>")
    parts.append(_PAGE_BREAK)

    # Table of contents
    parts.append("<h2>Contents</h2>")
    parts.append("<ol>")
    for article in good:
        parts.append(f'  <li><a href="#article-{article["index"]}">{html.escape(article["title"])}</a></li>')
    parts.append("</ol>")
    parts.append(_PAGE_BREAK)

    # Articles
    for article in good:
        parts.append(f'<a name="article-{article["index"]}"/>')
        parts.append(f'<h1>{html.escape(article["title"])}</h1>')
        parts.append(f'<p><i><a href="{article["url"]}">{article["url"]}</a></i></p>')
        parts.append(article["content_html"])
        parts.append(_PAGE_BREAK)

    parts.append("</body>")
    parts.append("</html>")

    return "\n".join(parts)
```

- [ ] **Step 4: Run to verify it passes**

Run: `pytest tests/test_compiler.py::test_output_is_valid_xhtml_skeleton -v`

Expected: PASSED.

> **→ Check with user before continuing.**

---

## Task 5.2: compiler — title page shows the correct number of days

**Files:**
- Modify: `tests/test_compiler.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_compiler.py`:

```python
def test_title_page_shows_days_count() -> None:
    xhtml = compile_xhtml([_ok(1, "A")], days=14)
    assert "14" in xhtml
```

- [ ] **Step 2: Run all compiler tests**

Run: `pytest tests/test_compiler.py -v`

Expected: 2 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 5.3: compiler — TOC links to each successful article

**Files:**
- Modify: `tests/test_compiler.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_compiler.py`:

```python
def test_toc_links_to_successful_articles() -> None:
    xhtml = compile_xhtml([_ok(1, "First"), _ok(2, "Second")], days=7)
    assert 'href="#article-1"' in xhtml
    assert "First" in xhtml
    assert 'href="#article-2"' in xhtml
    assert "Second" in xhtml
```

- [ ] **Step 2: Run all compiler tests**

Run: `pytest tests/test_compiler.py -v`

Expected: 3 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 5.4: compiler — TOC excludes failed articles

**Files:**
- Modify: `tests/test_compiler.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_compiler.py`:

```python
def test_toc_excludes_failed_articles() -> None:
    xhtml = compile_xhtml([_ok(1, "Good"), _failed(2, "Bad")], days=7)
    assert 'href="#article-1"' in xhtml
    assert "Good" in xhtml
    assert 'href="#article-2"' not in xhtml
    assert "Bad" not in xhtml
```

- [ ] **Step 2: Run all compiler tests**

Run: `pytest tests/test_compiler.py -v`

Expected: 4 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 5.5: compiler — article body contains anchor, title, URL, and content

**Files:**
- Modify: `tests/test_compiler.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_compiler.py`:

```python
def test_article_has_anchor_title_url_and_content() -> None:
    xhtml = compile_xhtml(
        [_ok(1, "My Article", url="https://mysite.com", content="<p>Text here</p>")],
        days=7,
    )
    assert 'name="article-1"' in xhtml
    assert "<h1>My Article</h1>" in xhtml
    assert "https://mysite.com" in xhtml
    assert "<p>Text here</p>" in xhtml
```

- [ ] **Step 2: Run all compiler tests**

Run: `pytest tests/test_compiler.py -v`

Expected: 5 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 5.6: compiler — failed articles are absent from the body

**Files:**
- Modify: `tests/test_compiler.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_compiler.py`:

```python
def test_failed_articles_absent_from_body() -> None:
    xhtml = compile_xhtml([_ok(1, "Good"), _failed(2, "Bad")], days=7)
    assert 'name="article-1"' in xhtml
    assert 'name="article-2"' not in xhtml
```

- [ ] **Step 2: Run all compiler tests**

Run: `pytest tests/test_compiler.py -v`

Expected: 6 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 5.7: compiler — page breaks appear between sections

**Files:**
- Modify: `tests/test_compiler.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_compiler.py`:

```python
def test_page_breaks_appear_between_sections() -> None:
    xhtml = compile_xhtml([_ok(1, "A"), _ok(2, "B")], days=7)
    # Minimum: after title page, after TOC, after each article (2)
    assert xhtml.count("<MBP:PAGEBREAK/>") >= 4
```

- [ ] **Step 2: Run all compiler tests**

Run: `pytest tests/test_compiler.py -v`

Expected: 7 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 5.8: compiler — special characters in titles are HTML-escaped

**Files:**
- Modify: `tests/test_compiler.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_compiler.py`:

```python
def test_special_chars_in_title_are_escaped() -> None:
    xhtml = compile_xhtml([_ok(1, "AT&T > Others")], days=7)
    assert "AT&amp;T" in xhtml
    assert "&gt;" in xhtml
    # The raw unescaped string must not appear
    assert "AT&T" not in xhtml.replace("AT&amp;T", "PLACEHOLDER")
```

- [ ] **Step 2: Run all compiler tests**

Run: `pytest tests/test_compiler.py -v`

Expected: 8 tests PASSED.

- [ ] **Step 3: Commit**

```bash
git add courier/compiler.py tests/test_compiler.py
git commit -m "feat: compiler module — assemble Kindle XHTML with TOC and page breaks"
```

> **→ Check with user before continuing.**

---

## Task 6: `main.py` — CLI entry point and orchestration

No unit tests for `main.py` — it is covered by the integration tests in Tasks 7.1–7.3.

**Files:**
- Create: `main.py`

- [ ] **Step 1: Implement `main.py`**

```python
import argparse
import sys
from datetime import datetime, timezone

from courier import compiler, pinboard, scraper, store


def _parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Fetch recent Pinboard bookmarks and compile them for Kindle."
    )
    parser.add_argument(
        "--days", type=int, default=7,
        help="Include bookmarks added in the last N days (default: 7)"
    )
    parser.add_argument(
        "--output", default="reading.html",
        help="Output filename (default: reading.html)"
    )
    return parser.parse_args()


def main() -> None:
    args = _parse_args()

    store.clear()

    print(f"Fetching bookmarks from the last {args.days} day(s)...")
    try:
        bookmarks = pinboard.fetch_bookmarks(args.days)
    except RuntimeError as e:
        print(f"Error: {e}", file=sys.stderr)
        sys.exit(1)

    if not bookmarks:
        print("No bookmarks found in that date range.")
        sys.exit(0)

    print(f"Found {len(bookmarks)} bookmark(s). Fetching articles...")

    progress = {
        "run_started_at": datetime.now(timezone.utc).isoformat(),
        "days": args.days,
        "bookmarks": bookmarks,
        "last_fetched_index": 0,
    }
    store.save_progress(progress)

    for i, bookmark in enumerate(bookmarks, start=1):
        print(f"  [{i}/{len(bookmarks)}] {bookmark['title']}")
        result = scraper.fetch_article(bookmark["url"])
        article = {
            "index": i,
            "title": bookmark["title"],
            "url": bookmark["url"],
            "fetched_at": datetime.now(timezone.utc).isoformat(),
            **result,
        }
        store.save_article(article)
        if result["status"] == "ok":
            progress["last_fetched_index"] = i
            store.save_progress(progress)
        else:
            print(f"  Warning: could not fetch {bookmark['url']}", file=sys.stderr)

    articles = store.load_articles()
    if not any(a["status"] == "ok" for a in articles):
        print("Error: no articles were successfully fetched.", file=sys.stderr)
        sys.exit(1)

    successful = sum(1 for a in articles if a["status"] == "ok")
    print(f"Compiling {successful} article(s) into {args.output}...")
    xhtml = compiler.compile_xhtml(articles, args.days)
    with open(args.output, "w", encoding="utf-8") as f:
        f.write(xhtml)
    print(f"Done. Written to {args.output}")


if __name__ == "__main__":
    main()
```

- [ ] **Step 2: Verify the CLI starts correctly**

Run: `python main.py --help`

Expected: usage text listing `--days` and `--output`, no import errors.

- [ ] **Step 3: Commit**

```bash
git add main.py
git commit -m "feat: main.py — CLI entry point and pipeline orchestration"
```

> **→ Check with user before continuing.**

---

## Task 7.1: integration — full pipeline produces a correctly structured output file

**Files:**
- Create: `tests/test_integration.py`

- [ ] **Step 1: Write the failing test**

```python
# tests/test_integration.py
import sys
from datetime import datetime, timezone, timedelta
from pathlib import Path
from unittest.mock import patch, MagicMock

import pytest

import courier.store as store


def _make_feed_entry(title: str, url: str, days_ago: float = 1) -> MagicMock:
    t = datetime.now(timezone.utc) - timedelta(days=days_ago)
    entry = MagicMock()
    entry.title = title
    entry.link = url
    entry.published_parsed = t.timetuple()
    return entry


def test_full_pipeline_produces_output_file(monkeypatch: pytest.MonkeyPatch, tmp_path: Path, work_dir: Path) -> None:
    output_file = tmp_path / "reading.html"
    monkeypatch.setattr(sys, "argv", ["main.py", "--days", "7", "--output", str(output_file)])

    feed = MagicMock()
    feed.bozo = False
    feed.bozo_exception = None
    feed.entries = [
        _make_feed_entry("First Article", "https://example.com/first"),
        _make_feed_entry("Second Article", "https://example.com/second"),
    ]

    mock_response = MagicMock()
    mock_response.text = "<html><body><p>Article content.</p></body></html>"
    mock_response.raise_for_status.return_value = None

    with patch("courier.pinboard.feedparser.parse", return_value=feed):
        with patch("courier.scraper.requests.get", return_value=mock_response):
            with patch("courier.scraper.trafilatura.extract", return_value="<p>Article content.</p>"):
                import main
                main.main()

    assert output_file.exists()
    content = output_file.read_text(encoding="utf-8")
    assert "First Article" in content
    assert "Second Article" in content
    assert "Contents" in content
    assert 'href="#article-1"' in content
    assert 'href="#article-2"' in content
    assert 'name="article-1"' in content
    assert 'name="article-2"' in content
    assert "<MBP:PAGEBREAK/>" in content
    assert "Article content." in content
```

- [ ] **Step 2: Run to verify it fails**

Run: `pytest tests/test_integration.py::test_full_pipeline_produces_output_file -v`

Expected: FAILED — `ModuleNotFoundError: No module named 'main'` or similar import error.

- [ ] **Step 3: Run to verify it passes**

Run: `pytest tests/test_integration.py::test_full_pipeline_produces_output_file -v`

Expected: PASSED.

> **→ Check with user before continuing.**

---

## Task 7.2: integration — a failed article is excluded from the output

**Files:**
- Modify: `tests/test_integration.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_integration.py`:

```python
def test_failed_article_excluded_from_output(monkeypatch: pytest.MonkeyPatch, tmp_path: Path, work_dir: Path) -> None:
    output_file = tmp_path / "reading.html"
    monkeypatch.setattr(sys, "argv", ["main.py", "--days", "7", "--output", str(output_file)])

    feed = MagicMock()
    feed.bozo = False
    feed.bozo_exception = None
    feed.entries = [
        _make_feed_entry("Good Article", "https://example.com/good"),
        _make_feed_entry("Bad Article", "https://example.com/bad"),
    ]

    good_response = MagicMock()
    good_response.text = "<html><body><p>Good content.</p></body></html>"
    good_response.raise_for_status.return_value = None

    def mock_get(url: str, **kwargs) -> MagicMock:
        if "bad" in url:
            raise Exception("Simulated failure")
        return good_response

    with patch("courier.pinboard.feedparser.parse", return_value=feed):
        with patch("courier.scraper.requests.get", side_effect=mock_get):
            with patch("courier.scraper.trafilatura.extract", return_value="<p>Good content.</p>"):
                import main
                main.main()

    content = output_file.read_text(encoding="utf-8")
    assert "Good Article" in content
    assert "Bad Article" not in content
```

- [ ] **Step 2: Run all integration tests**

Run: `pytest tests/test_integration.py -v`

Expected: 2 tests PASSED.

> **→ Check with user before continuing.**

---

## Task 7.3: integration — exits with code 1 when all articles fail

**Files:**
- Modify: `tests/test_integration.py`

- [ ] **Step 1: Write the test**

Add to `tests/test_integration.py`:

```python
def test_exits_with_error_when_all_articles_fail(monkeypatch: pytest.MonkeyPatch, tmp_path: Path, work_dir: Path) -> None:
    output_file = tmp_path / "reading.html"
    monkeypatch.setattr(sys, "argv", ["main.py", "--days", "7", "--output", str(output_file)])

    feed = MagicMock()
    feed.bozo = False
    feed.bozo_exception = None
    feed.entries = [_make_feed_entry("Any Article", "https://example.com/any")]

    with patch("courier.pinboard.feedparser.parse", return_value=feed):
        with patch("courier.scraper.requests.get", side_effect=Exception("All requests fail")):
            with pytest.raises(SystemExit) as exc_info:
                import main
                main.main()

    assert exc_info.value.code == 1
    assert not output_file.exists()
```

- [ ] **Step 2: Run all integration tests**

Run: `pytest tests/test_integration.py -v`

Expected: 3 tests PASSED.

- [ ] **Step 3: Run the full test suite**

Run: `pytest -v`

Expected: all tests PASSED, no failures.

- [ ] **Step 4: Commit**

```bash
git add tests/test_integration.py
git commit -m "test: integration tests — full pipeline with mocked network"
```

> **→ Check with user before continuing.**
