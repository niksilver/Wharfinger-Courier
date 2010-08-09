package org.pigsaw.wharfinger

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import Preamble._
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
      val handler = new DeliciousNetworkHandler
      handler.bookmarks.size should be > (20)
      (handler.bookmarks(0)).title should be ("Yes I am Precious")
    }
  }
}