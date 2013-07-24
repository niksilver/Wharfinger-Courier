package org.pigsaw.wharfinger.servlets

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.jdo.PersistenceManager
import java.util.logging.Logger
import xml.Node
import org.pigsaw.wharfinger._
import org.pigsaw.wharfinger.Preamble._

/**
 * To change this template use File | Settings | File Templates.
 */
class DoFetchArticleServlet extends HttpServlet with Transaction {

   val log = Logger.getLogger(this.getClass.getName)

   override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = doGet(req, resp)

   override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
     resp.setContentType("text/plain")
     val url = req.getParameter("url")
     val title = req.getParameter("title")
     val citation = req.getParameter("citation")
     if (tooManyRetries)
       findAndDeleteBookmark()
     else
       fetchArticle()

     def tooManyRetries: Boolean = {
       val retries_str = req.getHeader("X-AppEngine-TaskRetryCount")
       log.warning("TaskRetryCount is '" + retries_str + "'")
       retries_str match {
         case null => false
         case "" => false
         case _ => retries_str.toInt > 10
       }
     }

     def findAndDeleteBookmark() {
       log.warning("Too many retries. Deleting bookmark: " + url)
       val pm = PMF.get.getPersistenceManager
       persistAndClose(pm) {
         deleteBookmark(pm)
       }
     }

     def fetchArticle() {
       val handler = new InstapaperHandler(url)
       val content_div_option = handler.getContentDiv
       content_div_option match {
         case Some(content_div) => recordArticle(content_div)
         case None => {}
       }
     }

     def vizLog(s: String) = println(s)
     def println(s: String) = resp.getWriter.println(s)
     def print(s: String) = resp.getWriter.print(s)

     def recordArticle(content_div: Node) {
       val pm: PersistenceManager = PMF.get.getPersistenceManager
       println("Got article to persist: " + url)
       persistAndClose(pm) {
         pm.makePersistent(new Article(url,
           citation.escapeForHTML,
           title.escapeForHTML,
           content_div.imagesToText.escapeForHTML.toString))
         pm.makePersistent(new PastArticle(url))
         deleteBookmark(pm)
       }
     }

     def deleteBookmark(pm: PersistenceManager) {
       val bookmark = pm.getObjectById(classOf[BookmarkPendingFetch], url)
       println("Deleting bookmark: " + bookmark)
       pm.deletePersistent(bookmark)
     }
   }
 }
