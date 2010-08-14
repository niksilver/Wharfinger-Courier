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
    val bookmarks_div =  html findElementAttributeSubstring ("div", "@class", "bookmark ") //html \\ "div" containing (_ \ "@class" filter (_.text contains "bookmark "))
    for (bookmark_div <- bookmarks_div) {
      val a_elt = bookmark_div findElementAttributeSubstring ("a", "@class", "taggedlink")// \\ "a" containing (_ \ "@class" filter (_.text contains "taggedlink"))
      val link = (a_elt \ "@href").text

      val count_span = bookmark_div findElementAttributeText ("span", "@class", "delNavCount") // \\ "span" containing (_ \ "@class" filter (_.text contains "delNavCount"))
      val count = count_span match {
        case NodeSeq.Empty => 1
        case span => span.text.toInt
      }

      val username_a_elt = bookmark_div findElementAttributeText ("a", "@class", "user user-tag")
      // The href will be href="/currybet",
      // so we need to lose the / to get the username
      val username = (username_a_elt \ "@href").text.tail
      
      val article_url = new ArticleURL(link, count, username)
      bookmarks += article_url
    }
  }

}

class ArticleURL(val url: String, val count: Int, val username: String) {

  /** Process a single bookmark. */
  def process() {
    val task = new QueueableTask("/fetchurl")
    task += ("url" -> url)
    task.enqueue
  }

}