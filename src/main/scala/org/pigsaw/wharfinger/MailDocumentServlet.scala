package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.jdo.{PersistenceManagerFactory, PersistenceManager}
import scala.collection.JavaConversions._

/**
 * Mail out a Wharfinger Courier document.
 */

class MailDocumentServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain")
    val filename = req.getParameter("filename")
    getDocument match {
      case None => noSuchDocument
      case Some(document) => mailDocument(document)
    }

    def getDocument: Option[Document] = {
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Document])
      query.setFilter("filename == filenameParam")
      query.declareParameters("String filenameParam")
      val results: Seq[Document] = query.execute(filename).asInstanceOf[java.util.List[Document]]
      results.size match {
        case 0 => None
        case 1 => Option(results(0))
      }
    }

    def noSuchDocument {
      resp.getWriter.println("No such document named " + filename)      
    }

    def mailDocument(document: Document) {
      val email = new EMail(to = "nik.silver@free.kindle.com",
        toName = "Nik Silver",
        from = "nik.silver@gmail.com",
        fromName = "Nik Silver",
        subject = document.filename,
        bodyText = "Your daily digest attached")
      email.attachHTML(document.filename, document.getContent)
      email.send
      resp.getWriter.println("Mailed " + filename)
    }

  }
}