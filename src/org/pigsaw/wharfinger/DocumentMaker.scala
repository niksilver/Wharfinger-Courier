package org.pigsaw.wharfinger

import collection.mutable.ListBuffer
import xml.{XML, NodeBuffer, Node}

/**
 * Make a Wharfinger Courier document.
 */

class DocumentMaker(val title: String) {

  val articles = new ListBuffer[Article]

  def add(article: Article) { articles += article }

  def document: Node = {
    val toc = new NodeBuffer
    val main = new NodeBuffer

    toc += <p><a name="TOC"></a><h3>Wharfinger Courier 24 Aug 2010</h3></p>

    for (article <- articles) {
      main += <div class="wharfinger-citation"><blockquote><i>{ asXML(article.getCitation) }</i></blockquote></div>
      main += <div class="wharfinger-content">{ asXML(article.getContent) }</div>
    }
    
    <div>
      <div class="wharfinger-toc">{ toc }</div>
      { main }
    </div>
  }

  private def asXML(str: String): Seq[Node] = XML.loadString("<x>" + str + "</x>").child
}