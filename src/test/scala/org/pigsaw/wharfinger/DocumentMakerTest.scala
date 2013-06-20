package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import Preamble._
import xml.{XML, Node}

/**
 * Test the DocumentMaker
 */

class DocumentMakerTest extends FunSpec with ShouldMatchers {

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
      val (maker1, success1) = maker.add(article1)
      val (maker2, success2) = maker1.add(article2)
      val document = XML.loadString(maker2.document)

      success1 should be (true)
      success2 should be (true)
      maker2.articles.length should be (2)

      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      tocs.length should be === (1)

      val toc = tocs(0).child
      toc(0).toString should be === ("""<a name="toc"></a>""")
      toc(1).toString should be === ("""<a name="start"></a>""")
      toc(2).toString should be === ("""<center><h3>Wharfinger Courier</h3><h4>25 Aug 2010</h4></center>""")
      toc(3).toString should be === ("""<p align="right"><small>2 articles</small></p>""")

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

      val (maker1, success1) = maker.add(article1)
      val (maker2, success2) = maker1.add(article2)
      val (maker3, success3) = maker2.add(article3)
      val (maker4, success4) = maker3.add(article4)
      val (maker5, success5) = maker4.add(article5)
      val (maker6, success6) = maker5.add(article6)
      val (maker7, success7) = maker6.add(article7)
      val (maker8, success8) = maker7.add(article8)
      val (maker9, success9) = maker8.add(article9)
      
      success1 should be (true)
      success2 should be (true)
      success3 should be (true)
      success4 should be (true)
      success5 should be (true)
      success6 should be (true)
      success7 should be (true)
      success8 should be (false)
      success9 should be (false)

      maker9.articles.length should be (7)

      maker9.rejectedArticles should contain (article8)
      maker9.rejectedArticles should contain (article9)

      val document = XML.loadString(maker9.document)
      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      val toc = tocs(0).child
      // toc(0) is <a name="toc"></a>
      // toc(1) is <a name="start"></a>
      // toc(2) is <center><h3>Wharfinger Courier</h3><h4>25 Aug 2010</h4></center>
      toc(3).toString should be === ("""<p align="right"><small>7 articles, 2 remaining</small></p>""")

    }

  }
}