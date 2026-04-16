# Spike: Pinboard JSON feed format verification

## What This Tests
Validates that the Pinboard public JSON feed has the expected field names (href, description, time, toread) and value types needed by the Wharfinger Courier feed parser.

## Prerequisites
- Python 3.12+
- `requests` library installed
- Network access to feeds.pinboard.in

## How to Run

1. `cd /home/nik/dev/wharfinger-courier-arness/.arness/spikes/spike-002-pinboard-feed-format/`
2. `source /home/nik/dev/wharfinger-courier-arness/.venv/bin/activate`
3. `python run_spike.py`

## What to Look For
- **Success:** All four expected fields (href, description, time, toread) appear in the raw JSON output with expected types
- **Failure:** Fields are missing, renamed, or have different types than expected

## Result
- **Status:** Partially Validated
- **Evidence:** The feed IS accessible and returns valid JSON as a flat array of bookmark objects. However, ALL FOUR expected field names are wrong. The actual field names are abbreviated: `u` (not `href`), `d` (not `description`), `dt` (not `time`), and `t` (not `toread`). The `toread` concept is expressed as a tags array `t: ["toread"]`, not a dedicated boolean or string flag. The `dt` timestamp is ISO 8601 and parseable by `datetime.fromisoformat()`.
