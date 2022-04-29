/*
 * Prject: scala-exercise
 * Description: ZeroWwidthAssertion.scala
 * created at: 2014年11月28日
 */
package com.fangj.regex

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年11月28日 下午3:06:50
 * @version: $Rev$
 */
object ZeroWwidthAssertion extends App {
  val regex = """(.*)(?<=/)""".r
  val result = regex.findFirstIn("E:/workspaces/scala-workspace/spark-start/dd") match {
    case Some(regex(s1)) => (s1)
    case _ => (null, null, null)
  }
  println(result)
}