package com.fangj.zio.zlayer

import zio._

object Logging {

  type Logging = Has[Logging.Service]

  trait Service {
    def info(s: String): UIO[Unit]

    def error(s: String): UIO[Unit]
  }

  import zio.console.Console

  val consoleLogger: ZLayer[Console, Nothing, Logging] = ZLayer.fromFunction(console =>
    new Service {
      def info(s: String): UIO[Unit] = UIO.succeed(println(s"info - $s"))

      def error(s: String): UIO[Unit] = console.get.putStrLn(s"error - $s")
    }
  )

  // accessor methods
  // 如果方法中的返回值类型不写，accessM(e: Logging)中的e类型必须写
  def info(s: String) = ZIO.accessM((e: Logging) => e.get.info(s))

  // accessor methods
  def info2() = {
    ZIO.accessM((e: Has[String]) => UIO.succeed(e.get.toInt))
  }

  def error(s: String): URIO[Logging, Unit] = ZIO.accessM(_.get.error(s))

  val makeLogging: ZIO[Logging, Nothing, Unit] = for {
    _ <- Logging.info("inserting user")
    _ <- Logging.error(s"user inserted")
  } yield ()

  Logging.info("inserting user")

  def main(args: Array[String]): Unit = {
    val runtime: Runtime[zio.ZEnv] = Runtime.default
    val a = makeLogging.provideLayer(consoleLogger)
    runtime.unsafeRun(a)
  }

}
