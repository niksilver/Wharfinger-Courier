package org.pigsaw.wharfinger

class TwitterURL(url: String) extends URLString(url) {
  
  def isFromTwitter = { (domain endsWith ".twitter.com") || (domain == "twitter.com") }
  
  def isTwitterStatus = {
    domain == "twitter.com" &&
    {
      val parts = path split "/"
      parts.size >= 4 && parts(0) == "" && parts(2) == "status" && parts(3).nonEmpty
    }
  }
}

object TwitterURL {
  def apply(url: String) = new TwitterURL(url)
}
