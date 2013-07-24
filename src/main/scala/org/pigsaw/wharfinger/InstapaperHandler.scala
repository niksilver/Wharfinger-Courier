package org.pigsaw.wharfinger

import java.net.URLEncoder
import java.util.logging.Logger
import xml.Node
import org.pigsaw.wharfinger.Preamble._

/**
 * To change this template use File | Settings | File Templates.
 */
class InstapaperHandler(article_url: String) {
   val log = Logger.getLogger(this.getClass.getName)
   val url: String = "http://www.instapaper.com/text?u=" +
           URLEncoder.encode(article_url, "UTF-8")

   def getContentDiv: Option[Node] = {
     tryOrLogWarning {
       val html = HTMLNode(new URLReader(url, "UTF-8"))
       val story_div = html findDivWithId "story"
       if (story_div exists { n: Node => n.text.trim == "" })
         getSecondBody(html)
       else
         story_div
     }
   }

   private def getSecondBody(html: Node): Option[Node] = {
     val second_body = (html \\ "body")(1)
     val story_div = second_body.bodyToStoryDiv
     Some(story_div)
   }

   /** Get just what comes out of Instapaper, albeit processed by the Tag Soup XML loader
    */
   def getBasicHtml: Option[Node] = {
     tryOrLogWarning {
       Some(HTMLNode(new URLReader(url, "UTF-8")))
     }
   }

   def tryOrLogWarning(block: =>Option[Node]): Option[Node] = {
     try { block }
     catch {
       case e: Exception => {
         val str = new java.io.StringWriter
         e.printStackTrace(new java.io.PrintWriter(str))
         log.warning(str.toString)
         None
       }
     }
   }

 }
