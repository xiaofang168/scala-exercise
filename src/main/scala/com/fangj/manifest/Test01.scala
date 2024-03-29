/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.manifest
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.manifest

import scala.reflect.runtime.universe._

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:24:20
 * @version: V1.0
 */
object Test01 {
  //A =:= B  //表示A类型等同于B类型
  //A <:< B  //表示A类型是B类型的子类型
  def foo[T: Manifest](x: List[T]) = {
    if (typeOf[T] <:< typeOf[String])
      println("Hey, this list is full of strings")
    else
      println("Non-stringy list")
  }

  import scala.reflect.runtime.universe.TypeTag

  def f[A, B](a: A, b: B)(implicit evA: TypeTag[A], evB: TypeTag[B]): Boolean = evA == evB

  def manOf[T: Manifest](t: T): Manifest[T] = manifest[T]

  def main(args: Array[String]): Unit = {
    foo(List(3))

    println(manOf("3"))
  }
}