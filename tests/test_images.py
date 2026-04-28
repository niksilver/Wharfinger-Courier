"""Tests for courier.images — image localisation."""

import io
from unittest.mock import MagicMock, patch

from PIL import Image

from courier.images import localise_article_images


def _make_png(width: int, height: int) -> bytes:
    img = Image.new("RGB", (width, height), color=(255, 0, 0))
    buf = io.BytesIO()
    img.save(buf, format="PNG")
    return buf.getvalue()


def _mock_response(content: bytes, content_type: str = "image/png") -> MagicMock:
    r = MagicMock()
    r.content = content
    r.headers = {"Content-Type": content_type}
    r.raise_for_status = MagicMock()
    return r


def test_valid_image_is_downloaded_and_src_rewritten(tmp_path):
    img_bytes = _make_png(100, 100)
    content = '<p><img src="https://example.com/photo.png" alt="x"/></p>'

    with patch("courier.images.requests.get", return_value=_mock_response(img_bytes)):
        result = localise_article_images(content, tmp_path, "https://example.com/article")

    assert 'src="images/' in result
    assert "example.com/photo.png" not in result
    saved = list(tmp_path.iterdir())
    assert len(saved) == 1
    assert saved[0].suffix == ".png"


def test_srcset_is_stripped(tmp_path):
    img_bytes = _make_png(100, 100)
    content = (
        '<img src="https://example.com/photo.png"'
        ' srcset="https://example.com/photo@2x.png 2x"/>'
    )
    with patch("courier.images.requests.get", return_value=_mock_response(img_bytes)):
        result = localise_article_images(content, tmp_path, "https://example.com/article")

    assert "srcset" not in result


def test_relative_src_is_resolved_against_article_url(tmp_path):
    img_bytes = _make_png(100, 100)
    content = '<img src="/images/photo.png"/>'

    with patch("courier.images.requests.get", return_value=_mock_response(img_bytes)) as mock_get:
        localise_article_images(content, tmp_path, "https://example.com/article")

    mock_get.assert_called_once()
    called_url = mock_get.call_args[0][0]
    assert called_url == "https://example.com/images/photo.png"


def test_small_image_is_removed_silently(tmp_path):
    img_bytes = _make_png(5, 5)
    content = '<p>before<img src="https://example.com/tiny.png"/>after</p>'

    with patch("courier.images.requests.get", return_value=_mock_response(img_bytes)):
        result = localise_article_images(content, tmp_path, "https://example.com/article")

    assert "<img" not in result
    assert "[Image]" not in result
    assert "before" in result
    assert "after" in result
    assert not list(tmp_path.iterdir())


def test_failed_download_replaces_img_with_placeholder(tmp_path):
    content = '<p>See <img src="https://example.com/photo.png"/> here.</p>'

    with patch("courier.images.requests.get", side_effect=Exception("timeout")):
        result = localise_article_images(content, tmp_path, "https://example.com/article")

    assert "[Image]" in result
    assert "<img" not in result
    assert "See" in result
    assert "here." in result


def test_invalid_image_data_replaces_img_with_placeholder(tmp_path):
    content = '<p><img src="https://example.com/broken.png"/></p>'

    with patch("courier.images.requests.get", return_value=_mock_response(b"not-an-image")):
        result = localise_article_images(content, tmp_path, "https://example.com/article")

    assert "[Image]" in result
    assert "<img" not in result
    assert not list(tmp_path.iterdir())


def test_cache_hit_skips_download(tmp_path):
    img_bytes = _make_png(100, 100)
    content = '<img src="https://example.com/photo.png"/>'

    with patch("courier.images.requests.get", return_value=_mock_response(img_bytes)) as mock_get:
        localise_article_images(content, tmp_path, "https://example.com/article")
        assert mock_get.call_count == 1

        result = localise_article_images(content, tmp_path, "https://example.com/article")
        assert mock_get.call_count == 1

    assert 'src="images/' in result
