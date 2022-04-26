package com.fangj.akka.college

import akka.actor.{ActorSystem, Props}

/**
 * 大学抓取入口
 */
object StartActor {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("ActorSystem")
    val actor = system.actorOf(Props[CrawlerActor](), name = "startActor")
    actor ! "start"
  }

}
