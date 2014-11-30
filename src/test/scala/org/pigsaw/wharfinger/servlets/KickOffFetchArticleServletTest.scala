package org.pigsaw.wharfinger.servlets

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalamock.scalatest.MockFactory
import org.pigsaw.wharfinger._
import java.io.StringWriter
import java.io.PrintWriter
import java.util.logging.Logger

class KickOffFetchArticleGetterTest extends FunSpec with Matchers with MockFactory {

  val bookmark0 = new BookmarkPendingFetch("http://something", "My title", "My citation")
  
  trait NoConsoleLogging {
    this: { val log: Logger } =>
    log.setUseParentHandlers(false)
  }

  describe("KickOffFetchArticleGetter") {

    it("Should fetch article if entirely new") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) {
        override def isPastArticle(b: BookmarkPendingFetch) = false
        override def tooManyFetchAttempts(b: BookmarkPendingFetch) = false
      }
      getter.fetchableBookmark(bookmark0) should be (true)
    }

    it("Should not fetch article if used before") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) {
        override def isPastArticle(b: BookmarkPendingFetch) = true
        override def tooManyFetchAttempts(b: BookmarkPendingFetch) = false
      }
      getter.fetchableBookmark(bookmark0) should be (false)
    }

    it("Should not fetch article if too many fetch attempts") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) {
        override def isPastArticle(b: BookmarkPendingFetch) = false
        override def tooManyFetchAttempts(b: BookmarkPendingFetch) = true
      }
      getter.fetchableBookmark(bookmark0) should be (false)
    }

  }
}
