val str = """<item>
     <title><![CDATA[Science reporting on the BBC. Your chance to have a say.]]></title>
     <pubDate>Sun Sep 19 10:02:53 PDT 2010</pubDate>

     <guid isPermaLink='false'><![CDATA[user.14697612::1285003811106]]></guid>
     <link><![CDATA[http://www.dcscience.net/?p=3486]]></link>
     <description><![CDATA[    <div style='margin: 0 0 6px 0;'>
        <a target='_blank' href='http://www.dcscience.net/?p=3486' style='color: #009900; text-decoration: none;'>dcscience.net</a>
    </div>
    <div>
       <div id='fooid#img'>
              <img src='bbc-science.jpg' style='margin: 6px 6px 6px 0; max-width: 200px; width: expression((this.width > 200) && (this.width >= this.height) ? 200: true); max-height: 200px; height: expression((this.height > 200) && (this.height >= this.width) ? 200: true); border: none;'/>
       </div>
       <div id='fooid#snippet' style='margin: 6px 0 0 0;'>
          Steve Jones, UCL&rsquo;s star geneticist, has been commissioned by the BBC Trust to write a report on the impartiality of science journalism on the BBC.&nbsp; It covers both TV and radio, and all channels. Current programmes can be found by the BBC Science home page.

It is not uncommon for bloggers to be critical of science reporting in the mainstream media. Now is our chance to do something constructive about it. &nbsp; &nbsp; If you have opinions about this, please leave them in the comment..            &nbsp; <a href='http://www.dcscience.net/?p=3486' style='color: #093D72; font-style: italic; text-decoration: none;'>show all text</a>
       </div>
    </div>
    <div style='margin: 3px 0pt 0pt;'>
       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends:</strong>
         &nbsp;<span>(2)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/JR0cket' style='color:#093D72; text-decoration: none; font-weight: bold;'>JR0cket:</a></span>&nbsp;RT @david_colquhoun The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response&nbsp;
            <span><a target='_blank' href='http://twitter.com/JR0cket/status/25036750861' style='color:#666666; font-size:10px; text-decoration: none;'>20.09.2010 08.54.00</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/paulbradshaw' style='color:#093D72; text-decoration: none; font-weight: bold;'>paulbradshaw:</a></span>&nbsp;RT @david_colquhoun: BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response&nbsp;
            <span><a target='_blank' href='http://twitter.com/paulbradshaw/status/24950761443' style='color:#666666; font-size:10px; text-decoration: none;'>19.09.2010 10.02.53</a></span>
         </div>
       </div>

       <div style='margin: 3px 0pt 0pt;'>
         <strong>posted by friends of friends:</strong>
         &nbsp;<span>(4)</span>
       </div>
       <div style='margin: 0 0 0 15px;'>
         <div>
            <span><a target='_blank' href='http://twitter.com/markrock' style='color:#093D72; text-decoration: none; font-weight: bold;'>markrock:</a></span>&nbsp;RT @david_colquhoun: The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response&nbsp;
            <span><a target='_blank' href='http://twitter.com/markrock/status/24956063712' style='color:#666666; font-size:10px; text-decoration: none;'>19.09.2010 11.19.50</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/willperrin' style='color:#093D72; text-decoration: none; font-weight: bold;'>willperrin:</a></span>&nbsp;RT @paulbradshaw: RT @david_colquhoun: BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT&nbsp;
            <span><a target='_blank' href='http://twitter.com/willperrin/status/24957184396' style='color:#666666; font-size:10px; text-decoration: none;'>19.09.2010 11.37.02</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/billt' style='color:#093D72; text-decoration: none; font-weight: bold;'>billt:</a></span>&nbsp;RT @david_colquhoun The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response&nbsp;
            <span><a target='_blank' href='http://twitter.com/billt/status/25032470016' style='color:#666666; font-size:10px; text-decoration: none;'>20.09.2010 08.02.33</a></span>
         </div>
         <div>
            <span><a target='_blank' href='http://twitter.com/ruskin147' style='color:#093D72; text-decoration: none; font-weight: bold;'>ruskin147:</a></span>&nbsp;"@david_colquhoun: The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response"&nbsp;
            <span><a target='_blank' href='http://twitter.com/ruskin147/status/24952905252' style='color:#666666; font-size:10px; text-decoration: none;'>19.09.2010 10.33.01</a></span>
         </div>
       </div>

    </div>
    <div style='margin: 6px 0 0 0;'>
         <a target='_blank' href='/retweet.jsp?url=http%3A%2F%2Fwww.dcscience.net%2F%3Fp%3D3486&title=Science+reporting+on+the+BBC.+Your+chance+to+have+a+say.'>
             <span style='color:#093D72; text-decoration: none; font-weight: bold;'> retweet </span>
         </a>
    </div>
]]></description>
   </item>"""

print(str takeWhile { c:Char => c <127 })