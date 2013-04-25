package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Test URLString and related
 */

class URLStringTest extends FunSpec with ShouldMatchers {

  describe("URLString") {
    it("Should separate a URL string") {
      val url = URLString("http://www.scala-lang.org/node/112")
      url.server should be ("http://www.scala-lang.org")
      url.path should be ("/node/112")
    }

    it("Should separate a URL string with caps in the domain") {
      val url = URLString("http://www.scala-LANG.org/node/112")
      url.server should be ("http://www.scala-LANG.org")
      url.path should be ("/node/112")
    }

    it("Should separate an https URL string") {
      val url = URLString("https://www.scala-lang.org/node/112")
      url.server should be ("https://www.scala-lang.org")
      url.path should be ("/node/112")
    }

    it("Should separate a URL string with a port") {
      val url = URLString("http://www.scala-lang.org:8080/node/112")
      url.server should be ("http://www.scala-lang.org:8080")
      url.path should be ("/node/112")
    }

    it("Should separate a URL string without a path") {
      val url = URLString("http://www.scala-lang.org")
      url.server should be ("http://www.scala-lang.org")
      url.path should be ("")
    }

    it("Should separate a URL string with a port but without a path") {
      val url = URLString("http://www.scala-lang.org:8080")
      url.server should be ("http://www.scala-lang.org:8080")
      url.path should be ("")
    }

    it("Should separate a URL string with just a / for a path") {
      val url = URLString("http://www.scala-lang.org/")
      url.server should be ("http://www.scala-lang.org")
      url.path should be ("/")
    }

    it("Should separate a URL string with a port and just a / for a path") {
      val url = URLString("http://www.scala-lang.org:80/")
      url.server should be ("http://www.scala-lang.org:80")
      url.path should be ("/")
    }

    it("Should separate a URL string with a query string") {
      val url = URLString("http://www.scala-lang.org/node/112?text=true")
      url.server should be ("http://www.scala-lang.org")
      url.path should be ("/node/112?text=true")
    }

    it("Should handle malformed strings") {
      val url = URLString("www.scala-lang.org/node/112?text=true")
      url.server should be ("")
    }

    it("Should be able to normalise a URL with caps in the server") {
      val url = URLString("http://www.scala-LANG.org/node/112")
      val url2 = url.normalise
      url2.server should be ("http://www.scala-lang.org")
      url2.path should be ("/node/112")
    }

    it("Should be able to normalise a URL with just a / for its path") {
      val url = URLString("http://www.scala-lang.org/")
      val url2 = url.normalise
      url2.server should be ("http://www.scala-lang.org")
      url2.path should be ("")
    }

    it("Should be able to convert back to a string") {
      val url = URLString("http://www.scala-LANG.org/node/112")
      url.toString should be ("http://www.scala-LANG.org/node/112")
    }

    it("Should be able to convert a bad URL back to the same string") {
      val url = URLString("www.scala-LANG.org/node/112")
      url.toString should be ("www.scala-LANG.org/node/112")
    }

    it("Should tell us if it's a bad URL string") {
      URLString("http://www.scala-LANG.org/node/112").isBad should be (false)
      URLString("www.scala-LANG.org/node/112").isBad should be (true)
    }

    it("Should allow construction with a single string") {
      val url = new URLString("http://www.scala-lang.org/node/112")
      url.server should be ("http://www.scala-lang.org")
      url.path should be ("/node/112")
    }

    it("Should enable filtering with a trait") {
      trait NonArticleFilter extends URLString {
        override def isBad = (super.isBad ||
                domain == "www.youtube.com")
      }
      val url_1 = new URLString("http://www.scala-lang.org/node/112") with NonArticleFilter
      val url_2 = new URLString("http://www.youtube.com/watch?v=HMGIbOGu8q0") with NonArticleFilter
      url_1.isBad should be (false)
      url_2.isBad should be (true)
    }

    it("Should detect the domain") {
      URLString("http://tu.be/123").domain should be ("tu.be")
      URLString("https://tu.be/123").domain should be ("tu.be")
      URLString("http://TU.be/123").domain should be ("tu.be")
      URLString("https://TU.be/123").domain should be ("tu.be")
      URLString("http://tu.be:80/123").domain should be ("tu.be")
      URLString("tu.be/123").domain should be ("")
    }
  }
}