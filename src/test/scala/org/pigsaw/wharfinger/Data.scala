package org.pigsaw.wharfinger

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 06-Aug-2010
 * Time: 22:52:30
 * To change this template use File | Settings | File Templates.
 */

object Data {
  def instapaper_html = { """
  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
      <title>What collapsing
empire loo ...</title>
                  <meta name="viewport" content="width=500" />
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <meta name="robots" content="noindex"/>
      <link rel="icon" href="/images/favicon.png"/>
      <style type="text/css">
          body {
              font-family: Georgia;
              font-size: 16px;
              margin: 0px auto 0px auto;
              width: 500px;                                word-wrap: break-word;
              /* text-rendering: optimizeLegibility; */
          }

          h1 { font-size: 1.3em; }
          h2 { font-size: 1.15em; }
          h3, h4, h5, h6, h7 { font-size: 1.0em; }

          img { border: 0; display: block; margin: 0.5em 0;}
          pre, code { overflow: scroll; }
          #story { clear: both; padding: 0 10px; overflow: hidden; margin-bottom: 40px; }

          .bar {
              color: #555;
              font-family: 'Helvetica';
              font-size: 11pt;
                                  margin: 0 -20px;
                  padding: 10px 0;
                          }
          .top { border-bottom: 2px solid #000; }

                      .top a {
              display: block;
              float: right;
              text-decoration: none;
              font-size: 11px;
              background-color: #eee;
              -webkit-border-radius: 8px;
              -moz-border-radius: 8px;
              padding: 2px 15px;
          }

          #story div {
              margin: 1em 0;
          }

          .bottom {
              border-top: 2px solid #000;
              color: #555;
          }

          .bar a { color: #444; }

          blockquote {
              border-top: 1px solid #bbb;
              border-bottom: 1px solid #bbb;
              margin: 1.5em 0;
              padding: 0.5em 0;
          }
          blockquote.short { font-style: italic; }

          pre {
              white-space: pre-wrap;
          }

          ul.bodytext, ol.bodytext {
            list-style: none;
            margin-left: 0;
            padding-left: 0em;
          }


                      #text_controls {
              color: #555;
              text-align: center;
              font-size: 16px;
          }

              #text_controls a {
                  text-decoration: none;
                  color: #555;
              }
                  </style>

              <script type="text/javascript">
          /* thanks, http://www.quirksmode.org/js/cookies.html */
          function createCookie(name,value,days) {
            if (days) {
              var date = new Date();
              date.setTime(date.getTime()+(days*24*60*60*1000));
              var expires = "; expires="+date.toGMTString();
            }
            else var expires = "";
            document.cookie = name+"="+value+expires+"; path=/";
          }

          function readCookie(name) {
            var nameEQ = name + "=";
            var ca = document.cookie.split(';');
            for(var i=0;i < ca.length;i++) {
              var c = ca[i];
              while (c.charAt(0)==' ') c = c.substring(1,c.length);
              if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
            }
            return null;
          }

          var _fontSize, _fontFamily, _lineHeight, _width;

          function loadDefaults()
          {
              _fontSize = 16;
              _fontFamily = "G";
              _lineHeight = 1.5;
              _width = 500;
          }

          function saveFont()
          {
              applyFont();
              createCookie("fontMetrics", [_fontSize, _fontFamily, _lineHeight, _width].join("_"), 365);
          }

          function loadFont()
          {
              var cookieData = readCookie("fontMetrics");
              if (cookieData && (cookieData = cookieData.split("_")) && cookieData.length == 4) {
                  _fontSize = parseInt(cookieData[0]);
                  _fontFamily = cookieData[1];
                  _lineHeight = parseFloat(cookieData[2]);
                  _width = parseInt(cookieData[3]);
              } else loadDefaults();

              applyFont();
          }

          function applyFont()
          {
              if (_fontSize < 10) _fontSize = 10;
              else if (_fontSize > 48) _fontSize = 48;

              if (_width < 300) _width = 300;
              else if (_width > document.body.clientWidth - 80) _width = document.body.clientWidth - 80;

              if (_lineHeight > 3.0) _lineHeight = 3.0;
              else if (_lineHeight < 1.1) _lineHeight = 1.1;

              var story = document.getElementById('story');

              story.style.fontSize = _fontSize + "px";
              switch (_fontFamily) {
                  case "T": story.style.fontFamily = "Times, 'Times New Roman', serif"; break;
                  case "H": story.style.fontFamily = "Helvetica, Arial, sans-serif"; break;
                  case "V": story.style.fontFamily = "Verdana, sans-serif"; break;
                  default:
                  _fontFamily = "G";
                  story.style.fontFamily = "Georgia, serif"; break;
              }

              document.body.style.width = _width;                story.style.lineHeight = _lineHeight;

              // alert('set font: ' + [_fontSize, _fontFamily, _lineHeight, _width].join("_"));
          }
      </script>
          </head>
  <body onload="loadFont();">
      <div class="bar top">
                          <a href="http://twitter.com/edwardnh/status/20499057346">View original</a>
              <div class="sm">twitter.com</div>
                  </div>

                  <div id="text_controls_toggle" style="float: left; margin-left: 10px; margin-bottom: 30px; height: 20px;">
              <a href="#" style="color: #555;" onclick="document.getElementById('text_controls_toggle').style.display = 'none'; document.getElementById('text_controls').style.display = 'block'; document.getElementById('editing_controls').style.display = 'none'; return false;">
                  <span style="font-size: 9px; letter-spacing: -3px;">A</span>
                  <span style="font-size: 16px;">A</span>
              </a>
          </div>
          <div id="text_controls" style="display: none;">
              <div style="float: left; margin-top: 5px;  margin-left: 10px; margin-bottom: 25px; height: 20px;">

                  <a href="#" style="text-decoration: underline;" onclick="document.getElementById('text_controls').style.display = 'none'; document.getElementById('text_controls_toggle').style.display = 'block'; document.getElementById('editing_controls').style.display = 'block'; return false;">
                      <span style="font-family: Helvetica, Arial, sans-serif; font-size: 11px;">close</span>
                  </a>
              </div>


              <a href="#" onclick="_fontSize--; saveFont(); return false;">&ndash;</a>
                              <span style="font-size: 9px; letter-spacing: -3px;">A</span>
              <span style="font-size: 16px;">A</span>

                              <a href="#" onclick="_fontSize++; saveFont(); return false;">+</a>
              &nbsp;&nbsp;&nbsp;&nbsp;                <a href="#" onclick="_lineHeight -= 0.1; saveFont(); return false;" style="border-top: 1px solid #555; border-bottom: 1px solid #555; font-size: 2px; padding: 0 5px; vertical-align: 4px;">&nbsp;</a>
                              <a href="#" onclick="_lineHeight += 0.1; saveFont(); return false;" style="border-top: 1px solid #555; border-bottom: 1px solid #555; font-size: 3px; padding: 0 5px; vertical-align: 4px;">&nbsp;</a>
              &nbsp;&nbsp;&nbsp;&nbsp;                                <a href="#" onclick="_width -= 20; saveFont(); return false;" style="border-top: 1px solid #555; border-bottom: 1px solid #555; border-left: 6px solid #555; border-right: 6px solid #555; font-size: 8px; padding: 0 2px; vertical-align: 4px;">&nbsp;</a>
                              <a href="#" onclick="_width += 20; saveFont(); return false;" style="border-top: 1px solid #555; border-bottom: 1px solid #555; border-left: 3px solid #555; border-right: 3px solid #555; font-size: 8px; padding: 0 4px; vertical-align: 4px;">&nbsp;</a>
              &nbsp;&nbsp;&nbsp;&nbsp;                                <a href="#" onclick="_fontFamily = 'G'; saveFont(); return false;"  style="font-family: Georgia, serif;">G</a>

                              <a href="#" onclick="_fontFamily = 'T'; saveFont(); return false;"  style="font-family: Times, 'Times New Roman', serif;">T</a>
                              <a href="#" onclick="_fontFamily = 'H'; saveFont(); return false;"  style="font-family: Helvetica, Arial, sans-serif;">H</a>
                              <a href="#" onclick="_fontFamily = 'V'; saveFont(); return false;"  style="font-family: Verdana, sans-serif;">V</a>
              &nbsp;&nbsp;&nbsp;&nbsp;
                                  <a href="#" onclick="loadDefaults(); saveFont(); return false;" style="font-family: Helvetica, Arial, sans-serif; font-size: 11px; vertical-align: 2px; text-decoration: underline;">defaults</a>
                          </div>

      <div id="editing_controls" style="float: right; padding-top: 2px;">

                  </div>

      <div id="story">
  Twitter / Edward Harrison: What collapsing empire loo ...
  <ul id="accessibility" class="offscreen">
<li><a href="http://twitter.com/edwardnh/status/20499057346#content" accesskey="0">Skip past navigation</a></li>
<li>On a mobile phone? Check out <a href="http://m.twitter.com/">m.twitter.com</a>!</li>
<li><a href="http://twitter.com/edwardnh/status/20499057346#footer" accesskey="2">Skip to navigation</a></li>

  <li><a href="http://twitter.com/edwardnh/status/20499057346#signin">Skip to sign in form</a></li>
</ul>
<a href="http://twitter.com/" title="Twitter / Home" accesskey="1" id="logo">
</a>
<ul class="top-navigation round">
  <li><a href="http://twitter.com/login" accesskey="l">Login</a></li>
  <li class="signup-link"><a href="http://twitter.com/signup">Join Twitter!</a></li>
</ul>
                What collapsing empire looks like - Glenn Greenwald - Salon <a href="http://bit.ly/cOuqKq" class="tweet-url web" rel="nofollow" target="_blank">http://bit.ly/cOuqKq</a>
<a class="entry-date" rel="bookmark" href="http://twitter.com/edwardnh/status/20499057346">
  31 minutes ago</a>
via <a href="http://seesmic.com/seesmic_mobile/android/" rel="nofollow">Seesmic for Android</a>
    <a href="http://twitter.com/edwardnh" class="tweet-url profile-pic" hreflang="en"></a>
    <a href="http://twitter.com/edwardnh" class="tweet-url screen-name" hreflang="en" title="Edward Harrison">edwardnh</a>
            Edward Harrison
    <h3 class="offscreen">Footer</h3>
    <ul class="footer-nav">
        <li class="first">&copy; 2010 Twitter</li>
        <li><a href="http://twitter.com/about">About Us</a></li>

        <li><a href="http://twitter.com/about/contact">Contact</a></li>
        <li><a href="http://blog.twitter.com">Blog</a></li>
        <li><a href="http://status.twitter.com">Status</a></li>
                    <li><a href="http://twitter.com/goodies">Goodies</a></li>
                  <li><a href="http://dev.twitter.com/">API</a></li>
                    <li><a href="http://business.twitter.com/twitter101">Business</a></li>

                  <li><a href="http://support.twitter.com">Help</a></li>
        <li><a href="http://twitter.com/jobs">Jobs</a></li>
        <li><a href="http://twitter.com/tos">Terms</a></li>
        <li><a href="http://twitter.com/privacy">Privacy</a></li>
    </ul>
</div>
      <div class="bar bottom">
                          &#xab; <a href="/u">Back to Instapaper</a>
  </body>
  </html>
          """ }

  def twitter_times_nieman_lab_item = { <item>
			<title><![CDATA[Who clicks more on local news, New York or Omaha? Surprising data from the FCC on local online news  \u00bb  Nieman Journalism Lab \u00bb Pushing to the Future of Journalism]]></title>
			<pubDate>Fri, 17 Jun 2011 07:15:46 -0700</pubDate>
			<guid isPermaLink='false'><![CDATA[main::user.14697612::1308320146841]]></guid>
			<link><![CDATA[http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news]]></link>
			<description><![CDATA[<div style='margin: 0 0 6px 0;'>
		<a  href='http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news' style='color: #666666; text-decoration: none;'>niemanlab.org</a>
		<span style='color: #666666; text-decoration: none;'>- Joshua Benton</span>
	</div>
	<div>
		<div>
			<img src='http://www.niemanlab.org/images/fcc_newlogo.png' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
		</div>
		<div style='margin: 6px 0 0 0;'>While one can argue with some of the interpretations, Matthew Hindman&rsquo;s new study on how local online news is consumed gives us interesting data on reader behavior. And beyond the headline &mdash; people don&rsquo;t consume a lot of local online news &mdash; there&rsquo;s also an interesting data set that goes beyond national generalizations to individual markets.
Below, I&rsquo;ve broken out three slices of that data set that get at the same question: How does local online news consumption differ from community to communit...&nbsp; <a href='http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
		</div>
	</div>
	<div style='margin: 3px 0pt 0pt;'>
	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends:</strong>
		&nbsp;<span>(0)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
	</div>

	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends of friends:</strong>
		&nbsp;<span>(5)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/steverubel'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/1229138456/image_normal.jpg' alt='@steverubel on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/steverubel' style='color:#093D72; text-decoration: none; font-weight: bold;'>@steverubel:</a> Who clicks more on local news, New York or Omaha?  Surprising data from the FCC on local online news <a href="http://j.mp/mvNAS4" rel="nofollow">http://j.mp/mvNAS4</a>		</div>		<div>			<a href='http://twitter.com/steverubel/status/81648445072474112' style='color:#666666; font-size:11px; text-decoration: none;'>17.06.2011 02.04.40</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/Digidave'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/1324449574/david_normal.gif' alt='@Digidave on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/Digidave' style='color:#093D72; text-decoration: none; font-weight: bold;'>@Digidave:</a> I'm reading: Who clicks more on local news, New York or Omaha? Surprising data from the FCC on local online news <a href="http://bit.ly/iZSzvT" rel="nofollow">http://bit.ly/iZSzvT</a>		</div>		<div>			<a href='http://twitter.com/Digidave/status/81610704607641600' style='color:#666666; font-size:11px; text-decoration: none;'>16.06.2011 23.34.42</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/mediagazer'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/740681844/mediaGazer_twitter2white_normal.png' alt='@mediagazer on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/mediagazer' style='color:#093D72; text-decoration: none; font-weight: bold;'>@mediagazer:</a> Who clicks more on local news, New York or Omaha? Surprising data from the FCC on local online... <a href="http://j.mp/k3VucE" rel="nofollow">http://j.mp/k3VucE</a> <a href="http://mgzr.us/BfkM" rel="nofollow">http://mgzr.us/BfkM</a>		</div>		<div>			<a href='http://twitter.com/mediagazer/status/81491434666070016' style='color:#666666; font-size:11px; text-decoration: none;'>16.06.2011 15.40.46</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/NiemanLab'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/208769717/TwitterNgood_normal.png' alt='@NiemanLab on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/NiemanLab' style='color:#093D72; text-decoration: none; font-weight: bold;'>@NiemanLab:</a> What makes local news consumption different in Salt Lake City, Omaha or New York? More on the FCC data <a href="http://nie.mn/iwiZEM" rel="nofollow">http://nie.mn/iwiZEM</a>		</div>		<div>			<a href='http://twitter.com/NiemanLab/status/81456052301873152' style='color:#666666; font-size:11px; text-decoration: none;'>16.06.2011 13.20.10</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/Chanders'><img style='32px; height: 32px; border: none;' src='http://a1.twimg.com/profile_images/1127279279/Picture_19_normal.png' alt='@Chanders on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/Chanders' style='color:#093D72; text-decoration: none; font-weight: bold;'>@Chanders:</a> @<a class="tweet-url username" href="http://twitter.com/jbenton" rel="nofollow">jbenton</a> So as soon as I saw the Salt Lake numbers I thought of the Tow report too. Any connection, or a coincidence? <a href="http://bit.ly/jo99XR" rel="nofollow">http://bit.ly/jo99XR</a>		</div>		<div>			<a href='http://twitter.com/Chanders/status/81367166078824449' style='color:#666666; font-size:11px; text-decoration: none;'>16.06.2011 07.26.58</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/NiemanLab'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/208769717/TwitterNgood_normal.png' alt='@NiemanLab on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/NiemanLab' style='color:#093D72; text-decoration: none; font-weight: bold;'>@NiemanLab:</a> When you dig into the FCC local online news data, who consumes more news? <a href="http://nie.mn/iwiZEM" rel="nofollow">http://nie.mn/iwiZEM</a>		</div>		<div>			<a href='http://twitter.com/NiemanLab/status/81365487015038976' style='color:#666666; font-size:11px; text-decoration: none;'>16.06.2011 07.20.18</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
	</div>

	</div>
	<div style='clear:both; margin: 6px 0 0 0;'>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://twitter.com/share?url=http%3A%2F%2Fwww.niemanlab.org%2F2011%2F06%2Fwho-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news&text=Who+clicks+more+on+local+news%2C+New+York+or+Omaha%3F+Surprising+data+from+the+FCC+on+local+online+news++%C2%BB++Nieman+Journalism+Lab+%C2%BB+Pushing+to+the+Future+of+Journalism&via=twttimes'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/twitter-share.png'/>
		</a>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://facebook.com/sharer.php?u=http%3A%2F%2Fwww.niemanlab.org%2F2011%2F06%2Fwho-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news&t=Who+clicks+more+on+local+news%2C+New+York+or+Omaha%3F+Surprising+data+from+the+FCC+on+local+online+news++%C2%BB++Nieman+Journalism+Lab+%C2%BB+Pushing+to+the+Future+of+Journalism'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/facebook-share.png'/>
		</a>
	</div>
]]></description>
		</item> }

  def twitter_times_nieman_lab_item_with_2_tweeters = { <item>
			<title><![CDATA[Who clicks more on local news, New York or Omaha? Surprising data from the FCC on local online news  \u00bb  Nieman Journalism Lab \u00bb Pushing to the Future of Journalism]]></title>
			<pubDate>Fri, 17 Jun 2011 07:15:46 -0700</pubDate>
			<guid isPermaLink='false'><![CDATA[main::user.14697612::1308320146841]]></guid>
			<link><![CDATA[http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news]]></link>
			<description><![CDATA[<div style='margin: 0 0 6px 0;'>
		<a  href='http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news' style='color: #666666; text-decoration: none;'>niemanlab.org</a>
		<span style='color: #666666; text-decoration: none;'>- Joshua Benton</span>
	</div>
	<div>
		<div>
			<img src='http://www.niemanlab.org/images/fcc_newlogo.png' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
		</div>
		<div style='margin: 6px 0 0 0;'>While one can argue with some of the interpretations, Matthew Hindman&rsquo;s new study on how local online news is consumed gives us interesting data on reader behavior. And beyond the headline &mdash; people don&rsquo;t consume a lot of local online news &mdash; there&rsquo;s also an interesting data set that goes beyond national generalizations to individual markets.
Below, I&rsquo;ve broken out three slices of that data set that get at the same question: How does local online news consumption differ from community to communit...&nbsp; <a href='http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
		</div>
	</div>
	<div style='margin: 3px 0pt 0pt;'>
	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends:</strong>
		&nbsp;<span>(0)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
	</div>

	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends of friends:</strong>
		&nbsp;<span>(2)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/steverubel'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/1229138456/image_normal.jpg' alt='@steverubel on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/steverubel' style='color:#093D72; text-decoration: none; font-weight: bold;'>@steverubel:</a> Who clicks more on local news, New York or Omaha?  Surprising data from the FCC on local online news <a href="http://j.mp/mvNAS4" rel="nofollow">http://j.mp/mvNAS4</a>		</div>		<div>			<a href='http://twitter.com/steverubel/status/81648445072474112' style='color:#666666; font-size:11px; text-decoration: none;'>17.06.2011 02.04.40</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/Digidave'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/1324449574/david_normal.gif' alt='@Digidave on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/Digidave' style='color:#093D72; text-decoration: none; font-weight: bold;'>@Digidave:</a> I'm reading: Who clicks more on local news, New York or Omaha? Surprising data from the FCC on local online news <a href="http://bit.ly/iZSzvT" rel="nofollow">http://bit.ly/iZSzvT</a>		</div>		<div>			<a href='http://twitter.com/Digidave/status/81610704607641600' style='color:#666666; font-size:11px; text-decoration: none;'>16.06.2011 23.34.42</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
	</div>

	</div>
	<div style='clear:both; margin: 6px 0 0 0;'>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://twitter.com/share?url=http%3A%2F%2Fwww.niemanlab.org%2F2011%2F06%2Fwho-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news&text=Who+clicks+more+on+local+news%2C+New+York+or+Omaha%3F+Surprising+data+from+the+FCC+on+local+online+news++%C2%BB++Nieman+Journalism+Lab+%C2%BB+Pushing+to+the+Future+of+Journalism&via=twttimes'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/twitter-share.png'/>
		</a>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://facebook.com/sharer.php?u=http%3A%2F%2Fwww.niemanlab.org%2F2011%2F06%2Fwho-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news&t=Who+clicks+more+on+local+news%2C+New+York+or+Omaha%3F+Surprising+data+from+the+FCC+on+local+online+news++%C2%BB++Nieman+Journalism+Lab+%C2%BB+Pushing+to+the+Future+of+Journalism'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/facebook-share.png'/>
		</a>
	</div>
]]></description>
		</item> }

  def twitter_times_nieman_lab_item_with_1_tweeter = { <item>
			<title><![CDATA[Who clicks more on local news, New York or Omaha? Surprising data from the FCC on local online news  \u00bb  Nieman Journalism Lab \u00bb Pushing to the Future of Journalism]]></title>
			<pubDate>Fri, 17 Jun 2011 07:15:46 -0700</pubDate>
			<guid isPermaLink='false'><![CDATA[main::user.14697612::1308320146841]]></guid>
			<link><![CDATA[http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news]]></link>
			<description><![CDATA[<div style='margin: 0 0 6px 0;'>
		<a  href='http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news' style='color: #666666; text-decoration: none;'>niemanlab.org</a>
		<span style='color: #666666; text-decoration: none;'>- Joshua Benton</span>
	</div>
	<div>
		<div>
			<img src='http://www.niemanlab.org/images/fcc_newlogo.png' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
		</div>
		<div style='margin: 6px 0 0 0;'>While one can argue with some of the interpretations, Matthew Hindman&rsquo;s new study on how local online news is consumed gives us interesting data on reader behavior. And beyond the headline &mdash; people don&rsquo;t consume a lot of local online news &mdash; there&rsquo;s also an interesting data set that goes beyond national generalizations to individual markets.
Below, I&rsquo;ve broken out three slices of that data set that get at the same question: How does local online news consumption differ from community to communit...&nbsp; <a href='http://www.niemanlab.org/2011/06/who-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
		</div>
	</div>
	<div style='margin: 3px 0pt 0pt;'>
	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends:</strong>
		&nbsp;<span>(0)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
	</div>

	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends of friends:</strong>
		&nbsp;<span>(1)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/steverubel'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/1229138456/image_normal.jpg' alt='@steverubel on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/steverubel' style='color:#093D72; text-decoration: none; font-weight: bold;'>@steverubel:</a> Who clicks more on local news, New York or Omaha?  Surprising data from the FCC on local online news <a href="http://j.mp/mvNAS4" rel="nofollow">http://j.mp/mvNAS4</a>		</div>		<div>			<a href='http://twitter.com/steverubel/status/81648445072474112' style='color:#666666; font-size:11px; text-decoration: none;'>17.06.2011 02.04.40</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
	</div>

	</div>
	<div style='clear:both; margin: 6px 0 0 0;'>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://twitter.com/share?url=http%3A%2F%2Fwww.niemanlab.org%2F2011%2F06%2Fwho-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news&text=Who+clicks+more+on+local+news%2C+New+York+or+Omaha%3F+Surprising+data+from+the+FCC+on+local+online+news++%C2%BB++Nieman+Journalism+Lab+%C2%BB+Pushing+to+the+Future+of+Journalism&via=twttimes'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/twitter-share.png'/>
		</a>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://facebook.com/sharer.php?u=http%3A%2F%2Fwww.niemanlab.org%2F2011%2F06%2Fwho-clicks-more-on-local-news-new-york-or-omaha-surprising-data-from-the-fcc-on-local-online-news&t=Who+clicks+more+on+local+news%2C+New+York+or+Omaha%3F+Surprising+data+from+the+FCC+on+local+online+news++%C2%BB++Nieman+Journalism+Lab+%C2%BB+Pushing+to+the+Future+of+Journalism'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/facebook-share.png'/>
		</a>
	</div>
]]></description>
		</item> }

  def twitter_times_item_with_username_link = { <item>
			<title><![CDATA[Internet freedom 'is a matter for UN' | Owen Bowcott | Law | guardian.co.uk]]></title>
			<pubDate>Fri, 17 Jun 2011 07:15:46 -0700</pubDate>
			<guid isPermaLink='false'><![CDATA[main::user.14697612::1308320146840]]></guid>
			<link><![CDATA[http://www.guardian.co.uk/law/butterworth-and-bowcott-on-law/2011/jun/17/internet-freedom-matter-un]]></link>
			<description><![CDATA[<div style='margin: 0 0 6px 0;'>
		<a  href='http://www.guardian.co.uk/law/butterworth-and-bowcott-on-law/2011/jun/17/internet-freedom-matter-un' style='color: #666666; text-decoration: none;'>guardian.co.uk</a>
	</div>
	<div>
		<div>
			<img src='http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2011/6/17/1308301857741/Thomas-Hammarberg-003.jpg' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
		</div>
		<div style='margin: 6px 0 0 0;'>Owen Bowcott: Council of Europe's head of human rights says privacy and free speech are global issues and the UN should look at them		</div>
	</div>
	<div style='margin: 3px 0pt 0pt;'>
	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends:</strong>
		&nbsp;<span>(1)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/SiobhainB'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/1323862912/siobhain_butterworth__normal.jpg' alt='@SiobhainB on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/SiobhainB' style='color:#093D72; text-decoration: none; font-weight: bold;'>@SiobhainB:</a> Internet freedom 'is a matter for UN' | Owen Bowcott <a href="http://t.co/lM0xZly" rel="nofollow">http://t.co/lM0xZly</a> via @<a class="tweet-url username" href="http://twitter.com/guardian" rel="nofollow">guardian</a>		</div>		<div>			<a href='http://twitter.com/SiobhainB/status/81658795847200768' style='color:#666666; font-size:11px; text-decoration: none;'>17.06.2011 02.45.48</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
	</div>

	<div style='clear: both; margin: 3px 0pt 0pt;'>
		<strong>posted by friends of friends:</strong>
		&nbsp;<span>(2)</span>
	</div>
	<div style='margin: 0 0 0 15px'>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/perreau'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/72092043/ben_normal.jpg' alt='@perreau on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/perreau' style='color:#093D72; text-decoration: none; font-weight: bold;'>@perreau:</a> Internet freedom 'is a matter for UN' says Thomas Hammarberg, the Council of Europe's commissioner for human rights. <a href="http://t.co/5XB3UT9" rel="nofollow">http://t.co/5XB3UT9</a>		</div>		<div>			<a href='http://twitter.com/perreau/status/81658461099794432' style='color:#666666; font-size:11px; text-decoration: none;'>17.06.2011 02.44.28</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
		<div style='clear:both; margin: 3px 0px 0px 0px;'>
		<div style='float: left; 32px; height: 32px;'><a href='http://twitter.com/guardiantech'><img style='32px; height: 32px; border: none;' src='http://a0.twimg.com/profile_images/77733135/guardiantechlogo_normal.jpg' alt='@guardiantech on Twitter'/></a></div>		<div style='margin: 0px 0px 0px 48px'>		<div>			<a href='http://twitter.com/guardiantech' style='color:#093D72; text-decoration: none; font-weight: bold;'>@guardiantech:</a> Internet freedom 'is a matter for UN' | Owen Bowcott <a href="http://bit.ly/lJzGSJ" rel="nofollow">http://bit.ly/lJzGSJ</a>		</div>		<div>			<a href='http://twitter.com/guardiantech/status/81654395615457280' style='color:#666666; font-size:11px; text-decoration: none;'>17.06.2011 02.28.19</a> <a href='http://twitter.com'><img style='vertical-align:middle; border:none; width: 12px; height 12px;' alt='Twitter' src='http://tweetedtimes.com/pics/misc/bird_16_gray.png'/></a>
		</div>		</div>
		</div>
	</div>

	</div>
	<div style='clear:both; margin: 6px 0 0 0;'>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://twitter.com/share?url=http%3A%2F%2Fwww.guardian.co.uk%2Flaw%2Fbutterworth-and-bowcott-on-law%2F2011%2Fjun%2F17%2Finternet-freedom-matter-un&text=Internet+freedom+%27is+a+matter+for+UN%27+%7C+Owen+Bowcott+%7C+Law+%7C+guardian.co.uk&via=twttimes'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/twitter-share.png'/>
		</a>
		<a style='text-decoration: none; border: none;' target='_blank' href='http://facebook.com/sharer.php?u=http%3A%2F%2Fwww.guardian.co.uk%2Flaw%2Fbutterworth-and-bowcott-on-law%2F2011%2Fjun%2F17%2Finternet-freedom-matter-un&t=Internet+freedom+%27is+a+matter+for+UN%27+%7C+Owen+Bowcott+%7C+Law+%7C+guardian.co.uk'>
			<img style='border: none' src='http://tweetedtimes.com/pics/buttons/facebook-share.png'/>
		</a>
	</div>
]]></description>
		</item> }

  def twitter_times_two_bad_items = { <item>
      <title><![CDATA[The Bookseller launches site to oppose library closures | theBookseller.com]]></title>
      <pubDate>Mon, 17 Jan 2011 12:47:51 -0800</pubDate>
      <guid isPermaLink='false'><![CDATA[main::user.14697612::1295297271685]]></guid>
      <link><![CDATA[http://www.thebookseller.com/news/144637-the-bookseller-launches-site-to-oppose-library-closures.html.rss]]></link>

      <description><![CDATA[<div style='margin: 0 0 6px 0;'>
    <a  href='http://www.thebookseller.com/news/144637-the-bookseller-launches-site-to-oppose-library-closures.html.rss' style='color: #666666; text-decoration: none;'>thebookseller.com</a>
  </div>
  <div>
    <div style='margin: 6px 0 0 0;'>The Bookseller has launched a campaign to...		</div>
  </div>
  <div style='margin: 3px 0pt 0pt;'>
  <div style='margin: 3px 0pt 0pt;'>
    <strong>posted by friends:</strong>
    &nbsp;<span>(2)</span>
  </div>
  <div style='margin: 0 0 0 15px;'>
    <div>
      <span><a  href='http://twitter.com/stephen_abbott' style='color:#093D72; text-decoration: none; font-weight: bold;'>stephen abbott</a></span> @<a class="tweet-url username" href="http://twitter.com/Sarah_Crown" rel="nofollow">Sarah_Crown</a> Seen this? RT @<a class="tweet-url username" href="http://twitter.com/martinhearn" rel="nofollow">martinhearn</a>: Bookseller launches site to <a href="http://twitter.com/search?q=%23savelibraries" title="#savelibraries" class="tweet-url hashtag" rel="nofollow">#savelibraries</a> <a href="http://bit.ly/gBL7RR" rel="nofollow">http://bit.ly/gBL7RR</a>&rdquo; (via @<a class="tweet-url username" href="http://twitter.com/Fight4libraries" rel="nofollow">Fight4libraries</a>)&nbsp;
      <span><a  href='http://twitter.com/stephen_abbott/status/27043884815093760' style='color:#666666; font-size:10px; text-decoration: none;'>17.01.2011 08.45.39</a></span>
    </div>
    <div>
      <span><a  href='http://twitter.com/stephen_abbott' style='color:#093D72; text-decoration: none; font-weight: bold;'>stephen abbott</a></span>&nbsp;@<a class="tweet-url username" href="http://twitter.com/JosieLong" rel="nofollow">JosieLong</a> Seen this? RT @<a class="tweet-url username" href="http://twitter.com/martinhearn" rel="nofollow">martinhearn</a>: Bookseller launches site to <a href="http://twitter.com/search?q=%23savelibraries" title="#savelibraries" class="tweet-url hashtag" rel="nofollow">#savelibraries</a> <a href="http://bit.ly/gBL7RR" rel="nofollow">http://bit.ly/gBL7RR</a>&rdquo; (via @<a class="tweet-url username" href="http://twitter.com/Fight4libraries" rel="nofollow">Fight4libraries</a>)&nbsp;
      <span><a  href='http://twitter.com/stephen_abbott/status/27043857912832001' style='color:#666666; font-size:10px; text-decoration: none;'>17.01.2011 08.45.32</a></span>
    </div>
    <div>
      <span><a  href='http://twitter.com/martinhearn' style='color:#093D72; text-decoration: none; font-weight: bold;'>martinhearn:</a></span>&nbsp;The Bookseller launches site to oppose library closures <a href="http://bit.ly/gBL7RR" rel="nofollow">http://bit.ly/gBL7RR</a>&rdquo; (via @<a class="tweet-url username" href="http://twitter.com/Fight4libraries" rel="nofollow">Fight4libraries</a>)&nbsp;
      <span><a  href='http://twitter.com/martinhearn/status/27042605162307584' style='color:#666666; font-size:10px; text-decoration: none;'>17.01.2011 08.40.34</a></span>
    </div>
  </div>

  <div style='margin: 3px 0pt 0pt;'>
    <strong>posted by friends of friends:</strong>
    &nbsp;<span>(0)</span>
  </div>
  <div style='margin: 0 0 0 15px;'>
  </div>

  </div>
  <div style='margin: 6px 0 0 0;'>
    <a style='text-decoration: none; border: none;' target='_blank' href='http://twitter.com/share?url=http%3A%2F%2Fwww.thebookseller.com%2Fnews%2F144637-the-bookseller-launches-site-to-oppose-library-closures.html.rss&text=The+Bookseller+launches+site+to+oppose+library+closures+%7C+theBookseller.com&via=twttimes'>
      <img style='border: none' src='http://tweetedtimes.com/pics/buttons/twitter-share.png'/>
    </a>
    <a style='text-decoration: none; border: none;' target='_blank' href='http://facebook.com/sharer.php?u=http%3A%2F%2Fwww.thebookseller.com%2Fnews%2F144637-the-bookseller-launches-site-to-oppose-library-closures.html.rss&t=The+Bookseller+launches+site+to+oppose+library+closures+%7C+theBookseller.com'>
      <img style='border: none' src='http://tweetedtimes.com/pics/buttons/facebook-share.png'/>
    </a>
  </div>
]]></description>
    </item> }

}