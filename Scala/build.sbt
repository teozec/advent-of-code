ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

val commonDependencies = Seq("org.scalatest" %% "scalatest" % "3.2.19" % "test")

lazy val aoc2024 = (project in file("2024"))
  .settings(
    name := "Advent of Code 2024",
    libraryDependencies ++= commonDependencies
  )

lazy val aoc2015 = (project in file("2015"))
  .settings(
    name := "Advent of Code 2015",
    libraryDependencies ++= commonDependencies
  )
