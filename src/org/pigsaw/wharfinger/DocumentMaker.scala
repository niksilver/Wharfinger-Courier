package org.pigsaw.wharfinger

import collection.mutable.ListBuffer
import xml.{XML, NodeBuffer, Node}
import java.io.StringReader

/**
 * Make a Wharfinger Courier document.
 */

class DocumentMaker(val title: String) {

  val articles = new ListBuffer[Article]

  def add(article: Article) { articles += article }

  def document: Node = {
    val toc = new NodeBuffer
    val main = new NodeBuffer

    toc += <p><a name="TOC"></a><h3>{ title }</h3></p>

    for (i <- 0 until articles.length;
         article = articles(i);
         chapter_name = "wharfinger-" + (i+1)) {
      toc += <p><a href={ "#" + chapter_name }><h4>{ article.title }</h4></a></p>

      main += <div class="wharfinger-chapter">
                <a name={ chapter_name }></a>
                <div class="wharfinger-citation"><blockquote><i>{ asXML(article.getCitation) }</i></blockquote></div>
                <div class="wharfinger-content">{ asXML(article.getContent) }</div>
              </div>
    }
    
    <div>
      <div class="wharfinger-toc">{ toc }</div>
      { main }
    </div>
  }

  private def asXML(str: String): Seq[Node] = SloppyXMLNodeSeq(new StringReader(str))
}