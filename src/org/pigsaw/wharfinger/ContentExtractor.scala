package org.pigsaw.wharfinger

import xml.Node

/**
 * Tools to extract content from an HTML page.
 * Based on the source of Readability at
 * http://code.google.com/p/arc90labs-readability/source/browse/trunk/js/readability.js?r=162
 */

class ContentExtractor(val node: Node) {

  val article_title: Node = getArticleTitle
  val article_content: Node = grabArticle
  def removeScripts: Node = { null }
  def findNextPageLink: String = { null }
  def prepDocument: Node = { null }
  def getArticleTitle: Node = { null }
  def grabArticle: Node = { null }
}