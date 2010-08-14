package org.pigsaw.wharfinger

import Preamble._
import java.io.Reader
import collection.mutable.ListBuffer
import xml.{NodeSeq}

/**
 * An object that will read from Delicious and parse out
 * articles to fetch
 */

class DeliciousNetworkHandler(val reader: Reader) {

  val bookmarks = new ListBuffer[ArticleURL]()

  def this() = this(new URLReader("http://delicious.com/network/nik.silver?setcount=100"))

  /**Parse the HTML to create the bookmarks.
   */
  def parse() {
    val html = HtmlNode(reader)
    val bookmarks_div =  html \\ "div" containing (_ \ "@class" filter (_.text contains "bookmark "))
    for (bookmark <- bookmarks_div) {
      val a_elt = bookmark \\ "a" containing (_ \ "@class" filter (_.text contains "taggedlink"))
      val link = (a_elt \ "@href").text

      val count_span = bookmark \\ "span" containing (_ \ "@class" filter (_.text contains "delNavCount"))
      val count = count_span match {
        case NodeSeq.Empty => 1
        case span => span.text.toInt
      }
      
      val article_url = new ArticleURL(url = link, count = count)
      bookmarks += article_url
    }
  }

}

class ArticleURL(val url: String, val count: Int) {

  /** Process a single bookmark. */
  def process() {
    val task = new QueueableTask("/fetchurl")
    task += ("url" -> url)
    task.enqueue
  }

}