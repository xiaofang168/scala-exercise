/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: TestFlow.scala
 * created at: 2015年2月3日
 */
package com.fangj

import com.fangj.workflow.WorkFlowFactory
import com.fangj.flowservice.Form
import com.fangj.workflow.Action
import com.fangj.flowservice.User
import com.fangj.flowservice.FlowService

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月3日 下午2:07:29
 * @version: $Rev$
 */
object TestFlow extends App {

  val startTime = System.currentTimeMillis()
  //初始化
  WorkFlowFactory.init(getClass.getResource("/workflow").getPath())
  val endTime = System.currentTimeMillis()
  println(s"流程初始化初始化完成${endTime - startTime}毫秒")
  val workFlow = WorkFlowFactory.get("报批")
  workFlow match {
    case Some(flow) => {
      val form = new Form()
      form.id = 2
      form.name = "test"
      val node = flow.start("0")
      // 当前状态
      // 当前处理人
      val user = new User()
      val nextNode = flow.executor(Action(node, form, user, 1, List[Int](1)))
      println(nextNode)
    }
    case None =>
  }
  
}