package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import java.net.{HttpURLConnection, URL}
import com.google.appengine.api.datastore.Text
import org.xml.sax.InputSource
import java.io.{StringWriter, StringReader, Reader}
import java.nio.charset.Charset
import scala.xml.{NodeSeq, Node}

/**
 * Object with a factory method to return an HTML document
 * from a reader. Use it as
 * <code>
 * val html: Node = HtmlNode(new URLReader("http://www.guardian.co.uk"))
 * </code>
 * Returns content in an <html> tag, and <body> tag within that.
 */

object HtmlNodeOld {

  def apply(reader: Reader): Node = {
    val parser = new TagSoupFactoryAdapter
    //val source = new org.xml.sax.InputSource(reader)
    //source.setEncoding("UTF-8")
    //return parser loadXML source
    return parser load reader
  }
}

object HtmlNode {
  import scala.xml.{Elem, XML}
  import scala.xml.factory.XMLLoader
  import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl

  object TagSoupXmlLoader {
      private val factory = new SAXFactoryImpl()
      def get(): XMLLoader[Elem] = {
          XML.withSAXParser(factory.newSAXParser())
      }
  }

  def apply(reader: Reader): Node = {
    return TagSoupXmlLoader.get.load(reader)
  }

  def toHTMLString(node: Node): String = {
    // The content ends up between <html> and <body> tags if we don't
    // put a bogon in there.
    val xmlstr = (<org.pigsaw.wharfinger>{ node }</org.pigsaw.wharfinger>).toString
    val parser = new Parser
    val string_writer = new StringWriter
    val xml_writer = new XMLWriter(string_writer)
    parser.setContentHandler(new XMLWriter(string_writer))
    parser.parse(new InputSource(new StringReader(xmlstr)))
    val full_xml_str = string_writer.toString
    // We now have something like
    // <?xml ...?>
    // <org.pigsaw.wharfinger xmlns="http://www.w3.org/1999/xhtml">...content...</org.pigsaw.wharfinger>
    val without_start = full_xml_str.split("<org.pigsaw.wharfinger")(1).dropWhile(_ != '>').tail
    // Now we should have
    // ...content...</org.pigsaw.wharfinger>
    val main = without_start.split("</org.pigsaw.wharfinger>")(0)
    main
  }

  def toHTMLString(ns: NodeSeq): String = ns map {node => HtmlNode.toHTMLString(node)} mkString

  def toHTMLString(text: String): String = HtmlNode.toHTMLString(new scala.xml.Text(text))
}

/**
 * Turn some HTML-like code and return a NodeSeq in good XML.
 */
object SloppyXMLNodeSeq {

  def apply(reader: Reader): NodeSeq = (HtmlNode(reader) \\ "body")(0).child
}

class URLReader(val url: String, charset: Charset)
        extends java.io.BufferedReader(new java.io.InputStreamReader(new java.net.URL(url).openStream, charset)) {

  def this(url: String) = this(url, Charset.defaultCharset)
  def this(url: String, charset: String) = this(url, Charset.forName(charset))
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
