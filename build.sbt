name := "scala-exercise"

organization := "com.fangj"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq("-encoding", "UTF-8", "-target:jvm-1.8")

javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8")

compileOrder := CompileOrder.JavaThenScala

libraryDependencies ++= {
  val akkaVersion = "2.5.21"
  Seq(
    "com.twitter" %% "util-eval" % "6.24.0" withSources(),
    "io.spray" %% "spray-json" % "1.2.6" withSources(),
    "org.apache.pdfbox" % "pdfbox" % "1.8.8",
    "org.mongodb" %% "casbah-query" % "2.8.0",
    "org.mongodb" %% "casbah-core" % "2.8.0",
    "org.mongodb" %% "casbah-gridfs" % "2.8.0",
    "org.mongodb" %% "casbah-commons" % "2.8.0",
    "joda-time" % "joda-time" % "2.9.9",
    "org.typelevel" %% "cats-core" % "1.1.0",
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
    "dev.zio" %% "zio" % "1.0.4"
  )
}
