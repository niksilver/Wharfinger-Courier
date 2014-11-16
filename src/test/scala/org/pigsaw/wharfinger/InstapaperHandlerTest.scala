package org.pigsaw.wharfinger

import org.scalatest.Matchers
import org.scalatest.FunSpec

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 14-Aug-2010
 * Time: 23:43:57
 * To change this template use File | Settings | File Templates.
 */

class InstapaperHandlerTest extends FunSpec with Matchers {

  describe("InstapaperHandler") {

    val wsj_url = "http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html"
    val cif_america_url = "http://www.guardian.co.uk/commentisfree/cifamerica/2012/apr/03/sarah-palin-unreality-tv-show-today"

    it("Should create an appropriate URL") {
      val handler = new InstapaperHandler(wsj_url)
      handler.url should be ("http://www.instapaper.com/text?u=http%3A%2F%2Fonline.wsj.com%2Farticle%2FSB10001424052748703977004575393173432219064.html")
    }

    it("Should be able to find the content div") {
      val handler = new InstapaperHandler(cif_america_url)
      val Some(content) = handler.getContentDiv
      content.text.trim should include ("As far as I can tell")
    }

    it("Should get content by working around empty story div") {
      val handler = new InstapaperHandler(cif_america_url)
      val Some(content) = handler.getContentDiv
      content.text.trim should include ("Sarah Palin reads the newspapers")
    }

    it("Should should put content from workaround in a story div") {
      val handler = new InstapaperHandler(cif_america_url)
      val Some(content) = handler.getContentDiv
      content.toString should startWith ("""<div id="story">""")
    }

  }
}