/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test01.scala
 * @Prject: test03
 * @Package: com.fangj.covariance
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.covariance

/**
 * @ClassName: Test01
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:02:44
 * @version: V1.0
 */
object Test01 {
  
  trait A[+T]
  class C[-T] 
  class X; class Y extends X
  val t:C[Y] = new C[X]
  
  def main(args: Array[String]): Unit = {
    val list: List[_] = List("2", 5)
    for (element <- list) {
      Console println element
    }
    Console println t
  }
}