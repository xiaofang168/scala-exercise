package com.fangj.http

import com.alibaba.fastjson.JSON
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import scala.io.Source

object PostWithCookies extends App {
  val url = "http:///report/v2/monitor/config/save"
  val client = HttpClients.createDefault
  val httpPost = new HttpPost(url)
  httpPost.addHeader("Content-Type", "application/json;charset=utf-8")
  httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36")
  // cookie必传
  httpPost.addHeader("Cookie", "ddc_ticket=d110571c686cc1f80489bc4ab37a0abc000280000;username=jefffang")
  httpPost.addHeader("Host", "")
  val json = Source.fromFile(this.getClass.getResource("data.json").getPath).mkString
  val jsonStr = JSON.parseObject(json).toString
  httpPost.setEntity(new StringEntity(jsonStr, "utf-8"))
  val resp = client.execute(httpPost)
  val respEntity = resp.getEntity
  val str: String = EntityUtils.toString(respEntity, "UTF-8")
  print(str)
}
