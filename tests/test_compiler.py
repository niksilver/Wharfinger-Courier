"""Tests for courier.compiler.compile_document()."""

from datetime import date
from pathlib import Path
from unittest.mock import patch

from lxml import etree

from courier.compiler import Article, compile_document


def _article(
    title  = "Test Article",
    content = "<p>Some content.</p>",
    url     = "https://example.com/test",
) -> Article:
    return Article(title=title, content=content, url=url)


def test_compile_document_creates_file(tmp_path):
    output_path = compile_document([_article()], tmp_path)
    assert output_path.exists()
    assert output_path.suffix == ".xhtml"


def test_compile_document_contains_article_title(tmp_path):
    article     = _article(title="My Interesting Article")
    output_path = compile_document([article], tmp_path)
    content     = output_path.read_text(encoding="utf-8")
    assert "My Interesting Article" in content


def test_compile_document_output_is_valid_xhtml(tmp_path):
    output_path = compile_document([_article()], tmp_path)
    parser      = etree.XMLParser(load_dtd=False, no_network=True)
    etree.parse(str(output_path), parser)


def test_compile_document_empty_list(tmp_path):
    output_path = compile_document([], tmp_path)
    assert output_path.exists()
    parser      = etree.XMLParser(load_dtd=False, no_network=True)
    etree.parse(str(output_path), parser)



def test_compile_document_creates_output_dir(tmp_path):
    output_dir  = tmp_path / "new" / "nested" / "dir"
    output_path = compile_document([_article()], output_dir)
    assert output_dir.exists()
    assert output_path.exists()


def test_compile_document_title_format(tmp_path):
    fixed_date = date(2026, 4, 27)
    with patch("courier.compiler.date") as mock_date:
        mock_date.today.return_value = fixed_date
        output_path = compile_document([_article()], tmp_path)
    content = output_path.read_text(encoding="utf-8")
    assert "Wharfinger Courier, 27 April 2026" in content


def test_compile_document_has_no_manual_toc(tmp_path):
    articles    = [_article(title="First"), _article(title="Second")]
    output_path = compile_document(articles, tmp_path)
    content     = output_path.read_text(encoding="utf-8")
    assert "Table of Contents" not in content
    assert "#article-" not in content


def test_compile_document_articles_in_document_divs(tmp_path):
    articles    = [_article(title="Alpha"), _article(title="Beta")]
    output_path = compile_document(articles, tmp_path)
    parser      = etree.XMLParser(load_dtd=False, no_network=True)
    tree        = etree.parse(str(output_path), parser)
    ns          = {"h": "http://www.w3.org/1999/xhtml"}
    divs        = tree.findall(".//h:div[@class='document']", ns)
    assert len(divs) == 2


def test_compile_document_articles_have_chapter_h1(tmp_path):
    articles    = [_article(title="Alpha"), _article(title="Beta")]
    output_path = compile_document(articles, tmp_path)
    parser      = etree.XMLParser(load_dtd=False, no_network=True)
    tree        = etree.parse(str(output_path), parser)
    ns          = {"h": "http://www.w3.org/1999/xhtml"}
    h1s         = tree.findall(".//h:h1[@class='chapter']", ns)
    assert len(h1s) == 2
    titles      = [h1.text for h1 in h1s]
    assert "Alpha" in titles
    assert "Beta" in titles
