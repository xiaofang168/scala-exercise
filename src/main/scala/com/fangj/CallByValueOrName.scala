/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala-exercise
 * Description: CallByValueOrName.scala
 * created at: 下午6:12:23
 */
package com.fangj

/**
 * call by value or call by name
 * call by name 按需执行
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年1月17日
 * @version: $Rev$
 */
object CallByValueOrName {

  def square(x: Int, y: Int): Int = x * x

  def e(s: String) = {
    println(s)
    4 * 4
  }

  def aa(f: => Int, f2: => Int): Int = {
    f * f
  }

  def main(args: Array[String]) {
    square(3, 4)
    square(3 + 4, e("call by value!"))
    aa(3, e("call by name!") + 4)
  }

}