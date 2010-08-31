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
      val document = XML.loadString(maker.document)

      maker.articles.length should be (2)

      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      tocs.length should be === (1)

      val toc = tocs(0).child
      toc(0).toString should be === ("""<a name="TOC"></a>""")
      toc(1).toString should be === ("""<h3>Wharfinger Courier 25 Aug 2010</h3>""")
      toc(2).toString should startWith ("""<dl>""")

      val dl = toc(2).child

      dl(0).toString should be === ("""<dt><a href="#wharfinger-1">How I won the war</a></dt>""")
      dl(1).toString should be === ("""<dd>http://warring.com/winning</dd>""")
      dl(2).toString should be === ("""<dd><i>Spotted by one of your followers</i></dd>""")

      dl(3).toString should be === ("""<dt><a href="#wharfinger-2">The colour of cows</a></dt>""")
      dl(4).toString should be === ("""<dd>http://cows.co.uk/colours</dd>""")
      dl(5).toString should be === ("""<dd><i>Spotted by someone</i></dd>""")

      val chapter_refs = (document findElementAttributeText ("div", "@class", "wharfinger-chapter")) \ "a"
      chapter_refs(0).toString should be === ("""<a name="wharfinger-1"></a>""")
      chapter_refs(1).toString should be === ("""<a name="wharfinger-2"></a>""")

      val contents: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-content")
      contents(0).toString should be === ("""<div class="wharfinger-content"><div>I fought bravely</div></div>""")
      contents(1).toString should be === ("""<div class="wharfinger-content"><p>Brown, black and white</p></div>""")
    }

  }
}