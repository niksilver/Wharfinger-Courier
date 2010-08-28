package org.pigsaw.wharfinger

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URLEncoder
import scala.xml.Node
import scala.collection.JavaConversions._
import Preamble._
import javax.jdo.PersistenceManager
import org.ccil.cowan.tagsoup.TagSoupFactoryAdapter

/**
 * Fetch one or more URLs which are pending.
 */

class FetchSomeURLsServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/plain")
    val pm: PersistenceManager = PMF.get.getPersistenceManager
    bookmarksToFetch().foreach(bookmark => {
      if (isPastArticle(bookmark))
        rejectBookmark(bookmark)
      else
        fetchBookmark(bookmark)
    })
    println("Done fetching bookmarks")

    def println(s: String) = resp.getWriter.println(s)
    def print(s: String) = resp.getWriter.print(s)

    def isPastArticle(bookmark: BookmarkPendingFetch): Boolean = {
      val query = pm.newQuery(classOf[PastArticle])
      query.setFilter("url == urlParam")
      query.declareParameters("String urlParam")
      val results = query.execute(bookmark.url).asInstanceOf[java.util.List[BookmarkPendingFetch]]
      val num_found = results.size
      num_found == 1
    }

    def rejectBookmark(bookmark: BookmarkPendingFetch) {
      println("Forgetting bookmark for previously-read article " + bookmark.url)
      pm.deletePersistent(bookmark)
    }

    def bookmarksToFetch(): Seq[BookmarkPendingFetch] = {
      val query = pm.newQuery(classOf[BookmarkPendingFetch])
      query.setOrdering("fetchAttempts asc")
      query.setRange(0, 1)
      query.execute().asInstanceOf[java.util.List[BookmarkPendingFetch]]
    }

    def fetchBookmark(bookmark: BookmarkPendingFetch) = {

      val handler = new InstapaperHandler(bookmark.url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => persistArticleAndRemoveFromPendingList(content_div)
        case None => saveForLaterRetry()
      }

      def persistArticleAndRemoveFromPendingList(content_div: Node) {
        println("Got article to persist: " + bookmark.url)
        persistAndClose(pm) {
          pm.makePersistent(new Article(bookmark.url,
            bookmark.getCitation,
            bookmark.title,
            content_div.toString))
          pm.makePersistent(new PastArticle(bookmark.url))
          pm.deletePersistent(bookmark)
        }
      }

      def saveForLaterRetry() {
        println("Didn't get, will mark for retry: " + bookmark.url)
        persistAndClose(pm) {
          bookmark.incrementFetchAttempts
          if (bookmark.getFetchAttempts >= 10) {
            pm.deletePersistent(bookmark)
            println("Too many fetch attempts, giving up.")
          }
        }
        println("Fetch attempts = " + bookmark.getFetchAttempts)
      }

      def persistAndClose(pm: PersistenceManager)(block: Unit) {
        try { block }
        finally { pm.close }
      }
    }
  }
}

class TestEncodingsServlet extends HttpServlet {

  private val url = "http://blogs.journalism.co.uk/editors/2010/08/03/life-as-an-editor-through-the-eyes-of-a-journalism-student/"
  
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    req.getPathInfo.tail.split('/')(0) match {
      case "instapaper" => getInstapaper
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
        case Some(content_div) => displayContent(content_div.toString)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }
    }

    def getInstapaperH2A {
      val handler = new InstapaperHandler(url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => displayContent((content_div \\ "h2" \ "a").text)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }
    }

    def getInstapaperH2ACharacters {
      val handler = new InstapaperHandler(url)
      val content_div_option = handler.getContentDiv
      content_div_option match {
        case Some(content_div) => displayCharacters((content_div \\ "h2" \ "a").text)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }
    }

    def useTagSoupUsingSystemId {
      val parser = new TagSoupFactoryAdapter
      val source = new org.xml.sax.InputSource()
      source.setSystemId(url)
      source.setEncoding("UTF-8")
      val html = parser loadXML source
      displayContent(html.toString)
      /*val content_div_option = html findDivWithId "story"
      content_div_option match {
        case Some(content_div) => displayCharacters((content_div \\ "h2" \ "a").text)
        case None => throw new RuntimeException("Couldn't get content from Instapaper")
      }*/
    }

    def useTagSoupUsingString {
      val str = new StringBuilder
      val reader = new URLReader(url)
      var chr: Int = 0
      while ({chr = reader.read; chr != -1}) {
        str += chr.toChar
      }
      val parser = new TagSoupFactoryAdapter
      val html = parser loadString str.toString
      displayContent(html.toString)

    }

    def useTagSoupUsingStringAndBuffering {
      val str = new StringBuilder
      val buffer = new Array[Char](4*1024)
      val reader = new URLReader(url)
      val parser = new TagSoupFactoryAdapter
      read()
      lazy val html = parser loadString str.toString
      displayContent(html.toString)

      def read() {
        val length = reader.read(buffer, 0, buffer.length)
        if (length > 0) {
          str.append(buffer, 0, length)
          read()
        }
      }

    }
    def readRaw {
      val reader = new URLReader(url)
      resp.setContentType("text/html")
      var chr: Int = 0
      while ({chr = reader.read; chr != -1}) {
        printChr(chr.toChar)
      }
    }

    // This shows characters as
    // L i f e   a s   a n   e d i t o r   & # 8 2 1 1 ;   t h r o u g h...
    def readRawCharacters {
      val reader = new URLReader(url)
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
      resp.setContentType("text/plain")
      content.foreach(c => println("(" + c + ") = " + c.toInt))
    }

  }
}

class InstapaperHandler(article_url: String) {
  val url: String = "http://www.instapaper.com/text?u=" +
          URLEncoder.encode(article_url, "UTF-8")

  def getContentDiv(): Option[Node] = {
    val html = HtmlNode(new URLReader(url))
    html findDivWithId "story"
  }
}
