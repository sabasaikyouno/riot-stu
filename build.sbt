name := "riot-stu"
organization := ""

version := "0.1"

scalaVersion := "2.13.10"

publishTo := Some(Resolver.file("riot-stu", file("riot-stu"))(Patterns(true, Resolver.mavenStyleBasePattern)))

libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.7.0"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.32.0"
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "4.9.3"

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)