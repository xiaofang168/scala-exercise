/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test02.scala
 * @Prject: test03
 * @Package: com.fangj.extrator
 * @date: 2014年5月16日
 */
package com.fangj.extrator

/**
 * @ClassName: Test02
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午2:00:25
 * @version: V1.0
 */
class C {
}

case class D(val a: C)

object Test02 {

  def foo(a: Any) = a match {
    case D(n) => println("b")
    case _ => println("__")
  }

  def main(args: Array[String]): Unit = {
    val c = new C
    foo(D(c))
  }
}