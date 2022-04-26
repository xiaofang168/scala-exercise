/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test2.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: Test2
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午1:34:49
 * @version: V1.0
 */


object Test2 {
  class Rational2(val n: Int, val d: Int) {

    require(d != 0)

    override def toString = s"${n}/${d}"

    def add(a: Rational2): Rational2 =

      new Rational2(n * a.d + a.n * d, d * a.d)

  }

  class Plus(n: Int) {
    def next = n + 1
  }

  implicit def aa(n: Int) = new Plus(n)

  def main(args: Array[String]): Unit = {

    val oneHalf = new Rational2(1, 2)

    val twoThirds = new Rational2(2, 3)

    val s = oneHalf add twoThirds

    println(s)

    val list = new java.util.ArrayList[Int]() with ForEachAble[Int]
    list.add(1);
    list.add(2)
    list.foreach(println(_))
    //println("Json: " + list.toJson)
    val b = 2.next
    println(b)
  }

}