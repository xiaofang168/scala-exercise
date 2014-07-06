/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Rational.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014-3-25
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: Rational
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午6:07:21
 * @version: V1.0
 */
class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  Console println ">>>"
  def this(n: Int) = this(n, 1)
  override def toString = numer + "/" + denom
  def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
}