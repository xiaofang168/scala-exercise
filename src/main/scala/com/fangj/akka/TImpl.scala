package com.fangj.akka

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import java.nio.file.Paths
import scala.concurrent.Future
import scala.concurrent.duration._


/**
 * @author fangjie
 * @date Created in 下午6:10 18/6/4.
 */
object TImpl extends App {

  implicit val system = ActorSystem("QuickStart")
  val source = Source(1 to 100)
  source.runForeach(i => println(i))
  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)
  val result = factorials
    .map(num => ByteString(s"$num\n"))
    .runWith(FileIO.toPath(Paths.get("factorials.txt")))

  //reusable
  def lineSink(filename: String): Sink[String, Future[IOResult]] =
    Flow[String]
      .map(s => ByteString(s + "\n"))
      .toMat(FileIO.toPath(Paths.get(filename)))(Keep.right)

  factorials.map(_.toString).runWith(lineSink("factorial2.txt"))
  factorials.zipWith(Source(0 to 100))((num, idx) => s"$idx! = $num").throttle(1, 1.second, 1, ThrottleMode.Shaping).runForeach(println)
  Thread.sleep(110000)
  System.exit(0)

}
