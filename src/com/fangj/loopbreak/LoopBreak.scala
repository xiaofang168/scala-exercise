/*
 * @FileName: LoopBreak.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.loopbreak
 * @date: 2014-8-9
 */
package com.fangj.loopbreak

import scala.annotation.tailrec

/**
 * @ClassName: LoopBreak
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午3:01:33
 * @version: V1.0
 */
object LoopBreak {

  /**
   * Scala对形式上严格的尾递归进行了优化，对于严格的尾递归，可以放心使用，不必担心性能问题。对于是否是严格尾递归，若不能自行判断， 可使用Scala提供的尾递归标注@scala.annotation.tailrec，这个符号除了可以标识尾递归外，更重要的是编译器会检查该函数是否真的尾递归，若不是，会导致如下编译错误
   * could not optimize @tailrec annotated method fibonacci: it contains a recursive call not in tail position
   */
  @tailrec
  def searchIndex(indexList: List[Int], startIndex: Int, endIndex: Int): List[Int] = {
    if (indexList.size >= 100) indexList // 找到!
    else if (endIndex > 100000) indexList
    else {
      // 查找 conditions: Map[String, String]
      val list = lrange(startIndex, endIndex)
      println(s"startIndex:$startIndex  endIndex:$endIndex")
      searchIndex(indexList ++ list, endIndex + 1, endIndex + 1 + (endIndex - startIndex)) // 继续找
    }
  }

  def lrange(startIndex: Int, endIndex: Int): List[Int] = {
    List(1, 2, 3, 4)
  }

  def select(): List[String] = {
    List("1", "2")
  }

  def main(args: Array[String]) {
    searchIndex(List(), 0, 10000)
  }
}