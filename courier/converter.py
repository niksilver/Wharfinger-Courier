"""Convert XHTML documents to AZW3 using ebook-convert (Calibre)."""

from __future__ import annotations

import subprocess
from pathlib import Path


def convert_to_azw3(xhtml_path: Path, title: str) -> Path:
    """Convert xhtml_path to AZW3; return the AZW3 path.

    Raises FileNotFoundError if ebook-convert is not on PATH.
    Raises RuntimeError with captured output if ebook-convert exits non-zero.
    """
    azw3_path = xhtml_path.with_suffix(".azw3")
    try:
        result = subprocess.run(
            [
                "ebook-convert",
                str(xhtml_path),
                str(azw3_path),
                "--title",                title,
                "--chapter=//h:h1[@class = \"chapter\"]",
                "--chapter-mark=pagebreak",
                "--page-breaks-before=//h:div[@class = \"document\"]",
                "--mobi-toc-at-start",
            ],
            capture_output=True,
            text=True,
        )
    except FileNotFoundError:
        raise FileNotFoundError(
            "ebook-convert not found — is Calibre installed and on PATH?"
        )
    if result.returncode != 0:
        output = (result.stdout + "\n" + result.stderr).strip()
        raise RuntimeError(
            f"ebook-convert failed with exit code {result.returncode}:\n{output}"
        )
    return azw3_path
