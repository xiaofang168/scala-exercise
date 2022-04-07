name := "scala-exercise"

organization := "com.fangj"

version := "0.1"

scalaVersion := "2.11.6"

val circeVersion = "0.11.2"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-target:jvm-1.8")

// 应对java混写，可以在idea中编译scala选项中设置
compileOrder := CompileOrder.JavaThenScala

// 请注意，我们正在使用 Scala 2.13.6，我们将需要启用-ymacro-annotation 编译器标志，以便我们能够使用 ZIO 提供的一些宏。
// 如果你想使用 Scala < 2.13，you’ll need to add the macro paradise compiler plugin
libraryDependencies ++= {
  val akkaVersion = "2.5.21"
  Seq(compilerPlugin("org.scalamacros" %% "paradise" % "2.0.1" cross CrossVersion.full),
    "com.twitter" %% "util-eval" % "6.24.0" withSources(),
    "io.spray" %% "spray-json" % "1.2.6" withSources(),
    "org.apache.pdfbox" % "pdfbox" % "1.8.8",
    "org.mongodb" %% "casbah-query" % "2.8.0",
    "org.mongodb" %% "casbah-core" % "2.8.0",
    "org.mongodb" %% "casbah-gridfs" % "2.8.0",
    "org.mongodb" %% "casbah-commons" % "2.8.0",
    "joda-time" % "joda-time" % "2.9.9",
    "org.typelevel" %% "cats-core" % "2.0.0",
    "org.typelevel" %% "cats-free" % "2.0.0",
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % "10.0.11",
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-distributed-data-experimental" % "2.4.20",
    "com.typesafe.akka" %% "akka-typed-experimental" % "2.4.20",
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    "com.github.scullxbones" %% "akka-persistence-mongo-casbah" % "2.3.2",
    "org.iq80.leveldb" % "leveldb" % "0.7",
    "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
    "com.twitter" %% "chill-akka" % "0.8.0", //事件序列化依赖
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
    "commons-io" % "commons-io" % "2.8.0",
    "org.projectlombok" % "lombok" % "1.18.12" % "provided"
  )
}

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-refined"
).map(_ % circeVersion)
