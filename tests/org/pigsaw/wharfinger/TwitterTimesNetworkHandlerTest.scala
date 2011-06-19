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

      bookmark0.url should be === ("http://www.chicagotribune.com")
      bookmark1.url should be === ("http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news")
      bookmark2.url should be === ("http://www.guardian.co.uk/law/butterworth-and-bowcott-on-law/2011/jun/17/internet-freedom-matter-un")
    }
  }
}

class TwitterTimesBookmarkTest extends Spec with ShouldMatchers {

  describe("TwitterTimesBookmark") {
    it("Should extract the URL") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_nieman_lab_item)
      bookmark.url should be === ("http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news")
    }

    it("Should extract the title") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_nieman_lab_item)
      bookmark.title should be === ("Who clicks more on local news, New York or Omaha? Surprising data from the FCC on local online news  \u00bb  Nieman Journalism Lab \u00bb Pushing to the Future of Journalism")
    }

    it("Should have a description which is XML") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_nieman_lab_item)
      bookmark.description.getClass should be (classOf[Elem])
    }

    it("Should list friends and their tweets") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_nieman_lab_item)
      bookmark.tweets.length should be === (6)

      bookmark.tweets(0)(0) should be ("@steverubel")
      bookmark.tweets(1)(0) should be ("@Digidave")
      bookmark.tweets(2)(0) should be ("@mediagazer")
      bookmark.tweets(3)(0) should be ("@NiemanLab")
      bookmark.tweets(4)(0) should be ("@Chanders")
      bookmark.tweets(5)(0) should be ("@NiemanLab")

      bookmark.tweets(0)(1) should startWith ("Who clicks more on local news")
      bookmark.tweets(3)(1) should startWith ("What makes local news consumption")
    }

    it("Should make a citation") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_nieman_lab_item)
      bookmark.citation should startWith ("Tweeted by @steverubel and 5 others: Who clicks more on local news")
    }

    it("Should handle Twitter username links") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_item_with_username_link)
      bookmark.citation.last.toInt should be ('n'.toInt)
      bookmark.citation should be ("Tweeted by @SiobhainB and 2 others: Internet freedom 'is a matter for UN' | Owen Bowcott http://t.co/lM0xZly via @guardian")
    }

    it("Should make a grammatical citation with '1 other' tweeter") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_nieman_lab_item_with_2_tweeters)
      bookmark.citation should startWith ("Tweeted by @steverubel and 1 other: Who clicks more on local news")
    }

    it("Should make a grammatical citation with no other tweeters") {
      val bookmark = new TwitterTimesBookmark(Data.twitter_times_nieman_lab_item_with_1_tweeter)
      bookmark.citation should startWith ("Tweeted by @steverubel: Who clicks more on local news")
    }
  }
}