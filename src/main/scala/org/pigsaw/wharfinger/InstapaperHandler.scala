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
   * the Instapaper header. The one with the content is the first non-empty
   * one after this.
   */
  def getContentDiv: Option[Node] = {
    tryOrLogWarning {
      val Some(html) = getBasicHtml
      val content_bodies = (html \\ "body")
      val body_opt = content_bodies find { b => b.text.trim != "" }
      val story_div_opt = body_opt map { _.bodyToStoryDiv }
      val story_div_opt2 = story_div_opt map { _.removeFontControls }
      val story_div_opt3 = story_div_opt2 map { _.removeFooterControls }
      val story_div_opt4 = story_div_opt3 map { _.removeScriptTags }
      story_div_opt4
    }
  }

  /** Get just what comes out of Instapaper, albeit processed by the Tag Soup XML loader
    */
  def getBasicHtml: Option[Node] = {
    tryOrLogWarning {
      Some(HTMLNode.toNode(new URLReader(url, "UTF-8")))
    }
  }

}
