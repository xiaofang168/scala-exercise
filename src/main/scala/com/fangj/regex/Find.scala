/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: Find.scala
 * created at: 2015年2月4日
 */
package com.fangj.regex

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月4日 上午10:55:22
 * @version: $Rev$
 */
object Find extends App {
  val regex = """to\(.*\)""".r
  val str = """if(form.id>20){
  to("99")
}else{
  to("3",getXiuGaiActor)
}"""
  val res = regex.findAllIn(str).toList
  val Regex2 = """,.*\)""".r
  val tt = """to("99","dd")"""
  val zz = """if(form.id>20){
  to("99")
}else{
  to("3",getXiuGaiActor)
}"""

 val y = Regex2.replaceAllIn(zz, ")")
println(y)




}