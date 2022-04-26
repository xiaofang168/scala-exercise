/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala_test
 * Description: StreamTest.scala
 * created at: 下午10:06:29
 */
package com.fangj

/**
 * Streams are like list
 * but they tailored evaluated only on demand
 *
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年1月21日
 * @version: $Rev$
 */
object StreamTest {

  def streamRange(lo: Int, hi: Int): LazyList[Int] = {
    println(s"${lo}")
    if (lo >= hi) LazyList.empty
    // x :: xs always produces a list,never a stream
    // x #:: xs == Stream.cons(x,xs)
    else LazyList.cons(lo, streamRange(lo + 1, hi))
  }

  def main(args: Array[String]): Unit = {
    streamRange(1, 10).take(3).toList
  }

}