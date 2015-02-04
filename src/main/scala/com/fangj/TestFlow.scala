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
  val workFlow = WorkFlowFactory.get("报批流程")
  workFlow match {
    case Some(flow) => {
      val form = new Form()
      form.id = 28
      form.name = "test"
      form.creator = "张三"
      val node0 = flow.start("0")
      // 当前状态
      // 当前处理人
      val user = new User()
      val node1 = flow.executor(Action(node0, form, user, 1, List[Int](1)))
      node1 match {
        case Some(nextNode) => {
          nextNode.actor match {
            case Some(actor) => {
              val persons = actor(form)
              println(s"下一步流程:${nextNode.node.name},执行者:$persons")
            }
            case None => println("流程未开始!")
          }
          // 进入下一个流程
          val node2 = flow.start(nextNode.node.step)
          val node3 = flow.executor(Action(node2, form, user, 1, List[Int](1)))
          node3 match {
            case Some(nextNode) => {
              nextNode.actor match {
                case Some(actor) => {
                  val persons = actor(form)
                  println(s"下一步流程:${nextNode.node.name},执行者:$persons")
                }
                case None => println("流程未开始!")
              }
              // 进入下一个流程
              val node4 = flow.start(nextNode.node.step)
              val node5 = flow.executor(Action(node4, form, user, 1, List[Int](1)))
              node5 match {
                case Some(nextNode) => {
                  nextNode.actor match {
                    case Some(actor) => {
                      val persons = actor(form)
                      println(s"下一步流程:${nextNode.node.name},执行者:$persons")
                    }
                    case None => println("流程未开始!")
                  }
                }
                case None => println("流程结束!")
              }

            }
            case None => println("流程结束!")
          }

        }
        case None => println("流程结束!")
      }

    }
    case None =>
  }

}