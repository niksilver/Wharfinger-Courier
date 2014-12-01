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

  describe("KickOffFetchArticleGetter") {

    it("Should fetch article if entirely new") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging {
        override def isPastArticle(b: BookmarkPendingFetch) = false
        override def tooManyFetchAttempts(b: BookmarkPendingFetch) = false
        override def shouldNotFollow(url: String) = false
      }
      getter.fetchableBookmark(bookmark0) should be (true)
    }

    it("Should not fetch article if used before") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging {
        override def isPastArticle(b: BookmarkPendingFetch) = true
        override def tooManyFetchAttempts(b: BookmarkPendingFetch) = false
      }
      getter.fetchableBookmark(bookmark0) should be (false)
    }

    it("Should not fetch article if too many fetch attempts") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging {
        override def isPastArticle(b: BookmarkPendingFetch) = false
        override def tooManyFetchAttempts(b: BookmarkPendingFetch) = true
      }
      getter.fetchableBookmark(bookmark0) should be (false)
    }

    it("Should not follow a Twitter status URL") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging
      getter.shouldNotFollow("https://twitter.com/blangry/status/539156600221863936") should be (true)
    }

    it("Should not follow a YouTube video URL") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging
      getter.shouldNotFollow("https://www.youtube.com/watch?v=qjbFc_XFVek") should be (true)
    }

    it("Should not follow a Vimeo video URL") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging
      getter.shouldNotFollow("http://vimeo.com/97012707") should be (true)
    }

    it("Should not follow a Vine video URL") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging
      getter.shouldNotFollow("https://vine.co/v/MZeH3Kx5hhe") should be (true)
    }

    it("Should follow ordinary URL") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds)
      getter.shouldNotFollow("http://scalamock.org/user-guide/mocking_style/") should be (false)
    }

    it("Should not fetch article if URL should not be followed") {

      val pwriter = new PrintWriter(new StringWriter)
      val ds = stub[DataService]

      val getter = new KickOffFetchArticleGetter(pwriter, ds) with NoLogging {
        override def isPastArticle(b: BookmarkPendingFetch) = false
        override def tooManyFetchAttempts(b: BookmarkPendingFetch) = false
        override def shouldNotFollow(url: String) = true
      }
      getter.fetchableBookmark(bookmark0) should be (false)
    }

  }
}
