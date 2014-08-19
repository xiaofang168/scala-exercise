/*
 * @FileName: QuickSort.scala
 * @Prject:scala-exercise
 * @Package: com.fangj
 * @date: 2014-8-9
 */
package com.fangj

import scala.annotation.tailrec

/**
 * 快速算法的原理
 * @ClassName: QuickSort
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:50:50
 * @version: V1.0
 */
object QuickSort extends App {
  val oldList = List[Int](12, 40, 26, 89, 75, 44, 32, 65, 18)
  val al = oldList.sortWith(_ > _)
  val list = quickSort(oldList)
  println(list)

  def quickSort(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case head :: tail => {
      val (x1, x2) = tail.partition(list.head>);
      quickSort(x1) ::: head :: quickSort(x2)
    }
  }

  @tailrec
  def _insertSort[T](x1: List[T], x2: List[T])(p: (T, T) => Boolean): List[T] = x2 match {
    case Nil => x1
    case x2_head :: x2_tail =>
      val (x1_left, x1_right) = x1.partition(x => p(x, x2_head))
      val x1_new = (x1_left ::: (x2_head :: x1_right))
      _insertSort(x1_new, x2_tail)(p)
  }

  def insertSort[T](xs: List[T])(p: (T, T) => Boolean) = xs match {
    case Nil => Nil
    case head :: tail => _insertSort(head :: Nil, tail)(p)
  }

  // 定义比较函数
  val compare = (x1: Int, x2: Int) => x1 < x2;
  //生成测试样本
  val intList = List(3, 4, 2, 1, 8, 7, 6, 3)
  insertSort(intList)(compare)
}

