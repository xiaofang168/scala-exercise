/*
 * @FileName: IntSet.scala
 * @Prject:test03
 * @Package: com.fangj.abstrac
 * @date: 2014-7-4
 */
package com.fangj.abstrac

/**
 * @ClassName: IntSet
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:34:34
 * @version: V1.0
 */
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet

  /**
   * This method takes a predicate and returns a subset of all the elements
   * in the original set for which the predicate is true.
   *
   * Question: Can we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
  def filter(p: Int => Boolean): IntSet = filterAcc(p, new Empty())

  /**
   * This is a helper method for `filter` that propagetes the accumulated tweets.
   */
  def filterAcc(p: Int => Boolean, acc: IntSet): IntSet
}