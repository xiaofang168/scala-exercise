name := "scala-exercise"

organization  := "com.fangj"

version       := "0.1"

scalaVersion  := "2.11.6"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.withBundledScalaContainers := false

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq("-encoding", "UTF-8", "-target:jvm-1.7")

javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.7", "-target", "1.7")

compileOrder := CompileOrder.JavaThenScala

//externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

//resolvers ++= Seq(
//      "oschina" at "http://maven.oschina.net/content/groups/public"
//)

libraryDependencies ++= {
  Seq(
	"com.twitter"      %%  	   "util-eval"       % "6.24.0" withSources(),
	"io.spray" 			  %%   "spray-json"    % "1.2.6" withSources(),
	"org.apache.pdfbox" %      "pdfbox"          % "1.8.8",
	"org.mongodb" %% "casbah-query"% "2.8.0",
	"org.mongodb" %% "casbah-core"% "2.8.0",
	"org.mongodb" %% "casbah-gridfs"% "2.8.0",
    "org.mongodb" %% "casbah-commons"% "2.8.0"
	)
}
