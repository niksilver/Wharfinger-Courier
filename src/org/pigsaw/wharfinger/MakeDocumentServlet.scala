package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._

/**
 * Servlet to make a Wharfinger Courier.
 * Puts the document into the datastore and returns it
 */

class MakeDocumentServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val pm = PMF.get.getPersistenceManager
    try {
      val query = pm.newQuery(classOf[Article])
      val articles = query.execute.asInstanceOf[java.util.List[Article]]
      val datestamp = "2010-08-25"
      val maker = new DocumentMaker("Wharfinger Courier " + datestamp)
      for (article <- articles)
        maker.add(article)
      val doc_xml = maker.document
      val document = new Document(datestamp + ".html", "text/html", doc_xml.toString)
      for (article <- articles)
        pm.deletePersistent(article)
      resp.setContentType(document.contentType)
      resp.getWriter.print(document.getContent)
    }
    finally {
      pm.close
    }

  }
}