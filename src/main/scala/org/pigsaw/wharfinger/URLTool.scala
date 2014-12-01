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
}
