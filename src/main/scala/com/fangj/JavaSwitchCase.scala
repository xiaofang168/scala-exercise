/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: JavaSwitchCase.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: JavaSwitchCase
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午1:46:02
 * @version: V1.0
 */
object JavaSwitchCase {
  def fibonacci(in: Any): Int = in match {
    case 0 => 0
    case 1 => 1
    case n: Int if (n > 1) => fibonacci(n - 1) + fibonacci(n - 2)
    case _ => 0
  }
  def main(args: Array[String]): Unit = {
    println(fibonacci(3))
  }
}
