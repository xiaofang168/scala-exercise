/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test.scala
 * @Prject: test03
 * @Package: com.fangj.mapreduce
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月15日
 * @version: V1.0
 */
package com.fangj.mapreduce

/**
 * @ClassName: Test
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 上午10:25:34
 * @version: V1.0
 */
object Test {
  def main(args: Array[String]) {
    val foo = 1 to 5 toList
    val add1 = (x: Int) => x + 1
    val add100 = (x: Int) => x + 100
    val sq = (x: Int) => x * x //List(1, 4, 9, 16, 25)
    val add1List = foo map add1
    //sq(add1(x)) 
    val sqComposeAdd1 = sq compose add1
    foo map sqComposeAdd1
    foo map add1 map sq map add100
    val asq = foo map (add100 compose sq compose add1)
    //List(2, 3,4, 5, 6)
    //List(4, 9, 16, 25, 36)
    //asq.foreach(println(_))
    val fncs = List(add100, sq, add1)
    //add1(sq(x))
    val f = foo map (fncs reduce (_ compose _))
    f.foreach(println(_))
    foo map (add1 andThen sq andThen add100)
    foo map (fncs reduce (_ andThen _))

  }

}