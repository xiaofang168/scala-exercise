/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: ConcurrencyCompute.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: ConcurrencyCompute
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:01:08
 * @version: V1.0
 */
object ConcurrencyCompute {
  def main(args: Array[String]) {
    val urls = List("http://www.baidu.com",
      "http://www.csdn.net",
      "http://www.cnblogs.com/anrainie/archive/2012/03/09/2387272.html")

    def fromURL(url: String) = scala.io.Source.fromURL(url)
      .getLines().mkString("\n")

    val t = System.currentTimeMillis()
    //每个map中的函数都可以并发执行
    urls.par.map(fromURL(_))
    // 非并行执行
    //urls.map(fromURL(_))
    println("time: " + (System.currentTimeMillis - t) + "ms")
  }
}