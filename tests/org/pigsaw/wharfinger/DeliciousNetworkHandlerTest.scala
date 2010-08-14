package org.pigsaw.wharfinger

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import collection.mutable.ListBuffer
import java.io.StringReader
//import Preamble._
import xml.Node

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 09-Aug-2010
 * Time: 22:39:15
 * To change this template use File | Settings | File Templates.
 */

class DeliciousNetworkHandlerTest extends Spec with ShouldMatchers {

  describe("DeliciousNetworkHandler") {

    it("Should return a list of bookmarks") {
      val handler = new DeliciousNetworkHandler(new StringReader(Data.delicious_html)) {
        override def process(b: ArticleURL) {}
      }
      handler.parse
      handler.bookmarks.size should be === (10)
      val bookmark0 = handler.bookmarks(0)
      bookmark0.url should be ("http://www.leedsunited-mad.co.uk/news/tmnw/leeds_40_lincoln_by_sean_markey_aged_11_545231/index.shtml")
    }

    it("Should process each bookmark") {
      val processed_bookmarks = new ListBuffer[ArticleURL]()
      val handler = new DeliciousNetworkHandler(new StringReader(Data.delicious_html)) {
        override def process(bookmark: ArticleURL) {
          processed_bookmarks += bookmark
        }
      }
      handler.parse

      processed_bookmarks.size should be === (10)
      processed_bookmarks(0).url should be ("http://www.leedsunited-mad.co.uk/news/tmnw/leeds_40_lincoln_by_sean_markey_aged_11_545231/index.shtml")
      processed_bookmarks(9).url should be ("http://labs.slate.com/blog/2010/08/welcome-to-slate-labs/")
    }
  }
}