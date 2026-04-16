# Spike 001: Extraction Quality, XHTML Sanitisation, Title Quality

## What This Tests
Validates that readability-lxml can extract article content from real Pinboard bookmarks, that the extracted HTML can be sanitised to valid XHTML via lxml, and that title extraction produces usable results (with Pinboard description as fallback).

## Prerequisites
- Python 3.12+ with venv at project root `.venv/`
- Installed packages: requests, readability-lxml, lxml, jinja2
- Network access (fetches live URLs)

## How to Run

1. `cd /home/nik/dev/wharfinger-courier-arness`
2. `source .venv/bin/activate`
3. `cd .arness/spikes/spike-001-extraction-xhtml-quality/`
4. `python run_spike.py`

## What to Look For
- **Success:** Per-article table shows mostly "good" content quality, all "OK" for XML validity, mostly "good" for title quality. Summary shows >=80% meaningful extraction, 100% XHTML validity, >=70% good titles. `spike-output/test-document.xhtml` written and validated.
- **Failure:** Low extraction rates, XML parse errors, generic/empty titles dominating output.

## Result
- **Status:** Validated
- **Evidence:** 10/12 articles extracted with good content (83%). 11/12 produced valid XHTML (the 1 failure was a 403 fetch error from BoardGameGeek, not an XHTML issue). 11/12 titles were good quality (92%). A 148KB multi-article XHTML document was written and validated successfully. The arxiv.org abstract page extracted only 39 chars (expected -- it is a metadata page, not a full article).
