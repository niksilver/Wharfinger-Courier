package org.pigsaw.wharfinger.servlets

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import java.util.Calendar
import com.google.appengine.api.taskqueue.QueueFactory
import com.google.appengine.api.taskqueue.TaskOptions.Builder._
import com.google.appengine.api.taskqueue.TaskOptions.Method
import java.util.logging.Logger
import org.pigsaw.wharfinger._

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
      val articles: Seq[Article] = query.execute.asInstanceOf[java.util.List[Article]].toList
      val maker0 = new DocumentMaker("Wharfinger Courier", niceDate)
      val maker  = maker0.add(articles)
      val doc_str = maker.document
      val filename = "Wharfinger Courier " + niceDate + ".html"
      val document = new Document(filename, "text/html", doc_str)
      pm.makePersistent(document)
      deleteArticles(maker.articles)
      resp.setContentType("text/plain")
      resp.getWriter.print(document.getContent)
      queueMailingDocument(document.filename)
    }

    def deleteArticles(articles: Seq[Article]) {
      for (article <- articles)
        pm.deletePersistent(article)
    }

    def queueMailingDocument(filename: String) {
      log.info("Queueing mailing of file named " + filename)
      val queue = QueueFactory.getDefaultQueue
      val task = withUrl("/mail-document").param("filename", filename).method(Method.GET)
      queue.add(task)
      resp.getWriter.println("Queued task " + task.getUrl)
    }

    def niceDate: String = {
      val formatter = new SimpleDateFormat("EEE d MMM yyyy")
      val cal = Calendar.getInstance
      formatter.format(cal.getTime)
    }

  }
}
