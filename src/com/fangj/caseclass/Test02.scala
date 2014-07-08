/*
 * @FileName: Test02.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.caseclass
 * @date: 2014-7-8
 */
package com.fangj.caseclass

/**
 * @ClassName: Test02
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:39:13
 * @version: V1.0
 */
object Test02 {
  def main(args: Array[String]) {
    processItem(List("apple", "ibm"))
    processItem(List("red", "blue", "white"))
    processItem(List("red", "blue", "green"))
    processItem(List("apple", "orange", "banana"))
  }

  def processItem(items: List[String]) {
    items match {
      case List("apple", "ibm") => println("apple and ibm")
      case List("red", "blue", "white") => println("red and blue and white")
      case List("red", "blue", _*) => println("red and blue ...")
      // @ 为余下的匹配项取个名，后面可以引用到
      case List("apple", "orange", others @ _*) => println("apple and orange and" + others)
    }

  }
}