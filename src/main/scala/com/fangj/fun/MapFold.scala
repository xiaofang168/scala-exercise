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
  def main(args: Array[String]): Unit = {
    val map = Map("a" -> 40, "b" -> 35, "c" -> 20, "sort" -> "+id,age-")
    Console println map.foldLeft("")((a, t) => a + t._1 + " = " + t._2 + " and ")
    Console println map.foldLeft("")((acc, kv) => acc + kv._1 + kv._2)
    val sort = (map.filter(_._1.contains("sort")) map {
      case (k, v) => {
        v.toString.split(",") map {
          case (field) => {
            val sort = if (field.toString.contains("-")) " desc" else " asc"
            field.toString.replaceAll("\\-|\\+", "") + sort
          }
        } mkString (",")
      }
    } foldLeft ("order by "))((a, b) => a + b)

    Console println sort
  }
}