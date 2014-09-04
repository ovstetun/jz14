import Dependencies._
import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

libraryDependencies ++= unfilteredDeps

libraryDependencies ++= elasticDeps

libraryDependencies ++= logDeps

libraryDependencies ++= slickDeps

packageArchetype.java_application
