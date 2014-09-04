scalaVersion := "2.11.2"

organization := "no.ovstetun.fivetwo"

version := "1.0-SNAPSHOT"

lazy val common = project

lazy val ingredients = project.dependsOn(common)

lazy val env = project.dependsOn(common)
