/*
 * Copyright © 2014 fangjie All rights reserved.
 * @Description:
 * @Title: Dsl.scala
 * @Prject: test03
 * @Package: com.fangj
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 2014年5月10日
 * @version: V1.0
 */
package com.fangj

import java.util.Date
/**
 * @ClassName: Dsl
 * @Description:
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @date: 下午3:29:06
 * @version: V1.0
 */
object Dsl {
  case class Twitter(id: Long, text: String, publishedAt: Option[java.util.Date])
  
  def main(args: Array[String]) {
   /* var twitters = Twitter(1, "hello scala", Some(new Date())) ::
      Twitter(2, "I like scala tour", None) :: Nil
      var json = ("twitters"
      -> twitters.map(
        t => ("id" -> t.id)
          ~ ("text" -> t.text)
          ~ ("published_at" -> t.publishedAt.toString())))*/

    
  }
}