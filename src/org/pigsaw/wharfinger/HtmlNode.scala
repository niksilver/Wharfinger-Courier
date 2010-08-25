package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import xml.{NodeSeq, Node}
import java.net.{HttpURLConnection, URL}
import java.io.Reader
import com.google.appengine.api.datastore.Text

/**
 * Object with a factory method to return an HTML document
 * from a reader. Use it as
 * <code>
 * val html: Node = HtmlNode(new URLReader("http://www.guardian.co.uk"))
 * </code>
 * Returns content in an <html> tag, and <body> tag within that.
 */

object HtmlNode {

  def apply(reader: Reader): Node = {
    val parser = new TagSoupFactoryAdapter
    return parser load reader
  }
}

/**
 * Turn some HTML-like code and return a NodeSeq in good XML.
 */
object SloppyXMLNodeSeq {

  def apply(reader: Reader): NodeSeq = (HtmlNode(reader) \\ "body")(0).child
}

class URLReader(val url: String)
        extends java.io.BufferedReader(new java.io.InputStreamReader(new java.net.URL(url).openStream))

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
