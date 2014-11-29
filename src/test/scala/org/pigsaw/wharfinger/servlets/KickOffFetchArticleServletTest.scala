package org.pigsaw.wharfinger.servlets

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalamock.scalatest.MockFactory
import org.pigsaw.wharfinger._
import java.io.StringWriter
import java.io.PrintWriter

class KickOffFetchArticleGetterTest extends FunSpec with Matchers with MockFactory {

  describe("KickOffFetchArticleGetter") {
    it("Should be able to instatiate the servlet in a test") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = mock[DataService]
      
      val getter = new KickOffFetchArticleGetter(pwriter, ds)
    }
  }
}
