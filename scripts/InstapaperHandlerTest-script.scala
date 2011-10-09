// :load set-classpath.scala first

val handler = new InstapaperHandler("http://online.wsj.com/article/SB10001424052748703977004575393173432219064.html")
val Some(content) = handler.getContentDiv()
content
