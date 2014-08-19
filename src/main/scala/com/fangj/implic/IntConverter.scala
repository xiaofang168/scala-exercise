/*
 * @FileName: IntConverter.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.implic
 * @date: 2014-7-27
 */
package com.fangj.implic

import java.sql.Timestamp
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.GregorianCalendar

/**
 * @ClassName: IntConverter
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午2:17:49
 * @version: V1.0
 */
class IntConverter(value: Int) {
  def converter(): String = value.toString
}

class DoubleConverter(value: Int) {
  def converter(): String = value.toString
}

class StringConverter(value: String) {
  def converter(): String = value
}

class DateConverter(value: Timestamp) {
  def converter(): String = {
    val calendar = Calendar.getInstance().asInstanceOf[GregorianCalendar]
    calendar.setTimeInMillis(value.getTime())
    new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())
  }
}

object Converter {
  implicit class convertInt2String(value: Int) {
    def converter(): String = value.toString
  }
  implicit def convertString2String(value: String) = new StringConverter(value)
  implicit def convertDate2String(value: Timestamp) = new DateConverter(value)
}
object IntConverter {

  import Converter._

  implicit def converter(s: String) = s.toInt
  val map = Map[String,Any]("2" -> "66", "4" -> 5)
  val s = map("2")

  //s converter
}