# Contributing to The Wharfinger Courier

## Prerequisites

- Python 3.10 or newer (`python3 --version`)
- git
- Optional: [pyenv](https://github.com/pyenv/pyenv) for Python version management

## Setup

```bash
git clone <repo-url>
cd wharfinger-courier
bash scripts/setup.sh
source .venv/bin/activate
```

The setup script creates a virtual environment in `.venv/`, installs the package
in editable mode, and installs test dependencies (pytest, pytest-cov).

## Configuration

Copy `config.example.toml` and fill in your values:

```bash
mkdir -p ~/.config/courier
cp config.example.toml ~/.config/courier/config.toml
```

You will need your RSS feed URL, which may include a secret token.
For Pinboard, get it from <https://pinboard.in/settings/password>.

Set your preferred output directory in the config as well.

The config file lives at `~/.config/courier/config.toml` by default, or pass
`--config <path>` to use a different location. `config.toml` is gitignored --
never commit your feed URL secret.

## Running the tool

```bash
courier --config ~/.config/courier/config.toml             # compile new articles
courier --config ~/.config/courier/config.toml --dry-run   # preview without changes
courier --config ~/.config/courier/config.toml --since 7   # include last 7 days
```

## Development commands

```bash
pytest tests/ -v            # run tests
pytest tests/ --cov=courier # run tests with coverage
ruff check courier/ tests/  # lint
ruff format courier/ tests/ # format
```

## Project structure

```
courier/           # main package
  __main__.py      # CLI entry point
  config.py        # config loading
  fetcher.py       # RSS feed + article HTTP fetching
  extractor.py     # readability-lxml content extraction
  compiler.py      # Jinja2 XHTML compilation
  store.py         # status.json and cache filesystem operations
  orchestrator.py  # pipeline coordination
  templates/       # Jinja2 XHTML templates
tests/             # pytest tests
.arness/           # Arness Spark exploration artifacts (not production code)
config.example.toml  # example config -- copy and fill in your values
```
