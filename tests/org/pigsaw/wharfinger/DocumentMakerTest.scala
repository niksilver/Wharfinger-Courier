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

    it("Should insert articles up to 740k") {
      def content(msg: String, size: Int): String =
        (msg * (size/msg.length + 1)).slice(0, size)
      val maker = new DocumentMaker("Wharfinger Courier", "25 Aug 2010")

      val article1 = new Article("http://art.com/1","Cit 1", "Title 1", content("Content 1 ", 100*1024))
      val article2 = new Article("http://art.com/2","Cit 2", "Title 2", content("Content 2 ", 100*1024))
      val article3 = new Article("http://art.com/3","Cit 3", "Title 3", content("Content 3 ", 100*1024))
      val article4 = new Article("http://art.com/4","Cit 4", "Title 4", content("Content 4 ", 100*1024))
      val article5 = new Article("http://art.com/5","Cit 5", "Title 5", content("Content 5 ", 100*1024))
      val article6 = new Article("http://art.com/6","Cit 6", "Title 6", content("Content 6 ", 100*1024))
      val article7 = new Article("http://art.com/7","Cit 7", "Title 7", content("Content 7 ", 100*1024))
      val article8 = new Article("http://art.com/8","Cit 8", "Title 8", content("Content 8 ", 100*1024))
      val article9 = new Article("http://art.com/9","Cit 9", "Title 9", content("Content 9 ", 100*1024))

      maker.add(article1) should be (true)
      maker.add(article2) should be (true)
      maker.add(article3) should be (true)
      maker.add(article4) should be (true)
      maker.add(article5) should be (true)
      maker.add(article6) should be (true)
      maker.add(article7) should be (true)
      maker.add(article8) should be (false)
      maker.add(article9) should be (false)

      maker.articles.length should be (7)

      maker.rejectedArticles should contain (article8)
      maker.rejectedArticles should contain (article9)
    }

  }
}