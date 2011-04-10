class Prefix(val p: String)

def prettyPrint(msg: String)(implicit pre: Prefix) { println(pre.p + msg) }

class Clz {
  def Output(pre: Prefix) {
    implicit val _ = pre
    prettyPrint("This is the mssage")
    println(`_`)
  }
}

val c = new Clz
val prefix = new Prefix("--> ")
c.Output(prefix)
