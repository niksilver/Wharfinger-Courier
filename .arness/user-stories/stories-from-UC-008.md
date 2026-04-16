# User Stories from UC-008: Inspect Run Status

Source: [UC-008 Inspect Run Status](../use-cases/UC-008-inspect-run-status.md)

---

## US-023

**View article counts broken down by processing status**

> As Nik, I want to run `courier status` and see a summary of how many articles are in each state so that I can quickly understand the health of my reading list pipeline without opening any files manually.

**Acceptance criteria:**
- `courier status` reads `status.json` and prints a summary to stdout.
- The summary includes: total articles tracked, count per status (COMPILED, EXTRACTED, CACHED, FETCH_FAILED, EXTRACTION_FAILED, UNSUPPORTED_CONTENT_TYPE, PERMANENTLY_SKIPPED), timestamp of the most recent run, and the output file path from the most recent run (if any).
- No files are written or modified.
- The command does not access Pinboard or the web.

*Derived from: main success scenario; business rule BR-1.*

---

## US-024

**See permanently skipped articles listed individually**

> As Nik, I want PERMANENTLY_SKIPPED articles listed by URL and title in the status output so that I can decide whether to manually remove them from my Pinboard or reset their status.

**Acceptance criteria:**
- If any articles have PERMANENTLY_SKIPPED status, each is listed individually with its URL and title (where available).
- Other statuses are shown as counts only, not individual listings.

*Derived from: main success scenario step 5; business rule BR-2.*

---

## US-025

**Get a clear message when no run history exists**

> As Nik, I want `courier status` to tell me clearly when no runs have been recorded yet so that I don't mistake an empty state for an error.

**Acceptance criteria:**
- If `status.json` does not exist, the tool prints a message indicating no runs have been recorded.
- The command exits with success (not an error).
- No files are written or modified.

*Derived from: extension 2a.*
