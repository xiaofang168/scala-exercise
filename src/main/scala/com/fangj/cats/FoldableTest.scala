package com.fangj.cats

import cats.implicits._

object FoldableTest extends App {
  // 字符串拼接折叠
  val a = List("a", "b", "c").foldMap(_.toString)
  println(a)
}
