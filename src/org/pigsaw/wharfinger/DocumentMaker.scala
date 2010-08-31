package org.pigsaw.wharfinger

import collection.mutable.ListBuffer
import Preamble._

/**
 * Make a Wharfinger Courier document.
 */

class DocumentMaker(val title: String) {

  val articles = new ListBuffer[Article]

  def add(article: Article) { articles += article }

  def document: String = {
    val toc = new StringBuilder
    val main = new StringBuilder

    val toc_title = a_name("TOC") + h3(title)

    for (idx <- 0 until articles.length;
         article = articles(idx);
         chapter_name = "wharfinger-" + (idx+1)) {
      toc appendAll dt( a_href("#"+chapter_name, article.title) ) +
        dd( article.url ) +
        dd( i(article.getCitation) )

      main appendAll div_class("wharfinger-chapter",
        a_name(chapter_name) +
        div_class("wharfinger-content", article.getContent)
      )
    }
    
    div(
      div_class("wharfinger-toc", toc_title + dl(toc)) +
      main
    )
  }

  implicit def stringBuilder2String(s: StringBuilder) = s.toString

  private def elt(name: String, content: String) = "<" + name + ">" + content + "</" + name + ">"
  private def open(name: String, attr: Pair[String,String]*) = "<" + name +
    (attr map { a => " " + a._1 + "=\"" + a._2 + "\"" }).mkString + ">"
  private def close(name: String) = "</" + name + ">"

  private def a_name(name: String) = open("a", "name" -> name) + close("a")
  private def a_href(href: String, text: String) = open("a", "href" -> href) + text + close("a")

  private def div(text: String) = elt("div", text)
  private def div_class(name: String, text: String) = open("div", "class" -> name) + text + close("div")

  private def blockquote(text: String) = elt("blockquote", text)
  private def i(text: String) = elt("i", text)
  private def p(text: String) = elt("p", text)
  private def dl(text: String) = elt("dl", text)
  private def dt(text: String) = elt("dt", text)
  private def dd(text: String) = elt("dd", text)
  private def h3(text: String) = elt("h3", text)
  private def h4(text: String) = elt("h4", text)

}