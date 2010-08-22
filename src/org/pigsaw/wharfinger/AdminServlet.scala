package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._
import javax.jdo.PersistenceManager

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 15-Aug-2010
 * Time: 00:55:02
 * To change this template use File | Settings | File Templates.
 */

class AdminServlet extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {

    req.getPathInfo.tail.split('/')(0) match {
      case "cleardatastore" => clearDataStore
      case "showarticles" => showArticles
      case "showbookmarkspendingfetch" => showBookmarksPendingFetch
      case _ => {}
    }

    def clearDataStore() {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      deleteClass(classOf[Article])
      deleteClass(classOf[BookmarkPendingFetch])

      def deleteClass[T](clz: Class[T]) {
        val query = pm.newQuery(clz)
        transaction (query) {
          query.deletePersistentAll
          resp.getWriter.println("Deleted " + clz)
        }
      }
    }

    def showArticles() {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Article])
      transaction (query) {
        val articles = query.execute.asInstanceOf[java.util.List[Article]]
        for (article <- articles) {
          resp.getWriter.print(pad(article.getCitation, 40))
          resp.getWriter.print("  ")
          resp.getWriter.print(pad(article.getContent, 60))
          resp.getWriter.print("  ")
          resp.getWriter.println(article.url)
        }
      }
    }

    def showBookmarksPendingFetch() {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[BookmarkPendingFetch])
      transaction (query) {
        val bookmarks = query.execute.asInstanceOf[java.util.List[BookmarkPendingFetch]]
        for (bookmark <- bookmarks) {
          resp.getWriter.print(pad(bookmark.getFetchAttempts.toString, 4))
          resp.getWriter.print(pad(bookmark.title, 40))
          resp.getWriter.print("  ")
          resp.getWriter.println(bookmark.url)
        }
      }
    }

    def pad(str: String, len: Int): String = {
      val str_line = runLinesTogether(str)
      if (str_line.length > len) {
        str_line.take(len-3) + "..."
      } else {
        (str_line + (" " * len)).take(len)
      }
    }

    def runLinesTogether(str: String) = ("" /: str.lines)(_ + _)

    def transaction(query: javax.jdo.Query)(block: Unit): Unit = {
      try {
        block
      }
      finally {
        query.closeAll
      }
    }

  }
}
