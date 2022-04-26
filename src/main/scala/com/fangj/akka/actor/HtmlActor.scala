package com.fangj.akka.actor

import akka.actor.{Actor, Props}
import org.jsoup.Jsoup

/**
 * 解析html
 */
class HtmlActor extends Actor {

  def receive = {
    case url: String => {
      println("Actor:" + self.toString + " || 解析页面:" + url)
      val html = Jsoup.connect(url).get()
      val body = html.body()
      val navi = body.select("a.previous-comment-page").first()
      var next = navi.attr("href")
      if (next.startsWith("//")) {
        next = "http://" + next.substring(2)
      }

      // 通知 JianActor 来创建一个新的HtmlActor解析 next，如果调用自己的话就是在同一个线程中执行，会阻塞
      sender() ! next

      // download
      val comments = body.select("ol.commentlist li")
      for (i <- 0 until comments.size()) {
        val e = comments.get(i)
        if (!e.attr("id").isEmpty()) {
          val p = e.select("p")
          if (p != null) {
            val img = p.first().select("img").first()
            if (img != null) {
              var imgsrc = img.attr("src")
              if (imgsrc.startsWith("//")) {
                imgsrc = "http://" + imgsrc.substring(2)
              }
              // 一个下载一个actor处理，最大化利用CPU
              val downactor = context.actorOf(Props[PicActor]());
              downactor ! imgsrc
            }
          }
        }
      }
      println("*********解析完毕" + url)
    }
    case _ =>
  }

}