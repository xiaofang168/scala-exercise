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

  @Process(paramTypes = Array[String]("com.fangj.flowservice.User", "Int", "List[Int]"))
  def update(user: User, id: Int, list: List[Int]): Int = {
    println("执行修改!")
    0
  }

  @Process(paramTypes = Array[String]("com.fangj.flowservice.User", "Int", "List[Int]"))
  def baopi(user: User, id: Int, list: List[Int]): Int = {
    println("执行报批!")
    0
  }

  @Process(paramTypes = Array[String]("com.fangj.flowservice.User", "Int", "List[Int]"))
  def shenhe(user: User, id: Int, list: List[Int]): Int = {
    println("执行审核!")
    0
  }

  @Process(paramTypes = Array[String]("com.fangj.flowservice.User", "Int", "List[Int]"))
  def end(user: User, id: Int, list: List[Int]): Int = {
    println("流程结束!")
    0
  }

  /**
   * 获取报批执行者
   */
  def getBaoPiActor(form: Form): String = {
    form.creator
  }

  def getXiuGaiActor(form: Form): String = {
    form.creator
  }

  def getShenHeActor(form: Form): String = {
    "部门经理"
  }

}