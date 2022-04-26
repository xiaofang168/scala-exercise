/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Email.scala
 * @Prject: test03
 * @Package: com.fangj.extrator
 * @date: 2014年5月16日
 */
package com.fangj.extrator

import scala.util.matching.Regex

/**
 * @ClassName: Email
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午2:11:25
 * @version: V1.0
 */

object Test03 {

  def aa(s: String): Unit = {
    new Regex("""(.*)@(.*)""")
      .unapplySeq(s).get match {
        case user :: domain :: Nil => println(s"$user  $domain")
        case _ => None
      }

  }
  def main(args: Array[String]): Unit = {
    aa("hbxffj@163.com")
  }
}