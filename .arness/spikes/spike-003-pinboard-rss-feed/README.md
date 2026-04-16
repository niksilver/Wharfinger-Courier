# Spike 003: Pinboard RSS Feed Format and Parsability

## What This Tests
Validates that a private Pinboard RSS feed is accessible, parseable with Python's stdlib XML parser, and contains the fields needed by The Wharfinger Courier (article URL, title, timestamp).

## Prerequisites
- Python 3.12+
- `requests` library (installed in project venv)
- A valid Pinboard RSS feed URL with secret token

## How to Run

The feed URL contains a private Pinboard secret token. Pass it as a command-line argument -- do not hardcode it or commit it.

1. `cd /home/nik/dev/wharfinger-courier-arness`
2. `source .venv/bin/activate`
3. `cd .arness/spikes/spike-003-pinboard-rss-feed/`
4. `python run_spike.py "<YOUR_PINBOARD_RSS_FEED_URL>"`

## What to Look For
- **Success:** All five validation criteria show PASS. Items display URL, title, and parseable timestamps. Raw XML of first item is printed for inspection.
- **Failure:** HTTP error fetching the feed, XML parse errors, missing fields in items, or unparseable timestamp formats.

## Result
- **Status:** Validated
- **Evidence:** All 5 criteria passed. Feed is RDF/RSS 1.0 format with 19 items. Fields extracted successfully from all items. Timestamps are ISO 8601 format, parseable with `datetime.fromisoformat()`. Run on 2026-04-16.
