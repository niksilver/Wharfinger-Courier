package org.pigsaw.wharfinger

/**
 * Filter to determine whether a bookmark should be fetched.
 * E.g. want to avoid YouTube, Flickr, and other non-article pages.
 */

/** Class to separate
 *   http(s)://domain.name:1234
 * from
 *   /some/page.html?q=z
 */

class URLString private (val server: String, val path: String) {

  def this(url: String) = this(url)

  def isBad = (server == "")

  def normalise = new URLString(
    server.toLowerCase,
    path match {
      case "/" => ""
      case _ => path
    })

  override def toString = server + path
}

object URLString {
  // Separate
  //   http(s)://domain.name:1234
  // from
  //   /some/page.html?q=z
  private val separator = """(https?:\/\/[-A-Za-z.:0-9]*)(.*)""".r

  def apply(url: String) = {
    try {
      val separator(server, path) = url
      new URLString(server, path)
    }
    catch {
      case _ => new URLString("", url)
    }
  }
}