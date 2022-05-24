/*
 * Prject: scala-exercise
 * Description: Father.scala
 * created at: 2015年3月18日
 */
package com.fangj.linkcall

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年3月18日 下午4:36:34
 * @version: $Rev$
 */
//class A {def method1: A = this }
class A {
  def method1: this.type = this
}

class B extends A {
  def method2: this.type = this
}

object LinkCall extends App {
  val b = new B
  b.method1.method2 // ok
}
