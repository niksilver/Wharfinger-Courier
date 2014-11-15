package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import xml._
import java.io.Reader

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
    e.copy(e.prefix, e.label, e.attributes, e.scope, false, e.child map { transform(_, fn) })

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
