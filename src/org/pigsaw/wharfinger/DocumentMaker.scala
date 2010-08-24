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
    val main = new NodeBuffer
    for (article <- articles) {
      main += <div class="wharfinger-citation"><blockquote><i>{ asXML(article.getCitation) }</i></blockquote></div>
      main += <div class="wharfinger-content">{ asXML(article.getContent) }</div>
    }
    <div>{ main }</div>
  }

  private def asXML(str: String): Seq[Node] = XML.loadString("<x>" + str + "</x>").child
}