package org.pigsaw.wharfinger.servlets

import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServlet
import java.util.logging.{Logger => JavaLogger}
import org.pigsaw.wharfinger._
import scala.Some

/**
 * Read some bookmarks.
 */

class ReadBookmarksServlet extends ReadBookmarksServletShell with CombiLogger {
  val oLogger = Some(JavaLogger.getLogger(this.getClass.getName))
  val oResp = None
}

trait ReadBookmarksServletShell extends HttpServlet {
  this: CombiLogger =>

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain")

    val cLogger = this.withResponse(resp)
    val handler = getHandler(req.getPathInfo, cLogger)
    handler.bookmarksPendingFetch map { b: BookmarkPendingFetch =>
      cLogger.println("Saving for later fetching: " + b.url)
      val normalised_url = URLString(b.url).normalise.toString
      new BookmarkPendingFetch(normalised_url, b.title, b.getCitation).saveForLaterFetching()
    }
  }

  /**
   * Returns the correct handler accourding to the request details.
   * @param pathInfo  The path info of the request, without the leading `/read`.
   *                  For example, if the request was a GET of `/read/twitter-times`
   *                  then this parameter will come in as `/twitter-times`
   * @param sLogger  The [[org.pigsaw.wharfinger.CombiLogger]] for logging,
   *                 which will default to this object if omitted.
   */
  def getHandler(pathInfo: String, sLogger: CombiLogger = this) =
    pathInfo.tail.split('/')(0) match {
      case "twitter-times" => new TwitterTimesCollator
      case service => new UnknownServiceHandler(service, sLogger)
    }
}

class UnknownServiceHandler(service: String, logger: CombiLogger) extends BookmarkCollator[Nothing] {
  def bookmarks = {
    logger.warn("Unknown bookmark service: " + service)
    logger.println("Unknown bookmark service: " + service)
    Seq()
  }
  def bookmarksPendingFetch = Nil
}
