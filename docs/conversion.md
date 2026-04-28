# Successful conversion

The output document needs to have one `<title>` tag which is the document's
title: Wharfinger Courier, DD Month YYYY.

For each fetched article:
- Reduce it just to the contents of its <body> element - excluding the <body>
  tags themselves. This will be the chapter content.
- At the start of the chapter content add `<h1 class="chapter">Document title</h1>`.
- Surround this with `<div class="document">`.

We invoke `ebook-convert` with the following options.
This gives good chapter names, page breaks in the right place, and the TOC
at the start rather than the end:
- `--chapter='//h:h1[@class = "chapter"]'` (identifies each chapter)
- `--page-breaks-before='//h:div[@class = "document"]'` (page breaks only before each chapter)
- `--mobi-toc-at-start` (change default of TOC at end of document(!))

It's nice to add the following options, too:
- `--authors='Various'` (so that it's not 'Uknown')
- `--pubdate='DD Month YYYY'` (just for friendliness)
