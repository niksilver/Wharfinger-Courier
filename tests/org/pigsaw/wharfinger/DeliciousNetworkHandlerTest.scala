package org.pigsaw.wharfinger

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import Preamble._
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
    it("Should return a list of bookmarks pairs") {
      val handler = new DeliciousNetworkHandler {
        override def getHtml:Node = SimpleHtmlHandler.readFromString(Data.delicious_html)
      }
      handler.bookmarks.size should be === (10)
      (handler.bookmarks(0)).title should be ("Leeds 4-0 Lincoln (By Sean Markey, Aged 11) - LeedsUtdMAD")
    }
  }
}