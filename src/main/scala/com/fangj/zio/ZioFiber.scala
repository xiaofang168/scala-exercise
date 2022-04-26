package com.fangj.zio

import zio._
import zio.clock.Clock
import zio.duration._

/**
 * 使用了插件隐式转换输出zio值
 *
 * @see https://blog.rockthejvm.com/zio-fibers/
 */
object ZioFiber extends zio.App {

  import ZioPrintable.PrintableOps

  private val bathTime: UIO[String] = ZIO.succeed("去洗手间")
  val boilingWater = ZIO.succeed("烧水")
  val preparingCoffee = ZIO.succeed("准备咖啡")
  val aliceCalling = ZIO.succeed("Alice's call")
  private val preparingCoffeeWithSleep: ZIO[Clock, Any, String] = preparingCoffee.debug() *> ZIO.sleep(5.seconds) *> ZIO.succeed("Coffee ready")

  def sequentialWakeUpRoutine(): ZIO[zio.ZEnv, Nothing, Unit] =
    for {
      _ <- bathTime.debug()
      _ <- boilingWater.debug()
      _ <- preparingCoffee.debug()
    } yield ()

  def concurrentBathroomTimeAndBoilingWater(): ZIO[zio.ZEnv, Nothing, Unit] = for {
    _ <- bathTime.debug().fork
    _ <- boilingWater.debug()
  } yield ()

  def concurrentWakeUpRoutine(): ZIO[zio.ZEnv, Nothing, Unit] = for {
    bathFiber <- bathTime.debug().fork
    boilingFiber <- boilingWater.debug().fork
    zippedFiber = bathFiber.zip(boilingFiber)
    result <- zippedFiber.join.debug()
    _ <- ZIO.succeed(s"$result...done").debug() *> preparingCoffee.debug()
  } yield ()


  // 5.seconds is not the Scala standard duration
  val boilingWaterWithSleep = boilingWater *> ZIO.sleep(5.seconds) *> ZIO.succeed("Boiled water ready")

  def concurrentWakeUpRoutineWithAliceCall(): ZIO[zio.ZEnv, Nothing, Unit] = {
    val fork: URIO[Clock, Fiber.Runtime[Any, String]] = boilingWaterWithSleep.fork
    for {
      _ <- bathTime.debug()
      boilingFiber <- fork
      _ <- aliceCalling.debug().fork *> boilingFiber.interrupt.debug()
      _ <- ZIO.succeed("Going to the Cafe with Alice").debug()
    } yield ()
  }

  def concurrentWakeUpRoutineWithAliceCallingUsTooLate(): ZIO[zio.ZEnv, Nothing, Unit] = {
    val uninterruptible: ZIO[Clock, Nothing, Fiber.Runtime[Any, String]] = preparingCoffeeWithSleep.debug().fork.uninterruptible
    for {
      _ <- bathTime.debug()
      _ <- boilingWater.debug()
      coffeeFiber <- uninterruptible
      result <- aliceCalling.debug().fork *> coffeeFiber.interrupt.debug()
      _ <- result match {
        case Exit.Success(value) => ZIO.succeed("Making breakfast at home").debug()
        case _ => ZIO.succeed("Going to the Cafe with Alice").debug()
      }
    } yield ()
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    sequentialWakeUpRoutine().exitCode *>
      console.putStrLn("异步执行") *>
      concurrentBathroomTimeAndBoilingWater().exitCode *>
      console.putStrLn("异步后同步") *>
      concurrentWakeUpRoutine().exitCode *>
      console.putStrLn("异常中断") *>
      concurrentWakeUpRoutineWithAliceCall().exitCode *>
      console.putStrLn("完美中断") *>
      concurrentWakeUpRoutineWithAliceCallingUsTooLate().exitCode

}


