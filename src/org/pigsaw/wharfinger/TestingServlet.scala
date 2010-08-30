package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import scala.collection.JavaConversions._
import org.ccil.cowan.tagsoup.TagSoupFactoryAdapter
import Preamble._

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
      val html = HtmlNode(new URLReader("http://www.google.com", "UTF-8"))
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

class TestEncodingsServlet extends HttpServlet {

  private val url = "http://blogs.journalism.co.uk/editors/2010/08/03/life-as-an-editor-through-the-eyes-of-a-journalism-student/"

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    req.getPathInfo.tail.split('/')(0) match {
      case "instapaper" => getInstapaper
      case "instapaper-and-show-characters" => getInstapaperAndShowCharacters
      case "instapaper-h2-a" => getInstapaperH2A
      case "instapaper-h2-a-characters" => getInstapaperH2ACharacters
      case "tagsoup-using-system-id" => useTagSoupUsingSystemId
      case "tagsoup-using-string" => useTagSoupUsingString
      case "tagsoup-using-string-and-buffering" => useTagSoupUsingStringAndBuffering
      case "read-raw" => readRaw
      case "read-raw-characters" => readRawCharacters
      case _ => {}
    }

    def println(str: String) = resp.getWriter.println(str)
    def print(str: String) = resp.getWriter.print(str)
    def printChr(chr: Char) = resp.getWriter.print(chr)

    def getInstapaper {
      val handler = new InstapaperHandler(url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => displayContent(content_div.escapeForHTML.toString)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }
    }

    def getInstapaperAndShowCharacters {
      val handler = new InstapaperHandler(url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => displayCharacters(content_div.escapeForHTML.toString)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }
    }

    def getInstapaperH2A {
      val handler = new InstapaperHandler(url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => displayContent((content_div \\ "h2" \ "a").text.escapeForHTML)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }
    }

    def getInstapaperH2ACharacters {
      val handler = new InstapaperHandler(url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => displayCharacters((content_div \\ "h2" \ "a").text.escapeForHTML)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }
    }

    def useTagSoupUsingSystemId {
      val parser = new TagSoupFactoryAdapter
      val source = new org.xml.sax.InputSource()
      source.setSystemId(url)
      source.setEncoding("UTF-8")
      val html = parser loadXML source
      displayContent(html.escapeForHTML.toString)
    }

    def useTagSoupUsingString {
      val str = new StringBuilder
      val reader = new URLReader(url, "UTF-8")
      var chr: Int = 0
      while ({chr = reader.read; chr != -1}) {
        str += chr.toChar
      }
      val parser = new TagSoupFactoryAdapter
      val html = parser loadString str.toString
      displayContent(html.escapeForHTML.toString)
    }

    def useTagSoupUsingStringAndBuffering {
      val str = new StringBuilder
      val buffer = new Array[Char](4*1024)
      val reader = new URLReader(url, "UTF-8")
      val parser = new TagSoupFactoryAdapter
      read()
      lazy val html = parser loadString str.toString
      displayContent(html.escapeForHTML.toString)

      def read() {
        val length = reader.read(buffer, 0, buffer.length)
        if (length > 0) {
          str.appendAll(buffer, 0, length)
          read()
        }
      }
    }

    def readRaw {
      val reader = new URLReader(url, req.getParameter("charset"))
      resp.setContentType("text/html")
      var chr: Int = 0
      while ({chr = reader.read; chr != -1}) {
        printChr(chr.toChar)
      }
    }

    // This shows characters as
    // L i f e   a s   a n   e d i t o r   & # 8 2 1 1 ;   t h r o u g h...
    def readRawCharacters {
      val reader = new URLReader(url, req.getParameter("charset"))
      resp.setContentType("text/html")
      var chr: Int = 0
      while ({chr = reader.read; chr != -1}) {
        print("(")
        printChr(chr.toChar)
        print(") = " + chr)
      }
    }

    def displayContent(content: String) {
      resp.setContentType("text/html")
      print(content)
    }

    def displayCharacters(content: String) {
      resp.setContentType("text/html")
      content.foreach(c => print("(" + c + ")=" + c.toInt + " "))
    }

  }
}

