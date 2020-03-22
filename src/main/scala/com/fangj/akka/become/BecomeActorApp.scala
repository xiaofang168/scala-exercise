package com.fangj.akka.become

import akka.actor.{Actor, ActorSystem, Props}

case class A()

case class B()

class BecomeActor extends Actor {

  import context._

  var count = 0

  override def receive: Receive = {
    case A =>
      println("Hi A!!!")

      count = count + 1

      // receive在收到B消息后就会直接进入become内
      self ! B

      become {
        // 把处理代码切换成become内的逻辑，become之前的代码在become出现后就不再存在了
        case B =>
          println("Hi B!!!")
          count = count + 1
          println(count)
          Thread.sleep(1000)
          self ! A
          // 把代码处理逻辑切换一次，把become代码从堆栈中弹出
          unbecome()
      }

      println(count)

      if (count >= 10) context.stop(self)
  }

}

object BecomeActorApp {

  def main(args: Array[String]) {

    val system = ActorSystem("systemActor")

    val becomeActor = system.actorOf(Props[BecomeActor])

    becomeActor ! A

    Thread.sleep(20000)

    system.terminate()
  }

}


