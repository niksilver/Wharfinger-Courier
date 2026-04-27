# AZW3 Conversion Design

**Date:** 2026-04-27

## Overview

Add a post-compilation step to the Wharfinger Courier pipeline that converts the
generated XHTML document to AZW3 format using the `ebook-convert` CLI tool
(part of Calibre). The XHTML file is retained after conversion.

## Architecture & Components

A new module `courier/converter.py` exposes one public function:

```python
def convert_to_azw3(xhtml_path: Path, title: str) -> Path:
```

It invokes:

```
ebook-convert <xhtml_path> <azw3_path> --title <title>
```

via `subprocess.run`, where `azw3_path` is `xhtml_path` with a `.azw3` extension.
On success it returns the AZW3 path. On failure it raises a descriptive exception.

The orchestrator (`courier/orchestrator.py`) imports `converter` and calls
`convert_to_azw3()` in `run_pipeline()` immediately after `compile_document()`
returns the XHTML path. The title passed is formatted as:

```
Wharfinger Courier, DD Month YYYY
```

e.g. `"Wharfinger Courier, 30 April 2026"`.

If conversion raises, the orchestrator logs the error and returns `False`
(exit code 1 for the user).

## Error Handling

All failures are fatal. Three cases:

1. **`ebook-convert` not found** — `FileNotFoundError` from `subprocess.run`;
   raise with message `"ebook-convert not found — is Calibre installed and on PATH?"`.

2. **Non-zero exit code** — stdout and stderr are captured; raise with the exit
   code and the full captured output so the user sees exactly what `ebook-convert`
   reported.

3. **Other unexpected errors** — re-raise as-is.

In all cases the orchestrator logs the exception message at `ERROR` level (visible
on stderr), then returns `False`. The XHTML file is always retained.

## Testing

Tests for `converter.py` use `unittest.mock.patch` on `subprocess.run`:

1. **Success** — mock returns exit code 0; assert the returned path has a `.azw3`
   extension and the correct command was passed (including `--title`).

2. **Tool not found** — mock raises `FileNotFoundError`; assert the raised
   exception message mentions Calibre/PATH.

3. **Non-zero exit** — mock returns exit code 1 with captured stdout/stderr;
   assert the raised exception includes that output.

The orchestrator tests get one new case: when `convert_to_azw3` raises,
`run_pipeline` returns `False`.

No integration test against a real `ebook-convert` binary — external tool
dependencies are out of scope for the automated test suite.
