package org.pigsaw.wharfinger

import xml.{NodeSeq, Node}
import com.google.appengine.api.datastore.Text

object Preamble {

  class RichNode(n: Node) {
    def escapeForHTML: Node = HTMLNode.escapeForHTML(n)
  }

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

    def findDivWithId(id: String): Option[Node] = {
      val elts = (this findElementAttributeText ("div", "@id", id))
      elts.length match {
        case 0 => None
        case _ => Some(elts(0))
      }
    }

  }

  class RichStringForHTML(str: String) {
    def escapeForHTML: String = HTMLNode.escapeForHTML(str)
  }

  implicit def nodeSeq2RichNodeSeq(ns: NodeSeq) = new RichNodeSeq(ns)

  implicit def node2RichNode(n: Node) = new RichNode(n)
  implicit def node2RichNodeSeq(n: Node) = new RichNodeSeq(n.theSeq)

  implicit def string2RichStringForHTML(str: String) = new RichStringForHTML(str)

  implicit def string2GAEText(s: String) = s match {
    case null => null
    case _ => new Text(s)
  }
  implicit def GAEText2String(t: Text) = t.getValue
}
