/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala-exercise
 * Description: FutureParallel.scala
 * created at: 下午6:41:55
 */
package com.fangj.future

import scala.concurrent.Future
import scala.concurrent.{ ExecutionContext, Promise }
import ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration
/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年7月4日
 * @version: $Rev$
 */
object FutureParallel extends App {

  // f1,f2 run in parallel
  val f1 = Future { println("f1"); 1 }
  val f2 = Future {
    println("hellow")
    2
  }
  val f3 = for {
    a <- f1
    b <- f2
  } yield (a + b)

  //val r = Await.result(f3, Duration.Inf)

  // f2 is executed only after f1 is completed.
  for {
    milk <- Future { println("f1"); 1 }
    flour <- Future { println("f2"); 2 }
  } yield (milk + flour)

  Thread.sleep(1000)

}