/*
 * @FileName: LoopBreak.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.loopbreak
 * @date: 2014-8-9
 */
package com.fangj.loopbreak

/**
 * @ClassName: LoopBreak
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午3:01:33
 * @version: V1.0
 */
object LoopBreak {

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