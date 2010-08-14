package org.pigsaw.wharfinger

import Preamble._
import java.io.Reader
import collection.mutable.ListBuffer

/**
 * An object that will read from Delicious and parse out
 * articles to fetch
 */

class DeliciousNetworkHandler(val reader: Reader) {

  val bookmarks = new ListBuffer[ArticleURL]()

  def this() = this(new UrlReader("http://delicious.com/network/nik.silver?setcount=100"))

  /**Parse the HTML to create the bookmarks.
   */
  def parse() {
    val html = HtmlNode(reader)
    val bookmarks_div =  html \\ "div" having (_ \ "@class" filter (_.text contains "bookmark "))
    for (bookmark <- bookmarks_div) {
      val link = bookmark \\ "a" having (_ \ "@class" filter (_.text contains "taggedlink"))
      val article_url = new ArticleURL((link \ "@href").text)
      bookmarks += article_url
    }
  }

}

class ArticleURL(val url: String) {

  /** Process a single bookmark. */
  def process() {
    val task = new QueueableTask("/fetchurl")
    task += ("url" -> url)
    task.enqueue
  }

}