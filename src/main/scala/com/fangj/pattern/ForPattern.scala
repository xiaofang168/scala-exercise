/*
 * Prject: scala-exercise
 * Description: ForPattern.scala
 * created at: 2014年12月2日
 */
package com.fangj.pattern

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月2日 上午11:23:58
 * @version: $Rev$
 */
object ForPattern extends App {
  for (i@2 <- List(1, 2, 3)) {
    println(i)
  }
  val rs = for ((name, "female") <- Set("wang" -> "male", "zhang" -> "female")) yield name
  for ((k, v: Int) <- List(("A" -> 2), ("B" -> "C"))) {
    println(k)
  }
}