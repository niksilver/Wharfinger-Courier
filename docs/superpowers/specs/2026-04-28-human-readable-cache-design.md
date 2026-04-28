# Human-Readable Cache Directory Structure

**Date:** 2026-04-28

## Problem

The current cache stores article HTML under `{cache_dir}/cache/{sha256}/`, where `sha256` is the full 64-character SHA-256 of the article URL. This is opaque — a human browsing the cache cannot identify which directory belongs to which article.

## Goal

When a user knows an article URL, they can locate its cached files by browsing the filesystem without any tooling.

## New Structure

```
{cache_dir}/
├── status.json
└── cache/
    └── {domain}/
        └── {slug}--{hash8}/
            ├── raw.html
            └── extracted.html
```

### Path derivation rules

Given an article URL:

1. **Domain** — `urlparse(url).netloc`, stripping a leading `www.` prefix  
   e.g. `www.bbc.com` → `bbc.com`

2. **Slug** — URL path + query string joined, then:
   - Lowercase
   - Replace runs of non-alphanumeric characters with `_`
   - Strip leading/trailing underscores
   - Truncate to 60 characters

3. **Hash suffix** — first 8 hex characters of the SHA-256 of the full URL, appended with a `--` separator  
   e.g. `science_new-study-finds--a3f9c2d1`

The `--` separator visually distinguishes the slug from the disambiguating hash.

## Implementation

### Changes to `courier/store.py`

- Keep `url_hash(url)` — still used internally and in tests.
- Add `url_cache_path(cache_dir: Path, url: str) -> Path` — returns the article cache directory using the new scheme.
- Update `read_cached_html` and `write_cached_html` to call `url_cache_path` instead of constructing the path inline with `url_hash`.

### No other files change

`status.json` is unchanged. `orchestrator.py` calls `read_cached_html` / `write_cached_html` and is unaffected.

## Migration

Existing SHA-256 directories are orphaned — the pipeline will treat those articles as cache misses and re-fetch them. Old directories can be deleted manually. No automated migration script is needed.

## Testing

- Keep the existing `url_hash` test in `test_smoke.py`.
- Add a test for `url_cache_path` that asserts a known URL produces the expected domain/slug/hash path structure.
