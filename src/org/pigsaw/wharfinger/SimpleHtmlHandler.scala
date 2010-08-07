package org.pigsaw.wharfinger

import org.ccil.cowan.tagsoup._
import xml.{NodeSeq, Node}
import java.net.URL

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 07-Aug-2010
 * Time: 11:08:57
 * To change this template use File | Settings | File Templates.
 */

object SimpleHtmlHandler {

  def readFromString(str: String): Node = {
    val reader = new java.io.StringReader(str)
    val parser = new TagSoupFactoryAdapter
    return parser load reader
  }

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