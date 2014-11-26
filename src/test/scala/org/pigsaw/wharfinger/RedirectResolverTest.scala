package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers
import java.io.IOException

class RedirectResolverTest extends FunSpec with Matchers {

  describe("RedirectResolver") {

    it("Should allow URL redirects to be resolved") {
      val resolver = new RedirectResolver("http://bit.ly/9NQcyA")
      resolver.URL should be ("http://www.mobileuserexperience.com/?p=896")
    }

    it("Should throw an IOException if URL redirect cannot be resolved") {
      an [IOException] should be thrownBy {
        print("** URL is " + (new RedirectResolver("http://www.madeupdomain54321.com").URL))
      }
    }

    /* This will not work due to Java's security.
     *
    it("Should resolve URLs across HTTP(S) boundaries") {
      val resolver = new RedirectResolver("http://bit.ly/3hQYj")
      resolver.URL should be ("https://www.google.com")
    }
    */

  }
}