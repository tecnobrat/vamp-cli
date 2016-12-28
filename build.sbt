import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbt.Keys._

import scala.language.postfixOps
import scalariform.formatter.preferences._

name := "vamp-cli"

version := VersionHelper.versionByTag

scalaVersion := "2.12.1"

scalariformSettings ++ Seq(ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignParameters, true)
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(DanglingCloseParenthesis, Preserve)
  .setPreference(RewriteArrowSymbols, true))

lazy val root = project.in(sbt.file(".")).dependsOn(
  // for some reason it doesn't work for uri("git://github.com/magneticio/vamp.git")
  // workaround: vamp project needs to be checked out in ../
  ProjectRef(uri("../vamp"), "model")
)

mainClass in assembly := Some("io.vamp.cli.Boot")

scalacOptions += "-target:jvm-1.8"

javacOptions ++= Seq("-encoding", "UTF-8")

scalacOptions in ThisBuild ++= Seq(Opts.compile.deprecation, Opts.compile.unchecked) ++ Seq("-Ywarn-unused-import", "-Ywarn-unused", "-Xlint", "-feature")
