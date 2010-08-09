package org.pigsaw.wharfinger

import xml.Node
import Preamble._

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 09-Aug-2010
 * Time: 22:54:04
 * To change this template use File | Settings | File Templates.
 */

class DeliciousNetworkHandler {
  val html:Node = SimpleHtmlHandler.readFromURL("http://delicious.com/network/nik.silver?setcount=100")
  val bookmarks_html = html \\ "div" having (_ \ "@class" filter (_.text contains "bookmark "))
  val bookmarks = for (bookmark <- bookmarks_html) yield {
    val link = bookmark \\ "a" having (_ \ "@class" filter (_.text contains "taggedlink"))
    new DeliciousBookmark(link.text)
  }
}

class DeliciousBookmark(val title: String)