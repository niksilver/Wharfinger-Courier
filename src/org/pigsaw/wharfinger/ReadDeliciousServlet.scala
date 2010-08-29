package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 14-Aug-2010
 * Time: 23:28:59
 * To change this template use File | Settings | File Templates.
 */

class ReadDeliciousServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain")
    val handler = (new DeliciousNetworkHandler with ProcessReporter)
    handler.parse
    handler.process()

    trait ProcessReporter extends DeliciousNetworkHandler {
      override def process(a: DeliciousBookmark) {
        resp.getWriter.println("Queuing task to fetch " + a.url)
        resp.getWriter.println("  title: " + showCharacters(a.title))
        resp.getWriter.println("  citation: " + showCharacters(a.citation))
        super.process(a)

        def showCharacters(str: String): String = {
          str flatMap (c => "(" + c.toChar + ")=" + c.toInt + " ")
        }

      }
    }
  }
}