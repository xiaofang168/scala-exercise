/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.multiplebounds
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.multiplebounds

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午2:53:43
 * @version: V1.0
 */
object Test01 {
  class A
  class B
  implicit def string2A(s: String) = new A
  implicit def string2B(s: String) = new B
  def foo[T <% A <% B](x: T) = println("OK")
  def main(args: Array[String]) {
	  foo("ddd")
  }
}