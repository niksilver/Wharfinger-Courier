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

class URLString (url: String) {

  // Separate
  //   http(s)://domain.name:1234
  // from
  //   /some/page.html?q=z
  private val server_re = """(https?:\/\/[-A-Za-z.:0-9]*)""".r

  val (server, path) = server_re.findFirstIn(url) match {
    case Some(s) => (s, url.drop(s.length))
    case None => ("", url)
  }

  def isBad = (server == "")

  def normalise = new URLString(
    server.toLowerCase +
    (path match {
      case "/" => ""
      case _ => path
    }))

  override def toString = server + path
}

object URLString {

  def apply(url: String) = new URLString(url)
}