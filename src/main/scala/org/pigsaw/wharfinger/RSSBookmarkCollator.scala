package org.pigsaw.wharfinger

import scala.xml._
import java.io.Reader

/**
 * To change this template use File | Settings | File Templates.
 */
class RSSBookmarkCollator(val reader: Reader) extends BookmarkCollator[RSSBookmark] {

  val xml = XML.load(reader)

  def bookmarksPendingFetch: Seq[BookmarkPendingFetch] = Nil

  def bookmarks: Seq[RSSBookmark] = {
    val site = (xml \\ "channel" \ "title").text
    for (
      item <- (xml \\ "item");
      url = (item \ "link").text;
      title = (item \ "title").text;
      description = (item \ "description").text
    ) yield RSSBookmark(site, url, title, description)
  }
}

case class RSSBookmark(val site: String, val url: String, val title: String, val description: String)