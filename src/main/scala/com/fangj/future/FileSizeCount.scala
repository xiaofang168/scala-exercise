/*
 * Copyright 2015 The Jeff CO.Ltd
 * Prject: scala-exercise
 * Description: Test04.scala
 * created at: 下午2:13:56
 */
package com.fangj.future

import java.io.File
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.regex.Pattern
import scala.Array.canBuildFrom

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年4月12日
 * @version: $Rev$
 */
object FileSizeCount {

  def main(args: Array[String]) {
    println(getFilesSize("D:/dev-tools/ext-docs-5.0/images/", "\\.(gif|png|jpg)$") / 1024)
  }

  def getFilesSize(dir: String, fileRegex: String): Long = {
    println("visit file: " + dir)
    new File(dir) match {
      case null => 0
      case cat if cat.isFile() => if (Pattern.compile(fileRegex, Pattern.CASE_INSENSITIVE).matcher(dir).find) cat.length() else 0
      case cat if cat.isDirectory() && cat.listFiles() != null =>
        val tasks = cat.listFiles().map {
          file => Future { getFilesSize(file.getAbsolutePath(), fileRegex) }
        }
        val res = Await.result(Future.sequence(tasks.toSeq), 100 minutes)
        res.sum
      case _ => 0
    }

  }

}