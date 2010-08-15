package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._

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
      case _ => {}
    }

    def clearDataStore() {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Article])
      transaction (query) {
        query.deletePersistentAll
        resp.getWriter.println("Deleted articles")
      }
    }

    def showArticles() {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Article])
      transaction (query) {
        val articles = query.execute.asInstanceOf[java.util.List[Article]]
        for (article <- articles) {
          resp.getWriter.print(runLinesTogether(article.getContent).take(60))
          resp.getWriter.println("... " + article.url)
        }
      }

      def runLinesTogether(str: String) = ("" /: str.lines)(_ + _)
    }


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