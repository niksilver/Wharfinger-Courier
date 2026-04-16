#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

cd "$PROJECT_ROOT"

# --- Check Python version ---

if ! command -v python3 &>/dev/null; then
    echo "Error: python3 not found. Please install Python 3.10 or newer."
    exit 1
fi

PYTHON_VERSION="$(python3 -c 'import sys; print(f"{sys.version_info.major}.{sys.version_info.minor}")')"
PYTHON_MAJOR="$(python3 -c 'import sys; print(sys.version_info.major)')"
PYTHON_MINOR="$(python3 -c 'import sys; print(sys.version_info.minor)')"

if [ "$PYTHON_MAJOR" -lt 3 ] || { [ "$PYTHON_MAJOR" -eq 3 ] && [ "$PYTHON_MINOR" -lt 10 ]; }; then
    echo "Error: Python 3.10+ is required, but found Python $PYTHON_VERSION."
    echo "Please install Python 3.10 or newer."
    exit 1
fi

echo "Found Python $PYTHON_VERSION"

# --- Create virtual environment if it doesn't exist ---

if [ -d ".venv" ]; then
    echo "Virtual environment .venv/ already exists, skipping creation."
else
    echo "Creating virtual environment in .venv/ ..."
    python3 -m venv .venv
fi

# --- Install the package in editable mode with test extras ---

echo "Installing package in editable mode with test dependencies ..."
.venv/bin/pip install --upgrade pip --quiet
.venv/bin/pip install -e ".[test]" --quiet

echo ""
echo "Setup complete."
echo "Run '. .venv/bin/activate' to activate the venv, then 'courier --help' to verify the install."
