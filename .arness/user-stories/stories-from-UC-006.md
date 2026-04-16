# User Stories from UC-006: Compile Kindle Document

Source: [UC-006 Compile Kindle Document](../use-cases/UC-006-compile-kindle-document.md)

---

## US-017

**Assemble articles into a Kindle-compatible XHTML document**

> As Nik, I want all successfully extracted articles assembled into a single, well-structured XHTML file so that I can send it to my Kindle and read it as a coherent document.

**Acceptance criteria:**
- The output document includes an XML declaration, DOCTYPE, Kindle-compatible head metadata, a table of contents with internal links to each article, and each article's title and body in sequence.
- The output filename is `wharfinger-courier-<YYYY-MM-DD>.xhtml`.
- Each included article's status is updated to COMPILED with its `compiled-in` field set to the output filename.
- The document is valid XHTML.
- Articles appear in the order supplied by the orchestrator (feed order for UC-001; most-recently-bookmarked-first for UC-002).

*Derived from: main success scenario.*

---

## US-018

**Continue compilation when an individual article fails to render**

> As Nik, I want a single article that causes a rendering error to be skipped rather than aborting the whole document so that one bad bookmark doesn't cost me the rest of my reading list.

**Acceptance criteria:**
- If an article's content causes a template rendering error, a warning is logged identifying the article title and URL.
- The article is skipped and its status is set to EXTRACTION_FAILED with a note indicating rendering failure.
- Compilation continues with the remaining articles.
- The output document is produced even if one or more articles were skipped.

*Derived from: extension 3a.*

---

## US-019

**Complete gracefully when no articles are available to compile**

> As Nik, I want the tool to finish cleanly and report zero compiled articles when nothing is available, rather than producing an empty or invalid document.

**Acceptance criteria:**
- If no articles have EXTRACTED status at compilation time, no output file is written.
- The orchestrator receives a summary indicating zero compiled articles.
- The run completes with success (not an error).

*Derived from: extension 2a.*
