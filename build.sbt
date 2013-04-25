name := "Wharfinger Courier"

version := "0.2"

unmanagedJars in Compile <++= baseDirectory map { base =>
	val gaeLib = file("C:/Program Files/Google/appengine-java-sdk-1.6.4/lib")
	val jarDirs = (gaeLib / "shared") +++
		(gaeLib / "user" ) +++
		(gaeLib / "user" / "orm") +++
		(base / "lib" / "build") +++
		(base / "lib" / "runtime")
	val jars = jarDirs ** "*.jar"
	jars.classpath
}

scalacOptions += "-deprecation"
