/*
 * Prject: scala-exercise
 * Description: MapmapMakstring.scala
 * created at: 2014年7月16日
 */
package com.fangj.fun

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月16日 下午2:49:44
 * @version: $Rev$
 */
object MapmapMkstring {
  def main(args: Array[String]): Unit = {
    val map = Map("a" -> 40, "b" -> 35, "c" -> 20)
    val a = map.map {
      case (k, v) => {
        k + "=" + v
      }
    } mkString (" and ")

    println(a)
  }
}