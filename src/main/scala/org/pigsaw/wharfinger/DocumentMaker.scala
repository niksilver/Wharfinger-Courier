package org.pigsaw.wharfinger

import Preamble._

/**
 * Make a Wharfinger Courier document.
 * Will only add articles to a maximum length of 740k total.
 * This is to cope with GAE datastore's max entity size.
 */

class DocumentMaker(val title: String,
    val dateline: String,
    val articles: Seq[Article] = Nil,
    val rejected: Seq[Article] = Nil) {

  val maxArticlesLength = 740 * 1024
  
  private lazy val articles_length = articles.foldLeft(0)(_ + _.getContentLength)

  def add(article: Article): DocumentMaker = {
    if (articles_length + article.getContentLength > maxArticlesLength)
      new DocumentMaker(title, dateline, articles, rejected :+ article)
    else
      new DocumentMaker(title, dateline, articles :+ article, rejected)
  }

  def articleSummary: String = {
    (articles.length match {
      case 1 => "1 article"
      case n => n + " articles"
    }) + (rejected.length match {
      case 0 => ""
      case n => ", " + n + " remaining"
    })
  }

  def document: String = {
    val toc = new StringBuilder
    val main = new StringBuilder

    val toc_title = a_name("toc") + a_name("start") + center(h3(title) + h4(dateline)) +
      p_align_right(small(articleSummary))

    for (idx <- 0 until articles.length;
         article = articles(idx);
         chapter_name = "wharfinger-" + (idx+1);
         is_last = (idx == articles.length-1)) {
      toc appendAll a_href("#"+chapter_name, article.title) +
        " " + a_href(article.url, "(&gt;)") +
        small( div_style( "margin-left: 1em",
          i(article.getCitation) + br +
          article.url
        )
      )

      main appendAll div_class("wharfinger-chapter",
        a_name(chapter_name) +
        blockquote(small(i(article.getCitation))) +
        p_align_right(small(article.url)) +
        hr +
        div_class("wharfinger-content", article.getContent)
      )

      if (!is_last)
        main appendAll pagebreak
    }

    html(
      head( title(title) ) +
      body(
        div_class("wharfinger-toc", toc_title + toc) + pagebreak +
        main
      )
    )
  }

  implicit def stringBuilder2String(s: StringBuilder) = s.toString

  private def elt(name: String, content: String) = "<" + name + ">" + content + "</" + name + ">"
  private def open(name: String, attr: Pair[String,String]*) = "<" + name +
    (attr map { a => " " + a._1 + "=\"" + a._2 + "\"" }).mkString + ">"
  private def close(name: String) = "</" + name + ">"

  private def a_name(name: String) = open("a", "name" -> name) + close("a")
  private def a_href(href: String, text: String) = open("a", "href" -> href) + text + close("a")

  private def html(text: String) = elt("html", text)
  private def head(text: String) = elt("head", text)
  private def title(text: String) = elt("title", text)
  private def body(text: String) = elt("body", text)

  private def div(text: String) = elt("div", text)
  private def div_class(name: String, text: String) = open("div", "class" -> name) + text + close("div")
  private def div_style(style: String, text: String) = open("div", "style" -> style) + text + close("div")

  private def blockquote(text: String) = elt("blockquote", text)
  private def center(text: String) = elt("center", text)
  private def i(text: String) = elt("i", text)
  private def p(text: String) = elt("p", text)
  private def p_style(style: String, text: String) = open("p", "style" -> style) + text + close("p")
  private def p_align_right(text: String) = open("p", "align" -> "right") + text + close("p")
  private def br: String = "<br/>"
  private def hr: String = "<hr/>"
  private def small(text: String) = elt("small", text)
  private def dl(text: String) = elt("dl", text)
  private def dt(text: String) = elt("dt", text)
  private def dd(text: String) = elt("dd", text)

  private def h3(text: String) = elt("h3", text)
  private def h4(text: String) = elt("h4", text)

  private def pagebreak: String = "<mbp:pagebreak />"

}