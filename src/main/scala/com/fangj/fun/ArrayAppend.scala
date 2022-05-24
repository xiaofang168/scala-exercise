/*
 * Prject: scala-exercise
 * Description: ArrayAppend.scala
 * created at: 2014年8月8日
 */
package com.fangj.fun

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年8月8日 下午1:05:07
 * @version: $Rev$
 */
object ArrayAppend {
  def main(args: Array[String]): Unit = {
    val a = Array(1, 2)
    val b = Array(3, 4)
    val c = a ++ b
    println(c mkString (""))
  }
}