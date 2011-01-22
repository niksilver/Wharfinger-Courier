import xml.{Text, Elem, Node}

val tweets_html =
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


  /**To extract a username look for an A HREF to http://twitter.com/username.
   */
  def username(div: Node): String = ((div \ "span")(0) \ "a" \ "@href" text) split '/' last
def username2(div: Node): String = ":(.|\\s)*".r replaceFirstIn (div.text.trim, "")

  /**To extract a tweet take just the text, trim it, and drop
   * any NBSP (char 160s) from the start.
   */
  def tweet(div: Node): String =  {
    ((div.child) filterNot (_.isInstanceOf[Elem]) mkString).trim.dropWhile(_ == '\u00A0')
  }
def removeUsername(txt: String) = "^[^:]*:".r replaceFirstIn (txt, "")
def removeTimestamp(txt: String) = """(\d|\.|\s)*$""".r replaceFirstIn (txt , "")
def tweet2(div: Node): String =  {
  (removeUsername(removeTimestamp(div.text))).trim
}

  /** A mapping from Twitter usernames to tweets. */
  //val tweets = for (div <- tweets_html) yield ( username(div) -> tweet(div) )
  //val tweets = for (div <- tweets_html.child) { println("*** " + div.toString) }
"------------"
"""(\d|\.|\s)*$""".r replaceFirstIn("34.x2.43.2 \n\r ", "*")
val div: Node = (tweets_html \ "div")(0)
"tweet: " + tweet(div) + "#\n"
"tweet2: " + tweet2(div) + "#\n"
"removeTimestamp: " + removeTimestamp(div.text) + "#\n"
"removeUsername: " + removeUsername(div.text) + "#\n"
"username2: " + username2(div) + "#\n"
"*" + div.text + "*\n"

