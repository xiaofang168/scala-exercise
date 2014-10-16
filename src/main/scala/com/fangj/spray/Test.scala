/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: test
 * Description: Test1015.scala
 * created at: 2014年10月15日
 */
package com.fangj.spray

import spray.json._
import DefaultJsonProtocol._
import spray.json.JsonParser

object AggFunsEnum extends Enumeration {
  val COUNT, SUM, AVG, MAX, MIN = Value
}

/**聚合查询选择项模式类*/
case class Terms(fields: List[String], sort: Map[String, String])
// 函数接口
trait Fun {
  def getName: AggFunsEnum.Value
  def getField: String
}
// 聚合函数模式类
case class Avg(field: String)
case class Sum(field: String)
case class Count(field: String)
case class Max(field: String)
case class Min(field: String)

// 函数多态
case class CountFun(count: Count) extends Fun {
  def getName = AggFunsEnum.COUNT
  def getField = count.field
}
case class MaxFun(max: Max) extends Fun {
  def getName = AggFunsEnum.MAX
  def getField = max.field
}
case class MinFun(min: Min) extends Fun {
  def getName = AggFunsEnum.MIN
  def getField = min.field
}
case class AvgFun(avg: Avg) extends Fun {
  def getName = AggFunsEnum.AVG
  def getField = avg.field
}
case class SumFun(sum: Sum) extends Fun {
  def getName = AggFunsEnum.SUM
  def getField = sum.field
}

/**聚集对象模式类*/
case class Agg(filter: Option[Map[String, String]], terms: Option[Terms], funs: Map[String, Fun])
/**聚合查询模式类*/
case class AggsSearch(aggs: Map[String, Agg])

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年10月15日 下午5:09:35
 * @version: $Rev$
 */
object Test extends App {
  import JsonImplicits._
  val source = """|{
				  | "aggs" : {
				  |   "group_by_gender" : {
				  |    	  "filter":{
				  |	            "id":"1560"
				  |   	   },
				  |       "terms" : {
				  |          "fields" : ["gender","country"],
				  |          "sort" : { "avg_height" : "desc","count_id":"asc" }
				  |        },
				  |        "funs" : {
				  |           "avg_height" : { "avg" : { "field" : "height" } },
    			  |		      "count_id" : { "count" : { "field" : "id" } }
				  |        }
				  |    }
				  |  }
				  |}""".stripMargin
  val jsonAst = source.parseJson // or
  val aggSearch = jsonAst.convertTo[AggsSearch]
  println(aggSearch)
}