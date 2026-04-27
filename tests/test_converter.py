"""Tests for courier.converter."""

from __future__ import annotations

from pathlib import Path
from unittest.mock import MagicMock, patch

import pytest

from courier.converter import convert_to_azw3


def test_success_returns_azw3_path(tmp_path):
    xhtml = tmp_path / "doc.xhtml"
    mock_result = MagicMock()
    mock_result.returncode = 0
    with patch("courier.converter.subprocess.run", return_value=mock_result) as mock_run:
        result = convert_to_azw3(xhtml, "Wharfinger Courier, 30 April 2026")
    assert result == xhtml.with_suffix(".azw3")
    cmd = mock_run.call_args[0][0]
    assert cmd[0] == "ebook-convert"
    assert str(xhtml) in cmd
    assert str(xhtml.with_suffix(".azw3")) in cmd
    assert "--title" in cmd
    assert "Wharfinger Courier, 30 April 2026" in cmd


def test_tool_not_found_raises_descriptive_error(tmp_path):
    xhtml = tmp_path / "doc.xhtml"
    with patch("courier.converter.subprocess.run", side_effect=FileNotFoundError):
        with pytest.raises(FileNotFoundError, match="Calibre"):
            convert_to_azw3(xhtml, "Wharfinger Courier, 30 April 2026")


def test_success_passes_chapter_structure_options(tmp_path):
    xhtml = tmp_path / "doc.xhtml"
    mock_result = MagicMock()
    mock_result.returncode = 0
    with patch("courier.converter.subprocess.run", return_value=mock_result) as mock_run:
        convert_to_azw3(xhtml, "Wharfinger Courier, 27 April 2026")
    cmd = mock_run.call_args[0][0]
    assert "--chapter=//h:h1[@class = \"chapter\"]" in cmd
    assert "--chapter-mark=pagebreak" in cmd
    assert "--page-breaks-before=//h:div[@class = \"document\"]" in cmd
    assert "--mobi-toc-at-start" in cmd


def test_nonzero_exit_raises_with_output(tmp_path):
    xhtml = tmp_path / "doc.xhtml"
    mock_result = MagicMock()
    mock_result.returncode = 1
    mock_result.stdout = "some stdout"
    mock_result.stderr = "some error detail"
    with patch("courier.converter.subprocess.run", return_value=mock_result):
        with pytest.raises(RuntimeError) as exc_info:
            convert_to_azw3(xhtml, "Wharfinger Courier, 30 April 2026")
    msg = str(exc_info.value)
    assert "some stdout" in msg
    assert "some error detail" in msg
