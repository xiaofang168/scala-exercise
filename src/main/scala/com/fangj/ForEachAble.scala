/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: ForEachAble.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

/**
 * @ClassName: ForEachAble
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午1:33:43
 * @version: V1.0
 */
trait ForEachAble[A] {
  def iterator: java.util.Iterator[A]

  def foreach(f: A => Unit) = {
    val iter = iterator
    while (iter.hasNext)
      f(iter.next)
  }
}
