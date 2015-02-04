/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: WorkFlow.scala
 * created at: 2015年2月2日
 */
package com.fangj.workflow

import java.io.File
import scala.io.Source
import scala.io.Codec
import com.twitter.util.Eval

/**
 * 流程动作节点
 */
protected[this] case class Node(name: String, step: String, className: String, methodName: String, formName: String, body: String)

/**
 * 节点执行者
 */
protected[this] case class NodeExecutor(node: Node, process: (Any*) => Boolean, control: Any => String, actor: Option[Any => Either[Boolean, String]])

/**
 * 执行流程动作对象
 */
case class Action(nodeExecutor: NodeExecutor, formObj: Any, params: Any*)

/**
 * 执行者对象
 */
protected[this] case class FlowActor(step: String, className: String, methodName: String, formName: String)

/**
 * 工作流程实体类
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月2日 上午10:39:07
 * @version: $Rev$
 */
protected[this] class WorkFlow(val name: String, val nodeExecutors: List[NodeExecutor]) {

  /**
   * 根据状态(步骤)返回当前动作节点
   */
  def start(step: String): NodeExecutor = {
    val findNodeExecutors = nodeExecutors.filter(step == _.node.step)
    if (findNodeExecutors.size == 0) {
      throw WorkFlowException("999", s"${name}流程未找到动作节点${step}!")
    } else if (findNodeExecutors.size > 1) {
      throw WorkFlowException("999", s"${name}流程动作节点${step}重复!")
    } else {
      findNodeExecutors(0)
    }
  }

  /**
   * 执行业务操作,返回下一个动作节点
   */
  def executor(action: Action): Option[NodeExecutor] = {

    val nodeExecutor = action.nodeExecutor
    val node = nodeExecutor.node
    val formObj = action.formObj
    val params = action.params

    val processEval = nodeExecutor.process
    // 执行流程处理
    if (processEval(params)) {
      val controlStructureEval = nodeExecutor.control
      // 获取下一个流程动作
      val nextStep = controlStructureEval(formObj)
      if (nextStep.equals("99")) None else Some(start(nextStep))
    } else {
      throw WorkFlowException("999", s"执行业务操作出错!className:${node.className},methodName:${node.methodName}")
    }

  }

}