/*
 * @FileName: DatabaseConverter.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.databasetypeconverter
 * @date: 2014-7-27
 */
package com.fangj.databasetypeconverter

import java.sql.Types
import java.sql.Date
import java.util.Date
import java.text.SimpleDateFormat

/**
 * @ClassName: DatabaseConverter
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 上午10:14:32
 * @version: V1.0
 */

object DatabaseConverter extends Application {

  implicit class DatabaseTypeConverter(value: String) {

    def converter(t: String): String = {
      value
    }

    def converter(t: Double): Double = {
      value.toDouble
    }

    def converter(t: BigDecimal): Double = {
      value.toDouble
    }

    def converter(t: java.sql.Date): java.util.Date = {
      new SimpleDateFormat("yyyy-MM-dd").parse(value)
    }
  }

  def valueConverter(value: String, t: Int) = t match {
    // 数据库字符类型转换为java String类型
    case Types.VARCHAR => value.toString()
    // 数据INTEGER类型转换为java Integer类型
    case Types.INTEGER => value.toInt
    // 数据库date类型转换为java date 类型
    case Types.DATE => {
      new SimpleDateFormat("yyyy-MM-dd").parse(value)
    }
    // 数据库double 类型 转换为 java double 类型
    case Types.DOUBLE => value.toDouble

  }

  val value = valueConverter("333", java.sql.Types.INTEGER)
  println(value)
}