package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import Preamble._
import java.io.{StringReader, IOException}

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 07-Aug-2010
 * Time: 11:01:28
 * To change this template use File | Settings | File Templates.
 */

class HtmlNodeTest extends Spec with ShouldMatchers {

  describe("SimpleHtmlHandler") {

    it("Should read HTML text") {
      val str = "<a>This is the first level<b>This is the second level</b></a>"
      val html = HtmlNode(new StringReader(str));
      val message = (html \\ "b").text
      message should be ("This is the second level")
    }

    it("Should be able to handle non-HTML text") {
      val str = "<<?!"
      val html = HtmlNode(new StringReader(str));
      html should not be (null)
    }

    it("Should be able to use 'containing' to limit to nodes which contain certain other node sequences") {
      val html = HtmlNode(new StringReader(Data.instapaper_html))
      val nodes = html \\ "li" containing (_ \\ "@accesskey")
      
      nodes should have length (3)

      nodes(0).label should be ("li")
      nodes(0).text should be ("Skip past navigation")
      nodes(1).label should be ("li")
      nodes(1).text should be ("Skip to navigation")
      nodes(2).label should be ("li")
    }

    it("Should read HTML from a URL") {
      val html = HtmlNode(new URLReader("http://www.google.com"))
      val title = (html \\ "title").text
      title should be ("Google")
    }

    it("Should throw an exception reading from a problematic URL") {
      def readURL = HtmlNode(new URLReader("http://www.willnotresolve5432.com"))
      evaluating(readURL) should produce [IOException]
    }

    it("Should read HTML from a redirected URL") {
      val html = HtmlNode(new URLReader("http://bit.ly/9NQcyA"))
      val title = (html \\ "title").text
      title should include ("A mobile developer day too far")
    }
  }

  describe("RedirectResolver") {

    it("Should allow URL redirects to be resolved") {
      val resolver = new RedirectResolver("http://bit.ly/9NQcyA")
      resolver.URL should be ("http://www.mobileuserexperience.com/?p=896")
    }

    it("Should throw an IOException if URL redirect cannot be resolved") {
      evaluating(new RedirectResolver("http://www.madeupdomain54321.com")) should produce [IOException]
    }

    /* This will not work due to Java's security.
     *
    it("Should resolve URLs across HTTP(S) boundaries") {
      val resolver = new RedirectResolver("http://bit.ly/3hQYj")
      resolver.URL should be ("https://www.google.com")
    }
    */
  }

  describe("RichNodeSeq") {
    it("Should be able to find a single node by ID") {
      val html = HtmlNode(new StringReader(Data.instapaper_html))
      val Some(div) = html findDivWithId "story"
      div.toString should startWith ("""<div id="story">""")
    }
  }
}