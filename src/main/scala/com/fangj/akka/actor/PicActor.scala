package com.fangj.akka.actor

import java.io.FileOutputStream
import java.net.URL

import akka.actor.{Actor, PoisonPill}

class PicActor extends Actor {

  def receive = {
    case url: String => {
      val flag = download(url)
      if (flag) {
        println("Actor:" + self.toString + ">>>成功下载：" + url)
      } else {
        println("Actor:" + self.toString + ">>>失败下载：" + url)
      }
      // 下载完毕 通知自己关闭
      self ! PoisonPill
    }
  }

  def download(url: String): Boolean = {
    try {
      val name = url.split("/").last
      val u: URL = new URL(url);
      val is = u.openStream();
      val os = new FileOutputStream("/Users/fangjie/jiandan/" + name);
      var bytesRead = 0;
      val buffer = new Array[Byte](8192);
      bytesRead = is.read(buffer, 0, 8192)
      while (bytesRead != -1) {
        os.write(buffer, 0, bytesRead);
        bytesRead = is.read(buffer, 0, 8192)
      }
      return true;
    } catch {
      case e: Exception => {
        e.printStackTrace()
        return false;
      }
    }
  }
}