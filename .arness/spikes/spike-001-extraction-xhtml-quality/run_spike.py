"""
Spike 001: Extraction quality, XHTML sanitisation, Title quality

Validates three linked risks for The Wharfinger Courier:
1. readability-lxml extraction quality on real bookmarks
2. Extracted HTML -> valid XHTML via lxml
3. Title extraction quality (readability vs Pinboard fallback)
"""

import os
import sys
import json
import re
import html
from pathlib import Path

import requests
from readability import Document
from lxml import etree
import lxml.html
from jinja2 import Template

PINBOARD_FEED = "https://feeds.pinboard.in/json/u:niksilver/t:toread/"
USER_AGENT = "WharfingerCourier/0.1 spike"
TIMEOUT = 15
MAX_ARTICLES = 12

FALLBACK_URLS = [
    ("Things You Should Never Do, Part I", "https://www.joelonsoftware.com/2000/04/06/things-you-should-never-do-part-i/"),
    ("Beating the Averages", "https://paulgraham.com/avg.html"),
    ("Software Engineering Careers", "https://www.kalzumeus.com/2010/06/17/software-engineering-careers/"),
    ("Hell Yeah or No", "https://sive.rs/hellyeah"),
    ("The Gervais Principle", "https://www.ribbonfarm.com/2009/10/07/the-gervais-principle-or-the-office-according-to-the-office/"),
    ("BBC News article", "https://www.bbc.com/news/world-us-canada-68519299"),
    ("GPT-4 Technical Report", "https://arxiv.org/abs/2303.08774"),
    ("Lobsters discussion", "https://lobste.rs/s/mmvvvn/i_quit_my_job_to_go_work_on_open_source"),
    ("HN discussion (archive)", "https://web.archive.org/web/2024/https://news.ycombinator.com/item?id=39789411"),
    ("What Leaders Really Do", "https://hbr.org/2007/01/what-leaders-really-do"),
]

XHTML_TEMPLATE = """\
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>Wharfinger Courier — Spike Test</title></head>
<body>
<h1>Table of Contents</h1>
<ol>
{% for article in articles %}
<li>{{ article.title_escaped }}</li>
{% endfor %}
</ol>
{% for article in articles %}
<hr/>
<h2>{{ article.title_escaped }}</h2>
{{ article.body_xhtml }}
{% endfor %}
</body>
</html>
"""


def fetch_pinboard_feed():
    """Try to fetch the Pinboard feed. Return list of (title, url) or None."""
    try:
        print(f"Fetching Pinboard feed: {PINBOARD_FEED}")
        resp = requests.get(PINBOARD_FEED, timeout=TIMEOUT,
                            headers={"User-Agent": USER_AGENT})
        resp.raise_for_status()
        items = resp.json()
        if not items:
            return None
        bookmarks = []
        for item in items[:MAX_ARTICLES]:
            title = item.get("d", "") or item.get("description", "") or ""
            url = item.get("u", "") or item.get("href", "") or ""
            if url:
                bookmarks.append((title, url))
        if bookmarks:
            print(f"  Got {len(bookmarks)} bookmarks from Pinboard feed.")
            return bookmarks
        return None
    except Exception as e:
        print(f"  Pinboard feed failed: {e}")
        return None


def fetch_article(url):
    """Fetch article HTML. Returns (html_str, error_str)."""
    try:
        resp = requests.get(url, timeout=TIMEOUT,
                            headers={"User-Agent": USER_AGENT},
                            allow_redirects=True)
        resp.raise_for_status()
        return resp.text, None
    except Exception as e:
        return None, str(e)


def assess_content_quality(body_html, body_text_len):
    """Heuristic assessment of extraction quality."""
    if body_text_len < 50:
        return "failed"
    if body_text_len < 200:
        return "poor"
    # Check for nav/menu remnants
    nav_signals = ["navigation", "nav-menu", "sidebar", "footer-links",
                   "cookie-consent", "subscribe-form"]
    body_lower = body_html.lower()
    nav_count = sum(1 for s in nav_signals if s in body_lower)
    if nav_count >= 3:
        return "poor"
    if body_text_len < 500 and nav_count >= 1:
        return "partial"
    if body_text_len >= 500:
        return "good"
    return "partial"


def assess_title_quality(title):
    """Heuristic assessment of title quality."""
    if not title or not title.strip():
        return "empty"
    t = title.strip().lower()
    generic = ["home", "untitled", "index", "page", "document", "welcome",
               "error", "404", "403", "loading", "null", "none"]
    if t in generic:
        return "generic"
    if len(t) < 4:
        return "generic"
    # CMS slug patterns
    if re.match(r'^[a-z0-9\-]+$', t) and '-' in t and len(t) > 30:
        return "generic"  # likely a slug
    return "good"


def sanitise_to_xhtml(html_str):
    """
    Attempt to convert HTML to well-formed XHTML.
    Returns (xhtml_bytes, is_valid, error_msg).
    """
    # First: try direct XML parse
    try:
        etree.fromstring(html_str.encode("utf-8"))
        return html_str.encode("utf-8"), True, None
    except etree.XMLSyntaxError:
        pass

    # Fallback: parse as HTML, serialize as XML
    try:
        tree = lxml.html.fromstring(html_str)
        xhtml_bytes = lxml.html.tostring(tree, method="xml", encoding="unicode")
        # Verify it parses as XML
        etree.fromstring(xhtml_bytes.encode("utf-8"))
        return xhtml_bytes, True, None
    except Exception as e:
        return None, False, str(e)


def strip_tags_text(html_str):
    """Get plain text length from HTML."""
    try:
        tree = lxml.html.fromstring(html_str)
        return len(tree.text_content().strip())
    except Exception:
        return 0


def run():
    spike_dir = Path(__file__).parent
    output_dir = spike_dir / "spike-output"
    output_dir.mkdir(exist_ok=True)

    # Step 1: Get URLs
    bookmarks = fetch_pinboard_feed()
    if bookmarks is None:
        print("Using fallback URL list.\n")
        bookmarks = FALLBACK_URLS
        source = "fallback"
    else:
        source = "pinboard"

    # Step 2: Process each article
    results = []
    articles_for_doc = []

    for i, (pinboard_title, url) in enumerate(bookmarks[:MAX_ARTICLES]):
        print(f"[{i+1}/{len(bookmarks[:MAX_ARTICLES])}] Fetching: {url[:70]}...")

        raw_html, fetch_err = fetch_article(url)
        if fetch_err:
            print(f"  FETCH ERROR: {fetch_err}")
            results.append({
                "url": url,
                "pinboard_title": pinboard_title,
                "readability_title": "",
                "body_char_count": 0,
                "xml_valid": False,
                "xml_error": f"fetch failed: {fetch_err}",
                "content_quality": "failed",
                "title_quality": "empty",
            })
            continue

        # Readability extraction
        try:
            doc = Document(raw_html)
            body = doc.summary()
            readability_title = doc.title()
        except Exception as e:
            print(f"  READABILITY ERROR: {e}")
            results.append({
                "url": url,
                "pinboard_title": pinboard_title,
                "readability_title": "",
                "body_char_count": 0,
                "xml_valid": False,
                "xml_error": f"readability failed: {e}",
                "content_quality": "failed",
                "title_quality": "empty",
            })
            continue

        body_text_len = strip_tags_text(body)

        # XHTML sanitisation
        xhtml_body, xml_valid, xml_error = sanitise_to_xhtml(body)

        content_quality = assess_content_quality(body, body_text_len)
        title_quality = assess_title_quality(readability_title)

        results.append({
            "url": url,
            "pinboard_title": pinboard_title,
            "readability_title": readability_title,
            "body_char_count": body_text_len,
            "xml_valid": xml_valid,
            "xml_error": xml_error,
            "content_quality": content_quality,
            "title_quality": title_quality,
        })

        if xml_valid and content_quality in ("good", "partial"):
            # Pick best title
            best_title = readability_title
            if title_quality != "good" and pinboard_title.strip():
                best_title = pinboard_title

            articles_for_doc.append({
                "title_escaped": html.escape(best_title),
                "body_xhtml": xhtml_body if isinstance(xhtml_body, str) else xhtml_body.decode("utf-8"),
            })

        print(f"  content={content_quality}, xml_valid={xml_valid}, "
              f"title={title_quality}, chars={body_text_len}, "
              f"title_r=\"{readability_title[:50]}\"")

    # Step 3: Print results table
    print("\n" + "=" * 100)
    print(f"{'URL':<62} {'Content':<10} {'XML':<6} {'Title':<10}")
    print("-" * 100)
    for r in results:
        url_trunc = r["url"][:60]
        print(f"{url_trunc:<62} {r['content_quality']:<10} "
              f"{'OK' if r['xml_valid'] else 'FAIL':<6} {r['title_quality']:<10}")
    print("=" * 100)

    # Step 4: Print summary
    total = len(results)
    content_counts = {}
    xml_pass = 0
    title_counts = {}
    for r in results:
        content_counts[r["content_quality"]] = content_counts.get(r["content_quality"], 0) + 1
        if r["xml_valid"]:
            xml_pass += 1
        title_counts[r["title_quality"]] = title_counts.get(r["title_quality"], 0) + 1

    print(f"\nSUMMARY ({total} articles, source: {source})")
    print(f"\nContent quality:")
    for k in ["good", "partial", "poor", "failed"]:
        c = content_counts.get(k, 0)
        pct = (c / total * 100) if total else 0
        print(f"  {k:<10}: {c:>2} ({pct:.0f}%)")
    meaningful = content_counts.get("good", 0) + content_counts.get("partial", 0)
    pct_meaningful = (meaningful / total * 100) if total else 0
    print(f"  meaningful (good+partial): {meaningful}/{total} = {pct_meaningful:.0f}%")

    print(f"\nXHTML validity:")
    print(f"  valid:   {xml_pass}/{total}")
    print(f"  invalid: {total - xml_pass}/{total}")
    for r in results:
        if not r["xml_valid"] and r["xml_error"]:
            print(f"    {r['url'][:60]}: {r['xml_error'][:80]}")

    print(f"\nTitle quality:")
    for k in ["good", "generic", "empty"]:
        c = title_counts.get(k, 0)
        pct = (c / total * 100) if total else 0
        print(f"  {k:<10}: {c:>2} ({pct:.0f}%)")

    # Title fallback analysis
    fallback_useful = 0
    for r in results:
        if r["title_quality"] != "good" and r["pinboard_title"].strip():
            fb_quality = assess_title_quality(r["pinboard_title"])
            if fb_quality == "good":
                fallback_useful += 1
                print(f"  Fallback useful: readability=\"{r['readability_title'][:40]}\" "
                      f"-> pinboard=\"{r['pinboard_title'][:40]}\"")

    # Step 5: Write test XHTML document
    print(f"\nWriting test XHTML document with {len(articles_for_doc)} articles...")
    template = Template(XHTML_TEMPLATE)
    xhtml_doc = template.render(articles=articles_for_doc)

    out_path = output_dir / "test-document.xhtml"
    out_path.write_text(xhtml_doc, encoding="utf-8")
    print(f"  Written to: {out_path}")

    # Validate the output
    try:
        etree.parse(str(out_path))
        print("  XHTML document validation: PASSED")
        doc_valid = True
    except etree.XMLSyntaxError as e:
        print(f"  XHTML document validation: FAILED — {e}")
        doc_valid = False

    # Validation criteria assessment
    print("\n" + "=" * 100)
    print("VALIDATION CRITERIA ASSESSMENT")
    print("=" * 100)

    crit1 = pct_meaningful >= 80
    print(f"\n1. >=80% meaningful extraction: {pct_meaningful:.0f}% "
          f"-> {'PASS' if crit1 else 'FAIL'}")

    crit2 = xml_pass == total
    print(f"2. All articles valid XHTML: {xml_pass}/{total} "
          f"-> {'PASS' if crit2 else 'FAIL'}")

    good_titles = title_counts.get("good", 0)
    pct_good_title = (good_titles / total * 100) if total else 0
    crit3 = pct_good_title >= 70
    print(f"3. >=70% good titles: {pct_good_title:.0f}% "
          f"-> {'PASS' if crit3 else 'FAIL'}")

    crit4 = doc_valid and len(articles_for_doc) > 0
    print(f"4. Valid multi-article XHTML document: "
          f"{'PASS' if crit4 else 'FAIL'}")

    all_pass = crit1 and crit2 and crit3 and crit4
    print(f"\nOVERALL: {'ALL CRITERIA MET' if all_pass else 'SOME CRITERIA NOT MET'}")


if __name__ == "__main__":
    run()
