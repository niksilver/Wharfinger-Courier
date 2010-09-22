package org.pigsaw.wharfinger

import scala.collection.mutable.ListBuffer
import java.io.{StringReader, Reader}
import Preamble._
import xml.{Elem, Node, XML}

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
    bookmarks ++= (rss \ "channel" \ "item") map { new TwitterTimesBookmark(_) }
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

class TwitterTimesBookmark (item: Node) {
  val url = (item \ "link").text
  val description = HTMLNode(new StringReader(item \ "description" text))
  private val tweets_html = (description \\ "div") containing
          (_ \ "span" findElementAttributeStartingWith ("a", "@href", "http://twitter.com/"))
  val tweets = for (div <- tweets_html) yield ( username(div) -> tweet(div) )

  def username(div: Node): String = ((div \ "span")(0) \ "a" \ "@href" text) split '/' last
  def tweet(div: Node): String =  { (div.child) filterNot (_.isInstanceOf[Elem]) mkString }
}