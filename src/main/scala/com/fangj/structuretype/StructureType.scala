/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: StructureType.scala
 * created at: 2015年3月18日
 */
package com.fangj.structuretype

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年3月18日 下午5:11:05
 * @version: $Rev$
 */
class StructureType {
  def free(res: { def operate(): String }): String = {
    res.operate
  }
}

class A {
  def operate(): String = {
    "hello world!"
  }
}

object Test extends App {
  val a = new StructureType
  val s = a.free(new A)
  println(s)
}