package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import xml.{NodeSeq, Node}
import java.net.{HttpURLConnection, URL}

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 07-Aug-2010
 * Time: 11:08:57
 * To change this template use File | Settings | File Templates.
 */

object SimpleHtmlHandler {

  /** Take HTML as a string and get a parsable XML Node.
   */
  def readFromString(str: String): Node = {
    val reader = new java.io.StringReader(str)
    val parser = new TagSoupFactoryAdapter
    return parser load reader
  }

  /** Fetch HTML from a URL and get a parsable XML Node.
   */
  def readFromURL(url: String): Node = {
    val url_obj = new URL(url)
    val input = new java.io.InputStreamReader(url_obj.openStream)
    val reader = new java.io.BufferedReader(input)
    val parser = new TagSoupFactoryAdapter
    return parser load reader
  }

}

object Preamble {

  class RichNodeSeq(ns:Seq[Node]) {

    def having(nodeBuilder:Node => NodeSeq) = {
      ns filter( nodeBuilder(_).length > 0)
    }
  }

  implicit def nodeSeq2RichNodeSeq(ns:NodeSeq) = new RichNodeSeq(ns)
}

/**
 * Resolve a URL. Create a new instance using a URL string, then the field
 * <code>URL</code> contains the actual URL after any redirects.
 * Will not (apparently) resolve across HTTP/HTTPS boundaries; this is a
 * security feature of Java. See
 * http://stackoverflow.com/questions/1884230/java-doesnt-follow-redirect-in-urlconnection
 * for more.
 */
class URLResolver(url: String) {
  private val url_obj = new URL(url)
  private val connection = url_obj.openConnection.asInstanceOf[HttpURLConnection]
  connection.connect
  connection.getInputStream
  val URL = connection.getURL.toString
}
