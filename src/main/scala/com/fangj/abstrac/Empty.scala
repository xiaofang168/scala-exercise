/*
 * @FileName: Empty.scala
 * @Prject:test03
 * @Package: com.fangj.abstrac
 * @date: 2014-7-4
 */
package com.fangj.abstrac

/**
 * @ClassName: Empty
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:39:19
 * @version: V1.0
 */
class Empty extends IntSet {

  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  def union(other: IntSet): IntSet = other
  def filterAcc(p: Int => Boolean, acc: IntSet): IntSet = acc
  override def toString = "."

}