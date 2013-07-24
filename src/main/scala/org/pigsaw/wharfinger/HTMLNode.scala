package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import java.nio.charset.Charset
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

  def apply(reader: Reader): Node =
    TagSoupXMLLoader.get().load(reader)


  def transform(n: Node, fn: (Node)=>Node): Node = {
    n match {
      case e: Elem => fn(e) match {
          case e2: Elem => transformChildren(e2, fn)
          case other => other
        }
      case _ => fn(n)
    }
  }

  def transformChildren(e: Elem, fn: (Node)=>Node): Node =
    e.copy(e.prefix, e.label, e.attributes, e.scope, e.child map { transform(_, fn) })

  def escapeTrans(n: Node): Node = n match {
    case a:Atom[_] if needsEscaping(a.data.toString) => new Unparsed(escapeForHTML(a.data.toString))
    case x => x
  }

  def needsEscaping(s: String) = s exists charNeedsEscaping

  def escapeChar(c: Char): String = {
    if (charNeedsEscaping(c))
      "&#" + c.toInt + ";"
    else
      c.toString
  }

  def escapeForHTML(node: Node): Node = transform(node, escapeTrans)

  def escapeForHTML(str: String): String = {
    str flatMap escapeChar
  }

  private def charNeedsEscaping(c: Char) =
    (c > 0x7F || Character.isISOControl(c)) && !Character.isWhitespace(c)

  def imagesToTextTrans(n: Node): Node = n match {
    case e: Elem if (e.label.toLowerCase == "img") => new Text(altText(e))
    case x => x
  }

  def altText(e: Elem): String = e.attribute("alt") match {
    case Some(seq) if seq.mkString.length > 0 => "[Image: " + seq.mkString + "]"
    case _ => ""
  }

  def imagesToText(node: Node): Node = transform(node, imagesToTextTrans)

  def bodyToStoryDivTrans(n: Node): Node = n match {
    case e: Elem if (e.label.toLowerCase == "body") => <div id="story">{ e.child }</div>
    case x => x
  }

  def bodyToStoryDiv(node: Node): Node = transform(node, bodyToStoryDivTrans)
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
  connection.connect()
  val URL = connection.getURL.toString
}
