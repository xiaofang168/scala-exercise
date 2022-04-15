package com.fangj.cats

import cats.Functor
import cats.instances.list._

object FunctorTest extends App {


  // for Functor
  // for Functor

  val list1 = List(1, 2, 3)

  // list1: List[Int] = List(1, 2, 3)

  val list2 = Functor[List].map(list1)(_ * 2)

  val strings: List[String] = Functor[List].as(list1, "As")

}
