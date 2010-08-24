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
      val maker = new DocumentMaker
      val article1 = new Article(url = "http://warring.com/winning",
        citation = "Spotted by one of <em>your</em> followers",
        title = "How I won the war",
        content = "<div>I fought bravely</div>")
      val article2 = new Article(url = "http://cows.co.uk/colours",
        citation = "Spotted by <em>someone</em>",
        title = "The colour of cows",
        content = "<p>Brown, black and white</p>")
      maker.add(article1)
      maker.add(article2)
      val document = maker.document

      maker.articles.length should be (2)

      val citations: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-citation") map { n => (n.child)(0) }
      citations(0).toString should be === ("<blockquote><i>Spotted by one of <em>your</em> followers</i></blockquote>")
      citations(1).toString should be === ("<blockquote><i>Spotted by <em>someone</em></i></blockquote>")

      val contents: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-content")
      contents(0).toString should be === ("<div class=\"wharfinger-content\"><div>I fought bravely</div></div>")
      contents(1).toString should be === ("<div class=\"wharfinger-content\"><p>Brown, black and white</p></div>")
    }
  }
}