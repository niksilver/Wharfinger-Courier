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

  def delicious_html = { """<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store, must-revalidate, no-cache, private, max-age=0, post-check=0, pre-check=0">
<meta http-equiv="X-Xss-Protection" content="0">
<meta http-equiv="Expires" content="Sun, 1 Jan 2006 01:00:00 GMT">
<meta http-equiv="X-Ua-Compatible" content="IE=7">
    <meta name="robots" content="noarchive,noindex,nofollow">
<meta name="description" content="Keep, share, and discover the best of the Web using Delicious, the world&#039;s leading social bookmarking service.">
<meta name="keywords" content="del.icio.us, delicious, bookmarks, social bookmarking">
<meta name="language" content="en">
    <title>nik.silver's Network   on Delicious</title>

    <link rel="stylesheet" type="text/css" media="screen" href="http://l.yimg.com/hr/14412781/css/del-frontend-min.css">
<!--[if IE]>
<link rel="stylesheet" type="text/css" media="screen" href="http://l.yimg.com/hr/14412781/css/del-ie-min.css">
<![endif]-->
    <script type="text/javascript" src="http://l.yimg.com/hr/14412781/js/del-frontend-min.js"></script>
<!--[if IE]>
<script type="text/javascript" src="http://l.yimg.com/hr/14412781/js/del-ie-min.js"></script>
<![endif]-->
<script type="text/javascript" src="http://l.yimg.com/us.js.yimg.com/lib/mus/js/ymwp/mediaplayerloader_noyui-min-2.0.46.js"></script>
    <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
    <link rel="apple-touch-icon" href="http://static.delicious.com/img/delicious57px.png" />

        <link rel="alternate" type="application/rss+xml" title="RSS feed" href="http://feeds.delicious.com/v2/rss/network/nik.silver?count=15">
  </head>

<body id="index" class="fullpage network  is_firefox is_ff3">

<div id="siteNotice">
    <div class="info">
        <div class="notice">
        <a href="#" id="rkTcp6ynTlaNED0QR9YngA" class="hideAction promo">HIDE</a>
            <p>Did you know you can send your links to friends and services like Twitter? <a href="http://delicious.com/help/faq#inbox"> Find out how to send' bookmarks here.</a</p>
          <div class="clr"></div>

      </div>
    </div>
</div>


<div id="doc3" class="yui-t5 delui-v5">
    <script type="text/javascript">Delicious.Display.init();</script>

    <!-- HEADER -->
<div id="hd">
  <script>Delicious.Config.set('LoggedInUsername', 'nik.silver');</script><div id="header">

    <div id="globalnav">
<ul class="nav">
<li id="signedInAs">Hi, <strong id="signedInName"><a href="/nik.silver">nik.silver</a></strong>    <ul id="userOptions">
                        <li><a href="/logout?jump=https%3A%2F%2Fsecure.delicious.com%2Flogin">Sign in as a different user</a></li>
            </ul>
</li>
<li><a class="inbox" href="/inbox/nik.silver">Inbox</a></li>
<li><a href="http://delicious.com/settings/">Settings</a></li>

<li><a href="/help">Help</a></li>
<li class="last"><a href="http://delicious.com/logout">Sign Out</a></li>
</ul>
</div><!-- #globalnav -->
<script>Delicious.UserOptions.init();</script>
        <div id="banner">
        <div id="nav" class="rapidnofollow">
            <h1 id="site_title"><a href="/">delicious</a></h1>
            <ul class="nav-list">

                <li class="list-item" id="list-item-home">
                    <a href="/" class="list-link" id="list-link-home"><span>Home</span></a>
                </li>
                                <li class="list-item" id="list-item-bookmarks">
                        <a href="/nik.silver" class="list-link" id="list-link-bookmarks"><span>Bookmarks</span></a>
                        <a href="/nik.silver" class="toggle"></a>
                        <ul class="subnav-list" id="subnav-list-bookmarks">
                                                                <li class="sublist-item"><a href="/nik.silver" class="sublist-link first">My Bookmarks</a></li>

                                                                <li class="sublist-item"><a href="/popular/" class="sublist-link">Popular</a></li>
                                <li class="sublist-item"><a href="/recent/" class="sublist-link last">Recent</a></li>
                                <li class="sublist-item"><a href="/url/" class="sublist-link about">Look up a URL</a></li>
                        </ul>
                </li>
                                <li class="list-item" id="list-item-people">
                    <a href="/network/nik.silver" class="list-link" id="list-link-people"><span>People</span></a>

                    <a href="/network/nik.silver" class="toggle"></a>
                    <ul class="subnav-list" id="subnav-list-people">
                        <li class="sublist-item"><a href="/network/nik.silver" class="sublist-link first last">My Network</a></li>
                        <li class="sublist-item"><a href="/user/" class="sublist-link about">Go to a User</a></li>
                    </ul>
                </li>
                                <li class="list-item" id="list-item-tags">
                                                <a href="/tags/nik.silver" class="list-link" id="list-link-tags"><span>Tags</span></a>

                        <a href="/tags/nik.silver" class="toggle"></a>
                                                <ul class="subnav-list" id="subnav-list-tags">
                                                                <li class="sublist-item"><a href="/tags/nik.silver" class="sublist-link">My Tags</a></li>
                                <li class="sublist-item"><a href="/subscriptions/nik.silver" class="sublist-link">My Subscriptions</a></li>
                                                                <li class="sublist-item"><a href="/tag/" class="sublist-link about">Explore</a></li>
                        </ul>
                </li>

            </ul>
        </div>



<form method="get" action="/search" class="searchForm " id="global-searchform">
<div class="search-box" id="global-srchBox">
    <div class="search-input">
                <div id="global-searchChoices" class="searchchoices">
          <label id="global-chooseSearchType" class="chooseSearchType toggle"><em>choose a type</em></label>
          <ul id="global-subnav-list-search" class="subnav-list">
                          <li id="global-search-choice-same" class="search-choice on">Search these bookmarks</li>

                          <li id="global-search-choice-myuserposts" class="search-choice">My bookmarks</li>
              <li id="global-search-choice-mynetwork" class="search-choice">My Network's bookmarks</li>
              <li id="global-search-choice-all" class="search-choice">Everyone's bookmarks</li>
          </ul>
        </div>
                <input type="text" name="p" id="global-searchinput" class="searchinput" size="30" maxlength="255" value="Search Delicious">
    </div>

    <input type="hidden" name="chk" value="">
            <input type="hidden" name="context" class="searchcontext" id="global-searchCntxt" value="network|nik.silver">
                <input type="hidden" name="fr" id="global-searchfr" value="del_icio_us">
    <input type="hidden" id="global-searchLC" class="searchlc" name="lc" value="">
    <input type="submit" id="global-searchsubmit" class="searchsubmit" value="Search">
</div>

<div class="suggestions">

</div>
</form>

<script type="text/javascript">
    Delicious.SearchForm.init('global-searchform', false, 1 , 0);
</script>
    </div><!-- #banner -->

</div><!-- #hd -->
  <script type="text/javascript">Delicious.Navigation.init();</script>
</div><!-- #hd -->



<!-- PAGE TITLE -->
<div id="ttlbar">
  <div id="ttl">
  <div id="pagetitle">

    <div class="Network" id="pagetitleContent">
      <h2><em class="">nik.silver's</em> Network          </h2>
                <p id="alt_message" class="altMsg">
          <a class="" href="/nik.silver">Bookmarks</a> <em>|</em>
          <a class="current" href="/network/nik.silver">Network</a> <em>|</em>
          <a class="" href="/tags/nik.silver">Tags</a> <em>|</em>
          <a class="" href="/subscriptions/nik.silver">Subscriptions</a>

                    <em>|</em> <a class="" href="/inbox/nik.silver">Inbox</a>
                  </p>
            </div>




            <p id="see_also" class="seeAlso">
                        Also see more  bookmarks in <a href="/popular/">Popular</a> or <a href="/recent">Recent</a>.        </p>

      </div>
</div>


<div id="actions">
  <ul id="actions-list">

          <li id="actionSaveABookmark"><a href="/save" id="saveBookmark">Save a new bookmark</a></li>









      <li id="actionAddNetworkUser"><a href="/settings/people/network" id="options-act-addNetwork" class="options rapidnofollow">Add a user to Network</a></li>

      <li id="actionGoToUser"><a id="options-act-user" href="/user/">Go to a user</a></li>





      <li id="actionBrowse"><a href="/browsebar/network/nik.silver" id="options-act-browse">Browse these bookmarks</a> <sup>BETA</sup></li>




  </ul>
</div>


<script type="text/javascript">
  Delicious.Actions.init();
  </script>


</div><!-- #ttl -->

    <!-- MAIN BODY -->
    <div id="bd">

      <div id="yui-main">
        <div id="content" class="yui-b">
        <div id="tagscope">
  <ul id="tagscopenav">
    <li class="scope">
                        <a href="/nik.silver" class="currscope" id="currscope">nik.silver</a>
                </li>

	<li class="tags">

                <ul>
                                              <li class="tag section first">
                      <a href="/network/nik.silver" class="endtag">Network</a>
                  </li>

                                		</ul>
            </li>
        <li class="box">
        <form id="magicboxform" name="magicboxform" action="">

            <input type="hidden" name="tagtype" id="addtagType" value="tag">
            <input type="text" size="25" name="addtag" id="addtag" autocomplete="off" value="Type a tag">
            <input type="submit" name="addtagSubmit" value="" id="addtagSubmit">
        </form>
    </li>
                <li class="type">
        <strong><em><span id="tagScopeCount">10699</span></em> <span id="tagScopeMsg">Bookmarks</span></strong>

    </li>
      </ul>
</div>
<script type="text/javascript">Delicious.TagScopeNav.init();</script>




<div id="displayOptions">
            <div id="bmOpts" class="optsMod">

        <div class="optsModAct"><span id="bmOptsLnk" class="optsModLnk">Display options</span></div>
        <div id="bmOptsList" class="optsList">

            <ul class="toggle off">
                                                <li class="sub"><span><strong>Detail level:</strong>
                    <a href="/network/nik.silver?detail=1" id="blo-small">low</a>,
                    <em>med</em>,
                    <a href="/network/nik.silver?detail=3" id="blo-large">high</a>                </span></li>
                                                <li class="wrap">
                    <span><strong>Display:</strong> <em>10</em>, <a href="/network/nik.silver?setcount=25">25</a>, <a href="/network/nik.silver?setcount=50">50</a>, <a href="/network/nik.silver?setcount=100">100</a><br> bookmarks per page</span>

                </li>
                                                <li class="wrap isAdv">
                    <span><strong>Open bookmarks in:</strong><br>
                    <em>this window</em>,
                    <a href="/network/nik.silver?opennew=1">new window</a>                </li>
                <li class="adv toggle-button"><span class="toggle-button">View advanced options</span></li>
            </ul>

        </div><!-- #bmOptsList -->
    </div><!-- #bmOpts -->
  </div><!-- #displayOptions -->



<ul id="bookmarklist" class="bookmarks NOTHUMB">

<li class="post first  bookmark-0" id="item-692aa00dca94cc0f2cd34ea44ce72ec7-0">
  <div class="bookmark  NOTHUMB">


          <div class="dateGroup">
        <span title="11 AUG 10">

          11 AUG 10        </span>
      </div>


      <span class="jsEnabled action" id="audiofile0"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://www.leedsunited-mad.co.uk/news/tmnw/leeds_40_lincoln_by_sean_markey_aged_11_545231/index.shtml" >Leeds 4-0 Lincoln (By Sean Markey, Aged 11) - LeedsUtdMAD</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fwww.leedsunited-mad.co.uk%2Fnews%2Ftmnw%2Fleeds_40_lincoln_by_sean_markey_aged_11_545231%2Findex.shtml&amp;title=Leeds%204-0%20Lincoln%20%28By%20Sean%20Markey%2C%20Aged%2011%29%20-%20LeedsUtdMAD&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=leedsunited+leedsutdmad">SAVE</a></strong>

		                </em></span>


      </h4>










            <div class="description">
          Very passable match report from an 11 year old :-)      </div>


    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>

      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/leedsunited">leedsunited</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/leedsutdmad">leedsutdmad</a></li>
    </ul>
		  </div>

      <div class="clr"></div>

    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-1" id="item-9691bde7a4660b1d232e148a9ebd2b24-1">
  <div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile1"></span>


    <div class="data">





      <h4>


                  <a rel="nofollow" class="taggedlink  "  href="http://londonist.com/2010/08/hand-drawn_maps_of_london_walthamst.php?gallery0Pic=1#gallery" >Hand-Drawn Maps of London: Walthamstow By Mother And Daughter - Londonist</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Flondonist.com%2F2010%2F08%2Fhand-drawn_maps_of_london_walthamst.php%3Fgallery0Pic%3D1%23gallery&amp;title=Hand-Drawn%20Maps%20of%20London%3A%20Walthamstow%20By%20Mother%20And%20Daughter%20-%20Londonist&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=walthamstow+maps+londonist">SAVE</a></strong>
		                </em></span>


      </h4>










            <div class="description">
          &quot;Sadly, the map highlights for me that I don't have a very wild lifestyle in E17&quot;      </div>


    </div>

    <div class="meta">

                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/walthamstow">walthamstow</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/maps">maps</a></li>

            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/londonist">londonist</a></li>
    </ul>
		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-2" id="item-759a8f03a7635cb94f6f08290d998f8a-2">
  <div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile2"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://www.journalism.co.uk/young-journalists/?p=1094" >Blogging: The skill that begets all others | Tomorrow&#039;s News, Tomorrow&#039;s Journalists</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fwww.journalism.co.uk%2Fyoung-journalists%2F%3Fp%3D1094&amp;title=Blogging%3A%20The%20skill%20that%20begets%20all%20others%20%7C%20Tomorrow%27s%20News%2C%20Tomorrow%27s%20Journalists&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=journalism+journalismcouk+blogging">SAVE</a></strong>

		                </em></span>


      </h4>




      <div class="actions">
      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers1">
          <a class="delNav" href="/url/759a8f03a7635cb94f6f08290d998f8a">
              <span class="delNavCount">8</span>
          </a>

      </div>
      </div>







            <div class="description">
          &quot;If you're a young journalist sitting around wondering what you can do right now to make yourself more attractive to potential employers, here it is: blog. Blogging is very much the basis for all web communication and by blogging regularly, you will incidentally pick up several other skills that will look good on a resume&quot;. Although you will still occasionally run across people going &quot;Urgh! Blogging! Well you clearly can&#039;t be a journalist or do journalistic endeavour on a blog. I mean, what would be point of improving your writing, web communication, social media marketing and community management skills in the early 21st century?&quot;      </div>


    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>




          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/journalism">journalism</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/journalismcouk">journalismcouk</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/blogging">blogging</a></li>

    </ul>
		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-3" id="item-60960c0e184c4c3990856e82298bc512-3">
  <div class="bookmark  NOTHUMB">





      <span class="jsEnabled action" id="audiofile3"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://jonslattery.blogspot.com/2010/08/blast-from-past-how-bournemouth-echo.html" >Jon Slattery: Blast from the past: How Bournemouth Echo tackled the Southampton F.C. photographer ban</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fjonslattery.blogspot.com%2F2010%2F08%2Fblast-from-past-how-bournemouth-echo.html&amp;title=Jon%20Slattery%3A%20Blast%20from%20the%20past%3A%20How%20Bournemouth%20Echo%20tackled%20the%20Southampton%20F.C.%20photographer%20ban&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=jonslattery+bournemouthecho+southamptonfc">SAVE</a></strong>
		                </em></span>


      </h4>











            <div class="description">
          &quot;Today the Echo has illustrated its [Southampton] match report with pictures of a 1987 clash between the two sides&quot;. It is a serious issue, but I am loving the humour and inventiveness with which a bunch of papers are tackling it.      </div>


    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>

      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/jonslattery">jonslattery</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/bournemouthecho">bournemouthecho</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/southamptonfc">southamptonfc</a></li>
    </ul>
		  </div>

      <div class="clr"></div>

    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-4" id="item-f52c6b573ced478eb040713a67ff04ff-4">
  <div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile4"></span>


    <div class="data">





      <h4>


                  <a rel="nofollow" class="taggedlink  "  href="http://www.guardian.co.uk/news/datablog/2010/aug/10/government-data-information-architecture" >The IA behind the World Government Data store | Martin Belam | Guardian Datablog</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fwww.guardian.co.uk%2Fnews%2Fdatablog%2F2010%2Faug%2F10%2Fgovernment-data-information-architecture&amp;title=The%20IA%20behind%20the%20World%20Government%20Data%20store%20%7C%20Martin%20Belam%20%7C%20Guardian%20Datablog&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=ia+datastore+martinbelam+guardian">SAVE</a></strong>
		                </em></span>


      </h4>




      <div class="actions">
      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers2">

          <a class="delNav" href="/url/f52c6b573ced478eb040713a67ff04ff">
              <span class="delNavCount">11</span>
          </a>
      </div>
      </div>







            <div class="description">
          &quot;The Guardian&#039;s Information Architect, Martin Belam, provides a glimpse behind the scenes at the design process of our World Government Data store.&quot;      </div>



    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/ia">ia</a></li>

            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/datastore">datastore</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/martinbelam">martinbelam</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/guardian">guardian</a></li>
    </ul>
		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->


    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-5" id="item-12133f9d40ccf4567235d2a30a28e72a-5">
  <div class="bookmark  NOTHUMB">


          <div class="dateGroup">
        <span title="10 AUG 10">
          10 AUG 10        </span>
      </div>



      <span class="jsEnabled action" id="audiofile5"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://twitter.com/hayjane/status/20803380360" >Amusing, but I&#039;m so cynical about such things... | @hayjane</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Ftwitter.com%2Fhayjane%2Fstatus%2F20803380360&amp;title=Amusing%2C%20but%20I%27m%20so%20cynical%20about%20such%20things...%20%7C%20%40hayjane&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=hayleydunlop+viral+thatgirlwhatquitherjobonawhiteboardthing">SAVE</a></strong>
		                </em></span>


      </h4>











            <div class="description">
          Props to Hayley Dunlop who was first in my Twitter stream to call it fake...      </div>


    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>

      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/hayleydunlop">hayleydunlop</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/viral">viral</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/thatgirlwhatquitherjobonawhiteboardthing">thatgirlwhatquitherjobonawhiteboardthing</a></li>
    </ul>
		  </div>

      <div class="clr"></div>

    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-6" id="item-c03bbe3d3bf97cd0d07ad1dd3566a76c-6">
  <div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile6"></span>


    <div class="data">





      <h4>


                  <a rel="nofollow" class="taggedlink  "  href="http://londonist.com/2010/08/wanted_londons_silliest_wifi_networ.php" >Wanted: London&#039;s Silliest Wifi Network Names - Londonist</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Flondonist.com%2F2010%2F08%2Fwanted_londons_silliest_wifi_networ.php&amp;title=Wanted%3A%20London%27s%20Silliest%20Wifi%20Network%20Names%20-%20Londonist&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=londonist+wifi+funny">SAVE</a></strong>
		                </em></span>


      </h4>




      <div class="actions">
      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers1">

          <a class="delNav" href="/url/c03bbe3d3bf97cd0d07ad1dd3566a76c">
              <span class="delNavCount">2</span>
          </a>
      </div>
      </div>







            <div class="description">
          &quot;A meme doing the rounds on Twitter at the moment is seeking to compile the silliest Wifi network names in London&quot;      </div>



    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/londonist">londonist</a></li>

            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/wifi">wifi</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/funny">funny</a></li>
    </ul>
		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->

</li>

<li class="post   bookmark-7" id="item-c33dd8dcc8bdeaccb1d80939a089719e-7">
  <div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile7"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://crowdmap.com/" >Crowdmap</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fcrowdmap.com%2F&amp;title=Crowdmap&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=mattmcalister&amp;via=mattmcalister&amp;copyuser=mattmcalister&amp;copytags=via%3Atwitter">SAVE</a></strong>

		                </em></span>


      </h4>




      <div class="actions">
      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers2">
          <a class="delNav" href="/url/c33dd8dcc8bdeaccb1d80939a089719e">
              <span class="delNavCount">78</span>
          </a>

      </div>
      </div>







            <div class="description">
          RT @Info_Activism: And more from @ushahidi: @crowdmap crowd sourcing for non-techies  http://crowdmap.com      </div>


    </div>

    <div class="meta">
                      <a href="/mattmcalister" class="user user-tag"><span class="">mattmcalister</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>

      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first last"><a class="tagItem noplay" rel="tag" href="/mattmcalister/via%3Atwitter">via:twitter</a></li>
    </ul>
		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->


    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-8" id="item-da08025e823367a9a47016618cc96090-8">
  <div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile8"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://mediatingconflict.blogspot.com/2010/08/nick-robinson-looking-for-less-abusive.html?utm_source=feedburner&amp;utm_medium=feed&amp;utm_campaign=Feed%3A+MediatingConflict+%28Mediating+Conflict%29" >Nick Robinson looking for &quot;less abusive debate&quot; on blogs | Mediating Conflict</a>

        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fmediatingconflict.blogspot.com%2F2010%2F08%2Fnick-robinson-looking-for-less-abusive.html%3Futm_source%3Dfeedburner%26utm_medium%3Dfeed%26utm_campaign%3DFeed%253A%2BMediatingConflict%2B%2528Mediating%2BConflict%2529&amp;title=Nick%20Robinson%20looking%20for%20%22less%20abusive%20debate%22%20on%20blogs%20%7C%20Mediating%20Conflict&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=nickrobinson+ugc+comments+blogging+mediatingconflict+bbccojo">SAVE</a></strong>
		                </em></span>


      </h4>










            <div class="description">
          &quot;So I&#039;m going to be honest with you and I&#039;ve said this before and I&#039;ve upset some people. I don&#039;t read the comments anything like as much as I used to because there is too much static white noise in them and not enough pure feedback. But if we could find a way of having a more thoughtful, less abusive debate via blogs I think that would be a good thing.&quot;      </div>



    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/nickrobinson">nickrobinson</a></li>

            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/ugc">ugc</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/comments">comments</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/blogging">blogging</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/mediatingconflict">mediatingconflict</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/bbccojo">bbccojo</a></li>
    </ul>

		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->
</li>

<li class="post   bookmark-9" id="item-b089add6c6ae7b44b2d98f13ac6ca585-9">
  <div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile9"></span>



    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://labs.slate.com/blog/2010/08/welcome-to-slate-labs/" >Slate Labs - Blog</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Flabs.slate.com%2Fblog%2F2010%2F08%2Fwelcome-to-slate-labs%2F&amp;title=Slate%20Labs%20-%20Blog&amp;jump=%2Fnetwork%2Fnik.silver%3Fsetcount%3D10&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=slatelabs+beta+experiment">SAVE</a></strong>
		                </em></span>


      </h4>




      <div class="actions">

      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers2">
          <a class="delNav" href="/url/b089add6c6ae7b44b2d98f13ac6ca585">
              <span class="delNavCount">16</span>
          </a>
      </div>
      </div>







            <div class="description">

          &quot;Today we're kicking off an exciting new project at Slate called &#039;Slate Labs&#039;...As you may have gathered from the logo, this site will serve as a home base for all of Slate's experiments with multimedia journalism. We've been doing a lot of them over the past few years, so we've collected a gallery of our favorite interactives from the Slate archives. To keep things fresh, we're also posting projects currently in development that we could use some help figuring out how to use effectively&quot;      </div>


    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>

      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/slatelabs">slatelabs</a></li>
            <li class="off tag-chain-tag"><a class="tagItem noplay" rel="tag" href="/currybet/beta">beta</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/experiment">experiment</a></li>
    </ul>
		  </div>

      <div class="clr"></div>

    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div><!-- #meta -->
</li>

</ul>


  <div id="pagination"> <span>1</span> <a href="/network/nik.silver?page=2">2</a>  <a href="/network/nik.silver?page=3">3</a>  <a href="/network/nik.silver?page=4">4</a>  <a href="/network/nik.silver?page=5">5</a>  <a href="/network/nik.silver?page=6">6</a>  <a href="/network/nik.silver?page=7">7</a> ... <a href="/network/nik.silver?page=2" class="pn next">Next &gt;</a><p>10699 Bookmarks</p></div>


    <div id="display-and-rss-options">
    <div id="bookmark-rss" class="rr">
            <a href="http://feeds.delicious.com/v2/rss/network/nik.silver?count=15" class="rss">RSS feed</a> <span>for these Bookmarks</span>
                <span id="ccLicense"><em>All Rights Reserved</em></span>

            </div>
</div>


<script>Delicious.BookmarkList.init('bookmarklist');</script>


        </div>
      </div>
      <div id="sidebar" class="yui-b">

<div id="options-mod-addNetwork" class="optionsMod">
    <h3 class="options-title">Add a user to Network <a href="javascript:void(0);" class="closeMod" id="options-close-addSubscriptions"><em>Close</em></a></h3>
    <form id="addMember" class="addForm" method="post" action="/settings/networkadd">
        <input type="hidden" class="hddn" name=".crumb" value="sBBNxeLIYFNtP.NujArsB_D3u7Y-">
        <div class="options-content" id="addSubOptions">

            <div class="inputField">
                <label for="networkadd" class="subtag">Username</label>
                <input type="text" name="networkadd" id="options-focus-addNetwork" value ="" class="text" autocomplete="off">
            </div>
            <div id="addConfBttns" class="inputField bttns">
                <span class="btn btn-active"   id="subscription-add-wrapper"><input type="submit" class="bttn" id="subscription-add" value ="Add"></span>
                <span class="btn btn-inactive" id="subscription-cancel-wrapper"><button id="subscription-cancel" class="bttn closeMod" value="Cancel">Cancel</button></span>
            </div>

            <div class="clr"></div>
        </div>
    </form>
</div>

<div id="sidenav-title">
    <h3 class="people"><span>People</span></h3>
    <div id="netOpts" class="optsMod sidebarOptsMod">
        <div class="optsModAct"><span id="netOptsLnk" class="optsModLnk">Options</span></div>

        <div id="netOptsList" class="optsList">
          <ul>
                <li><span>Show | <a href="/settings/people/privacy?fromnet=1&privacy=1&.crumb=7xJISe5DYAxencNSVKajPdF5HR0-" class="postOption">hide</a> your network from other people</span></li>
    <li><span>Show | <a href="/settings/people/privacy?fromnet=1&privacy=2&.crumb=7xJISe5DYAxencNSVKajPdF5HR0-" class="postOption">hide</a> your fans from other people</span></li>
    <li class="sub"><span><a href="/settings/network/bundle">Manage Network bundles...</a></span></li>

          </ul>
        </div>
    </div>
</div>





<ul id="network">

<li class="sidebar-list toggle on" id="network-friends">
    <h3><span class="toggle-button">Network</span>  <em>3</em></h3>

        <ul id="network-groups" class="network-list editable-list list">
    <li class="sidebar-list-item first" id="n-0" title="Clay Shirky (clay@shirky.com)">
                                  <a href="/cshirky" id="nu-0" class="user" title="Clay Shirky (clay@shirky.com)">
              <span class="username " id="username-n-0"><strong>Clay Shirky (clay@sh...</strong></span>
                                        </a>
            <a class="edit" id="edit0" href="/settings/networkedit?networkedit=cshirky&.crumb=noSeHRAqXsFvwmsyxf76OKS5R0s-"><em class="inside-edit" id="innerEdit0">EDIT</em></a>                  </li>
    <li class="sidebar-list-item" id="n-1" title="Martin Belam">

                                  <a href="/currybet" id="nu-1" class="user" title="Martin Belam">
              <span class="username " id="username-n-1"><strong>Martin Belam</strong></span>
                                        </a>
            <a class="edit" id="edit1" href="/settings/networkedit?networkedit=currybet&.crumb=noSeHRAqXsFvwmsyxf76OKS5R0s-"><em class="inside-edit" id="innerEdit1">EDIT</em></a>                  </li>
    <li class="sidebar-list-item" id="n-2" title="mattmcalister">
                                  <a href="/mattmcalister" id="nu-2" class="user" title="mattmcalister">
              <span class="username " id="username-n-2"><strong>mattmcalister</strong></span>

                                        </a>
            <a class="edit" id="edit2" href="/settings/networkedit?networkedit=mattmcalister&.crumb=noSeHRAqXsFvwmsyxf76OKS5R0s-"><em class="inside-edit" id="innerEdit2">EDIT</em></a>                  </li>
    </ul>
        <div class="clr"></div>

    <ul class="legend" id="networkLegend">
        <li class="new">= New fan</li>
        <li class="mutual">= Mutual fan</li>

    </ul>

</li>

</ul>

<ul id="networkfans">


<li class="sidebar-list toggle on" id="network-fans">
    <h3><span class="toggle-button">Fans</span> <em>6</em></h3>
        <ul id="fans-groups" class="network-list editable-list list">

        <li id="f-0" class="sidebar-list-item first">
                        <a id="nu-f-0" class="user" href="/chewy.x" id="fu0" title="chewy.x">
                <span id="username-f-0" class="username "><strong>chewy.x</strong></span>
                                            </a>
                            <a class="add" href="/settings/networkadd?networkadd=chewy.x&.crumb=sBBNxeLIYFNtP.NujArsB_D3u7Y-"><em class="inside-add">ADD</em></a>


        </li>

        <li id="f-1" class="sidebar-list-item">

                        <a id="nu-f-1" class="user" href="/davorg" id="fu1" title="Dave Cross">
                <span id="username-f-1" class="username "><strong>Dave Cross</strong></span>
                                            </a>
                            <a class="add" href="/settings/networkadd?networkadd=davorg&.crumb=sBBNxeLIYFNtP.NujArsB_D3u7Y-"><em class="inside-add">ADD</em></a>


        </li>

        <li id="f-2" class="sidebar-list-item">
                        <a id="nu-f-2" class="user" href="/HealthBlog" id="fu2" title="HealthBlog">

                <span id="username-f-2" class="username "><strong>HealthBlog</strong></span>
                                            </a>
                            <a class="add" href="/settings/networkadd?networkadd=HealthBlog&.crumb=sBBNxeLIYFNtP.NujArsB_D3u7Y-"><em class="inside-add">ADD</em></a>


        </li>

        <li id="f-3" class="sidebar-list-item">
                        <a id="nu-f-3" class="user" href="/Chaddocxfootwear" id="fu3" title="Jermar">
                <span id="username-f-3" class="username "><strong>Jermar</strong></span>

                                            </a>
                            <a class="add" href="/settings/networkadd?networkadd=Chaddocxfootwear&.crumb=sBBNxeLIYFNtP.NujArsB_D3u7Y-"><em class="inside-add">ADD</em></a>


        </li>

        <li id="f-4" class="sidebar-list-item">
                        <a id="nu-f-4" class="user" href="/jsvaughan" id="fu4" title="jsvaughan">
                <span id="username-f-4" class="username "><strong>jsvaughan</strong></span>
                                            </a>

                            <a class="add" href="/settings/networkadd?networkadd=jsvaughan&.crumb=sBBNxeLIYFNtP.NujArsB_D3u7Y-"><em class="inside-add">ADD</em></a>


        </li>

        <li id="f-5" class="sidebar-list-item">
                        <a id="nu-f-5" class="user" href="/martinstabe" id="fu5" title="Martin Stabe">
                <span id="username-f-5" class="username "><strong>Martin Stabe</strong></span>
                                            </a>
                            <a class="add" href="/settings/networkadd?networkadd=martinstabe&.crumb=sBBNxeLIYFNtP.NujArsB_D3u7Y-"><em class="inside-add">ADD</em></a>



        </li>
    </ul>
        <div class="clr"></div>
</li>

</ul>
<script>

  // need dynamic tags for Autocomplete
  // Add Crumbs for use by javascript
  Delicious.Network.init();
  var _crumbs = Delicious.Network.Crumbs;
  _crumbs.networkadd = 'sBBNxeLIYFNtP.NujArsB_D3u7Y-';
  _crumbs.networkedit = 'noSeHRAqXsFvwmsyxf76OKS5R0s-';
  _crumbs.networkrm  = '5DFVUxgUV41L7NPxyTF9kM_g.0U-';
</script>
      </div>
    </div><!-- #bd -->


    <script type="text/javascript">
        Delicious.UserTagsData.setTagFeed('http://feeds.delicious.com/v2/json/tags/nik.silver?callback=Delicious.TagsData.callbackDynamicUserTags&private=ZFN9DAThO6FdkTlePkUFEjp2V4Y-');
        Delicious.UserSendData.setTagFeed('http://feeds.delicious.com/v2/json/socialcontacts/nik.silver?callback=Delicious.TagsData.callbackDynamicSocialSend&private=lsjzlsUhgV_PeWdKkq1f5Qixlqg-');
        Delicious.MagicTagsData.disable();
        Delicious.TagsData.init('network');
    </script>


    <div id="ft">
  <div id="ftNav">
      <a id="yahooLink" href="http://info.yahoo.com" target="_blank"><span>a Yahoo! company</span></a>
      <ul>
          <li class="first"><a href="http://m.delicious.com/settings/switch/mobile">mobile</a></li>
          <li><a href="/help/tools" target="_blank">tools</a></li>

          <li><a href="http://blog.delicious.com/" target="_blank">blog</a></li>
          <li><a href="http://support.delicious.com/forum/" target="_blank">forums</a></li>
          <li><a href="/help/support">support</a></li>
          <li><a href="/help/terms">terms of service</a></li>
          <li><a href="http://info.yahoo.com/privacy/us/delicious/" target="_blank">privacy policy</a></li>
          <li><a href="http://info.yahoo.com/copyright/details.html" target="_blank">copyright policy</a></li>

          <li><a href="http://info.yahoo.com/relevantads/" target="_blank">about our ads</a></li>
      </ul>
      <div class="clear"></div>
  </div>
</div>
</div>
<!-- SpaceID=2015250148 loc=FR01 noad -->
<script src="http://l.yimg.com/d/lib/rapid/rapid_1.8.0.js"></script>
<script>var keys={A_pn:'Unnamed Page'};
var conf={keys:keys, lt_attr:'text', client_only:0, spaceid:'2015250148', tracked_mods:{pagination:'pagination',globalnav:'site navigation'}, track_lv:false, ywa:{project_id:'10001996193462'},track_module:true};
var ins = new YAHOO.i13n.Track(conf); ins.init();</script>
<noscript><img width=1 height=1 src=//geo.yahoo.com/p?s=2015250148&A_pn=Unnamed%20Page></noscript>

<script src="http://l.yimg.com/d/lib/rapid/rapid_1.8.0.js"></script>
<script>var keys={A_pn:'Unnamed Page',ywa:{project_id:'10001996193462'}};
var conf={keys:keys, lt_attr:'text', client_only:0, spaceid:'2015250148', tracked_mods:{'actions-list':'actions box',siteNotice:'message bar',nav:'content navigation',ft:'footer'}, track_lv:true};
var ins = new YAHOO.i13n.Track(conf); ins.init();</script>
<noscript><img width=1 height=1 src=//geo.yahoo.com/p?s=2015250148&A_pn=Unnamed%20Page></noscript>
</body>
</html>
<!-- fe01.web.del.ac4.yahoo.net compressed/chunked Thu Aug 12 12:57:03 PDT 2010 -->
""" }

  def delicious_bookmark_div_with_full_data = { """<div class="bookmark  NOTHUMB">




      <span id="audiofile6" class="jsEnabled action"></span>


    <div class="data">





      <h4>

                  <a href="http://www.guardian.co.uk/news/datablog/2010/aug/10/government-data-information-architecture" class="taggedlink  " rel="nofollow">The IA behind the World Government Data store | Martin Belam | Guardian Datablog</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a href="/save?url=http%3A%2F%2Fwww.guardian.co.uk%2Fnews%2Fdatablog%2F2010%2Faug%2F10%2Fgovernment-data-information-architecture&amp;title=The%20IA%20behind%20the%20World%20Government%20Data%20store%20%7C%20Martin%20Belam%20%7C%20Guardian%20Datablog&amp;jump=%2Fnetwork%2Fnik.silver&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=ia+datastore+martinbelam+guardian" class="inlinesave action rapidnofollow">SAVE</a></strong>
		                </em></span>


      </h4>




      <div class="actions">
      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers2">
          <a href="/url/f52c6b573ced478eb040713a67ff04ff" class="delNav">
              <span class="delNavCount">13</span>
          </a>
      </div>
      </div>







            <div class="description">
          "The Guardian's Information Architect, Martin Belam, provides a glimpse behind the scenes at the design process of our World Government Data store."      </div>


    </div>

    <div class="meta">
                      <a class="user user-tag" href="/currybet"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel" style="visibility: hidden;">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a href="/currybet/ia" rel="tag" class="tagItem noplay">ia</a></li>
            <li class="off tag-chain-tag"><a href="/currybet/datastore" rel="tag" class="tagItem noplay">datastore</a></li>
            <li class="off tag-chain-tag"><a href="/currybet/martinbelam" rel="tag" class="tagItem noplay">martinbelam</a></li>
            <li class="off tag-chain-tag last"><a href="/currybet/guardian" rel="tag" class="tagItem noplay">guardian</a></li>
    </ul>
		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div>""" }

  def delicious_bookmark_div_with_partial_data = { """<div class="bookmark  NOTHUMB">




      <span id="audiofile54" class="jsEnabled action"></span>


    <div class="data">





      <h4>

                  <a href="http://www.sciencedirect.com/science?_ob=ArticleURL&amp;_udi=B6V84-4CS4KXX-2&amp;_user=142623&amp;_coverDate=10%2F31%2F2004&amp;_rdoc=1&amp;_fmt=high&amp;_orig=search&amp;_sort=d&amp;_docanchor=&amp;view=c&amp;_acct=C000000333&amp;_version=1&amp;_urlVersion=0&amp;_userid=142623&amp;md5=28edbadbb8d08ff3c150330060b33661#toc1" class="taggedlink  " rel="nofollow">ScienceDirect - Economics Letters : The effect of democracy and press freedom on corruption: an empirical test</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a href="/save?url=http%3A%2F%2Fwww.sciencedirect.com%2Fscience%3F_ob%3DArticleURL%26_udi%3DB6V84-4CS4KXX-2%26_user%3D142623%26_coverDate%3D10%252F31%252F2004%26_rdoc%3D1%26_fmt%3Dhigh%26_orig%3Dsearch%26_sort%3Dd%26_docanchor%3D%26view%3Dc%26_acct%3DC000000333%26_version%3D1%26_urlVersion%3D0%26_userid%3D142623%26md5%3D28edbadbb8d08ff3c150330060b33661%23toc1&amp;title=ScienceDirect%20-%20Economics%20Letters%20%3A%20The%20effect%20of%20democracy%20and%20press%20freedom%20on%20corruption%3A%20an%20empirical%20test&amp;jump=%2Fnetwork%2Fnik.silver%3Fpage%3D2&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=cshirky&amp;via=cshirky&amp;copyuser=cshirky&amp;copytags=press+freedom+democracy+corruption" class="inlinesave action rapidnofollow">SAVE</a></strong>
		                </em></span>


      </h4>












    </div>

    <div class="meta">
                      <a class="user user-tag" href="/cshirky"><span class="">Clay Shirky (clay@s...</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>
      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a href="/cshirky/press" rel="tag" class="tagItem noplay">press</a></li>
            <li class="off tag-chain-tag"><a href="/cshirky/freedom" rel="tag" class="tagItem noplay">freedom</a></li>
            <li class="off tag-chain-tag"><a href="/cshirky/democracy" rel="tag" class="tagItem noplay">democracy</a></li>
            <li class="off tag-chain-tag last"><a href="/cshirky/corruption" rel="tag" class="tagItem noplay">corruption</a></li>
    </ul>
		  </div>

      <div class="clr"></div>
    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div>""" }

  def delicious_bookmark_div_with_special_characters = {"""<div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile26"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://sarahhartley.wordpress.com/2010/08/25/10-characteristics-of-hyperlocal/" >10 Characteristics of hyperlocal \u00AB Sarah Hartley</a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fsarahhartley.wordpress.com%2F2010%2F08%2F25%2F10-characteristics-of-hyperlocal%2F&amp;title=10%20Characteristics%20of%20hyperlocal%20%C2%AB%20Sarah%20Hartley&amp;jump=%2Fnetwork%2Fnik.silver&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=hyperlocal+sarahhartley">SAVE</a></strong>

		                </em></span>


      </h4>




      <div class="actions">
      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers2">
          <a class="delNav" href="/url/c58f81286f4daee146b13d78dc3b9ca1">
              <span class="delNavCount">33</span>
          </a>

      </div>
      </div>







            <div class="description">
          &quot;What, if anything, the term \u2018hyperlocal\u2019 now means is something that keeps coming up in conversations I have and it strikes me that it\u2019s no longer necessarily defined by a tight geographical area, but instead seems to have evolved to describe more of an attitude than a place&quot;. great list from Sarah Hartley      </div>


    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>

      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/hyperlocal">hyperlocal</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/sarahhartley">sarahhartley</a></li>
    </ul>
		  </div>

      <div class="clr"></div>

    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div>"""}

  def delicious_bookmark_div_with_html_in_fields = {"""<div class="bookmark  NOTHUMB">




      <span class="jsEnabled action" id="audiofile26"></span>


    <div class="data">





      <h4>

                  <a rel="nofollow" class="taggedlink  "  href="http://sarahhartley.wordpress.com/2010/08/25/10-characteristics-of-hyperlocal/" >Something about <em>hyperlocal</em></a>
        <span class="saverem"><em class="bookmark-actions"><strong><a class="inlinesave action rapidnofollow" href="/save?url=http%3A%2F%2Fsarahhartley.wordpress.com%2F2010%2F08%2F25%2F10-characteristics-of-hyperlocal%2F&amp;title=10%20Characteristics%20of%20hyperlocal%20%C2%AB%20Sarah%20Hartley&amp;jump=%2Fnetwork%2Fnik.silver&amp;key=efYRvX6rsyXVRqq19kahvq1OM5s-&amp;copyuser=currybet&amp;via=currybet&amp;copyuser=currybet&amp;copytags=hyperlocal+sarahhartley">SAVE</a></strong>

		                </em></span>


      </h4>




      <div class="actions">
      <h5 class="savers-label">PEOPLE</h5>
      <div class="savers savers2">
          <a class="delNav" href="/url/c58f81286f4daee146b13d78dc3b9ca1">
              <span class="delNavCount">33</span>
          </a>

      </div>
      </div>







            <div class="description">
          What is this <em>hyperlocal</em> thing?      </div>


    </div>

    <div class="meta">
                      <a href="/currybet" class="user user-tag"><span class="">Martin Belam</span></a>



          <h5 class="tag-chain-label tag-chain-tagLabel">TAGS</h5>

      <h5 class="tag-chain-label tag-chain-forLabel">SHARED</h5>
      <div class="tagdisplay">
        <ul class="tag-chain">        <li class="off tag-chain-tag first"><a class="tagItem noplay" rel="tag" href="/currybet/hyperlocal">hyperlocal</a></li>
            <li class="off tag-chain-tag last"><a class="tagItem noplay" rel="tag" href="/currybet/sarahhartley">sarahhartley</a></li>
    </ul>
		  </div>

      <div class="clr"></div>

    </div><!-- #tagdisplay -->

    <div class="clr"></div>
  </div>"""}

  def twitter_times_rss = { <rss version='2.0'>
 <channel>
   <title>All News on  'The Twitter Times: pigsaw'</title>
   <link>http://twittertim.es/pigsaw</link>
   <description>The Twitter Times is a real-time personalized newspaper generated from your Twitter account</description>
   <language>en-us</language>
   <pubDate>Wed Sep 15 13:50:40 PDT 2010</pubDate>

   <lastBuildDate>Wed Sep 15 13:50:40 PDT 2010</lastBuildDate>   <item>
     <title><![CDATA[Seedcamp Week 2010: an education in enterprise | Media | guardian.co.uk]]></title>
     <pubDate>Wed Sep 15 09:35:16 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284583191417]]></guid>
     <link><![CDATA[http://www.guardian.co.uk/media/pda/2010/sep/15/seedcamp-week-2010]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.guardian.co.uk/media/pda/2010/sep/15/seedcamp-week-2010' style='color: #009900; text-decoration: none;'>guardian.co.uk</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          Investor: 'When the water level goes down you will see who is swimming naked &ndash; so only true entrepreneurs and true investors are left'
Youthful entrepreneur and Seedcamp veteran Emi Gal cited a wise adage in a recent TechCrunch article: &quot;Ask an investor for money and he'll give you advice. Ask him for advice and he'll give you money.&quot;
Twenty-three early-stage companies from 16 different geographies are this week holed up in the engineering building at University College London, ..            &nbsp; <a href='http://www.guardian.co.uk/media/pda/2010/sep/15/seedcamp-week-2010' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/JoshHalliday' style='color:#093D72; text-decoration: none; font-weight: bold;'>JoshHalliday:</a></span>&nbsp;By me @ t'Guardian: Seedcamp Week 2010: an education in enterprise http://bit.ly/a4kXZA&nbsp;
            <span><a target='_blank' href='http://twitter.com/JoshHalliday/status/24585785226' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.42.31</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/MrsBunz' style='color:#093D72; text-decoration: none; font-weight: bold;'>MrsBunz:</a></span>&nbsp;Today's #seedcamp venture capital lecture perfectly resumed by @JoshHalliday for @guardianpda -  http://bit.ly/a4kXZA&nbsp;
            <span><a target='_blank' href='http://twitter.com/MrsBunz/status/24588922679' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.24.20</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/guardiantech' style='color:#093D72; text-decoration: none; font-weight: bold;'>guardiantech:</a></span>&nbsp;Seedcamp Week 2010: an education in enterprise http://bit.ly/cAlqOo&nbsp;
            <span><a target='_blank' href='http://twitter.com/guardiantech/status/24589287037' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.29.21</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediaguardian' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediaguardian:</a></span>&nbsp;Seedcamp Week 2010: an education in enterprise http://bit.ly/alx56O&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediaguardian/status/24585210249' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.35.16</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.guardian.co.uk%2Fmedia%2Fpda%2F2010%2Fsep%2F15%2Fseedcamp-week-2010&title=Seedcamp+Week+2010%3A+an+education+in+enterprise+%7C+Media+%7C+guardian.co.uk'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>

   <item>
     <title><![CDATA[Digital Agencies of the Future]]></title>
     <pubDate>Wed Sep 15 09:37:02 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284583191416]]></guid>
     <link><![CDATA[http://www.narrowdesign.com/future]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.narrowdesign.com/future' style='color: #009900; text-decoration: none;'>narrowdesign.com</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/paulcarvill' style='color:#093D72; text-decoration: none; font-weight: bold;'>paulcarvill:</a></span>&nbsp;LOL LOL LOL RT @sicross Brilliant - the websites of ad/marketing/web agencies... on mobile phones: http://bit.ly/aJZNfG&nbsp;
            <span><a target='_blank' href='http://twitter.com/paulcarvill/status/24587896223' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.10.22</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/charlesarthur' style='color:#093D72; text-decoration: none; font-weight: bold;'>charlesarthur:</a></span>&nbsp;Linkage: Digital Agencies of the Future >> Narrowdesign http://bit.ly/bLAcjv #fb&nbsp;
            <span><a target='_blank' href='http://twitter.com/charlesarthur/status/24588490977' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.18.23</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/dansumption' style='color:#093D72; text-decoration: none; font-weight: bold;'>dansumption:</a></span>&nbsp;OUCH! Jaw-dropping showcase of mobile websites of ...agencies http://is.gd/fc2PL  /via @seb_ly @narrowd&nbsp;
            <span><a target='_blank' href='http://twitter.com/dansumption/status/24585351114' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.37.02</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.narrowdesign.com%2Ffuture&title=Digital+Agencies+of+the+Future'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>

     <title><![CDATA[Their Questions Answered]]></title>
     <pubDate>Wed Sep 15 09:52:04 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284583191415]]></guid>
     <link><![CDATA[http://theirquestionsanswered.wordpress.com]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://theirquestionsanswered.wordpress.com' style='color: #009900; text-decoration: none;'>theirquestionsanswered.wordpress.com</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/qwghlm' style='color:#093D72; text-decoration: none; font-weight: bold;'>qwghlm:</a></span>&nbsp;This is wonderfully pedantic - blog answering the rhetorical questions sent in to the Guardian Weekend http://bit.ly/aeB0qp&nbsp;
            <span><a target='_blank' href='http://twitter.com/qwghlm/status/24586527938' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.52.04</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/thommeread' style='color:#093D72; text-decoration: none; font-weight: bold;'>thommeread:</a></span>&nbsp;RT @qwghlm: This is wonderfully pedantic - blog answering the rhetorical questions sent in to the Guardian Weekend http://bit.ly/aeB0qp&nbsp;
            <span><a target='_blank' href='http://twitter.com/thommeread/status/24590805101' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.51.37</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/kevinmarks' style='color:#093D72; text-decoration: none; font-weight: bold;'>kevinmarks:</a></span>&nbsp;This is fantastic RT @qwghlm: answering rhetorical questions from Guardian Weekend's letter page http://bit.ly/aeB0qp (via @oliverburkeman)&nbsp;
            <span><a target='_blank' href='http://twitter.com/kevinmarks/status/24590528457' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.47.33</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Ftheirquestionsanswered.wordpress.com&title=Their+Questions+Answered'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[The Rise of Apps Culture | Pew Research Center's Internet & American Life Project]]></title>

     <pubDate>Tue Sep 14 07:51:29 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793149]]></guid>
     <link><![CDATA[http://pewinternet.org/Reports/2010/The-Rise-of-Apps-Culture.aspx]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://pewinternet.org/Reports/2010/The-Rise-of-Apps-Culture.aspx' style='color: #009900; text-decoration: none;'>pewinternet.org</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/steve_wing' style='color:#093D72; text-decoration: none; font-weight: bold;'>steve_wing:</a></span>&nbsp;Reading:The Rise of Apps Culture. Pew Research http://t.co/TdNkMbU Seems to suggest more of a mis(h)app culture. Okay it's been along day&nbsp;
            <span><a target='_blank' href='http://twitter.com/steve_wing/status/24495732296' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.42.18</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(3)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/Pew_Internet' style='color:#093D72; text-decoration: none; font-weight: bold;'>Pew_Internet:</a></span>&nbsp;Roughly half (47%) of apps downloaders have paid for an app; the rest say they only download apps that are free. http://pewrsr.ch/apps2010&nbsp;
            <span><a target='_blank' href='http://twitter.com/Pew_Internet/status/24489361832' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 09.20.02</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Pew_Internet' style='color:#093D72; text-decoration: none; font-weight: bold;'>Pew_Internet:</a></span>&nbsp;Almost half of the adult app user population in the US is under age 30. Read more: http://pewrsr.ch/apps2010&nbsp;
            <span><a target='_blank' href='http://twitter.com/Pew_Internet/status/24517658579' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.15.02</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/acarvin' style='color:#093D72; text-decoration: none; font-weight: bold;'>acarvin:</a></span>&nbsp;New Pew report on mobile apps use: http://bit.ly/crOsHC. 82% of US adults use cell phones; 24% use apps.&nbsp;
            <span><a target='_blank' href='http://twitter.com/acarvin/status/24581256639' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.47.10</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/steverubel' style='color:#093D72; text-decoration: none; font-weight: bold;'>steverubel:</a></span>&nbsp;RT @Pew_Internet: New @Pew_Internet & Nielsen report, "The Rise of Apps Culture" http://pewrsr.ch/apps2010 who uses apps & how they use them&nbsp;
            <span><a target='_blank' href='http://twitter.com/steverubel/status/24490390191' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 09.32.23</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Pew_Internet' style='color:#093D72; text-decoration: none; font-weight: bold;'>Pew_Internet:</a></span>&nbsp;New @Pew_Internet & Nielsen report, "The Rise of Apps Culture" http://pewrsr.ch/apps2010 explores who uses apps & how they use them&nbsp;
            <span><a target='_blank' href='http://twitter.com/Pew_Internet/status/24481927117' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 07.51.29</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fpewinternet.org%2FReports%2F2010%2FThe-Rise-of-Apps-Culture.aspx&title=The+Rise+of+Apps+Culture+%7C+Pew+Research+Center%27s+Internet+%26amp%3B+American+Life+Project'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Twitter redesign – APIs make you raise your game  |  Lloyd Shepherd @work]]></title>
     <pubDate>Wed Sep 15 00:38:42 PDT 2010</pubDate>

     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793148]]></guid>
     <link><![CDATA[http://www.lloydshepherd.com/2010/09/15/twitter-redesign-apis-make-you-raise-your-game]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.lloydshepherd.com/2010/09/15/twitter-redesign-apis-make-you-raise-your-game' style='color: #009900; text-decoration: none;'>lloydshepherd.com</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          The thing I like &ndash; really like &ndash; about the upcoming Twitter redesign is the way it turns the Twitter website back into something it should always have been: the best place to get the purest Twitter experience.

To date, Twitter&rsquo;s website has always felt slightly weaker as an experience than some of the best Twitter apps out there, because it&rsquo;s a website, and it&rsquo;s had to make all the compromises a website has to make to be a website. The fact that Twitter had a full..            &nbsp; <a href='http://www.lloydshepherd.com/2010/09/15/twitter-redesign-apis-make-you-raise-your-game' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/openplatform' style='color:#093D72; text-decoration: none; font-weight: bold;'>openplatform:</a></span>&nbsp;BLOG: Twitter redesign show how APIs force you to raise your own game http://tinyurl.com/2e28cjl&nbsp;
            <span><a target='_blank' href='http://twitter.com/openplatform/status/24581302774' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.47.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/lloydshep' style='color:#093D72; text-decoration: none; font-weight: bold;'>lloydshep:</a></span>&nbsp;BLOG: Twitter redesign show how APIs force you to raise your own game http://tinyurl.com/2e28cjl&nbsp;
            <span><a target='_blank' href='http://twitter.com/lloydshep/status/24550591938' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 00.38.42</a></span>
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
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.lloydshepherd.com%2F2010%2F09%2F15%2Ftwitter-redesign-apis-make-you-raise-your-game&title=Twitter+redesign+%26%238211%3B+APIs+make+you+raise+your+game++%7C++Lloyd+Shepherd+%40work'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Times Launching Web Notifications Dashboard, TV Ad For Paid Pitch																																																		| 	paidContent:UK]]></title>
     <pubDate>Wed Sep 15 04:10:11 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793147]]></guid>

     <link><![CDATA[http://paidcontent.co.uk/article/419-times-launching-web-notifications-dashboard-tv-ad-for-paid-pitch]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://paidcontent.co.uk/article/419-times-launching-web-notifications-dashboard-tv-ad-for-paid-pitch' style='color: #009900; text-decoration: none;'>paidcontent.co.uk</a>
    </div>
    <div>
       <div id='fooid#img'>
              <img src='http://paidcontent.org/images/editorial/f_small/rupert-everett-in-times-web-ad-s.png' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
       </div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          I often find it confusing when people charge The Times with having introduced reader fees without having launched a service that really takes advantage of the internet (have you seen those infographics or daily emails?).
But a new launch on TheTimes.co.uk and SundayTimes.co.uk may help put paid to that&hellip;
The papers are launching Dashboard, a feature that lets customers receive&hellip; &mdash;custom notifications of new stories in particular sections &mdash;notifications when a previously-..            &nbsp; <a href='http://paidcontent.co.uk/article/419-times-launching-web-notifications-dashboard-tv-ad-for-paid-pitch' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/RobertAndrews' style='color:#093D72; text-decoration: none; font-weight: bold;'>RobertAndrews:</a></span>&nbsp;Video: Rupert Everett tells you why "good journalism is very important" in TV ad for Times website http://cnt.to/mne&nbsp;
            <span><a target='_blank' href='http://twitter.com/RobertAndrews/status/24560298606' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.10.11</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/timesjoanna' style='color:#093D72; text-decoration: none; font-weight: bold;'>timesjoanna:</a></span>&nbsp;Times Launching Web Notifications Dashboard, TV Ad For Paid Pitch http://icio.us/wuufld&nbsp;
            <span><a target='_blank' href='http://twitter.com/timesjoanna/status/24577426467' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.02.31</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Mazi' style='color:#093D72; text-decoration: none; font-weight: bold;'>Mazi:</a></span>&nbsp;RT @Times Launching Web Notifications Dashboard, TV Ad For Paid Pitch http://icio.us/wuufld&nbsp;
            <span><a target='_blank' href='http://twitter.com/Mazi/status/24577825956' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.07.05</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fpaidcontent.co.uk%2Farticle%2F419-times-launching-web-notifications-dashboard-tv-ad-for-paid-pitch&title=Times+Launching+Web+Notifications+Dashboard%2C+TV+Ad+For+Paid+Pitch%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%7C+%09paidContent%3AUK'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Scripting News: No more Twitter apps]]></title>
     <pubDate>Wed Sep 15 05:59:05 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793146]]></guid>
     <link><![CDATA[http://scripting.com/stories/2010/09/15/noMoreTwitterApps.html]]></link>

     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://scripting.com/stories/2010/09/15/noMoreTwitterApps.html' style='color: #009900; text-decoration: none;'>scripting.com</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          Every so often I get asked to look at a Twitter app. I usually look, and usually am depressed by what I see.
They're all scams. If they're any good they'd be better without the connection to Twitter. At least they'd be more honest.
I don't like calling software or platforms dead, but if I did, I'd say that about the Twitter API.
In any case, I don't expect to see any compelling Twitter apps from here-out.       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/om' style='color:#093D72; text-decoration: none; font-weight: bold;'>om:</a></span>&nbsp;Scripting News: I don't expect to see any compelling Twitter apps from here-out.  http://r2.ly/69nb&nbsp;
            <span><a target='_blank' href='http://twitter.com/om/status/24568020206' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.08.30</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mathewi' style='color:#093D72; text-decoration: none; font-weight: bold;'>mathewi:</a></span>&nbsp;Dave Winer says he doesn't expect to see any compelling new Twitter apps in the future: http://bit.ly/bLB0eX /via @graubart&nbsp;
            <span><a target='_blank' href='http://twitter.com/mathewi/status/24568510854' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.15.00</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Scobleizer' style='color:#093D72; text-decoration: none; font-weight: bold;'>Scobleizer:</a></span>&nbsp;Scripting News: I don't expect to see any compelling Twitter apps from here-out.  http://r2.ly/69nb&nbsp;
            <span><a target='_blank' href='http://twitter.com/Scobleizer/status/24582373426' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.00.32</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/davewiner' style='color:#093D72; text-decoration: none; font-weight: bold;'>davewiner:</a></span>&nbsp;Scripting News: I don't expect to see any compelling Twitter apps from here-out.  http://r2.ly/69nb&nbsp;
            <span><a target='_blank' href='http://twitter.com/davewiner/status/24567309880' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 05.59.05</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fscripting.com%2Fstories%2F2010%2F09%2F15%2FnoMoreTwitterApps.html&title=Scripting+News%3A+No+more+Twitter+apps'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[How to Pitch a VC (]]></title>
     <pubDate>Wed Sep 15 07:45:23 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793145]]></guid>
     <link><![CDATA[http://www.slideshare.net/dmc500hats/how-to-pitch-a-vc-5207258]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.slideshare.net/dmc500hats/how-to-pitch-a-vc-5207258' style='color: #009900; text-decoration: none;'>slideshare.net</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/tim' style='color:#093D72; text-decoration: none; font-weight: bold;'>tim:</a></span>&nbsp;RT @davemcclure: slides from my #SeedCamp talk "How to Pitch a VC (aka Startup Viagra)" http://slidesha.re/97XLKr #pitchVC #NSFW #problem&nbsp;
            <span><a target='_blank' href='http://twitter.com/tim/status/24576452334' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 07.51.22</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/JoshHalliday' style='color:#093D72; text-decoration: none; font-weight: bold;'>JoshHalliday:</a></span>&nbsp;RT @davemcclure: slides from my #SeedCamp talk "How to Pitch a VC (aka Startup Viagra)" http://slidesha.re/97XLKr #pitchVC #NSFW #problem&nbsp;
            <span><a target='_blank' href='http://twitter.com/JoshHalliday/status/24576371172' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 07.50.26</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/davemcclure' style='color:#093D72; text-decoration: none; font-weight: bold;'>davemcclure:</a></span>&nbsp;slides from my #SeedCamp talk "How to Pitch a VC (aka Startup Viagra)" http://slidesha.re/97XLKr #pitchVC #NSFW #problem&nbsp;
            <span><a target='_blank' href='http://twitter.com/davemcclure/status/24575937966' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 07.45.23</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mikebutcher' style='color:#093D72; text-decoration: none; font-weight: bold;'>mikebutcher:</a></span>&nbsp;RT @davemcclure: slides from my #SeedCamp talk "How to Pitch a VC (aka Startup Viagra)" http://slidesha.re/97XLKr #pitchVC #NSFW #problem&nbsp;
            <span><a target='_blank' href='http://twitter.com/mikebutcher/status/24578877516' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.19.09</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.slideshare.net%2Fdmc500hats%2Fhow-to-pitch-a-vc-5207258&title=How+to+Pitch+a+VC+%28'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>

   </item>
   <item>
     <title><![CDATA[BBC News - Pope aide pulls out of trip after 'Third World jibe]]></title>
     <pubDate>Wed Sep 15 08:31:14 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793144]]></guid>
     <link><![CDATA[http://www.bbc.co.uk/news/uk-11317441]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.bbc.co.uk/news/uk-11317441' style='color: #009900; text-decoration: none;'>bbc.co.uk</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/davorg' style='color:#093D72; text-decoration: none; font-weight: bold;'>davorg:</a></span>&nbsp;Pope adviser calls the UK a 'Third World country' http://bbc.in/9X1U6e -  "a new and aggressive atheism" Hurrah!&nbsp;
            <span><a target='_blank' href='http://twitter.com/davorg/status/24579903697' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.31.14</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/bengoldacre' style='color:#093D72; text-decoration: none; font-weight: bold;'>bengoldacre:</a></span>&nbsp;Pope adviser calls UK a Third World Country, deploys racism, then says atheists are aggressive: bizarre http://dlvr.it/5JMYn&nbsp;
            <span><a target='_blank' href='http://twitter.com/bengoldacre/status/24584297799' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.24.02</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/benayers' style='color:#093D72; text-decoration: none; font-weight: bold;'>benayers:</a></span>&nbsp;Looks like some genuine news might have happened re #Pope's dull state visit: http://bbc.in/aTNnEJ > cardinal says UK 'Third World'&nbsp;
            <span><a target='_blank' href='http://twitter.com/benayers/status/24587123206' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.59.53</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.bbc.co.uk%2Fnews%2Fuk-11317441&title=BBC+News+-+Pope+aide+pulls+out+of+trip+after+%26%23039%3BThird+World+jibe'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>

   <item>
     <title><![CDATA[Ironically, She Fully Understands ROFLMAODZEDUN - Failbook - Funny Facebook Status Messages ( Failbooking )]]></title>
     <pubDate>Wed Sep 15 09:04:17 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793143]]></guid>
     <link><![CDATA[http://failbook.failblog.org/2010/09/14/funny-facebook-fails-ironically-she-fully-understands-roflmaodzedun]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://failbook.failblog.org/2010/09/14/funny-facebook-fails-ironically-she-fully-understands-roflmaodzedun' style='color: #009900; text-decoration: none;'>failbook.failblog.org</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/tdree' style='color:#093D72; text-decoration: none; font-weight: bold;'>tdree:</a></span>&nbsp;Love failbooking: "My mum thinks 'LOL' means 'lots of love'- she texted: 'Your grandam has just died LOL'" http://bit.ly/98z48O&nbsp;
            <span><a target='_blank' href='http://twitter.com/tdree/status/24583757003' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.17.23</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/badjournalism' style='color:#093D72; text-decoration: none; font-weight: bold;'>badjournalism:</a></span>&nbsp;Love failbooking: "My mum thinks 'LOL' means 'lots of love'- she texted: 'Your grandam has just died LOL'" http://bit.ly/98z48O&nbsp;
            <span><a target='_blank' href='http://twitter.com/badjournalism/status/24582971429' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.07.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/commentisfree' style='color:#093D72; text-decoration: none; font-weight: bold;'>commentisfree:</a></span>&nbsp;RT @rosieswash: Love failbooking: "My mum thinks LOL means 'lots of love' she texted: 'Your grandma has just died LOL'" http://bit.ly/98z48O&nbsp;
            <span><a target='_blank' href='http://twitter.com/commentisfree/status/24582687381' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.04.17</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Ffailbook.failblog.org%2F2010%2F09%2F14%2Ffunny-facebook-fails-ironically-she-fully-understands-roflmaodzedun&title=Ironically%2C+She+Fully+Understands+ROFLMAODZEDUN+-+Failbook+-+Funny+Facebook+Status+Messages+%28+Failbooking+%29'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>

     <title><![CDATA[Apple to announce subscription plan for newspapers - San Jose Mercury News]]></title>
     <pubDate>Wed Sep 15 07:41:23 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284578793142]]></guid>
     <link><![CDATA[http://www.mercurynews.com/ci_16076241?source=most_viewed&nclick_check=1]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.mercurynews.com/ci_16076241?source=most_viewed&nclick_check=1' style='color: #009900; text-decoration: none;'>mercurynews.com</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/romenesko' style='color:#093D72; text-decoration: none; font-weight: bold;'>romenesko:</a></span>&nbsp;Apple is expected to announce a subscription plan for newspapers. It'll probably take a 30% cut. http://journ.us/aG0Kba&nbsp;
            <span><a target='_blank' href='http://twitter.com/romenesko/status/24575595834' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 07.41.23</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu' style='color:#093D72; text-decoration: none; font-weight: bold;'>jayrosen_nyu:</a></span>&nbsp;Brutal! For newspaper apps on the iPad, Apple wants a 30% cut of subscriptions and maybe 40% of ad revenue? http://jr.ly/4y46 via @Romenesko&nbsp;
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu/status/24585555309' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.39.37</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/CharlieBeckett' style='color:#093D72; text-decoration: none; font-weight: bold;'>CharlieBeckett:</a></span>&nbsp;That's a hell of a cut: RT @jayrosen_nyu For newspaper apps on iPad, Apple wants 30% cut of subs & 40% of ad revenue? http://jr.ly/4y46&nbsp;
            <span><a target='_blank' href='http://twitter.com/CharlieBeckett/status/24585761489' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.42.12</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/KristineLowe' style='color:#093D72; text-decoration: none; font-weight: bold;'>KristineLowe:</a></span>&nbsp;Brutal! For newspaper apps on the iPad, Apple wants a 30% cut of subscriptions and maybe 40% of ad revenue? http://jr.ly/4y46 via @Romenesko&nbsp;
            <span><a target='_blank' href='http://twitter.com/KristineLowe/status/24586744214' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.54.52</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.mercurynews.com%2Fci_16076241%3Fsource%3Dmost_viewed%26nclick_check%3D1&title=Apple+to+announce+subscription+plan+for+newspapers+-+San+Jose+Mercury+News'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[GCreep: Google Engineer Stalked Teens, Spied on Chats]]></title>

     <pubDate>Tue Sep 14 12:31:25 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284567914233]]></guid>
     <link><![CDATA[http://gawker.com/5637234]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://gawker.com/5637234' style='color: #009900; text-decoration: none;'>gawker.com</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(5)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/BenLaMothe' style='color:#093D72; text-decoration: none; font-weight: bold;'>BenLaMothe:</a></span>&nbsp;GCreep: Google Engineer Stalked Teens, Spied on Chats http://t.co/lv5qola via @gawker&nbsp;
            <span><a target='_blank' href='http://twitter.com/BenLaMothe/status/24503162583' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 12.35.02</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mathewi' style='color:#093D72; text-decoration: none; font-weight: bold;'>mathewi:</a></span>&nbsp;wow -- if my mom reads about this Google engineer who snooped on kids, I'm never going to convince her to use Gmail: http://is.gd/faK6w&nbsp;
            <span><a target='_blank' href='http://twitter.com/mathewi/status/24505428766' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.11.09</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/dannysullivan' style='color:#093D72; text-decoration: none; font-weight: bold;'>dannysullivan:</a></span>&nbsp;the @gawker story on google engineer who accessed private data http://bit.ly/cWoDUM now confirmed http://tcrn.ch/9RE0xP & 2nd time&nbsp;
            <span><a target='_blank' href='http://twitter.com/dannysullivan/status/24524724789' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.49.52</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/dannysullivan' style='color:#093D72; text-decoration: none; font-weight: bold;'>dannysullivan:</a></span>&nbsp;key question from @gawker piece on google engineer who read private data -- can data be encrypted even from googlers? http://bit.ly/cWoDUM&nbsp;
            <span><a target='_blank' href='http://twitter.com/dannysullivan/status/24533567017' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 19.43.21</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/amayfield' style='color:#093D72; text-decoration: none; font-weight: bold;'>amayfield:</a></span>&nbsp;Google engineer fired for cyber-stalking http://gawker.com/5637234/&nbsp;
            <span><a target='_blank' href='http://twitter.com/amayfield/status/24572392713' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 07.03.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/nicknotned' style='color:#093D72; text-decoration: none; font-weight: bold;'>nicknotned:</a></span>&nbsp;Google confirms it fired an engineer who snuck into its chat service to spy on teens: http://gawker.com/5637234/&nbsp;
            <span><a target='_blank' href='http://twitter.com/nicknotned/status/24525350092' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.58.37</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/nicknotned' style='color:#093D72; text-decoration: none; font-weight: bold;'>nicknotned:</a></span>&nbsp;Gawker reveals the teen-obsessed Google engineer who used his clearance to spy on targets' web chats.
http://gawker.com/5637234/&nbsp;
            <span><a target='_blank' href='http://twitter.com/nicknotned/status/24502929812' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 12.31.25</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fgawker.com%2F5637234&title=GCreep%3A+Google+Engineer+Stalked+Teens%2C+Spied+on+Chats'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Nieman Journalism Lab shows the way to do apologies and corrections online - Martin Belam's currybetdotnet blog - September 15, 2010]]></title>
     <pubDate>Wed Sep 15 01:39:29 PDT 2010</pubDate>

     <guid isPermaLink='false'><![CDATA[user.14697612::1284560824565]]></guid>
     <link><![CDATA[http://www.currybet.net/cbet_blog/2010/09/handling-errors.php]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.currybet.net/cbet_blog/2010/09/handling-errors.php' style='color: #009900; text-decoration: none;'>currybet.net</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          A good compare and contrast with media accountability over mistakes.
Yesterday, the Nieman Journalism Lab broke an embargo over a Pew Research Centre study. When they realised, they removed the story and apologised on Twitter, and once it was republished, added the following note on the bottom:
&quot;[Editor's note: Originally, we accidentally published this post too early and jumped the gun on an embargo. Our sincere apologies to the Pew Research Center's Internet &amp; American Life Project, ..            &nbsp; <a href='http://www.currybet.net/cbet_blog/2010/09/handling-errors.php' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/currybet' style='color:#093D72; text-decoration: none; font-weight: bold;'>currybet:</a></span>&nbsp;Nieman Journalism Lab shows the way to do apologies and corrections online http://bit.ly/aL8I4J&nbsp;
            <span><a target='_blank' href='http://twitter.com/currybet/status/24553178220' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 01.39.29</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/sophiebr' style='color:#093D72; text-decoration: none; font-weight: bold;'>sophiebr:</a></span>&nbsp;Good example of best practice RT @BBCCollege: Martin Belam: the way to do apologies and corrections online http://bit.ly/bg5xCS&nbsp;
            <span><a target='_blank' href='http://twitter.com/sophiebr/status/24555077284' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 02.23.03</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/stevebuttry' style='color:#093D72; text-decoration: none; font-weight: bold;'>stevebuttry:</a></span>&nbsp;Classy recovery by @lkmcgann. RT @currybet Nieman Journalism Lab shows the way to do apologies and corrections online http://bit.ly/aL8I4J&nbsp;
            <span><a target='_blank' href='http://twitter.com/stevebuttry/status/24562249121' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.43.56</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.currybet.net%2Fcbet_blog%2F2010%2F09%2Fhandling-errors.php&title=Nieman+Journalism+Lab+shows+the+way+to+do+apologies+and+corrections+online+-+Martin+Belam%27s+currybetdotnet+blog+-+September+15%2C+2010'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Tech Weekly at Nokia World | Technology | guardian.co.uk]]></title>
     <pubDate>Wed Sep 15 04:12:59 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284560824564]]></guid>

     <link><![CDATA[http://www.guardian.co.uk/technology/blog/audio/2010/sep/15/nokia-world-jane-mcgonigal-mobile-gartenberg]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.guardian.co.uk/technology/blog/audio/2010/sep/15/nokia-world-jane-mcgonigal-mobile-gartenberg' style='color: #009900; text-decoration: none;'>guardian.co.uk</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          Aleks Krotoski presents this week's programme from Nokia World &ndash; the Finnish mobile communication giant's world conference and developer summit in London's Docklands.
Charles Arthur catches up with technology analyst Michael Gartenberg to find out what he makes of this week's movements at the top of Nokia that have seen its chairman and former chief executive Jorma Ollia, its current chief executive Olli-Pekka Kallasvuo, and the head of mobile Anssi Vanjoki announce their departures from ..            &nbsp; <a href='http://www.guardian.co.uk/technology/blog/audio/2010/sep/15/nokia-world-jane-mcgonigal-mobile-gartenberg' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/guardiantech' style='color:#093D72; text-decoration: none; font-weight: bold;'>guardiantech:</a></span>&nbsp;Tech Weekly at Nokia World http://bit.ly/diC8wS&nbsp;
            <span><a target='_blank' href='http://twitter.com/guardiantech/status/24560831764' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.19.48</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/aleksk' style='color:#093D72; text-decoration: none; font-weight: bold;'>aleksk:</a></span>&nbsp;By Me @ Guardian>> Tech Weekly at Nokia World: Aleks Krotoski presents this week's programme from Nokia World – th... http://bit.ly/cIUUtS&nbsp;
            <span><a target='_blank' href='http://twitter.com/aleksk/status/24560703174' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.17.29</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/charlesarthur' style='color:#093D72; text-decoration: none; font-weight: bold;'>charlesarthur:</a></span>&nbsp;By me @ Guardian: Tech Weekly at Nokia World http://bit.ly/b3CRLx #fb&nbsp;
            <span><a target='_blank' href='http://twitter.com/charlesarthur/status/24560816823' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.19.32</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediaguardian' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediaguardian:</a></span>&nbsp;Tech Weekly at Nokia World http://bit.ly/cIUUtS&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediaguardian/status/24560453051' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.12.59</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.guardian.co.uk%2Ftechnology%2Fblog%2Faudio%2F2010%2Fsep%2F15%2Fnokia-world-jane-mcgonigal-mobile-gartenberg&title=Tech+Weekly+at+Nokia+World+%7C+Technology+%7C+guardian.co.uk'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Is Murdoch preparing to launch two 'tablet newspapers'? | Media | guardian.co.uk]]></title>
     <pubDate>Wed Sep 15 04:42:44 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284560824563]]></guid>
     <link><![CDATA[http://www.guardian.co.uk/media/greenslade/2010/sep/15/news-corporation-robert-thomson]]></link>

     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.guardian.co.uk/media/greenslade/2010/sep/15/news-corporation-robert-thomson' style='color: #009900; text-decoration: none;'>guardian.co.uk</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          I'm not certain what we're to make of the hush-hush unit being set up for News Corporation under the aegis of the Wall Street Journal's managing editor Robert Thomson, as revealed in a memo posted by Romenesko (well done again, Jim).
I say hush-hush, but the so-called &quot;WSJ/DJ Special Project&quot; was hardly going to remain secret after Thomson circulated the memo to WSJ staff.
But, unless he is trying to provoke rivals by laying a false trail, it must be big because he refers to it being ..            &nbsp; <a href='http://www.guardian.co.uk/media/greenslade/2010/sep/15/news-corporation-robert-thomson' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/janinegibson' style='color:#093D72; text-decoration: none; font-weight: bold;'>janinegibson:</a></span>&nbsp;Is Murdoch preparing to launch two 'tablet newspapers'? http://bit.ly/a170ka&nbsp;
            <span><a target='_blank' href='http://twitter.com/janinegibson/status/24564237197' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 05.15.04</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediaguardian' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediaguardian:</a></span>&nbsp;Is Murdoch preparing to launch two 'tablet newspapers'? http://bit.ly/a170ka&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediaguardian/status/24562177302' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.42.44</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/guardiantech' style='color:#093D72; text-decoration: none; font-weight: bold;'>guardiantech:</a></span>&nbsp;Is Murdoch preparing to launch two 'tablet newspapers'? http://bit.ly/9Rampo&nbsp;
            <span><a target='_blank' href='http://twitter.com/guardiantech/status/24562630854' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.50.11</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.guardian.co.uk%2Fmedia%2Fgreenslade%2F2010%2Fsep%2F15%2Fnews-corporation-robert-thomson&title=Is+Murdoch+preparing+to+launch+two+%27tablet+newspapers%27%3F+%7C+Media+%7C+guardian.co.uk'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[TIME Magazine -- U.S. Edition -- September 20, 2010 Vol. 176 No. 12]]></title>
     <pubDate>Wed Sep 15 05:47:34 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284560824562]]></guid>
     <link><![CDATA[http://www.time.com/time/magazine/current]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.time.com/time/magazine/current' style='color: #009900; text-decoration: none;'>time.com</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/currybet' style='color:#093D72; text-decoration: none; font-weight: bold;'>currybet:</a></span>&nbsp;Slight difference between the US and world editions of Time this week http://bit.ly/bdvPdk&nbsp;
            <span><a target='_blank' href='http://twitter.com/currybet/status/24566557763' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 05.48.38</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/qwghlm' style='color:#093D72; text-decoration: none; font-weight: bold;'>qwghlm:</a></span>&nbsp;Slight difference between the US and world editions of Time this week http://bit.ly/bdvPdk&nbsp;
            <span><a target='_blank' href='http://twitter.com/qwghlm/status/24566481547' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 05.47.34</a></span>
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
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.time.com%2Ftime%2Fmagazine%2Fcurrent&title=TIME+Magazine+--+U.S.+Edition+--+September+20%2C+2010+Vol.+176+No.+12'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>

   </item>
   <item>
     <title><![CDATA[Poynter Online - Top Stories]]></title>
     <pubDate>Wed Sep 15 06:21:59 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284560824561]]></guid>
     <link><![CDATA[http://www.poynter.org/column.asp?id=101&aid=190565]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.poynter.org/column.asp?id=101&aid=190565' style='color: #009900; text-decoration: none;'>poynter.org</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/RobertAndrews' style='color:#093D72; text-decoration: none; font-weight: bold;'>RobertAndrews:</a></span>&nbsp;RT @rafatali: the sheer physical toll that digital news industry takes on you is the unsaid story of our gen http://bit.ly/cbJazb&nbsp;
            <span><a target='_blank' href='http://twitter.com/RobertAndrews/status/24578850438' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.18.50</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(8)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/rorybrown' style='color:#093D72; text-decoration: none; font-weight: bold;'>rorybrown:</a></span>&nbsp;.@RafatAli sees tough future for online content http://journ.us/d6hZkS  as he ponders travel guide industry venture. http://journ.us/dlVkhz&nbsp;
            <span><a target='_blank' href='http://twitter.com/rorybrown/status/24569114235' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.22.54</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/craigmcginty' style='color:#093D72; text-decoration: none; font-weight: bold;'>craigmcginty:</a></span>&nbsp;paidContent's Rafat Ali Describes Grim View of Online News' Prospects http://bit.ly/9ezgvX&nbsp;
            <span><a target='_blank' href='http://twitter.com/craigmcginty/status/24582300395' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.59.43</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/KristineLowe' style='color:#093D72; text-decoration: none; font-weight: bold;'>KristineLowe:</a></span>&nbsp;Lots 2 ponder: PaidContent founder @rafatali bearish on online news http://bit.ly/9ezgvX  ht @johncthompson MoreUpbeat: http://bit.ly/aQj7Sq&nbsp;
            <span><a target='_blank' href='http://twitter.com/KristineLowe/status/24587377733' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.03.04</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/romenesko' style='color:#093D72; text-decoration: none; font-weight: bold;'>romenesko:</a></span>&nbsp;paidContent founder Rafat Ali discusses media covering media, Flipboard, TBD, Blodget, Denton & more. http://journ.us/d6hZkS&nbsp;
            <span><a target='_blank' href='http://twitter.com/romenesko/status/24570413334' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.39.29</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/rafatali' style='color:#093D72; text-decoration: none; font-weight: bold;'>rafatali:</a></span>&nbsp;In which I tell Poynter why I exited the news industry. Boy I sound bearish. http://bit.ly/cbJazb&nbsp;
            <span><a target='_blank' href='http://twitter.com/rafatali/status/24569730181' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.30.49</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/davidaKaplan' style='color:#093D72; text-decoration: none; font-weight: bold;'>davidaKaplan:</a></span>&nbsp;the sheer physical toll that digital news industry takes on you is the unsaid story of our gen http://bit.ly/cbJazb will write more on it.&nbsp;
            <span><a target='_blank' href='http://twitter.com/davidaKaplan/status/24581212631' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.46.39</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/johncthompson' style='color:#093D72; text-decoration: none; font-weight: bold;'>johncthompson:</a></span>&nbsp;PaidContent founder Rafat Ali bearish on online news prospects http://bit.ly/9ezgvX&nbsp;
            <span><a target='_blank' href='http://twitter.com/johncthompson/status/24585344183' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.36.57</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/rafatali' style='color:#093D72; text-decoration: none; font-weight: bold;'>rafatali:</a></span>&nbsp;the sheer physical toll that digital news industry takes on you is the unsaid story of our gen http://bit.ly/cbJazb will write more on it.&nbsp;
            <span><a target='_blank' href='http://twitter.com/rafatali/status/24578012983' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.09.14</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu' style='color:#093D72; text-decoration: none; font-weight: bold;'>jayrosen_nyu:</a></span>&nbsp;"Everything undercuts everything." @rafatali explains why the news business is an impossible business to be in right now http://jr.ly/69nh&nbsp;
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu/status/24569043926' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.21.59</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.poynter.org%2Fcolumn.asp%3Fid%3D101%26aid%3D190565&title=Poynter+Online+-+Top+Stories'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>

   <item>
     <title><![CDATA[Twitter COO Dick Costolo On Ad Sales and the New Twitter.com | Peter Kafka | MediaMemo | AllThingsD]]></title>
     <pubDate>Tue Sep 14 18:45:16 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284560824560]]></guid>
     <link><![CDATA[http://mediamemo.allthingsd.com/20100914/the-new-twitter-com-is-a-consumption-environment-translation-twitter-is-a-reluctant-media-company]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://mediamemo.allthingsd.com/20100914/the-new-twitter-com-is-a-consumption-environment-translation-twitter-is-a-reluctant-media-company' style='color: #009900; text-decoration: none;'>mediamemo.allthingsd.com</a>
    </div>
    <div>
       <div id='fooid#img'>
              <img src='http://mediamemo.allthingsd.com/files/2009/09/3d-glasses-life-226x300.jpg' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
       </div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          Twitter&rsquo;s new Web site has lots of cool features and gizmos. But they&rsquo;re all supposed to do one thing in particular: They&rsquo;re meant to encourage you to spend more time on Twitter.com, where the company can show you some ads.
The Twitter folks don&rsquo;t exactly present it that way, of course. I talked to CEO Ev Williams and COO Dick Costolo earlier today, and they both repeated the mantra that the new Twitter is supposed to reflect the fact that Twitter is a &ldquo;consumption..            &nbsp; <a href='http://mediamemo.allthingsd.com/20100914/the-new-twitter-com-is-a-consumption-environment-translation-twitter-is-a-reluctant-media-company' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(5)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/rachelsterne' style='color:#093D72; text-decoration: none; font-weight: bold;'>rachelsterne:</a></span>&nbsp;RT @ckanal Reading @pkafka's take on #NewTwitter. For him, it signifies Twitter has become a media company: http://bit.ly/bK75OB&nbsp;
            <span><a target='_blank' href='http://twitter.com/rachelsterne/status/24542067536' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 21.45.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/JimMacMillan' style='color:#093D72; text-decoration: none; font-weight: bold;'>JimMacMillan:</a></span>&nbsp;RT @jayrosen_nyu: Twitter is a media company? http://jr.ly/69nc I didn't know that. Will have to consider.&nbsp;
            <span><a target='_blank' href='http://twitter.com/JimMacMillan/status/24567562666' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.02.22</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mathewi' style='color:#093D72; text-decoration: none; font-weight: bold;'>mathewi:</a></span>&nbsp;@pkafka has a point here -- Twitter is coming to terms with being a media company: http://is.gd/fb6Lv  -- also this: http://is.gd/fb6Pb&nbsp;
            <span><a target='_blank' href='http://twitter.com/mathewi/status/24529070147' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 18.45.16</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/gaberivera' style='color:#093D72; text-decoration: none; font-weight: bold;'>gaberivera:</a></span>&nbsp;The New Twitter.com Is a "Consumption Environment". ... (@pkafka / MediaMemo) http://j.mp/9LZzs0 http://techme.me/A3GC&nbsp;
            <span><a target='_blank' href='http://twitter.com/gaberivera/status/24554506131' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 02.10.09</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu' style='color:#093D72; text-decoration: none; font-weight: bold;'>jayrosen_nyu:</a></span>&nbsp;Twitter is a media company? http://jr.ly/69nc I didn't know that. Will have to consider.&nbsp;
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu/status/24567527158' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 06.01.57</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fmediamemo.allthingsd.com%2F20100914%2Fthe-new-twitter-com-is-a-consumption-environment-translation-twitter-is-a-reluctant-media-company&title=Twitter+COO+Dick+Costolo+On+Ad+Sales+and+the+New+Twitter.com+%7C+Peter+Kafka+%7C+MediaMemo+%7C+AllThingsD'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>

     <title><![CDATA[Google confirms plans for social features | Technology | guardian.co.uk]]></title>
     <pubDate>Wed Sep 15 02:29:28 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284555825092]]></guid>
     <link><![CDATA[http://www.guardian.co.uk/technology/pda/2010/sep/15/google-social-networking]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.guardian.co.uk/technology/pda/2010/sep/15/google-social-networking' style='color: #009900; text-decoration: none;'>guardian.co.uk</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/guardiantech' style='color:#093D72; text-decoration: none; font-weight: bold;'>guardiantech:</a></span>&nbsp;Google confirms plans for social features http://bit.ly/arROyC&nbsp;
            <span><a target='_blank' href='http://twitter.com/guardiantech/status/24556200835' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 02.48.00</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediaguardian' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediaguardian:</a></span>&nbsp;Google confirms plans for social features http://bit.ly/aICoN0&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediaguardian/status/24555366455' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 02.29.28</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/JonClements' style='color:#093D72; text-decoration: none; font-weight: bold;'>JonClements:</a></span>&nbsp;And Twitter says it's "not a social network". Confused? - RT @ESpotlight: Google confirms plans for social features - http://bit.ly/cszGrN&nbsp;
            <span><a target='_blank' href='http://twitter.com/JonClements/status/24558070317' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 03.27.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/JoshHalliday' style='color:#093D72; text-decoration: none; font-weight: bold;'>JoshHalliday:</a></span>&nbsp;By me @ t'Guardian: Google confirms plans for social features http://bit.ly/bNUs87&nbsp;
            <span><a target='_blank' href='http://twitter.com/JoshHalliday/status/24558634282' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 03.38.48</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.guardian.co.uk%2Ftechnology%2Fpda%2F2010%2Fsep%2F15%2Fgoogle-social-networking&title=Google+confirms+plans+for+social+features+%7C+Technology+%7C+guardian.co.uk'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Twitter unveils new multimedia features | Technology | guardian.co.uk]]></title>

     <pubDate>Tue Sep 14 18:07:39 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284537945291]]></guid>
     <link><![CDATA[http://www.guardian.co.uk/technology/blog/2010/sep/15/new-twitter-unveiled]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.guardian.co.uk/technology/blog/2010/sep/15/new-twitter-unveiled' style='color: #009900; text-decoration: none;'>guardian.co.uk</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/ithiad' style='color:#093D72; text-decoration: none; font-weight: bold;'>ithiad:</a></span>&nbsp;<i>[protected tweet]</i>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(3)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/charlesarthur' style='color:#093D72; text-decoration: none; font-weight: bold;'>charlesarthur:</a></span>&nbsp;By me @ Guardian: Twitter unveils new multimedia features http://bit.ly/cPMqbY #fb&nbsp;
            <span><a target='_blank' href='http://twitter.com/charlesarthur/status/24528379486' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 18.36.35</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/guardiantech' style='color:#093D72; text-decoration: none; font-weight: bold;'>guardiantech:</a></span>&nbsp;Twitter unveils new multimedia features http://bit.ly/d1CUGf&nbsp;
            <span><a target='_blank' href='http://twitter.com/guardiantech/status/24526078014' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 18.07.39</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediaguardian' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediaguardian:</a></span>&nbsp;Twitter unveils new multimedia features http://bit.ly/a1Ql1b&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediaguardian/status/24527582113' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 18.26.40</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.guardian.co.uk%2Ftechnology%2Fblog%2F2010%2Fsep%2F15%2Fnew-twitter-unveiled&title=Twitter+unveils+new+multimedia+features+%7C+Technology+%7C+guardian.co.uk'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Boxee bounds into internet TV market | Media | guardian.co.uk]]></title>
     <pubDate>Tue Sep 14 09:52:59 PDT 2010</pubDate>

     <guid isPermaLink='false'><![CDATA[user.14697612::1284529937108]]></guid>
     <link><![CDATA[http://www.guardian.co.uk/media/pda/2010/sep/14/boxee-internet-tv]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.guardian.co.uk/media/pda/2010/sep/14/boxee-internet-tv' style='color: #009900; text-decoration: none;'>guardian.co.uk</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          Internet TV products from Apple and Google get new competition in form of US start-up Boxee
Google and Apple are to face competition from US start-ups as Boxee becomes the latest company to throw its hat into the internet TV ring, launching within weeks of efforts from the California-based technology companies.
Until now a software-only company, Boxee will begin shipping it's long-awaited set-top box in mid-November, two months after Apple TV's latest iteration and around the same time as Googl..            &nbsp; <a href='http://www.guardian.co.uk/media/pda/2010/sep/14/boxee-internet-tv' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/waldo' style='color:#093D72; text-decoration: none; font-weight: bold;'>waldo:</a></span>&nbsp;Boxee bounds into internet TV market http://j.mp/dwpRUT  <because the world needs another set top box&nbsp;
            <span><a target='_blank' href='http://twitter.com/waldo/status/24541381668' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 21.34.24</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/Addiply' style='color:#093D72; text-decoration: none; font-weight: bold;'>Addiply:</a></span>&nbsp;RT @mediaguardian: Boxee enters internet TV market http://bit.ly/chIM59 <Someone sent that to Mr Shott, Mr Parry & Mr Hunt? Local 'tv' box.&nbsp;
            <span><a target='_blank' href='http://twitter.com/Addiply/status/24492492208' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 09.58.48</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/guardiantech' style='color:#093D72; text-decoration: none; font-weight: bold;'>guardiantech:</a></span>&nbsp;Boxee bounds into internet TV market http://bit.ly/cCXEil&nbsp;
            <span><a target='_blank' href='http://twitter.com/guardiantech/status/24492026826' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 09.52.59</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediaguardian' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediaguardian:</a></span>&nbsp;Boxee bounds into internet TV market http://bit.ly/chIM59&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediaguardian/status/24492085174' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 09.53.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/JoshHalliday' style='color:#093D72; text-decoration: none; font-weight: bold;'>JoshHalliday:</a></span>&nbsp;By me @ t'Guardian: Boxee bounds into internet TV market http://bit.ly/dqw0vQ&nbsp;
            <span><a target='_blank' href='http://twitter.com/JoshHalliday/status/24494639402' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.26.57</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.guardian.co.uk%2Fmedia%2Fpda%2F2010%2Fsep%2F14%2Fboxee-internet-tv&title=Boxee+bounds+into+internet+TV+market+%7C+Media+%7C+guardian.co.uk'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Meet the new Twitter.com]]></title>
     <pubDate>Tue Sep 14 16:42:22 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284513463926]]></guid>

     <link><![CDATA[http://twitter.com/newtwitter]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://twitter.com/newtwitter' style='color: #009900; text-decoration: none;'>twitter.com</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/jaggeree' style='color:#093D72; text-decoration: none; font-weight: bold;'>jaggeree:</a></span>&nbsp;RT @joshelman: Meet the new Twitter.com: http://t.co/AYZZWUV&nbsp;
            <span><a target='_blank' href='http://twitter.com/jaggeree/status/24519639229' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.42.22</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(15)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/Jason' style='color:#093D72; text-decoration: none; font-weight: bold;'>Jason:</a></span>&nbsp;wow... the new twitter is amazing! http://twitter.com/newtwitter congrats @ev @biz @goldman and @twitter team!!! #newtwitter #sick&nbsp;
            <span><a target='_blank' href='http://twitter.com/Jason/status/24519835936' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.45.07</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/pwthornton' style='color:#093D72; text-decoration: none; font-weight: bold;'>pwthornton:</a></span>&nbsp;The new Twitter looks pretty awesome: http://bit.ly/co8rJq&nbsp;
            <span><a target='_blank' href='http://twitter.com/pwthornton/status/24578471069' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.14.26</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/adnys' style='color:#093D72; text-decoration: none; font-weight: bold;'>adnys:</a></span>&nbsp;I barely understand old twitter! @twitter #newtwitter Embedded media, infinite scroll, new architecture & more.video: twitter.com/newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/adnys/status/24552700440' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 01.28.12</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;Meet the new Twitter: http://rww.to/b733GS #NewTwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24519688716' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.43.04</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediatwit' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediatwit:</a></span>&nbsp;Sneak of new Twitter format at http://twitter.com/newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediatwit/status/24519805953' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.44.43</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/kcorrick' style='color:#093D72; text-decoration: none; font-weight: bold;'>kcorrick:</a></span>&nbsp;RT @joshelman: Meet the new Twitter.com: http://t.co/AYZZWUV&nbsp;
            <span><a target='_blank' href='http://twitter.com/kcorrick/status/24520166501' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.49.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/tomcoates' style='color:#093D72; text-decoration: none; font-weight: bold;'>tomcoates:</a></span>&nbsp;Twitter announces their new design, with inline images/video from 16 partners including @kickstarter! http://twitter.com/newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/tomcoates/status/24521436946' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.06.52</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/twittermedia' style='color:#093D72; text-decoration: none; font-weight: bold;'>twittermedia:</a></span>&nbsp;You can find more details on the #NewTwitter right over here: http://t.co/sYO4YYX (And note the #VMA tweet!)&nbsp;
            <span><a target='_blank' href='http://twitter.com/twittermedia/status/24521580929' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.08.49</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/chrismessina' style='color:#093D72; text-decoration: none; font-weight: bold;'>chrismessina:</a></span>&nbsp;#newtwitter Embedded media, infinite scroll, new architecture and so much more. Watch the video! http://t.co/E9Dbh0H&nbsp;
            <span><a target='_blank' href='http://twitter.com/chrismessina/status/24523494745' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.33.56</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/dannysullivan' style='color:#093D72; text-decoration: none; font-weight: bold;'>dannysullivan:</a></span>&nbsp;From @twitter: Meet the new Twitter.com http://t.co/E9Dbh0H&nbsp;
            <span><a target='_blank' href='http://twitter.com/dannysullivan/status/24521491216' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.07.35</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/davewiner' style='color:#093D72; text-decoration: none; font-weight: bold;'>davewiner:</a></span>&nbsp;Meet the new Twitter.com. http://r2.ly/4wjk&nbsp;
            <span><a target='_blank' href='http://twitter.com/davewiner/status/24520545477' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.54.56</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/noodlepie' style='color:#093D72; text-decoration: none; font-weight: bold;'>noodlepie:</a></span>&nbsp;This 2 minute video took me 45 minutes to load #africa #broadband #realitycheck RT @IanDouglas http://twitter.com/newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/noodlepie/status/24553592589' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 01.49.16</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/IanDouglas' style='color:#093D72; text-decoration: none; font-weight: bold;'>IanDouglas:</a></span>&nbsp;http://twitter.com/newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/IanDouglas/status/24551182654' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 00.52.37</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/leolaporte' style='color:#093D72; text-decoration: none; font-weight: bold;'>leolaporte:</a></span>&nbsp;Meet the new Twitter.com: http://twitter.com/newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/leolaporte/status/24520011524' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.47.33</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/SocialMedia411' style='color:#093D72; text-decoration: none; font-weight: bold;'>SocialMedia411:</a></span>&nbsp;Meet the new Twitter.com: http://bit.ly/dBpVe5&nbsp;
            <span><a target='_blank' href='http://twitter.com/SocialMedia411/status/24523325478' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.31.47</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Ftwitter.com%2Fnewtwitter&title=Meet+the+new+Twitter.com'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[scobleizer on USTREAM: Robert Scoble interviews technologists about the bleeding edge of the Web. He is a tech enthusiast, video blogger, media innovator, ...]]></title>
     <pubDate>Tue Sep 14 15:29:38 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284513463925]]></guid>
     <link><![CDATA[http://www.ustream.tv/channel/scobleizer]]></link>

     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.ustream.tv/channel/scobleizer' style='color: #009900; text-decoration: none;'>ustream.tv</a>
    </div>
    <div style='margin: 3px 0 3px 0;'>
        <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="320" height="260" id="utv806673"><param name="flashvars" value="autoplay=false&brand=embed&cid=4420903&locale=en_US"/><param name="allowfullscreen" value="true"/><param name="allowscriptaccess" value="always"/><param name="movie" value="http://www.ustream.tv/flash/live/1/4420903"/><embed flashvars="autoplay=false&brand=embed&cid=4420903&locale=en_US" width="320" height="260" allowfullscreen="true" allowscriptaccess="always" id="utv806673" name="utv_n_545616" src="http://www.ustream.tv/flash/live/1/4420903" type="application/x-shockwave-flash" /></object>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(12)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/adnys' style='color:#093D72; text-decoration: none; font-weight: bold;'>adnys:</a></span>&nbsp;I just got permission to live stream the Twitter press conference. I will do that at http://www.ustream.tv/channel/scobleizer Starts at 4 pm&nbsp;
            <span><a target='_blank' href='http://twitter.com/adnys/status/24515189188' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.40.39</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/davewiner' style='color:#093D72; text-decoration: none; font-weight: bold;'>davewiner:</a></span>&nbsp;Scoble has video from twitter's announcement. http://r2.ly/9qqv&nbsp;
            <span><a target='_blank' href='http://twitter.com/davewiner/status/24515747611' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.48.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediatwit' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediatwit:</a></span>&nbsp;Fun to watch @ev and @biz at Twitter announcement via @Scobleizer
http://www.ustream.tv/channel/scobleizer&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediatwit/status/24517097609' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.07.25</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/jowyang' style='color:#093D72; text-decoration: none; font-weight: bold;'>jowyang:</a></span>&nbsp;Twitter's announcement is being ustreamed by @scobleizer here http://www.ustream.tv/channel/scobleizer&nbsp;
            <span><a target='_blank' href='http://twitter.com/jowyang/status/24518688970' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.29.14</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/sacca' style='color:#093D72; text-decoration: none; font-weight: bold;'>sacca:</a></span>&nbsp;Live streaming from Twitter press conference here: http://www.ustream.tv/channel/scobleizer @nickhalstead says announcements are "huge."&nbsp;
            <span><a target='_blank' href='http://twitter.com/sacca/status/24516619266' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.00.58</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/themediaisdying' style='color:#093D72; text-decoration: none; font-weight: bold;'>themediaisdying:</a></span>&nbsp;Thanks @scobelizer  for live streaming the Twitter event / press conference. : http://bit.ly/c5Du4n  Starts at 4 pm PDT&nbsp;
            <span><a target='_blank' href='http://twitter.com/themediaisdying/status/24514621497' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.32.33</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Scobleizer' style='color:#093D72; text-decoration: none; font-weight: bold;'>Scobleizer:</a></span>&nbsp;Live streaming from Twitter press conference here: http://www.ustream.tv/channel/scobleizer @nickhalstead says announcements are "huge."&nbsp;
            <span><a target='_blank' href='http://twitter.com/Scobleizer/status/24515904302' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.51.00</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/ginatrapani' style='color:#093D72; text-decoration: none; font-weight: bold;'>ginatrapani:</a></span>&nbsp;thanks to @scobleizer for live streaming the Twitter announcement starting in 5 mins: http://bit.ly/c5Du4n&nbsp;
            <span><a target='_blank' href='http://twitter.com/ginatrapani/status/24516275898' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.56.13</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/sdkstl' style='color:#093D72; text-decoration: none; font-weight: bold;'>sdkstl:</a></span>&nbsp;Completely new Twitter.com starting to roll out in 30 min. Built off its own API. (new fail whale, too?) livestream  http://bit.ly/dc3ifB&nbsp;
            <span><a target='_blank' href='http://twitter.com/sdkstl/status/24519163742' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.35.45</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Scobleizer' style='color:#093D72; text-decoration: none; font-weight: bold;'>Scobleizer:</a></span>&nbsp;I just got permission to live stream the Twitter press conference. I will do that at http://www.ustream.tv/channel/scobleizer Starts at 4 pm&nbsp;
            <span><a target='_blank' href='http://twitter.com/Scobleizer/status/24514412681' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.29.38</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Scobleizer' style='color:#093D72; text-decoration: none; font-weight: bold;'>Scobleizer:</a></span>&nbsp;Come get a tour of Twitter's lobby. http://www.ustream.tv/channel/scobleizer live.&nbsp;
            <span><a target='_blank' href='http://twitter.com/Scobleizer/status/24514898276' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.36.26</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/dannysullivan' style='color:#093D72; text-decoration: none; font-weight: bold;'>dannysullivan:</a></span>&nbsp;from scobleize,r live stream of twitter announcement http://bit.ly/aqs2Zu - so far, nothing but a big Mork & Mindy looking egg&nbsp;
            <span><a target='_blank' href='http://twitter.com/dannysullivan/status/24516594884' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.00.39</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/laughingsquid' style='color:#093D72; text-decoration: none; font-weight: bold;'>laughingsquid:</a></span>&nbsp;- @scobleizer is live streaming the @twitter "hatching" event http://bit.ly/9imb4T & @parislemon is live blogging it http://tcrn.ch/cMf0U1&nbsp;
            <span><a target='_blank' href='http://twitter.com/laughingsquid/status/24516882783' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.04.30</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mikebutcher' style='color:#093D72; text-decoration: none; font-weight: bold;'>mikebutcher:</a></span>&nbsp;The perfect storm of @Scobleizer and a live Twitter press conference http://bit.ly/c5Du4n&nbsp;
            <span><a target='_blank' href='http://twitter.com/mikebutcher/status/24517134699' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.07.55</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.ustream.tv%2Fchannel%2Fscobleizer&title=scobleizer+on+USTREAM%3A+Robert+Scoble+interviews+technologists+about+the+bleeding+edge+of+the+Web.+He+is+a+tech+enthusiast%2C+video+blogger%2C+media+innovator%2C+...'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Twitter]]></title>
     <pubDate>Tue Sep 14 10:23:46 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284513463924]]></guid>
     <link><![CDATA[http://twitter.com]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://twitter.com' style='color: #009900; text-decoration: none;'>twitter.com</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/ryansholin' style='color:#093D72; text-decoration: none; font-weight: bold;'>ryansholin:</a></span>&nbsp;OK, I watched the intro video for the Twitter.com redesign. Now gimme it. Gimme gimme. Want.&nbsp;
            <span><a target='_blank' href='http://twitter.com/ryansholin/status/24559035375' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 03.46.30</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(24)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;The new Twitter.com will be released to select users first. Live blog: http://rww.to/cwHRaq #NewTwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24518967831' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.33.02</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mashable' style='color:#093D72; text-decoration: none; font-weight: bold;'>mashable:</a></span>&nbsp;Our top story right now: "Here Comes the New Twitter.com" - http://mash.to/2EGcp&nbsp;
            <span><a target='_blank' href='http://twitter.com/mashable/status/24581749879' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.53.02</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;Twitter has partnered with 16 media providers to enhance the new Twitter.com experience. http://rww.to/cwHRaq&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24518574360' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.27.37</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/sophiebr' style='color:#093D72; text-decoration: none; font-weight: bold;'>sophiebr:</a></span>&nbsp;Good blog from @econsultancy on Twitter's redesign http://bit.ly/avLr5A. I rarely use Twitter.com these days, will be interesting...&nbsp;
            <span><a target='_blank' href='http://twitter.com/sophiebr/status/24554027819' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 01.59.24</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mathewi' style='color:#093D72; text-decoration: none; font-weight: bold;'>mathewi:</a></span>&nbsp;@kevinmarks I said in my original post that 3rd-party client users are some of the most active. Twitter.com is by far the biggest client tho&nbsp;
            <span><a target='_blank' href='http://twitter.com/mathewi/status/24522921823' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.26.31</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/SocialMedia411' style='color:#093D72; text-decoration: none; font-weight: bold;'>SocialMedia411:</a></span>&nbsp;Evan Williams Coughed Up $7,500 For Twitter.com Back In The Day (TechCrunch): http://tcrn.ch/9fHTyr&nbsp;
            <span><a target='_blank' href='http://twitter.com/SocialMedia411/status/24494405540' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.23.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Addiply' style='color:#093D72; text-decoration: none; font-weight: bold;'>Addiply:</a></span>&nbsp;They pitch the new Twitter.com as a better, richer way to navigate. But where are the ads? #NewTwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/Addiply/status/24519077006' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.34.32</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/laughingsquid' style='color:#093D72; text-decoration: none; font-weight: bold;'>laughingsquid:</a></span>&nbsp;congrats @k, @trammell & team, looking forward to seeing the new twitter.com&nbsp;
            <span><a target='_blank' href='http://twitter.com/laughingsquid/status/24521009218' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.01.11</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/kevinmarks' style='color:#093D72; text-decoration: none; font-weight: bold;'>kevinmarks:</a></span>&nbsp;I still say @ev quoting 78% of users on twitter.com is misleading. Did he say what percentage of activity was there?&nbsp;
            <span><a target='_blank' href='http://twitter.com/kevinmarks/status/24521139737' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.02.57</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/steverubel' style='color:#093D72; text-decoration: none; font-weight: bold;'>steverubel:</a></span>&nbsp;New Twitter.com is all about appealing to the masses. That's what they need to do to get to the next level.&nbsp;
            <span><a target='_blank' href='http://twitter.com/steverubel/status/24526713733' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 18.15.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediatwit' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediatwit:</a></span>&nbsp;They pitch the new Twitter.com as a better, richer way to navigate. But where are the ads? #NewTwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediatwit/status/24518999699' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.33.28</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/laughingsquid' style='color:#093D72; text-decoration: none; font-weight: bold;'>laughingsquid:</a></span>&nbsp;the @twitter team is about to start making the new twitter.com live and it will slowly start rolling out to users&nbsp;
            <span><a target='_blank' href='http://twitter.com/laughingsquid/status/24520544062' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.54.55</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/acarvin' style='color:#093D72; text-decoration: none; font-weight: bold;'>acarvin:</a></span>&nbsp;@webbmedia: I always thought the data was opposite - that most people had left twitter.com and were using clients. Confused.&nbsp;
            <span><a target='_blank' href='http://twitter.com/acarvin/status/24521437188' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.06.52</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/davewiner' style='color:#093D72; text-decoration: none; font-weight: bold;'>davewiner:</a></span>&nbsp;How the New Twitter.com Gives Your Favorite App a Run for Its Money. http://r2.ly/4wmi&nbsp;
            <span><a target='_blank' href='http://twitter.com/davewiner/status/24560438462' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.12.43</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediatwit' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediatwit:</a></span>&nbsp;New Twitter.com website according to @ev: faster, better experience to find things in your world. [Playing video]&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediatwit/status/24518218217' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.22.44</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/om' style='color:#093D72; text-decoration: none; font-weight: bold;'>om:</a></span>&nbsp;good stuff from @lizgannes: 10 Things You Didn’t Know About the New Twitter.com http://bit.ly/bMHrbj”&nbsp;
            <span><a target='_blank' href='http://twitter.com/om/status/24533220496' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 19.38.49</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/matthewbuckland' style='color:#093D72; text-decoration: none; font-weight: bold;'>matthewbuckland:</a></span>&nbsp;Twitter.com unveils a new, richer interface http://bit.ly/afXJj7&nbsp;
            <span><a target='_blank' href='http://twitter.com/matthewbuckland/status/24560383140' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.11.44</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;Agree. RT @kosso: @RWW that aside, one thing which I think the new twitter.com site is really lacking is multiple account sign-in&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24583666635' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 09.16.16</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;New Twitter.com with integrated media. Pretty sweet demo video. We'll post vid when available. http://rww.to/cwHRaq&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24518434943' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.25.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/xenijardin' style='color:#093D72; text-decoration: none; font-weight: bold;'>xenijardin:</a></span>&nbsp;@ev, hadn't you heard the web is dead? (congrats, digging the new twitter.com)&nbsp;
            <span><a target='_blank' href='http://twitter.com/xenijardin/status/24521054916' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.01.48</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mashable' style='color:#093D72; text-decoration: none; font-weight: bold;'>mashable:</a></span>&nbsp;Here Comes the New Twitter.com - http://mash.to/2EjHw [updated with more images of the new interface] #newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/mashable/status/24524013440' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.40.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/SocialMedia411' style='color:#093D72; text-decoration: none; font-weight: bold;'>SocialMedia411:</a></span>&nbsp;Best review of the new Twitter.com I've seen so far: "Nice incremental change." #BackHandedCompliment&nbsp;
            <span><a target='_blank' href='http://twitter.com/SocialMedia411/status/24532643043' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 19.31.13</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/gaberivera' style='color:#093D72; text-decoration: none; font-weight: bold;'>gaberivera:</a></span>&nbsp;The New Twitter.com Is a "Consumption Environment". ... (@pkafka / MediaMemo) http://j.mp/9LZzs0 http://techme.me/A3GC&nbsp;
            <span><a target='_blank' href='http://twitter.com/gaberivera/status/24554506131' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 02.10.09</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/ev' style='color:#093D72; text-decoration: none; font-weight: bold;'>ev:</a></span>&nbsp;Twitter.com has always been the most popular Twitter client. 78% of active users have used it in the last 30 days. http://t.co/cDH1P0j&nbsp;
            <span><a target='_blank' href='http://twitter.com/ev/status/24517964294' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.19.17</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mathewi' style='color:#093D72; text-decoration: none; font-weight: bold;'>mathewi:</a></span>&nbsp;"10 Things You Didn’t Know About the New Twitter.com" http://dlvr.it/5GWbj&nbsp;
            <span><a target='_blank' href='http://twitter.com/mathewi/status/24535034257' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 20.02.53</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediatwit' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediatwit:</a></span>&nbsp;Kinda weird (but not surprising) that Twitter revamps Twitter.com but doesn't keep ads or search in mind. (Never mentioned.)&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediatwit/status/24519735152' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.43.44</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/ryancarson' style='color:#093D72; text-decoration: none; font-weight: bold;'>ryancarson:</a></span>&nbsp;New http://twitter.com re-design launches! Well done @stop + team :) Screenshot here: http://cot.ag/aFEKR8 #tv&nbsp;
            <span><a target='_blank' href='http://twitter.com/ryancarson/status/24557199997' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 03.09.34</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu' style='color:#093D72; text-decoration: none; font-weight: bold;'>jayrosen_nyu:</a></span>&nbsp;@webbmedia In reply to your question, I use the web interface at twitter.com and that is all I have ever used.&nbsp;
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu/status/24520992761' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.00.58</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/charlesarthur' style='color:#093D72; text-decoration: none; font-weight: bold;'>charlesarthur:</a></span>&nbsp;@MattPRD I don't use twitter.com - I use apps. Prefer 'em.&nbsp;
            <span><a target='_blank' href='http://twitter.com/charlesarthur/status/24575784092' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 07.43.37</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Scobleizer' style='color:#093D72; text-decoration: none; font-weight: bold;'>Scobleizer:</a></span>&nbsp;The new Twitter.com brings neat keyboard shortcuts. http://tnw.to/16nLN&nbsp;
            <span><a target='_blank' href='http://twitter.com/Scobleizer/status/24542409649' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 21.51.36</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/webbmedia' style='color:#093D72; text-decoration: none; font-weight: bold;'>webbmedia:</a></span>&nbsp;A lot of the new twitter.com looks like @TweetDeck to me.&nbsp;
            <span><a target='_blank' href='http://twitter.com/webbmedia/status/24520466599' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.53.51</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/charlesarthur' style='color:#093D72; text-decoration: none; font-weight: bold;'>charlesarthur:</a></span>&nbsp;*whispers* I liked the old twitter.com better. Apart from anything, it would load in my favourite browser (Camino).&nbsp;
            <span><a target='_blank' href='http://twitter.com/charlesarthur/status/24562456750' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 04.47.21</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/JimMacMillan' style='color:#093D72; text-decoration: none; font-weight: bold;'>JimMacMillan:</a></span>&nbsp;RT @webbmedia: A lot of the new twitter.com looks like @TweetDeck to me.&nbsp;
            <span><a target='_blank' href='http://twitter.com/JimMacMillan/status/24567044171' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 05.55.24</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;Good morning! First things first: Who has the new Twitter.com and what's your review? #NewTwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24572257044' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 07.02.11</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mathewi' style='color:#093D72; text-decoration: none; font-weight: bold;'>mathewi:</a></span>&nbsp;I still say @ev quoting 78% of users on twitter.com is misleading. Did he say what percentage of activity was there?&nbsp;
            <span><a target='_blank' href='http://twitter.com/mathewi/status/24521307002' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.05.07</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mathewi' style='color:#093D72; text-decoration: none; font-weight: bold;'>mathewi:</a></span>&nbsp;all these new Twitter.com features look great, but I still don't see myself going to the website over using an app like Tweetdeck&nbsp;
            <span><a target='_blank' href='http://twitter.com/mathewi/status/24518950929' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.32.49</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediatwit' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediatwit:</a></span>&nbsp;The New Twitter.com Is a "Consumption Environment". Translation: Twitter is a (Reluctant) Media Company [MediaMemo] http://bit.ly/cxyq35&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediatwit/status/24523606357' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.35.23</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/SocialMedia411' style='color:#093D72; text-decoration: none; font-weight: bold;'>SocialMedia411:</a></span>&nbsp;Everything You Need To Know About The New Twitter.com (SAI): http://bit.ly/9hwrMG&nbsp;
            <span><a target='_blank' href='http://twitter.com/SocialMedia411/status/24593369638' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 11.30.06</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/laughingsquid' style='color:#093D72; text-decoration: none; font-weight: bold;'>laughingsquid:</a></span>&nbsp;here's the @twitter video that @ev showed during his announcement of the new twitter.com http://bit.ly/aKz7Ra #NewTwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/laughingsquid/status/24519897790' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.45.59</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;Thanks everyone for following the Twitter live blog. More coverage on the new Twitter.com coming soon!&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24521201786' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.03.43</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mashable' style='color:#093D72; text-decoration: none; font-weight: bold;'>mashable:</a></span>&nbsp;Here Comes the New Twitter.com [PIC] - http://mash.to/2Eim9&nbsp;
            <span><a target='_blank' href='http://twitter.com/mashable/status/24518873082' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.31.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/sdkstl' style='color:#093D72; text-decoration: none; font-weight: bold;'>sdkstl:</a></span>&nbsp;Completely new Twitter.com starting to roll out in 30 min. Built off its own API. (new fail whale, too?) livestream  http://bit.ly/dc3ifB&nbsp;
            <span><a target='_blank' href='http://twitter.com/sdkstl/status/24519163742' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 16.35.45</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/kevinmarks' style='color:#093D72; text-decoration: none; font-weight: bold;'>kevinmarks:</a></span>&nbsp;@kevinmarks I said in my original post that 3rd-party client users are some of the most active. Twitter.com is by far the biggest client tho&nbsp;
            <span><a target='_blank' href='http://twitter.com/kevinmarks/status/24525182095' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.55.40</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/acarvin' style='color:#093D72; text-decoration: none; font-weight: bold;'>acarvin:</a></span>&nbsp;Good writeup on Twitter.com's new features. RT @briansolis: The New and Improved Twitter http://bit.ly/aYmjWD #newtwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/acarvin/status/24577839977' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 08.07.15</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/RWW' style='color:#093D72; text-decoration: none; font-weight: bold;'>RWW:</a></span>&nbsp;RWW doesn't have the new Twitter.com yet. Where is the love, @Twitter? #NewTwitter&nbsp;
            <span><a target='_blank' href='http://twitter.com/RWW/status/24521918391' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.13.18</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/ev' style='color:#093D72; text-decoration: none; font-weight: bold;'>ev:</a></span>&nbsp;@kevinmarks I said in my original post that 3rd-party client users are some of the most active. Twitter.com is by far the biggest client tho&nbsp;
            <span><a target='_blank' href='http://twitter.com/ev/status/24522553228' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 17.21.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Scobleizer' style='color:#093D72; text-decoration: none; font-weight: bold;'>Scobleizer:</a></span>&nbsp;good stuff from @lizgannes: 10 Things You Didn’t Know About the New Twitter.com http://bit.ly/bMHrbj”&nbsp;
            <span><a target='_blank' href='http://twitter.com/Scobleizer/status/24542332943' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 21.50.18</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Ftwitter.com&title=Twitter'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>

   </item>
   <item>
     <title><![CDATA[Inline Pictures And Video Are A Part Of What Twitter Is Announcing Tonight]]></title>
     <pubDate>Tue Sep 14 13:29:08 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284509320839]]></guid>
     <link><![CDATA[http://techcrunch.com/2010/09/14/twitter-inline-pictures-videos]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://techcrunch.com/2010/09/14/twitter-inline-pictures-videos' style='color: #009900; text-decoration: none;'>techcrunch.com</a>
    </div>
    <div>
       <div id='fooid#img'>
              <img src='http://tctechcrunch.files.wordpress.com/2010/09/b2.png?w=630&h=464' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
       </div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          The entire Internet seems to be guessing about what Twitter is going to unveil at their big event later this afternoon. Analytics, deeper Google&nbsp;integration, the killer business model? All fair guesses, but we&rsquo;ve just heard from a source at least one of the things being announced tonight: inline pictures and videos on twitter.com.
Such a feature would mimic&nbsp;functionality&nbsp;that some third-party Twitter clients have been doing for a while. The most notable of these may be Briz..            &nbsp; <a href='http://techcrunch.com/2010/09/14/twitter-inline-pictures-videos' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(7)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/charlesarthur' style='color:#093D72; text-decoration: none; font-weight: bold;'>charlesarthur:</a></span>&nbsp;@janinegibson http://techcrunch.com/2010/09/14/twitter-inline-pictures-videos/&nbsp;
            <span><a target='_blank' href='http://twitter.com/charlesarthur/status/24509127416' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 14.09.51</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/Scobleizer' style='color:#093D72; text-decoration: none; font-weight: bold;'>Scobleizer:</a></span>&nbsp;big twitter news to come in about 20 mins. @techcrunch says inline pics & video http://tcrn.ch/9OCOaW&nbsp;
            <span><a target='_blank' href='http://twitter.com/Scobleizer/status/24514731510' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.34.03</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/adnys' style='color:#093D72; text-decoration: none; font-weight: bold;'>adnys:</a></span>&nbsp;Inline Pictures & Video Part Of What Twitter Is Announcing (in about 15min) http://j.mp/bHxR2I « Bye Brizzly, was awsm using you. #sm&nbsp;
            <span><a target='_blank' href='http://twitter.com/adnys/status/24514751852' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.34.20</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/laughingsquid' style='color:#093D72; text-decoration: none; font-weight: bold;'>laughingsquid:</a></span>&nbsp;Twitter is will be announcing inline photos & video at today's special event http://tcrn.ch/ds1arx via @parislemon&nbsp;
            <span><a target='_blank' href='http://twitter.com/laughingsquid/status/24506566687' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.29.08</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/dannysullivan' style='color:#093D72; text-decoration: none; font-weight: bold;'>dannysullivan:</a></span>&nbsp;big twitter news to come in about 20 mins. @techcrunch says inline pics & video http://tcrn.ch/9OCOaW&nbsp;
            <span><a target='_blank' href='http://twitter.com/dannysullivan/status/24514027280' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.24.16</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/davewiner' style='color:#093D72; text-decoration: none; font-weight: bold;'>davewiner:</a></span>&nbsp;Inline Pictures And Video Are A Part Of What Twitter Is Announcing Tonight. http://r2.ly/69if&nbsp;
            <span><a target='_blank' href='http://twitter.com/davewiner/status/24508619302' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 14.01.52</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/SocialMedia411' style='color:#093D72; text-decoration: none; font-weight: bold;'>SocialMedia411:</a></span>&nbsp;Inline Pictures And Video Are A Part Of What Twitter Is Announcing Tonight (TechCrunch): http://tcrn.ch/addGZ3&nbsp;
            <span><a target='_blank' href='http://twitter.com/SocialMedia411/status/24508099872' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.53.31</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Ftechcrunch.com%2F2010%2F09%2F14%2Ftwitter-inline-pictures-videos&title=Inline+Pictures+And+Video+Are+A+Part+Of+What+Twitter+Is+Announcing+Tonight'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>

   <item>
     <title><![CDATA[Real IRA says it will target UK bankers | UK news | The Guardian]]></title>
     <pubDate>Tue Sep 14 13:20:11 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284509320838]]></guid>
     <link><![CDATA[http://www.guardian.co.uk/uk/2010/sep/14/real-ira-targets-banks-bankers]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.guardian.co.uk/uk/2010/sep/14/real-ira-targets-banks-bankers' style='color: #009900; text-decoration: none;'>guardian.co.uk</a>
    </div>
    <div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          Exclusive: Republican terror group vows to resume mainland attacks with banks and bankers now potential targets
Banks and bankers are now potential targets for the Real IRA, leaders of the dissident republican terror group have warned in an exclusive interview with the Guardian. Despite having only 100 activists they also said that targets in England remained a high priority.
In an attempt to tap into the intense hostility towards the banks on both sides of the Irish border they branded bankers..            &nbsp; <a href='http://www.guardian.co.uk/uk/2010/sep/14/real-ira-targets-banks-bankers' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(5)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/hayjane' style='color:#093D72; text-decoration: none; font-weight: bold;'>hayjane:</a></span>&nbsp;Scary stuff: Real IRA says it will target UK bankers http://gu.com/p/2jy3z/tw&nbsp;
            <span><a target='_blank' href='http://twitter.com/hayjane/status/24506126078' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.22.08</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/kmflett' style='color:#093D72; text-decoration: none; font-weight: bold;'>kmflett:</a></span>&nbsp;RT @Pam_Nash: Guardian: 'Real IRA says it will target UK bankers'  http://bit.ly/aPwWqo >US method -putting them on trial -seems preferable&nbsp;
            <span><a target='_blank' href='http://twitter.com/kmflett/status/24511323058' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 14.44.32</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/janinegibson' style='color:#093D72; text-decoration: none; font-weight: bold;'>janinegibson:</a></span>&nbsp;Real IRA bids for popular appeal by announcing it will target bankers http://bit.ly/byJZBz&nbsp;
            <span><a target='_blank' href='http://twitter.com/janinegibson/status/24506185418' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.23.06</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/iankatz1000' style='color:#093D72; text-decoration: none; font-weight: bold;'>iankatz1000:</a></span>&nbsp;Real IRA bids for popular appeal by announcing it will target bankers http://bit.ly/byJZBz&nbsp;
            <span><a target='_blank' href='http://twitter.com/iankatz1000/status/24506000283' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.20.11</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/paulcarvill' style='color:#093D72; text-decoration: none; font-weight: bold;'>paulcarvill:</a></span>&nbsp;talk about playing to the gallery RT @JonathanHaynes Real IRA says it will target UK bankers - interview with Guardian http://bit.ly/dxLF6l&nbsp;
            <span><a target='_blank' href='http://twitter.com/paulcarvill/status/24507845779' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.49.26</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/GNM_Press' style='color:#093D72; text-decoration: none; font-weight: bold;'>GNM_Press:</a></span>&nbsp;Real IRA says it will target UK bankers http://gu.com/p/2jy3z/tw&nbsp;
            <span><a target='_blank' href='http://twitter.com/GNM_Press/status/24506137072' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.22.19</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.guardian.co.uk%2Fuk%2F2010%2Fsep%2F14%2Freal-ira-targets-banks-bankers&title=Real+IRA+says+it+will+target+UK+bankers+%7C+UK+news+%7C+The+Guardian'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>

     <title><![CDATA[The Hamster Wheel : CJR]]></title>
     <pubDate>Tue Sep 14 09:37:38 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284496171180]]></guid>
     <link><![CDATA[http://www.cjr.org/cover_story/the_hamster_wheel.php?page=all]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.cjr.org/cover_story/the_hamster_wheel.php?page=all' style='color: #009900; text-decoration: none;'>cjr.org</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/kevglobal' style='color:#093D72; text-decoration: none; font-weight: bold;'>kevglobal:</a></span>&nbsp;Questioning journalism's "hamster wheel" and obsession with clicks in the Columbia Journalism Review. More is less. http://bit.ly/dnuitN&nbsp;
            <span><a target='_blank' href='http://twitter.com/kevglobal/status/24545971599' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 22.57.58</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(7)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/carr2n' style='color:#093D72; text-decoration: none; font-weight: bold;'>carr2n:</a></span>&nbsp;You just gotta read hamster wheel riff by @deanstarkman http://bit.ly/a2nDU1 Asks questions abt news orgs click-lust no one else has.&nbsp;
            <span><a target='_blank' href='http://twitter.com/carr2n/status/24512984170' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 15.09.36</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/romenesko' style='color:#093D72; text-decoration: none; font-weight: bold;'>romenesko:</a></span>&nbsp;Dean Starkman on the implications of the do-more-with-less meme (he calls it The Hamster Wheel). http://journ.us/95fAOs&nbsp;
            <span><a target='_blank' href='http://twitter.com/romenesko/status/24498541940' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.24.06</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/carr2n' style='color:#093D72; text-decoration: none; font-weight: bold;'>carr2n:</a></span>&nbsp;most underused words in the news business today:" let’s pass on that" http://bit.ly/a2nDU1 Dean Starkman on #HampsterWheelJournalism&nbsp;
            <span><a target='_blank' href='http://twitter.com/carr2n/status/24512219546' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 14.58.22</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/psmith' style='color:#093D72; text-decoration: none; font-weight: bold;'>psmith:</a></span>&nbsp;RT @Edgecliffe: Demand Media dubbed "the world’s leading hamster-powered content farm" http://bit.ly/a2nDU1 // some business-savvy hamsters&nbsp;
            <span><a target='_blank' href='http://twitter.com/psmith/status/24490829715' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 09.37.38</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/scottros' style='color:#093D72; text-decoration: none; font-weight: bold;'>scottros:</a></span>&nbsp;"news panic" hamster wheel described here http://is.gd/faAE5 is not a Web pathology but result of news orgs' dysfunctional *response* to Web&nbsp;
            <span><a target='_blank' href='http://twitter.com/scottros/status/24497933997' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.14.48</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu' style='color:#093D72; text-decoration: none; font-weight: bold;'>jayrosen_nyu:</a></span>&nbsp;"The most underused words in the news business today: let’s pass on that." http://jr.ly/4wgb Some smart reflections on churnalism in @CJR.&nbsp;
            <span><a target='_blank' href='http://twitter.com/jayrosen_nyu/status/24494738199' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.28.17</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/macloo' style='color:#093D72; text-decoration: none; font-weight: bold;'>macloo:</a></span>&nbsp;RT @journojos @mims: Can't think of a piece w/ broader relevance to journalists ... than Starkman's "The Hamster Wheel" http://bit.ly/9ZD4BF&nbsp;
            <span><a target='_blank' href='http://twitter.com/macloo/status/24590434635' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 10.46.11</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/KristineLowe' style='color:#093D72; text-decoration: none; font-weight: bold;'>KristineLowe:</a></span>&nbsp;Questioning journalism's "hamster wheel" and obsession with clicks in the Columbia Journalism Review. More is less. http://bit.ly/dnuitN&nbsp;
            <span><a target='_blank' href='http://twitter.com/KristineLowe/status/24546776242' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 23.14.13</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.cjr.org%2Fcover_story%2Fthe_hamster_wheel.php%3Fpage%3Dall&title=The+Hamster+Wheel+%3A+CJR'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Making Future Magic: light painting with the iPad  –  Blog –       BERG]]></title>

     <pubDate>Tue Sep 14 10:14:58 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284492127563]]></guid>
     <link><![CDATA[http://berglondon.com/blog/2010/09/14/magic-ipad-light-painting]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://berglondon.com/blog/2010/09/14/magic-ipad-light-painting' style='color: #009900; text-decoration: none;'>berglondon.com</a>
    </div>
    <div>
       <div id='fooid#img'>
              <img src='http://farm5.static.flickr.com/4131/4989817636_5868029964.jpg' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
       </div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          &ldquo;Making Future Magic&rdquo; is the goal of Dentsu London, the creative communications agency. We made this film with them to explore this statement.

We&rsquo;re working with Beeker Northam at Dentsu, using their strategy to explore how the media landscape is changing. From Beeker&rsquo;s correspondence with us during development:
&ldquo;&hellip;what might a magical version of the future of media look like?&rdquo;
and
&hellip;we [Dentsu] are interested in the future, but not so much in sc..            &nbsp; <a href='http://berglondon.com/blog/2010/09/14/magic-ipad-light-painting' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(0)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(8)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/rooreynolds' style='color:#093D72; text-decoration: none; font-weight: bold;'>rooreynolds:</a></span>&nbsp;RT @BERGLONDON: "Making Future Magic - a film of stop-motion iPad light painting" http://bit.ly/magicfilm <== !!!&nbsp;
            <span><a target='_blank' href='http://twitter.com/rooreynolds/status/24494187723' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.20.48</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/robinsloan' style='color:#093D72; text-decoration: none; font-weight: bold;'>robinsloan:</a></span>&nbsp;Oh, I like this a lot. @BERGLONDON conjures 3D ghost robots out of an iPad: http://t.co/HvhLOFK&nbsp;
            <span><a target='_blank' href='http://twitter.com/robinsloan/status/24494036584' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.18.46</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/bowbrick' style='color:#093D72; text-decoration: none; font-weight: bold;'>bowbrick:</a></span>&nbsp;Glorious ==> RT @meeware: RT @neb: there they go, extruding light into space again: http://bit.ly/bFI2XV&nbsp;
            <span><a target='_blank' href='http://twitter.com/bowbrick/status/24493750725' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.14.58</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/LDN' style='color:#093D72; text-decoration: none; font-weight: bold;'>LDN:</a></span>&nbsp;Fantastic - light painting with the iPad http://bit.ly/d8A6n9 (via @berglondon)&nbsp;
            <span><a target='_blank' href='http://twitter.com/LDN/status/24558928838' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 03.44.29</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mattb' style='color:#093D72; text-decoration: none; font-weight: bold;'>mattb:</a></span>&nbsp;bit jealous of the awesome disciplines and skills mix that BERG wield in their video http://bit.ly/b0kC0F (like you haven't seen it already)&nbsp;
            <span><a target='_blank' href='http://twitter.com/mattb/status/24551908816' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 01.09.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/brainpicker' style='color:#093D72; text-decoration: none; font-weight: bold;'>brainpicker:</a></span>&nbsp;Making Future Magic – remarkable light painting on the iPad, ace work by @BERGLONDON http://is.gd/faMX3&nbsp;
            <span><a target='_blank' href='http://twitter.com/brainpicker/status/24508515994' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 14.00.13</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/GreatDismal' style='color:#093D72; text-decoration: none; font-weight: bold;'>GreatDismal:</a></span>&nbsp;@GreatDismal - truly locative  "Making Future Magic - a film of stop-motion iPad light painting" http://bit.ly/magicfilm -via @rooreynolds&nbsp;
            <span><a target='_blank' href='http://twitter.com/GreatDismal/status/24498306187' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.20.26</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/bengoldacre' style='color:#093D72; text-decoration: none; font-weight: bold;'>bengoldacre:</a></span>&nbsp;3D light painting with the iPad: the film is fun, via http://waxy.org/links/ and... @waxpancake http://dlvr.it/5HmmH&nbsp;
            <span><a target='_blank' href='http://twitter.com/bengoldacre/status/24563903205' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 05.10.02</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fberglondon.com%2Fblog%2F2010%2F09%2F14%2Fmagic-ipad-light-painting&title=Making+Future+Magic%3A+light+painting+with+the+iPad++%26%238211%3B++Blog+%26%238211%3B+++++++BERG'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[Rewired State]]></title>
     <pubDate>Tue Sep 14 10:51:33 PDT 2010</pubDate>

     <guid isPermaLink='false'><![CDATA[user.14697612::1284492127558]]></guid>
     <link><![CDATA[http://rewiredstate.org/events/nhs-big-screens]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://rewiredstate.org/events/nhs-big-screens' style='color: #009900; text-decoration: none;'>rewiredstate.org</a>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(3)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;@adrianshort YAY!!! WOOT... glee... *sigh* - over christmas too :) wahay http://bit.ly/9TZoiz&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24496720097' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.56.47</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;@fidothe you want to play too? http://rewiredstate.org/events/nhs-big-screens&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24499915332' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.45.14</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;@felix_cohen consider yourself invited :) http://rewiredstate.org/events/nhs-big-screens&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24499839002' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.44.05</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;@frankieroberto hunting for people, yr name came up http://rewiredstate.org/events/nhs-big-screens&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24500016875' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.46.47</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;OK, drumrolll trrrrrrrrrrrrrrrrrrrrrrrrr here is the latest Rewired State developer challenge http://rewiredstate.org/events/nhs-big-screens&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24496367847' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.51.33</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/openplatform' style='color:#093D72; text-decoration: none; font-weight: bold;'>openplatform:</a></span>&nbsp;OK, drumrolll trrrrrrrrrrrrrrrrrrrrrrrrr here is the latest Rewired State developer challenge http://rewiredstate.org/events/nhs-big-screens&nbsp;
            <span><a target='_blank' href='http://twitter.com/openplatform/status/24500877513' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 12.00.09</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;Play! == RT @hubmum: OK, here is the latest Rewired State developer challenge http://rewiredstate.org/events/nhs-big-screens&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24550100980' style='color:#666666; font-size:10px; text-decoration: none;'>15.09.2010 00.27.20</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/revdancatt' style='color:#093D72; text-decoration: none; font-weight: bold;'>revdancatt:</a></span>&nbsp;Play! == RT @hubmum: OK, here is the latest Rewired State developer challenge http://rewiredstate.org/events/nhs-big-screens&nbsp;
            <span><a target='_blank' href='http://twitter.com/revdancatt/status/24496687250' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 10.56.18</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;@nickludlam I am told that you might love this http://rewiredstate.org/events/nhs-big-screens :) please?!&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24499947172' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.45.42</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/hubmum' style='color:#093D72; text-decoration: none; font-weight: bold;'>hubmum:</a></span>&nbsp;@memotv can't find email contact for you either, r u interested? http://rewiredstate.org/events/nhs-big-screens :) will be ace&nbsp;
            <span><a target='_blank' href='http://twitter.com/hubmum/status/24500092242' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 11.47.57</a></span>
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
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Frewiredstate.org%2Fevents%2Fnhs-big-screens&title=Rewired+State'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
   <item>
     <title><![CDATA[The Future of Social Media in Journalism]]></title>
     <pubDate>Tue Sep 14 08:26:13 PDT 2010</pubDate>
     <guid isPermaLink='false'><![CDATA[user.14697612::1284418596735]]></guid>

     <link><![CDATA[http://mashable.com/2010/09/13/future-social-media-journalism]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://mashable.com/2010/09/13/future-social-media-journalism' style='color: #009900; text-decoration: none;'>mashable.com</a>
    </div>
    <div>
       <div id='fooid#img'>
              <img src='http://cdn.mashable.com/wp-content/uploads/2009/09/news.jpg' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
       </div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          This series is supported by Gist. Gist provides a full view of the contacts in your professional network by creating a rich business profile for each one that includes the most news, status updates, and work details. See how it works here.
 The future of social media in journalism will see the death of &ldquo;social media.&rdquo; That is, all media as we know it today will become social, and feature a social component to one extent or another. After all, much of the web experience, particularly..            &nbsp; <a href='http://mashable.com/2010/09/13/future-social-media-journalism' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(1)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/saralinfoot' style='color:#093D72; text-decoration: none; font-weight: bold;'>saralinfoot:</a></span>&nbsp;The Future of Social Media in Journalism http://t.co/DhrIQaz via @mashsocialmedia @mashable&nbsp;
            <span><a target='_blank' href='http://twitter.com/saralinfoot/status/24505310367' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.09.17</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/scottros' style='color:#093D72; text-decoration: none; font-weight: bold;'>scottros:</a></span>&nbsp;“Journalists need to give up their self-adoration as the authority on topics they write about" sez @michelemclellan http://mash.to/2DCxo&nbsp;
            <span><a target='_blank' href='http://twitter.com/scottros/status/24484915762' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 08.26.13</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/jackschofield' style='color:#093D72; text-decoration: none; font-weight: bold;'>jackschofield:</a></span>&nbsp;The Future of Social Media in Journalism, at @mashable - http://bit.ly/a6i2va&nbsp;
            <span><a target='_blank' href='http://twitter.com/jackschofield/status/24489691195' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 09.24.08</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/mediatwit' style='color:#093D72; text-decoration: none; font-weight: bold;'>mediatwit:</a></span>&nbsp;A great, meaty post from @lavrusik: The Future of Social Media in Journalism: http://ow.ly/2DDRI via @mashable&nbsp;
            <span><a target='_blank' href='http://twitter.com/mediatwit/status/24505965627' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 13.19.39</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/JimMacMillan' style='color:#093D72; text-decoration: none; font-weight: bold;'>JimMacMillan:</a></span>&nbsp;Nice roundup: The Future of Social Media in Journalism http://bit.ly/d6VHfe&nbsp;
            <span><a target='_blank' href='http://twitter.com/JimMacMillan/status/24529815554' style='color:#666666; font-size:10px; text-decoration: none;'>14.09.2010 18.54.37</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fmashable.com%2F2010%2F09%2F13%2Ffuture-social-media-journalism&title=The+Future+of+Social+Media+in+Journalism'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>
 </channel>
</rss> }
}