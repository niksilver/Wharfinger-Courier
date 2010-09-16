package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import java.io.StringReader

/**
 * Test the thing that fetches and parses a the Twitter Times RSS feed
 */

class TwitterTimesNetworkHandlerTest extends Spec with ShouldMatchers {

  describe("TwitterTimesNetworkHandler") {
    it("Should return a list of bookmarks") {
      val handler = new TwitterTimesNetworkHandler(new StringReader(Data.twitter_times_rss))
      handler.parse

      handler.bookmarks.size should be === (30)

      val bookmark0 = handler.bookmarks(0)
      val bookmark1 = handler.bookmarks(1)
      val bookmark2 = handler.bookmarks(2)

      bookmark0 should be === ("http://www.guardian.co.uk/media/pda/2010/sep/15/seedcamp-week-2010")
      bookmark1 should be === ("http://www.narrowdesign.com/future")
      bookmark2 should be === ("http://theirquestionsanswered.wordpress.com")
    }
  }
}