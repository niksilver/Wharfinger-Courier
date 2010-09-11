package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import java.nio.charset.Charset
import xml.transform.{RuleTransformer, RewriteRule}
import xml._
import java.net.{HttpURLConnection, URL}
import java.io.{InputStream, Reader}

/**
 * Object with a factory method to return an HTML document
 * from a reader. Use it as
 * <code>
 * val html: Node = HTMLNode(new URLReader("http://www.guardian.co.uk"))
 * </code>
 * Returns content in an <html> tag, and <body> tag within that.
 */

object HTMLNode {
  import scala.xml.{Elem, XML}
  import scala.xml.factory.XMLLoader
  import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl

  // From http://blog.dub.podval.org/2010/08/scala-and-tag-soup.html
  object TagSoupXMLLoader {
      private val factory = new SAXFactoryImpl()
      def get(): XMLLoader[Elem] = {
        factory.setFeature(Parser.defaultAttributesFeature, false)
        XML.withSAXParser(factory.newSAXParser())
      }
  }

  def apply(reader: Reader): Node = {
    return TagSoupXMLLoader.get.load(reader)
  }

  def escapeForHTML(node: Node): Node = {

    def escapeTransform = new RuleTransformer(escapeRule)

    def escapeRule = new RewriteRule {
      override def transform(n: Node): NodeSeq = {
        n match {
          case a:Atom[_] if needsEscaping(a.data.toString) => escape(a.data.toString)
          case x => x
        }
      }
    }

    def needsEscaping(s: String) = s exists charNeedsEscaping

    def escapeChar(c: Char): Node = {
      if (charNeedsEscaping(c))
        new EntityRef("#" + c.toInt)
      else
        new Text(c.toString)
    }

    def escape(str: String) = str map escapeChar

    escapeTransform(node)
  }

  def escapeForHTML(str: String): String = {
    str flatMap { c =>
      if (charNeedsEscaping(c))
        "&#" + c.toInt + ";"
      else
        c.toString
    }
  }

  private def charNeedsEscaping(c: Char) =
    (c > 0x7F || Character.isISOControl(c)) && !Character.isWhitespace(c)

}

/**
 * Turn some HTML-like code and return a NodeSeq in good XML.
 */
object SloppyXMLNodeSeq {

  def apply(reader: Reader): NodeSeq = (HTMLNode(reader) \\ "body")(0).child
}

class URLReader(val url: String, charset: String)
        extends java.io.BufferedReader(
          new java.io.InputStreamReader(URLReader.makeInputStream(url), Charset.forName(charset)))

object URLReader {

  /** Make input stream from the URL, with useful timeouts.
   */
  def makeInputStream(url: String): InputStream = {
    val url_obj = new java.net.URL(url)
    val connection = url_obj.openConnection
    connection.setConnectTimeout(10*1000)
    connection.setReadTimeout(10*1000)
    connection.getInputStream
  }
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
