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
  // "org.eclipse.jetty" % "jetty-webapp" % "7.6.8.v20121106" % "container"
)

/////////////////////////////////////////////////////

// These two lines for sbt-appengine with sbt 0.13
// From https://github.com/sbt/sbt-appengine

libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"

appengineSettings

/////////////////////////////////////////////////////
