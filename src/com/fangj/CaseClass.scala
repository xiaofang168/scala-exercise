/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: CaseClass.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: CaseClass
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午1:48:26
 * @version: V1.0
 */
abstract class Expr

case class FibonacciExpr(n: Int) extends Expr {
  require(n >= 0)
}

case class SumExpr(a: Expr, b: Expr) extends Expr

object CaseClass {
  def value(in: Expr): Int = in match {
    case FibonacciExpr(0) => 0
    case FibonacciExpr(1) => 1
    case FibonacciExpr(n) if (n>1) =>
      value(SumExpr(FibonacciExpr(n - 1), FibonacciExpr(n - 2)))
    case SumExpr(a, b) => value(a) + value(b)
    case _ => 0
  }
  def main(args: Array[String]) {
    var s =  FibonacciExpr(2)
    Console println s.n
    println(value(FibonacciExpr(9)))
  }
}