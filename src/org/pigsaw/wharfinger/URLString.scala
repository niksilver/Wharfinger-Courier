package org.pigsaw.wharfinger

/**
 * Filter to determine whether a bookmark should be fetched.
 * E.g. want to avoid YouTube, Flickr, and other non-article pages.
 */

class URLString(val server: String, val path: String)

object URLString {
  // Separate
  //   http(s)://domain.name:1234
  // from
  //   /some/page.html?q=z
  private val separator = """(https?:\/\/[-A-Za-z.:0-9]*)(.*)""".r

  def apply(url: String) = {
    val separator(server, path) = url
    new URLString(server, path)
  }
}