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
  val mongoClient = MongoClient("localhost", 27023)
  val db = mongoClient("test")
  db.collectionNames
  val coll = db("test")
  val a = MongoDBObject("name" -> "胡锦涛")
  val b = MongoDBObject("name" -> "温家宝")
  coll.insert(a)
  coll.insert(b)
}