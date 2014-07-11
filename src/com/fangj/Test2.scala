/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test2.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: Test2
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午1:34:49
 * @version: V1.0
 */
object Test2 {
  implicit def aa(n: Int) = new { def next = n + 1 }
  def main(args: Array[String]) {
    val list = new java.util.ArrayList[Int]() with ForEachAble[Int] with JsonAble
    list.add(1); list.add(2)
    list.foreach(println(_))
    //println("Json: " + list.toJson)
    Console println 2.next

    def getProperty(name: String): Option[String] = {
      val value = System.getProperty(name)
      if (value != null) Some(value) else None
    }

    val osName = getProperty("os.name")

    osName match {
      case Some(value) => value
      case _ => "none"
    }
    osName.foreach(print _)
  }
}