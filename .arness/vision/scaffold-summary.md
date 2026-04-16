# The Wharfinger Courier — Scaffold Summary

## Technology Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| Runtime | Python | 3.12.3 (requires 3.10+) |
| Package management | pip + pyproject.toml | pip 24.0 |
| HTTP client | requests | 2.33.1 |
| Content extraction (primary) | readability-lxml | 0.8.4.1 |
| Content extraction (fallback) | trafilatura | 2.0.0 |
| XHTML templating | Jinja2 | 3.1.6 |
| XHTML sanitisation | lxml | 6.0.4 |
| Config parsing | stdlib tomllib | (Python 3.12 stdlib) |
| CLI | stdlib argparse | (stdlib) |
| Logging | stdlib logging | (stdlib) |
| Test framework | pytest + pytest-cov | 9.0.3 / 7.1.0 |
| Linter/formatter | Ruff | 0.15.10 |

No UI framework, CSS framework, component library, or icon library — this is a pure Python CLI tool.

## Pillar Alignment

| Pillar | Status | How the Stack Serves It |
|--------|--------|------------------------|
| Pinboard as Source of Truth | Supported | `requests` is used read-only (HTTP GET). No write API is called anywhere. Local state is purely downstream of the feed. |
| Readable Output Quality | Supported — validation required | `readability-lxml` + `lxml` sanitisation + Jinja2 XHTML template is the extraction and formatting pipeline. Quality must be validated against real bookmarks (see Known Risks in architecture vision). |
| No Infrastructure Ceremony | Supported | Four pip-installable external packages. No server, no DB, no container. `pip install -e .` and a single config file is the full installation. |
| Resilient and Resumable Runs | Supported | `state.py` stub implements atomic write pattern (`os.replace()`). Per-article exception handling and retry logic are in `orchestrator.py` stub. |

## Key Files

**Configuration:**
- `pyproject.toml` — project metadata, dependencies, Ruff and pytest configuration, `courier` console script entry point
- `requirements.txt` — pip-installable dependency list (without dev extras)
- `config.example.toml` — annotated example config (copy to `~/.config/courier/config.toml` to use)
- `.gitignore` — excludes venv, pycache, pytest cache, ruff cache, `status.json`, `cache/`, `output/`, `config.toml`

**Entry points:**
- `courier/__main__.py` — CLI entry point; `argparse` flags: `--config`, `--since N`, `--dry-run`
- `courier/__init__.py` — package root with version string

**Package modules (stubs):**
- `courier/config.py` — `Config` dataclass; TOML loading via stdlib `tomllib` (3.11+) or `tomli` (3.10)
- `courier/fetcher.py` — `Fetcher` class stub (Pinboard feed + article HTTP fetching)
- `courier/extractor.py` — `Extractor` class stub (readability-lxml primary, trafilatura fallback)
- `courier/compiler.py` — `Compiler` class stub (Jinja2 XHTML rendering)
- `courier/store.py` — `StateManager` class stub (status.json atomic read/write, cache directory)
- `courier/orchestrator.py` — `Orchestrator` class stub (pipeline coordination)

**Templates:**
- `courier/templates/document.xhtml` — Jinja2 XHTML template stub (Kindle-compatible structure placeholder)

**Tests:**
- `tests/test_smoke.py` — 3 smoke tests: package import, `Config` dataclass, `url_hash` helper

## Commands

| Action | Command |
|--------|---------|
| Activate venv | `. .venv/bin/activate` |
| Install (editable + test deps) | `pip install -e ".[test]"` |
| Run | `courier --config ~/.config/courier/config.toml` |
| Dry run | `courier --config ~/.config/courier/config.toml --dry-run` |
| Test | `pytest tests/ -v` |
| Test with coverage | `pytest tests/ --cov=courier` |
| Lint | `ruff check courier/ tests/` |
| Format check | `ruff format --check courier/ tests/` |
| Format (apply) | `ruff format courier/ tests/` |

No dev server or build step — this is a CLI tool installed via pip.

## Build Verification

- **Installation:** pass (`pip install -e ".[test]"` completed without errors)
- **Tests:** pass (3/3 smoke tests passed)
- **Lint:** pass (ruff check clean)
- **Format:** pass (ruff format check clean)
- **CLI:** pass (`courier --help` works via console script entry point)
- **Warnings:** none
- **Issues resolved during scaffolding:** `pyproject.toml` initially had an invalid build-backend value (`setuptools.backends._legacy:_Backend`); corrected to `setuptools.build_meta` (standard PEP 517 backend).
