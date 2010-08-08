package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 08-Aug-2010
 * Time: 15:53:47
 * To change this template use File | Settings | File Templates.
 */

class TestingServlet extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {

    req.getPathInfo match {
      case "/gethtml" => testGetHtml
      case "/redirectresolution" => testRedirectResolution
      case _ => testBasicOutput
    }

    def testBasicOutput: Unit = {
      resp.setContentType("text/plain")
      resp.getWriter.println("This is the testing servlet, basic test")
    }

    def testGetHtml: Unit = {
      resp.setContentType("text/plain")
      val html = SimpleHtmlHandler.readFromURL("http://www.google.com")
      val title = (html \\ "title").text
      resp.getWriter.println("Title of Google is '" + title + "'")      
    }

    def testRedirectResolution: Unit = {
      resp.setContentType("text/plain")
      val bitly_url = "http://bit.ly/9NQcyA"
      val resolver = new URLResolver(bitly_url)
      resp.getWriter.println("'" + bitly_url + "' resolves to '" + resolver.URL + "'")
      
    }

  }
}