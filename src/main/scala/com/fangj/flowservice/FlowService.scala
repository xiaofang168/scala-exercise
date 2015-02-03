/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: FlowService.scala
 * created at: 2015年2月2日
 */
package com.fangj.flowservice

import com.fangj.workflow.annotation.Process

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月2日 上午11:39:30
 * @version: $Rev$
 */
class FlowService {

  @Process(paramTypes = Array[String]("com.fangj.flowservice.User", "Int", "List[Int]"))
  def save(user: User, id: Int, list: List[Int]): Int = {
    println("保存用户!")
    0
  }

}