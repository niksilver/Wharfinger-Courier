package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.xml.Node
import scala.collection.JavaConversions._
import Preamble._
import javax.jdo.PersistenceManager

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 13-Aug-2010
 * Time: 22:34:45
 * To change this template use File | Settings | File Templates.
 */

class FetchSomeURLsServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/plain")
    val pm: PersistenceManager = PMF.get.getPersistenceManager
    val query = pm.newQuery(classOf[BookmarkPendingFetch])
    query.setRange(0, 1)
    val results = query.execute().asInstanceOf[java.util.List[BookmarkPendingFetch]]
    if (results.size == 0)
      sayNoBookmarksToFetch
    else
      fetchBookmark(results(0))

    def sayNoBookmarksToFetch = {
      resp.getWriter.println("No bookmarks to fetch")
    }

    def fetchBookmark(bookmark: BookmarkPendingFetch) = {

      val handler = new InstapaperHandler(bookmark.url)
      lazy val content_div = getContentDivSafely()
      if (content_div.text == "") {
        resp.getWriter.println("Didn't get, retrying (to be written)")
        retry(bookmark.url)
      }
      else {
        resp.getWriter.println("Got article to persist: " + bookmark.url)
        persistArticleAndRemoveFromPendingList()
      }

      def getContentDivSafely(): Node = {
        try {
          handler.getContentDiv()
        }
        catch {
          case _ => <div></div>
        }
      }

      def persistArticleAndRemoveFromPendingList() {
        try {
          pm.makePersistent(new Article(bookmark.url, content_div.toString))
          pm.deletePersistent(bookmark)
        }
        finally {
          pm.close
        }
      }

      def retry(url: String) {
        /*val pm = PMF.get.getPersistenceManager
        try {
          pm.makePersistent(new BookmarkToRetry(url))
        }
        finally {
          pm.close
        }*/
      }
    }
  }
}

class InstapaperHandler(article_url: String) {
  val url: String = "http://www.instapaper.com/text?u=" +
          URLEncoder.encode(article_url, "UTF-8")

  def getContentDiv(): Node = {
    val html = HtmlNode(new URLReader(url))
    html findDivWithId "story" match {
      case None => <div></div>
      case Some(story_div) => story_div
    }
  }
}