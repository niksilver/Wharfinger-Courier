# User Stories from UC-002: Compile Archive Reading List

Source: [UC-002 Compile Archive Reading List](../use-cases/UC-002-compile-archive-reading-list.md)

---

## US-005

**Compile articles from the last N days**

> As Nik, I want to run `courier --since N` and have all articles bookmarked within the last N days compiled into a Kindle document — including ones I've already read — so that I can revisit recent reading in a single document.

**Acceptance criteria:**
- `--since N` selects all articles whose bookmark date falls within the last N calendar days, regardless of their COMPILED status.
- Articles are ordered most-recently-bookmarked first.
- The cap (default 30 articles) is applied after date filtering; if articles are excluded by the cap, a message is printed to stdout stating how many were excluded.
- The output file follows the same naming convention as UC-001 (`wharfinger-courier-<YYYY-MM-DD>.xhtml`).
- `status.json` is updated atomically.

*Derived from: main success scenario; business rules BR-1, BR-2, BR-4.*

---

## US-006

**Reuse cached content for previously compiled articles**

> As Nik, I want the archive compile to reuse cached article content where it exists so that articles I've already fetched aren't downloaded again unnecessarily.

**Acceptance criteria:**
- Articles with cached raw or extracted content are not re-fetched or re-extracted during an archive run.
- The cache reuse behaviour mirrors UC-001 BR-3.

*Derived from: extension 6b; business rule BR-3.*

---

## US-007

**Reject an invalid --since value with a clear error**

> As Nik, I want the tool to immediately reject a non-numeric, zero, or negative `--since` value with a clear error message so that I don't accidentally run an archive compile with a nonsensical window.

**Acceptance criteria:**
- If `--since` is given a non-numeric, zero, or negative value, the tool exits with an error message stating that `--since` requires a positive integer.
- No files are read or written when this validation fails.

*Derived from: extension 1a.*
