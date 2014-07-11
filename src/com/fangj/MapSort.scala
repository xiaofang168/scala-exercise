/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: test03
 * Description: MapSort.scala
 * created at: 2014年6月26日
 */
package com.fangj

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年6月26日 下午1:30:40
 * @version: V1.0
 */
object MapSort {
  def main(args: Array[String]) {
    val map = Map("a" -> 40, "b" -> 35, "c" -> 20)
    val sortList = map.toList.sortWith((x, y) => x._2 > y._2)
    sortList.foreach((x) => println(x._1 + "-->" + x._2))
  }
}