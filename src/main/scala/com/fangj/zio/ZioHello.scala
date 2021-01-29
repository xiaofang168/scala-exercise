package com.fangj.zio

import zio.console.Console
import zio.{IO, ZIO, _}


/**
 * 没有 *> logger.info 只是一个ZIO[, x, x] ， 这个结构没有runtime的情况下是没法执行
 */
object ZioHello extends App {

  println(">>>>")

  val r: ZIO[Console, Throwable, Int] = ZIO.succeed(42)
  val b = r.fold(
    f => {
      println(s"fail f=$f");
      0
    },
    s => {
      println(s"success res = $s");
      1
    }
  )

  val runtime: Runtime[zio.ZEnv] = Runtime.default
  runtime.unsafeRun(b)

  val program: ZIO[Console, Nothing, Unit] =
    console.putStrLn("TicTacToe game!")

  runtime.unsafeRun(program)

  def run(args: List[String]) = {
    (IO.effect(println("Hello World!")) *> IO.unit.forever).run.exitCode
  }

}
