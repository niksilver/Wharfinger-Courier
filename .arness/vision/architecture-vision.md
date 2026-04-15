# The Wharfinger Courier — Architecture Vision

## Technology Stack

| Layer | Technology | Rationale |
|-------|-----------|-----------|
| Runtime | **Python 3.10+** | Primary language; perfect fit for a synchronous I/O-bound batch pipeline; no performance requirements that would push elsewhere |
| HTTP client | **`requests` 2.33.x** | Clean exception hierarchy for per-article retry logic; synchronous API matches the batch pipeline; well-known and stable |
| Feed parsing | **`requests` + stdlib `json`** | Pinboard JSON feed is a flat array of objects; `feedparser` solves problems (multi-format RSS, namespaces, encoding edge cases) this project doesn't have |
| Content extraction | **`readability-lxml` 0.8.x** (primary) + **`trafilatura` 2.0.x** (fallback) | `readability-lxml` returns structured HTML (headings, paragraphs, lists) via `doc.summary()` / `doc.title()` — direct input to the XHTML pipeline. `trafilatura` has higher overall extraction accuracy (F1 0.958 vs 0.922) and is invoked when readability returns insufficient content |
| XHTML generation | **`jinja2` 3.x** + **`lxml` 5.x** | Jinja2 template captures the Kindle document structure readably; `lxml` sanitises extracted HTML to valid XHTML (`tostring(method="xml")`) before template insertion — required because HTML allows unclosed tags, XHTML does not |
| State / cache | **JSON files** (stdlib `json`) | Filesystem-only per pillar. `status.json` captures per-run outcomes and is the resumption checkpoint; `cache/<url-hash>/` stores raw and extracted article HTML. Human-readable, `cat`-inspectable, no schema migrations |
| CLI | **stdlib `argparse`** | One command, a handful of flags (`--since N`, `--dry-run`). Click and Typer solve problems this project doesn't have; zero added dependencies |
| Logging | **stdlib `logging`** | `FileHandler` in append mode covers the "append-only log for major actions" requirement exactly; zero added dependencies |

**Total external dependencies (pip install):** `requests`, `readability-lxml`, `trafilatura`, `jinja2`. `lxml` comes transitively via `readability-lxml`. All other layers use the standard library.

## Business Constraints & Trade-offs

| Constraint | How Stack Addresses It |
|-----------|----------------------|
| Zero infrastructure cost | All four external dependencies are open-source with permissive licences. No cloud services, no paid APIs. |
| Open-source only | `requests` (Apache 2.0), `readability-lxml` (Apache 2.0), `trafilatura` (Apache 2.0), `jinja2` (BSD-3). No licence conflicts. |
| No database / no server | State is entirely in the local filesystem. The "no DB" pillar is satisfied in letter and spirit — `sqlite3` was explicitly evaluated and rejected for this use case. |

## Pillar Alignment

| Pillar | Status | How the Stack Serves It |
|--------|--------|------------------------|
| Pinboard as source of truth | Supported | Pinboard is accessed via a single read-only HTTP GET to the JSON feed endpoint. No Pinboard write API is used anywhere in the pipeline. Local state is purely downstream — it caches what was fetched, never pushes back. |
| Readable output quality | Supported — validation required | `readability-lxml` returns structured HTML with headings/paragraphs preserved via `doc.summary()`; `lxml` sanitises it to valid XHTML; `jinja2` wraps it in a Kindle-conformant document. `trafilatura` as fallback reduces permanently-failed articles. **Must validate extraction quality on a sample of actual Pinboard bookmarks before building the pipeline** — see Known Risks. |
| No infrastructure ceremony | Supported | Four pip-installable packages; stdlib handles CLI, logging, JSON, and file I/O. No server, no DB, no container. Installation is `pip install -r requirements.txt` and a config file. |
| Resilient and resumable runs | Supported | `status.json` is written atomically at run end (write to temp file, `os.replace()`) and read at run start — it is the resumption checkpoint. Per-article cache files are idempotent. `requests` exceptions are caught per-article; a fetch failure records the error and increments the retry counter without aborting the run. |

## High-Level Architecture

```
┌──────────────────────────────────────────────────────────────┐
│                   courier  (CLI entry point)                  │
│                    argparse  ·  logging                       │
└──────────────────────────────┬───────────────────────────────┘
                                │
               ┌────────────────▼───────────────┐
               │       Pipeline Orchestrator     │
               │  · reads status.json on start   │
               │  · drives fetch → extract loop  │
               │  · writes status.json on finish │
               └──────┬───────────────────┬──────┘
                      │                   │
          ┌───────────▼──────┐  ┌─────────▼──────────┐
          │     Fetcher       │  │      Compiler       │
          │  requests         │  │  jinja2 (template)  │
          │  · Pinboard feed  │  │  lxml (XHTML clean) │
          │  · article URLs   │  │  · TOC generation   │
          └───────────┬──────┘  └─────────┬──────────┘
                      │                   │
          ┌───────────▼──────┐            │
          │    Extractor      │            │
          │  readability-lxml │            │
          │  trafilatura (fb) │            │
          └───────────┬──────┘            │
                      │                   │
          ┌───────────▼───────────────────▼────────────┐
          │              Filesystem Store               │
          │                                             │
          │  cache/<url-hash>/raw.html                  │
          │  cache/<url-hash>/extracted.html            │
          │  status.json          ← resumption point    │
          │  courier.log          ← append-only         │
          │  output/<date>.xhtml  ← compiled document   │
          └─────────────────────────────────────────────┘
                 ▲                         │
        Pinboard JSON feed          Kindle (manual transfer)
        (outbound HTTPS)
```

### Pipeline Orchestrator

The central coordinator. Responsibilities:
- Read `status.json` on startup to determine which articles have been fetched and compiled
- Fetch the Pinboard JSON feed and identify new bookmarks not yet in the cache
- Iterate articles: skip compiled ones (unless `--since N` override), fetch and extract uncached ones, record outcomes
- Invoke the Compiler once all articles are fetched
- Write `status.json` atomically at the end of the run
- Write major actions to `courier.log` throughout

### Fetcher

Handles all outbound HTTP. Responsibilities:
- Fetch the Pinboard JSON feed (`GET /json/`) and parse the response
- Fetch individual article URLs with configurable timeout
- Raise typed exceptions on failure (connection error, timeout, HTTP error) for the orchestrator to catch and record
- Write raw HTML to `cache/<url-hash>/raw.html`

### Extractor

Isolates article body from raw HTML. Responsibilities:
- Run `readability-lxml` (`Document(html).summary()` and `.title()`) as primary
- Fall back to `trafilatura` if readability returns insufficient content (configurable minimum length threshold)
- Write extracted HTML to `cache/<url-hash>/extracted.html`
- Sanitise extracted HTML to valid XHTML using `lxml.html.tostring(..., method="xml")`

### Compiler

Produces the Kindle document. Responsibilities:
- Collect all extracted articles to include in this run
- Render the Jinja2 XHTML template: XML declaration, DOCTYPE, `<head>` with Kindle metadata, table of contents, article sections in order
- Write the output document to the configured output directory with a datestamped filename
- Return document metadata (title, article count, file path) to the orchestrator for the status file

## Pipeline Design

### Run Modes

**Default run** (`courier`):
1. Read `status.json` — identify articles already compiled
2. Fetch Pinboard JSON feed — identify bookmarks not yet compiled
3. For each uncompiled bookmark (up to 30, ordered by recency): check cache → fetch if absent → extract → record outcome
4. Compile all successfully-extracted, uncompiled articles into the output document
5. Write `status.json` and append to `courier.log`

**Archive run** (`courier --since N`):
- Same pipeline, but the "skip compiled articles" filter is replaced by "include all articles bookmarked within the last N days", re-fetching from cache where available

### Article Lifecycle

```
[NEW in Pinboard feed]
        │
        ▼
[FETCH PENDING] ──fetch fails──► [FETCH FAILED] ──retry count > max──► [PERMANENTLY SKIPPED]
        │                               │
        │ fetch succeeds                │ next run retries
        ▼                               ▼
[CACHED] ──extraction fails──► [EXTRACTION FAILED] (logged, skipped from compilation)
        │
        │ extraction succeeds
        ▼
[EXTRACTED] ──included in document──► [COMPILED]
```

All states are persisted in `status.json`. The orchestrator reconstructs the full article set by merging the status file with the current Pinboard feed on each run.

### Retry Behaviour

- Failed HTTP fetches are retried on subsequent runs (not within the same run)
- Each article in `status.json` carries a `fetch_attempts` counter
- Once `fetch_attempts` reaches the configured maximum (default: 10), the article is marked `PERMANENTLY SKIPPED` and excluded from future runs
- A single `requests.Session` is used per run; no automatic retry within a single fetch attempt (retry logic is at the orchestrator level, not the HTTP level)

## Data Architecture

### Filesystem Layout

```
<configured-base-dir>/
├── cache/
│   └── <url-hash>/           # SHA-256 of the article URL, hex-encoded
│       ├── raw.html           # fetched page HTML (source of truth for re-extraction)
│       └── extracted.html     # readability-lxml or trafilatura output
├── status.json                # last run state (written atomically, read on startup)
├── courier.log                # append-only major action log
└── output/
    └── wharfinger-courier-<YYYY-MM-DD>.xhtml
```

### `status.json` Schema

```json
{
  "run_started": "2026-04-15T09:30:00Z",
  "run_finished": "2026-04-15T09:31:42Z",
  "duration_seconds": 102,
  "articles": {
    "<url-hash>": {
      "url": "https://example.com/article",
      "pinboard_title": "Article title from Pinboard bookmark",
      "bookmarked_at": "2026-04-14T18:00:00Z",
      "status": "compiled",
      "fetch_attempts": 1,
      "last_fetch_attempt": "2026-04-15T09:30:05Z",
      "compiled_in": "wharfinger-courier-2026-04-15.xhtml"
    }
  },
  "document": {
    "filename": "wharfinger-courier-2026-04-15.xhtml",
    "article_count": 18,
    "output_path": "/home/nik/courier-output/wharfinger-courier-2026-04-15.xhtml"
  }
}
```

### Atomic Write Pattern

`status.json` is never written in-place. The pattern is:

```python
tmp = status_path.with_suffix(".json.tmp")
tmp.write_text(json.dumps(state, indent=2))
tmp.replace(status_path)   # os.replace() — atomic on POSIX and Windows
```

This ensures a crashed run never leaves a corrupt status file; the previous run's state is always intact.

## Installation & Distribution

### Installation

```bash
pip install requests readability-lxml trafilatura jinja2
```

Or via `pyproject.toml` / `requirements.txt` in the project root. A `pyproject.toml` with `[project.scripts] courier = "courier.__main__:main"` enables `courier` as a direct command after `pip install -e .`.

### Configuration

A single config file (TOML or JSON) in the project directory or a conventional location (e.g., `~/.config/courier/config.toml`). Required fields:

```toml
pinboard_feed_url = "https://feeds.pinboard.in/json/u:niksilver/t:toread/"
cache_dir = "/home/nik/.local/share/courier/cache"
output_dir = "/home/nik/Documents/courier-output"
max_fetch_attempts = 10
max_articles_per_run = 30
```

### Platform Notes

- **Linux / macOS:** No platform-specific concerns. `os.replace()` is atomic on ext4 and APFS.
- **Windows (WSL):** Run inside WSL; native Windows is not a primary target. `os.replace()` is atomic on NTFS on the same filesystem. Cross-filesystem moves (WSL ↔ Windows mount) should be avoided for the temp-file atomic write.
- **Python version:** 3.10+ required. No f-string `=` specifier or `match` statements are mandated, but 3.10 is the oldest actively maintained version and the minimum worth targeting.

## Known Risks & Mitigations

### Risk 1: Extraction quality on Nik's specific bookmark diet

**Description:** `readability-lxml` achieves F1 0.922 in benchmark tests, but benchmarks use curated datasets. Nik's Pinboard feed may contain a disproportionate share of non-standard sites (personal blogs, academic papers, forums, paywalled sites) that underperform on benchmarks. If >20% of articles extract poorly, the "Readable output quality" pillar is compromised.

**Mitigation:** Before building the full pipeline, run a manual validation spike: fetch 15–20 real Pinboard `toread` URLs, apply `readability-lxml`, and manually inspect the output for content completeness and structural HTML preservation.

**Fallback:** If `readability-lxml` proves insufficient as primary, promote `trafilatura` to primary and use `readability-lxml` as fallback. `trafilatura` has higher recall and its `output_format="html"` mode, while less structured, can be post-processed with `lxml`.

---

### Risk 2: Extracted HTML produces invalid XHTML

**Description:** `readability-lxml` returns HTML, not XHTML. HTML permits unclosed void elements (`<br>`, `<img>`), missing quotes, and other constructs that are invalid XHTML. If the sanitisation step (`lxml.html.tostring(..., method="xml")`) does not handle all edge cases — malformed entities, deeply nested structures, unusual Unicode — the compiled document may fail to render on Kindle.

**Mitigation:** Validate the sanitisation step during the extraction quality spike (Risk 1 mitigation). Produce a 3-article test XHTML document and transfer to a Kindle device before writing any pipeline logic.

**Fallback:** If `lxml` sanitisation produces invalid XHTML for specific inputs, add a pre-sanitisation step with `BeautifulSoup(html, "html.parser")` to normalise the HTML before passing to `lxml`. This catches malformed entity references and encoding issues.

---

### Risk 3: Pinboard JSON feed format differs from expected

**Description:** The Pinboard JSON feed field names and structure have not been verified against the actual endpoint. If the fields differ from the expected schema (`href`, `description`, `time`, `toread`), feed parsing breaks silently or raises unhandled exceptions.

**Mitigation:** Fetch Nik's actual Pinboard `toread` JSON feed (`https://feeds.pinboard.in/json/u:niksilver/t:toread/`) before implementing the feed parser. Confirm field names, data types, datetime format, and the structure of the `toread` flag.

**Fallback:** Pinboard also exposes an RSS feed; `feedparser` handles it cleanly. Switching from JSON to RSS parsing is a <30-minute change.

---

### Risk 4: Title extraction quality

**Description:** `readability-lxml`'s `doc.title()` performs site-suffix stripping (e.g., `"Article | SiteName" → "Article"`) but cannot recover a meaningful title from pages that use unhelpful `<title>` tags (e.g., `"Home"`, `"Untitled"`, or a CMS slug).

**Mitigation:** Use the Pinboard bookmark's `description` field as fallback when `doc.title()` returns a string that is identical to the site domain, too short (<10 chars), or appears to be a generic template.

**Fallback:** If title quality remains poor for a significant portion of articles, display the URL hostname as a last resort — at minimum the TOC entry is navigable even if not descriptive.

## Future Architecture Considerations

- **Image support in compiled documents:** Embedding images requires either base64-encoding them inline or managing an XHTML file bundle (`.zip` as `.xhtml`). The current architecture writes a single XHTML file; moving to a bundle would change the Compiler output stage and the file-writing logic. v1's Jinja2 template can accommodate this by adding an `<img>` filter to the extraction step — the structural change is localised to the Compiler. Images could be stripped in v1 and added later without architectural rework.

- **Scheduled / automated runs:** Adding a cron job or launchd entry requires no architectural change — `courier` is already a standalone script. The only consideration is ensuring the log file and status file paths are absolute (not relative to CWD) so the script runs correctly from any invocation context. This is a configuration hygiene issue, not an architectural one.

- **Automated Kindle delivery:** Amazon's Send-to-Kindle accepts email delivery (`.html`, `.pdf`). Adding this would be a new optional post-compile step in the Orchestrator: if configured, `smtplib` sends the compiled XHTML to the device's Kindle email address. This is additive — no existing components change. The current architecture accommodates it naturally.

- **Headless browser support for JS-rendered pages:** Adding Playwright or Puppeteer for JS-heavy pages would replace the `requests` fetch step with a browser-based fetch for specific URLs. The Extractor and Compiler are unaffected. The Fetcher would need a browser-pool abstraction. This is a significant dependency addition and is explicitly deferred — the v1 assumption is that pages are directly accessible HTML.

- **Calibre integration for EPUB/MOBI output:** Calibre's `ebook-convert` CLI can take XHTML input and produce EPUB or MOBI. Adding this would be a new post-compile step: shell out to `ebook-convert` with the compiled XHTML. The Compiler's XHTML output is already the correct input format. Zero architectural changes to existing components.
