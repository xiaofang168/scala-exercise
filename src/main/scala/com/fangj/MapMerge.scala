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

  // 返回的值为m1类型，updateWith(_, _) 第一个参数为m1,第二个参数为m2元素
  def mergeMapValues[A, B](m1: Map[A, B], m2: Map[A, B])(f: (B, B) => B): Map[A, B] = m2.foldLeft(m1)(updateWith(_, _)(f))

  def main(args: Array[String]): Unit = {
    val m1 = Map(1 -> 2, 3 -> 8)
    val m2 = Map(1 -> 4, 3 -> 9)
    val m = mergeMapValues(m2, m1)(_ + _)
    println(m)

    val L = List(2, 3, 4)
    val ll = L.foldLeft(List[Int](2, 3))((a, b) => {
      println(b); for (i <- a) yield (i + b)
    })
    println(ll)
  }

}