/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: test
 * Description: ListSortTest.scala
 * created at: 2014年7月25日
 */
package com.fangj.fun

import scala.math.Ordering

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月25日 上午10:17:19
 * @version: $Rev$
 */
case class Row(var firstName: String, var lastName: String, var city: String)

object ListSortTest extends Application {

  var rows = List(new Row("Oscar", "1", "London"),
    new Row("Otto", "5", "Berlin"),
    new Row("Carl", "1", "Paris"),
    new Row("Hans", "7", "Dublin"),
    new Row("Hugo", "2", "Sligo"))
  //val s = List("10", "3", "8", "0", "2")
  //val result = (rows.sortBy(r => (r.lastName, r.firstName)))(Ordering.Tuple2(Ordering.String.reverse, Ordering.String))
  //val result = s.sortWith(compfn1(_, _))
  //Console println result
  aa
  //rows.sortWith(lt)

  /* sort alphabetical and ignoring case */
  def compfn1(e1: String, e2: String) = (e1 compareToIgnoreCase e2) < 0

  /* sort alphabetical and ignoring case: alternate */
  def compfn2(e1: String, e2: String) = (e1.toLowerCase < e2.toLowerCase)

  def aa() {
    val sortItems = List("id", "age")
    val result = List(Map[String, String]("id" -> "7", "age" -> "40"), Map[String, String]("id" -> "10", "age" -> "48"), Map[String, String]("id" -> "19", "age" -> "40"))

    val sortMap = Map("id" -> "+", "age" -> "+")

    val typeMap = Map[String, String]("id" -> "double", "age" -> "double", "name" -> "string")

    val myOrdering: (List[Map[String, String]], Array[String]) => List[Map[String, _]] = (result, sortItems) => {
      sortItems match {
        case Array(e1) => {
          val sortResult = typeMap(e1) match {
            case "double" => result.sortBy(m => m(e1).toDouble)
            case _ => result.sortBy(m => m(e1))
          }
          if (sortMap(e1) == "-") sortResult.reverse else sortResult
        }
        case Array(e1, e2) => {
          (typeMap(e1), typeMap(e2)) match {
            // 都为数字的情况
            case ("double", "double") => {
              (sortMap(e1), sortMap(e2)) match {
                // 都为降序的情况
                case ("-", "-") => result.sortBy(m => (m(e1).toDouble, m(e2).toDouble))(Ordering.Tuple2(Ordering.Double.reverse, Ordering.Double.reverse))
                // 都为升序的情况
                case ("+", "+") => result.sortBy(m => (m(e1).toDouble, m(e2).toDouble))(Ordering.Tuple2(Ordering.Double, Ordering.Double))
                // 前者升序,后者降序
                case ("+", "-") => result.sortBy(m => (m(e1).toDouble, m(e2).toDouble))(Ordering.Tuple2(Ordering.Double, Ordering.Double.reverse))
                // 前者降序,后者升序
                case ("-", "+") => result.sortBy(m => (m(e1).toDouble, m(e2).toDouble))(Ordering.Tuple2(Ordering.Double.reverse, Ordering.Double))
              }
            }
            // 都为字符串的情况
            case ("string", "string") => {
              (sortMap(e1), sortMap(e2)) match {
                // 都为降序的情况
                case ("-", "-") => result.sortBy(m => (m(e1), m(e2)))(Ordering.Tuple2(Ordering.String.reverse, Ordering.String.reverse))
                // 都为升序的情况
                case ("+", "+") => result.sortBy(m => (m(e1), m(e2)))(Ordering.Tuple2(Ordering.String, Ordering.String))
                // 前者升序,后者降序
                case ("+", "-") => result.sortBy(m => (m(e1), m(e2)))(Ordering.Tuple2(Ordering.String, Ordering.String.reverse))
                // 前者降序,后者升序
                case ("-", "+") => result.sortBy(m => (m(e1), m(e2)))(Ordering.Tuple2(Ordering.String.reverse, Ordering.String))
              }
            }
            // 前者为数字,后者为字符串的情况
            case ("double", "string") => {
              (sortMap(e1), sortMap(e2)) match {
                // 都为降序的情况
                case ("-", "-") => result.sortBy(m => (m(e1).toDouble, m(e2)))(Ordering.Tuple2(Ordering.Double.reverse, Ordering.String.reverse))
                // 都为升序的情况
                case ("+", "+") => result.sortBy(m => (m(e1).toDouble, m(e2)))(Ordering.Tuple2(Ordering.Double, Ordering.String))
                // 前者升序,后者降序
                case ("+", "-") => result.sortBy(m => (m(e1).toDouble, m(e2)))(Ordering.Tuple2(Ordering.Double, Ordering.String.reverse))
                // 前者降序,后者升序
                case ("-", "+") => result.sortBy(m => (m(e1).toDouble, m(e2)))(Ordering.Tuple2(Ordering.Double.reverse, Ordering.String))
              }
            }
            // 前者为字符串,后者为数字的情况
            case ("string", "double") => {
              (sortMap(e1), sortMap(e2)) match {
                // 都为降序的情况
                case ("-", "-") => result.sortBy(m => (m(e1), m(e2).toDouble))(Ordering.Tuple2(Ordering.String.reverse, Ordering.Double.reverse))
                // 前者升序,后者降序
                case ("+", "-") => result.sortBy(m => (m(e1), m(e2).toDouble))(Ordering.Tuple2(Ordering.String, Ordering.Double.reverse))
                // 前者降序,后者升序
                case ("-", "+") => result.sortBy(m => (m(e1), m(e2).toDouble))(Ordering.Tuple2(Ordering.String.reverse, Ordering.Double))
              }
            }
            case _ => result.sortBy(m => m(e1))
          }
        }

      }
    }

    val r = myOrdering(result, sortItems.toArray)
    println("排序结果:" + r)

  }

  def getOrderingBySortOp[T](sortOp: String, ordering: Ordering[String]) = {
    if (sortOp == "-") ordering.reverse
    else ordering
  }

}