/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.extrator
 * @date: 2014年5月16日
 */
package com.fangj.extrator

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午1:50:25
 * @version: V1.0
 */
class A {
  println("a")
}
class B(val a: A)
object TT {
  def apply(p1: String, p2: String): B = new B(new A)
  def unapply(b: B) = Some(new A)
  def unapply(a: A) = Some(new A, new B(new A))
}
object Test01 {
  def main(args: Array[String]): Unit = {
    val b = new B(new A)
    //TT.unapply(b)  match{ case Some(new A) => }
    b match { case TT(a) => println(a) }
    new A match { case TT(a, b) => println(a, b) }
  }
}