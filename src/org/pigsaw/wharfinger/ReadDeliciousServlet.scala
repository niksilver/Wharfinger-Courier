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
      override def process(a: ArticleURL) {
        resp.getWriter.println("Queuing task to fetch " + a.url)
        super.process(a)
      }
    }
  }
}