"""Compile extracted articles into a Kindle-compatible XHTML document."""

from __future__ import annotations

import logging
from dataclasses import dataclass
from datetime import date
from pathlib import Path

from jinja2 import Environment, PackageLoader

logger = logging.getLogger(__name__)

# autoescape=True is the safety default and protects title and url fields.
# Article content uses ``| safe`` in the template because it has already been
# sanitised to XHTML by extractor._sanitise_to_xhtml(). This is a known trust
# boundary: if the extraction pipeline changes, review whether | safe is still safe.
_env = Environment(
    loader=PackageLoader("courier", "templates"),
    autoescape=True,
)
_template = _env.get_template("document.xhtml")


@dataclass
class Article:
    title: str
    content: str
    url: str


def compile_document(articles: list[Article], output_dir: Path) -> Path:
    """Compile a list of extracted articles into a single XHTML document.

    Returns the path to the written output file.
    """
    today = date.today().isoformat()
    filename = f"wharfinger-courier-{today}.xhtml"
    output_path = output_dir / filename

    rendered = _template.render(
        title=f"Wharfinger Courier - {today}",
        date=today,
        articles=articles,
    )

    output_dir.mkdir(parents=True, exist_ok=True)
    output_path.write_text(rendered, encoding="utf-8")
    logger.info("Compiled %d articles to %s", len(articles), output_path)

    return output_path
