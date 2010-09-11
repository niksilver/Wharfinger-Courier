package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.xml.Node
import scala.collection.JavaConversions._
import Preamble._
import javax.jdo.PersistenceManager

/**
 * Fetch a pending article.
 */

/* class FetchArticleServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/plain")
    val pm: PersistenceManager = PMF.get.getPersistenceManager
    vizLog("Got pm = " + pm)
    bookmarksToFetch().foreach(bookmark => {
      vizLog("foreach, bookmark = " + bookmark.url)
      if (isPastArticle(bookmark))
        { vizLog("rejecting"); rejectBookmark(bookmark) }
      else
        { vizLog("fetching"); fetchBookmark(bookmark) }
    })
    println("Done fetching bookmarks")

    def vizLog(s: String) = println(s + " / transaction active? " + pm.currentTransaction.isActive)
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
      num_found == 1
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

    def fetchBookmark(bookmark: BookmarkPendingFetch) = {

      vizLog("Entering fetchBookmark")
      persistAndClose(pm) {
        val handler = new InstapaperHandler(bookmark.url)
        markFetchAttempt()
        val content_div_option = handler.getContentDiv
        content_div_option match {
          case Some(content_div) => persistArticleAndRemoveFromPendingList(content_div)
          case None => saveForLaterRetry()
        }
      }

      def markFetchAttempt() {
        vizLog("Entering markFetchAttempt")
        bookmark.incrementFetchAttempts
        pm.makePersistent(bookmark)
      }

      def persistArticleAndRemoveFromPendingList(content_div: Node) {
        vizLog("Entering persistArticleAndRemoveFromPendingList")
        println("Got article to persist: " + bookmark.url)
        pm.makePersistent(new Article(bookmark.url,
          bookmark.getCitation.escapeForHTML,
          bookmark.title.escapeForHTML,
          content_div.escapeForHTML.toString))
        pm.makePersistent(new PastArticle(bookmark.url))
        pm.deletePersistent(bookmark)
      }

      def saveForLaterRetry() {
        vizLog("Entering saveForLaterRetry")
        println("Didn't get " + bookmark.url)
        println("Number of fetch attempts: " + bookmark.getFetchAttempts)
        if (tooManyFetchAttempts(bookmark)) {
          pm.deletePersistent(bookmark)
          println("Too many fetch attempts, giving up.")
        }
      }
    }

    def persistAndClose(pm: PersistenceManager)(block: =>Unit) {
      try { block }
      finally { if (!pm.isClosed) pm.close }
    }
  }
}
*/