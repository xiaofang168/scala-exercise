/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.implicit
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.implic

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午2:05:09
 * @version: V1.0
 */

class StringOrInt[T]
object StringOrInt {
  implicit object IntWitness extends StringOrInt[Int]
  implicit object StringWitness extends StringOrInt[String]
}


object Test01 {
  def main(args: Array[String]) {
    Bar.foo("dd")
  }
}

object Bar {
  def foo[T: StringOrInt](x: T) = x match {
    case _: String => println("str")
    case _: Int => println("int")
  }
}
