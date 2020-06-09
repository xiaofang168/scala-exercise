package com.fangj.akka.college

import akka.actor.{Actor, Props}
import com.alibaba.fastjson.{JSON, JSONArray}

import scala.collection.JavaConverters._
import scala.io.Source

class CrawlerActor extends Actor {

  override def receive: Receive = {
    case "start" => {
      // 读取城市文件
      val filePath = getClass.getResourceAsStream("/college_city.json")
      val json = Source.fromInputStream(filePath).mkString
      val mapTypes = JSON.parse(json).asInstanceOf[java.util.Map[String, JSONArray]]
      val map: Map[String, JSONArray] = mapTypes.asScala.toMap
      map.foreach(e => {
        val cityActor = context.actorOf(Props[CityActor]);
        cityActor ! Province(e._1, e._2)
      })
    }
  }

}
