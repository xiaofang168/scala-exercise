/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala-exercise
 * Description: Test.scala
 * created at: 下午4:44:36
 */
package com.fangj.future

import scala.concurrent.Future
import scala.concurrent.{ ExecutionContext, Promise }
import ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年4月25日
 * @version: $Rev$
 */
case class TaxCut(reduction: Int)
object Government extends App {


   val a:Future[String] = Future {


      "We reduced the taxes! You must reelect us!!!!1111"
    }
  a.onComplete { msg => println(">>>" + msg.get) }
  
} 

