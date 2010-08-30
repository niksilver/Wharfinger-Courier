package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import Preamble._
import xml.Node

/**
 * Test the DocumentMaker
 */

class DocumentMakerTest extends Spec with ShouldMatchers {

  describe("DocumentMaker") {
    it("Should be able to create a document with two articles") {
      val maker = new DocumentMaker("Wharfinger Courier 25 Aug 2010")
      val article1 = new Article(url = "http://warring.com/winning",
        citation = "Spotted by one of your followers",
        title = "How I won the war",
        content = "<div>I fought bravely</div>")
      val article2 = new Article(url = "http://cows.co.uk/colours",
        citation = "Spotted by someone",
        title = "The colour of cows",
        content = "<p>Brown, black and white</p>")
      maker.add(article1)
      maker.add(article2)
      val document = maker.document

      maker.articles.length should be (2)

      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      tocs.length should be === (1)

      val toc = tocs(0).child
      toc(0).toString should be === ("""<p><a name="TOC"></a><h3>Wharfinger Courier 25 Aug 2010</h3></p>""")
      toc(1).toString should be === ("""<p><a href="#wharfinger-1"><h4>How I won the war</h4></a></p>""")
      toc(2).toString should be === ("""<p><a href="#wharfinger-2"><h4>The colour of cows</h4></a></p>""")

      val chapter_refs = (document findElementAttributeText ("div", "@class", "wharfinger-chapter")) \ "a"
      chapter_refs(0).toString should be === ("""<a name="wharfinger-1"></a>""")
      chapter_refs(1).toString should be === ("""<a name="wharfinger-2"></a>""")

      val citations: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-citation") map { n => (n.child)(0) }
      citations(0).toString should be === ("<blockquote><i>Spotted by one of your followers</i></blockquote>")
      citations(1).toString should be === ("<blockquote><i>Spotted by someone</i></blockquote>")

      val contents: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-content")
      contents(0).toString should be === ("""<div class="wharfinger-content"><div>I fought bravely</div></div>""")
      contents(1).toString should be === ("""<div class="wharfinger-content"><p>Brown, black and white</p></div>""")
    }

    it("Should be able to handle malformed HTML in the citation") {
      val maker = new DocumentMaker("Wharfinger Courier 25 Aug 2010")
      val article = new Article(url = "http://warring.com/winning",
        citation = "Both backwards & forwards",
        title = "How I won the war",
        content = "<div>I fought bravely</div>")
      maker.add(article)
      val document = maker.document
      val citations: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-citation") map { n => (n.child)(0) }
      citations(0).toString should be === ("<blockquote><i>Both backwards &amp; forwards</i></blockquote>")
    }

    it("Should be able to handle malformed HTML in the title") {
      val maker = new DocumentMaker("Wharfinger Courier 25 Aug 2010")
      val article = new Article(url = "http://warring.com/winning",
        citation = "Both backwards",
        title = "How I won & so on",
        content = "<div>I fought bravely</div>")
      maker.add(article)

      val document = maker.document
      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      val toc = tocs(0).child
      toc(0).toString should be === ("""<p><a name="TOC"></a><h3>Wharfinger Courier 25 Aug 2010</h3></p>""")
      toc(1).toString should be === ("""<p><a href="#wharfinger-1"><h4>How I won &amp; so on</h4></a></p>""")
    }

    it("Should be able to handle malformed HTML in the content") {
      val maker = new DocumentMaker("Wharfinger Courier 25 Aug 2010")
      val article = new Article(url = "http://warring.com/winning",
        citation = "Both backwards",
        title = "How I won",
        content = "I fought & lost")
      maker.add(article)

      val document = maker.document
      val contents: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-content")
      contents(0).toString should be === ("""<div class="wharfinger-content">I fought &amp; lost</div>""")
    }

  }
}