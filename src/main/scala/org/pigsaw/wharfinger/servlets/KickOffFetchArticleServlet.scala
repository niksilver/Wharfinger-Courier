package org.pigsaw.wharfinger.servlets

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.collection.JavaConversions._
import javax.jdo.PersistenceManager
import com.google.appengine.api.taskqueue.QueueFactory
import com.google.appengine.api.taskqueue.TaskOptions.Builder._
import java.util.logging.Logger
import com.google.appengine.api.taskqueue.TaskOptions.Method
import xml.{NodeSeq, Node}
import org.pigsaw.wharfinger._
import org.pigsaw.wharfinger.Preamble._

/**
 * Kick off fetching a pending article.
 */

class KickOffFetchArticleServlet extends HttpServlet with Transaction {

  val log = Logger.getLogger(this.getClass.getName)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/plain")
    val pm: PersistenceManager = PMF.get.getPersistenceManager
    persistAndClose(pm) {
      bookmarksToFetch() find fetchableBookmark map queueFetchBookmark
    }
    println("Done kick-off")

    def println(s: String) { resp.getWriter.println(s) }
    def print(s: String) { resp.getWriter.print(s) }

    def shouldNotFetch(bookmark: BookmarkPendingFetch): Boolean =
      tooManyFetchAttempts(bookmark) || isPastArticle(bookmark)

    def tooManyFetchAttempts(bookmark: BookmarkPendingFetch): Boolean = (bookmark.getFetchAttempts >= 10)

    def isPastArticle(bookmark: BookmarkPendingFetch): Boolean = {
      log.info("Entering isPastArticle")
      val query = pm.newQuery(classOf[PastArticle])
      query.setFilter("url == urlParam")
      query.declareParameters("String urlParam")
      val results = query.execute(bookmark.url).asInstanceOf[java.util.List[BookmarkPendingFetch]]
      val num_found = results.size
      num_found >= 1
    }

    def fetchableBookmark(bookmark: BookmarkPendingFetch): Boolean = {
      if (isPastArticle(bookmark)) {
        rejectBookmark(bookmark, "Have published this before"); false
      } else if (tooManyFetchAttempts(bookmark)) {
        rejectBookmark(bookmark, "Too many fetch attempts"); false
      } else
        true
    }

    def rejectBookmark(bookmark: BookmarkPendingFetch, reason: String) {
      log.warning("Rejecting bookmark: " + bookmark.url)
      log.warning("Reason for rejection: " + reason)
      println("Rejecting bookmark: " + bookmark.url)
      println("Reason for rejection: " + reason)
      pm.deletePersistent(bookmark)
    }

    def bookmarksToFetch(): Seq[BookmarkPendingFetch] = {
      log.info("Entering bookmarksToFetch")
      val query = pm.newQuery(classOf[BookmarkPendingFetch])
      query.setOrdering("fetchAttempts asc")
      query.setRange(0, 5)
      query.execute().asInstanceOf[java.util.List[BookmarkPendingFetch]]
    }

    def queueFetchBookmark(bookmark: BookmarkPendingFetch) = {
      log.info("Queueing task to fetch bookmark: " + bookmark.url)
      println("Queueing task to fetch bookmark: " + bookmark.url)
      val queue = QueueFactory.getDefaultQueue
      val task = withUrl("/do-fetch-article").
        param("url", bookmark.url).
        param("title", bookmark.title).
        param("citation", bookmark.getCitation).
        method(Method.GET)
      queue.add(task)
      markFetchAttempt()

      def markFetchAttempt() {
        log.info("Entering markFetchAttempt")
        bookmark.incrementFetchAttempts()
        pm.makePersistent(bookmark)
      }
    }

  }
}




