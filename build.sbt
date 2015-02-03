name := "scala-exercise"

organization  := "com.fangj"

version       := "0.1"

scalaVersion  := "2.10.3"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6")

javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.6", "-target", "1.6")

compileOrder := CompileOrder.JavaThenScala

externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

resolvers ++= Seq(
      "oschina" at "http://maven.oschina.net/content/groups/public"
)

libraryDependencies ++= {
  Seq(
	"org.scala-lang"   %       "scala-compiler"  % "2.10.3",
	"com.twitter"      %%  	   "util-eval"       % "6.12.1" withSources(),
	"io.spray" 			  %%   "spray-json"    % "1.2.6" withSources(),
	"org.apache.pdfbox" %      "pdfbox"          % "1.8.8"
  )
}
