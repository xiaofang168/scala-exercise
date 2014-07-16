/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: MapFold.scala
 * created at: 2014年7月16日
 */
package com.fangj.fun

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月16日 下午2:48:47
 * @version: $Rev$
 */
object MapFold {
  def main(args: Array[String]) {
    val map = Map("a" -> 40, "b" -> 35, "c" -> 20)
    Console println map.foldLeft("")((a, t) => a + t._1 + " = " + t._2 + " and ")
    Console println map.foldLeft("")((acc, kv) => acc + kv._1 + kv._2)
  }
}