package org.pigsaw.wharfinger.servlets

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._
import org.pigsaw.wharfinger._

/**
 * Handle core admin requests.
 */

class AdminServlet extends HttpServlet with Transaction {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    implicit val _ = resp

    req.getPathInfo.tail.split('/')(0) match {
      case "clear-datastore" => clearDataStore
      case "show-articles" => showArticles
      case "show-bookmarks-pending-fetch" => showBookmarksPendingFetch
      case "show-past-articles" => showPastArticles
      case _ => unrecognisedRequest
    }

    def clearDataStore() {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      req.getParameter("confirm") match {
        case "yes" => deleteClasses
        case _ => println("Parameter confirm=yes required")
      }

      def deleteClasses {
        deleteClass(classOf[Article])
        deleteClass(classOf[BookmarkPendingFetch])
        deleteClass(classOf[PastArticle])
        deleteClass(classOf[Document])
      }

      def deleteClass[T](clz: Class[T]) {
        val query = pm.newQuery(clz)
        transaction (query) {
          query.deletePersistentAll
          println("Deleted " + clz)
        }
      }
    }

    def showArticles() {
      showDataItems(classOf[Article]) {
        article => {
          print(pad(article.getCitation, 40))
          print("  ")
          print(pad(article.title, 30))
          print("  ")
          print(pad(article.getContent, 60))
          print("  ")
          println(article.url)
        }
      }
    }

    def showBookmarksPendingFetch() {
      showDataItems(classOf[BookmarkPendingFetch]) {
        bookmark => {
          print(pad(bookmark.getFetchAttempts.toString, 4))
          print(pad(bookmark.title, 40))
          print("  ")
          println(bookmark.url)
        }
      }
    }

    def showPastArticles() {
      showDataItems(classOf[PastArticle]) {
        article => resp.getWriter.println(article.url)
      }
    }

    def showDataItems[T](clz: Class[T])(printer: T => Unit) {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(clz)
      transaction(query) {
        val articles = query.execute.asInstanceOf[java.util.List[T]]
        for (article <- articles) {
          printer(article)
        }
      }
    }

    def unrecognisedRequest {
      resp.setContentType("text/plain")
      resp.getWriter.println("Unrecognised request")
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

  }
}
