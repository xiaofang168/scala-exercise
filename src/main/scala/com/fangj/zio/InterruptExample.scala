package com.fangj.zio

import zio._

object InterruptExample extends App {

  import java.time.LocalTime

  import zio._
  import zio.duration._

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    for {
      fiber <- runSomeLongJob
        .ensuring(printWithTime("finalizing job!"))
        .forkDaemon
      _ <- printWithTime("do stuff").repeat(Schedule.spaced(1.seconds) && Schedule.recurs(2))
      _ <- fiber.interrupt
      _ <- printWithTime("do other stuff").repeat(Schedule.spaced(1.seconds))
    } yield ExitCode(0)


  // 优雅的中断协程
  def run2(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    for {
      fiber <- runSomeLongJob
        .ensuring(printWithTime("finalizing job!"))
        .forkDaemon
      _ <- printWithTime("do stuff").repeat(Schedule.spaced(1.seconds) && Schedule.recurs(2))
      timeout <- fiber.join.resurrect.ignore.disconnect.timeout(5.seconds)
      _ <- ZIO.when(timeout.isEmpty)(fiber.interruptFork)
      _ <- printWithTime("do other stuff").repeat(Schedule.spaced(1.seconds))
    } yield ExitCode(0)

  private def runSomeLongJob = {
    printWithTime("long running job...") *> clock.sleep(100.seconds)
  }

  private def printWithTime(msg: String) =
    console.putStrLn(s"${LocalTime.now().toString} $msg")
}
