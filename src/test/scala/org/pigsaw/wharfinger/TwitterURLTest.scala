package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers

/**
 * Test URLString and related
 */

class TwitterURLTest extends FunSpec with Matchers {

  describe("TwitterURL") {
    it("Should recognise the Twitter home page as from Twitter") {
      TwitterURL("http://twitter.com/").isFromTwitter should be (true)
      TwitterURL("https://twitter.com/").isFromTwitter should be (true)
    }
    
    it("Should know a non-Twitter URL is not from Twitter") {
      TwitterURL("http://niksilver.com/").isFromTwitter should be (false)
    }
    
    it("Should recognise a Twitter subdomained URL is from Twitter") {
      TwitterURL("http://blog.twitter.com/").isFromTwitter should be (true)
      TwitterURL("https://blog.twitter.com/").isFromTwitter should be (true)
    }
    
    it("Should know an almost-but-not-quite Twitter URL is not from Twitter") {
      TwitterURL("http://prefixtwitter.com/").isFromTwitter should be (false)
    }

    it("Should recognise the Twitter home page as from Twitter, despite odd casing") {
      TwitterURL("http://twiTTer.com/").isFromTwitter should be (true)
      TwitterURL("https://twiTTer.com/").isFromTwitter should be (true)
    }

    it("Should recognise a Twitter subdomained URL is from Twitter, despite odd casing") {
      TwitterURL("http://blog.twiTTer.com/").isFromTwitter should be (true)
      TwitterURL("https://blog.twiTTer.com/").isFromTwitter should be (true)
    }
  
    it("Should recognise a Twitter status URL") {
      TwitterURL("https://twitter.com/qwghlm/status/539062683908440065").isTwitterStatus should be (true)      
      TwitterURL("https://twitter.com/david_harvey/status/539153903875792896").isTwitterStatus should be (true)      
    }
  
    it("Should know a close-to-Twitter-status-but-not-quite URL is not a status URL") {
      TwitterURL("https://xxxxxxx.com/qwghlm/status/539062683908440065").isTwitterStatus should be (false)      
      TwitterURL("https://twitter.com/status/539062683908440065").isTwitterStatus should be (false)      
      TwitterURL("https://twitter.com/qwghlm/status").isTwitterStatus should be (false)      
      TwitterURL("https://twitter.com/qwghlm/status539062683908440065").isTwitterStatus should be (false)      
      TwitterURL("https://blog.twitter.com/qwghlm/status/539062683908440065").isTwitterStatus should be (false)      
    }
    
  }
}