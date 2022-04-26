/*
 * @FileName: FutureExecutionContextOrActor.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.future
 * @date: 2014-8-2
 */
package com.fangj.future

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
//import akka.actor.ActorSystem
import scala.collection.parallel.CollectionConverters._

/**
 * @ClassName: FutureExecutionContextOrActor
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午10:14:47
 * @version: V1.0
 */
object FutureExecutionContextOrActor {

  def main(args: Array[String]): Unit = {
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
    test()
    pool.shutdown()
  }

  /**
   * Wait for several Futures
   * How to wait for list of `Future`s created using different `ExecutorServices`
   */
  def test(): Unit = {
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

    resultF foreach {
      case (r1, r2, r3) => println(s"$r1, $r2, $r3"); pool.shutdown()
    }

  }
}