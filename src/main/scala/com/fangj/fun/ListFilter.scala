/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: ListFilter.scala
 * created at: 2014年7月17日
 */
package com.fangj.fun

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月17日 下午2:13:56
 * @version: $Rev$
 */

case class Display(include: List[String] = List("*"), exclude: List[String] = null, additionalFields: Map[String, String] = null)

object ListFilter {
  def main(args: Array[String]): Unit = {
    // 获取查询列
    val list = List("id", "name", "age", "sex")
    val display = new Display(List("*"), List("name", "age"), Map())
    val result = if (display.exclude == null) display.include else list.filterNot(display.exclude.contains(_))
    Console println result

    val unionList = list.concat(List("id"))
    Console println unionList

    // 连接查询字段(as)
    val joinSelectField: Map[String, String] => Iterable[String] = for ((k: String, v: String) <- _) yield (v + " as " + k)
    // 添加的额外的查询字段数组
    val additionalSelectFields = joinSelectField(display.additionalFields)
    Console println additionalSelectFields

    getFirstAndLast(List[String]("2", "3", "5", "6"))
  }

  def getFirstAndLast(list: List[_]) = list match {

    case l1 :: l2 => println(s"${l1}>>>${l2}")
    case _ => println("not found")
  }
}