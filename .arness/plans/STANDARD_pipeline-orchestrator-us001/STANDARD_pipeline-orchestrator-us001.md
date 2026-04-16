# Standard Plan: Implement Courier Pipeline Orchestrator (US-001)

**Tier:** standard
**Scope:** Wire together all pipeline stages (fetch → extract → compile) in `run_pipeline()`, fixing the RSS feed parsing bug in `fetcher.py` and adding full status tracking, retry/permanent-skip logic, article cap, and run summary output.
**Architect assessment:** 4 files touched (2 modify, 2 create); moderate scope; no new architectural patterns — follows all existing conventions. One blocking bug (feed format) must be fixed before the orchestrator can work.

---

## Spec-Lite

### Problem Statement

`run_pipeline()` is a placeholder stub with no pipeline logic; additionally `fetcher.py` calls `response.json()` on an RDF/RSS 1.0 XML feed, which will raise a parse error on any real run. This change fixes the feed parser and implements the full orchestration: fetch → filter → process articles (fetch HTML, extract, compile) → write output document → print summary.

### Key Requirements

- Fix `courier/fetcher.py`: replace `response.json()` with RDF/RSS 1.0 XML parsing returning `list[dict]` with keys `url`, `title`, `timestamp`
- Implement `run_pipeline()` with private helpers: `_fetch_and_parse_feed`, `_filter_articles`, `_process_article`, `_print_summary`
- Filter out articles in COMPILED, PERMANENTLY_SKIPPED, UNSUPPORTED_CONTENT_TYPE states; cap at `config.max_articles_per_run`
- Track per-article fetch failures; permanently skip after `config.max_fetch_attempts`
- Atomic status writes via `store.write_status()` after each article is processed
- Print run summary to stdout: compiled count, failed, skipped, output path
- `dry_run=True` prints the article list and exits without writing anything; `since_days` accepted but not yet filtered (add `# TODO: US-002` comment)

### Architectural Notes

The orchestrator is the top-level coordinator in a strict single-responsibility pipeline. It calls `fetcher.fetch_feed()` → `fetcher.fetch_article()` → `extractor.extract()` → `compiler.compile_document()`, delegating all side effects to existing modules. `store.py` handles status reads/writes with atomic rename; `compiler.py` provides `Article(title, content, url)` and `compile_document(articles, output_dir) -> Path`. The `status.json` structure: `{"version": 1, "last_run": {...}, "articles": {"<url>": {"title", "status", "fetch_fail_count", "last_updated", "word_count", "last_error"}}}`.

---

## Files to Modify

| File | Action | Rationale |
|------|--------|-----------|
| `courier/fetcher.py` | Modify | Replace `response.json()` with RDF/RSS 1.0 XML parsing using `xml.etree.ElementTree` |
| `courier/orchestrator.py` | Implement | Replace placeholder stub with full pipeline logic |
| `tests/test_fetcher.py` | Create | Test RSS parsing against fixture XML; test HTTP error handling |
| `tests/test_orchestrator.py` | Create | Unit tests with mocked `fetcher`, `extractor`, `store`, `compiler` |

---

## Patterns to Follow

| Pattern | How It Applies |
|---------|---------------|
| Module-level logger | `orchestrator.py` already has `logger = logging.getLogger(__name__)`; `fetcher.py` must use same pattern |
| `_` prefix for private helpers | `_fetch_and_parse_feed`, `_filter_articles`, `_process_article`, `_print_summary` in orchestrator |
| Atomic writes via `store.write_status()` | Call after each article processed — `store.py` handles temp-file + rename internally |
| `pytest` + `tmp_path` + `unittest.mock.patch` | All new tests use `tmp_path` for directories; mock `requests.get` in fetcher tests; mock all I/O in orchestrator tests |
| One responsibility per module | Orchestrator coordinates only; no HTML parsing or XML parsing inline — delegate to helpers |

---

## Implementation Tasks

1. **Fix RSS feed parsing in `courier/fetcher.py`**
   - Files: `courier/fetcher.py`
   - What: Replace `response.json()` with `xml.etree.ElementTree` parsing of RDF/RSS 1.0. Parse `<item>` elements from `{http://www.w3.org/1999/02/22-rdf-syntax-ns#}RDF` root; extract `<link>`, `<title>`, `<dc:date>` into `{"url": ..., "title": ..., "timestamp": ...}` dicts. Return `list[dict]`. Reference implementation: `spikes/spike-003-pinboard-rss-feed/run_spike.py` lines 92–201. Do not log the full feed URL (contains secret token).
   - Pattern: Module-level logger; `requests` for HTTP only
   - Depends on: None

2. **Implement `run_pipeline()` in `courier/orchestrator.py`**
   - Files: `courier/orchestrator.py`
   - What: Implement the full pipeline with four private helpers:
     - `_fetch_and_parse_feed(config)` → calls `fetcher.fetch_feed(config.pinboard_feed_url)`
     - `_filter_articles(feed_items, status_data, config)` → excludes COMPILED/PERMANENTLY_SKIPPED/UNSUPPORTED_CONTENT_TYPE; caps at `config.max_articles_per_run`
     - `_process_article(item, config, status_data)` → fetches HTML, extracts, updates status; increments `fetch_fail_count`; permanently skips when `fetch_fail_count >= config.max_fetch_attempts`; calls `store.write_status()` atomically after each article
     - `_print_summary(compiled, failed, skipped, output_path)` → prints to stdout
     - `run_pipeline()` orchestrates: load status → filter → process (or dry-run preview) → compile → write status → print summary
     - Add `# TODO: US-002` comment where `since_days` filtering would go
   - Pattern: `_` prefix helpers; atomic writes; module-level logger
   - Depends on: Task 1

3. **Write tests for `courier/fetcher.py`**
   - Files: `tests/test_fetcher.py`
   - What: Three test cases: (a) valid RDF/RSS XML returns correct `list[dict]` with `url`, `title`, `timestamp`; (b) HTTP 4xx raises `requests.HTTPError`; (c) malformed XML raises a parse error. Use `unittest.mock.patch("requests.get")` with fixture XML from the spike (`spikes/spike-003-pinboard-rss-feed/sample_feed.xml` if it exists, otherwise inline fixture string).
   - Pattern: `pytest`; `unittest.mock.patch`; no real network calls
   - Depends on: Task 1

4. **Write tests for `courier/orchestrator.py`**
   - Files: `tests/test_orchestrator.py`
   - What: Test cases covering: (a) end-to-end happy path (feed → extract → compile → output file created); (b) article cap enforced; (c) COMPILED articles skipped; (d) PERMANENTLY_SKIPPED articles skipped; (e) fetch failure increments `fetch_fail_count`; (f) permanent skip triggered when `fetch_fail_count >= max_fetch_attempts`; (g) `dry_run=True` prints list without writing files; (h) status written atomically after each article; (i) extraction failure sets EXTRACTION_FAILED; (j) feed fetch failure exits gracefully. Mock `fetcher.fetch_feed`, `fetcher.fetch_article`, `extractor.extract`, `compiler.compile_document`, `store.read_status`, `store.write_status`.
   - Pattern: `pytest` + `tmp_path` + `unittest.mock.patch`
   - Depends on: Task 2

---

## Test Plan

### Tests to Update

| Test File | What to Update | Why |
|-----------|---------------|-----|
| `tests/test_smoke.py` | Verify smoke test still passes | Fetcher signature changes must not break smoke test |

### Tests to Add

| Test File | What to Test | Coverage Gap |
|-----------|-------------|-------------|
| `tests/test_fetcher.py` | RSS XML parsing; HTTP errors; malformed XML | `fetch_feed()` had no tests |
| `tests/test_orchestrator.py` | Full pipeline paths; retry logic; dry-run; article cap | `run_pipeline()` was a stub |

### Verification Command

```bash
pytest tests/ -v
```

---

## Risks & Mitigations

| Risk | Severity | Mitigation |
|------|----------|-----------|
| Feed URL contains secret token — must not appear in logs | Medium | Log only domain or a redacted form at INFO level; full URL only at DEBUG if needed |
| `since_days` parameter is unimplemented — could confuse callers | Low | Add `# TODO: US-002` comment and log a warning if non-None value passed |
| Atomic write relies on `store.write_status()` — if called incorrectly, data could be lost | Medium | Use `store.write_status()` exactly as existing callers do; test with `tmp_path` |
| RDF/RSS 1.0 namespace handling is error-prone | Medium | Copy namespace dict from spike-003 reference implementation exactly |

---

## Review-Lite

| Check | What to Verify |
|-------|---------------|
| Pattern compliance | `orchestrator.py` uses `_` helpers; no business logic in `fetcher.py`; module-level loggers used |
| Test verification | `pytest tests/ -v` passes with all new tests |
| Architect concerns | Feed URL not logged at INFO; `since_days` has TODO comment; atomic writes used correctly |
| Spec-Lite alignment | All 7 key requirements satisfied; `dry_run` works; article cap enforced; permanent skip logic present |

---

## Notes

- Reference for RSS parsing: `spikes/spike-003-pinboard-rss-feed/run_spike.py` lines 92–201
- `since_days` filtering is intentionally deferred to US-002 (UC-002)
- Do not push — user manages git push independently
