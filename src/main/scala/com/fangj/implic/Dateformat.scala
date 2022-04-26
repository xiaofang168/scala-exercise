/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: Dateformat.scala
 * created at: 2014年7月11日
 */
package com.fangj.implic

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月11日 下午2:28:17
 * @version: $Rev$
 */
class RichDate(d: Date) {
  def format(s: String) = new SimpleDateFormat(s).format(d)
}

object Dateformat {

  def main(args: Array[String]): Unit = {
    implicit def toRichDate(d: Date) = new RichDate(d)
    // 方式一
    val d = new Date()
    Console println d.format("yyyy-MM-dd")
    // 方式二
    implicit def toRichDate2(d: Date) = {
      def format(s: String) = new SimpleDateFormat(s).format(d)
    }
    val d2 = new Date()
    Console println d2.format("yyyy-MM-dd HH-mm-ss")
  }

}