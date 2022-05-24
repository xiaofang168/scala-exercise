/*
 * Prject: scala-exercise
 * Description: ImplicParam.scala
 * created at: 2015年2月7日
 */
package com.fangj.implic

/**
 * The compiler will figure out the right implicit to pass based on the demanded type
 *
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月7日 上午11:30:11
 * @version: $Rev$
 */

abstract class SemiGroup[A] {
  def add(x: A, y: A): A
}

abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}

object ImplicParam extends App {

  implicit object StringMonoid extends Monoid[String] {
    def add(x: String, y: String): String = x concat y

    def unit: String = ""
  }

  implicit object IntMonoid extends Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y

    def unit: Int = 0
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))

  println(sum(List(1, 2, 3)))
  println(sum(List("a", "b", "c")))
}