package com.fangj.fs2

import cats.effect.{IO, IOApp}
import fs2.Stream

import scala.util.Random

object AcqAndRel extends IOApp.Simple {

  override def run(): IO[Unit] = {
    val acquire = IO(println("Aquiring resource")) *> IO(new Random())
    val release = (_: Random) => IO(println("Releasing resource"))
    Stream.bracket(acquire)(release).flatMap { rand =>
      fs2.Stream.emits(1 to 10).map(_ => rand.nextInt(1000))
    }.evalTap(n => IO(println(n))).compile.drain
  }

}
