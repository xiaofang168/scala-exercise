package com.fangj.zio

import zio._
import zio.console._

object ZioZManaged extends zio.App {

  def run(args: List[String]) = {
    // 构造资源
    val zManagedResource: ZManaged[Console, Nothing, Int] = ZManaged.succeed(3)
    // 使用资源
    val zUsedResource: URIO[Console, Unit] = zManagedResource.use { a => putStrLn(s"the value is $a") }
    zUsedResource.exitCode
  }

}
