/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.contextbounds
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.contextbounds

import java.util.Comparator

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午2:37:50
 * @version: V1.0
 */
object Test01 {
  def max[T: Comparator](a: T, b: T) = {
    def inner(implicit c: Comparator[T]) = c.compare(a, b);
    if (inner > 0) a else b
  }
  implicit val c = new Comparator[Int] {
    override def compare(a: Int, b: Int) = a - b
  }
  def main(args: Array[String]) {
    Console println max(3, 5)
  }
}