package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import xml._
import java.io.Reader
import org.pigsaw.wharfinger.Preamble._

/**
 * Object with a factory method to return an HTML document
 * from a reader. Use it as
 * <code>
 * val html: Node = HTMLNode.toNode(new URLReader("http://www.guardian.co.uk"))
 * </code>
 * Returns content in an <html> tag, and <body> tag within that.
 */

object HTMLNode {
  import scala.xml.{ Elem, XML }
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

  def toNode(reader: Reader): Node =
    TagSoupXMLLoader.get().load(reader)

  def transformChildren(e: Elem, fn: (Node) => Node): Node =
    e.copy(e.prefix, e.label, e.attributes, e.scope, false, e.child map { _.transform(fn) })

  def altText(e: Elem): String = e.attribute("alt") match {
    case Some(seq) if seq.mkString.length > 0 => "[Image: " + seq.mkString + "]"
    case _ => ""
  }

  def needsEscaping(s: String) = s exists charNeedsEscaping

  def escapeChar(c: Char): String = {
    if (charNeedsEscaping(c))
      "&#" + c.toInt + ";"
    else
      c.toString
  }

  def escapeForHTML(str: String): String = {
    str flatMap escapeChar
  }

  private def charNeedsEscaping(c: Char) =
    (c > 0x7F || Character.isISOControl(c)) && !Character.isWhitespace(c)
}

/**
 * Useful HTML-related methods for an XML Node.
 */
class HTMLNode(n: Node) {
  import HTMLNode._
    
  def transform(fn: (Node) => Node): Node = {
    n match {
      case e: Elem => fn(e) match {
        case e2: Elem => transformChildren(e2, fn)
        case other => other
      }
      case _ => fn(n)
    }
  }

  def escapeForHTML: Node = n.transform(_.escapeTrans)

  def escapeTrans: Node = n match {
    case a: Atom[_] if needsEscaping(a.data.toString) => new Unparsed(HTMLNode.escapeForHTML(a.data.toString))
    case x => x
  }

  /** Translate every element with a given label into another node.
   */
  def elemToNode(label: String, f: (Elem)=>Node): Node = n.transform(_ match {
    case e: Elem if (e.label.toLowerCase == label) => f(e)
    case x => x
  })
  
  def imagesToText: Node = elemToNode("img", { e => new Text(altText(e)) })

  def bodyToStoryDiv: Node = elemToNode("body", { e => <div id="story">{ e.child }</div> })

  def convertIfAttrOf(attr_name: String, attr_val: String): Node = {
    val meta = n \ ("@"+attr_name)
    if (meta.length == 1 && meta(0).toString == attr_val)
      Text("")
    else
      n
  }
  
  def transformIfAttrOf(attr_name: String, attr_val: String): Node =
    n.transform(_.convertIfAttrOf(attr_name, attr_val))

  def removeFontControls: Node = n.transformIfAttrOf("class", "page_header_read")

  def removeFooterControls = n.transformIfAttrOf("id", "evernote_modal").
    transformIfAttrOf("id", "highlight_create_popover").
    transformIfAttrOf("id", "highlight_delete_popover")

  def removeScriptTags = elemToNode("script", { _ => Text("") })
}

/**
 * Turn some HTML-like code and return a NodeSeq in good XML.
 */
object SloppyXMLNodeSeq {

  def apply(reader: Reader): NodeSeq = (HTMLNode.toNode(reader) \\ "body")(0).child
}
