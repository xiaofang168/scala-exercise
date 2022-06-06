package com.fangj.http

import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

object DomainHttpClientUtil {

  def postJson(url: String, body: String, ddcTicket: String): String = {
    val client = HttpClients.createDefault
    val httpPost = new HttpPost(url)
    httpPost.addHeader("Content-Type", "application/json;charset=utf-8")
    httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36")
    // cookie必传
    httpPost.addHeader("Cookie", s"ddc_ticket=$ddcTicket;username=jefffang")
    httpPost.addHeader("Host", "")
    httpPost.setEntity(new StringEntity(body, "utf-8"))
    val resp = client.execute(httpPost)
    val respEntity = resp.getEntity
    EntityUtils.toString(respEntity, "UTF-8")
  }

  def get(url: String): String = {
    val client = HttpClients.createDefault
    val httpGet = new HttpGet(url)
    client.execute(httpGet)
    val resp = client.execute(httpGet)
    val respEntity = resp.getEntity
    EntityUtils.toString(respEntity, "UTF-8")
  }

}
