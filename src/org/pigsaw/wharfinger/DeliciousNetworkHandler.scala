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

  val bookmarks = new ListBuffer[BookmarkPendingFetch]()

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
  def process(b: BookmarkPendingFetch) = {
    b.saveForLaterFetching
  }

  /** Process all articles which meet a given condition.
   */
  def process(cond: BookmarkPendingFetch => Boolean) {
    (bookmarks filter cond).foreach (process _)
  }

  /**Process all the bookmarks which meet some predefined condition.
   */
  def process(): Unit = process(bookmark => bookmark.popularity > 0)
}

object DeliciousNetworkHandler {

  def makeBookmark(bookmark_div: Node): BookmarkPendingFetch = {
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
    val username = (username_a_elt \ "@href").text.tail

    val citation_div = bookmark_div findElementAttributeText ("div", "@class", "description")
    val citation = citation_div.length match {
      case 0 => null
      case _ => citation_div.text.trim
    }

    /*new BookmarkPendingFetch(url = link,
      popularity = count,
      username = username,
      citation = citation)*/
    new BookmarkPendingFetch(link, count, username, citation)
  }

}
