package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

/**
 * Test URLString and related
 */

class URLStringTest extends Spec with ShouldMatchers {

  describe("URLString") {
    it("Should separate a URL string") {
      val url = URLString("http://www.scala-lang.org/node/112")
      url.server should be ("http://www.scala-lang.org")
      url.path should be ("/node/112")
    }
  }
}