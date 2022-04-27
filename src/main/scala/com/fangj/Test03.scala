/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test03.scala
 * @Prject: test03
 * @Package: com.fangj
 * @date: 2014年5月15日
 */
package com.fangj

import java.util.{Calendar, Date}

/**
 * @ClassName: Test03
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午3:58:44
 * @version: V1.0
 */
object Test03 {

  def main(args: Array[String]): Unit = {
    val c = Calendar.getInstance()
    c.setTime(new Date())
    Console println c.getTime().getTime()
    multiTable()
  }

  def multiTable(): Unit = {
    for {
      i <- 1 to 9
      j <- 1 to i
      ss = s"$j*$i=${j * i}\t"
    } yield {
      if (i == j) s"$ss\n" else ss
    } foreach print
  }

}