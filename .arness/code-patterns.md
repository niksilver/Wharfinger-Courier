# Code Patterns

## Project Stack
- **Language:** Python 3.10+
- **Framework:** None (stdlib CLI with argparse)
- **Package manager:** pip (setuptools)
- **Project layout:** Flat package (`courier/`)

## Naming Conventions

### Pattern: snake_case for modules, functions, and variables
**Description:** All module files, functions, and variables use Python snake_case. No abbreviations except well-known ones (e.g., `url`, `html`).
**Reference:** `courier/store.py:15-24`
**Example:**
```python
def url_hash(url: str) -> str:
    """Return the SHA-256 hex digest of a URL, used as the cache directory name."""
    return hashlib.sha256(url.encode("utf-8")).hexdigest()


def read_status(cache_dir: Path) -> dict[str, Any]:
    """Read status.json from the cache directory. Returns empty dict if not found."""
    status_path = cache_dir / "status.json"
    if not status_path.exists():
        return {}
    return json.loads(status_path.read_text(encoding="utf-8"))
```
**How to apply:** Name all modules, functions, and variables in snake_case. Use descriptive names that indicate purpose. Prefix private/internal functions with underscore.

### Pattern: PascalCase dataclasses for structured data
**Description:** Dataclasses use PascalCase names and serve as the primary structured data containers (Config, Article).
**Reference:** `courier/config.py:13-19`
**Example:**
```python
@dataclass
class Config:
    pinboard_feed_url: str
    cache_dir: Path
    output_dir: Path
    max_fetch_attempts: int = 10
    max_articles_per_run: int = 30
```
**How to apply:** Use `@dataclass` with PascalCase names for any structured data type. Include type annotations on all fields. Use default values where sensible.

### Pattern: Private helper functions with underscore prefix
**Description:** Internal helper functions within a module use a leading underscore to signal they are not part of the module's public API.
**Reference:** `courier/extractor.py:34-53`
**Example:**
```python
def _extract_with_readability(raw_html: str) -> tuple[str, str]:
    """Extract using readability-lxml. Returns (title, html_content)."""
    doc = Document(raw_html)
    return doc.title(), doc.summary()


def _extract_with_trafilatura(raw_html: str, url: str = "") -> str:
    """Extract using trafilatura as fallback. Returns HTML content."""
    import trafilatura

    result = trafilatura.extract(raw_html, url=url, output_format="html", include_links=True)
    return result or ""
```
**How to apply:** Prefix functions that are internal implementation details with `_`. Public API functions (like `extract_article`) have no prefix.

## Project Structure

### Pattern: One responsibility per module
**Description:** Each module in the `courier/` package handles a single responsibility in the pipeline: config loading, HTTP fetching, content extraction, document compilation, filesystem caching, and orchestration.
**Reference:** `courier/` package (all modules)
**Example:**
```
courier/config.py       — Configuration loading
courier/fetcher.py      — HTTP fetching
courier/extractor.py    — Content extraction
courier/compiler.py     — XHTML compilation
courier/store.py        — Filesystem cache/status
courier/orchestrator.py — Pipeline coordination
```
**How to apply:** When adding new functionality, create a new module if it represents a distinct pipeline concern. Keep modules focused on a single responsibility. The orchestrator coordinates between them.

### Pattern: Module-level logger and constants
**Description:** Each module creates its own logger via `logging.getLogger(__name__)` and defines module-level constants before function definitions.
**Reference:** `courier/extractor.py:1-13`
**Example:**
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
**How to apply:** Start each module with a docstring, then imports (future, stdlib, third-party, local), then `logger = logging.getLogger(__name__)`, then UPPER_CASE constants, then function definitions.

## Error Handling

### Pattern: Raise standard exceptions for configuration errors
**Description:** Configuration errors raise standard Python exceptions (FileNotFoundError, KeyError) rather than custom exception classes. The CLI entry point converts the pipeline result to an exit code.
**Reference:** `courier/config.py:32-33`
**Example:**
```python
if not config_path.exists():
    raise FileNotFoundError(f"Config file not found: {config_path}")
```
**How to apply:** Use built-in exceptions for clear error cases. Let `requests` exceptions propagate naturally from the fetcher. The `__main__.py` entry point converts success/failure to exit codes 0/1.

### Pattern: Fallback strategy in extraction
**Description:** The extractor uses readability-lxml as the primary engine and falls back to trafilatura when content is too short, logging the decision.
**Reference:** `courier/extractor.py:16-31`
**Example:**
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
**How to apply:** When a primary approach may fail or produce poor results, implement a fallback with logging. Compare results quantitatively before choosing the fallback.

## Configuration

### Pattern: TOML config with dataclass mapping
**Description:** Configuration is loaded from a TOML file and mapped to a typed Config dataclass. The loader handles Python 3.10/3.11 compatibility for TOML parsing and expands user home directory paths.
**Reference:** `courier/config.py:22-44`
**Example:**
```python
def load_config(path: str | None = None) -> Config:
    if path is not None:
        config_path = Path(path)
    else:
        config_path = Path("config.toml")

    if not config_path.exists():
        raise FileNotFoundError(f"Config file not found: {config_path}")

    with open(config_path, "rb") as f:
        data = tomllib.load(f)

    return Config(
        pinboard_feed_url=data["pinboard_feed_url"],
        cache_dir=Path(data["cache_dir"]).expanduser(),
        output_dir=Path(data["output_dir"]).expanduser(),
        max_fetch_attempts=data.get("max_fetch_attempts", 10),
        max_articles_per_run=data.get("max_articles_per_run", 30),
    )
```
**How to apply:** Define configuration as a dataclass with typed fields and defaults. Load from TOML using `tomllib`/`tomli`. Use `dict.get()` for optional fields with defaults. Expand user paths with `.expanduser()`.
