/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014-3-24
 * @version: V1.0
 */
package com.fangj

import com.fangj.{Rational => r}

import java.io.{File, PrintWriter}
import scala.collection.mutable.ArrayBuffer

/**
 * @ClassName: Test
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午5:40:12
 * @version: V1.0
 */
object Test {

  var assertionsEnabled = true

  val filesHere = new java.io.File(".").listFiles()

  def main(args: Array[String]) {
    //filesEnding("abc")
    // withPrintWriter(new File("date.txt"),_.println(""))
    //withPrintWriter(new File("date.txt"), writer => writer.println(new java.util.Date))
    //assertionsEnabled = false
    //byNameAssert(5 / 0 == 0)
    //boolAssert(5 / 0 == 0)

    //var arr1 = Array(1, 2)
    //var arr2 = Array(3, 4, 5)
    //var arr3 = arr1 ++ arr2
    //arr3.foreach(println _)
    //    val queue = new BasicIntQueue
    //    queue.put(10)
    //    queue.put(20)
    //    Console println queue.get
    //    val queue = (new BasicIntQueue with Incrementing with Filtering)
    //    queue.put(-1); queue.put(0); queue.put(1)
    //    Console println queue.get
    //    Console println queue.get

    //    val queue1 = (new BasicIntQueue with Filtering with Incrementing)
    //    queue1.put(-1); queue1.put(0); queue1.put(1)
    //    Console println queue1.get
    //    Console println queue1.get
    //    Console println queue1.get

    //    val q = new BasicIntQueue with Incrementing with Doubling
    //    q.put(42)
    //    Console println q.get
    // 引用重命名成员
    val a = new r(1, 2)
    // yield 用法
    new ArrayElement(
      for (
        (line1, line2) <- Array("1", "2") zip Array("3", "4")
      ) yield line1 + line2)
    val args = Array("dd", "444")
    printArgs(args)
  }

  // 把函数当中参数传递(使用了闭包)
  def filesEnding(query: String) = filesMatching(query, _.endsWith(query))

  def filesMatching(query: String, matcher: (String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName()))
      yield file
  }

  def withPrintWriter(file: File, op: (PrintWriter) => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  // 传递表达式和传递参数的区别
  def byNameAssert(predicate: => Boolean) = {
    if (assertionsEnabled && !predicate)
      throw new AssertionError
  }

  def boolAssert(predicate: Boolean) = {
    if (assertionsEnabled && !predicate) {
      throw new AssertionError
    }
  }

  // 特质类的运用（堆叠）
  abstract class IntQueue {
    def get(): Int

    def put(x: Int)
  }

  trait Doubling extends IntQueue {
    abstract override def put(x: Int) {
      super.put(2 * x)
    }
  }

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) {
      super.put(x + 1)
    }
  }

  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]

    def get() = buf.remove(0)

    def put(x: Int) {
      buf += x
    }
  }

  trait Filtering extends IntQueue {
    abstract override def put(x: Int) {
      if (x >= 0) super.put(x)
    }
  }

  class ArrayElement(
                      // 请注意，小括号
                      val contents: Array[String])

  def printArgs(args: Array[String]): Unit = {
    args.foreach(println)
  }
}