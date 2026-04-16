# User Stories from UC-005: Fetch and Extract Article

Source: [UC-005 Fetch and Extract Article](../use-cases/UC-005-fetch-and-extract-article.md)

---

## US-013

**Fetch and cache article content for extraction**

> As Nik, I want each article to be fetched once and cached so that interrupted runs can resume without re-downloading content I've already retrieved.

**Acceptance criteria:**
- The raw HTML response is written to `cache/<url-hash>/raw.html` after a successful fetch.
- If `raw.html` already exists in the cache, the HTTP fetch is skipped.
- The extracted content is written to `cache/<url-hash>/extracted.html`.
- If `extracted.html` already exists in the cache, both the fetch and extraction steps are skipped.
- The article status is set to EXTRACTED with word count, HTTP status code, and content type recorded.
- The URL hash is the SHA-256 hex digest of the article URL.

*Derived from: main success scenario; extensions 2a, 3a.*

---

## US-014

**Fall back to a secondary extractor for thin content**

> As Nik, I want the tool to try a secondary content extractor when the primary one produces very little text so that I get the best possible article content even on pages where the primary extractor struggles.

**Acceptance criteria:**
- If the primary extractor's output falls below the configured minimum word count (default 100), the tool runs the fallback extractor (trafilatura) against the same raw HTML.
- If the fallback output is longer than the primary output, the fallback result is used.
- The article is marked as suspect whenever the primary extractor falls below the threshold, even if the fallback succeeds.
- If both extractors produce insufficient content, the article status is set to EXTRACTION_FAILED and the run continues with the next article.

*Derived from: extension 8a and 8a.2a.*

---

## US-015

**Skip non-HTML content without aborting the run**

> As Nik, I want articles that point to PDFs, images, or other non-HTML content to be skipped gracefully so that one unsupported bookmark doesn't stop the rest of the document from being compiled.

**Acceptance criteria:**
- If the HTTP response Content-Type is not `text/html` or `application/xhtml+xml`, the article status is set to UNSUPPORTED_CONTENT_TYPE.
- No content is written to the cache for unsupported types.
- The run continues with the next article.

*Derived from: extension 4b.*

---

## US-016

**Enforce a response size limit to avoid oversized pages**

> As Nik, I want the tool to reject HTTP responses that exceed the configured size limit so that a single large page doesn't cause memory or disk issues.

**Acceptance criteria:**
- If the response body exceeds the configured `max_response_size` (default 10 MB), the article status is set to FETCH_FAILED with a note indicating the size limit was exceeded.
- No content is written to the cache for oversized responses.
- The run continues with the next article.

*Derived from: extension 4c.*
