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
      case "/show-document" => showDocument
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
          println("<a href=\"/show-document/" + document.filename + "\">" +
                  document.filename + "</a><br/>")
        }
      }
    }

    def showDocument() {
      val filename = req.getPathInfo.tail.split('/')(0)
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

    def unrecognisedRequest {
      resp.setContentType("text/plain")
      resp.getWriter.println("Unrecognised request")
    }

    def transaction(query: javax.jdo.Query)(block: =>Unit): Unit = {
      try { block }
      finally { query.closeAll }
    }

  }
}