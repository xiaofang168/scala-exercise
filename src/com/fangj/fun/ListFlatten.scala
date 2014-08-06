/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: test
 * Description: ListFlatten.scala
 * created at: 2014年8月6日
 */
package com.fangj.fun

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年8月6日 下午4:19:07
 * @version: $Rev$
 */
object ListFlatten {
  def main(args: Array[String]) {
    val list1 = List(1, 3, 5)
    val list2 = List(2, 4, 6)
    val result = List(1, 2).zipWithIndex.map {
      case (el, i) =>
        if (i == 0) list1
        else if (i == 1) list2
    }
    println(flat(result))
    val foo = List(List(1), List(2), List(3))
    println(foo.flatten)
  }

  def flat(list: List[Any]): List[Any] = list flatten {
    case i: List[Any] => flat(i)
    case e => List(e)
  }
}