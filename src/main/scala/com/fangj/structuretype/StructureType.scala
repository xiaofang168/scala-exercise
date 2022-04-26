package com.fangj.structuretype

class A {
  def operate(): String = {
    "hello world!"
  }
}

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年3月18日 下午5:11:05
 * @version: $Rev$
 */
class StructureType {

  def free(res: {def operate(): String}): String = {
    res.operate()
  }

}

object StructureType extends App {
  val a = new StructureType
  val s = a.free(new A)
  println(s"${s}>>>>")
}



