package com.fangj.akka.persistence

import java.util.concurrent.{ExecutorService, Executors}

import akka.actor.{ActorSystem, Props}
import com.fangj.akka.persistence.IdProvider._

object PersistentActorApp {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("demo")
    val actor = system.actorOf(Props[IdProvider], "IdProviderActor")
    val pool: ExecutorService = Executors.newFixedThreadPool(10)
    for (elem <- (1 to 100)) {
      actor ! GenerateId
    }
    Thread.sleep(5000)
    pool.shutdown()
    system.terminate()
  }

}
