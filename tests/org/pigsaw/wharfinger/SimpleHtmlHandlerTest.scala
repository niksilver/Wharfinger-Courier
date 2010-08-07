package org.pigsaw.wharfinger

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import Preamble._
import java.io.IOException

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 07-Aug-2010
 * Time: 11:01:28
 * To change this template use File | Settings | File Templates.
 */

class SimpleHtmlHandlerTest extends Spec with ShouldMatchers {

  describe("SimpleHtmlHandler") {

    it("Should read HTML text") {
      val str = "<a>This is the first level<b>This is the second level</b></a>"
      val html = SimpleHtmlHandler.readFromString(str);
      val message = (html \\ "b").text
      message should be ("This is the second level")
    }

    it("Should be able to use 'having' to limit to nodes which contain certain other node sequences") {
      val html = SimpleHtmlHandler.readFromString(Data.instapaper_html)
      val nodes = html \\ "li" having (_ \\ "@accesskey")
      
      nodes should have length (3)

      nodes(0).label should be ("li")
      nodes(0).text should be ("Skip past navigation")
      nodes(1).label should be ("li")
      nodes(1).text should be ("Skip to navigation")
      nodes(2).label should be ("li")
    }

    it("Should read HTML from a URL") {
      val html = SimpleHtmlHandler.readFromURL("http://www.google.com")
      val title = (html \\ "title").text
      title should be ("Google")
    }
  }

  it("Should handle reading from a problematic URL") {
    def readURL = SimpleHtmlHandler.readFromURL("http://www.willnotresolve5432.com")
    evaluating(readURL) should produce [IOException]
  }
}