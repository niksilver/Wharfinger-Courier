package org.pigsaw.wharfinger

import Preamble._
import java.io.Reader
import collection.mutable.ListBuffer
import xml.{Node, NodeSeq}

abstract class BookmarkServiceNetworkHandler {
  def parse: Unit
  def bookmarksPendingFetch: Seq[BookmarkPendingFetch]
}

/**
 * An object that will read from Delicious and parse out
 * articles to fetch
 */

class DeliciousNetworkHandler(val reader: Reader) extends BookmarkServiceNetworkHandler {

  val bookmarks = new ListBuffer[DeliciousBookmark]()

  def this() = this(new URLReader("http://delicious.com/network/nik.silver?setcount=50", "UTF-8"))

  /**Parse the HTML to create the bookmarks.
   */
  def parse() {
    val html = HTMLNode(reader)
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

  def bookmarksPendingFetch: Seq[BookmarkPendingFetch] =
    for (b <- bookmarks) yield b.bookmarkPendingFetch
}

object DeliciousNetworkHandler {

  def makeBookmark(bookmark_div: Node): DeliciousBookmark = {
    val a_elt = bookmark_div findElementAttributeSubstring ("a", "@class", "taggedlink")
    val link = (a_elt \ "@href").text
    val title = a_elt.text

    val count_span = bookmark_div findElementAttributeText ("span", "@class", "delNavCount")
    val count = count_span match {
      case NodeSeq.Empty => 1
      case span => span.text.toInt
    }

    val username_a_elt = bookmark_div findElementAttributeText ("a", "@class", "user user-tag")
    // The href will be href="/currybet",
    // so we need to lose the / to get the username
    val username = (username_a_elt \ "@href").text.tail

    val description_div = bookmark_div findElementAttributeText ("div", "@class", "description")
    val description = description_div.length match {
      case 0 => None
      case _ => Some(description_div.text.trim)
    }

    new DeliciousBookmark(url = link,
      username = username,
      popularity = count,
      title = title,
      description = description
    )
  }

}

class DeliciousBookmark(
  val url: String,
  val username: String,
  val popularity: Int,
  val title: String,
  val description: Option[String]) {

  val bookmarkPendingFetch = new BookmarkPendingFetch(url,
    title,
    makeCitation(popularity, username, description))

  def citation = bookmarkPendingFetch.getCitation
  
  def saveForLaterFetching() { bookmarkPendingFetch.saveForLaterFetching }

  private def makeCitation(popularity: Int, username: String, citation: Option[String]) = {
    val bookmarkers = popularity match {
      case 1 => username
      case 2 => username + " and one other"
      case _ => username + " and " + (popularity-1) + " others"
    }
    val citation_suffix = citation match {
      case Some(citation_text) => ": " + citation_text
      case None => ""
    }
    "Bookmarked by " + bookmarkers + citation_suffix
  }
}
