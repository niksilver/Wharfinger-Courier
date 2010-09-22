package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import java.io.{FileReader}
import xml.Elem

/**
 * Test the thing that fetches and parses a the Twitter Times RSS feed
 */

class TwitterTimesNetworkHandlerTest extends Spec with ShouldMatchers {

  describe("TwitterTimesNetworkHandler") {
    it("Should return a list of bookmarks") {
      val handler = new TwitterTimesNetworkHandler(new FileReader("tests/org/pigsaw/wharfinger/twitter-times-rss.xml"))
      handler.parse

      handler.bookmarks.size should be === (30)

      val bookmark0 = handler.bookmarks(0)
      val bookmark1 = handler.bookmarks(1)
      val bookmark2 = handler.bookmarks(2)

      bookmark0.url should be === ("http://www.guardian.co.uk/media/audio/2010/sep/20/ken-bruce-popmaster")
      bookmark1.url should be === ("http://behindthecurtain.us/2010/06/12/my-first-week-with-the-iphone")
      bookmark2.url should be === ("http://www.ecorazzi.com/2010/09/17/president-bill-clinton-confirms-hes-experimenting-with-vegan-diet")
    }
  }
}

class TwitterTimesBookmarkTest extends Spec with ShouldMatchers {

  describe("TwitterTimesBookmark") {
    it("Should extract the URL") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_ken_bruce_item)
      bookmark.url should be === ("http://www.guardian.co.uk/media/audio/2010/sep/20/ken-bruce-popmaster")
    }

    it("Should have a description which is XML") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_ken_bruce_item)
      bookmark.description.getClass should be (classOf[Elem])
    }

    it("Should list friends and their tweets") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_bbc_item)
      bookmark.tweets.length should be === (6)

      bookmark.tweets(0)._1 should be ("JR0cket")
      bookmark.tweets(1)._1 should be ("paulbradshaw")
      bookmark.tweets(2)._1 should be ("markrock")
      bookmark.tweets(3)._1 should be ("willperrin")
      bookmark.tweets(4)._1 should be ("billt")
      bookmark.tweets(5)._1 should be ("ruskin147")

      bookmark.tweets(0)._2 should include ("RT @david_colquhoun The BBC Trust")
      bookmark.tweets(3)._2 should include ("RT @paulbradshaw: RT @david_colquhoun")
    }
  }
}