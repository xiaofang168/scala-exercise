package com.fangj.zio

import zio.console.Console
import zio.{IO, ZIO, _}

import scala.io.StdIn


/**
 * <pre>
 * 没有 *> logger.info 只是一个ZIO[, x, x] ， 这个结构没有runtime的情况下是没法执行
 * zip，顺序地将两个effect的结果组合成结果的元组。
 * zipLeft，顺序地合并两个effect，并返回第一个的结果；
 * zipRight，依次组合两个effect并返回第二个结果。
 * 有时，您会看到<*用作zipLeft的别名，而*>用作zipRight的别名。
 * <h3>当你不需要所有effect的结果,这些运算符就特别有用</<h3>
 * 例如，在以下代码段中，我们顺序组合两个effect，返回右手effect的Unit成功值：
 * val helloWorld = ZIO.effect(print("Hello, ")) *> ZIO.effect(print("World!\n"))
 * *>非常有用，我们希望程序按顺序执行，但是左边的effect返回一个Unit,这个返回值并没有什么作用。
 * </pre>
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

    val firstName = ZIO.effect(StdIn.readLine("What is your first name?"))
    val lastName = ZIO.effect(StdIn.readLine("What is your last name"))
    val fullName = firstName.zipWith(lastName)((first, last) => s"$first $last")

    val f = fullName.fold(
      _ => {
        0
      },
      s => {
        println("???" + s)
        0
      }
    )
    runtime.unsafeRun(f)

    (IO.effect(println("Hello World!")) *> IO.unit.forever).run.exitCode

  }

}
