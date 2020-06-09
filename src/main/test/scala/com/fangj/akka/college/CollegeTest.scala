package com.fangj.akka.college

import org.jsoup.Jsoup

object CollegeTest {

  def main(args: Array[String]): Unit = {
    val document = Jsoup.connect("http://www.sdaxue.com/api/college_location/%E5%8C%97%E4%BA%AC/%E8%A5%BF%E5%9F%8E")
      .ignoreContentType(true)
      .header("X-Requested-With", "XMLHttpRequest")
      .get()
    System.out.println(document.body.text)
  }

}
