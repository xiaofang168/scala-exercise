package com.fangj.excel

import com.alibaba.excel.EasyExcelFactory
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import org.apache.commons.lang3.StringUtils

import scala.jdk.CollectionConverters._

object ReadExcel {

  def main(args: Array[String]): Unit = {
    val list: java.util.List[DataBean] = EasyExcelFactory.read("/Users//test_new.xlsx").head(classOf[DataBean])
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

    settingsList.foreach(e => println(e.getProject))

    // 写入json文件
    //FileUtils.writeStringToFile(new File("/Users//inspection.json"), JSON.toJSONString(settingsList.asJava, SerializerFeature.PrettyFormat), UTF_8)
    //prettyJson(settingsList.asJava)
  }

  def prettyJson(`object`: Any): Unit = {
    val json = JSON.toJSONString(`object`, SerializerFeature.PrettyFormat)
    System.out.println(json)
  }

}
