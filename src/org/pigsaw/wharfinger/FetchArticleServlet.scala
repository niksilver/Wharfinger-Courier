package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.xml.Node
import scala.collection.JavaConversions._
import Preamble._
import javax.jdo.PersistenceManager

/**
 * Fetch one or more articles which are pending.
 */

class FetchArticleServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/plain")
    val pm: PersistenceManager = PMF.get.getPersistenceManager
    bookmarksToFetch().foreach(bookmark => {
      if (isPastArticle(bookmark))
        rejectBookmark(bookmark)
      else
        fetchBookmark(bookmark)
    })
    println("Done fetching bookmarks")

    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)

    def isPastArticle(bookmark: BookmarkPendingFetch): Boolean = {
      val query = pm.newQuery(classOf[PastArticle])
      query.setFilter("url == urlParam")
      query.declareParameters("String urlParam")
      val results = query.execute(bookmark.url).asInstanceOf[java.util.List[BookmarkPendingFetch]]
      val num_found = results.size
      num_found == 1
    }

    def rejectBookmark(bookmark: BookmarkPendingFetch) {
      println("Forgetting bookmark for previously-read article " + bookmark.url)
      pm.deletePersistent(bookmark)
    }

    def bookmarksToFetch(): Seq[BookmarkPendingFetch] = {
      val query = pm.newQuery(classOf[BookmarkPendingFetch])
      query.setOrdering("fetchAttempts asc")
      query.setRange(0, 1)
      query.execute().asInstanceOf[java.util.List[BookmarkPendingFetch]]
    }

    def fetchBookmark(bookmark: BookmarkPendingFetch) = {

      try {
        val handler = new InstapaperHandler(bookmark.url)
        val content_div_option = handler.getContentDiv
        content_div_option match {
          case Some(content_div) => persistArticleAndRemoveFromPendingList(content_div)
          case None => saveForLaterRetry()
        }
      }
      catch {
        case e => saveForLaterRetry()
      }

      def persistArticleAndRemoveFromPendingList(content_div: Node) {
        println("Got article to persist: " + bookmark.url)
        persistAndClose(pm) {
          pm.makePersistent(new Article(bookmark.url,
            bookmark.getCitation.escapeForHTML,
            bookmark.title.escapeForHTML,
            content_div.escapeForHTML.toString))
          pm.makePersistent(new PastArticle(bookmark.url))
          pm.deletePersistent(bookmark)
        }
      }

      def saveForLaterRetry() {
        println("Didn't get, will mark for retry: " + bookmark.url)
        persistAndClose(pm) {
          bookmark.incrementFetchAttempts
          if (bookmark.getFetchAttempts >= 10) {
            pm.deletePersistent(bookmark)
            println("Too many fetch attempts, giving up.")
          }
        }
        println("Fetch attempts = " + bookmark.getFetchAttempts)
      }

      def persistAndClose(pm: PersistenceManager)(block: Unit) {
        try { block }
        finally { pm.close }
      }
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
