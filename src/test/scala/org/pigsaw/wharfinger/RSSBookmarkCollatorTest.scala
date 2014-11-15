package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers
import java.io.StringReader

/**
 * To change this template use File | Settings | File Templates.
 */
class RSSBookmarkCollatorTest extends FunSpec with Matchers {
  describe("RSSBookmarkCollator") {

    it ("Should return bookmarks") {
      val xml = Data.pando_daily_rss
      val service = new RSSXMLStringCollatorService {}
      val collator = service.collator(xml)
      val bookmarks: Seq[RSSBookmark] = collator.bookmarks

      bookmarks(0).site should be ("PandoDaily")
      bookmarks(0).url should be ("http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f29c9da/sc/1/l/0Lpandodaily0N0C20A130C0A70C250Cwhat0Ethe0Ehell0Eis0Ea0Epatent0Etroll0Eanyway0C/story01.htm")
      bookmarks(0).title should be ("What the hell is a “patent troll” anyway?")
      bookmarks(0).description should startWith ("What the hell is a patent troll anyways? And how")

      bookmarks(1).site should be ("PandoDaily")
      bookmarks(1).url should be ("http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f2934c1/sc/5/l/0Lpandodaily0N0C20A130C0A70C250Cdrawbridge0Enow0Eallows0Emobile0Emarketers0Eto0Etrack0Eusers0Eanonymous0Eacross0Edevices0C/story01.htm")
      bookmarks(1).title should startWith ("Drawbridge now allows")
      bookmarks(1).description should startWith ("The conversation around mobile these days")

      bookmarks(2).site should be ("PandoDaily")
      bookmarks(2).url should be ("http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f28ff81/sc/21/l/0Lpandodaily0N0C20A130C0A70C250Cbaby0Esteps0Emayers0Eacquistion0Espree0Edoesnt0Eturn0Eyahoo0Earound0Ebut0Eit0Estarts0Eto0Esolve0Ecore0Eproblems0C/story01.htm")
      bookmarks(2).title should startWith ("Baby steps: Mayer’s acquistion spree")
      bookmarks(2).description should startWith ("At one point during the long, painful, multi-year talks")
    }
  }
}
