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
      case "clear-datastore" => clearDataStore
      case "show-articles" => showArticles
      case "show-bookmarks-pending-fetch" => showBookmarksPendingFetch
      case "show-past-articles" => showPastArticles
      case "show-document" => showDocument
      case _ => unrecognisedRequest
    }

    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)

    def clearDataStore() {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      deleteClass(classOf[Article])
      deleteClass(classOf[BookmarkPendingFetch])
      deleteClass(classOf[PastArticle])
      deleteClass(classOf[Document])

      def deleteClass[T](clz: Class[T]) {
        val query = pm.newQuery(clz)
        transaction (query) {
          query.deletePersistentAll
          resp.getWriter.println("Deleted " + clz)
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
      showDataItems(classOf[Article]) {
        article => resp.getWriter.println(article.url)
      }
    }

    def showDocument() {
      val filename = req.getParameter("filename")
      val document = getDocument(filename)
      document match {
        case Some(doc) => outputDocument(doc)
        case _ => outputNoDocument(filename)
      }
    }

    def getDocument(filename: String): Option[Document] = {
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Document])
      query.setFilter("filename == filenameParam")
      query.declareParameters("String filenameParam")
      var out: Option[Document] = None
      transaction(query) {
        val results: Seq[Document] = query.execute(filename).asInstanceOf[java.util.List[Document]]
        results.size match {
          case 1 => out = Some(results(0))
          case _ => out = None
        }
      }
      out
    }

    def outputDocument(doc: Document) {
      resp.setContentType(doc.contentType)
      resp.getWriter.print(doc.getContent)
    }

    def outputNoDocument(filename: String) {
      resp.setContentType("text/plain")
      resp.getWriter.println("Could not find document called '" + filename + "'")
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

    def transaction(query: javax.jdo.Query)(block: Unit): Unit = {
      try { block }
      finally { query.closeAll }
    }

  }
}
