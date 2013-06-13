// Code to add the sbt-appengine plugin

addSbtPlugin("com.eed3si9n" % "sbt-appengine" % "0.4.1")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.cc" /*,
  Resolver.url("sbt-plugin-releases",
  url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))
  (Resolver.ivyStylePatterns) */
)

// How to allow JDO enhancement, from
// http://stackoverflow.com/questions/9837191/sbt-task-classpath
// and
// http://www.scala-sbt.org/release/docs/Detailed-Topics/Library-Management.html
// and
// http://www.datanucleus.org/products/accessplatform/jdo/enhancer.html#manual
//
// This is the same version of the enhancer as comes with the version
// of GAE installed, but is downloaded from Maven

libraryDependencies ++= Seq(
  "asm" % "asm" % "3.1",
  "org.datanucleus" % "datanucleus-enhancer" % "1.1.4",
  "org.datanucleus" % "datanucleus-core" % "1.1.5",
  //"org.datanucleus" % "datanucleus-jpa" % "1.1.5",
  "javax.jdo" % "jdo2-api" % "2.3-eb"
  //"org.apache.geronimo.specs" % "geronimo-jpa_3.0_spec" % "1.1.1",
  //"org.apache.geronimo.specs" % "geronimo-jta_1.1_spec" % "1.1.1"
)
