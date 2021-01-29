package com.fangj.zio

import zio.console._

object ZioMyApp extends zio.App {
  def run(args: List[String]) = {
    print(myAppLogic)
    myAppLogic.exitCode
  }

  val myAppLogic =
    for {
      _ <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _ <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
    } yield ()

}
