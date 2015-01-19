/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala_test
 * Description: TestCall.scala
 * created at: ����7:42:00
 */
package com.fangj

/**
 * currying return function
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年1月19日
 * @version: $Rev$
 */
object TestHighLevelAbstractCall {

  def a(f: Double => Double)(first: Double) = f

  def averageDamp(f: Double => Double)(x: Double) = x

  def main(args: Array[String]) {
    a(x => x + 1)(2)
    // return fun is use one param 
    a(averageDamp(x => x + 2))(2)
  }

}