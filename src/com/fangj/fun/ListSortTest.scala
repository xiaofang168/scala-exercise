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
  val result = (rows.sortBy(r => (r.lastName, r.firstName)))(Ordering.Tuple2(Ordering.String.reverse, Ordering.String))
  //val result = s.sortWith(compfn1(_, _))
  Console println result
  aa
  //rows.sortWith(lt)

  /* sort alphabetical and ignoring case */
  def compfn1(e1: String, e2: String) = (e1 compareToIgnoreCase e2) < 0

  /* sort alphabetical and ignoring case: alternate */
  def compfn2(e1: String, e2: String) = (e1.toLowerCase < e2.toLowerCase)

  def aa() {
    val sortItems = List("id", "age")
    val result = List(Map[String, String]("id" -> "7", "age" -> "40"),Map[String, String]("id" -> "10", "age" -> "48"), Map[String, String]("id" -> "19", "age" -> "40"))

    //类型排序
    val sortResult = result.sortBy(m => {
      val sortConditions = for (sortItem <- sortItems) yield (m(sortItem))
      sortConditions mkString

    })

    val s = "-"
    val a = result.sortWith((m1, m2) => {
      //      val test = for (
      //        sortItem <- sortItems
      //
      //      ) yield ((op: String) => if (op == "-") m1(sortItem) < m2(sortItem) else m1(sortItem) > m2(sortItem))
      //for (a <- test) yield (a("-"))
      m1("id") < m2("id")
    }) sortWith ((m1, m2) => m1("age") < m2("age"))
    Console println a

    //类型排序
    val typeSortResult = result.sortBy(m => ((m(sortItems(0))).toInt, m("age")))(Ordering.Tuple2(Ordering.Int, getOrdering("")))
    Console println typeSortResult

    val sortMap = Map("id" -> "+", "age" -> "+")

    val myOrdering: (List[Map[String, String]], Array[String]) => List[Map[String, _]] = (result, sortItems) => {
      sortItems match {
        case Array(e1) => {
          val sortResult = result.sortBy(m => m(e1).toString())
          if (sortMap(e1) == "-") sortResult.reverse else sortResult
        }
        case Array(e1, e2) => {
          result.sortBy(m => (m(e1), m(e2)))(Ordering.Tuple2(getOrderingBySortOp(sortMap(e1), getOrderingByDataType(e1)), getOrderingBySortOp(sortMap(e2), getOrderingByDataType(e2))))
        }
        case Array(e1, e2, e3) => {
          result.sortBy(m => (m(e1), m(e2), m(e3)))(Ordering.Tuple3(getOrderingBySortOp(sortMap(e1), getOrderingByDataType(e1)), getOrderingBySortOp(sortMap(e2), getOrderingByDataType(e2)), getOrderingBySortOp(sortMap(e3), getOrderingByDataType(e3))))
        }
        case Array(e1, e2, e3, e4) => {
          result.sortBy(m => (m(e1), m(e2), m(e3), m(e4)))(Ordering.Tuple4(getOrderingBySortOp(sortMap(e1), getOrderingByDataType(e1)), getOrderingBySortOp(sortMap(e2), getOrderingByDataType(e2)), getOrderingBySortOp(sortMap(e3), getOrderingByDataType(e3)), getOrderingBySortOp(sortMap(e4), getOrderingByDataType(e4))))
        }
      }

    }

    val r = myOrdering(result, sortItems.toArray)
    println("排序结果:" + r)
    // (r.lastName, r.firstName))( Ordering.Tuple2(Ordering.String.reverse, Ordering.String)

  }

  def getOrdering(sortOp: String) = {
    if (sortOp == "-") Ordering.String.reverse
    else Ordering.String
  }

  def getOrderingByDataType[T](t: T): Ordering[T] = t match {
    case s: Int => Ordering.Int.asInstanceOf[Ordering[T]]
    case s: String => Ordering.String.asInstanceOf[Ordering[T]]
  }

  def getOrderingBySortOp[T](sortOp: String, ordering: Ordering[T]) = {
    if (sortOp == "-") ordering.reverse
    else ordering
  }

}