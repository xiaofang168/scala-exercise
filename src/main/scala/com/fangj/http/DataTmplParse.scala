package com.fangj.http

import com.google.common.collect.Maps
import com.hubspot.jinjava.Jinjava

import java.util
import scala.io.Source

object DataTmplParse {

  def main(args: Array[String]): Unit = {
    val str: String = parse("d_2001_12_28", "09:50:00")
    println(str)
  }

  def parse(source: String, time: String): String = {
    val jinjava = new Jinjava()
    val context: util.Map[String, String] = Maps.newHashMap[String, String]
    context.put("source", source)
    context.put("time", time)
    val template = Source.fromFile(this.getClass.getResource("data_tmpl.json").getPath).mkString
    jinjava.render(template, context)
  }

}
