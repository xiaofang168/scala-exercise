package com.fangj.fs2

import cats.effect.{IO, IOApp}
import fs2.Stream

import scala.concurrent.duration._

trait Counter {
  def incrementBy2: IO[Unit]

  def printCounter: IO[Unit]
}

object Counter {
  val inMemory: IO[Counter] =
    IO.ref(0).map { ref =>
      new Counter {
        override final val incrementBy2: IO[Unit] =
          ref.update(c => c + 2)

        override final val printCounter: IO[Unit] =
          ref.get.flatMap(IO.println)
      }
    }
}

object Program {
  def run(counter: Counter): Stream[IO, Unit] =
    Stream
      .repeatEval(counter.printCounter)
      .metered(1.second)
      .concurrently(
        Stream.repeatEval(counter.incrementBy2).metered(2.seconds)
      ).interruptAfter(10.seconds)
}

object TwoStream extends IOApp.Simple {
  override final val run: IO[Unit] =
    Stream
      .eval(Counter.inMemory)
      .flatMap(Program.run)
      .compile
      .drain
}
