/*
 * @FileName: QuickSort.scala
 * @Prject:scala-exercise
 * @Package: com.fangj
 * @date: 2014-8-9
 */
package com.fangj

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
}