import scala.util.parsing.combinator._

object Breaks extends JavaTokenParsers {
  def break: Parser[Any] = """b\b""".r ^^ (x => "BREAK")
  def text: Parser[Any] = """[^b]\w*""".r | """b\w+""".r
  def sym: Parser[Any] = break | text
  def seq: Parser[Any] = rep(sym)
}

Breaks.parseAll(Breaks.seq, "Once upon a time b there was a b baby girl called bb Golidlocks")