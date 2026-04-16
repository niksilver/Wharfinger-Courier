# User Stories from UC-007: Preview Run Without Changes

Source: [UC-007 Preview Run Without Changes](../use-cases/UC-007-preview-run-without-changes.md)

---

## US-020

**Preview which articles would be compiled without making changes**

> As Nik, I want to run `courier --dry-run` and see which articles would be processed so that I can check what's in my queue before committing to a full compile.

**Acceptance criteria:**
- `courier --dry-run` fetches the Pinboard feed and applies the same article selection logic as UC-001 (uncompiled articles, up to the configured cap).
- A preview is printed to stdout listing each article's title and URL.
- No output document is written.
- `status.json` is not modified.
- `courier.log` is not modified.
- No article URLs are fetched (only the Pinboard feed is accessed).

*Derived from: main success scenario; business rules BR-1 through BR-4.*

---

## US-021

**See per-article cache status in the preview**

> As Nik, I want the dry-run preview to tell me which articles are already cached and which would require a fresh fetch so that I can gauge how much network activity a real run would involve.

**Acceptance criteria:**
- The preview distinguishes articles already in CACHED or EXTRACTED state (no fetch needed) from articles that would require a fresh HTTP request.
- The preview includes counts: total articles, cached articles, articles requiring a fresh fetch.
- Articles with prior fetch failures include a status hint (e.g., "(failed 3 times, 7 attempts remaining)").

*Derived from: main success scenario steps 5–6.*

---

## US-022

**Fail safely when the feed is unreachable during a dry run**

> As Nik, I want a dry-run to exit with a clear error message if the Pinboard feed cannot be fetched so that I get useful feedback even when the preview can't complete.

**Acceptance criteria:**
- If the Pinboard feed is unreachable during `--dry-run`, the error is reported to stdout and the command exits with failure.
- No files are written or modified.

*Derived from: extension 3a.*
