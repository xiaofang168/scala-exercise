package com.fangj.fs2

import fs2.Stream

object Example {

  def main(args: Array[String]): Unit = {
    val s1a = Stream(1, 2, 3) // variadic
    val s1 = Stream.emit(1)
    val list: List[Int] = s1.toList
    println(list)
  }

}
