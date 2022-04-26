package com.fangj.akka.actor

import akka.actor.{ActorSystem, Props}

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("ActorSystem")
    val actor = system.actorOf(Props[JiandanActor](), name = "MyActor")
    actor ! "start"
  }
}