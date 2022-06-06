package com.fangj.http

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}

import scala.io.Source

object PostWithCookies {

  def main(args: Array[String]): Unit = {
    val json = Source.fromFile(this.getClass.getResource("/batch_data.json").getPath).mkString
    val array: JSONArray = JSON.parseArray(json)
    array.forEach(e => {
      val body: String = e.asInstanceOf[JSONObject].toJSONString
      val result = DomainHttpClientUtil.postJson("http://bigdata.xiaojukeji.com/report/v2/monitor/config/save", body, "233dda557f5e4904bbdcd2ddc564c765000280000")
      println(result)
    })
  }

}
