/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: dbproxy-services
 * Description: JsonImplicits.scala
 * created at: 2014年7月21日
 */
package com.fangj.spray

import spray.json.DefaultJsonProtocol
import spray.json._
import DefaultJsonProtocol._
import spray.json.JsonParser
/**
 * 对象json隐式转换处理
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月21日 下午1:30:47
 * @version: $Rev: 3248 $
 */
object JsonImplicits extends DefaultJsonProtocol {
  import spray.json._
  import DefaultJsonProtocol._

  implicit val impTerms = jsonFormat2(Terms)
  implicit val impAvg = jsonFormat1(Avg)
  implicit val impSum = jsonFormat1(Sum)
  implicit val impMax = jsonFormat1(Max)
  implicit val impMin = jsonFormat1(Min)
  implicit val impCount = jsonFormat1(Count)

  implicit val impSumFun = jsonFormat1(SumFun.apply)
  implicit val impAvgFun = jsonFormat1(AvgFun.apply)
  implicit val impMaxFun = jsonFormat1(MaxFun)
  implicit val impMinFun = jsonFormat1(MinFun)
  implicit val impCountFun = jsonFormat1(CountFun)

  implicit object funJsonFormat extends RootJsonFormat[Fun] {

    def write(a: Fun) = a match {
      case sumf: SumFun => sumf.toJson
      case avgf: AvgFun => avgf.toJson
      case countf: CountFun => countf.toJson
      case maxf: MaxFun => maxf.toJson
      case minf: MinFun => minf.toJson
    }
    // If you need to read, you will need something in the 
    // JSON that will tell you which subclass to use
    def read(value: JsValue) = {
      val fields = value.asJsObject.fields
      // 平均
      val avgFun = fields.get(AggFunsEnum.AVG.toString().toLowerCase()) orElse (null)
      // 求和
      val sumFun = fields.get(AggFunsEnum.SUM.toString().toLowerCase()) orElse (null)
      // 统计
      val countFun = fields.get(AggFunsEnum.COUNT.toString().toLowerCase()) orElse (null)
      // 最大值
      val maxFun = fields.get(AggFunsEnum.MAX.toString().toLowerCase()) orElse (null)
      // 最小值
      val minFun = fields.get(AggFunsEnum.MIN.toString().toLowerCase()) orElse (null)
      if (avgFun != null) {
        value.convertTo[AvgFun]
      } else if (sumFun != null) {
        value.convertTo[SumFun]
      } else if (countFun != null) {
        value.convertTo[CountFun]
      } else if (maxFun != null) {
        value.convertTo[MaxFun]
      } else if (minFun != null) {
        value.convertTo[MinFun]
      } else {
        value.convertTo[CountFun]
      }
    }
  }

  implicit val impAgg = jsonFormat3(Agg)
  implicit val impAggsSearch = jsonFormat1(AggsSearch)

}