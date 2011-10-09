package org.pigsaw.wharfinger

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 14-Aug-2010
 * Time: 23:43:57
 * To change this template use File | Settings | File Templates.
 */

class InstapaperHandlerTest extends Spec with ShouldMatchers {

  describe("InstapaperHandler") {

    it("Should create an appropriate URL") {
      val handler = new InstapaperHandler("http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html")
      handler.url should be ("http://www.instapaper.com/text?u=http%3A%2F%2Fonline.wsj.com%2Farticle%2FSB10001424052748703977004575393173432219064.html")
    }

    it("Should be able to find the content div") {
      val handler = new InstapaperHandler("http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html")
      val Some(content) = handler.getContentDiv()
      val h3 = content \\ "h3"
      h3.text should startWith ("By JULIA")
    }
  }
}