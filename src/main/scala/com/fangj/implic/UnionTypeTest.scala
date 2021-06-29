/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test03.scala
 * @Prject: test03
 * @Package: com.fangj.implic
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.implic

/**
 * 限定参数类型
 *
 * @ClassName: Test03
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:53:35
 * @version: V1.0
 */
object UnionTypeTest {
  def f[A](a: A)(implicit ev: (Int with String) <:< A) = println("OK")

  def main(args: Array[String]) {
    f(2)
    f("ee")
  }
}