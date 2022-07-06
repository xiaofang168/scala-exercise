package com.fangj.akka


import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object FutureThenFutureExample extends App {

  implicit val system = akka.actor.ActorSystem("my-classic-actor-system")

  val delayed: Future[String] = akka.pattern.after(1000.millis)(Future.successful {
    println("delayed one second...")
    "OHNOES"
  })

  val future = Future {
    println("休眠2秒...")
    Thread.sleep(2000);
    "foo"
  }

  val f = future.flatMap(_ => {
    println("休眠5秒...")
    Thread.sleep(5000);
    Future.successful {
      "休眠1秒"
    }
  })

  Future.firstCompletedOf(Seq(f, delayed))

  val result = Future.sequence(Seq(f))
  result.onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(exception)
  }

}
