package org.pigsaw.wharfinger

import java.io.Reader
import scala.collection.mutable.ListBuffer
import scala.xml.XML

/**
 * Read and parse the Twitter Times RSS feed
 */

class TwitterTimesNetworkHandler(val reader: Reader) {

  val bookmarks = new ListBuffer[TwitterTimesBookmark]()

  def this() = this(new URLReader("http://twittertim.es/pigsaw/rss.xml", "UTF-8"))

  /**Parse the RSS to create the bookmarks.
   */
  def parse() {
    val rss = XML.load(reader)
    val items = rss \ "channel" \ "item"
    for (item <- items) {
      bookmarks += new TwitterTimesBookmark((item \ "link").text)
    }
  }

  /** Process a single article URL.
   */
  /*def process(b: DeliciousBookmark) = {
    b.saveForLaterFetching
  }*/

  /** Process all articles which meet a given condition.
   */
  /*def process(cond: DeliciousBookmark => Boolean) {
    (bookmarks filter cond).foreach (process _)
  }*/

  /**Process all the bookmarks which meet some predefined condition.
   */
  //def process(): Unit = process(bookmark => bookmark.popularity > 0)
}

class TwitterTimesBookmark (val url: String)