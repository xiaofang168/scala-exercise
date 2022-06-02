import sbt._

object Dependencies {

  val akkaVersion = "2.6.19"

  val circeVersion = "0.14.1"

  lazy val testDependencies = Seq(
    "org.scalactic" %% "scalactic" % "3.2.11",
    "org.scalatest" %% "scalatest" % "3.2.11" % "test",
  )

  lazy val rootDependencies = Seq(
    "joda-time" % "joda-time" % "2.9.9",
    "org.typelevel" %% "cats-core" % "2.0.0",
    "org.typelevel" %% "cats-free" % "2.0.0",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % "10.2.9",
    "org.iq80.leveldb" % "leveldb" % "0.7",
    "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
    "com.twitter" %% "chill-akka" % "0.10.0", //事件序列化依赖
    "org.jsoup" % "jsoup" % "1.12.1",
    "com.alibaba" % "fastjson" % "1.2.62",
    "org.apache.commons" % "commons-lang3" % "3.9",
    "org.apache.commons" % "commons-text" % "1.8",
    "dev.zio" %% "zio" % "1.0.4",
    "com.chuusai" %% "shapeless" % "2.3.3",
    "io.estatico" %% "newtype" % "0.4.4",
    "eu.timepit" %% "refined" % "0.9.12",
    "com.softwaremill.common" %% "tagging" % "2.2.1",
    "com.alibaba" % "easyexcel" % "3.0.5",
    "org.json4s" %% "json4s-jackson" % "4.0.3",
    "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
    "org.apache.httpcomponents" % "httpclient" % "4.5.13",
    "org.projectlombok" % "lombok" % "1.18.12" % "provided",
    "org.apache.camel" % "camel-core" % "3.14.0"
  )

  lazy val circeDependencies = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser",
    "io.circe" %% "circe-refined",
    "io.circe" %% "circe-generic-extras"
  ).map(_ % circeVersion)

  lazy val fs2Dependencies = Seq(
    "co.fs2" %% "fs2-core" % "3.2.4"
  )

  lazy val dependencies = Seq(
    "com.twitter" % "util-eval_2.12" % "6.43.0" withSources() exclude("org.scala-lang.modules", "scala-parser-combinators_2.12"),
    "io.spray" %% "spray-json" % "1.3.6",
    "org.apache.pdfbox" % "pdfbox" % "1.8.8",
    "org.mongodb" % "casbah-query_2.12" % "3.1.1",
    "org.mongodb" % "casbah-core_2.12" % "3.1.1",
    "org.mongodb" % "casbah-gridfs_2.12" % "3.1.1",
    "org.mongodb" % "casbah-commons_2.12" % "3.1.1"
  ) ++ testDependencies ++ circeDependencies ++ fs2Dependencies ++ rootDependencies

}