/*
 * @FileName: MapMerge.scala
 * @Prject:scala-exercise
 * @Package: com.fangj
 * @date: 2014-9-14
 */
package com.fangj

/**
 * @ClassName: MapMerge
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午10:03:06
 * @version: V1.0
 */
object MapMerge {
  def updateWith[A, B](m: Map[A, B], kv: (A, B))(f: (B, B) => B): Map[A, B] = {
    val (k, v) = kv
    val newV = if (m contains k) f(m(k), v) else v
    m.updated(k, newV)
  }

  def mergeMapValues[A, B](m1: Map[A, B], m2: Map[A, B])(f: (B, B) => B): Map[A, B] = m2.foldLeft(m1)(updateWith(_, _)(f))

  def main(args: Array[String]) {
    val m1 = Map(1 -> 2)
    val m2 = Map(1 -> 4)
    val m = mergeMapValues(m1, m2)(_ + _)
    println(m)
  }

}