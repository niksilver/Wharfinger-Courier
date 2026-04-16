"""
Spike 002: Pinboard JSON feed format verification.

Validates that the public Pinboard JSON feed has the expected field names,
types, and structure for the Wharfinger Courier feed parser.
"""

import json
import sys
from datetime import datetime

import requests


FEED_URL = "https://feeds.pinboard.in/json/u:niksilver/t:toread/"
USER_AGENT = "WharfingerCourier/0.1 spike"
TIMEOUT = 30

EXPECTED_FIELDS = {"href", "description", "time", "toread"}


def fetch_feed():
    """Fetch the Pinboard JSON feed. Returns (response, error_message)."""
    try:
        resp = requests.get(
            FEED_URL,
            timeout=TIMEOUT,
            headers={"User-Agent": USER_AGENT},
        )
    except requests.RequestException as e:
        return None, f"Request failed: {e}"

    if resp.status_code != 200:
        return None, f"Non-200 status code: {resp.status_code}. Body: {resp.text[:500]}"

    return resp, None


def analyse_time_field(value):
    """Try to parse the time field and report on its format."""
    print(f"  Raw value: {value!r}")
    print(f"  Type: {type(value).__name__}")

    # Try ISO 8601 via fromisoformat
    try:
        parsed = datetime.fromisoformat(value)
        print(f"  datetime.fromisoformat() succeeded: {parsed}")
        return True
    except (ValueError, TypeError) as e:
        print(f"  datetime.fromisoformat() failed: {e}")

    # Try common alternative formats
    for fmt_name, fmt in [
        ("RFC 2822-ish", "%a, %d %b %Y %H:%M:%S %z"),
        ("Unix timestamp (string)", "unix"),
    ]:
        if fmt == "unix":
            try:
                parsed = datetime.fromtimestamp(float(value))
                print(f"  Parsed as Unix timestamp: {parsed}")
                return True
            except (ValueError, TypeError):
                pass
        else:
            try:
                parsed = datetime.strptime(value, fmt)
                print(f"  Parsed with {fmt_name} format: {parsed}")
                return True
            except (ValueError, TypeError):
                pass

    print("  Could not parse with any known format.")
    return False


def main():
    print("=" * 60)
    print("Spike 002: Pinboard JSON Feed Format Verification")
    print("=" * 60)
    print(f"\nFetching: {FEED_URL}")

    resp, err = fetch_feed()
    if err:
        print(f"\nFETCH FAILED: {err}")
        print("\nVerdict: FAILED -- feed not accessible.")
        sys.exit(1)

    print(f"HTTP status: {resp.status_code}")
    print(f"Content-Type: {resp.headers.get('Content-Type', 'not set')}")

    # Parse JSON
    try:
        data = resp.json()
    except json.JSONDecodeError as e:
        print(f"\nJSON PARSE FAILED: {e}")
        print(f"Raw body (first 500 chars): {resp.text[:500]}")
        print("\nVerdict: FAILED -- response is not valid JSON.")
        sys.exit(1)

    # --- Raw output of first 2 bookmarks ---
    print("\n" + "-" * 60)
    print("RAW JSON: First 2 bookmarks")
    print("-" * 60)

    if isinstance(data, list):
        sample = data[:2]
    elif isinstance(data, dict):
        # Maybe it wraps bookmarks in a key
        print(f"Top-level is a dict with keys: {list(data.keys())}")
        # Try to find an array inside
        for key, val in data.items():
            if isinstance(val, list) and len(val) > 0:
                print(f"Found list under key {key!r} with {len(val)} items")
                sample = val[:2]
                break
        else:
            sample = [data]
    else:
        sample = []

    print(json.dumps(sample, indent=2, ensure_ascii=False))

    # --- Structure analysis ---
    print("\n" + "-" * 60)
    print("STRUCTURE ANALYSIS")
    print("-" * 60)

    print(f"\nTop-level type: {type(data).__name__}")
    if isinstance(data, list):
        print(f"Top-level length: {len(data)}")
        bookmarks = data
    else:
        print("WARNING: Top-level is NOT a list.")
        bookmarks = sample  # use whatever we found

    if not bookmarks:
        print("No bookmarks found to analyse.")
        sys.exit(1)

    first = bookmarks[0]
    all_fields = set(first.keys())
    print(f"\nAll field names in first bookmark: {sorted(all_fields)}")

    # Check for expected fields
    print("\n--- Expected field check ---")
    for field in sorted(EXPECTED_FIELDS):
        if field in all_fields:
            val = first[field]
            print(f"  {field}: present, type={type(val).__name__}, value={val!r}")
        else:
            print(f"  {field}: MISSING")

    # Check for unexpected fields
    unexpected = all_fields - EXPECTED_FIELDS
    if unexpected:
        print(f"\n--- Additional fields (not in expected set) ---")
        for field in sorted(unexpected):
            val = first[field]
            print(f"  {field}: type={type(val).__name__}, value={val!r}")
    else:
        print("\nNo unexpected fields.")

    # --- toread analysis ---
    print("\n" + "-" * 60)
    print("TOREAD FIELD ANALYSIS")
    print("-" * 60)
    if "toread" in all_fields:
        val = first["toread"]
        print(f"  Value: {val!r}")
        print(f"  Type: {type(val).__name__}")
        if isinstance(val, str):
            print(f"  Is string 'yes': {val == 'yes'}")
            print(f"  Is string 'true': {val == 'true'}")
        elif isinstance(val, bool):
            print(f"  Boolean value: {val}")
        elif isinstance(val, int):
            print(f"  Integer value: {val}")
    else:
        print("  toread field is ABSENT.")
        print("  Checking for alternative fields...")
        for candidate in ["shared", "read", "unread", "is_toread"]:
            if candidate in all_fields:
                print(f"  Found '{candidate}': {first[candidate]!r}")

    # --- time analysis ---
    print("\n" + "-" * 60)
    print("TIME FIELD ANALYSIS")
    print("-" * 60)
    if "time" in all_fields:
        analyse_time_field(first["time"])
    elif "dt" in all_fields:
        print("  'time' absent but 'dt' found:")
        analyse_time_field(first["dt"])
    else:
        print("  No time-like field found.")

    # --- Verdicts ---
    print("\n" + "=" * 60)
    print("VALIDATION VERDICTS")
    print("=" * 60)

    # Criterion 1: accessible without auth
    print("\n1. Feed accessible without authentication:")
    print(f"   PASS -- HTTP {resp.status_code}, got {len(bookmarks)} bookmarks")

    # Criterion 2: field names
    print("\n2. Expected field names present (href, description, time, toread):")
    missing = EXPECTED_FIELDS - all_fields
    if not missing:
        print("   PASS -- all four fields present")
    else:
        print(f"   FAIL -- missing: {missing}")
        extra_note = all_fields - EXPECTED_FIELDS
        if extra_note:
            print(f"   Note: additional fields found: {extra_note}")

    # Criterion 3: toread value/type
    print("\n3. toread flag value and type:")
    if "toread" in all_fields:
        val = first["toread"]
        print(f"   Value={val!r}, type={type(val).__name__}")
        print("   PASS -- field present and inspectable")
    else:
        print("   FAIL -- toread field not present")

    # Criterion 4: time format
    print("\n4. time field datetime format:")
    if "time" in all_fields:
        time_val = first["time"]
        try:
            datetime.fromisoformat(time_val)
            print(f"   PASS -- ISO 8601 parseable via fromisoformat()")
            print(f"   Format: {time_val!r}")
        except Exception:
            print(f"   WARNING -- not ISO 8601: {time_val!r}")
    else:
        print("   FAIL -- time field not present")

    # Criterion 5: flat array
    print("\n5. Top-level structure is flat array of bookmark objects:")
    if isinstance(data, list) and len(data) > 0 and isinstance(data[0], dict):
        print("   PASS -- list of dicts")
    else:
        print(f"   FAIL -- top-level type is {type(data).__name__}")

    print("\n" + "=" * 60)
    print("DONE")
    print("=" * 60)


if __name__ == "__main__":
    main()
