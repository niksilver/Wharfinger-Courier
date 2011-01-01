package org.pigsaw.wharfinger

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import scala.util.parsing.json._

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 14-Aug-2010
 * Time: 23:43:57
 * To change this template use File | Settings | File Templates.
 */

class ClnMeHandlerTest extends Spec with ShouldMatchers {

  describe("ClnMeHandler") {

    it("Should create an appropriate URL") {
      val handler = new ClnMeHandler("http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html")
      handler.url should be ("http://cln.me/clean.json?url=http%3A%2F%2Fonline.wsj.com%2Farticle%2FSB10001424052748703977004575393173432219064.html")
    }

    it("Should be able to read JSON text") {
      val handler = new ClnMeHandler("http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html")
      val text = handler.getJSONText
      text should include ("""videos":[],"cleanHtml":"<div """)
    }

    it("Should be able to find the clean HTML as text") {
      val handler = new ClnMeHandler("http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html")
      val text = handler.getCleanHTMLAsText
      text should include ("more than 100 tracking tools")
    }

    it("Should be able to find the clean HTML") {
      val handler = new ClnMeHandler("http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html")
      val Some(content) = handler.getCleanHTML
      val h1 = content \\ "h1"
      h1.text should be ("Personal Information Exposed Via Biggest U.S. Websites")
    }

    it("Should handle an error") {
      val handler = new ClnMeHandler("rubbish URL")
      val content = handler.getCleanHTML
      content should be (None)
    }

  }
}