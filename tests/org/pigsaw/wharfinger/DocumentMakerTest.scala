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
    it("Should be able to create a document with a single article") {
      val maker = new DocumentMaker
      val article1 = new Article(url = "http://some.site/here",
        citation = "Spotted by one of your followers",
        title = "How I won the war",
        content = "<div>I fought bravely</div>")
      maker.add(article1)
      val document = maker.document

      maker.articles.length should be (1)

      val citations: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-citation") map { n: Node => (n.child)(0) }
      
      citations(0).toString should be === ("<blockquote><i>Spotted by one of your followers</i></blockquote>")
    }
  }
}