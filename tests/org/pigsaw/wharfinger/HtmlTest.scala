package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import org.ccil.cowan.tagsoup.TagSoupFactoryAdapter

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 05-Aug-2010
 * Time: 22:26:34
 * To change this template use File | Settings | File Templates.
 */

class HtmlTest extends Spec with ShouldMatchers {

  describe("Tagsoup") {

    it("Should parse very simple HTML") {
      val str = "<html><title>This is my title</title><body>This is my body</body></html>"
      val reader = new java.io.StringReader(str)
      val parser = new TagSoupFactoryAdapter
      val html = parser load reader
      val title = (html \\ "title").text
      title should be ("This is my title")
    }

  }
}