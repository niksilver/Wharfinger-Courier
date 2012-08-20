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

  def this() = this(new URLReader("http://tweetedtimes.com/pigsaw/rss.xml", "UTF-8"))

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

  private val tweets_html = description findElementAttributeText
    ("div", "@style", "margin: 0px 0px 0px 48px")

  private val space = """[\s\u00A0]+"""
  private val timestamp = """[\d\.]+\s+[\d\.]+"""
  private val username = """([^\s\u00A0]+)"""
  private val message = """([^\s\u00A0].*[^\s\u00A0])"""

  // <div style='float: left; 32px; height: 32px;'>
  //   <a href='http://twitter.com/steverubel'>
  //     <img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/1229138456/image_normal.jpg' alt='@steverubel on Twitter'/>
  //   </a>
  // </div>[space]
  // <div style='margin: 0px 0px 0px 48px'>[space]
  //   <div>[space]
  //     <a href='http://twitter.com/steverubel' style='color:#093D72; text-decoration: none; font-weight: bold;'>
  //       @steverubel:
  //     </a>
  //     [space]Who clicks more on local news, New York or Omaha?  Surprising data from the FCC on local online news
  //     <a href="http://j.mp/mvNAS4" rel="nofollow">http://j.mp/mvNAS4</a>[space]
  //   </div>[space]
  //   <div>[space]
  //   <a href='http://twitter.com/steverubel/status/81648445072474112' style='color:#666666; font-size:11px; text-decoration: none;'>
  //     17.06.2011 02.04.40
  //   </a>[space]
  //   <a href='http://twitter.com'>
  //     <img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/>
  //   </a>
  val tweet = (space + username + ":" + space + message + space + timestamp + space).r

  // Split a tweet into Some(List(username, message)) or None
  def splitTweet(str: String) = tweet.unapplySeq(str)

  /** A list of List(username, message) */
  val tweets = tweets_html.
    map(_.text).
    map(splitTweet(_)).
    filter(_.isDefined).
    flatten

  val citation = tweets match {
    case Nil => "Could not recognise tweets"
    case _ => "Tweeted by " + tweets(0)(0) + andOthersText(tweets.length - 1) + ": " + tweets(0)(1)
  }

  def andOthersText(num_others: Int) = num_others match {
    case 0 => ""
    case 1 => " and 1 other"
    case _ => " and " + num_others + " others"
  }

}