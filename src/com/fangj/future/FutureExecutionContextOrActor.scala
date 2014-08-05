/*
 * @FileName: FutureExecutionContextOrActor.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.future
 * @date: 2014-8-2
 */
package com.fangj.future

import java.util.concurrent.Executors
import scala.concurrent.{ ExecutionContext, Promise }
import scala.concurrent.Await
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.Success
//import akka.actor.ActorSystem
import ExecutionContext.Implicits.global

/**
 * @ClassName: FutureExecutionContextOrActor
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午10:14:47
 * @version: V1.0
 */
object FutureExecutionContextOrActor {

  def main(args: Array[String]) {
    // 方式一
    //    val system = ActorSystem("ActorSystem")
    // 方式二
    val pool = Executors.newCachedThreadPool()
    implicit val ec = ExecutionContext.fromExecutorService(pool)
    for (i <- (1 to 1000).par) {
      Future {
        Thread.sleep(1000 * 2)
        println(">>>>>>")
      }
      Future {
        println("aa:" + i)
      }
      Future {
        println("bb:" + i)
      }
      Future {
        println("???")
      }
    }
    println("ooooo")
    test
    pool.shutdown()
  }

  def test() {
    val pool = Executors.newCachedThreadPool()
    implicit val ec = ExecutionContext.fromExecutorService(pool)
    val a = Future {
      Thread.sleep(1000 * 2)
      println(">>>>>>")
      "aa"
    }
    val b = Future {
      println("bb:")
      "bb"
    }
    val c = Future {
      println("cc:")
      "cc"
    }
    val d = Future {
      println("dd")
      "dd"
    }

    val resultF = for {
      r1 <- a
      r2 <- b
      r3 <- c
    } yield (r1, r2, r3)
    
    resultF.onSuccess {
      case (r1, r2, r3) => println(s"$r1, $r2, $r3"); pool.shutdown()
    }

  }
}