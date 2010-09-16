package org.pigsaw.wharfinger

import java.io.Reader
import collection.mutable.ListBuffer

/**
 * Read and parse the Twitter Times RSS feed
 */

class TwitterTimesNetworkHandler(val reader: Reader) {

  val bookmarks = new ListBuffer[DeliciousBookmark]()

  def this() = this(new URLReader("http://twittertim.es/pigsaw/rss.xml", "UTF-8"))

  /**Parse the RSS to create the bookmarks.
   */
  def parse() {
    /*val rss = HTMLNode(reader)
    val bookmarks_div =  html findElementAttributeSubstring ("div", "@class", "bookmark ")
    for (bookmark_div <- bookmarks_div) {
      bookmarks += DeliciousNetworkHandler.makeBookmark(bookmark_div)
    }*/
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
