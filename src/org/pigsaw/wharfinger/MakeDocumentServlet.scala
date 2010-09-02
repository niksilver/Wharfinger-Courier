package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import java.util.Calendar
import Preamble._

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
      val maker = new DocumentMaker("Wharfinger Courier " + niceDate)
      for (article <- articles)
        maker.add(article)
      val doc_str = maker.document
      val document = new Document(techDate + ".html", "text/html", doc_str)
      pm.makePersistent(document)
      for (article <- articles)
        pm.deletePersistent(article)
      resp.setContentType(document.contentType)
      resp.getWriter.print(document.getContent)
    }
    finally {
      pm.close
    }

    def techDate: String = {
      val formatter = new SimpleDateFormat("yyyy-MM-dd-HHmm")
      val cal = Calendar.getInstance
      formatter.format(cal.getTime)
    }

    def niceDate: String = {
      val formatter = new SimpleDateFormat("EEE d MMM yyyy")
      val cal = Calendar.getInstance
      formatter.format(cal.getTime)
    }

  }
}