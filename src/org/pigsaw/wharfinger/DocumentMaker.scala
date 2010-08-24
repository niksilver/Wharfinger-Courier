package org.pigsaw.wharfinger

import collection.mutable.ListBuffer
import xml.{NodeBuffer, Node}

/**
 * Make a Wharfinger Courier document.
 */

class DocumentMaker {

  val articles = new ListBuffer[Article]

  def add(article: Article) { articles += article }

  def document: Node = {
    val doc = new NodeBuffer
    for (article <- articles) {
      doc += <div class="wharfinger-citation"><blockquote><i>{ article.getCitation }</i></blockquote></div>
    }
    <div>{ doc }</div>
  }
}