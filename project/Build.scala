import sbt._
import Keys._
import org.datanucleus.enhancer.DataNucleusEnhancer
import javax.jdo._

object HelloBuild extends Build {
  val hwsettings = Defaults.defaultSettings ++ Seq(
    organization := "hello",
    name         := "world",
    version      := "1.0-SNAPSHOT"
    //scalaVersion := "2.9.0-1"
  )

  val hello = TaskKey[Unit]("hello", "Prints 'Hello World'")

  val helloTask = hello := {
    println("Hello World")
    new org.objectweb.asm.ByteVector()
    //org.datanucleus.enhancer.DataNucleusEnhancer.main(Array("-v", "-dir", "target/scala-2.9.2/classes", "org/pigsaw/wharfinger/Article.class"))
    val enhancer = JDOHelper.getEnhancer();
    enhancer.setVerbose(true);
    //enhancer.addPersistenceUnit("MyPersistenceUnit");
    enhancer.enhance();
}

  lazy val project = Project (
    "project",
    file ("."),
    settings = hwsettings ++ Seq(helloTask)
  )
}
