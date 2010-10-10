object Chars {
  def apply(c: Char*) = c.mkString
  def unapplySeq(s: String) = Some(s.toSeq)
}
