package org.pigsaw.wharfinger

import java.io.InputStream
import java.net.{HttpURLConnection, URL}
import java.nio.charset.Charset

/**
 * To change this template use File | Settings | File Templates.
 */

class URLReader(val url: String, charset: String)
  extends java.io.BufferedReader(
    new java.io.InputStreamReader(URLReader.makeInputStream(url), Charset.forName(charset)))

object URLReader {

  /** Make input stream from the URL, with useful timeouts.
    */
  def makeInputStream(url: String): InputStream = {
    val url_obj = new java.net.URL(url)
    val connection = url_obj.openConnection
    connection.setConnectTimeout(10*1000)
    connection.setReadTimeout(10*1000)
    connection.getInputStream
  }
}

/**
 * Resolve a URL. Create a new instance using a URL string, then the field
 * <code>URL</code> contains the actual URL after any redirects.
 * Will not (apparently) resolve across HTTP/HTTPS boundaries; this is a
 * security feature of Java. See
 * http://stackoverflow.com/questions/1884230/java-doesnt-follow-redirect-in-urlconnection
 * for more.
 */
class RedirectResolver(url: String) {
  private val url_obj = new URL(url)
  private val connection = url_obj.openConnection.asInstanceOf[HttpURLConnection]
  connection.setRequestMethod("HEAD")
  connection.connect()
  val URL = connection.getURL.toString
}
