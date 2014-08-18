/*
 * @FileName: BKDRHash.scala
 * @Prject:scala-exercise
 * @Package: com.fangj
 * @date: 2014-8-18
 */
package com.fangj

import scala.annotation.tailrec

/**
 * @ClassName: BKDRHash
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午7:39:56
 * @version: V1.0
 */
object BKDRHash {

  def BKDRHash(str: String): Int = {
    hash(0, str.toList)
  }

  def BKDRHash2(str: String): Int = {
    val seed = 131; // 31 131 1313 13131 131313 etc..
    var hash = 0;
    for (c <- str) {
      hash = (hash * seed) + c
    }
    hash & 0x7FFFFFFF
  }

  @tailrec
  private def hash(h: Int, chars: List[Char]): Int = {
    if (!chars.isEmpty) {
      val seed = 131
      if (chars.length > 1) {
        val ha = h * seed + chars.head
        hash(ha, chars.tail)
      } else h * seed + chars.head
    } else 0
  }

  def main(args: Array[String]) {
    val a = BKDRHash("tt")
    val b = BKDRHash2("tt")
    println(a == b)
  }
}