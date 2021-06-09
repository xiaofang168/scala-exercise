package com.fangj.zio

import zio._

object DefectNotHandledExample extends App {

  import zio._
  import zio.duration._

  def run(args: List[String]) =
    for {
      _ <- runSomePeriodicJob
        .tapCause(c => console.putStrLn(s"job failed with $c. extra information..."))
        .repeat(Schedule.spaced(1.seconds))
        .forkDaemon
      _ <- console.putStrLn("do other stuff")
        .repeat(Schedule.spaced(1.seconds))
    } yield ExitCode(0)


  private def runSomePeriodicJob = {
    ZIO.succeed(throw new RuntimeException("unexpected defect")) *> ZIO.effect(println("running job..."))
  }

}