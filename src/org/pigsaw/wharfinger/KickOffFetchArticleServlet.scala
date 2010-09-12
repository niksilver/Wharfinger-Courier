package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.xml.Node
import scala.collection.JavaConversions._
import Preamble._
import javax.jdo.PersistenceManager
import com.google.appengine.api.labs.taskqueue.QueueFactory
import com.google.appengine.api.labs.taskqueue.TaskOptions.Builder._
import java.util.logging.Logger

/**
 * Kick off fetching a pending article.
 */

class KickOffFetchArticleServlet extends HttpServlet {

  val log = Logger.getLogger(this.getClass.getName)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/plain")
    val pm: PersistenceManager = PMF.get.getPersistenceManager
    vizLog("Got pm = " + pm)
    bookmarksToFetch().foreach(bookmark => {
      vizLog("foreach, bookmark = " + bookmark.url)
      if (shouldNotFetch(bookmark))
        { vizLog("rejecting"); rejectBookmark(bookmark) }
      else
        { vizLog("fetching"); queueFetchBookmark(bookmark) }
    })
    println("Done fetching bookmarks")

    def vizLog(s: String) = log(s)
    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)

    def shouldNotFetch(bookmark: BookmarkPendingFetch): Boolean =
      tooManyFetchAttempts(bookmark) || isPastArticle(bookmark)

    def tooManyFetchAttempts(bookmark: BookmarkPendingFetch): Boolean = (bookmark.getFetchAttempts >= 10)

    def isPastArticle(bookmark: BookmarkPendingFetch): Boolean = {
      vizLog("Entering isPastArticle")
      val query = pm.newQuery(classOf[PastArticle])
      query.setFilter("url == urlParam")
      query.declareParameters("String urlParam")
      val results = query.execute(bookmark.url).asInstanceOf[java.util.List[BookmarkPendingFetch]]
      val num_found = results.size
      num_found >= 1
    }

    def rejectBookmark(bookmark: BookmarkPendingFetch) {
      vizLog("Entering rejectBookmark")
      persistAndClose(pm) {
        println("Forgetting bookmark for previously-read article " + bookmark.url)
        pm.deletePersistent(bookmark)
      }
    }

    def bookmarksToFetch(): Seq[BookmarkPendingFetch] = {
      vizLog("Entering bookmarksToFetch")
      val query = pm.newQuery(classOf[BookmarkPendingFetch])
      query.setOrdering("fetchAttempts asc")
      query.setRange(0, 1)
      query.execute().asInstanceOf[java.util.List[BookmarkPendingFetch]]
    }

    def queueFetchBookmark(bookmark: BookmarkPendingFetch) = {
      vizLog("Entering queueFetchBookmark")
      persistAndClose(pm) {
        markFetchAttempt()
        val queue = QueueFactory.getDefaultQueue
        val task = url("/do-fetch-article").
          param("url", bookmark.url).
          param("title", bookmark.title).
          param("citation", bookmark.getCitation)
        queue.add(task)
        println("Queued task " + task.getUrl)
      }

      def markFetchAttempt() {
        vizLog("Entering markFetchAttempt")
        bookmark.incrementFetchAttempts
        pm.makePersistent(bookmark)
      }
    }

    def persistAndClose(pm: PersistenceManager)(block: =>Unit) {
      try { block }
      finally { if (!pm.isClosed) pm.close }
    }
  }
}

class DoFetchArticleServlet extends HttpServlet {

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) {
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
      retries_str match {
        case null => false
        case "" => false
        case _ => retries_str.toInt > 10
      }
    }

    def findAndDeleteBookmark {
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
      vizLog("Entering recordArticle")
      val pm: PersistenceManager = PMF.get.getPersistenceManager
      println("Got article to persist: " + url)
      persistAndClose(pm) {
        pm.makePersistent(new Article(url,
          citation.escapeForHTML,
          title.escapeForHTML,
          content_div.escapeForHTML.toString))
        pm.makePersistent(new PastArticle(url))
        deleteBookmark(pm)
      }
    }

    def deleteBookmark(pm: PersistenceManager) {
      val bookmark = pm.getObjectById(classOf[BookmarkPendingFetch], url)
      println("Deleting bookmark: " + bookmark)
      pm.deletePersistent(bookmark)
    }

    def persistAndClose(pm: PersistenceManager)(block: =>Unit) {
      try { block }
      finally { if (!pm.isClosed) pm.close }
    }
  }
}

class InstapaperHandler(article_url: String) {
  val url: String = "http://www.instapaper.com/text?u=" +
          URLEncoder.encode(article_url, "UTF-8")

  def getContentDiv(): Option[Node] = {
    val html = HTMLNode(new URLReader(url, "UTF-8"))
    html findDivWithId "story"
  }
}
