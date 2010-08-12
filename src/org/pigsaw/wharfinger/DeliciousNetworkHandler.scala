package org.pigsaw.wharfinger

import xml.Node
import Preamble._
import collection.immutable.List

/**
 * On construction, a DeliciousNetworkHandler will fetch and parse
 * a Delicious page, and process the bookmarks
 */

class DeliciousNetworkHandler {

  /** Read in the HTML
   */
  def readHtml():Node = SimpleHtmlHandler.readFromURL("http://delicious.com/network/nik.silver?setcount=100")

  val bookmarks_html = readHtml() \\ "div" having (_ \ "@class" filter (_.text contains "bookmark "))
  val bookmarks = for (bookmark <- bookmarks_html) yield {
    val link = bookmark \\ "a" having (_ \ "@class" filter (_.text contains "taggedlink"))
    new DeliciousBookmark(
      title = link.text,
      url = (link \ "@href").text)
  }
  bookmarks.foreach( process )

  def process(bookmark:DeliciousBookmark) { }
}

class DeliciousBookmark(
        val title: String,
        val url: String)