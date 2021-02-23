package com.fangj.zio

import zio.console.Console
import zio.{IO, ZIO, _}


/**
 * 没有 *> logger.info 只是一个ZIO[, x, x] ， 这个结构没有runtime的情况下是没法执行
 */
object ZioHello extends App {

  println(">>>>")

  val r: ZIO[Console, Throwable, Int] = ZIO.succeed(42)

  // fold同时处理失败和成功
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

  // 构造println输出的运行环境
  val runtime: Runtime[zio.ZEnv] = Runtime.default

  // 传入R输出环境
  runtime.unsafeRun(b)

  val program: ZIO[Console, Nothing, Unit] =
    console.putStrLn("TicTacToe game!")

  // 传入R输出环境
  runtime.unsafeRun(program)


  def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] = {
    val zipped: ZIO[Console, Throwable, (String, Int)] = ZIO.succeed("8").zip(ZIO.succeed(8))

    val b = zipped.fold(
      _ => {
        println("fail");
        0
      },
      s => {
        println(">>>" + s)
        1
      }
    )
    runtime.unsafeRun(b)

    val goShopping = ZIO.effect(println("Going to the grocery store"))
    runtime.unsafeRun(goShopping)

    // zipRight
    val helloWorld = ZIO.effect(print("Hello, ")) *> ZIO.effect(print("World!\n"))
    runtime.unsafeRun(helloWorld)

    (IO.effect(println("Hello World!")) *> IO.unit.forever).run.exitCode

  }

}
