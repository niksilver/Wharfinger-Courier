"""Spike 003: Validate Pinboard RSS feed format and parsability.

Usage: python run_spike.py <FEED_URL>

The feed URL contains a private Pinboard secret token.
Do not hardcode it or commit it.
"""

import sys
import xml.etree.ElementTree as ET
from datetime import datetime
from email.utils import parsedate_to_datetime

import requests


def main():
    if len(sys.argv) < 2:
        print("Usage: python run_spike.py <FEED_URL>")
        print("  The feed URL should be a Pinboard RSS feed URL.")
        sys.exit(1)

    feed_url = sys.argv[1]

    # --- Criterion 1: Feed is accessible (HTTP 200) ---
    print("=" * 60)
    print("FETCHING FEED")
    print("=" * 60)
    print(f"URL: {feed_url[:40]}...{feed_url[-20:]}")
    print()

    try:
        resp = requests.get(
            feed_url,
            timeout=30,
            headers={"User-Agent": "WharfingerCourier/0.1 spike"},
        )
    except requests.RequestException as e:
        print(f"FETCH ERROR: {e}")
        sys.exit(1)

    print(f"Status code: {resp.status_code}")
    print(f"Content-Type: {resp.headers.get('Content-Type', '(not set)')}")
    print(f"Content length: {len(resp.content)} bytes")
    print()

    if resp.status_code != 200:
        print(f"FAIL: Non-200 status code: {resp.status_code}")
        sys.exit(1)

    # --- Criterion 2: Parse with stdlib xml.etree.ElementTree ---
    print("=" * 60)
    print("PARSING XML")
    print("=" * 60)

    try:
        root = ET.fromstring(resp.content)
        print(f"Root tag: {root.tag}")
        print("Parse: OK (stdlib xml.etree.ElementTree)")
    except ET.ParseError as e:
        print(f"PARSE ERROR: {e}")
        print("FAIL: Cannot parse with stdlib ElementTree")
        sys.exit(1)

    print()

    # --- Detect feed format ---
    print("=" * 60)
    print("FEED FORMAT DETECTION")
    print("=" * 60)

    # RSS 2.0: root tag is <rss>, items under <channel><item>
    # Atom: root tag is <feed> (with Atom namespace), entries under <entry>
    # RDF/RSS 1.0: root tag is <rdf:RDF>, items under <item>

    atom_ns = "http://www.w3.org/2005/Atom"
    rdf_ns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    dc_ns = "http://purl.org/dc/elements/1.1/"

    feed_format = "unknown"
    items = []

    if root.tag == "rss":
        feed_format = "RSS 2.0"
        channel = root.find("channel")
        if channel is not None:
            items = channel.findall("item")
    elif root.tag == f"{{{atom_ns}}}feed" or root.tag == "feed":
        feed_format = "Atom"
        items = root.findall(f"{{{atom_ns}}}entry") or root.findall("entry")
    elif root.tag == f"{{{rdf_ns}}}RDF":
        feed_format = "RDF/RSS 1.0"
        # RDF items are direct children of root, not under channel
        items = root.findall("{http://purl.org/rss/1.0/}item")
        if not items:
            items = root.findall("item")
    else:
        feed_format = f"unknown (root tag: {root.tag})"

    print(f"Detected format: {feed_format}")
    print(f"Number of items found: {len(items)}")
    print()

    # --- Print raw XML of first item ---
    print("=" * 60)
    print("RAW XML OF FIRST ITEM")
    print("=" * 60)

    if items:
        ET.indent(items[0], space="  ")
        raw_xml = ET.tostring(items[0], encoding="unicode")
        print(raw_xml)
    else:
        print("(no items found)")
    print()

    # --- Extract fields from each item (up to 5) ---
    print("=" * 60)
    print("EXTRACTED FIELDS (up to 5 items)")
    print("=" * 60)

    rss10_ns = "http://purl.org/rss/1.0/"
    parsed_items = []

    for i, item in enumerate(items[:5]):
        print(f"\n--- Item {i + 1} ---")

        # Extract URL
        url = None
        if feed_format == "RSS 2.0":
            link_el = item.find("link")
            if link_el is not None and link_el.text:
                url = link_el.text.strip()
            if not url:
                guid_el = item.find("guid")
                if guid_el is not None and guid_el.text:
                    url = guid_el.text.strip()
        elif feed_format == "Atom":
            link_el = item.find(f"{{{atom_ns}}}link")
            if link_el is not None:
                url = link_el.get("href", "").strip()
            if not url:
                id_el = item.find(f"{{{atom_ns}}}id")
                if id_el is not None and id_el.text:
                    url = id_el.text.strip()
        elif feed_format == "RDF/RSS 1.0":
            # RDF items often have rdf:about attribute as the URL
            url = item.get(f"{{{rdf_ns}}}about")
            if not url:
                link_el = item.find(f"{{{rss10_ns}}}link")
                if link_el is not None and link_el.text:
                    url = link_el.text.strip()
                else:
                    link_el = item.find("link")
                    if link_el is not None and link_el.text:
                        url = link_el.text.strip()

        # Extract title
        title = None
        if feed_format == "RSS 2.0":
            title_el = item.find("title")
        elif feed_format == "Atom":
            title_el = item.find(f"{{{atom_ns}}}title")
        elif feed_format == "RDF/RSS 1.0":
            title_el = item.find(f"{{{rss10_ns}}}title")
            if title_el is None:
                title_el = item.find("title")
        else:
            title_el = item.find("title")
        if title_el is not None and title_el.text:
            title = title_el.text.strip()

        # Extract timestamp
        timestamp_raw = None
        timestamp_source = None
        if feed_format == "RSS 2.0":
            for tag in ["pubDate", "dc:date"]:
                el = item.find(tag)
                if el is not None and el.text:
                    timestamp_raw = el.text.strip()
                    timestamp_source = tag
                    break
        elif feed_format == "Atom":
            for tag in [f"{{{atom_ns}}}published", f"{{{atom_ns}}}updated"]:
                el = item.find(tag)
                if el is not None and el.text:
                    timestamp_raw = el.text.strip()
                    timestamp_source = tag.split("}")[-1]
                    break
        elif feed_format == "RDF/RSS 1.0":
            # Try dc:date (Dublin Core)
            el = item.find(f"{{{dc_ns}}}date")
            if el is not None and el.text:
                timestamp_raw = el.text.strip()
                timestamp_source = "dc:date"
            if not timestamp_raw:
                el = item.find("pubDate")
                if el is not None and el.text:
                    timestamp_raw = el.text.strip()
                    timestamp_source = "pubDate"

        print(f"  URL:       {url or '(not found)'}")
        print(f"  Title:     {title or '(not found)'}")
        print(f"  Timestamp: {timestamp_raw or '(not found)'} (from <{timestamp_source}>)")

        # --- Criterion 4: Parse timestamp ---
        parsed_dt = None
        parse_method = None

        if timestamp_raw:
            # Try fromisoformat first
            try:
                parsed_dt = datetime.fromisoformat(timestamp_raw)
                parse_method = "datetime.fromisoformat()"
            except ValueError:
                pass

            # Try RFC 2822 (common in RSS pubDate)
            if parsed_dt is None:
                try:
                    parsed_dt = parsedate_to_datetime(timestamp_raw)
                    parse_method = "email.utils.parsedate_to_datetime()"
                except (ValueError, TypeError):
                    pass

            if parsed_dt:
                print(f"  Parsed:    {parsed_dt.isoformat()} via {parse_method}")
            else:
                print(f"  Parsed:    FAILED to parse '{timestamp_raw}'")

        parsed_items.append({
            "url": url,
            "title": title,
            "timestamp_raw": timestamp_raw,
            "timestamp_parsed": parsed_dt,
            "parse_method": parse_method,
        })

    # --- Verdicts ---
    print()
    print("=" * 60)
    print("VALIDATION VERDICTS")
    print("=" * 60)

    c1 = resp.status_code == 200
    print(f"1. RSS feed accessible (HTTP 200):        {'PASS' if c1 else 'FAIL'}")

    c2 = root is not None
    print(f"2. Parseable with stdlib ElementTree:      {'PASS' if c2 else 'FAIL'}")

    c3 = all(
        p["url"] and p["title"] and p["timestamp_raw"]
        for p in parsed_items
    ) if parsed_items else False
    print(f"3. Items have URL, title, timestamp:       {'PASS' if c3 else 'FAIL'}")

    c4 = all(
        p["timestamp_parsed"] is not None
        for p in parsed_items
    ) if parsed_items else False
    methods_used = set(p["parse_method"] for p in parsed_items if p["parse_method"])
    print(f"4. Timestamps parseable with stdlib:        {'PASS' if c4 else 'FAIL'}  (methods: {methods_used or 'none'})")

    c5 = len(parsed_items) >= 1
    print(f"5. At least one article present:           {'PASS' if c5 else 'FAIL'}  (found: {len(items)})")

    print()
    all_pass = all([c1, c2, c3, c4, c5])
    print(f"OVERALL: {'ALL CRITERIA PASS' if all_pass else 'SOME CRITERIA FAILED'}")


if __name__ == "__main__":
    main()
