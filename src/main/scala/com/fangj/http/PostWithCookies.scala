package com.fangj.http

import com.alibaba.fastjson.{JSON, JSONArray}
import io.circe.Json
import io.circe.parser._

import scala.io.Source

object PostWithCookies {

  def main(args: Array[String]): Unit = {
    val json = Source.fromFile(this.getClass.getResource("/batch_data.json").getPath).mkString
    // circe用法
    val parseResult = parse(json).getOrElse(Json.Null)
    val size: Int = parseResult.hcursor.values.get.size
    println(s"数组大小：$size")
    parseResult.hcursor.values.get.foreach(e => {
      val body = e.hcursor.top.get.noSpaces
      val result = DomainHttpClientUtil.postJson("http://..com/report/v2/monitor/config/save", body, "c6e4eb950c5840991d13b69db788ecd2000280000")
      println(result)
      //println(e.hcursor.downField("dispatchTime").as[String].getOrElse(">>>"))
    })
    // fastjson用法
    val array: JSONArray = JSON.parseArray(json)
    println("数组大小：" + array.size())
    //    val array: JSONArray = JSON.parseArray(json)
    //    array.forEach(e => {
    //      val body: String = e.asInstanceOf[JSONObject].toJSONString
    //      val result = DomainHttpClientUtil.postJson("http://..com/report/v2/monitor/config/save", body, "c6e4eb950c5840991d13b69db788ecd2000280000")
    //      println(result)
    //    })
  }

}
