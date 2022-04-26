import Dependencies.dependencies

ThisBuild / scalaVersion := "2.13.8"

// "-Xfatal-warnings", // Fail the compilation if there are any warnings 开启后鸭子类型编译不通过
scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-encoding",
  "utf8",
  "-explaintypes",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps", // 可以不用import scala.language.postfixOps
  "-Ymacro-annotations" // 请注意，我们正在使用 Scala 2.13，我们将需要启用-ymacro-annotation 编译器标志，以便我们能够使用 ZIO 提供的一些宏
)

lazy val root = (project in file("."))
  .settings(
    name := "scala-exercise",
    organization := "com.fangj",
    version := "1.0",
    libraryDependencies ++= dependencies
  )

// 应对java混写，可以在idea中编译scala选项中设置
compileOrder := CompileOrder.JavaThenScala

