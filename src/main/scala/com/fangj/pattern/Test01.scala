/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.pattern
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.pattern

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 上午11:14:50
 * @version: V1.0
 */
object Test01 {
  def main(args: Array[String]): Unit = {
    Array(1, 2, 3) match {
      case Array(1, 2, 3) => println("ok")
    } //ok
    Array(1, 2, 3) match {
      case Array(1, _*) => println("ok")
    } // ok
    List("A", "B", "C") match {
      case List("A", _, "C") => println("ok")
      case _ => println("nothing...")
    } //ok
    val a = 100
    a match {
      case 100 => Console println "ok"
      case _ => println("nothing...")
    }
    a match {
      case _: Int => println("ok")
    }
    //变量模式通常不会单独使用，而是在多种模式组合时使用
    List("tt", 2) match {
      case List(x, 2) => println(x)
      case _ => println("nothing...")
    }

    val tree = Tree(TreeNode("root", TreeNode("left", null, null), TreeNode("right", null, null)))


    def foo(a: Any) = a match {
      case a: List[_] => println("ok");
      case _ =>
    }

    foo(List(2))
    val m = 2
    val n = 3
    val o, p = 100
    val e@b = 100
    Console println s"$o $p"

    type s = String
    val c: s = "dd"
    Console println c
  }

  //抽象节点
  trait Node

  //具体的节点实现，有两个子节点
  case class TreeNode(v: String, left: Node, right: Node) extends Node

  //Tree，构造参数是根节点
  case class Tree(root: TreeNode)
}
