package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.xml.Node
import scala.collection.JavaConversions._
import Preamble._
import javax.jdo.PersistenceManager

/**
 * Fetch one or more URLs which are pending.
 */

class FetchSomeURLsServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/plain")
    val pm: PersistenceManager = PMF.get.getPersistenceManager
    bookmarksToFetch().foreach(fetchBookmark _)
    resp.getWriter.println("Done fetching bookmarks")

    def bookmarksToFetch(): Seq[BookmarkPendingFetch] = {
      val query = pm.newQuery(classOf[BookmarkPendingFetch])
      query.setRange(0, 1)
      query.execute().asInstanceOf[java.util.List[BookmarkPendingFetch]]
    }

    def fetchBookmark(bookmark: BookmarkPendingFetch) = {

      val handler = new InstapaperHandler(bookmark.url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => persistArticleAndRemoveFromPendingList(content_div)
        case None => saveForLaterRetry(bookmark.url)
      }

      def persistArticleAndRemoveFromPendingList(content_div: Node) {
        resp.getWriter.println("Got article to persist: " + bookmark.url)
        persistAndClose(pm) {
          pm.makePersistent(new Article(bookmark.url, bookmark.getCitation, content_div.toString))
          pm.deletePersistent(bookmark)
        }
      }

      def saveForLaterRetry(url: String) {
        resp.getWriter.println("Didn't get, will mark for retry: " + bookmark.url)
        bookmark.fetchAttempts += 1
        persistAndClose(pm) {
          pm.makePersistent(bookmark)
        }
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
    val html = HtmlNode(new URLReader(url))
    html findDivWithId "story"
  }
}