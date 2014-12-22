package org.pigsaw.wharfinger.servlets

import javax.servlet.http.{ HttpServletResponse, HttpServletRequest, HttpServlet }
import java.net.URLEncoder
import scala.collection.JavaConversions._
import javax.jdo.PersistenceManager
import com.google.appengine.api.taskqueue.QueueFactory
import com.google.appengine.api.taskqueue.TaskOptions.Builder._
import java.util.logging.Logger
import com.google.appengine.api.taskqueue.TaskOptions.Method
import xml.{ NodeSeq, Node }
import org.pigsaw.wharfinger._
import org.pigsaw.wharfinger.Preamble._

/**
 * Kick off fetching a pending article.
 */

class KickOffFetchArticleServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val ds = new GAEDataService(PMF.get.getPersistenceManager)
    val getter = new KickOffFetchArticleGetter(resp.getWriter, ds)
    resp.setContentType("text/plain")
    getter.doGetLogic
  }
}

class KickOffFetchArticleGetter(out: java.io.PrintWriter, ds: DataService) extends Logging {

  def doGetLogic {
    ds.persistAndClose {
      ds.bookmarksToFetch find fetchableBookmark map queueFetchBookmark
    }
    out.println("Done kick-off")

  }

  def tooManyFetchAttempts(bookmark: BookmarkPendingFetch): Boolean = (bookmark.getFetchAttempts >= 10)

  def isPastArticle(bookmark: BookmarkPendingFetch): Boolean = {
    val num_found = ds.countPastArticle(bookmark)
    num_found >= 1
  }
  
  /** URLs that shouldn't be followed as articles: YouTube, Twitter statuses, etc.
   */
  def shouldNotFollow(url: String) =
    URLTool.isTwitterStatus(url) ||
    URLTool.isYouTubeVideo(url) ||
    URLTool.isVimeoVideo(url) ||
    URLTool.isVineVideo(url)

  def fetchableBookmark(bookmark: BookmarkPendingFetch): Boolean = {
    val failure =
      if (isPastArticle(bookmark))        { Some("Have published this before") } else
      if (tooManyFetchAttempts(bookmark)) { Some("Too many fetch attempts")    } else
      if (shouldNotFollow(bookmark.url))  { Some("Will not follow this kind of URL") } else
      { None }
    failure match {
      case Some(reason) => rejectBookmark(bookmark, reason); false
      case None => true
    }
  }

  def rejectBookmark(bookmark: BookmarkPendingFetch, reason: String) {
    log.warning("Rejecting bookmark: " + bookmark.url)
    log.warning("Reason for rejection: " + reason)
    out.println("Rejecting bookmark: " + bookmark.url)
    out.println("Reason for rejection: " + reason)
    ds.delete(bookmark)
  }

  def queueFetchBookmark(bookmark: BookmarkPendingFetch) = {
    log.info("Queueing task to fetch bookmark: " + bookmark.url)
    out.println("Queueing task to fetch bookmark: " + bookmark.url)
    val queue = QueueFactory.getDefaultQueue
    val task = withUrl("/do-fetch-article").
      param("url", bookmark.url).
      param("title", bookmark.title).
      param("citation", bookmark.getCitation).
      method(Method.GET)
    queue.add(task)
    ds.markFetchAttempt(bookmark)
  }

}

/**
 * Business logic facade to the persistence layer.
 */
trait DataService {

  /** Do some work, persist the results, and close the connection.
   */
  def persistAndClose(block: =>Unit): Unit

  /**
   * How many times has this bookmark been a past article?
   */
  def countPastArticle(bookmark: BookmarkPendingFetch): Integer

  def delete(bookmark: BookmarkPendingFetch): Unit
  def bookmarksToFetch(): Seq[BookmarkPendingFetch]
  def markFetchAttempt(bookmark: BookmarkPendingFetch): Unit

}

class GAEDataService(pm: PersistenceManager) extends DataService {

  val log = Logger.getLogger(this.getClass.getName)

  def persistAndClose(block: =>Unit) {
    try { block }
    finally { if (!pm.isClosed) pm.close }
  }

  /**
   * How many times has this bookmark been a past article?
   */
  def countPastArticle(bookmark: BookmarkPendingFetch): Integer = {
    val query = pm.newQuery(classOf[PastArticle])
    query.setFilter("url == urlParam")
    query.declareParameters("String urlParam")
    val results = query.execute(bookmark.url).asInstanceOf[java.util.List[BookmarkPendingFetch]]
    results.size
  }

  def delete(bookmark: BookmarkPendingFetch) {
    pm.deletePersistent(bookmark)
  }

  def bookmarksToFetch(): Seq[BookmarkPendingFetch] = {
    log.info("Entering bookmarksToFetch")
    val query = pm.newQuery(classOf[BookmarkPendingFetch])
    query.setOrdering("fetchAttempts asc")
    query.setRange(0, 5)
    query.execute().asInstanceOf[java.util.List[BookmarkPendingFetch]]
  }

  def markFetchAttempt(bookmark: BookmarkPendingFetch) {
    bookmark.incrementFetchAttempts()
    pm.makePersistent(bookmark)
  }

}