/*
 * Prject: scala-exercise
 * Description: Test.scala
 * created at: 2014年12月2日
 */
package com.fangj.toolbox

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年12月2日 上午11:16:49
 * @version: $Rev$
 */

import scala.tools.reflect.ToolBox

object Test extends App {
  val tb = scala.reflect.runtime.universe.runtimeMirror(getClass.getClassLoader).mkToolBox()
  println(tb.parse(
    """class Counter(n: Int) {
  def biggerThan(c: Counter) {
    this.n > c.n
  }
}"""))

  println(tb.parse(
    """class Counter(n: Int) {
  var count = n
  def biggerThan(c: Counter) {
    this.n > c.count
  }
}"""))
}