package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import Preamble._
import java.io.{StringReader, IOException}
import xml.NodeSeq

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
      val html = HTMLNode(new StringReader(str));
      val message = (html \\ "b").text
      message should be ("This is the second level")
    }

    it("Should be able to handle non-HTML text") {
      val str = "<<?!"
      val html = HTMLNode(new StringReader(str));
      html should not be (null)
    }

    it("Should be able to use 'containing' to limit to nodes which contain certain other node sequences") {
      val html = HTMLNode(new StringReader(Data.instapaper_html))
      val nodes = html \\ "li" containing (_ \\ "@accesskey")
      
      nodes should have length (3)

      nodes(0).label should be ("li")
      nodes(0).text should be ("Skip past navigation")
      nodes(1).label should be ("li")
      nodes(1).text should be ("Skip to navigation")
      nodes(2).label should be ("li")
    }

    it("Should read HTML from a URL") {
      val html = HTMLNode(new URLReader("http://www.google.com", "UTF-8"))
      val title = (html \\ "title").text
      title should be ("Google")
    }

    it("Should throw an exception reading from a problematic URL") {
      def readURL = HTMLNode(new URLReader("http://www.willnotresolve5432.com", "UTF-8"))
      evaluating(readURL) should produce [IOException]
    }

    it("Should be able to escaping special characters in XML") {
      val msg1 = "Message... one"
      val msg2 = "Message\u2014two"
      val xml = <outside class="clz" id="my\u2010id"><in1>{ msg1 }</in1><in2>{ msg2 }</in2></outside>
      val html = HTMLNode.escapeForHTML(xml)
      (html \\ "@id").toString should be === ("my\u2010id")
      (html \\ "in2").text should be === ("Message&#8212;two")
    }

    it("Should be able to escape special characters in a string") {
      val msg = "Message\u2014two"
      HTMLNode.escapeForHTML(msg) should be === ("Message&#8212;two")
    }

    it("Should not escape whitespace characters in a string") {
      val msg = "New\nline\tand so on"
      HTMLNode.escapeForHTML(msg) should be === ("New\nline\tand so on")
    }

    it("Should read HTML from a redirected URL") {
      val html = HTMLNode(new URLReader("http://bit.ly/9NQcyA", "UTF-8"))
      val title = (html \\ "title").text
      title should include ("A mobile developer day too far")
    }
  }

  describe("SloppyXMLNodeSeq") {

    it("Should tidy sloppy HTML-XML") {
      val xml = SloppyXMLNodeSeq(new StringReader("Back & forth"))
      xml.toString should be === ("Back &amp; forth")
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
      val html = HTMLNode(new StringReader(Data.instapaper_html))
      val Some(div) = html findDivWithId "story"
      div.toString should startWith ("""<div id="story">""")
    }
  }
}