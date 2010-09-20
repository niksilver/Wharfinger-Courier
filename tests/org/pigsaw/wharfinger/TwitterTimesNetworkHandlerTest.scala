package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import java.io.{FileReader, StringReader}

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