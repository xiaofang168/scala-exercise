package com.fangj.csv

import java.io.{File, PrintWriter}

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializeConfig

import scala.collection.JavaConverters._
import scala.io.Source


object ReadCsvFile {

  case class College(id: Long, name: String)

  def main(args: Array[String]): Unit = {
    val bufferedSource = Source.fromFile(s"${this.getClass.getResource(".").getPath}/college_csv.csv")
    val list: List[College] = bufferedSource.getLines.filter(_.split(",").length != 1)
      .map(line => {
        val cols = line.split(",")
        College(cols(0).toLong, cols(1))
      })
      .toList
    bufferedSource.close()
    val a = List(new College(1, "1"), new College(2, "2"))
    val b = list.asJava
    val jsonStr = JSON.toJSONString(b, new SerializeConfig(true))
    val writer = new PrintWriter(new File("college.json"))
    writer.write(jsonStr)
    writer.close()
    print(list)
  }

}
