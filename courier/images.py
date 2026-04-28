"""Localise article images: download remote images and rewrite src to local paths."""

from __future__ import annotations

import hashlib
import io
import logging
from pathlib import Path
from urllib.parse import urljoin, urlparse

import requests
from lxml.html import fromstring as html_fromstring
from lxml.html import tostring as html_tostring
from PIL import Image, UnidentifiedImageError

logger = logging.getLogger(__name__)

_VALID_EXTS = {".jpg", ".jpeg", ".png", ".gif", ".webp", ".svg"}
_CONTENT_TYPE_TO_EXT = {
    "image/jpeg": ".jpg",
    "image/png":  ".png",
    "image/gif":  ".gif",
    "image/webp": ".webp",
}
_MIN_DIMENSION = 10


def localise_article_images(content: str, images_dir: Path, article_url: str) -> str:
    """Download remote images in extracted HTML and rewrite src to local relative paths.

    Returns the modified HTML string with src attributes pointing to images/filename.
    """
    tree = html_fromstring(f"<div>{content}</div>")

    for img in tree.xpath(".//img"):
        src = img.get("src", "")
        if not src:
            continue

        absolute_url = urljoin(article_url, src)
        if not absolute_url.startswith(("http://", "https://")):
            continue

        ext          = _ext_from_url(absolute_url)
        filename     = hashlib.sha256(absolute_url.encode()).hexdigest()[:16] + ext
        local_path   = images_dir / filename

        if local_path.exists():
            saved_name = filename
        else:
            saved_name = _download_and_save(img, absolute_url, local_path, images_dir)
            if saved_name is None:
                continue

        img.attrib["src"] = f"images/{saved_name}"
        img.attrib.pop("srcset", None)

    return (tree.text or "") + "".join(
        html_tostring(child, method="xml", encoding="unicode") for child in tree
    )


def _ext_from_url(url: str) -> str:
    """Return the file extension from a URL path, or empty string if not a known image type."""
    suffix = Path(urlparse(url).path).suffix.lower()
    return suffix if suffix in _VALID_EXTS else ""


def _download_and_save(
    img,
    url: str,
    local_path: Path,
    images_dir: Path,
) -> str | None:
    """Download image at url, check dimensions, save to local_path.

    Returns the saved filename, or None on failure (img element already modified).
    """
    try:
        response = requests.get(url, timeout=30)
        response.raise_for_status()
    except Exception as exc:
        logger.warning("Failed to download image %s: %s", url, exc)
        _replace_img_with_text(img, "[Image]")
        return None

    image_bytes = response.content

    if local_path.suffix == "":
        ct  = response.headers.get("Content-Type", "").split(";")[0].strip()
        ext = _CONTENT_TYPE_TO_EXT.get(ct, "")
        local_path = local_path.with_name(local_path.name + ext)

    try:
        pil_img        = Image.open(io.BytesIO(image_bytes))
        width, height  = pil_img.size
    except (UnidentifiedImageError, Exception) as exc:
        logger.warning("Invalid image data for %s: %s", url, exc)
        _replace_img_with_text(img, "[Image]")
        return None

    if width < _MIN_DIMENSION or height < _MIN_DIMENSION:
        logger.debug("Skipping small image %s (%dx%d)", url, width, height)
        _remove_img(img)
        return None

    images_dir.mkdir(parents=True, exist_ok=True)
    local_path.write_bytes(image_bytes)
    return local_path.name


def _replace_img_with_text(img, text: str) -> None:
    """Replace an img element with plain text, preserving the tail."""
    parent = img.getparent()
    if parent is None:
        return
    tail        = img.tail or ""
    replacement = text + tail
    idx         = list(parent).index(img)
    parent.remove(img)
    if idx == 0:
        parent.text = (parent.text or "") + replacement
    else:
        prev       = parent[idx - 1]
        prev.tail  = (prev.tail or "") + replacement


def _remove_img(img) -> None:
    """Remove an img element, preserving the tail as surrounding text."""
    parent = img.getparent()
    if parent is None:
        return
    tail = img.tail or ""
    idx  = list(parent).index(img)
    parent.remove(img)
    if idx == 0:
        parent.text = (parent.text or "") + tail
    else:
        prev      = parent[idx - 1]
        prev.tail = (prev.tail or "") + tail
