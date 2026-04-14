# Kindle Scraper Design

**Date:** 2026-04-14
**Project:** wharfinger-courier

## Overview

A Python application that reads bookmarks tagged `toread` on Pinboard, scrapes the linked pages, extracts the main article content, and produces a single Kindle-compatible XHTML file for manual transfer to an old Amazon Kindle.

## CLI

```
python main.py --days 7 --output reading.html
```

- `--days N` — include bookmarks added in the last N days (default: 7)
- `--output FILE` — path to write the Kindle XHTML file (default: `reading.html`)

## Project Layout

```
wharfinger-courier/
├── main.py               # Entry point, CLI parsing, orchestration
└── courier/
    ├── __init__.py
    ├── pinboard.py       # Fetch bookmarks from Pinboard RSS feed
    ├── scraper.py        # Fetch page + extract article content
    └── compiler.py       # Assemble articles into Kindle XHTML
```

## Data Flow

1. `main.py` parses CLI args
2. `pinboard.py` fetches the public RSS feed at `https://feeds.pinboard.in/rss/u:niksilver/t:toread/` and returns bookmarks (title + URL) added within the last N days (capped at 50 items — the RSS feed limit)
3. For each bookmark, `scraper.py` fetches the page, extracts the main article content using `trafilatura`, and saves the result to `work/articles/`
4. `compiler.py` reads all article files from `work/articles/` and writes the Kindle XHTML output file

## Work Directory and Logging

```
work/
├── progress.json          # Run state: date range, bookmark list, last successful fetch
└── articles/
    ├── 001-article-slug.json
    ├── 002-article-slug.json
    └── ...
```

**Article JSON schema:**
```json
{
  "index": 1,
  "title": "Article Title",
  "url": "https://example.com/article",
  "fetched_at": "2026-04-14T10:30:00Z",
  "status": "ok",
  "content_html": "<p>Article body...</p>"
}
```

`status` is either `"ok"` or `"failed"`. Failed articles are recorded but skipped during compilation. `content_html` is omitted when status is `"failed"`.

**`progress.json` schema:**
```json
{
  "run_started_at": "2026-04-14T10:29:55Z",
  "days": 7,
  "bookmarks": [
    {"title": "...", "url": "...", "pinboard_date": "2026-04-13T..."}
  ],
  "last_fetched_index": 3
}
```

`last_fetched_index` is updated after each successful article fetch, enabling future retry logic to resume from where it left off.

The `work/` directory is overwritten on each fresh run of `main.py`.

## Output Document Structure

A single XHTML file formatted for Amazon Kindle (XHTML 1.1):

```
[Title page: "Reading — last 7 days" with run date]
<MBP:PAGEBREAK/>
[Table of contents — numbered list of article titles as anchor links]
<MBP:PAGEBREAK/>
[Article 1 — H1 title, source URL as small italic text, then content]
<MBP:PAGEBREAK/>
[Article 2 ...]
...
```

TOC links use `<A HREF="#article-1">Title</A>` pointing to `<A NAME="article-1"/>` anchors placed just before each article's `<H1>`.

Failed articles are omitted from both the TOC and the body.

## Dependencies

- `requests` — HTTP fetching
- `trafilatura` — article content extraction
- `feedparser` — RSS feed parsing
- Standard library: `argparse`, `json`, `datetime`, `re`, `os`

## Error Handling

- If a page fetch or extraction fails, the article is saved with `"status": "failed"` and a console warning is printed. The run continues with remaining articles.
- If the Pinboard RSS feed is unreachable, the script exits with a clear error message.
- If no articles are successfully fetched, the script exits without writing an output file.

## Testing

- `pinboard.py` — unit tests with a mock RSS response covering date filtering and the 50-item cap
- `scraper.py` — unit tests with mock HTTP responses; at least one test for extraction failure handling
- `compiler.py` — unit tests asserting correct TOC structure, page breaks, and that failed articles are excluded
- One integration test running the full pipeline against a small fixture RSS feed and mock pages
