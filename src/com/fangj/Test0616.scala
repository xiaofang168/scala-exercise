/*
 * @FileName: Test0616.scala
 * @Prject:test03
 * @Package: com.fangj
 * @date: 2014-6-16
 */
package com.fangj

/**
 * @ClassName: Test0616
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午10:22:14
 * @version: V1.0
 */
object Test0616 {
  def main(args: Array[String]) {
    // Make a list via the companion object factory
    val days = List("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    // Make a list element-by-element
    val when = "AM" :: "PM" :: List()
    
    // Pattern match
    days match {
      case firstDay :: otherDays =>
        println("The first day of the week is: " + firstDay)
      case List() =>
        println("There don't seem to be any week days.")
    }
  }
}