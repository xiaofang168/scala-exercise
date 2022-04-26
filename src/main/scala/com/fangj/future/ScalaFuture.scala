/*
 * @FileName: ScalaFuture.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.future
 * @date: 2014-7-24
 */
package com.fangj.future

/**
 * @ClassName: ScalaFuture
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午10:19:01
 * @version: V1.0
 */

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

object ScalaFuture extends App {

  val f1 = Future[String] {
    "Hello" + "World"
  }

  // 开启线程
  val f2: Future[Int] = f1 map { x => x.length }

  // 等待f2 完成 阻塞线程
  Await.result(f2, 10 second)

  // f2 完成之后执行的方法
  val k = f2.onComplete(
    msg => println(">>>" + msg.get))

  // f2 结束才会调用该foreach
  f2.foreach(e => println(s"${e}  f2 结束了"))

  Console println ("Main线程......")

}