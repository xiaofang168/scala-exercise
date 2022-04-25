package com.fangj.http


import io.circe.generic.auto._
import io.circe.syntax._
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


object DataCommitApp {

  val batchData = CommitBatchData("2022-04-21", SourceData(1000, "20:10"),
    List(SourceData(100, "21:10"), SourceData(200, "21:20"),
      SourceData(250, "21:20"), SourceData(100, "21:30")),
    SourceData(350, "22:10"), "172.30.176.9")

  def main(args: Array[String]): Unit = {
    // 入催
    commit(CommitData("dept_collection_rc", BizExt(batchData.rc.count).asJson.noSpaces, DateTime.parse(batchData.day + " " + batchData.rc.time + ":00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis, batchData.ip))

    // 打包
    val packageData = batchData.packages.map(e => CommitData("dept_collection_package", BizExt(e.count).asJson.noSpaces,
      DateTime.parse(batchData.day + " " + e.time + ":00",
        DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis, batchData.ip))
    packageData.foreach(commit(_))

    // 分案
    commit(CommitData("dept_collection_fa", BizExt(batchData.fa.count).asJson.noSpaces, DateTime.parse(batchData.day + " " + batchData.fa.time + ":00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis, batchData.ip))
  }

  def commit(data: CommitData): Unit = {
    val result = doPost("http://10.161.179.23:8083/data/commit", data.asJson.noSpaces)
    println(result)
  }

  def doPost(url: String, json: String): String = {
    val client = HttpClients.createDefault
    val httpPost = new HttpPost(url)
    httpPost.addHeader("Content-Type", "application/json;charset=utf-8")
    httpPost.setEntity(new StringEntity(json, "utf-8"))
    val resp = client.execute(httpPost)
    val respEntity = resp.getEntity
    EntityUtils.toString(respEntity, "UTF-8")
  }

}
