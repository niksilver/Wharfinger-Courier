import java.io.InputStream
import java.nio.charset.Charset
import scala.xml._

val xml = <li>
  <h3>
    <a href="/url" id="an2">
      <b>EC2 Java</b> Ecommerce
    </a>
  </h3>
  <b>Java</b> Shopping Cart for <b>EC2</b>/S3 Source Code, Clustered, Fast<br/>
  <cite>Avetti.com</cite></li>


def recur(n: Node, trans: (Node)=>Node): Node = {
  n match {
    case e: Elem => {
      e.copy(e.prefix, e.label+"x", e.attributes, e.scope, e.child map (n => recur(n, trans)))
    }
    case _ => {
      trans(n)
    }
  }
}
def trans(n: Node): Node = n match {
  case t: Text => new Text("*" + t.text + "*")
  case _ => n
}
def time(name: String)(code: =>Unit) {
  val start = System.currentTimeMillis
  code
  val end = System.currentTimeMillis
  println(name + ": Duration = " + (end-start) + "ms")
}
var large_xml: Elem = _
var large_xml_trans: Node = _
time("Loading XML") {
  large_xml = XML.load("http://www.w3.org/TR/2001/REC-xsl-20011015/xslspec.xml")
}
println("large_xml length is " + (large_xml.toString.length)/1024 + "kB")
time("Transforming large XML") {
  large_xml_trans = recur(large_xml, trans)
}
println(large_xml_trans.toString.take(200) + "...")