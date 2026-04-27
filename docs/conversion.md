# Successful conversion

The output document needs to have one `<title>` tag which is the document's
title: Wharfinger Courier, DD Month YYYY.

Each fetched page needs its `<body>` tag replaced with a `<div class="document">`
and this needs to be inserted as the first thing in the div:
`<h1 class="chapter">Document title</h1>`.

We invoke `ebook-convert` with the following options.
This gives good chapter names, page breaks in the right place, and the TOC
at the start rather than the end:
- `--chapter='//h:h1[@class = "chapter"]'`
- `--chapter-mark=pagebreak`
- `--page-breaks-before='//h:div[@class = "document"]'`
- `--mobi-toc-at-start`
