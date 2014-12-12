package org.pigsaw.wharfinger

object URLTool {

  def isFromTwitter(url: String) = {
    val u = URLString(url)
    (u.domain endsWith ".twitter.com") || (u.domain == "twitter.com")
  }

  def isTwitterStatus(url: String) = {
    val u = URLString(url)
    u.domain == "twitter.com" &&
      {
        val parts = u.path split "/"
        parts.size >= 4 && parts(0) == "" && parts(2) == "status" && parts(3).nonEmpty
      }
  }

  def isYouTubeVideo(url: String) = {
    val u = URLString(url)
    (u.domain == "www.youtube.com") && (u.path startsWith "/watch?")
  }

  def isVimeoVideo(url: String) = {
    val u = URLString(url)
    val parts = u.path split Array('/', '#', '?')
    (u.domain == "vimeo.com") &&
      (parts.size >= 2) &&
      (parts(0) == "") &&
      isJustDigits(parts(1))
  }
  
  def isJustDigits(str: String) = {
    val digits = """^\d+$""".r
    (digits findFirstIn str).nonEmpty
  }

  def isVineVideo(url: String) = {
    val u = URLString(url)
    (u.domain == "vine.co") && (u.path startsWith "/v/")
  }
}
