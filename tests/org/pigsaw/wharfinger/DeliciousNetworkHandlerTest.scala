package org.pigsaw.wharfinger

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import collection.mutable.ListBuffer
import java.io.StringReader

//import Preamble._

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 09-Aug-2010
 * Time: 22:39:15
 * To change this template use File | Settings | File Templates.
 */

class DeliciousNetworkHandlerTest extends Spec with ShouldMatchers {

  describe("DeliciousNetworkHandler") {

    it("Should return a list of bookmarks") {
      val handler = new DeliciousNetworkHandler(new StringReader(Data.delicious_html))
      handler.parse

      handler.bookmarks.size should be === (10)

      val bookmark0 = handler.bookmarks(0)
      val bookmark1 = handler.bookmarks(1)
      val bookmark2 = handler.bookmarks(2)

      bookmark0.url should be ("http://www.leedsunited-mad.co.uk/news/tmnw/leeds_40_lincoln_by_sean_markey_aged_11_545231/index.shtml")
      bookmark0.popularity should be (1)
      bookmark0.username should be ("currybet")
      bookmark0.description should be ("Very passable match report from an 11 year old :-)")

      bookmark2.url should be ("http://www.journalism.co.uk/young-journalists/?p=1094")
      bookmark2.popularity should be (8)

    }

    it("Should handle bookmark processing with full data") {
      val bookmark_div = HtmlNode(new StringReader(Data.delicious_bookmark_div_with_full_data))
      val bookmark = DeliciousNetworkHandler.makeBookmark(bookmark_div)
      bookmark.url should be ("http://www.guardian.co.uk/news/datablog/2010/aug/10/government-data-information-architecture")
      bookmark.popularity should be (13)
      bookmark.username should be ("currybet")
      bookmark.description should be ("\"The Guardian's Information Architect, Martin Belam, provides a glimpse behind the scenes at the design process of our World Government Data store.\"")
    }

    it("Should handle bookmark processing with partial data") {
      val bookmark_div = HtmlNode(new StringReader(Data.delicious_bookmark_div_with_partial_data))
      val bookmark = DeliciousNetworkHandler.makeBookmark(bookmark_div)

      bookmark.url should be ("http://www.sciencedirect.com/science?_ob=ArticleURL&_udi=B6V84-4CS4KXX-2&_user=142623&_coverDate=10%2F31%2F2004&_rdoc=1&_fmt=high&_orig=search&_sort=d&_docanchor=&view=c&_acct=C000000333&_version=1&_urlVersion=0&_userid=142623&md5=28edbadbb8d08ff3c150330060b33661#toc1")
      bookmark.popularity should be (1)
      bookmark.username should be ("cshirky")
      bookmark.description should be (null)
    }

    it("Should process selectively") {
      val processed = new ListBuffer[DeliciousBookmark]()
      val handler = new DeliciousNetworkHandler(new StringReader(Data.delicious_html)) {
        override def process(a: DeliciousBookmark) {
          processed += a.asInstanceOf[DeliciousBookmark]
        }
      }
      handler.parse
      handler.process(bookmark => bookmark.popularity > 1)

      processed.size should be (5)
      processed(0).popularity should be (8)
      processed(1).popularity should be (11)
      processed(2).popularity should be (2)
      processed(3).popularity should be (78)
      processed(4).popularity should be (16)
    }

    it("Should make a citation with count 1") {
      val bookmark = new DeliciousBookmark(
        url = "some://thing", popularity = 1, username = "bobby", description = "I like this")
      bookmark.citation should be ("Bookmarked by bobby: I like this")
    }
  }
}