package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers
import Preamble._
import xml.{XML, Node}

/**
 * Test the DocumentMaker
 */

class DocumentMakerTest extends FunSpec with Matchers {

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
      val maker1 = maker.add(article1)
      val maker2 = maker1.add(article2)
      val document = XML.loadString(maker2.document)

      maker1.rejected should be (Nil)
      maker2.rejected should be (Nil)
      maker2.articles.length should be (2)

      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      tocs.length should be (1)

      val toc = tocs(0).child
      toc(0) should be (<a name="toc"></a>)
      toc(1) should be (<a name="start"></a>)
      toc(2) should be (<center><h3>Wharfinger Courier</h3><h4>25 Aug 2010</h4></center>)
      toc(3) should be (<p align="right"><small>2 articles</small></p>)

      val chapter_refs = (document findElementAttributeText ("div", "@class", "wharfinger-chapter")) \ "a"
      chapter_refs.length should be (2)
      chapter_refs(0) should be (<a name="wharfinger-1"></a>)
      chapter_refs(1) should be (<a name="wharfinger-2"></a>)

      val contents: Seq[Node] = document findElementAttributeText (
              "div", "@class", "wharfinger-content")
      contents(0) should be (<div class="wharfinger-content"><div>I fought bravely</div></div>)
      contents(1) should be (<div class="wharfinger-content"><p>Brown, black and white</p></div>)
    }

    it("Should be able to add multiple articles") {
      val maker = new DocumentMaker("Wharfinger Courier", "25 Aug 2010")
      val article1 = new Article("http://warring.com/winning",
        "Spotted by one of your followers",
        "How I won the war",
        "<div>I fought bravely</div>")
      val article2 = new Article("http://cows.co.uk/colours",
        "Spotted by someone",
        "The colour of cows",
        "<p>Brown, black and white</p>")
      val maker2 = maker.add(List(article1, article2))
      val document = XML.loadString(maker2.document)

      maker2.rejected should be (Nil)
      maker2.articles.length should be (2)

      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      tocs.length should be (1)

      val toc = tocs(0).child
      toc(0) should be (<a name="toc"></a>)
      toc(1) should be (<a name="start"></a>)
      toc(2) should be (<center><h3>Wharfinger Courier</h3><h4>25 Aug 2010</h4></center>)
      toc(3) should be (<p align="right"><small>2 articles</small></p>)

      val chapter_refs = (document findElementAttributeText ("div", "@class", "wharfinger-chapter")) \ "a"
      chapter_refs.length should be (2)
      chapter_refs(0) should be (<a name="wharfinger-1"></a>)
      chapter_refs(1) should be (<a name="wharfinger-2"></a>)

      val contents: Seq[Node] = document findElementAttributeText (
        "div", "@class", "wharfinger-content")
      contents(0) should be (<div class="wharfinger-content"><div>I fought bravely</div></div>)
      contents(1) should be (<div class="wharfinger-content"><p>Brown, black and white</p></div>)
    }

    it("Should be able to add multiple articles from Java List") {
      val maker = new DocumentMaker("Wharfinger Courier", "25 Aug 2010")
      val article1 = new Article("http://warring.com/winning",
        "Spotted by one of your followers",
        "How I won the war",
        "<div>I fought bravely</div>")
      val article2 = new Article("http://cows.co.uk/colours",
        "Spotted by someone",
        "The colour of cows",
        "<p>Brown, black and white</p>")
      val articles: Seq[Article] = List(article1, article2)
      val maker2 = maker.add(articles)

      maker2.rejected should be (Nil)
      maker2.articles.length should be (2)
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

      val maker1 = maker.add(article1)
      val maker2 = maker1.add(article2)
      val maker3 = maker2.add(article3)
      val maker4 = maker3.add(article4)
      val maker5 = maker4.add(article5)
      val maker6 = maker5.add(article6)
      val maker7 = maker6.add(article7)
      val maker8 = maker7.add(article8)
      val maker9 = maker8.add(article9)
      
      maker1.rejected should be (Nil)
      maker2.rejected should be (Nil)
      maker3.rejected should be (Nil)
      maker4.rejected should be (Nil)
      maker5.rejected should be (Nil)
      maker6.rejected should be (Nil)
      maker7.rejected should be (Nil)
      maker8.rejected.length should be (1)
      maker9.rejected.length should be (2)

      maker9.articles.length should be (7)

      maker9.rejected should contain (article8)
      maker9.rejected should contain (article9)

      val document = XML.loadString(maker9.document)
      val tocs: Seq[Node] = document findElementAttributeText ("div", "@class", "wharfinger-toc")
      val toc = tocs(0).child
      // toc(0) is <a name="toc"></a>
      // toc(1) is <a name="start"></a>
      // toc(2) is <center><h3>Wharfinger Courier</h3><h4>25 Aug 2010</h4></center>
      toc(3) should be (<p align="right"><small>7 articles, 2 remaining</small></p>)

    }

  }
}