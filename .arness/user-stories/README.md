# The Wharfinger Courier — User Stories

## Introduction

User stories derived from the Cockburn fully-dressed use cases in `.arness/use-cases/`. Each story covers either a main success scenario or a significant extension path. Stories use lightweight format: a role/action/benefit statement plus acceptance criteria.

**Format:**
> As Nik, I want [action] so that [benefit].

Stories are grouped one file per source use case. The story IDs are sequential across all files for easy cross-referencing.

## Story Index

| Story ID | Title | Source UC | Priority |
|----------|-------|-----------|----------|
| [US-001](./stories-from-UC-001.md#us-001) | Compile unread bookmarks into a Kindle document | UC-001 | Must-have |
| [US-002](./stories-from-UC-001.md#us-002) | Resume an interrupted run without re-fetching articles | UC-001 | Must-have |
| [US-003](./stories-from-UC-001.md#us-003) | Skip articles that repeatedly fail to fetch | UC-001 | Must-have |
| [US-004](./stories-from-UC-001.md#us-004) | Recover from a corrupted status file | UC-001 | Must-have |
| [US-005](./stories-from-UC-002.md#us-005) | Compile articles from the last N days | UC-002 | Should-have |
| [US-006](./stories-from-UC-002.md#us-006) | Reuse cached content for previously compiled articles | UC-002 | Should-have |
| [US-007](./stories-from-UC-002.md#us-007) | Reject an invalid --since value with a clear error | UC-002 | Should-have |
| [US-008](./stories-from-UC-003.md#us-008) | Set up the tool with a TOML configuration file | UC-003 | Must-have |
| [US-009](./stories-from-UC-003.md#us-009) | See clear errors for invalid or missing configuration | UC-003 | Must-have |
| [US-010](./stories-from-UC-003.md#us-010) | Continue setup despite a temporarily unreachable feed | UC-003 | Must-have |
| [US-011](./stories-from-UC-004.md#us-011) | Fetch and parse the Pinboard bookmark feed | UC-004 | Must-have |
| [US-012](./stories-from-UC-004.md#us-012) | Abort the run cleanly when the feed is unreachable | UC-004 | Must-have |
| [US-013](./stories-from-UC-005.md#us-013) | Fetch and cache article content for extraction | UC-005 | Must-have |
| [US-014](./stories-from-UC-005.md#us-014) | Fall back to a secondary extractor for thin content | UC-005 | Must-have |
| [US-015](./stories-from-UC-005.md#us-015) | Skip non-HTML content without aborting the run | UC-005 | Must-have |
| [US-016](./stories-from-UC-005.md#us-016) | Enforce a response size limit to avoid oversized pages | UC-005 | Must-have |
| [US-017](./stories-from-UC-006.md#us-017) | Assemble articles into a Kindle-compatible XHTML document | UC-006 | Must-have |
| [US-018](./stories-from-UC-006.md#us-018) | Continue compilation when an individual article fails to render | UC-006 | Must-have |
| [US-019](./stories-from-UC-006.md#us-019) | Complete gracefully when no articles are available to compile | UC-006 | Must-have |
| [US-020](./stories-from-UC-007.md#us-020) | Preview which articles would be compiled without making changes | UC-007 | Should-have |
| [US-021](./stories-from-UC-007.md#us-021) | See per-article cache status in the preview | UC-007 | Should-have |
| [US-022](./stories-from-UC-007.md#us-022) | Fail safely when the feed is unreachable during a dry run | UC-007 | Should-have |
| [US-023](./stories-from-UC-008.md#us-023) | View article counts broken down by processing status | UC-008 | Could-have |
| [US-024](./stories-from-UC-008.md#us-024) | See permanently skipped articles listed individually | UC-008 | Could-have |
| [US-025](./stories-from-UC-008.md#us-025) | Get a clear message when no run history exists | UC-008 | Could-have |

## Traceability

| Source UC | Stories |
|-----------|---------|
| UC-001 Compile Current Reading List | US-001, US-002, US-003, US-004 |
| UC-002 Compile Archive Reading List | US-005, US-006, US-007 |
| UC-003 Configure the Tool for First Use | US-008, US-009, US-010 |
| UC-004 Fetch Bookmark Feed | US-011, US-012 |
| UC-005 Fetch and Extract Article | US-013, US-014, US-015, US-016 |
| UC-006 Compile Kindle Document | US-017, US-018, US-019 |
| UC-007 Preview Run Without Changes | US-020, US-021, US-022 |
| UC-008 Inspect Run Status | US-023, US-024, US-025 |
