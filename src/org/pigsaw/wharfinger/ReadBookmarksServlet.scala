package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.util.logging.Logger

/**
 * Read some bookmarks.
 */

class ReadBookmarksServlet extends HttpServlet {

  val log = Logger.getLogger(this.getClass.getName)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain")

    lazy val handler = req.getPathInfo.tail.split('/')(0) match {
      case "delicious" => (new DeliciousNetworkHandler with ProcessReporter)
      case service => new UnknownServiceHandler(service, log)
    }
    handler.parse
    handler.process()

    trait ProcessReporter extends DeliciousNetworkHandler {
      override def process(a: DeliciousBookmark) {
        resp.getWriter.println("Queuing task to fetch " + a.url)
        super.process(a)
      }
    }

    class UnknownServiceHandler(service: String, log: Logger) extends DeliciousNetworkHandler {
      override def parse = {}
      override def process = {
        resp.getWriter.println("Unknown bookmark service: " + service)
        log.warning("Unknown bookmark service: " + service)
      }
    }
  }
}