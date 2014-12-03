scalaVersion in ThisBuild := "2.11.2"

organization in ThisBuild := "no.ovstetun.fivetwo"

version in ThisBuild := "1.0-SNAPSHOT"

lazy val common = project

lazy val ingredients = project.dependsOn(common)

lazy val env = project.dependsOn(common)
