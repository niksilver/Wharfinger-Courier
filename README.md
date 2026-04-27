# Wharfinger Courier

A personal command-line tool that fetches articles from RSS feeds and compiles them into a Kindle-compatible AZW3 document.

## Setup

Requires Python 3.10+ and [Calibre](https://calibre-ebook.com/) (`ebook-convert` must be on your PATH).

```bash
bash scripts/setup.sh
source .venv/bin/activate
```

Then create your config file:

```bash
cp config.example.toml config.toml
# Edit config.toml with your feed URL and preferred directories
```

## Running

```bash
courier
```

Options:

| Flag | Description |
|------|-------------|
| `--dry-run` | Show what would be fetched without making changes |
| `--since N` | Archive mode: include articles bookmarked in the last N days |
| `--config PATH` | Use a config file other than `config.toml` |
| `--verbose` | Enable debug logging |

## Running the tests

```bash
pytest
```
