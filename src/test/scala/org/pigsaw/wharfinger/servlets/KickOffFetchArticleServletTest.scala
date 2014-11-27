package org.pigsaw.wharfinger.servlets

import org.scalatest.FunSpec
import org.scalatest.Matchers

import org.pigsaw.wharfinger._

class KickOffFetchArticleServletTest extends FunSpec with Matchers {

  describe("KickOffFetchArticleServlet") {
    it("Should be able to instatiate the servlet in a test") {

      val servlet = new KickOffFetchArticleServlet
    }
  }
}
