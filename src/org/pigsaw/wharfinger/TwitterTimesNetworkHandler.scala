package org.pigsaw.wharfinger

import scala.collection.mutable.ListBuffer
import java.io.{StringReader, Reader}
import Preamble._
import xml.{Elem, Node, XML}
import util.matching.Regex

/**
 * Read and parse the Twitter Times RSS feed
 */

class TwitterTimesNetworkHandler(val reader: Reader) extends BookmarkServiceNetworkHandler {

  val bookmarks = new ListBuffer[TwitterTimesBookmark]()

  def this() = this(new URLReader("http://twittertim.es/pigsaw/rss.xml", "UTF-8"))

  /**Parse the RSS to create the bookmarks.
   */
  def parse() {
    val rss = XML.load(reader)
    bookmarks ++= (rss \ "channel" \ "item") map { new TwitterTimesBookmark(_) }
  }

  /** Get all the bookmarks pending fetch (as JDOs).
   */
  def bookmarksPendingFetch =
    for (b <- bookmarks) yield new BookmarkPendingFetch(b.url, b.title, b.citation)
}

class TwitterTimesBookmark (item: Node) {

  val url = (item \ "link").text

  val title = (item \ "title").text

  val description = HTMLNode(new StringReader(item \ "description" text))

  private val tweets_html = (description \\ "div") containing
          (_ \ "span" findElementAttributeStartingWith ("a", "@href", "http://twitter.com/"))

  /** A mapping from Twitter usernames to tweets. */
  val tweets = for (div <- tweets_html) yield ( username(div) -> tweet(div) )

  val citation = "Tweeted by " + tweets(0)._1 + andOthersText(tweets.length - 1) + ": " +
    tweets(0)._2

  def andOthersText(num_others: Int) = num_others match {
    case 0 => ""
    case 1 => " and 1 other"
    case _ => " and " + num_others + " others"
  }

  /** To extract a username look for an A HREF to http://twitter.com/username.
   */
  def username(div: Node): String = ":(.|\\s)*".r replaceFirstIn (div.text.trim, "")

  /** Remove the username and ": " from the start of the tweet text.
   */
  def removeUsername(txt: String) = "^[^:]*:.".r replaceFirstIn (txt, "")

  /** Remove the timestamp at the end of the tweet text
   */
  def removeTimestamp(txt: String) = """(\d|\.|\s)*$""".r replaceFirstIn (txt , "")

  /** Like trim, but removes all kinds of whitespace, not just that which is \u0020
   *  and below.
   */
  def hardTrim(txt: String) = "^(\\s|\\u00A0)*".r replaceFirstIn ("(\\s|\\u00A0)*$".r replaceFirstIn(txt, ""), "")

  /**To extract a tweet take just the text, trim it, and drop
    *  any NBSP (char 160s) from the start.
   */
  def tweet(div: Node): String = hardTrim(removeUsername(removeTimestamp(div.text)))

  /** Remove a regular expression from a string.
   */
  def remove(txt: String, re: String): String = re.r replaceFirstIn (txt, "")
}