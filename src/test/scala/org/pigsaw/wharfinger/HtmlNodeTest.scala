package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers
import Preamble._
import java.io.{StringReader, IOException}
import scala.xml._

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 07-Aug-2010
 * Time: 11:01:28
 * To change this template use File | Settings | File Templates.
 */

class HtmlNodeTest extends FunSpec with Matchers {

  describe("SimpleHtmlHandler") {

    it("Should read HTML text") {
      val str = "<a>This is the first level<b>This is the second level</b></a>"
      val html = HTMLNode.toNode(new StringReader(str));
      val message = (html \\ "b").text
      message should be ("This is the second level")
    }

    it("Should be able to handle non-HTML text") {
      val str = "<<?!"
      val html = HTMLNode.toNode(new StringReader(str));
      html should not be (null)
    }

    it("Should be able to use 'containing' to limit to nodes which contain certain other node sequences") {
      val html = HTMLNode.toNode(new StringReader(Data.instapaper_html))
      val nodes = html \\ "li" containing (_ \\ "@accesskey")
      
      nodes should have length (3)

      nodes(0).label should be ("li")
      nodes(0).text should be ("Skip past navigation")
      nodes(1).label should be ("li")
      nodes(1).text should be ("Skip to navigation")
      nodes(2).label should be ("li")
    }

    it("Should read HTML from a URL") {
      val html = HTMLNode.toNode(new URLReader("http://www.google.com", "UTF-8"))
      val title = (html \\ "title").text
      title should be ("Google")
    }

    it("Should throw an exception reading from a problematic URL") {
      def readURL = HTMLNode.toNode(new URLReader("http://www.willnotresolve5432.com", "UTF-8"))
      an [IOException] should be thrownBy { readURL }
    }

    it("Should be able to escaping special characters in XML") {
      val msg1 = "Message... one"
      val msg2 = "Message\u2014two"
      val xml = <outside class="clz" id="my\u2010id"><in1>{ msg1 }</in1><in2>{ msg2 }</in2></outside>
      val html = xml.escapeForHTML
      (html \\ "@id").toString should be ("my\u2010id")
      (html \\ "in2").text should be ("Message&#8212;two")
    }

    it("Should be able to escape special characters in a string") {
      val msg = "Message\u2014two"
      HTMLNode.escapeForHTML(msg) should be ("Message&#8212;two")
    }

    it("Should not escape whitespace characters in a string") {
      val msg = "New\nline\tand so on"
      HTMLNode.escapeForHTML(msg) should be ("New\nline\tand so on")
    }

    it("Should be able to replace images with text") {
      val xml = <p><a href="/hello.txt"><img src="hello.jpg" alt="Smiley"/></a><img src="underline.jpg" alt="Underline"/></p>
      xml.imagesToText.toString should be (
        """<p><a href="/hello.txt">[Image: Smiley]</a>[Image: Underline]</p>""")
    }

    it("Should be able to replace images which have empty alt text") {
      val xml = <p><a href="/hello.txt"><img src="hello.jpg" alt=""/></a><img src="underline.jpg" alt=""/></p>
      xml.imagesToText.toString should be (
        """<p><a href="/hello.txt"></a></p>""")
    }

    it("Should be able to replace images which have no text") {
      val xml = <p><a href="/hello.txt"><img src="hello.jpg"/></a><img src="underline.jpg"/></p>
      xml.imagesToText.toString should be (
        """<p><a href="/hello.txt"></a></p>""")
    }

    it("Should be able to replace images which have non-empty Text nodes as alt text") {
      val text = new Text("Hello")
      val xml = <p><a href="/hello.txt"><img src="hello.jpg" alt={ text }/></a></p>
      xml.imagesToText.toString should be (
        """<p><a href="/hello.txt">[Image: Hello]</a></p>""")
    }

    it("Should be able to replace images which have empty Text nodes as alt text") {
      val empty_text = new Text("")
      val xml = <p><a href="/hello.txt"><img src="hello.jpg" alt={ empty_text }/></a></p>
      xml.imagesToText.toString should be (
        """<p><a href="/hello.txt"></a></p>""")
    }

    it("Should allow you to replace images and escape HTML") {
      import Preamble._
      val msg = "Message\u2014again"
      val xml = <p><a href="/hello.txt">{ msg }<img src="hello.jpg" alt="Hello"/></a><img src="underline.jpg" alt="Under\u2014line"/></p>
      xml.imagesToText.escapeForHTML.toString should be (
        """<p><a href="/hello.txt">Message&#8212;again[Image: Hello]</a>[Image: Under&#8212;line]</p>""")
    }

    it("Should be able to change a body into a story div") {
      val xml = <body>It's <out0></out0> and <out1>Out<in1>In 1</in1><in2>In 2</in2>side</out1></body>
      xml.bodyToStoryDiv.toString should be (
        """<div id="story">It's <out0></out0> and <out1>Out<in1>In 1</in1><in2>In 2</in2>side</out1></div>""")
    }

    it("Should read HTML from a redirected URL") {
      val html = HTMLNode.toNode(new URLReader("http://bit.ly/9NQcyA", "UTF-8"))
      val title = (html \\ "title").text
      title should include ("A mobile developer day too far")
    }

    it("Should be able to transform elements recursively") {
      val nested_as = <a level="one"><a level="two"></a><b><a level="three"><a>4</a></a></b></a>
      def asToAsTrans(n: Node): Node = {
        n match {
          case e: Elem if (e.label == "a") =>
            e.copy(e.prefix, "A", e.attributes, e.scope, false, e.child)
          case x => x
        }
      }

      val nested_As = nested_as.transform(asToAsTrans)
      nested_As should be (<A level="one"><A level="two"></A><b><A level="three"><A>4</A></A></b></A>)
    }
  }

  describe("SloppyXMLNodeSeq") {

    it("Should tidy sloppy HTML-XML") {
      val xml = SloppyXMLNodeSeq(new StringReader("Back & forth"))
      xml.toString should be ("Back &amp; forth")
    }
  }

  describe("RichNodeSeq") {
    it("Should be able to find a single node by ID") {
      val html = HTMLNode.toNode(new StringReader(Data.instapaper_html))
      val Some(div) = html findDivWithId "story"
      div.toString should startWith ("""<div id="story">""")
    }

    it("Should allow you to find an element with an attribute whose value starts with something") {
      val n = <a>
          <b id="oneanother"></b>
          <c><b id="onegin"></b></c>
          <b id="twozzack"></b>
        </a>
      val extract = n findElementAttributeStartingWith ("b", "@id", "one")
      extract.size should be (2)
      extract(0).attribute("id").get(0).toString should be ("oneanother")
      extract(1).attribute("id").get(0).toString should be ("onegin")
    }
  }
}