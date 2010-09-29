package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import java.util.Calendar
import Preamble._
import com.google.appengine.api.labs.taskqueue.QueueFactory
import com.google.appengine.api.labs.taskqueue.TaskOptions.Builder._
import com.google.appengine.api.labs.taskqueue.TaskOptions.Method
import java.util.logging.Logger

/**
 * Servlet to make a Wharfinger Courier.
 * Puts the document into the datastore and returns it
 */

class MakeDocumentServlet extends HttpServlet {

  val log = Logger.getLogger(this.getClass.getName)
  
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val pm = PMF.get.getPersistenceManager
    try {
      val query = pm.newQuery(classOf[Article])
      val articles: Seq[Article] = query.execute.asInstanceOf[java.util.List[Article]]
      val maker = new DocumentMaker("Wharfinger Courier", niceDate)
      addArticles(articles, maker)
      val doc_str = maker.document
      val filename = "Wharfinger Courier " + niceDate + ".html"
      val document = new Document(filename, "text/html", doc_str)
      pm.makePersistent(document)
      deleteArticles(articles)
      resp.setContentType(document.contentType)
      resp.getWriter.print(document.getContent)
      queueMailingDocument(document.filename)
    }
    finally {
      pm.close
    }

    def addArticles(articles: Seq[Article], maker: DocumentMaker) {
      for (article <- articles)
        maker.add(article)
    }

    def deleteArticles(articles: Seq[Article]) {
      for (article <- articles)
        pm.deletePersistent(article)
    }

    def queueMailingDocument(filename: String) {
      log.info("Queueing mailing of file named " + filename)
      val queue = QueueFactory.getDefaultQueue
      val task = url("/mail-document").param("filename", filename).method(Method.GET)
      queue.add(task)
      println("Queued task " + task.getUrl)
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

class BackfillDatesServlet extends MakeDocumentServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val pm = PMF.get.getPersistenceManager
    val date_re = """Wharfinger Courier (.*)\.html""".r
    val format = new SimpleDateFormat("EEE d MMM yyyy")
    resp.setContentType("text/plain")
    try {
      val query = pm.newQuery(classOf[Document])
      transaction(query) {
        val documents = query.execute.asInstanceOf[java.util.List[Document]]
        for (document <- documents; if (document.publicationDate == null)) {
          println("Null date for " + document.filename)
          val date_re(date_str) = document.filename
          println("  Date string is '" + date_str + "'")
          val date = format.parse(date_str)
          println("  Date is " + date)
          document.setPublicationDate(date)
          pm.makePersistent(document)
        }
      }
    }
    finally {
      pm.close
    }

    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)
  }

  def transaction(query: javax.jdo.Query)(block: =>Unit): Unit = {
    try { block }
    finally { query.closeAll }
  }

}