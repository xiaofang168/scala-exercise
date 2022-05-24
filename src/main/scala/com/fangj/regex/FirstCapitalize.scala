/*
 * Prject: scala-exercise
 * Description: FirstCapitalize.scala
 * created at: 2014年12月10日
 */
package com.fangj.regex

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月10日 下午7:10:24
 * @version: $Rev$
 */
object FirstCapitalize extends App {
  val a = "huang_hong_qiao".split("_").map(_.capitalize).mkString("")
  println(a)
}