package org.pigsaw.wharfinger

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import collection.mutable.ListBuffer
import java.io.StringReader
//import Preamble._
import xml.Node

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
      bookmark0.count should be (1)

      bookmark2.url should be ("http://www.journalism.co.uk/young-journalists/?p=1094")
      bookmark2.count should be (8)

    }

  }
}