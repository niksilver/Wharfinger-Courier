package org.pigsaw.wharfinger.servlets

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.util.logging.Logger
import org.pigsaw.wharfinger.{BookmarkCollator, BookmarkPendingFetch, URLString, TwitterTimesCollator}

/**
 * Read some bookmarks.
 */

class ReadBookmarksServlet extends ReadBookmarksServletShell with ServletLogger {
  val oLogger = Some(Logger.getLogger(this.getClass.getName))
  val oResp = None
}

trait ReadBookmarksServletShell extends HttpServlet {
  this: ServletLogger =>

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain")

    lazy val handler = getHandler(req.getPathInfo, this.withResponse(resp))
    handler.bookmarks
    handler.bookmarksPendingFetch map { b: BookmarkPendingFetch =>
      resp.getWriter.println("Saving for later fetching: " + b.url)
      val normalised_url = URLString(b.url).normalise.toString
      new BookmarkPendingFetch(normalised_url, b.title, b.getCitation).saveForLaterFetching()
    }
  }

  def getHandler(pathInfo: String, sLogger: ServletLogger) =
    pathInfo.tail.split('/')(0) match {
      case "twitter-times" => new TwitterTimesCollator
      case service => new UnknownServiceHandler(service, sLogger)
    }
}

class UnknownServiceHandler(service: String, logger: ServletLogger) extends BookmarkCollator[Nothing] {
  def bookmarks = {
    logger.warn("Unknown bookmark service: " + service)
    Seq()
  }
  def bookmarksPendingFetch = Nil
}

trait ServletLogger {
  outer: ServletLogger =>

  val oLogger: Option[Logger]
  val oResp: Option[HttpServletResponse]

  def warn(msg: String) {
    oResp map { r => r.getWriter.println(msg) }
    oLogger map { g => g.warning(msg)}
  }

  def withResponse(resp: HttpServletResponse): ServletLogger =
    new ServletLogger {
      val oLogger = outer.oLogger
      val oResp = Some(resp)
    }
}
