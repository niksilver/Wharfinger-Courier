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

class MakeDocumentServlet extends HttpServlet with Transaction {

  val log = Logger.getLogger(this.getClass.getName)
  
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val pm = PMF.get.getPersistenceManager
    persistAndClose(pm) {
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
      resp.getWriter.println("Queued task " + task.getUrl)
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
