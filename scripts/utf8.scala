val str = """<item>
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
			<span><a  href='http://twitter.com/stephen_abbott' style='color:#093D72; text-decoration: none; font-weight: bold;'>stephen_abbott:</a></span>&nbsp;@<a class="tweet-url username" href="http://twitter.com/Sarah_Crown" rel="nofollow">Sarah_Crown</a> Seen this? RT @<a class="tweet-url username" href="http://twitter.com/martinhearn" rel="nofollow">martinhearn</a>: Bookseller launches site to <a href="http://twitter.com/search?q=%23savelibraries" title="#savelibraries" class="tweet-url hashtag" rel="nofollow">#savelibraries</a> <a href="http://bit.ly/gBL7RR" rel="nofollow">http://bit.ly/gBL7RR</a>&rdquo; (via @<a class="tweet-url username" href="http://twitter.com/Fight4libraries" rel="nofollow">Fight4libraries</a>)&nbsp;
			<span><a  href='http://twitter.com/stephen_abbott/status/27043884815093760' style='color:#666666; font-size:10px; text-decoration: none;'>17.01.2011 08.45.39</a></span>
		</div>
		<div>
			<span><a  href='http://twitter.com/stephen_abbott' style='color:#093D72; text-decoration: none; font-weight: bold;'>stephen_abbott:</a></span>&nbsp;@<a class="tweet-url username" href="http://twitter.com/JosieLong" rel="nofollow">JosieLong</a> Seen this? RT @<a class="tweet-url username" href="http://twitter.com/martinhearn" rel="nofollow">martinhearn</a>: Bookseller launches site to <a href="http://twitter.com/search?q=%23savelibraries" title="#savelibraries" class="tweet-url hashtag" rel="nofollow">#savelibraries</a> <a href="http://bit.ly/gBL7RR" rel="nofollow">http://bit.ly/gBL7RR</a>&rdquo; (via @<a class="tweet-url username" href="http://twitter.com/Fight4libraries" rel="nofollow">Fight4libraries</a>)&nbsp;
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
		</item>"""

print(str takeWhile { c:Char => c <127 })