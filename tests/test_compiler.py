"""Tests for courier.compiler.compile_document()."""

from pathlib import Path

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
