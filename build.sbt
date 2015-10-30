name in ThisBuild := "sesame-rdfs"

organization in ThisBuild := "org.bananardf"

scalaVersion in ThisBuild := "2.11.7"

scalacOptions in ThisBuild ++= Seq("-feature", "-deprecation", "-unchecked")

lazy val dependencies = Seq(
  libraryDependencies += "org.openrdf.sesame" % "sesame-runtime" % "4.0.0"
)

lazy val root = project
  .in(file("."))
  .settings(dependencies)
