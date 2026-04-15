# Expert Review: Round 1 — Flow Reviewer

**Agent:** arn-spark-ux-specialist
**Phase:** Phase 1: Independent Review
**Mode:** Independent
**Use cases reviewed:** 7

---

## Per-Use-Case Feedback

**UC-001: Compile Current Reading List**
- **[Minor]:** Step 7 writes the output file and step 8 updates status.json, but there is no extension covering a crash between steps 7 and 8 (file written, status not updated). On the next run the same articles would be re-compiled into a duplicate document. --> Add an extension (or a note in the minimal guarantee) describing what happens if the process is interrupted between file write and status update, and how a subsequent run recovers (e.g., detect existing output file for today's date).
- **[Minor]:** The output filename uses `<YYYY-MM-DD>`, but there is no extension for running the tool twice on the same day. The second run would overwrite the first output file silently. --> Document the collision behavior explicitly: either overwrite is intentional (state it) or add a sequence suffix.
- **[Minor]:** Extension 2b (corrupted status.json) aborts the run entirely, which is safe but offers no recovery path. For a personal tool, the user's next step is unclear. --> Add guidance in the extension: suggest Nik delete or rename the corrupted status.json and re-run (treating all articles as new, per extension 2a).
- **[Minor]:** The run summary (step 10) lists counts but does not mention suspect articles by name/URL. For the user's primary success measure ("did I get a readable document with good articles?"), knowing which articles are suspect is more useful than just a count. --> Consider listing suspect article titles in the stdout summary, or at minimum referencing the log for details.

**UC-002: Compile Archive Reading List**
- **[Critical]:** Step 4 applies the 30-article cap after the date filter, but there is no specification of ordering when more than 30 articles fall within the window. The user has no way to predict which articles are included and which are silently dropped. --> Specify the sort order (e.g., most recently bookmarked first) and print a message when the cap is reached, stating how many articles were excluded.
- **[Minor]:** The `--since N` flag accepts only a number of days. There is no extension for invalid input (non-numeric, zero, negative). --> Add an extension for invalid N values with a clear error message.
- **[Minor]:** There is no way to combine `--since` with `--dry-run`. UC-007 only mirrors UC-001 selection logic. --> Either document that `courier --dry-run --since N` is supported (and update UC-007 accordingly) or state explicitly that dry-run is not available for archive mode. The omission is a gap in the user's ability to preview archive runs.

**UC-003: Configure the Tool for First Use**
- **[Critical]:** The trigger says "Nik runs the tool for the first time (or any time no status.json exists)," but the main scenario has Nik manually creating a TOML file before invoking the tool. These are two different mental models: one is a first-run detection flow, the other is a manual setup flow. The trigger implies the system initiates validation when it detects missing state, but the steps describe Nik acting before invoking the tool. --> Clarify the trigger. The natural reading is: configuration validation runs on every invocation, not just first use. Consider renaming to "Validate Configuration" and noting it is executed as a preamble to every run, which removes the confusing "first use" framing.
- **[Minor]:** Step 7 performs a test HTTP GET to the feed URL on every first-use configuration. This is a network call that could slow down the workflow or fail transiently even though the config is correct. --> Make the connectivity check optional or note that it only runs during explicit validation, not on every tool invocation. Otherwise, a transient Pinboard outage blocks all runs.
- **[Minor]:** There is no extension for a syntactically invalid TOML file (e.g., mismatched quotes, bad encoding). Extension 5a only covers missing fields. --> Add an extension for TOML parse errors with a message pointing to the problematic line.

**UC-004: Fetch Bookmark Feed**
- **[Minor]:** The use case makes exactly one HTTP request with no retry, and any failure is fatal to the entire run. For a personal CLI tool running on a home network, transient failures (DNS blip, brief timeout) are common. --> Consider a single automatic retry with a brief delay before declaring a fatal failure, or document the rationale for no retry so the design decision is explicit.
- **[Minor]:** Step 5 reads status.json to partition bookmarks into new vs. known. This couples UC-004 to state management, which is the orchestrator's responsibility per the business rules ("status.json is read but never written by this use case"). The partitioning logic feels like it belongs in the orchestrator (UC-001/UC-002), not in the feed-fetching subfunction. --> Consider having UC-004 return only the parsed feed data and letting the orchestrator handle the partitioning. This simplifies UC-004 and avoids the need for UC-004 to know about status.json.

**UC-005: Fetch and Extract Article**
- **[Minor]:** Extension 8a sets the suspect flag when the primary extractor produces low word count, even if the fallback succeeds. The suspect flag's downstream effect is unclear from this use case alone. The user sees a "suspect" count in the run summary (UC-001 step 10) but has no way to act on it. --> Define what a user should do with suspect articles. Options: inspect them in the output document; re-run with a flag to skip them; review them in the log. Without guidance, the flag is noise.
- **[Minor]:** There is no timeout or size limit specified for the HTTP fetch (step 4 says "using the configured timeout" but no extension covers an excessively large response, e.g., a URL that points to a multi-gigabyte file). --> Add an extension or business rule for maximum response size to protect against pathological URLs.
- **[Minor]:** The XHTML sanitisation step (step 9) has no failure extension. Malformed HTML that cannot be sanitised to valid XHTML would silently produce broken output or crash. --> Add an extension for sanitisation failure (treat as EXTRACTION_FAILED).

**UC-006: Compile Kindle Document**
- **[Minor]:** Extension 1a (archive mode) says "the system also includes articles with status COMPILED that fall within the archive date range," but how the archive date range reaches UC-006 is not specified. UC-006's trigger and preconditions do not mention receiving a mode or date range parameter from the orchestrator. --> Add the mode/date-range to the trigger or preconditions so the interface between orchestrator and UC-006 is clear.
- **[Minor]:** There is no extension for template rendering failure (e.g., a malformed article body that breaks the XHTML template). --> Add an extension: if a single article's content causes a rendering error, skip that article and log a warning, rather than failing the entire compilation.
- **[Minor]:** The business rule states "articles appear in the document in the same order they were collected from the feed," but there is no guarantee about feed ordering from Pinboard's side. If feed order changes between runs, the document's article sequence changes unpredictably. --> State whether feed order is relied upon or whether the tool applies its own sort (e.g., by bookmark date).

**UC-007: Preview Run Without Changes**
- **[Minor]:** The preview output (step 6) lists titles and URLs but does not show article status from previous runs (e.g., "failed 3 times previously, 7 attempts remaining"). This context would help Nik decide whether to run or investigate a problematic URL first. --> Include per-article status hints in the preview for articles that have prior fetch failures.
- **[Minor]:** As noted under UC-002, dry-run does not support archive mode (`--since N`). This means the user cannot preview an archive compilation. --> Either support `--dry-run --since N` or document the limitation explicitly.

## Cross-Cutting Observations

- **Duplicate output filename collision.** UC-001, UC-002, and UC-006 all reference the output filename `wharfinger-courier-<YYYY-MM-DD>.xhtml` but none address what happens on a second run in the same day. This applies to both normal and archive mode runs, and to mixed-mode runs (a normal run followed by an archive run on the same day). The behavior should be specified once and referenced consistently.
- **Suspect articles lack a complete user journey.** The suspect flag is set in UC-005, counted in UC-001's summary, and presumably appears in the output document, but there is no use case or extension that describes how Nik reviews, acknowledges, or acts on suspect articles. For the user's primary success measure ("did I get a readable document with good articles?"), this is a meaningful gap.
- **Dry-run / archive mode interaction is unspecified.** UC-007 only mirrors UC-001 selection logic. The absence of a dry-run for UC-002 means the user has no low-risk way to preview archive compilations. This should be an explicit design decision, not an accidental omission.
- **Step granularity is consistent** across all seven use cases. User-goal use cases (UC-001, UC-002, UC-003, UC-007) describe steps at the right level for an actor-perspective narrative. Subfunction use cases (UC-004, UC-005, UC-006) go into appropriate implementation detail for system-level steps. No use cases need splitting or merging.
- **Error recovery guidance is minimal.** Extensions describe what the system does on failure (print error, abort run) but rarely describe what the user should do next. For a personal CLI tool, a sentence of guidance in key failure extensions (corrupted status.json, persistent feed failures, permission errors) would improve the user's recovery experience.
- **No Ctrl-C / SIGINT handling described.** For a CLI tool that makes multiple network requests sequentially, graceful interruption is a realistic scenario. None of the use cases describe what happens if Nik presses Ctrl-C mid-run. The key concern is whether status.json is left in a consistent state. This could be a single cross-cutting note rather than per-use-case extensions.
- **Logging scope could be tighter.** UC-001 and UC-002 append to courier.log, and UC-007 explicitly does not. But UC-003 (configuration validation) does not state whether it logs. If a user hits repeated config errors, the log might be useful for diagnosis. Specify logging behavior for UC-003.
