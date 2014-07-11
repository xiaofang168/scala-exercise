/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: RegexTest.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月12日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: RegexTest
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午1:21:09
 * @version: V1.0
 */
object RegexTest {
  def main(args: Array[String]) {
    val Name = """(Mr|Mrs|Ms)\. ([A-Z][a-z]+) ([A-Z][a-z]+)""".r
    val smith = "Mr. John Smith"
    val Name(title, first, last) = smith

    Console println title
    println(s"$title $first $last") 
  }
}