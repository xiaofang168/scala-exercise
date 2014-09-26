/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: FactorialTest.scala
 * created at: 2014年9月26日
 */
package com.fangj.tailrec

import scala.annotation.tailrec

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年9月26日 下午4:22:03
 * @version: $Rev$
 */
object FactorialTest {
  
  @tailrec
  def factorialTailrec(n: BigInt, acc: BigInt): BigInt = {
    if (n <= 1) acc
    else factorialTailrec(n - 1, acc * n)
  }

  def main(args: Array[String]) {
    val result = BigInt("2").pow(12)
    println(result)
  }
  
}