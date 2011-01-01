package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import Preamble._
import xml.{XML, Node}

/**
 * Test the DocumentMaker
 */

class DocumentMakerTest extends Spec with ShouldMatchers {

  describe("DocumentMaker") {
    it("Should be able to create a document with two articles") {
      val maker = new DocumentMaker("Wharfinger Courier", "25 Aug 2010")
      val article1 = new Article("http://warring.com/winning",
        "Spotted by one of your followers",
        "How I won the war",
        "<div>I fought bravely</div>")
      val article2 = new Article("http://cows.co.uk/colours",
        "Spotted by someone",
        "The colour of cows",
        "<p>Brown, black and white</p>")
      maker.add(article1)
      maker.add(article2)
      val document = XML.loadString(maker.document)

      maker.articles.length should be (2)

      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      tocs.length should be === (1)

      val toc = tocs(0).child
      toc(0).toString should be === ("""<a name="toc"></a>""")
      toc(1).toString should be === ("""<a name="start"></a>""")
      toc(2).toString should be === ("""<center><h3>Wharfinger Courier</h3><h4>25 Aug 2010</h4></center>""")

      val chapter_refs = (document findElementAttributeText ("div", "@class", "wharfinger-chapter")) \ "a"
      chapter_refs.length should be === (2)
      chapter_refs(0).toString should be === ("""<a name="wharfinger-1"></a>""")
      chapter_refs(1).toString should be === ("""<a name="wharfinger-2"></a>""")

      val contents: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-content")
      contents(0).toString should be === ("""<div class="wharfinger-content"><div>I fought bravely</div></div>""")
      contents(1).toString should be === ("""<div class="wharfinger-content"><p>Brown, black and white</p></div>""")
    }

  }
}