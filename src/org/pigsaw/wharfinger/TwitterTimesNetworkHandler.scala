package org.pigsaw.wharfinger

import scala.collection.mutable.ListBuffer
import java.io.{StringReader, Reader}
import Preamble._
import xml.{Elem, Node, XML}

/**
 * Read and parse the Twitter Times RSS feed
 */

class TwitterTimesNetworkHandler(val reader: Reader) extends BookmarkServiceNetworkHandler {

  val bookmarks = new ListBuffer[TwitterTimesBookmark]()

  def this() = this(new URLReader("http://twittertim.es/pigsaw/rss.xml", "UTF-8"))

  /** Parse the RSS to create the bookmarks.
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

  private val space = """[\s\u00A0]+"""
  private val timestamp = """[\d\.]+\s+[\d\.]+"""
  private val username = """([^\s\u00A0]+)"""
  private val message = """([^\s\u00A0].*[^\s\u00A0])"""

  val tweet = (space + username + ":" + space + message + space + timestamp + space).r

  def splitTweet(str: String) =
    tweet.unapplySeq(str).flatMap(mtch => Some( mtch(0), mtch(1) ))

  /** A mapping from Twitter usernames to tweets. */
  val tweets = tweets_html.
    map(_.text).
    map(splitTweet(_)).
    filter(_.isDefined).
    flatten

  val citation = tweets match {
    case Nil => "Could not recognise tweets"
    case _ => "Tweeted by " + tweets(0)._1 + andOthersText(tweets.length - 1) + ": " + tweets(0)._2
  }

  def andOthersText(num_others: Int) = num_others match {
    case 0 => ""
    case 1 => " and 1 other"
    case _ => " and " + num_others + " others"
  }

}