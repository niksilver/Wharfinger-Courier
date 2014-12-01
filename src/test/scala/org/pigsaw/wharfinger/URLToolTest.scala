package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers

/**
 * Test URLString and related
 */

class URLToolTest extends FunSpec with Matchers {

  describe("URLTool") {
    it("Should recognise the Twitter home page as from Twitter") {
      URLTool.isFromTwitter("http://twitter.com/") should be (true)
      URLTool.isFromTwitter("https://twitter.com/") should be (true)
    }
    
    it("Should know a non-Twitter URL is not from Twitter") {
      URLTool.isFromTwitter("http://niksilver.com/") should be (false)
    }
    
    it("Should recognise a Twitter subdomained URL is from Twitter") {
      URLTool.isFromTwitter("http://blog.twitter.com/") should be (true)
      URLTool.isFromTwitter("https://blog.twitter.com/") should be (true)
    }
    
    it("Should know an almost-but-not-quite Twitter URL is not from Twitter") {
      URLTool.isFromTwitter("http://prefixtwitter.com/") should be (false)
    }

    it("Should recognise the Twitter home page as from Twitter, despite odd casing") {
      URLTool.isFromTwitter("http://twiTTer.com/") should be (true)
      URLTool.isFromTwitter("https://twiTTer.com/") should be (true)
    }

    it("Should recognise a Twitter subdomained URL is from Twitter, despite odd casing") {
      URLTool.isFromTwitter("http://blog.twiTTer.com/") should be (true)
      URLTool.isFromTwitter("https://blog.twiTTer.com/") should be (true)
    }
  
    it("Should recognise a Twitter status URL") {
      URLTool.isTwitterStatus("https://twitter.com/qwghlm/status/539062683908440065") should be (true)      
      URLTool.isTwitterStatus("https://twitter.com/david_harvey/status/539153903875792896") should be (true)      
    }
  
    it("Should know a close-to-Twitter-status-but-not-quite URL is not a status URL") {
      URLTool.isTwitterStatus("https://xxxxxxx.com/qwghlm/status/539062683908440065") should be (false)      
      URLTool.isTwitterStatus("https://twitter.com/status/539062683908440065") should be (false)      
      URLTool.isTwitterStatus("https://twitter.com/qwghlm/status") should be (false)      
      URLTool.isTwitterStatus("https://twitter.com/qwghlm/status539062683908440065") should be (false)      
      URLTool.isTwitterStatus("https://blog.twitter.com/qwghlm/status/539062683908440065") should be (false)      
    }
    
  }
}