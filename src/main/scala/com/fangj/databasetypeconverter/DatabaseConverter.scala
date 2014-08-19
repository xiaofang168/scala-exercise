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
import java.sql.Timestamp
import java.util.Calendar
import java.util.GregorianCalendar

/**
 * @ClassName: DatabaseConverter
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 上午10:14:32
 * @version: V1.0
 */

object DatabaseConverter {

  implicit class DatabaseTypeConverter(value: String) {
    def converter(t: String): String = value
    def converter(t: Double): Double = value.toDouble
    def converter(t: BigDecimal): Double = value.toDouble
    def converter(t: java.sql.Date): java.util.Date = new SimpleDateFormat("yyyy-MM-dd").parse(value)
  }

  /**String 转换为其他类型对象(用于传递参数做查询)*/
  def StringConverterObject(value: String, t: Int) = t match {
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

  /**其他类型对象转换为String(用于出库,转换json)*/
  def ObjectConverterString(value: Object, t: Int): String = t match {
    case Types.DATE => {
      val databaseObject = value.asInstanceOf[Timestamp]
      val calendar = Calendar.getInstance().asInstanceOf[GregorianCalendar]
      calendar.setTimeInMillis(databaseObject.getTime())
      new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())
    }
    case _ => value.toString()
  }

  def main(args: Array[String]) {
    val value = StringConverterObject("333", java.sql.Types.INTEGER)
    val gc = new GregorianCalendar
    val str = ObjectConverterString(new Timestamp(gc.getTime().getTime()), Types.DATE)
    println(str)
  }

}