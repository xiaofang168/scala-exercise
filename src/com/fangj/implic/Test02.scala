/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Test02.scala
 * @Prject: test03
 * @Package: com.fangj.implic
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月14日
 * @version: V1.0
 */
package com.fangj.implic

/**
 * @ClassName: Test02
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:47:59
 * @version: V1.0
 */
object Test02 {
  //A =:= B  //表示A类型等同于B类型
  //A <:< B  //表示A类型是B类型的子类型
  def test[T](i: T)(implicit ev: T <:< java.io.Serializable) { print("OK") }
  def main(args: Array[String]) {
    test("22")
  }
}