/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: WordCount.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: WordCount
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午2:02:59
 * @version: V1.0
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val file = List("warn 2013 msg", "warn 2012 msg",
      "error 2013 msg", "warn 2013 msg")

    def wordcount(str: String): Int = str.split(" ").count("msg" == _)

    val num = file.map(wordcount).reduceLeft(_ + _)

    println("wordcount:" + num)
  }
}