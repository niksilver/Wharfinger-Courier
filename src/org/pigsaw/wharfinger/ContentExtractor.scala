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
    // 363-373: Make sure there's a body element
    // 377-420: If there are frames, find the biggest one we can access (i.e. is in
    //          the same domain) and make that our main content.
    // 422-433: Remove all the stylesheets and all the style tags outside the head.
    // 435-438: Turn all double-BRs into proper paragraphs
    null
  }

  // 440-514: addFootnotes: Convert links to local anchors in Footnotes section below

  // 573-623: Prepare the article node for display. Clean out any inline styles, iframes,
  //          forms, strip extraneous <p> tags, etc.
  def prepArticle(articleContent: Node) = {
    // 581: Remove all styles
    // 582: Replace multiple <br/>s each with one <br/>
    // 585: Clean-conditionally form tags
    // 586: Remove object tags
    // 587: Remove h1 tags
    // 589-595: If there's only one h2 tag, remove it
    // 596: Remove iframe tags
    // 598: Clean headers fn
    // 601: Clean-conditionally table
    // 602: Clean-conditionally ul tags
    // 603: Clean-conditionally ul div
    // 605-615: Remove all paragraphs that have no text, no img, no embed and no object tags
    // 617-622: Remove any br that's followed immediately by a p
    null
  }

  // Initialise a node with a readability score
  def initialiseNode(n: Node) {
    // 625-669: Node gets an initial score according to its label:
    //          div: 5
    //          pre, td, blockquote: 3
    //          address, ol, ul, dl, dd, dt, li, form: -3
    //          h1-h6, th: -5
  }

  // grabArticle - Using a variety of metrics (content score, classname, element types),
  // find the content that is most likely to be the stuff a user wants to read.
  // Then return it wrapped up in a div.
  // @param page a document to run upon. Needs to be a full document, complete with body.

  def grabArticle(html: Node): Node = {
    //679: WE ARE HERE
    null
  }
}