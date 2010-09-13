package org.pigsaw.wharfinger

import xml.Node

/**
 * Tools to extract content from an HTML page.
 * Based on the source of Readability at
 * http://code.google.com/p/arc90labs-readability/source/browse/trunk/js/readability.js?r=162
 */

class ContentExtractor(val html: Node) {

  // Right now we won't deal with:
  // - Next page links
  // - Frames

  val html1 = removeScripts(html)
  val html2 = prepDocument(html1)
  val article_title: String = getArticleTitle(html2)
  val article_content: Node = grabArticle(html2)

  def removeScripts(html: Node): Node = { null }
  def getArticleTitle(html: Node): String = {
    // 273-280: Get document title from the first tag called <title>
    // Now tidy the title...
    // 282-289: If the title has ' - ' or ' | ' then get the text before that, unless it's
    //          less than three words, in which case take what's after it.
    // 290-297: Else if title has ': ' then get the text before that, unless it's
    //          less than three words, in which case take what's after it.
    // 298-305: Else if the title is too big (>150 chars) or too small (<15 chars) and
    //          there's only one H1, then take the text of the H1
    // Trim what we've got, and if it's less than 4 words then go back to what we
    // got originally
    null
  }
  def prepDocument(html: Node): Node = {
    // 358: WE ARE HERE
    null
  }
  def grabArticle(html: Node): Node = { null }
}