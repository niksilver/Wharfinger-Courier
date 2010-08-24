package org.pigsaw.wharfinger

import collection.mutable.ListBuffer
import xml.{XML, NodeBuffer, Node}

/**
 * Make a Wharfinger Courier document.
 */

class DocumentMaker {

  val articles = new ListBuffer[Article]

  def add(article: Article) { articles += article }

  def document: Node = {
    val doc = new NodeBuffer
    for (article <- articles) {
      doc += <div class="wharfinger-citation"><blockquote><i>{ asXML(article.getCitation) }</i></blockquote></div>
      doc += <div class="wharfinger-content">{ asXML(article.getContent) }</div>
    }
    <div>{ doc }</div>
  }

  private def asXML(str: String): Seq[Node] = XML.loadString("<x>" + str + "</x>").child
}