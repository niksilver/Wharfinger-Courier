package org.pigsaw.wharfinger.servlets

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

import org.pigsaw.wharfinger._

/**
 * To change this template use File | Settings | File Templates.
 */
class ReadBookmarksServletTest extends FunSpec with ShouldMatchers {

  describe("ReadBookmarksServlet") {
    it("Should select the right handler for Twitter Times") {

      val servlet = new ReadBookmarksServlet
      // Should proceed without exception
      servlet.getHandler("/twitter-times").asInstanceOf[TwitterTimesCollator]
    }

    it("Should select right handler for an RSS feed") {

      val servlet = new ReadBookmarksServlet
      // Should proceed without exception
      servlet.getHandler("/rss-feed").asInstanceOf[RSSBookmarkCollatorService#RSSBookmarkCollator]
    }
  }
}
