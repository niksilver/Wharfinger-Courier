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

  def pando_daily_rss = { """<?xml version="1.0" encoding="UTF-8"?>
                     |<?xml-stylesheet type='text/xsl' href='http://pandodaily.com.feedsportal.com/xsl/eng/rss.xsl'?>
                     |<rss xmlns:content="http://purl.org/rss/1.0/modules/content/" xmlns:slash="http://purl.org/rss/1.0/modules/slash/" xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:taxo="http://purl.org/rss/1.0/modules/taxonomy/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:atom="http://www.w3.org/2005/Atom" xmlns:sy="http://purl.org/rss/1.0/modules/syndication/" xmlns:wfw="http://wellformedweb.org/CommentAPI/" xmlns:media="http://search.yahoo.com/mrss/" version="2.0">
                     |   <channel>
                     |      <atom:link href="http://pandodaily.com/feed/" rel="self" type="application/rss+xml" />
                     |      <title>PandoDaily</title>
                     |      <link>http://pandodaily.com</link>
                     |      <description>the site of record for silicon valley</description>
                     |      <language>en</language>
                     |      <pubDate>Thu, 25 Jul 2013 20:11:49 GMT</pubDate>
                     |      <lastBuildDate>Thu, 25 Jul 2013 20:11:49 GMT</lastBuildDate>
                     |      <ttl>30</ttl>
                     |      <sy:updatePeriod>hourly</sy:updatePeriod>
                     |      <sy:updateFrequency>1</sy:updateFrequency>
                     |      <sy:updateBase>2013-07-25T20:17:16Z</sy:updateBase>
                     |      <image>
                     |         <title>PandoDaily</title>
                     |         <url>http://1.gravatar.com/blavatar/f62ca55a2bb411ae79d3b46aa43f9dc2?s=96&amp;d=http%3A%2F%2Fs2.wp.com%2Fi%2Fbuttonw-com.png</url>
                     |         <link>http://pandodaily.com</link>
                     |      </image>
                     |      <item>
                     |         <title>What the hell is a â€œpatent trollâ€� anyway?</title>
                     |         <link>http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f29c9da/sc/1/l/0Lpandodaily0N0C20A130C0A70C250Cwhat0Ethe0Ehell0Eis0Ea0Epatent0Etroll0Eanyway0C/story01.htm</link>
                     |         <description>What the hell is a patent troll anyways? And how&amp;#8217;d they get stuck with a nickname that connotes dumb, ugly, evil monsters (or weird neon-haired creatures with rhinestones for belly buttons, depending on whom you ask)? Gary Shapiro, president of the Consumer Electronics Association, &lt;a href="http://www.ce.org/News/News-Releases/Press-Releases/2013-Press-Releases/CEA-Commends-Rep-Farenthold-and-Jeffries-on-the-In.aspx"&gt;put&lt;/a&gt; it best, &amp;#8220;Patent trolls [are] companies that do not produce products or services but...&lt;img alt="" border="0" src="http://stats.wordpress.com/b.gif?host=pandodaily.com&amp;#038;blog=30860228&amp;#038;post=97503&amp;#038;subd=pandodaily&amp;#038;ref=&amp;#038;feed=1" width="1" height="1" /&gt;&lt;img width='1' height='1' src='http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f29c9da/sc/1/mf.gif' border='0'/&gt;&lt;div class='mf-viral'&gt;&lt;table border='0'&gt;&lt;tr&gt;&lt;td valign='middle'&gt;&lt;a href="http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&amp;t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/twitter.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&amp;t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/facebook.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&amp;t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/linkedin.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&amp;t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/googleplus.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&amp;t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/email.png" border="0" /&gt;&lt;/a&gt;&lt;/td&gt;&lt;td valign='middle'&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;&lt;br/&gt;&lt;a href="http://da.feedsportal.com/r/172312137559/u/393/f/650422/c/35141/s/2f29c9da/a2.htm"&gt;&lt;img src="http://da.feedsportal.com/r/172312137559/u/393/f/650422/c/35141/s/2f29c9da/a2.img" border="0"/&gt;&lt;/a&gt;&lt;img width="1" height="1" src="http://pi.feedsportal.com/r/172312137559/u/393/f/650422/c/35141/s/2f29c9da/a2t.img" border="0"/&gt;</description>
                     |         <category domain="">iPhone</category>
                     |         <category domain="">lodsys</category>
                     |         <category domain="">Apple</category>
                     |         <category domain="">Patent Troll</category>
                     |         <category domain="">Consumer Electronics Association</category>
                     |         <category domain="">Gary Shapiro</category>
                     |         <category domain="">Rocket Lawyer</category>
                     |         <category domain="">Tech</category>
                     |         <category domain="">patent</category>
                     |         <category domain="">patenttrollsmackdown</category>
                     |         <pubDate>Thu, 25 Jul 2013 20:11:44 GMT</pubDate>
                     |         <comments>http://pandodaily.com/2013/07/25/what-the-hell-is-a-patent-troll-anyway/#comments</comments>
                     |         <guid isPermaLink="false">http://pandodaily.com/?p=97503</guid>
                     |         <content:encoded><![CDATA[<p><img class="alignleft size-full wp-image-97508" alt="trolls" src="http://pandodaily.files.wordpress.com/2013/07/trolls.jpg?w=584" /></p> <p>What the hell is a patent troll anyways? And how&#8217;d they get stuck with a nickname that connotes dumb, ugly, evil monsters (or weird neon-haired creatures with rhinestones for belly buttons, depending on whom you ask)?</p> <p>Gary Shapiro, president of the Consumer Electronics Association, <a href="http://www.ce.org/News/News-Releases/Press-Releases/2013-Press-Releases/CEA-Commends-Rep-Farenthold-and-Jeffries-on-the-In.aspx">put</a> it best, &#8220;Patent trolls [are] companies that do not produce products or services but sue those who do.&#8221; Basically, they&#8217;re organizations that buy up ambiguously worded patents and then use said patents to extort money (by suing or demanding licensing fees) from developers whose product infringes on the claims of the broadly defined patent.</p> <p>Let&#8217;s consider an example. Patent troll Lodsys purchased patents from a man named Dan Abelow in 2004 and is using it to go after iPhone app developers. One particular Abelow-Lodsys <a href="http://patft.uspto.gov/netacgi/nph-Parser?Sect1=PTO1&#38;Sect2=HITOFF&#38;d=PALL&#38;p=1&#38;u=%2Fnetahtml%2FPTO%2Fsrchnum.htm&#38;r=1&#38;f=G&#38;l=50&#38;s1=7222078.PN.&#38;OS=PN/7222078&#38;RS=PN/7222078">patent</a> is described as such in the summary section of the application:</p> <blockquote><p><em>This Customer-Based Product Design Module invention uses a combination of computer hardware, software and communications technologies to construct a module that is built into certain products and services, to establish a network of customer-vendor-distributor interactions and communications (or a network of internal organization-wide interactions in the area of computer-based performance)â€¦ This is the ability to learn interactively and iteratively from the users of products and information systems anywhere in the world while they are in use &#8212; without having to travel to their sites (or without having to bring them to a testing laboratory).</em></p></blockquote> <p>Original inventor Abelow told <a href="http://www.guardian.co.uk/technology/2011/may/13/apple-iphone-developers-app">The Guardian</a>, &#8220;The idea was that if you&#8217;re sitting and holding in your hand a product and you use it, why shouldn&#8217;t it be aware of your behaviour, digitally, and conduct your needs to the vendor, who could interact with you.&#8221; He filed that patent before the Internet was a mainstream thing.</p> <p><a href="http://www.rocketlawyer.com/profiles/view-profile-Michael+Alan+Shimokaji.aspx">Michael Shimokaji</a>, intellectual property lawyer at startup <a href="http://www.rocketlawyer.com/about-us.rl">Rocket Lawyer</a>, explained to me how a broadly defined patent works. There&#8217;s a section of a patent application called &#8220;claims&#8221; that defines the legal boundaries of an idea. The scope of those patent claims can be broader than the demonstration figures shown. And those are the claims that allow patent trolls to sue companies for infringing.</p> <p>In the previously mentioned Lodsys patent, there are 74 legal claims listed, the second of which states:</p> <blockquote><p><em>The system of claim 1 in which the user interface is triggered based on user behaviors to generate two-way interactions with each of the users, each of the interactions relating to a corresponding specific one of the behaviors.&#8221; I don&#8217;t speak legalese, but this claim sounds to me like it could apply to half the Internet.</em></p></blockquote> <p>Lodsys&#8217; patent claims are broad enough that they&#8217;ve already got Apple paying licensing fees for its &#8220;in-app&#8221; purchasing option, and the troll is simultaneously going after a handful of individual developers who use that option. This is why patent trolls get a bad rap &#8212; they&#8217;re warping the rules meant to protect innovators, and profiting from it without developing anything themselves.</p> <p>For more information on patent trolldom, check out this funny, fast-moving video by <a href="http://www.nowthisnews.com/" target="_blank">NowThis News</a> on patent trolls, or read Pando&#8217;s recent <a href="http://pandodaily.com/tag/patentsmackdown/" target="_blank">coverage</a> for our Patent Troll Smackdown series:</p> <iframe width='625' height='400' src='http://p.nowthisnews.com/entry/3053/' frameborder='0' allowfullscreen></iframe> <br /> <img alt="" border="0" src="http://stats.wordpress.com/b.gif?host=pandodaily.com&#038;blog=30860228&#038;post=97503&#038;subd=pandodaily&#038;ref=&#038;feed=1" width="1" height="1" /><img width='1' height='1' src='http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f29c9da/sc/1/mf.gif' border='0'/><div class='mf-viral'><table border='0'><tr><td valign='middle'><a href="http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"><img src="http://res3.feedsportal.com/social/twitter.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"><img src="http://res3.feedsportal.com/social/facebook.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"><img src="http://res3.feedsportal.com/social/linkedin.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"><img src="http://res3.feedsportal.com/social/googleplus.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fwhat-the-hell-is-a-patent-troll-anyway%2F&t=What+the+hell+is+a+%E2%80%9Cpatent+troll%E2%80%9D+anyway%3F" target="_blank"><img src="http://res3.feedsportal.com/social/email.png" border="0" /></a></td><td valign='middle'></td></tr></table></div><br/><br/><a href="http://da.feedsportal.com/r/172312137559/u/393/f/650422/c/35141/s/2f29c9da/a2.htm"><img src="http://da.feedsportal.com/r/172312137559/u/393/f/650422/c/35141/s/2f29c9da/a2.img" border="0"/></a><img width="1" height="1" src="http://pi.feedsportal.com/r/172312137559/u/393/f/650422/c/35141/s/2f29c9da/a2t.img" border="0"/>]]></content:encoded>
                     |         <slash:comments>0</slash:comments>
                     |         <wfw:commentRss>http://pandodaily.com/2013/07/25/what-the-hell-is-a-patent-troll-anyway/feed/</wfw:commentRss>
                     |         <dc:creator>Carmel DeAmicis</dc:creator>
                     |         <media:thumbnail url="http://pandodaily.files.wordpress.com/2013/07/trolls.jpg?w=100" />
                     |         <media:content lang="" url="http://pandodaily.files.wordpress.com/2013/07/trolls.jpg?w=100">
                     |            <media:title type="html">trolls</media:title>
                     |         </media:content>
                     |         <media:content lang="" url="http://1.gravatar.com/avatar/d522a793c968cdb277a524fe2b4494c2?s=96&amp;d=identicon&amp;r=G">
                     |            <media:title type="html">carmeldee</media:title>
                     |         </media:content>
                     |         <media:content lang="" url="http://pandodaily.files.wordpress.com/2013/07/trolls.jpg">
                     |            <media:title type="html">trolls</media:title>
                     |         </media:content>
                     |      </item>
                     |      <item>
                     |         <title>Drawbridge now allows mobile marketers to track anonymous users across devices</title>
                     |         <link>http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f2934c1/sc/5/l/0Lpandodaily0N0C20A130C0A70C250Cdrawbridge0Enow0Eallows0Emobile0Emarketers0Eto0Etrack0Eusers0Eanonymous0Eacross0Edevices0C/story01.htm</link>
                     |         <description>The conversation around mobile these days most often comes down to one thing: Monetization. From Web giants like Google and &lt;a href="http://pandodaily.com/2013/07/24/how-facebook-bucked-the-trend-of-disappointing-earnings/"&gt;Facebook&lt;/a&gt; to indie developers, everyone is looking for solutions for generating revenue on devices with limited screen sizes and dramatically different usage patterns, relative to the desktop Web, without damaging the user experience. Today, machine learning-based ad targeting startup...&lt;img alt="" border="0" src="http://stats.wordpress.com/b.gif?host=pandodaily.com&amp;#038;blog=30860228&amp;#038;post=97490&amp;#038;subd=pandodaily&amp;#038;ref=&amp;#038;feed=1" width="1" height="1" /&gt;&lt;img width='1' height='1' src='http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f2934c1/sc/5/mf.gif' border='0'/&gt;&lt;div class='mf-viral'&gt;&lt;table border='0'&gt;&lt;tr&gt;&lt;td valign='middle'&gt;&lt;a href="http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&amp;t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/twitter.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&amp;t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/facebook.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&amp;t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/linkedin.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&amp;t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/googleplus.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&amp;t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/email.png" border="0" /&gt;&lt;/a&gt;&lt;/td&gt;&lt;td valign='middle'&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;&lt;br/&gt;&lt;a href="http://da.feedsportal.com/r/172312136258/u/393/f/650422/c/35141/s/2f2934c1/kg/342-363/a2.htm"&gt;&lt;img src="http://da.feedsportal.com/r/172312136258/u/393/f/650422/c/35141/s/2f2934c1/kg/342-363/a2.img" border="0"/&gt;&lt;/a&gt;&lt;img width="1" height="1" src="http://pi.feedsportal.com/r/172312136258/u/393/f/650422/c/35141/s/2f2934c1/kg/342-363/a2t.img" border="0"/&gt;</description>
                     |         <category domain="">Tech</category>
                     |         <pubDate>Thu, 25 Jul 2013 19:14:20 GMT</pubDate>
                     |         <comments>http://pandodaily.com/2013/07/25/drawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices/#comments</comments>
                     |         <guid isPermaLink="false">http://pandodaily.com/?p=97490</guid>
                     |         <content:encoded><![CDATA[<p><img class="alignleft size-full wp-image-97494" alt="smartphone and tablet" src="http://pandodaily.files.wordpress.com/2013/07/smartphone-and-tablet.png?w=584&#038;h=438" width="584" height="438" /></p> <p>The conversation around mobile these days most often comes down to one thing: Monetization. From Web giants like Google and <a href="http://pandodaily.com/2013/07/24/how-facebook-bucked-the-trend-of-disappointing-earnings/">Facebook</a> to indie developers, everyone is looking for solutions for generating revenue on devices with limited screen sizes and dramatically different usage patterns, relative to the desktop Web, without damaging the user experience.</p> <p>Today, machine learning-based ad targeting startup <a href="http://www.drawbrid.ge/">Drawbridge</a> announced a new solution to this ubiquitous problem. The company has been testing its new <a href="http://pandodaily.com/2012/11/15/beyond-mobile-ads-drawbridge-talks-cross-device/">cross-device mobile-to-mobile retargeting</a> solution for several months in private beta, and today makes the platform available to the public.</p> <p>Tracking users activity online was far easier when the average user conducted the vast majority of their Web browsing activity on a single PC, and cookies could easily track browsing during and across sessions. But as users began using multiple devices, including mobile devices which cannot be tracked nearly as effectively using cookies, this became a much less simple proposition. In its previous solutions, Drawbridge enabled marketers to connect user identity across PC and mobile devices, such that, for example, they could recognize when single user views a banner on a PC and then later downloads the corresponding app on their mobile device. Today&#8217;s new solution extends this functionality between mobile devices.</p> <p>Drawbridge&#8217;s cross-device mobile-to-mobile retargeting product allows marketers to input criteria to define target audiences, such as lapsed, not-yet-activated, or most loyal users, among current and previous mobile users, and then target marketing campaigns based on this identity.</p> <p>Since its launch, Drawbridge has specialized in using big data analytics to parse billions of ad requests based on device ID, device type, browser type, IP address, and time to observe the online behavior of an individual user across both the desktop and mobile Web. But this technology has historically been limited. All data coming into the Drawbridge system from ad networks is anonymized, and as a result the system has only been able to offer 60 to 70 percent accuracy, on average. Cross-device tracing purportedly offers dramatically more accurate results.</p> <p>Drawbridge is claiming some impressive results from its early trials, although its data set is certainly limited in size. The company claims that marketers targeting &#8220;lapsed users&#8221; have achieved over 100 percent Return on Ad Spend (ROAS) &#8212;  incremental profitability, as compared to the cost of the ad campaign &#8212; in under three weeks. Moreover, marketers targeting &#8220;active users&#8221; have seen more than 100 percent intraday ROAS. If the company is able to sustain these results across broader deployment, it would easily be one of, if no the most effective mobile marketing technique.</p> <p>â€œMarketing efforts in mobile have been concentrated in new user acquisition, as marketers scrambled in the land grab that mobile offered as a platform,&#8221; explains Drawbridge founder and CEO Kamakshi Sivaramakrishnan. &#8221;However, many marketers have already acquired millions or even tens of millions of users; they now need to turn their attention to tools and techniques that allow better engagement marketing towards this user base.â€�</p> <p>Similar trends have unfolded in the ecommerce sector, where companies spend enormous sums of money to acquire customers with the hope that they will eventually pay back that investment in lifetime value. But without dedicated customer retention and reengagement efforts, this often proves to be an ineffective strategy. Companies like <a href="http://pandodaily.com/2013/02/05/meet-retention-score-the-new-metric-that-predicts-your-customer-churn/">Retention Science</a> have emerged to offer software tools to improve retention rates.</p> <p>Today&#8217;s announcement comes at an interesting time in the user privacy debate, with most of the largest Web companies defending themselves against accusations that they&#8217;ve offered the NSA backdoor access to user data via the <a href="http://www.guardian.co.uk/world/prism">PRISM</a> program. Drawbridge, however relies on anonymized user data that is used in one manner or another by nearly every other ad-tech company. Nonetheless, it&#8217;s a sensitive subject and grabbing headlines around &#8220;user tracking&#8221; will surely raise a few eyebrows. Drawbridge appears to be acting responsibly, and is surely in line with the rest of its industry. Moreover, if the company can do its job effectively, it stands to benefit consumers through more applicable and well-targeted advertising. After all, if we&#8217;re going to be advertised to &#8212; which isn&#8217;t changing any time soon &#8212; it might as well be with relevant and appealing ads.</p> <p>Drawbridge completed a $14 million Series B financing round in February that reportedly valued the company at an <a href="http://techcrunch.com/2013/02/22/drawbridge-series-b/">$85 million pre-money valuation</a>. The round included Northgate Capital, Sequoia Capital, and Kleiner Perkins Caufield &#38; Byers. The company was reportedly operating at an $8 million per month revenue run rate at the time of this financing.</p> <div id="author-info"> <h3>Michael Carney</h3> <div style="float:left; margin:0 10px 10px 0;"> <img width="100" height="100" src="http://pandodaily.files.wordpress.com/2011/03/mcarney-headshot-11-12-maui-iii.png?w=100&#038;h=100" class="attachment-thumbnail wp-post-image" alt="MCarney Headshot 11.12 Maui III" /> </div> Michael Carney has spent his career exploring the world of early stage technology as an investor and entrepreneur and has participated in building companies in multiple countries within North and South America and Asia. Ultimately, he is an enthusiast of all things shiny and electronic and is inspired by those who build businesses and regularly tackle difficult problems. You can follow Michael on Twitter <a href="https://twitter.com/#!/mcarney">@mcarney</a>. </div><!-- #author-info --> <br /> <img alt="" border="0" src="http://stats.wordpress.com/b.gif?host=pandodaily.com&#038;blog=30860228&#038;post=97490&#038;subd=pandodaily&#038;ref=&#038;feed=1" width="1" height="1" /><img width='1' height='1' src='http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f2934c1/sc/5/mf.gif' border='0'/><div class='mf-viral'><table border='0'><tr><td valign='middle'><a href="http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"><img src="http://res3.feedsportal.com/social/twitter.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"><img src="http://res3.feedsportal.com/social/facebook.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"><img src="http://res3.feedsportal.com/social/linkedin.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"><img src="http://res3.feedsportal.com/social/googleplus.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fdrawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices%2F&t=Drawbridge+now+allows+mobile+marketers+to+track+anonymous+users+across+devices" target="_blank"><img src="http://res3.feedsportal.com/social/email.png" border="0" /></a></td><td valign='middle'></td></tr></table></div><br/><br/><a href="http://da.feedsportal.com/r/172312136258/u/393/f/650422/c/35141/s/2f2934c1/kg/342-363/a2.htm"><img src="http://da.feedsportal.com/r/172312136258/u/393/f/650422/c/35141/s/2f2934c1/kg/342-363/a2.img" border="0"/></a><img width="1" height="1" src="http://pi.feedsportal.com/r/172312136258/u/393/f/650422/c/35141/s/2f2934c1/kg/342-363/a2t.img" border="0"/>]]></content:encoded>
                     |         <slash:comments>3</slash:comments>
                     |         <wfw:commentRss>http://pandodaily.com/2013/07/25/drawbridge-now-allows-mobile-marketers-to-track-users-anonymous-across-devices/feed/</wfw:commentRss>
                     |         <dc:creator>Michael Carney</dc:creator>
                     |         <media:thumbnail url="http://pandodaily.files.wordpress.com/2013/07/smartphone-and-tablet.png?w=100" />
                     |         <media:content lang="" url="http://pandodaily.files.wordpress.com/2013/07/smartphone-and-tablet.png?w=100">
                     |            <media:title type="html">smartphone and tablet</media:title>
                     |         </media:content>
                     |         <media:content lang="" url="http://2.gravatar.com/avatar/59ea267f68726d574d99efe84a85df0e?s=96&amp;d=identicon&amp;r=G">
                     |            <media:title type="html">mcarney7</media:title>
                     |         </media:content>
                     |         <media:content lang="" url="http://pandodaily.files.wordpress.com/2013/07/smartphone-and-tablet.png">
                     |            <media:title type="html">smartphone and tablet</media:title>
                     |         </media:content>
                     |      </item>
                     |      <item>
                     |         <title>Baby steps: Mayerâ€™s acquistion spree doesnâ€™t turn Yahoo around, but it starts to solve core problems</title>
                     |         <link>http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f28ff81/sc/21/l/0Lpandodaily0N0C20A130C0A70C250Cbaby0Esteps0Emayers0Eacquistion0Espree0Edoesnt0Eturn0Eyahoo0Earound0Ebut0Eit0Estarts0Eto0Esolve0Ecore0Eproblems0C/story01.htm</link>
                     |         <description>At one point during the long, painful, multi-year talks between Yahoo and Alibaba and Softbank, there was a very complicated deal on the table that would sell off Yahoo&amp;#8217;s stake in Alibaba. But to avoid taxes, Yahoo would have to reinvest the money in a variety of ways quickly. One of them was M&amp;#38;A. I was talking to a VC...&lt;img alt="" border="0" src="http://stats.wordpress.com/b.gif?host=pandodaily.com&amp;#038;blog=30860228&amp;#038;post=97452&amp;#038;subd=pandodaily&amp;#038;ref=&amp;#038;feed=1" width="1" height="1" /&gt;&lt;img width='1' height='1' src='http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f28ff81/sc/21/mf.gif' border='0'/&gt;&lt;div class='mf-viral'&gt;&lt;table border='0'&gt;&lt;tr&gt;&lt;td valign='middle'&gt;&lt;a href="http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&amp;t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/twitter.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&amp;t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/facebook.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&amp;t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/linkedin.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&amp;t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/googleplus.png" border="0" /&gt;&lt;/a&gt;&amp;nbsp;&lt;a href="http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&amp;t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"&gt;&lt;img src="http://res3.feedsportal.com/social/email.png" border="0" /&gt;&lt;/a&gt;&lt;/td&gt;&lt;td valign='middle'&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;&lt;br/&gt;&lt;a href="http://da.feedsportal.com/r/172312116807/u/393/f/650422/c/35141/s/2f28ff81/a2.htm"&gt;&lt;img src="http://da.feedsportal.com/r/172312116807/u/393/f/650422/c/35141/s/2f28ff81/a2.img" border="0"/&gt;&lt;/a&gt;&lt;img width="1" height="1" src="http://pi.feedsportal.com/r/172312116807/u/393/f/650422/c/35141/s/2f28ff81/a2t.img" border="0"/&gt;</description>
                     |         <category domain="">Tech</category>
                     |         <pubDate>Thu, 25 Jul 2013 18:47:43 GMT</pubDate>
                     |         <comments>http://pandodaily.com/2013/07/25/baby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems/#comments</comments>
                     |         <guid isPermaLink="false">http://pandodaily.com/?p=97452</guid>
                     |         <content:encoded><![CDATA[<p><img class="alignleft wp-image-97491" alt="mayer" src="http://pandodaily.files.wordpress.com/2013/07/mayer1.jpg?w=900&#038;h=675" width="900" height="675" /></p> <p>At one point during the long, painful, multi-year talks between Yahoo and Alibaba and Softbank, there was a very complicated deal on the table that would sell off Yahoo&#8217;s stake in Alibaba. But to avoid taxes, Yahoo would have to reinvest the money in a variety of ways quickly. One of them was M&#38;A. I was talking to a VC at the time who was salivating over the prospect. &#8220;It would be a shot of adrenaline to the heart of Silicon Valley,&#8221; he said.</p> <p>Well, that deal fell through, and a different one was struck. But Yahoo CEO Marissa Mayer has delivered a shot of adrenaline to the heart of Silicon Valley nonetheless with a spending spree that rivals any big turn-around effort I&#8217;ve seen in covering this industry. It has included sexy <a href="http://pandodaily.com/2013/05/20/sorry-nyc-tumblr-selling-to-yahoo-was-not-the-win-we-needed/" target="_blank">consumer products like Tumblr</a> that people wonder how Yahoo will make the most of and not-so-sexy consumer products like <a href="http://pandodaily.com/2013/07/04/is-mayer-aiming-for-a-turnaround-of-the-stock-or-a-turnaround-of-the-business/" target="_blank">the continually pivoting Qwiki</a> that people wonder why Yahoo wanted.</p> <p>Mayer was already popular in Silicon Valley. But now <a href="http://pandodaily.com/2013/05/09/cant-raise-a-series-a-just-sell-yourself-to-yahoo/" target="_blank">she&#8217;s <em>really</em> popular</a>.</p> <p>I will confess, like a lot of people, I still have no idea how all of this stuff will come together to make Yahoo&#8217;s core assets relevant again. People say Mayer is a &#8220;product person,&#8221; but what this product vision is and how it is different from Yahoo&#8217;s status quo has gone <a href="http://pandodaily.com/2013/01/25/marissa-mayers-daily-habit-just-a-catchphrase-or-actual-new-mission/" target="_blank">from vague</a> to crazy-quilt confusing.</p> <p>This is a big company, and its challenge is that a lot of its verticals like Sports and Finance are too good and too popular to kill &#8212; but not good enough to wrap a credible, product-led turn-around story. Yahoo&#8217;s problem has long been that it has too many silos, that it needed to focus. And the better ones are more around media than technology. A Noah&#8217;s Ark of startups doesn&#8217;t seem like the antidote to that.</p> <p>But one thing at a time. While these moves may not herald a bold new monetization plan, product roadmap, or a way to move Yahoo&#8217;s core assets into the 21st century, they are putting some of the pieces into place that could enable those things. And when you&#8217;re starting from as big of a deficit as Yahoo was, you take baby steps.</p> <p>It&#8217;s tempting to think Mayer is flailing around and making moves just to make them, that she&#8217;s putting out press releases to drive the stock price. But if you look closely, you could also argue that she&#8217;s doing the opposite of some slash-and-grab, quick-fix Band-Aid of a turn-around job. She&#8217;s trying to solve Yahoo&#8217;s core cultural problems before she gets to the product and monetization fixes that will really cement a major change at the Sunnyvale giant.</p> <p>Even I have to admit, while I don&#8217;t get the acquisition strategy for its parts, a turnaround in sentiment around Yahoo &#8212; both on Wall Street and in Silicon Valley &#8212; is working. And a few months ago that seemed impossible.</p> <p>Here are some wins she&#8217;s racking up that aren&#8217;t as immediate as revenue that could nonetheless prove important in actually getting this Titanic to float again:</p> <ul> <li><strong>The stock &#8211;</strong> It may seem backwards to a lot of entrepreneurs who prefer building to marketing, but the stock price &#8212; a sometimes arbitrary amalgam of confidence, buzz, and perception&#8211; is key to driving reality in a case like this. A rising stock price does a few key things that matter. It keeps investor pressure off Mayer, so she can actually focus long term, not quarter-to-quarter. Eventually she is going to have to make some bold product bets that will take time to come to fruition. It also gives Yahoo a currency that matters and incentivizes acqui-hires to stay around.</li> <li><strong>People take Yahoo&#8217;s calls again &#8211;</strong> A real nadir in Yahoo&#8217;s ability to do anything meaningful was several years ago when it tried to acquire Yelp, and the <a href="http://techcrunch.com/2010/10/01/the-ugliest-girl-at-the-dance-how-yahoo-destroyed-yelps-google-acquisition/">team balked</a>, because they didn&#8217;t want to work at Yahoo. When you can&#8217;t even acquire talent for a premium, you are hosed.</li> <li><strong>So now Mayer can acquire companies. But aren&#8217;t acquihires a lousy way to get talent?</strong> <strong>&#8211;</strong> Yes. Everyone says these moves are about talent, but the quality of the talent still left at a struggling acqui-hire candidate is a big question mark. Sometimes it&#8217;s excellent. Sometimes it&#8217;s not. If the talent will actually stay is another big if. And how hard that talent will work while it&#8217;s there is yet a third big if. A lot of people refer to their lock-up times at a giant like Yahoo or Google as &#8220;vacation.&#8221; But baby steps. Compared to where Yahoo was in an earlier era, the fact that a company like Tumblr would sell to them with a straight face isan  improvement. Yahoo didn&#8217;t get in this hole overnight, and it won&#8217;t get out of it overnight.</li> <li><strong>Creating a better environment so the new talent (if indeed it&#8217;s great talent) doesn&#8217;t leave &#8211; </strong>It&#8217;s the little things that make up a culture, and Mayer has been steadily focusing on little things in house, while she feverishly does deals to inject more talent. Things like giving the team UP bands, better food, iPhones, and the like. These telegraph to the team that they matter. And those little things do more than raises or empty promises about the future, especially as everyone is talking up the new talent coming in.</li> </ul> <p>Here&#8217;s the thing, though. Her work is not close to done. All those soft or semi-intangible turn-arounds in how Yahoo is regarded by people who can influence its future won&#8217;t be enough to actually turn the company around.</p> <p>The next thing Mayer has to do is cut staff in a big way. Whenever you are trying to change a culture, there are floaters in-house who resist that change. And everyone knows this company is still too bloated. The people invested in the old way things were done at Yahoo will drive the new talent out, if she lets them. There are two reasons a big layoff is &#8212; regrettably &#8212; important: To help change the culture, and to make the company leaner and more profitable.</p> <p>We, and plenty of others, <a href="http://pandodaily.com/2012/07/16/andreessens-advice-to-marissa-mayer-cut-10000-plus-jobs/" target="_blank">have criticized her for not doing this already</a>. But to be fair to Mayer, separating wheat from chaff is easier said than done. By now, she&#8217;s been at the company long enough to assess things, and her new rules about people coming into the office have likely helped managers determine who is pulling their weight, and who is not.</p> <p>A big layoff before bringing in a lot of talent, and doing little things to make the people she wants to keep feel more appreciated, could be a major morale blow to a company that doesn&#8217;t need another major morale blow. By delaying it, you could argue, she&#8217;s succeeded in making Yahoo a place people will fight to keep a job at.</p> <p>[Image courtesy <a href="http://www.flickr.com/photos/fortunelivemedia/">Fortune Live Media</a>]</p> <div id="author-info"> <h3>Sarah Lacy</h3> <div style="float:left; margin:0 10px 10px 0;"> <img width="100" height="100" src="http://pandodaily.files.wordpress.com/2012/01/sarah_lacy_6x61.jpg?w=100&#038;h=100" class="attachment-thumbnail wp-post-image" alt="Sarah_Lacy_6x6" /> </div> Sarah Lacy is the founder and editor-in-chief of PandoDaily. She is an award winning journalist and author of two critically acclaimed books, "Once You're Lucky, Twice You're Good: The Rebirth of Silicon Valley and the Rise of Web 2.0" (Gotham Books, May 2008) and "Brilliant, Crazy, Cocky: How the Top 1% of Entrepreneurs Profit from Global Chaos" (Wiley, February 2011). She has been covering technology news for over 15 years, most recently as a senior editor for TechCrunch. </div><!-- #author-info --> <br /> <img alt="" border="0" src="http://stats.wordpress.com/b.gif?host=pandodaily.com&#038;blog=30860228&#038;post=97452&#038;subd=pandodaily&#038;ref=&#038;feed=1" width="1" height="1" /><img width='1' height='1' src='http://pandodaily.com.feedsportal.com/c/35141/f/650422/s/2f28ff81/sc/21/mf.gif' border='0'/><div class='mf-viral'><table border='0'><tr><td valign='middle'><a href="http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"><img src="http://res3.feedsportal.com/social/twitter.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"><img src="http://res3.feedsportal.com/social/facebook.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"><img src="http://res3.feedsportal.com/social/linkedin.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"><img src="http://res3.feedsportal.com/social/googleplus.png" border="0" /></a>&nbsp;<a href="http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fpandodaily.com%2F2013%2F07%2F25%2Fbaby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems%2F&t=Baby+steps%3A+Mayer%E2%80%99s+acquistion+spree+doesn%E2%80%99t+turn+Yahoo+around%2C+but+it+starts+to+solve+core+problems" target="_blank"><img src="http://res3.feedsportal.com/social/email.png" border="0" /></a></td><td valign='middle'></td></tr></table></div><br/><br/><a href="http://da.feedsportal.com/r/172312116807/u/393/f/650422/c/35141/s/2f28ff81/a2.htm"><img src="http://da.feedsportal.com/r/172312116807/u/393/f/650422/c/35141/s/2f28ff81/a2.img" border="0"/></a><img width="1" height="1" src="http://pi.feedsportal.com/r/172312116807/u/393/f/650422/c/35141/s/2f28ff81/a2t.img" border="0"/>]]></content:encoded>
                     |         <slash:comments>1</slash:comments>
                     |         <wfw:commentRss>http://pandodaily.com/2013/07/25/baby-steps-mayers-acquistion-spree-doesnt-turn-yahoo-around-but-it-starts-to-solve-core-problems/feed/</wfw:commentRss>
                     |         <dc:creator>Sarah Lacy</dc:creator>
                     |         <media:thumbnail url="http://pandodaily.files.wordpress.com/2013/07/mayer1.jpg?w=100" />
                     |         <media:content lang="" url="http://pandodaily.files.wordpress.com/2013/07/mayer1.jpg?w=100">
                     |            <media:title type="html">mayer</media:title>
                     |         </media:content>
                     |         <media:content lang="" url="http://2.gravatar.com/avatar/2e197a9560dd191a6039c8c650f7f98f?s=96&amp;d=identicon&amp;r=G">
                     |            <media:title type="html">pandosarahlacy</media:title>
                     |         </media:content>
                     |         <media:content lang="" url="http://pandodaily.files.wordpress.com/2013/07/mayer1.jpg">
                     |            <media:title type="html">mayer</media:title>
                     |         </media:content>
                     |      </item>
                    |   </channel>
                     |</rss>
                     |""".stripMargin }
}