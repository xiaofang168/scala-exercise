name := "scala-exercise"

organization := "com.fangj"

version := "0.1"

scalaVersion := "2.11.6"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.withBundledScalaContainers := false

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq("-encoding", "UTF-8", "-target:jvm-1.8")

javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8")

compileOrder := CompileOrder.JavaThenScala

//externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

//resolvers ++= Seq(
//      "oschina" at "http://maven.oschina.net/content/groups/public"
//)

libraryDependencies ++= {
  val akkaVersion = "2.4.19"
  Seq(
    "com.twitter" %% "util-eval" % "6.24.0" withSources(),
    "io.spray" %% "spray-json" % "1.2.6" withSources(),
    "org.apache.pdfbox" % "pdfbox" % "1.8.8",
    "org.mongodb" %% "casbah-query" % "2.8.0",
    "org.mongodb" %% "casbah-core" % "2.8.0",
    "org.mongodb" %% "casbah-gridfs" % "2.8.0",
    "org.mongodb" %% "casbah-commons" % "2.8.0",
    "org.typelevel" %% "cats-core" % "1.1.0",
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-distributed-data-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-typed-experimental" % akkaVersion
  )
}
