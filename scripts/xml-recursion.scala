
object HTMLNode {
  import scala.xml._

  def transform(n: Node, fn: (Node)=>Node): Node = {
    n match {
      case e: Elem => {
        fn(e) match {
          case e2: Elem => transformChildren(e2, fn)
          case n => n
        }
      }
      case _ => {
        fn(n)
      }
    }
  }

  def transformChildren(e: Elem, fn: (Node)=>Node): Node = {
    e.copy(e.prefix, e.label, e.attributes, e.scope, e.child map (n => { println("    ---> " + n); transform(n, fn) }))
  }

  def escapeTrans(n: Node): Node = n match {
    case a:Atom[_] if needsEscaping(a.data.toString) => new Unparsed(escape(a.data.toString))
    case x => x
  }

  def needsEscaping(s: String) = s exists charNeedsEscaping

  def escapeChar(c: Char): String = {
    if (charNeedsEscaping(c))
      "&#" + c.toInt + ";"
    else
      c.toString
  }

  def escape(str: String): String = str flatMap escapeChar

  def escapeForHTML(node: Node): Node = transform(node, escapeTrans)

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

  def imagesToTextTrans(n: Node): Node = {
    n match {
      case e: Elem if (e.label.toLowerCase == "img") => new Text(altText(e))
      case x => x
    }
  }

  def altText(e: Elem): String = e.attribute("alt") match {
    case Some(seq) if seq.toString.length > 0 => "[Image: " + seq.toString + "]"
    case _ => ""
  }

  def imagesToText(node: Node): Node = transform(node, imagesToTextTrans)
}

val xml = <p><a href="/hello.txt"><img src="hello.jpg" alt="Smiley"/></a><img src="underline.jpg" alt="Underline"/></p>
HTMLNode.imagesToText(xml)

import scala.xml.Node
import scala.xml.Elem

val nested_as = <a level="one"><a level="two"></a><b><a level="three"><a>4</a></a></b></a>
def asToAsTrans(n: Node): Node = {
  n match {
      case e: Elem if (e.label == "a") =>
        e.copy(e.prefix, "A", e.attributes, e.scope, e.child)
      case x => x
    }
}

HTMLNode.transform(nested_as, asToAsTrans)