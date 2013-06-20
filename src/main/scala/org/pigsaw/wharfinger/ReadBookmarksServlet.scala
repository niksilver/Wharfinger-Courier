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
      case "delicious" => new DeliciousNetworkHandler
      case "twitter-times" => new TwitterTimesNetworkHandler
      case service => new UnknownServiceHandler(service, log)
    }
    handler.bookmarks
    handler.bookmarksPendingFetch.foreach( b => {
      resp.getWriter.println("Saving for later fetching: " + b.url)
      val normalised_url = URLString(b.url).normalise.toString
      new BookmarkPendingFetch(normalised_url, b.title, b.getCitation).saveForLaterFetching
    })

    class UnknownServiceHandler(service: String, log: Logger) extends BookmarkServiceNetworkHandler[Nothing] {
      def bookmarks = {
        resp.getWriter.println("Unknown bookmark service: " + service)
        log.warning("Unknown bookmark service: " + service)
        Seq()
      }
      def bookmarksPendingFetch = Nil
    }
  }
}