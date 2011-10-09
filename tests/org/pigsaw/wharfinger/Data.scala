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

  def currybet_delicious_rss = { """<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom"
       xmlns:content="http://purl.org/rss/1.0/modules/content/"
       xmlns:wfw="http://wellformedweb.org/CommentAPI/"
       xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
       xmlns:dc="http://purl.org/dc/elements/1.1/"
       xmlns:cc="http://web.resource.org/cc/">
      <channel>
          <title>Delicious/currybet</title>
          <link>http://www.delicious.com/currybet</link>
          <description>links posted by currybet</description>
          <atom:link rel="self" type="application/rss+xml" href="http://delicious.com/v2/rss/currybet"/>

          <item>
              <title>- The Obvious? - Publicness and Real Love</title>
              <pubDate>Mon, 03 Oct 2011 18:29:38 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/cbf3e70c15b23064df303f533f44f4e3#currybet</guid>
              <link>http://euansemple.squarespace.com/theobvious/2011/10/3/publicness-and-real-love.html</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/cbf3e70c15b23064df303f533f44f4e3</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/cbf3e70c15b23064df303f533f44f4e3</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      &#34;What are we so afraid of? We hide from others but we also hide from ourselves. We should get over worrying about who sees our indiscretions on Facebook or who gets to know what we are thinking through our blogs. We should get over our squeamishness about exposing our feelings and stop disapproving of those who do. Who knows what we might learn about ourselves and the world around us ...&#34;

              </description>
              <category domain="http://www.delicious.com/currybet/">sharing</category>
              <category domain="http://www.delicious.com/currybet/">socialmedia</category>
              <category domain="http://www.delicious.com/currybet/">euansemple</category>

          </item>

          <item>
              <title>Decoding Our Twitter Chatter - WSJ.com</title>
              <pubDate>Mon, 03 Oct 2011 18:27:03 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/9e8cec725486dd0624118b172fc9f588#currybet</guid>
              <link>http://online.wsj.com/article/SB10001424052970204138204576598942105167646.html</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/9e8cec725486dd0624118b172fc9f588</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/9e8cec725486dd0624118b172fc9f588</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      &#34;When Virginia&#39;s magnitude 5.8 earthquake hit last August, the first Twitter reports sent from people at the epicenter began almost instantly at 1:51 p.m.\u2014and reached New York about 40 seconds ahead of the quake&#39;s first shock waves, according to calculations by the social media company SocialFlow.&#34;

              </description>
              <category domain="http://www.delicious.com/currybet/">wsj</category>
              <category domain="http://www.delicious.com/currybet/">twitter</category>
              <category domain="http://www.delicious.com/currybet/">socialmedia</category>

          </item>

          <item>
              <title>Skewed questioning \u00AB mmitII</title>
              <pubDate>Mon, 03 Oct 2011 18:26:06 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/497f41212c82ddc524694743e4e4b940#currybet</guid>
              <link>https://mmitii.wordpress.com/2011/10/03/skewed-questioning/</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/497f41212c82ddc524694743e4e4b940</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/497f41212c82ddc524694743e4e4b940</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      &#34;Online survey tools, email and databases have effectively reduced the administrative cost of social surveys to nothing, and the increasing use of inter- and intra-organisational metrics about customer satisfaction that can only be calculated by surveying have likely drowned the practice.&#34;

              </description>
              <category domain="http://www.delicious.com/currybet/">surveys</category>
              <category domain="http://www.delicious.com/currybet/">research</category>
              <category domain="http://www.delicious.com/currybet/">socialmedia</category>

          </item>

          <item>
              <title>The short history of Mac malware: 1982 &#2013; 2011 | Naked Security</title>
              <pubDate>Mon, 03 Oct 2011 12:23:37 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/99186556331ca121ce6171fb6480e5ca#currybet</guid>
              <link>http://nakedsecurity.sophos.com/2011/10/03/mac-malware-history/?utm_source=twitter&amp;utm_medium=gcluley&amp;utm_campaign=naked%2Bsecurity</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/99186556331ca121ce6171fb6480e5ca</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/99186556331ca121ce6171fb6480e5ca</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      &#34;Before we begin, it&#39;s worth recognising that malware on the Mac is a subject which raises strong emotions. There are some who believe that the problem is overhyped (or even non-existent!) and others who believe that the malware problem on Macs is underestimated by the Apple-loving community.&#34;

              </description>
              <category domain="http://www.delicious.com/currybet/">mac</category>
              <category domain="http://www.delicious.com/currybet/">malware</category>
              <category domain="http://www.delicious.com/currybet/">apple</category>

          </item>

          <item>
              <title>What\u2019s different about mobile UX design? | @dmitryn/blog</title>
              <pubDate>Mon, 03 Oct 2011 08:38:32 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/519d5c257ed93c579255f7e0644fc60f#currybet</guid>
              <link>http://www.dmitryn.com/2011/08/11/whats-different-about-mobile-ux-design/</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/519d5c257ed93c579255f7e0644fc60f</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/519d5c257ed93c579255f7e0644fc60f</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      Amongst other things, makes a great point that \u201Dperpetual beta\u201D does not work for native mobile apps, because of the friction involved in downloading them.

              </description>
              <category domain="http://www.delicious.com/currybet/">mobile</category>
              <category domain="http://www.delicious.com/currybet/">ux</category>

          </item>

          <item>
              <title>cdixon.org \u2013 chris dixon&#39;s blog / Some lessons learned</title>
              <pubDate>Mon, 03 Oct 2011 08:37:03 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/04ea5111931aa80ee0304ae6033be268#currybet</guid>
              <link>http://cdixon.org/2011/09/28/some-lessons-learned/</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/04ea5111931aa80ee0304ae6033be268</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/04ea5111931aa80ee0304ae6033be268</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      \u201CPredicting the future of the Internet is easy: anything it hasn\u2019t yet dramatically transformed, it will.\u201D - also includes a great point about \u201Cthe next big thing\u201D always initially being dismissed as a toy.

              </description>
              <category domain="http://www.delicious.com/currybet/">advice</category>
              <category domain="http://www.delicious.com/currybet/">startups</category>
              <category domain="http://www.delicious.com/currybet/">internet</category>

          </item>

          <item>
              <title>Sites we love \u00AB The Typekit Blog</title>
              <pubDate>Mon, 03 Oct 2011 08:35:24 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/738fa3845a1d2218ee830b3507ca606a#currybet</guid>
              <link>http://blog.typekit.com/2011/09/30/sites-we-love/</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/738fa3845a1d2218ee830b3507ca606a</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/738fa3845a1d2218ee830b3507ca606a</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      Typekit showing off some nice type-y stuff, including NYTimes and the New Yorker.

              </description>
              <category domain="http://www.delicious.com/currybet/">typekit</category>
              <category domain="http://www.delicious.com/currybet/">typography</category>
              <category domain="http://www.delicious.com/currybet/">webfonts</category>

          </item>

          <item>
              <title>The Fine Balance Between Personal and Professional \u00ab Sysomos Blog</title>
              <pubDate>Mon, 03 Oct 2011 08:34:15 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/22d08757d0be010b774fe357b602fbda#currybet</guid>
              <link>http://blog.sysomos.com/2011/09/28/the-balancing-act-between-personal-and-professional/</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/22d08757d0be010b774fe357b602fbda</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/22d08757d0be010b774fe357b602fbda</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      Making public personalities of your backroom staff can be risky for a business.

              </description>
              <category domain="http://www.delicious.com/currybet/">community</category>
              <category domain="http://www.delicious.com/currybet/">communitymanagement</category>
              <category domain="http://www.delicious.com/currybet/">socialmedia</category>

          </item>

          <item>
              <title>Coming Soon - n0tice</title>
              <pubDate>Mon, 03 Oct 2011 08:32:12 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/ad0de5fe299b0f69e85005263c2d7b94#currybet</guid>
              <link>http://n0tice.com/</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/ad0de5fe299b0f69e85005263c2d7b94</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/ad0de5fe299b0f69e85005263c2d7b94</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      Sign up to join the beta trial of an interesting web community noticeboard idea.

              </description>
              <category domain="http://www.delicious.com/currybet/">n0tice</category>
              <category domain="http://www.delicious.com/currybet/">community</category>
              <category domain="http://www.delicious.com/currybet/">local</category>
              <category domain="http://www.delicious.com/currybet/">hyperlocal</category>

          </item>

          <item>
              <title>Five News Sites and Apps Named World\u2019s Best Designed \u2013 The Society for News Design \u2013 SND</title>
              <pubDate>Mon, 03 Oct 2011 08:29:31 +0000</pubDate>
              <guid isPermaLink="false">http://www.delicious.com/url/2bcadf76ddc48a6bc221c6262ff7a003#currybet</guid>
              <link>http://www.snd.org/2011/10/worlds-best-designed/</link>
              <dc:creator><![CDATA[currybet]]></dc:creator>
              <comments>http://www.delicious.com/url/2bcadf76ddc48a6bc221c6262ff7a003</comments>
              <wfw:commentRss>http://feeds.delicious.com/v2/rss/url/2bcadf76ddc48a6bc221c6262ff7a003</wfw:commentRss>
              <source url="http://feeds.delicious.com/v2/rss/currybet">currybet's links</source>
              <description>

                      Guardian iPhone app wins best designed mobile app from the Society of News Design. Well done John-Henry, Andy and Mr J. Moore.

              </description>
              <category domain="http://www.delicious.com/currybet/">guardian</category>
              <category domain="http://www.delicious.com/currybet/">iphone</category>
              <category domain="http://www.delicious.com/currybet/">awards</category>
              <category domain="http://www.delicious.com/currybet/">snd</category>
              <category domain="http://www.delicious.com/currybet/">design</category>

          </item>

      </channel>

  </rss>""" }

}