package org.pigsaw.wharfinger

import scala.xml._
import java.io.{StringReader, Reader}

case class RSSBookmark(val site: String, val url: String, val title: String, val description: String)

trait RSSBookmarkCollatorService {
  /** Get a reader of RSS XML from a given String. */
  def reader(str: String): Reader

  /** Get an RSS bookmark collator which takes an appropriate string for the reader. */
  def collator(str: String): RSSBookmarkCollator = new RSSBookmarkCollator(reader(str))

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
}

trait RSSXMLStringCollatorService extends RSSBookmarkCollatorService {
  def reader(xml: String) = new StringReader(xml)
}