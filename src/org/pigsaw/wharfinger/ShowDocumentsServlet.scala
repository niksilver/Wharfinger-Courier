package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._

/**
 * Handle requests to deliver a document or show all documents.
 */

class ShowDocumentsServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    req.getServletPath match {
      case "/show-documents" => showDocuments
      case _ => unrecognisedRequest
    }

    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)

    def showDocuments() {
      resp.setContentType("text/html")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Document])
      query.setOrdering("filename desc")
      transaction(query) {
        val documents = query.execute.asInstanceOf[java.util.List[Document]]
        for (document <- documents) {
          println("<a href=\"/admin/show-document?filename=" + document.filename + "\">" +
                  document.filename + "</a><br/>")
        }
      }
    }

    def unrecognisedRequest {
      resp.setContentType("text/plain")
      resp.getWriter.println("Unrecognised request")
    }

    def transaction(query: javax.jdo.Query)(block: Unit): Unit = {
      try { block }
      finally { query.closeAll }
    }

  }
}