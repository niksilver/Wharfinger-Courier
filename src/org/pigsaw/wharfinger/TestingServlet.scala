package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 08-Aug-2010
 * Time: 15:53:47
 * To change this template use File | Settings | File Templates.
 */

class TestingServlet extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {

    req.getPathInfo.tail.split('/')(0) match {
      case "get-html" => testGetHtml
      case "redirect-resolution" => testRedirectResolution
      case "save-message" => testSaveMessage(req.getPathInfo + ":" + req.getRequestURI)
      case "load-message" => testLoadMessage
      case "delete-message" => testDeleteMessage
      case _ => testBasicOutput
    }

    def testBasicOutput: Unit = {
      resp.setContentType("text/plain")
      resp.getWriter.println("This is the testing servlet, basic test")
    }

    def testGetHtml: Unit = {
      resp.setContentType("text/plain")
      val html = HtmlNode(new URLReader("http://www.google.com"))
      val title = (html \\ "title").text
      resp.getWriter.println("Title of Google is '" + title + "'")      
    }

    def testRedirectResolution: Unit = {
      resp.setContentType("text/plain")
      val bitly_url = "http://bit.ly/9NQcyA"
      val resolver = new RedirectResolver(bitly_url)
      resp.getWriter.println("'" + bitly_url + "' resolves to '" + resolver.URL + "'")
    }

    def testSaveMessage(msg: String): Unit = {
      resp.setContentType("text/plain")

      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Message])
      transaction (query) {
        val message = new Message
        message.setMessage(msg)
        pm.makePersistent(message)
        resp.getWriter.println("Message was set to '" + msg + "'")
      }
    }

    def testLoadMessage: Unit = {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Message])
      transaction (query) {
        val messages = query.execute.asInstanceOf[java.util.List[Message]]
        val output = messages.size match {
          case 0 => "No message saved"
          case _ => "The message is '" + messages(0).getMessage + "'"
        }
        resp.getWriter.println(output)
      }
    }

    def testDeleteMessage: Unit = {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Message])
      transaction (query) {
        query.deletePersistentAll
        resp.getWriter.println("Deleted message")
      }
    }

    def transaction(query: javax.jdo.Query)(block: Unit): Unit = {
      try {
        block
      }
      catch {
        case e => resp.getWriter.println("Exception: " + e.getMessage)
      }
      finally {
        query.closeAll
      }
    }
  }
}