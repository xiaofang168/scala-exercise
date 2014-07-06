/*
 * @FileName: Insets.scala
 * @Prject:test03
 * @Package: com.fangj.abstrac
 * @date: 2014-7-4
 */
package com.fangj.abstrac

/**
 * @ClassName: Insets
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午10:15:32
 * @version: V1.0
 */
object Insets extends App {

  val t1 = new NonEmpty(3, new Empty, new Empty)
  val t2 = new NonEmpty(5, new Empty, new Empty)
  val t3 = new NonEmpty(7, t2, new Empty)
  val t4 = new NonEmpty(4, new Empty, new Empty)
  
  println(t1)
  Console println t1.incl(4)
  Console println t1.union(t3)

}