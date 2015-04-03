/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala-exercise
 * Description: ConnectionMongo.scala
 * created at: 下午5:38:28
 */
package com.fangj.mongodb

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年3月29日
 * @version: $Rev$
 */

import com.mongodb.casbah.Imports._

object ConnectionMongo extends App {

  // 主节点
  val sa1 = new ServerAddress("54.223.176.78", 27018)
  var sa2 = new ServerAddress("172.31.1.56", 27018)
  var sa3 = new ServerAddress("172.31.1.57", 27018)
  val addresses = List(sa1, sa2, sa3)

  val mongoClient = MongoClient(addresses )
  val db = mongoClient("test")
  val coll = db("user")
  // 在复制集中优先读secondary，如果secondary访问不了的时候就从master中读
  val a = MongoDBObject("name" -> "李华", "age" -> 20)
  val b = MongoDBObject("name" -> "李丽", "age" -> 22)
  coll.insert(a)
  coll.insert(b)

  // find
  val allDocs = coll.find()
  println(allDocs)
  for (doc <- allDocs) println(doc)
}