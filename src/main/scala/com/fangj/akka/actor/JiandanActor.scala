package com.fangj.akka.actor

import akka.actor.{Actor, Props}

class JiandanActor extends Actor {


  override def receive = {
    case "start" => {
      val html = context.actorOf(Props[HtmlActor](), "html");
      html ! "http://jandan.net/ooxx"
    }

    // 解析html
    case url: String => {
      // 开启一个新的Actor进行处理
      val html = context.actorOf(Props[HtmlActor]());
      html ! url
    }
  }
}