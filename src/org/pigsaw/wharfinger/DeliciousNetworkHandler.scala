package org.pigsaw.wharfinger

import Preamble._
import java.io.Reader
import collection.mutable.ListBuffer
import xml.{Node, NodeSeq}

/**
 * An object that will read from Delicious and parse out
 * articles to fetch
 */

class DeliciousNetworkHandler(val reader: Reader) {

  val bookmarks = new ListBuffer[DeliciousBookmark]()

  def this() = this(new URLReader("http://delicious.com/network/nik.silver?setcount=100"))

  /**Parse the HTML to create the bookmarks.
   */
  def parse() {
    val html = HtmlNode(reader)
    val bookmarks_div =  html findElementAttributeSubstring ("div", "@class", "bookmark ")
    for (bookmark_div <- bookmarks_div) {
      bookmarks += DeliciousNetworkHandler.makeBookmark(bookmark_div)
    }
  }

  /** Process a single article URL.
   */
  def process(b: DeliciousBookmark) = {
    b.saveForLaterFetching
  }

  /** Process all articles which meet a given condition.
   */
  def process(cond: DeliciousBookmark => Boolean) {
    (bookmarks filter cond).foreach (process _)
  }

  /**Process all the bookmarks which meet some predefined condition.
   */
  def process(): Unit = process(bookmark => bookmark.popularity > 0)
}

class DeliciousBookmark(
  val url: String,
  val username: String,
  val popularity: Int,
  val description: String) {

  private val bookmark_jdo = new BookmarkPendingFetch(url, makeCitation(popularity, username, description))

  def citation = bookmark_jdo.getCitation
  
  def saveForLaterFetching() { bookmark_jdo.saveForLaterFetching }

  private def makeCitation(count: Int, username: String, citation: String) = {
    "Bookmarked by " + username + ": " + citation
  }
}

object DeliciousNetworkHandler {

  def makeBookmark(bookmark_div: Node): DeliciousBookmark = {
    val a_elt = bookmark_div findElementAttributeSubstring ("a", "@class", "taggedlink")
    val link = (a_elt \ "@href").text

    val count_span = bookmark_div findElementAttributeText ("span", "@class", "delNavCount")
    val count = count_span match {
      case NodeSeq.Empty => 1
      case span => span.text.toInt
    }

    val username_a_elt = bookmark_div findElementAttributeText ("a", "@class", "user user-tag")
    // The href will be href="/currybet",
    // so we need to lose the / to get the username
    val username0 = (username_a_elt \ "@href").text.tail

    val description_div = bookmark_div findElementAttributeText ("div", "@class", "description")
    val description0 = description_div.length match {
      case 0 => null
      case _ => description_div.text.trim
    }

    new DeliciousBookmark(url = link,
      username = username0,
      popularity = count,
      description = description0
    )
  }

}
