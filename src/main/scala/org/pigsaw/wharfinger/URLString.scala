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
  private val server_re = """(https?:\/\/[-A-Za-z.:0-9]*)(.*)""".r
  private val domain_re = """https?:\/\/([-A-Za-z.0-9]*)(:[0-9]*)?""".r

  val (server, path) = url match {
    case server_re(s, p) => (s, p)
    case _ => ("", url)
  }

  val domain = server match {
    case domain_re(d, _) => d.toLowerCase
    case _ => ""
  }

  def isBad = (server == "")

  def normalise = new URLString(normalisedServer + normalisedPath)

  def normalisedServer = server.toLowerCase
  
  def normalisedPath = path match {
      case "/" => ""
      case _ => path
    }

  override def toString = server + path
}

object URLString {

  def apply(url: String) = new URLString(url)
}
