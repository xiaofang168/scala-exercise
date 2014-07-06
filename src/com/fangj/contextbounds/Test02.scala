/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test02.scala
 * @Prject: test03
 * @Package: com.fangj.contextbounds
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.contextbounds

import java.util.Comparator

/**
 * @ClassName: Test02
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:32:48
 * @version: V1.0
 */
object Test02 {
  def max2[T: Comparator](a: T, b: T) = {

    val cp = implicitly[Comparator[T]]

    if (cp.compare(a, b) > 0) a else b
  }

  implicit val c = new Comparator[Int] {
    override def compare(a: Int, b: Int) = a - b
  }
  def main(args: Array[String]) {
    max2(4, 7)
  }
}