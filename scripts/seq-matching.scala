object Chars {
  def apply(c: Char*) = c.mkString
  def unapplySeq(s: String) = Some(s.toSeq)
}

val space0 = "[\\s\\u00A0]*"
val space = "[\\s\\u00A0]+"
val timestamp = """[\d\.]+\s+[\d\.]+"""
val username = "(\\S+)"
val message = "(\\S.*\\S)"

val tweet = (space + username + ":" + space + message + space + timestamp + space).r

val tweet(nu, le) = " \u00A0  sa: Hello world! 23.45.67 45.56.67  "
