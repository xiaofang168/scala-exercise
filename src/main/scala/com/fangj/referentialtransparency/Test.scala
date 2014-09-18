/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: Test.scala
 * created at: 2014年9月17日
 */
package com.fangj.referentialtransparency

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年9月17日 下午4:26:50
 * @version: $Rev$
 */
object Test extends App {
  def read = new IOAction[String](">>")

  def write(str: String) = new IOAction[Unit](println(str))

  read(new State)
  Console println read.apply(new State)
 
  write("<<<<").apply(new State)
  write("<<<<")(new State)
}