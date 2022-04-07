package com.fangj.excel

import akka.util.ByteString.UTF_8
import com.alibaba.excel.EasyExcelFactory
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils

import java.io.File
import scala.collection.JavaConverters._

object ReadExcel {

  def main(args: Array[String]): Unit = {
    val list: java.util.List[DataBean] = EasyExcelFactory.read("/Users/didi/test_new.xlsx").head(classOf[DataBean])
      .sheet(0)
      .doReadSync()

    // 过滤掉特殊的设置
    val haveProjectResult = list.asScala
      .toList.filter(e => StringUtils.isNotBlank(e.getProject) && StringUtils.isNotBlank(e.getDailyTimer)
      && !e.getProject.replace("\u200B", "").trim.equals("")
      && !e.getProject.replace("\u200B", "").trim.equals("")
      && StringUtils.isNotBlank(e.getUrl))

    // 按项目分组
    val stringToBeans = haveProjectResult.groupBy(_.getProject)

    val settingsList = stringToBeans map {
      case (k, v) =>
        val dataBean: DataBean = v(0)
        // 算indexes
        val indexes = v.map(e =>
          InspectionIndex.builder()
            .name(e.getIndexName)
            .`type`(e.getIndexType)
            .desc(e.getIndexDesc)
            .build())

        // 算定时
        val dailyTimers = dataBean.getDailyTimer
          .substring(2)
          .split("，")
          .map(e => {
            val t = if (e.contains(";")) e.split(";") else e.split(":")
            DailyTimer.builder()
              .hour(t(0).replace("\u200B", "").trim.toInt)
              .minute(t(1).replace("\u200B", "").trim.toInt)
              .build()
          }).toList

        // 构造配置
        InspectionSettings.builder()
          .team(dataBean.getTeam)
          .biz(dataBean.getBiz)
          .project(k)
          .indexes(indexes.asJava)
          .dailyTimers(dailyTimers.asJava)
          .realname(dataBean.getUsername)
          .url(dataBean.getUrl)
          .build()
    } toList

    // 写入json文件
    FileUtils.writeStringToFile(new File("/Users/didi/inspection.json"), JSON.toJSONString(settingsList.asJava, SerializerFeature.PrettyFormat), UTF_8)
    prettyJson(settingsList.asJava)
  }

  def prettyJson(`object`: Any): Unit = {
    val json = JSON.toJSONString(`object`, SerializerFeature.PrettyFormat)
    System.out.println(json)
  }

}
