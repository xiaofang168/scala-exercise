/*
 * Prject: scala-exercise
 * Description: Test.scala
 * created at: 2015年3月18日
 */
package com.fangj.variance

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年3月18日 下午5:45:52
 * @version: $Rev$
 */
trait A[+T]

class C[+T] extends A[T] // C是invariant的

class X

class Y extends X

object Test extends App {

  val t: C[X] = new C[Y]

  val list: List[X] = List[Y]()

}