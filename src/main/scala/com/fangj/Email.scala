/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Email.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

import scala.util.matching.Regex

/**
 * @ClassName: Email
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:13:48
 * @version: V1.0
 */
object Email {
  def unapply(str: String) = new Regex("""(.*)@(.*)""")
    .unapplySeq(str).get match {
      case user :: domain :: Nil => Some(user, domain)
      case _ => None
    }

  def main(args: Array[String]): Unit = {
    "hbxffj@163.com" match {
      case Email(user, domain) => println(user + "@" + domain)
    }
  }
}