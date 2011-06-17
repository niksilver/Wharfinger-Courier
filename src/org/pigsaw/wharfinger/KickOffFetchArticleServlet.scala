package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.collection.JavaConversions._
import Preamble._
import javax.jdo.PersistenceManager
import com.google.appengine.api.labs.taskqueue.QueueFactory
import com.google.appengine.api.labs.taskqueue.TaskOptions.Builder._
import java.util.logging.Logger
import com.google.appengine.api.labs.taskqueue.TaskOptions.Method
import io.Source
import util.parsing.json.{JSONObject, JSON, JSONType}
import xml.{NodeSeq, Node}

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
      val task = url("/do-fetch-article").
        param("url", bookmark.url).
        param("title", bookmark.title).
        param("citation", bookmark.getCitation).
        method(Method.GET)
      queue.add(task)
      markFetchAttempt()

      def markFetchAttempt() {
        log.info("Entering markFetchAttempt")
        bookmark.incrementFetchAttempts
        pm.makePersistent(bookmark)
      }
    }

  }
}

class DoFetchArticleServlet extends HttpServlet with Transaction {

  val log = Logger.getLogger(this.getClass.getName)

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = doGet(req, resp)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain")
    val url = req.getParameter("url")
    val title = req.getParameter("title")
    val citation = req.getParameter("citation")
    if (tooManyRetries)
      findAndDeleteBookmark
    else
      fetchArticle

    def tooManyRetries: Boolean = {
      val retries_str = req.getHeader("X-AppEngine-TaskRetryCount")
      log.warning("TaskRetryCount is '" + retries_str + "'")
      retries_str match {
        case null => false
        case "" => false
        case _ => retries_str.toInt > 10
      }
    }

    def findAndDeleteBookmark {
      log.warning("Too many retries. Deleting bookmark: " + url)
      val pm = PMF.get.getPersistenceManager
      persistAndClose(pm) {
        deleteBookmark(pm)
      }
    }

    def fetchArticle {
      val handler = new InstapaperHandler(url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => recordArticle(content_div)
        case None => {}
      }
    }

    def vizLog(s: String) = println(s)
    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)

    def recordArticle(content_div: Node) {
      val pm: PersistenceManager = PMF.get.getPersistenceManager
      println("Got article to persist: " + url)
      persistAndClose(pm) {
        pm.makePersistent(new Article(url,
          citation.escapeForHTML,
          title.escapeForHTML,
          content_div.imagesToText.escapeForHTML.toString))
        pm.makePersistent(new PastArticle(url))
        deleteBookmark(pm)
      }
    }

    def deleteBookmark(pm: PersistenceManager) {
      val bookmark = pm.getObjectById(classOf[BookmarkPendingFetch], url)
      println("Deleting bookmark: " + bookmark)
      pm.deletePersistent(bookmark)
    }
  }
}

class InstapaperHandler(article_url: String) {
  val log = Logger.getLogger(this.getClass.getName)
  val url: String = "http://www.instapaper.com/text?u=" +
          URLEncoder.encode(article_url, "UTF-8")

  def getContentDiv(): Option[Node] = {
    try {
      val html = HTMLNode(new URLReader(url, "UTF-8"))
      html findDivWithId "story"
    }
    catch {
      case e => {
        val str = new java.io.StringWriter
        e.printStackTrace(new java.io.PrintWriter(str))
        log.warning(str.toString)
        None
      }
    }
  }
}
