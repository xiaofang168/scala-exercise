/**
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test.scala
 * @Prject: test03
 * @Package: com.fangj.enumer
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月15日
 * @version: V1.0
 */
package com.fangj.enumer

/**
 * @ClassName: Test
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 上午11:21:19
 * @version: V1.0
 */
object Test {
  import Items._

  def doWhat(color: Value) = {
    if (color == RED) println("stop")
    else if (color == YELLOW) "hurry up"
    else "go"

  }
  def main(args: Array[String]) {
  }
}