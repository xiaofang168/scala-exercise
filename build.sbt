name := "scala-exercise"

organization  := "com.fangj"

version       := "0.1"

scalaVersion  := "2.10.3"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq("-encoding", "UTF-8", "-target:jvm-1.7")

javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.7", "-target", "1.7")

compileOrder := CompileOrder.JavaThenScala

libraryDependencies ++= {
  Seq(
	"org.scala-lang"   %       "scala-compiler"  % "2.10.3",
	"com.twitter"      %%  	   "util-eval"       % "6.12.1" withSources()
  )
}
