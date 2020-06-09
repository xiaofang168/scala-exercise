package com.fangj.akka.college

import java.io.PrintWriter

import akka.actor.Actor
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

/**
 * 接口解析节点
 */
class ApiParseActor extends Actor {

  override def receive: Receive = {
    case (city: String, area: String) => {
      val document = Jsoup.connect(s"http://www.sdaxue.com/api/college_location/$city/$area")
        .ignoreContentType(true)
        .header("X-Requested-With", "XMLHttpRequest")
        .get()
      // 保存数据
      val text = StringEscapeUtils.unescapeJava(document.body().text())
      val writer = new PrintWriter(new java.io.File(s"/Users/fangjie/college1/$city-$area.json"))
      writer.write(text)
      writer.close()
    }
    case _ =>
  }

}

