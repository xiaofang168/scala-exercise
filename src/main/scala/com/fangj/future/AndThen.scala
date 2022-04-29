/*
 * Prject: scala-exercise
 * Description: AndThen.scala
 * created at: 2014年8月8日
 */
package com.fangj.future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.postfixOps
import scala.util.Success

/**
 * Controlling flow with Scala Futures
 *
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年8月8日 上午9:30:34
 * @version: $Rev$
 */
object AndThen {
  def main(args: Array[String]): Unit = {

    Future {
      Thread.sleep(500)
      Console.println("Wham!")
      1000
    } andThen {
      case Success(e) => {
        Future {
          println(e)
          Thread.sleep(500)
          Console.println("Bam!")
          2000
        } andThen {
          case Success(e) => {
            println(e)
            Thread.sleep(500)
            Console.println("Thank you ma'am!")
          }
        }
      }
    }

    Console.println("Will you score?")

    Thread.sleep(2000)

  }
}