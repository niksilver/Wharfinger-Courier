name := "Wharfinger Courier"

version := "0.2"

scalaVersion := "2.11.2"

scalacOptions ++=
	Seq("-deprecation", "-feature",
		"-language:implicitConversions",
		"-language:postfixOps")

unmanagedJars in Compile <++= baseDirectory map { base =>
	val jarDirs =
		(base / "lib" / "build") +++
		(base / "lib" / "runtime")
	val jars = jarDirs ** "*.jar"
	jars.classpath
}

libraryDependencies ++= Seq(
  // Use the Scala XML module (which came out of the core in 2.11)
  "org.scala-lang.modules" %% "scala-xml" % "1.0.1",
  "javax.servlet" % "servlet-api" % "2.5" % "provided"
)

// For Scalatest for Scala 2.11
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

/////////////////////////////////////////////////////

// These two lines for sbt-appengine with sbt 0.13
// From https://github.com/sbt/sbt-appengine

libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"

appengineSettings

/////////////////////////////////////////////////////
