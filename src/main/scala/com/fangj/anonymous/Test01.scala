/*
 * @FileName: Test01.scala
 * @Prject:test03
 * @Package: com.fangj.anonymous
 * @date: 2014-5-18
 */
package com.fangj.anonymous

/**
 * @ClassName: Test01
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午3:30:20
 * @version: V1.0
 */
object Test01 {

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    return f(a) + a + b
  }

  def matchs(f: (String) => Boolean): Unit = {
    if (f("aa")) Console println ">>>"
  }

  def main(args: Array[String]): Unit = {
    matchs(_.endsWith("a"))
  }
}