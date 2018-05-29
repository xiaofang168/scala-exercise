package com.fangj.cats

/**
  * @author fangjie
  * @date Created in 下午3:34 18/5/29.
  */

import cats._
import cats.implicits._

case class Person(name: String)

case class Car(model: String)

object ShowObject extends App {


  3.show

  implicit val personShow = Show.show[Person](_.name)

  Console println Person("Alice").show

  implicit val carShow = Show.fromToString[Car]

  Console println Car("CR-V").show

  val s = Monoid[String].combineAll(List("a", "b", "c"))
  println(s)


}
