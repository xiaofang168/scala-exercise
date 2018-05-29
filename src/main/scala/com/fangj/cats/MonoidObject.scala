package com.fangj.cats

/**
  * @author fangjie
  * @date Created in 下午5:54 18/5/29.
  */

import cats._
import cats.implicits._

object MonoidObject extends App {

  val s = Monoid[String].combineAll(List("a", "b", "c"))
  println(s)

}
