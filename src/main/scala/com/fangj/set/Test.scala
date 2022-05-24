/*
 * Prject: scala-exercise
 * Description: Test.scala
 * created at: 2014年8月21日
 */
package com.fangj.set

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年8月21日 下午2:51:33
 * @version: $Rev$
 */
object Test extends App {

  val set = Set(1, 3, 5)
  val set2 = Set(2, 3, 6)
  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
  val list2 = List(3, 6)
  list.intersect(list2)
  // 交集
  Console println set & set2
  Console println set.intersect(set2)

  // 并集
  Set(1, 2, 3) ++ Set(2, 4)
  Set(1, 2, 3) | Set(2, 4) // |方法等同于union方法
  Set(1, 2, 3) union Set(2, 4)

  // 差集
  Set(1, 2, 3) -- Set(2, 4) //得到 Set(1,3)
  Set(1, 2, 3) &~ Set(2, 4)
  Set(1, 2, 3) diff Set(2, 4)

}