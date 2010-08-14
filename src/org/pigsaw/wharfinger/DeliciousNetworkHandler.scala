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
  
  def parse() {
    val html = HtmlNode(reader)
    val bookmarks_div =  html \\ "div" having (_ \ "@class" filter (_.text contains "bookmark "))
    for (bookmark <- bookmarks_div) {
      val link = bookmark \\ "a" having (_ \ "@class" filter (_.text contains "taggedlink"))
      val article_url = new ArticleURL((link \ "@href").text)
      bookmarks += article_url
      process(article_url)
    }
  }

  /** Process a single bookmark. */
  def process(bookmark: ArticleURL) {
    val task = new QueueableTask("/fetchurl")
    task += ("url" -> bookmark.url)
    task.enqueue
  }
}

class ArticleURL(val url: String)