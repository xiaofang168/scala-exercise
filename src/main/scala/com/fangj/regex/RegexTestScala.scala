/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: test
 * Description: RegexTestScala.scala
 * created at: 2014年8月18日
 */
package com.fangj.regex

import scala.util.matching.Regex

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年8月18日 下午3:31:26
 * @version: $Rev$
 */
object RegexTestScala extends App {
  val name = """(Mr|Mrs|Ms)\. ([A-Z][a-z]+) ([A-Z][a-z]+)"""
  val str = "233e3"
  val s = str.matches(name)
  println(s)

  val r1 = "[0-9]"
  Console println str.matches(r1)
  val pattern = "Scala".r
  val str2 = "Scala is Scalable and cool"

  println(pattern findFirstIn str2)

  val pattern3 = "(S|s)cala".r
  val str3 = "Scala is scalable and cool"

  println((pattern3 findAllIn str3).mkString(","))

  val pattern4 = "(S|s)cala".r
  val str4 = "Scala is scalable and cool"

  println(pattern4 replaceFirstIn (str4, "Java"))

  Console println """([abc])""".r.replaceAllIn("a b c d e", """'$1'""")

  println("""hello { \" world \" } \n""".replaceAll("""([{}]|\\["n])""", "'$1'"))

  val re = new Regex("([a-z])([A-Z])", "lc", "uc")
  var output = re.replaceAllIn("camelCaseShouldBeUnderBar", m =>
    m.group("lc") + "_" + m.group("uc").toLowerCase)
  println(output)

  val string = "one493two483three"
  val pattern7 = """(.*3)?(two)*(three)""".r
  pattern7.findAllIn(string).foreach(println)

  Console println pattern7.replaceAllIn(string, m => s"'${m.group(0)}'")

}