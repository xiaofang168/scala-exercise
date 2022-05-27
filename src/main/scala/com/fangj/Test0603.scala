/*
 * @FileName: Test0603.scala
 * @Prject:test03
 * @Package: com.fangj
 * @date: 2014-6-3
 */
package com.fangj

/**
 * @ClassName: Test0603
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午10:30:49
 * @version: V1.0
 */
object Test0603 {
  def hello(m: String): Int = {
    var a = 1
    for (i <- m) {
      Console println i
      a = a * i
      Console println a
    }
    if (a % 2 == 0) {
      a = a - 1
      a
    } else a
  }

  def main(args: Array[String]): Unit = {
    val m = "Scala"
    val s = hello(m)
    val map: Map[String, Either[Double, String]] = Map("a" -> Left(1), "b" -> Right("hello"))
    println(s)
    println(map)
  }

}