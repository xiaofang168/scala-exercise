package com.fangj.akka.college

import akka.actor.{Actor, Props}
import com.alibaba.fastjson.JSONArray

import scala.jdk.CollectionConverters._

case class Province(name: String, cities: JSONArray)

class CityActor extends Actor {

  override def receive: Receive = {
    case province: Province => {
      if (province.cities.size() == 0) {
        // 开启一个新的Actor进行处理
        val apiParseActor = context.actorOf(Props[ApiParseActor]())
        apiParseActor ! (province.name, "")
      } else {
        province.cities.asScala.foreach(city => {
          val apiParseActor = context.actorOf(Props[ApiParseActor]())
          apiParseActor ! (province.name, city)
        })
      }
    }
    case _ => println("unknown")
  }

}
