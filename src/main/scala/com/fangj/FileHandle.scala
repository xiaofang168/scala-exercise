package com.fangj

import scala.io.Source

import spray.json._
import DefaultJsonProtocol._
import java.nio.file.{ Paths, Files }
import java.nio.charset.StandardCharsets

object FileHandle extends App {

  val is = getClass().getResourceAsStream("/weather_area_grid.txt");
  val datas = Source.fromInputStream(is)("UTF-8").mkString
  val newDatas = datas.split("\n") map (data => data.split(" ")) map (data => data(3) -> Map("y" -> data(4), "x" -> data(5)))
  val jsonObj = newDatas.toMap.toJson

  Files.write(Paths.get("E:/area.json"), jsonObj.toString().getBytes(StandardCharsets.UTF_8))

}