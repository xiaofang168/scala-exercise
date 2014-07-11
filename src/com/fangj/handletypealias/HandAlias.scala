/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: test03
 * Description: HandAlias.scala
 * created at: 2014年7月5日
 */
package com.fangj.handletypealias

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月5日 上午10:16:34
 * @version: $Rev$
 */
class HandAlias {

  type computer = (Int, Int) => Int

  val plus: computer = _ + _

  val multiply: computer = _ * _

}

object HandAlias {

  def main(args: Array[String]) {
	  val hand = new HandAlias
	  Console println hand.plus(3,2)
	  Console println hand.multiply(5,6)
  }
}