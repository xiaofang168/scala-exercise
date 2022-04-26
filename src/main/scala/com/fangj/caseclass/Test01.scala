/*
 * @FileName: Test01.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.caseclass
 * @date: 2014-7-8
 */
package com.fangj.caseclass

/**
 * @ClassName: Test01
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:35:49
 * @version: V1.0
 */
case class A(id: Int, name: String)

object Test01 {

  def main(args: Array[String]): Unit = {
    val k = A(2, "123nnn")
    // @ 为余下的匹配项取个名，后面可以引用到
    // 这里k和myUser是一样的
    //所以myUser是代表了@后面的那一串东西

    k match {
      case myUser@A(id, name) => println(myUser.id)
    }

    def what(d: Drawing) = d match {
      case Point(_, _) => "點"
      case Cylinder(_, _) => "柱"
      case Circle(_, _) => "圆"
    }

    def what2(d: Drawing) = (d: @unchecked) match {
      case Point(_, _) => "點"
      case Cylinder(_, _) => "柱"
      case Circle(_, _) => "圆"
    }

  }
}