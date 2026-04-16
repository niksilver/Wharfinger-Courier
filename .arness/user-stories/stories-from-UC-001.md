# User Stories from UC-001: Compile Current Reading List

Source: [UC-001 Compile Current Reading List](../use-cases/UC-001-compile-current-reading-list.md)

---

## US-001

**Compile unread bookmarks into a Kindle document**

> As Nik, I want to run `courier` and have my unread Pinboard bookmarks fetched, extracted, and compiled into a single XHTML document so that I can transfer it to my Kindle and read offline.

**Acceptance criteria:**
- Running `courier` with no flags fetches the Pinboard `toread` feed and processes unread articles.
- Articles already marked COMPILED in `status.json` are skipped.
- The output file is named `wharfinger-courier-<YYYY-MM-DD>.xhtml` and written to the configured output directory.
- `status.json` is updated atomically with the per-article outcomes (COMPILED, FETCH_FAILED, PERMANENTLY_SKIPPED) and run metadata.
- A run summary is printed to stdout: articles fetched, compiled, failed, and the output file path.
- Suspect articles (low word count) are called out individually in the summary.
- The run is capped at the configured maximum (default 30 articles).

*Derived from: main success scenario.*

---

## US-002

**Resume an interrupted run without re-fetching articles**

> As Nik, I want articles that were already fetched or extracted in a previous interrupted run to be reused on the next run so that I don't burn unnecessary network requests or lose progress.

**Acceptance criteria:**
- Articles in CACHED state are not re-fetched; their raw HTML is read from the cache.
- Articles in EXTRACTED state are not re-fetched or re-extracted; their extracted content is read from the cache.
- The resumed run produces the same output document as if it had completed uninterrupted.
- `status.json` is never left in a partially-written state after an interruption (atomic writes).

*Derived from: extensions 5b and 7b; business rules BR-3, BR-5, BR-7.*

---

## US-003

**Skip articles that repeatedly fail to fetch**

> As Nik, I want articles that have failed to fetch 10 or more times across runs to be permanently skipped so that they don't block future runs or accumulate in a retry loop.

**Acceptance criteria:**
- Each failed fetch attempt increments the article's `fetch_attempts` counter in `status.json`.
- Once the counter reaches the configured maximum (default 10), the article's status is set to PERMANENTLY_SKIPPED.
- PERMANENTLY_SKIPPED articles are not retried on subsequent runs.
- The run summary identifies any newly PERMANENTLY_SKIPPED articles.

*Derived from: extensions 5a, 5c; business rule BR-4.*

---

## US-004

**Recover from a corrupted status file**

> As Nik, I want a clear error message and recovery instructions when `status.json` is corrupted so that I know exactly what to do to get the tool working again.

**Acceptance criteria:**
- If `status.json` exists but cannot be parsed, the tool exits with an error message identifying the file and the nature of the failure.
- The error message includes recovery guidance: delete or rename the corrupted file to treat all articles as new on the next run.
- No output document is written and no files are modified when this error occurs.

*Derived from: extension 2b.*
