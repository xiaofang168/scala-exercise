package com.fangj.zio

import zio._
import zio.clock.Clock
import zio.console._
import zio.duration._

object ZioHeartBeatServer extends zio.ManagedApp {
  def sendHeartBeat(): URIO[Console with Clock, Unit] =
    console.putStrLn("heart-beat").repeat(Schedule.fixed(1.second)).ignore

  override def run(args: List[String]): ZManaged[zio.ZEnv, Nothing, ExitCode] =
    Managed
      .make(sendHeartBeat().fork.interruptible)(_.interrupt)
      .map(_ => ExitCode(0))

}
