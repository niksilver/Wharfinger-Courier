package org.pigsaw.wharfinger

import org.scalatest.FunSpec
import org.scalatest.Matchers

/**
 * Test URLString and related
 */

class URLToolTest extends FunSpec with Matchers {

  describe("Twitter recognition") {
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
  
  describe("YouTube video recognition") {
    it("Should recognise a YouTube video") {
      URLTool.isYouTubeVideo("https://www.youtube.com/watch?v=qjbFc_XFVek") should be (true)
      URLTool.isYouTubeVideo("https://www.youtube.com/watch?v=QR7Ze0dNClo") should be (true)
    }
    
    it("Should recognise not-a-YouTube-video as such") {
      URLTool.isYouTubeVideo("https://www.youtube.com/feed/subscriptions") should be (false)
      URLTool.isYouTubeVideo("http://blog.youtube.com/watch?v=QR7Ze0dNClo") should be (false)
      URLTool.isYouTubeVideo("https://www.nottube.com/watch?v=QR7Ze0dNClo") should be (false)
    }
  }
  
  describe("Vimeo video recognition") {
    it("Should recognise a Vimeo video") {
      URLTool.isVimeoVideo("http://vimeo.com/97012707") should be (true)
      URLTool.isVimeoVideo("http://vimeo.com/88769226") should be (true)
      URLTool.isVimeoVideo("http://vimeo.com/88769226?") should be (true)
      URLTool.isVimeoVideo("http://vimeo.com/88769226#") should be (true)
      URLTool.isVimeoVideo("http://vimeo.com/88769226/") should be (true)
    }
    
    it("Should recognise not-a-Vimeo-video as such") {
      URLTool.isVimeoVideo("http://vimeo.com/create") should be (false)
      URLTool.isVimeoVideo("http://twitter.com/88769226") should be (false)
      URLTool.isVimeoVideo("http://blog.vimeo.com/88769226") should be (false)
      URLTool.isVimeoVideo("http://vimeo.com/88769226X") should be (false)
    }
  }
  
  describe("Vine video recognition") {
    it("Should recognise a Vimeo video") {
      URLTool.isVineVideo("https://vine.co/v/b6wxtwrwP7P") should be (true)
      URLTool.isVineVideo("https://vine.co/v/MZeH3Kx5hhe") should be (true)
      URLTool.isVineVideo("https://vine.co/v/b6wxtwrwP7P?") should be (true)
      URLTool.isVineVideo("https://vine.co/v/b6wxtwrwP7P#") should be (true)
      URLTool.isVineVideo("https://vine.co/v/b6wxtwrwP7P/") should be (true)
    }
    
    it("Should recognise not-a-Vine-video as such") {
      URLTool.isVineVideo("https://vine.co/Zach.King") should be (false)
      URLTool.isVineVideo("http://twitter.com/v/b6wxtwrwP7P/") should be (false)
      URLTool.isVineVideo("https://blog.vine.co/v/b6wxtwrwP7P") should be (false)
      URLTool.isVineVideo("https://vine.co/vx/b6wxtwrwP7P") should be (false)
    }
  }
}