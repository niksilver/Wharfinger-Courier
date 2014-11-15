import sbtappengine.Plugin.{AppengineKeys => gae}

name := "Wharfinger Courier"

version := "0.2"

scalaVersion := "2.11.2"

// Google App Engine plugin from https://github.com/sbt/sbt-appengine

unmanagedJars in Compile <++= baseDirectory map { base =>
	val gaeLib = file("C:/Program Files/Google/appengine-java-sdk-1.8.1/lib")
	val jarDirs = (gaeLib / "shared") +++
		(gaeLib / "user" ) +++
		(gaeLib / "user" / "orm") +++
		(base / "lib" / "build") +++
		(base / "lib" / "runtime")
	val jars = jarDirs ** "*.jar"
	jars.classpath
}

scalacOptions ++=
	Seq("-deprecation", "-feature",
		"-language:implicitConversions",
		"-language:postfixOps")

libraryDependencies ++= Seq(
  // Use the Scala XML module (which came out of the core in 2.11)
  "org.scala-lang.modules" %% "scala-xml" % "1.0.1",
  // "net.databinder" %% "unfiltered-filter" % "0.6.4",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "7.6.8.v20121106" % "container"
)

seq(appengineSettings: _*)

// How to add a task, from http://www.scala-sbt.org/release/docs/Detailed-Topics/Tasks.html

//TaskKey[Unit]("hello") := {
//  println("hello worldy!")
//  println("Classpath is " + java.lang.System.getProperty("java.class.path"))
//  org.datanucleus.enhancer.DataNucleusEnhancer.main(Array("-v", "-cp", "C:/Program Files/Google/appengine-java-sdk-1.6.4/lib/tools/orm/asm-3.1.jar", "-dir", "target/scala-2.9.2/classes", "org/pigsaw/wharfinger/Article.class"))
//}
