# User Stories from UC-004: Fetch Bookmark Feed

Source: [UC-004 Fetch Bookmark Feed](../use-cases/UC-004-fetch-bookmark-feed.md)

---

## US-011

**Fetch and parse the Pinboard bookmark feed**

> As Nik, I want the tool to reliably retrieve and parse my Pinboard bookmark feed so that subsequent pipeline steps have accurate, up-to-date data to work with.

**Acceptance criteria:**
- A single HTTP GET request is made to the configured `pinboard_feed_url`.
- The JSON response is parsed into a list of bookmark records, each with `href`, `description`, `time`, and `toread` fields.
- The parsed list is returned to the orchestrating pipeline step; no filtering or status cross-referencing is done within this step.
- `status.json` is not read or written by this step.

*Derived from: main success scenario.*

---

## US-012

**Abort the run cleanly when the feed is unreachable**

> As Nik, I want the tool to exit with a clear error message when the Pinboard feed cannot be fetched so that I understand why no document was produced and no state is left in an inconsistent condition.

**Acceptance criteria:**
- A network error, connection timeout, HTTP 4xx/5xx response, or malformed JSON response all cause the current run to abort.
- The error message identifies the nature of the failure (network error, HTTP status code, or JSON parse error).
- `status.json` is not updated when the feed fetch fails.
- No output document is written.

*Derived from: extensions 2a, 3a, 4a.*
