# The Wharfinger Courier — Product Concept

## Vision

The Wharfinger Courier is a personal command-line tool that bridges Nik's existing Pinboard bookmarking workflow and his Kindle reading habit. It periodically compiles articles tagged `toread` on Pinboard into a clean, well-formatted Kindle document — *The Wharfinger Courier* — ready to transfer and read offline. Unlike general-purpose read-it-later services, it is a single-user pipeline with no account management, no separate bookmarking system, and no infrastructure beyond the local filesystem.

## Problem Statement

**The problem:** Articles bookmarked on Pinboard for later reading pile up unread because the browser context (desktop or phone) doesn't match the preferred reading context (Kindle, offline, distraction-free).

**Who experiences it:** Nik — a hobby coder who actively uses Pinboard as his bookmarking system and reads long-form articles on a Kindle.

**Current workarounds:** Articles sit unread in Pinboard indefinitely. Existing solutions (Pocket, Instapaper, Send-to-Kindle extension) require either switching bookmarking systems or manual one-by-one article processing.

**Why existing solutions fall short:** Nothing connects Pinboard directly to Kindle with batch processing, clean content extraction, and resumable state tracking. The Send-to-Kindle browser extension is per-article and manual. Calibre can be scripted but is heavyweight. Pocket and Instapaper are separate bookmarking systems — they would duplicate rather than extend the existing Pinboard workflow.

**Severity:** Vitamin — the problem doesn't cause acute pain, but the backlog slowly grows and the reading habit doesn't form without a frictionless pipeline.

## Target Personas

This is a single-user personal utility. There is one user: the author. No persona variation is relevant — the product is done when it works for Nik's workflow.

### Archetype: The Hobby Coder Bibliophile

#### Abstracted Profile (Mould)

**Demographic range:** 30s–60s, technically experienced but not professionally employed as a developer, comfortable with the command line, active in a personal bookmarking/reading workflow.

**Personality spectrum:** Pragmatic ↔ craft-oriented, low ceremony ↔ high quality bar for output, self-directed ↔ externally curious (bookmarks many things, reads selectively).

**Core motivation pattern:** Wants to close the loop between "saving for later" and "actually reading later" — the backlog is a source of mild guilt and lost value.

**Pain pattern:** Context mismatch: the tool used for capture (browser) is not the tool used for deep reading (e-reader). No automated bridge exists for their specific combination.

**Adoption pattern:** Builds the tool themselves when the friction of alternatives (switching bookmark systems, manual per-article workflows) exceeds the effort of building a custom pipeline.

**Variation axes:**
- Bookmarking volume: light (5–10/week) → heavy (30+/week)
- Kindle usage: occasional → daily reader
- Tolerance for imperfect extraction: high (skips gracefully) → low (wants every article perfect)

**Boundary conditions:** Not someone who uses Pocket or Instapaper as their primary bookmark system. Not someone who reads primarily on a phone or tablet. Not a commercial product builder.

#### Concrete Example: Nik

**Demographics:** Hobby coder, experienced developer (builds independently), uses Python and JavaScript primarily, runs scripts locally on a personal machine.

**Personality Traits:** Pragmatic, craft-conscious (cares that the output looks right), low tolerance for unnecessary infrastructure, values resilience over perfection.

**Goals:**
- Primary: Have a ready-made Kindle document of interesting articles waiting for him when he picks up his Kindle
- Secondary: Keep the Pinboard workflow unchanged — Pinboard is the source of truth, the courier is downstream

**Pain Points:**
- Pinboard backlog grows; reading context never matches bookmarking context
- Existing solutions would require switching bookmarking systems or doing per-article manual work
- No tool handles the Pinboard → Kindle pipeline end-to-end

**Current Workarounds:** Bookmarks sit in Pinboard. Occasionally reads on laptop browser. Backlog grows.

**Decision Factors:** Tool must fit into existing Pinboard workflow without changes, must produce output that actually looks good on a Kindle, must be resilient enough to run unattended without constant debugging.

**Day-in-the-Life:** Nik picks up his Kindle on a Sunday evening, finds nothing new to read. He has 40 unread articles in Pinboard but no easy way to get them onto the device. He runs `courier`, gets a compiled document, transfers it, and has a reading session.

**Adoption Trigger:** The backlog crosses a threshold where it feels wasteful not to read it — and building a pipeline is more satisfying than switching to Pocket.

**Frustration Threshold:** If the tool requires debugging on most runs, or produces documents that look broken on the Kindle, it gets abandoned.

### Differentiation Summary

| Dimension | Nik |
|-----------|-----|
| Primary motivation | Close the Pinboard → Kindle gap |
| Technical sophistication | Experienced coder, comfortable with CLI |
| Adoption posture | Builds his own solution |
| Pain severity | Medium (growing backlog, occasional frustration) |
| Decision style | Pragmatic — must work, must look right, no ceremony |

## Product Pillars

### Pinboard as Source of Truth

The Wharfinger Courier is downstream of Pinboard, not parallel to it. Pinboard is never written to, never modified, never duplicated. The courier reads the feed and stops there. Any workflow change that requires modifying Pinboard — removing tags, updating bookmarks, adding metadata — compromises this pillar and should be rejected.

**Litmus test:** Does this feature or design require writing to Pinboard? If yes, it violates this pillar. Defer or redesign.

### Readable Output Quality

The document produced is named *The Wharfinger Courier* — it has character, not just content. Articles must be extracted cleanly (navbars, ads, and boilerplate stripped), and the XHTML must be formatted to the Kindle standard. A document that technically works but looks rough or broken on the device defeats the purpose.

**Litmus test:** Would Nik pick up his Kindle, open this document, and read it comfortably? If the answer involves "well, most articles are fine" with significant exceptions, the extraction or formatting needs work.

### No Infrastructure Ceremony

This is a personal script, not a service. No database, no web server, no persistent process, no Docker container. State lives in the filesystem. Installation should be runnable in minutes. Adding infrastructure should require a clear and compelling justification — the default answer to "should we add a DB?" is no.

**Litmus test:** Can Nik run this on a freshly set up machine with just a dependency install and a single command? If not, the complexity has grown too high.

### Resilient and Resumable Runs

The courier operates in an unreliable environment: HTTP fetches fail, pages time out, content is sometimes malformed. Every run writes a status file capturing what happened. The next run reads that file and continues intelligently — retrying failures up to a limit, skipping already-compiled articles. A failed fetch should never crash a run or leave the pipeline in an unrecoverable state.

**Litmus test:** If Nik's internet drops halfway through a run, does the next run pick up cleanly without manual intervention? If not, resilience needs work.

## Competitive Landscape

This is a personal utility. The framing below uses "Existing Tools" rather than "Competitors" — these are alternatives Nik evaluated before deciding to build his own pipeline.

### Primary Existing Tools (Reference)

1. **Pocket** (https://getpocket.com) — Read-it-later service with Kindle delivery integration; requires switching from Pinboard as the primary bookmarking tool.
   **Why primary:** The most direct functional equivalent — saves articles and delivers to Kindle. The dealbreaker is that it would replace Pinboard rather than extend it.
   **Confidence:** Verified

2. **Instapaper** (https://www.instapaper.com) — Read-it-later service with Kindle delivery via scheduled email; same limitation as Pocket.
   **Why primary:** Offers Kindle delivery natively including periodic compilation. Again requires a separate bookmarking system.
   **Confidence:** Verified

3. **Calibre** (https://calibre-ebook.com) — E-book management tool with a "News download" feature that can be scripted with custom recipes to fetch web content and compile to Kindle formats.
   **Why primary:** Could theoretically be scripted to achieve the same result. Heavyweight application dependency; the recipe system is complex; output format is EPUB/MOBI rather than XHTML.
   **Confidence:** Verified

4. **Amazon Send-to-Kindle** (https://www.amazon.com/sendtokindle) — Browser extension and email service to send individual articles or documents to a Kindle device.
   **Why primary:** Already in Nik's toolchain. Per-article only — no batch compilation, no TOC, no Pinboard integration.
   **Confidence:** Verified

### Extended Landscape

- **Wallabag** (https://wallabag.org) — Self-hosted read-it-later with e-reader export; requires running a server. **Confidence: Verified**
- **Reabble** (https://reabble.com) — RSS-to-Kindle service; not bookmark-based. **Confidence: Inferred**

### Indirect Alternatives

- **Manual / "Do Nothing"** — Articles remain in Pinboard indefinitely; read occasionally in a browser when remembered.
- **Saved web pages** — Save individual pages as HTML or PDF manually, transfer to Kindle via USB. Tedious at scale.
- **Readwise Reader** — Integrates Pinboard via RSS; proprietary platform; monthly subscription.

**Initial positioning:** The Wharfinger Courier fills the gap between Pinboard (source of truth, unchanged) and Kindle (reading device) that none of the existing tools address without requiring a system change. It is a purpose-built glue script for one specific workflow.

### Research Metadata
- **Research date:** 2026-04-15
- **Search coverage:** Conversation-based identification; no formal web research conducted
- **Raw candidates found:** 6
- **Validated alternatives:** 4 primary, 2 extended
- **Research mode:** Identification only (landscape mapping — deep analysis available via dedicated skills)

## Core Experience

### The Compile Run

The primary interaction is a single command: `courier`. Nik invokes it from the terminal when he wants fresh reading material. The script reads the last status file to determine what has already been fetched and compiled, fetches any new `toread` bookmarks from Pinboard not yet in the local cache (up to 30), extracts clean article content from each URL, and compiles everything into a single XHTML document — *The Wharfinger Courier* — with a table of contents listing all included articles. The document is written to a configured output directory. A new status file is written capturing everything that happened. Progress is appended to a log file throughout. Nik then transfers the document to his Kindle manually (USB or Send-to-Kindle).

### The Archive Run

When Nik wants to re-read or catch up on older articles, he invokes `courier --since N` (where N is a number of days). This overrides the default behaviour and includes previously compiled articles from the last N days alongside any new ones, up to the 30-article cap. Articles are still capped and ordered by recency. This is the mechanism for "I want this weekend's reading to include some articles from last week I didn't get to."

### First-Time Setup

No onboarding wizard. Nik configures a settings file (or environment variables) with the Pinboard feed URL, the output directory for compiled documents, and the cache/state directory. Runs immediately.

## Trust & Security Model

### First-Time Setup

No authentication is required. The Pinboard `toread` feed is a public URL. The only configuration is the feed URL and local filesystem paths.

### Network Trust

All HTTP requests are outbound-only (Pinboard feed + article fetches). No inbound connections. HTTPS is used where the target site supports it. No credentials are stored or transmitted.

### Local Filesystem Only

All state (cache, status files, log, compiled documents) is stored in local directories configured by Nik. No cloud sync, no remote state, no external service dependencies beyond Pinboard and the article sites themselves.

## Filesystem & State Management

The courier maintains a local cache directory with one subdirectory per fetched article (keyed by URL hash or slug), storing the raw fetched content and extraction metadata. A status file is written at the end of each run in JSON or similar structured format, capturing: run timestamps (start, stop, duration), per-article fetch outcomes (success, failure, retry count), and details of any compiled document (title, article count, output path). The most recent status file is the resumption point for the next run. A separate append-only log file records major actions (run started, article fetched, document compiled, run complete) for progress reassurance during long runs.

Failed fetches are retried on subsequent runs. Each bookmark tracks a cumulative failed-fetch count; once that count exceeds the configured maximum (default: 10), the bookmark is skipped permanently and noted in the status file.

## Target Platforms

- Any platform where the chosen scripting language runtime is available (Linux, macOS, Windows with WSL)
- Command-line invocation only — no GUI
- Kindle device: any generation supporting XHTML document transfer

## Participants

Single user, single machine. No networking between participants, no multi-user topology, no concurrency concerns. One instance of `courier` runs at a time.

## Business Constraints

**Business model:** Personal utility. No revenue model. Free to use.

**Tenancy:** Not applicable — single user.

**Compliance:** None. No personal data beyond Nik's own browsing history (his own Pinboard feed, public). No regulatory requirements.

**Cost constraints:** Zero infrastructure cost target. No cloud services, no paid APIs. Dependency on free-tier or open-source libraries only.

**Vendor & technology constraints:** Pinboard feed is the only external dependency. Language choice is flexible — Python is the natural fit given Nik's stack and the availability of content extraction libraries.

**Timeline:** No hard deadline. Personal project, ship when working.

## Key Assumptions

| Assumption | Confidence | What Would Disprove It |
|-----------|-----------|----------------------|
| The Pinboard `toread` feed is reliably accessible as a public URL | High | Feed returns 4xx/5xx consistently or requires authentication |
| Most bookmarked articles are machine-readable HTML (not JS-only rendered content) | Medium | >30% of bookmarked articles fail extraction with a readability-style library |
| A readability-style content extraction library will strip navbars/ads adequately for most articles | Medium | Extracted content is consistently cluttered or incomplete on common sites |
| The Kindle XHTML formatting standard from the referenced blog post is accurate and current | Medium | Compiled documents fail to open or render incorrectly on Kindle hardware |
| Manual Kindle transfer (USB or Send-to-Kindle) is acceptable friction for regular use | High | Nik stops using the tool because the transfer step is too inconvenient |
| A 30-article cap per document produces a manageable reading session | Medium | Documents feel too long (fatigue) or too short (not worth the transfer) |
| Filesystem-only state (no DB) is sufficient for the required state tracking | High | State management becomes complex enough to warrant a lightweight DB |

## Success Criteria

| Metric | Target | Timeframe | Why It Matters |
|--------|--------|-----------|----------------|
| Script runs without manual debugging intervention | 9 out of 10 runs | After first month of use | Resilience is a pillar — if it needs babysitting, it won't be used |
| Compiled documents render correctly on Kindle | All articles in a document readable without formatting issues | From first release | Output quality is a pillar — broken rendering kills the habit |
| Pinboard backlog actually gets read | Nik reads at least one compiled Courier document per week | After first month | The tool exists to close the read-it-later loop |
| Failed fetch count stays manageable | <20% of bookmarks permanently skipped (max retries hit) | Ongoing | Indicates extraction approach is viable for Nik's link diet |

## Future Considerations (Not in v1)

- **Image support in compiled documents** — Embedding images adds significant complexity (base64 encoding or file bundles). Text-only output is acceptable for v1; most long-form articles read well without images. Revisit if charts or visual content are common in Nik's bookmarks.
- **Scheduled / automated runs** — Cron or system service to run `courier` automatically. Deferred until the pipeline is stable enough to run unattended reliably.
- **Automated Kindle delivery** — Email delivery via Amazon's Send-to-Kindle email endpoint, or USB auto-detection. Manual transfer is acceptable for v1; automation is a convenience improvement once the core pipeline works.
- **Headless browser support** — For JS-rendered pages that fail content extraction. Adds a heavyweight dependency (Playwright/Puppeteer). Deferred — v1 assumes pages are directly accessible HTML.
- **Calibre integration** — For MOBI or EPUB output in addition to XHTML. Deferred — XHTML is the target format per the Kindle formatting standard Nik referenced.
- **Article preview / selection before compilation** — A `courier list` command to show pending articles before committing to a compile run. Useful if the backlog is large and Nik wants to curate the document. Deferred — simple enough to add later.
