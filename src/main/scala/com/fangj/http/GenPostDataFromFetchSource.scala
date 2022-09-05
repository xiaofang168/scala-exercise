package com.fangj.http

import java.io.{File, PrintWriter}
import java.util.Date

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.fangj.excel.ReadExcel
import com.fangj.fun.PipelineOp._
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime

import scala.io.Source
import scala.jdk.CollectionConverters._

object GenPostDataFromFetchSource {

  def main(args: Array[String]): Unit = {
    val sourceTimeMap = collectCron() ->> convert
    val sourceSet = fetch() ->> toJSONArray ->> collectSource
    val set = sourceSet.map(e => DataTmplParse.parse(e, sourceTimeMap.get(e).get))
    val jsonObjectSet: Set[JSONObject] = set.map(JSON.parseObject(_))
    val str: String = ReadExcel.getPrettyJson(jsonObjectSet.asJava)
    val writer = new PrintWriter(new File("batch_data.json"))
    writer.write(str)
    writer.close()
  }

  def fetch(): String = {
    val url = ""
    val json = Source.fromFile(this.getClass.getResource("fetch.json").getPath).mkString
    val jsonStr = JSON.parseObject(json).toString
    DomainHttpClientUtil.postJson(url, jsonStr, "c6e4eb950c5840991d13b69db788ecd2000280000")
  }

  def toJSONArray(str: String): JSONArray = {
    val nObject: JSONObject = JSON.parseObject(str)
    nObject.getJSONObject("data").getJSONObject("dsDatas").getJSONArray("nodes")
  }

  def collectSource(array: JSONArray) = {
    val sourceSet = scala.collection.mutable.Set[String]()
    array.forEach(e => {
      val jsonObject: JSONObject = e.asInstanceOf[JSONObject]
      val source: String = jsonObject.getString("table_1653635662711.datae_column_c902aa2b3b")
      if (source.contains("3080")) {
        sourceSet += source
      }
    })
    sourceSet.toSet
  }

  def collectCron(): Map[String, String] = {
    val map = scala.collection.mutable.Map[String, String]()
    val str: String = DomainHttpClientUtil.get("")
    val nObject: JSONObject = JSON.parseObject(str)
    val array: JSONArray = nObject.getJSONArray("data")
    array.forEach(e => {
      val jsonObject: JSONObject = e.asInstanceOf[JSONObject]
      val executorParam: String = jsonObject.getString("executor_param")
      val jobCron = jsonObject.getString("job_cron")
      val paramArray = executorParam.split(",")
      val paramMap = paramArray.filter(_.split("=").length > 1).map(e => {
        (e.split("=")(0), e.split("=")(1))
      }).toMap

      val pid: String = paramMap.get("pid").getOrElse("")
      val cid: String = paramMap.get("cid").getOrElse("")
      val startIndex: String = paramMap.get("startIndex").getOrElse("")
      val endIndex: String = paramMap.get("endIndex").getOrElse("")
      val overdue: String = paramMap.get("overdue").getOrElse("")
      val source: String = String.format("_%s_%s_%s_%s", pid, cid, startIndex, endIndex)
      if (StringUtils.isNotBlank(overdue)) {
        map += (source + "_" + overdue -> jobCron)
      } else {
        map += (source -> jobCron)
      }
    })
    map.toMap
  }

  def convert(cronMap: Map[String, String]): Map[String, String] = {
    cronMap map {
      case (k, v) => (k, getFirstTime(v, 40))
    }
  }

  def getFirstTime(cron: String, minutes: Int) = {
    val startDate = new DateTime(DateTime.now.millisOfDay.withMinimumValue.getMillis).toDate
    val cronExpression: CronExpression = new CronExpression(cron)
    val date: Date = cronExpression.getTimeAfter(startDate)
    new DateTime(date.getTime).plusMinutes(minutes).toString("HH:mm:ss")
  }

}
