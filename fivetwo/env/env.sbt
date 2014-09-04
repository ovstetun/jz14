import Dependencies._
import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

libraryDependencies ++= unfilteredDeps

libraryDependencies ++= logDeps

packageArchetype.java_application
