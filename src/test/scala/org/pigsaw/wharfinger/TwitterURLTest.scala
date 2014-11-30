package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers

/**
 * Test URLString and related
 */

class TwitterURLTest extends FunSpec with Matchers {

  describe("TwitterURL") {
    it("Should recognise the Twitter home page as from Twitter") {
      TwitterURL.isFromTwitter("http://twitter.com/") should be (true)
      TwitterURL.isFromTwitter("https://twitter.com/") should be (true)
    }
    
    it("Should know a non-Twitter URL is not from Twitter") {
      TwitterURL.isFromTwitter("http://niksilver.com/") should be (false)
    }
    
    it("Should recognise a Twitter subdomained URL is from Twitter") {
      TwitterURL.isFromTwitter("http://blog.twitter.com/") should be (true)
      TwitterURL.isFromTwitter("https://blog.twitter.com/") should be (true)
    }
    
    it("Should know an almost-but-not-quite Twitter URL is not from Twitter") {
      TwitterURL.isFromTwitter("http://prefixtwitter.com/") should be (false)
    }

    it("Should recognise the Twitter home page as from Twitter, despite odd casing") {
      TwitterURL.isFromTwitter("http://twiTTer.com/") should be (true)
      TwitterURL.isFromTwitter("https://twiTTer.com/") should be (true)
    }

    it("Should recognise a Twitter subdomained URL is from Twitter, despite odd casing") {
      TwitterURL.isFromTwitter("http://blog.twiTTer.com/") should be (true)
      TwitterURL.isFromTwitter("https://blog.twiTTer.com/") should be (true)
    }
  
    it("Should recognise a Twitter status URL") {
      TwitterURL.isTwitterStatus("https://twitter.com/qwghlm/status/539062683908440065") should be (true)      
      TwitterURL.isTwitterStatus("https://twitter.com/david_harvey/status/539153903875792896") should be (true)      
    }
  
    it("Should know a close-to-Twitter-status-but-not-quite URL is not a status URL") {
      TwitterURL.isTwitterStatus("https://xxxxxxx.com/qwghlm/status/539062683908440065") should be (false)      
      TwitterURL.isTwitterStatus("https://twitter.com/status/539062683908440065") should be (false)      
      TwitterURL.isTwitterStatus("https://twitter.com/qwghlm/status") should be (false)      
      TwitterURL.isTwitterStatus("https://twitter.com/qwghlm/status539062683908440065") should be (false)      
      TwitterURL.isTwitterStatus("https://blog.twitter.com/qwghlm/status/539062683908440065") should be (false)      
    }
    
  }
}