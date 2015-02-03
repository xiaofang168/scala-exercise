/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: RegexTest.scala
 * created at: 2015年2月2日
 */
package com.fangj.regex

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月2日 下午4:26:02
 * @version: $Rev$
 */
object RegexTest2 {
  def main(args: Array[String]) {
    val Step = """to\((.*)\)""".r

    val Name = """to\((".*")\)""".r
    val smith = """to("2")"""
    val Name(title) = smith
    println(title)
  }
}