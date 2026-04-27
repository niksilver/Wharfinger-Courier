# Architecture

## Technology Stack

| Layer | Choice | Rationale |
|-------|--------|-----------|
| Language | Python 3.10+ | Modern Python with union type syntax; broad library support for web scraping and content extraction |
| Framework | None (stdlib + libraries) | Lightweight CLI pipeline; no web framework needed |
| Package manager | pip (setuptools) | Standard Python packaging with pyproject.toml |
| Build system | setuptools >= 68 | Simple pure-Python package; no compiled extensions |
| Linter | Ruff | Fast Python linter configured for pycodestyle, pyflakes, and import sorting |
| Testing | pytest + pytest-cov | Standard Python test runner with coverage support |
| Templating | Jinja2 >= 3 | XHTML document generation from article data |

## Key Architectural Decisions

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Project layout | Flat package (`courier/`) | Small single-package project; no need for src layout |
| Content extraction | Dual extractor (readability-lxml + trafilatura) | Readability as primary with trafilatura fallback for robustness |
| Configuration format | TOML | Python 3.11 has built-in tomllib; tomli backport for 3.10 |
| Output format | AZW3 | Kindle-compatible format which can be generated from XHTML |
| Caching strategy | Filesystem-based with SHA-256 URL hashing | Simple, no database dependency; deterministic cache paths |
| Pipeline architecture | Orchestrator pattern | Single `run_pipeline` function coordinates fetch, extract, and compile stages |

## Dependencies

### External
- **requests >= 2.33** — HTTP fetching for RSS feed and article URLs
- **readability-lxml >= 0.8** — Primary article content extraction from HTML
- **trafilatura >= 2.0** — Fallback article content extraction
- **jinja2 >= 3** — Templating engine for XHTML document compilation
- **tomli >= 2.0** — TOML config parsing (Python < 3.11 only)
- **pytest** — Test runner (test dependency)
- **pytest-cov** — Coverage reporting (test dependency)
- **ebook-convert** - Command line conversion from XHTML to AZW3

### Internal
- **courier.config** — TOML configuration loading into a Config dataclass
- **courier.fetcher** — HTTP fetching for feed and article URLs
- **courier.extractor** — HTML content extraction with dual-engine approach
- **courier.compiler** — Jinja2-based XHTML document compilation
- **courier.store** — Filesystem cache and status management
- **courier.orchestrator** — Pipeline coordination across stages

## Project Layout

```
wharfinger-courier/
├── courier/                  # Main package
│   ├── __init__.py           # Package metadata and version
│   ├── __main__.py           # CLI entry point
│   ├── config.py             # TOML config loading
│   ├── fetcher.py            # HTTP fetching
│   ├── extractor.py          # Content extraction
│   ├── compiler.py           # XHTML document compilation
│   ├── orchestrator.py       # Pipeline coordinator
│   ├── store.py              # Filesystem cache/status
│   └── templates/
│       └── document.xhtml    # Jinja2 template for output
├── tests/                    # All automated tests (smoke, unit, integration)
│   └── test_smoke.py         # Smoke tests
├── scripts/
│   └── setup.sh              # Dev environment setup
├── pyproject.toml            # Project metadata and tool config
├── requirements.txt          # Pinned dependencies
└── config.example.toml       # Example configuration
```

## Codebase References

| Area | File Path | Purpose |
|------|-----------|---------|
| Entry point | `courier/__main__.py` | CLI argument parsing, logging setup, pipeline invocation |
| Configuration | `courier/config.py` | Loads TOML config into a Config dataclass |
| HTTP layer | `courier/fetcher.py` | Fetches RSS feed and article HTML |
| Extraction | `courier/extractor.py` | Extracts article content using readability-lxml with trafilatura fallback |
| Compilation | `courier/compiler.py` | Renders articles into a single XHTML document via Jinja2 |
| Cache/store | `courier/store.py` | Filesystem-based caching with SHA-256 URL hashing and atomic status writes |
| Orchestrator | `courier/orchestrator.py` | Coordinates the full fetch-extract-compile pipeline |
| XHTML template | `courier/templates/document.xhtml` | Kindle-compatible XHTML document template with TOC |
| Package config | `pyproject.toml` | Project metadata, dependencies, tool configuration |
| Example config | `config.example.toml` | Template for user configuration |
| Setup script | `scripts/setup.sh` | Creates venv and installs package in editable mode |
| Tests | `tests/` | All automated tests; currently `test_smoke.py` for imports, config dataclass, and store hashing |
