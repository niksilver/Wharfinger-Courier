import xml.{Text, Elem, Node}

val tweets_html = <div>
            <span><a style="color:#093D72; text-decoration: none; font-weight: bold;" href="http://twitter.com/JR0cket" target="_blank">JR0cket:</a></span> RT @david_colquhoun The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response 
            <span><a style="color:#666666; font-size:10px; text-decoration: none;" href="http://twitter.com/JR0cket/status/25036750861" target="_blank">20.09.2010 08.54.00</a></span>
         </div><div>
            <span><a style="color:#093D72; text-decoration: none; font-weight: bold;" href="http://twitter.com/paulbradshaw" target="_blank">paulbradshaw:</a></span> RT @david_colquhoun: BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response 
            <span><a style="color:#666666; font-size:10px; text-decoration: none;" href="http://twitter.com/paulbradshaw/status/24950761443" target="_blank">19.09.2010 10.02.53</a></span>
         </div><div>
            <span><a style="color:#093D72; text-decoration: none; font-weight: bold;" href="http://twitter.com/markrock" target="_blank">markrock:</a></span> RT @david_colquhoun: The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response 
            <span><a style="color:#666666; font-size:10px; text-decoration: none;" href="http://twitter.com/markrock/status/24956063712" target="_blank">19.09.2010 11.19.50</a></span>
         </div><div>
            <span><a style="color:#093D72; text-decoration: none; font-weight: bold;" href="http://twitter.com/willperrin" target="_blank">willperrin:</a></span> RT @paulbradshaw: RT @david_colquhoun: BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT 
            <span><a style="color:#666666; font-size:10px; text-decoration: none;" href="http://twitter.com/willperrin/status/24957184396" target="_blank">19.09.2010 11.37.02</a></span>
         </div><div>
            <span><a style="color:#093D72; text-decoration: none; font-weight: bold;" href="http://twitter.com/billt" target="_blank">billt:</a></span> RT @david_colquhoun The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response 
            <span><a style="color:#666666; font-size:10px; text-decoration: none;" href="http://twitter.com/billt/status/25032470016" target="_blank">20.09.2010 08.02.33</a></span>
         </div><div>
            <span><a style="color:#093D72; text-decoration: none; font-weight: bold;" href="http://twitter.com/ruskin147" target="_blank">ruskin147:</a></span> &quot;@david_colquhoun: The BBC Trust wants your opinion on BBC science reporting. http://bit.ly/dyMbaH Please RT to get big response&quot; 
            <span><a style="color:#666666; font-size:10px; text-decoration: none;" href="http://twitter.com/ruskin147/status/24952905252" target="_blank">19.09.2010 10.33.01</a></span>
         </div>
val out = for (tweet <- tweets_html) yield
  { ((tweet \ "span")(0) \ "a" \ "@href" text) split '/' last }
for (o <- out) { println("**" + o + "**")}

def transform(n: Node, fn: (Node)=>Node): Node = {
      n match {
        case e: Elem => e.copy(e.prefix, e.label,
          e.attributes, e.scope, e.child map (n => transform(n, fn)))
        case _ => {
          fn(n)
        }
      }
    }

def removeAs(n: Node) = n match {
  case <a/> => new Text("AAA")
  case _ => n
}

val out2 = for (tweet <- tweets_html) yield
  { (tweet.child) filterNot (_.isInstanceOf[Elem]) mkString }
