package org.pigsaw.wharfinger

import xml.Node
import Preamble._

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
    new ArticleURL((link \ "@href").text)
  }
  bookmarks.foreach( process )

  /** Process a single bookmark. */
  def process(bookmark: ArticleURL) {
    val task = new QueueableTask("/fetchurl")
    task += ("url" -> bookmark.url)
    task.enqueue
  }
}

class ArticleURL(val url: String)