package org.pigsaw.wharfinger.servlets

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._
import scala.util.Random
import java.util.logging.Logger
import org.pigsaw.wharfinger._
import org.pigsaw.wharfinger.Preamble._

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 08-Aug-2010
 * Time: 15:53:47
 * To change this template use File | Settings | File Templates.
 */

class TestingServlet extends HttpServlet with Transaction {

  val log = Logger.getLogger(this.getClass.getName)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {

    req.getPathInfo.tail.split('/')(0) match {
      case "get-html" => testGetHtml()
      case "redirect-resolution" => testRedirectResolution()
      case "save-message" => testSaveMessage(req.getPathInfo + ":" + req.getRequestURI)
      case "load-message" => testLoadMessage()
      case "delete-message" => testDeleteMessage()
      case "mail-simple-message" => testMailSimpleMessage()
      case "mail-message-with-html-attachment" => testMailMessageWithHTMLAttachment()
      case "tag-soup" => testTagSoup()
      case "tag-soup-and-character-codes" => testTagSoupAndCharacterCodes()
      case "instapaper-html" => testInstapaperHtml()
      case "instapaper-content-extraction" => testInstapaperContentExtraction()
      case "instapaper-and-character-codes" => testInstapaperAndCharacterCodes()
      case "instapaper-and-transformation" => testInstapaperAndTransformation()
      case _ => testBasicOutput()
    }

    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)

    def testBasicOutput(): Unit = {
      resp.setContentType("text/plain")
      val num = Random.nextInt
      println("This is the testing servlet, basic test " + num)
      log.info("This is info from testBasicOutput " + num)
      log.warning("This is a warning from testBasicOutput " + num)
    }

    def testGetHtml(): Unit = {
      resp.setContentType("text/plain")
      val html = HTMLNode.toNode(new URLReader("http://www.google.com", "UTF-8"))
      val title = (html \\ "title").text
      println("Title of Google is '" + title + "'")
    }

    def testRedirectResolution(): Unit = {
      resp.setContentType("text/plain")
      val bitly_url = "http://bit.ly/9NQcyA"
      val resolver = new RedirectResolver(bitly_url)
      println("'" + bitly_url + "' resolves to '" + resolver.URL + "'")
    }

    def testSaveMessage(msg: String): Unit = {
      resp.setContentType("text/plain")

      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Message])
      transactionWithReporting (query, resp) {
        val message = new Message
        message.setMessage(msg)
        pm.makePersistent(message)
        println("Message was set to '" + msg + "'")
      }
    }

    def testLoadMessage(): Unit = {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Message])
      transactionWithReporting (query, resp) {
        val messages = query.execute.asInstanceOf[java.util.List[Message]]
        val output = messages.size match {
          case 0 => "No message saved"
          case _ => "The message is '" + messages(0).getMessage + "'"
        }
        println(output)
      }
    }

    def testDeleteMessage(): Unit = {
      resp.setContentType("text/plain")
      val pm = PMF.get.getPersistenceManager
      val query = pm.newQuery(classOf[Message])
      transactionWithReporting (query, resp) {
        query.deletePersistentAll()
        println("Deleted message")
      }
    }

    def testMailSimpleMessage() {
      val random = "...." map {c => Random.nextPrintableChar }
      val mail = new EMail(to = "nik.silver@gmail.com", toName = "Nik Silver",
        from = "nik.silver@gmail.com", fromName = "Nik Silver",
        subject = "Simple test message " + random,
        bodyText = "Dear Nik,\nThis is a simple message with content " + random + ".\nYours\nNik\n")
      mail.send()
      resp.setContentType("text/plain")
      println("Sent simple message with random content " + random)
    }

    def testMailMessageWithHTMLAttachment() {
      val random = "...." map {c => Random.nextPrintableChar }
      val mail = new EMail(to = "nik.silver@gmail.com", toName = "Nik Silver",
        from = "nik.silver@gmail.com", fromName = "Nik Silver",
        subject = "Test message with HTML attachment and " + random,
        bodyText = "Dear Nik,\nThis is a message with an HTML attachment and " +
                random + ".\nYours\nNik\n")
      val html = HTMLNode.toNode(new URLReader("http://sharkysoft.com/tutorials/jsa/", "UTF-8"))
      mail.attachHTML("javascript-answers.html", html.toString)
      mail.send()
      resp.setContentType("text/plain")
      println("Sent message with attachment and random content " + random)
    }

    def testTagSoup() {
      val url = req.getParameter("url")
      val html = HTMLNode.toNode(new URLReader(url, "UTF-8"))
      resp.setContentType("text/html")
      print(html.toString)
    }

    def characterView(c: Char) = c.toString + " (" + c.toInt + ") "

    def testTagSoupAndCharacterCodes() {
      val url = req.getParameter("url")
      val html = HTMLNode.toNode(new URLReader(url, "UTF-8"))
      resp.setContentType("text/html")
      print(html.toString flatMap characterView)
    }

    def testInstapaperHtml() {
      val url = req.getParameter("url")
      val handler = new InstapaperHandler(url)
      val Some(content_div) = handler.getBasicHtml
      resp.setContentType("text/html")
      print(content_div.toString)
    }

    def testInstapaperContentExtraction() {
      val url = req.getParameter("url")
      val handler = new InstapaperHandler(url)
      val Some(content_div) = handler.getContentDiv
      resp.setContentType("text/html")
      print(content_div.toString)
    }

    def testInstapaperAndCharacterCodes() {
      val url = req.getParameter("url")
      val handler = new InstapaperHandler(url)
      val Some(content_div) = handler.getContentDiv
      resp.setContentType("text/html")
      print(content_div.toString flatMap characterView)
    }

    def testInstapaperAndTransformation() {
      val url = req.getParameter("url")
      val handler = new InstapaperHandler(url)
      val Some(content_div) = handler.getContentDiv
      resp.setContentType("text/html")
      print(content_div.imagesToText.escapeForHTML.toString)
    }

    def testInstapaperAndTransformationWithCharacterCodes() {
      val url = req.getParameter("url")
      val handler = new InstapaperHandler(url)
      val Some(content_div) = handler.getContentDiv
      resp.setContentType("text/html")
      print(content_div.imagesToText.escapeForHTML.toString flatMap
        {c => c.toString + " (" + c.toInt + ") "})
    }

  }
}

