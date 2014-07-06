/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.xml
 * @date: 2014年5月16日
 */
package com.fangj.xml

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午12:57:50
 * @version: V1.0
 */
object Test01 {
  def main(args: Array[String]) {
    val foo = <foo><bar type="greet">hi</bar><bar type="count">1</bar><bar type="color">yellow</bar></foo>
    Console println foo.text
    val s = (foo \ "bar").map(_.text).mkString(" ")
    Console println s
    val a = (foo \ "bar").map(_ \ "@type")
    //a.foreach(Console println _)
    Console println "readStringxml"
    readString
  }

  def readString() {

    val fooString = """
      <foo><bar type="greet">hi</bar><bar type="count">1</bar>
      <bar type="color">yellow</bar></foo>
      """
    val fooElemFromString = scala.xml.XML.loadString(fooString)
    (fooElemFromString \ "bar").foreach(
      barList =>
        (barList \ "@type").foreach(println(_)))
  }
}