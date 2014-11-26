package org.pigsaw.wharfinger

import java.net.URLEncoder
import java.util.logging.Logger
import xml.Node
import org.pigsaw.wharfinger.Preamble._

/**
 * To change this template use File | Settings | File Templates.
 */
class InstapaperHandler(article_url: String) extends WarningTrier[Node] {

  val log = Logger.getLogger(this.getClass.getName)
  val prefix = "https://www.instapaper.com/text?u="
  val url: String = prefix + URLEncoder.encode(article_url, "UTF-8")

  /**
   * There are several body tags in an Instapaper document. The first contains
   * the Instapaper header. The one with the content is the first non-emtpy
   * one after this.
   */
  def getContentDiv: Option[Node] = {
    tryOrLogWarning {
      val Some(html) = getBasicHtml
      val content_bodies = (html \\ "body")
      val body_opt = content_bodies find { b => b.text.trim != "" }
      val story_div_opt = body_opt map { bo => bo.bodyToStoryDiv }
      story_div_opt
    }
  }

  /** Get just what comes out of Instapaper, albeit processed by the Tag Soup XML loader
    */
  def getBasicHtml: Option[Node] = {
    tryOrLogWarning {
      Some(HTMLNode(new URLReader(url, "UTF-8")))
    }
  }

}
