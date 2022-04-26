/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala-exercise
 * Description: FutureCallback.scala
 * created at: 下午4:27:52
 */
package com.fangj.future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年6月21日
 * @version: $Rev$
 */
object FutureCallback extends App {

  val future1 = Future(2)
  val futrue2 = Future(3)

  val purchase = for {
    usd <- future1
    chf <- futrue2
  } yield usd + chf
  val f = future1.flatMap { x => futrue2.map { y => x + y } }
  f foreach { case e => println(e) }
  purchase foreach { case e => println(e) }
  Thread.sleep(15)
}