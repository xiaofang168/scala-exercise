package com.fangj.fun

import scala.language.implicitConversions
import scala.util.chaining._

object Pipe extends App {

  import scala.language.implicitConversions

  def plus1(i: Int) = i + 1

  def double(i: Int) = i * 2

  def square(i: Int) = i * i

  val x = 1.pipe(plus1).pipe(double)

  println(x)

  val x2 = 1.pipe(plus1)
    .pipe(double)
    .tap(res => println(s"DEBUG: x = $res"))
    .pipe(double)


}
