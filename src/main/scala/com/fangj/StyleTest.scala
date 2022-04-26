/*
 * @FileName: StyleTest.scala
 * @Prject:test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014-5-12
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: StyleTest
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午10:22:07
 * @version: V1.0
 */
object StyleTest {
  def fib(n: Int): Int = {
    def fibIter(i: Int, a: Int, b: Int): Int =
      if (i == n) a else fibIter(i + 1, b, a + b)
    fibIter(0, 0, 1)
  }
  def main(args: Array[String]): Unit = {
	  Console println fib(3)
  }
}