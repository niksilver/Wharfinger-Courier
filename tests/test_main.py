"""Tests for courier.__main__ CLI entry point."""

from __future__ import annotations

from courier.__main__ import main


def test_since_rejects_zero(capsys):
    """--since 0 exits with error before loading any config."""
    result = main(["--since", "0"])
    assert result != 0
    assert "positive" in capsys.readouterr().err.lower()


def test_since_rejects_negative(capsys):
    """--since -1 exits with error before loading any config."""
    result = main(["--since", "-1"])
    assert result != 0
    assert "positive" in capsys.readouterr().err.lower()
