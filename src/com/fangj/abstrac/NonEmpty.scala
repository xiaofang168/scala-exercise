/*
 * @FileName: NonEmpty.scala
 * @Prject:test03
 * @Package: com.fangj.abstrac
 * @date: 2014-7-4
 */
package com.fangj.abstrac

/**
 * @ClassName: NonEmpty
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:44:23
 * @version: V1.0
 */
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean = {
    if (x < elem) left.contains(x)
    else if (x > elem) right.contains(x)
    else true
  }

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left.incl(x), right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  def union(that: IntSet): IntSet = filterAcc(x => this.contains(x), that.filterAcc(x => that.contains(x), new Empty()))

  def filterAcc(p: Int => Boolean, acc: IntSet): IntSet = {
    if (p(elem)) {
      Console println ">>>"
      right.filterAcc(p, left.filterAcc(p, acc.incl(elem)))
    } else {
      Console println "????"
      right.filterAcc(p, left.filterAcc(p, acc))
    }
  }

  override def toString = "{" + left + elem + right + "}"
}