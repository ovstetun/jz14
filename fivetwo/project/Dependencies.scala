import sbt._
import sbt.Keys._

object Dependencies {

  object versions {
    val unfiltered = "0.8.1"
    val dispatch = "0.11.2"
    val slick = "2.1.0"
    val c3p0 = "0.9.1.2"
    val mysql = "5.1.32"
    val flyway = "3.0"
    val elastic4s = "1.3.0.0"

    val specs = "2.4.1"
    val h2 = "1.4.181"
  }


  val unfilteredDeps = Seq(
    "net.databinder" %% "unfiltered-filter" % versions.unfiltered,
    "net.databinder" %% "unfiltered-jetty" % versions.unfiltered,
    "net.databinder" %% "unfiltered-directives" % versions.unfiltered,
    "net.databinder" %% "unfiltered-json4s" % versions.unfiltered,
    "net.databinder" %% "unfiltered-netty-server" % versions.unfiltered,
    "net.databinder.dispatch" %% "dispatch-core" % versions.dispatch
  )

  val logDeps = Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.2"
  )

  val elasticDeps = Seq(
    "com.sksamuel.elastic4s" %% "elastic4s" % versions.elastic4s,
    "com.fasterxml.jackson.core" % "jackson-core" % "2.4.1",
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.1.1",
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.4.0-rc2" exclude("org.scalatest", "scalatest_2.10.0")
  )

  val slickDeps = Seq(
    "com.typesafe.slick" %% "slick" % versions.slick,
    "mysql" % "mysql-connector-java" % versions.mysql,
    "c3p0" % "c3p0" % versions.c3p0,
    "org.flywaydb" % "flyway-core" % versions.flyway
  )


  val testDeps = Seq(
    "org.specs2" %% "specs2" % versions.specs,
    "com.h2database" % "h2" % versions.h2
  ) map (_ % "test")

}
