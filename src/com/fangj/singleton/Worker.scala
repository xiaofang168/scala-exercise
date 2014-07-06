/*
 * @FileName: Worker.scala
 * @Prject:test03
 * @Package: com.fangj.singleton
 * @date: 2014-5-15
 */
package com.fangj.singleton

/**
 * @ClassName: Worker
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午8:24:30
 * @version: V1.0
 */
class Worker private {
  def work() = println("I am the only worker!")
}

object Worker {
  val worker = new Worker
  def GetWorkInstance(): Worker = {
    worker
  }
}

object Job {
  def main(args: Array[String]) {
    for (i <- 1 to 5) {
      val worker = Worker.GetWorkInstance();
      worker.work()
      Console println worker
    }
  }
}