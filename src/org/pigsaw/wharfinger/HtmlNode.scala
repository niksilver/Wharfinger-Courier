package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import xml.{NodeSeq, Node}
import java.net.{HttpURLConnection, URL}
import java.io.Reader

/**
 * Object with a factory method to return an HTML document
 * from a reader. Use it as
 * <code>
 * val html: Node = HtmlNode(new URLReader("http://www.guardian.co.uk"))
 * </code>
 */

object HtmlNode {

  def apply(reader: Reader): Node = {
    val parser = new TagSoupFactoryAdapter
    return parser load reader
  }
}

class URLReader(val url: String)
        extends java.io.BufferedReader(new java.io.InputStreamReader(new java.net.URL(url).openStream))

object Preamble {

  class RichNodeSeq(ns:Seq[Node]) {

    def containing(nodeBuilder: Node => NodeSeq): Seq[Node] = {
      ns filter( nodeBuilder(_).length > 0)
    }

    def findElementAttributeText(matcher: Tuple3[String,String,String]): Seq[Node] = {
      val (elt, attr, text) = matcher
      new RichNodeSeq(ns \\ elt) containing (_ \ attr filter (_.text == text))
    }

    def findElementAttributeSubstring(matcher: Tuple3[String,String,String]): Seq[Node] = {
      val (elt, attr, subs) = matcher
      new RichNodeSeq(ns \\ elt) containing (_ \ attr filter (_.text contains subs))
    }
  }

  implicit def nodeSeq2RichNodeSeq(ns:NodeSeq) = new RichNodeSeq(ns)
  implicit def node2RichNodeSeq(n:Node) = new RichNodeSeq(NodeSeq.Empty :+ n)
}

/**
 * Resolve a URL. Create a new instance using a URL string, then the field
 * <code>URL</code> contains the actual URL after any redirects.
 * Will not (apparently) resolve across HTTP/HTTPS boundaries; this is a
 * security feature of Java. See
 * http://stackoverflow.com/questions/1884230/java-doesnt-follow-redirect-in-urlconnection
 * for more.
 */
class RedirectResolver(url: String) {
  private val url_obj = new URL(url)
  private val connection = url_obj.openConnection.asInstanceOf[HttpURLConnection]
  connection.setRequestMethod("HEAD")
  connection.connect
  connection.getInputStream
  val URL = connection.getURL.toString
}
