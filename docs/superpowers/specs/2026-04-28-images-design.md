# Image downloading for fetched articles

Date: 2026-04-28

## Problem

Extracted article HTML contains `<img>` tags with remote `src` URLs. The current
pipeline passes this HTML to ebook-convert as a single XHTML file. ebook-convert
does not reliably download remote images, so the AZW3 output contains no images.

## Approach

Download images as part of the pipeline, after article extraction and before
compilation. Save them to a shared `images/` directory alongside the output XHTML.
Rewrite `<img src>` attributes to relative paths so ebook-convert embeds them
natively into the AZW3.

## Pipeline flow

```
fetch → extract → download images → compile XHTML → convert to AZW3
```

## New module: `courier/images.py`

Single public function:

```python
def download_article_images(content: str, images_dir: Path, article_url: str) -> str
```

Steps:

1. Parse `<img>` tags from the extracted HTML using lxml.
2. For each `src` with an `http`/`https` URL:
   - Compute a filename: `sha256(url)[:16] + ext` (ext from URL path or Content-Type header).
   - If the file already exists in `images_dir`, skip downloading (cache hit).
   - Otherwise download the image bytes via `requests`.
   - Check dimensions using Pillow. If either dimension < 10px, remove the tag silently.
   - On successful download and size check, save to `images_dir / filename`.
   - Rewrite `src` to `images/filename`.
   - Strip the `srcset` attribute (Kindle does not use it).
3. Return the modified HTML string.

Error handling:

| Situation | Behaviour |
|-----------|-----------|
| Download fails | Log warning; replace `<img>` with `[Image]` |
| Image < 10×10px | Log debug; remove `<img>` silently |
| Invalid/unrecognisable image data | Log warning; replace `<img>` with `[Image]` |

## `images_dir` location

`config.output_dir / "images"` — a single directory shared across all articles
and all pipeline runs. Persists between runs, so already-downloaded images are
never re-fetched.

## Changes to existing modules

### `courier/orchestrator.py`

In `_process_article`, after `extractor.extract_article`:

```python
images_dir = config.output_dir / "images"
content = images.download_article_images(content, images_dir, url)
```

### `courier/compiler.py` and template

No changes. The compiler already renders `article.content | safe`; rewritten
`src="images/..."` paths work without modification.

### `courier/compiler.py` — `Article` dataclass

No changes.

## New dependency

**Pillow** — for reading image dimensions. Added to `pyproject.toml` and
`requirements.txt`.

## Testing

- Unit tests for `download_article_images` with mocked HTTP calls:
  - Src rewriting to `images/filename.ext`
  - srcset stripping
  - Size filtering (< 10×10 → removed silently)
  - Failed download → `[Image]` replacement
  - Invalid image data → `[Image]` replacement
  - Cache hit (file already exists → no HTTP call)
- Existing tests are unaffected (no interface changes to compiler, store, or extractor).
