/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: WorkFlowFactory.scala
 * created at: 2015年2月2日
 */
package com.fangj.workflow

import scala.io.Source
import java.io.File
import java.io.FileInputStream
import com.twitter.util.Eval

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月2日 下午1:53:52
 * @version: $Rev$
 */
object WorkFlowFactory {

  // 读取存放流程文件目录
  private def getWorkFlowFiles(path: String): Array[File] = {
    val f = new File(path)
    f.listFiles
  }

  // 读取xml文件内容
  private def readXml(file: File): String = {
    Source.fromFile(file) mkString
  }

  /**
   * 实例化流程
   */
  private def instanceWorkFlow(xml: String): WorkFlow = {
    val wf = scala.xml.XML.loadString(xml)
    val workFlowName = (wf \\ "workflow" \ "@name").text
    val nodeExecutorList: List[NodeExecutor] = (wf \ "node").map {
      node =>
        {
          val name = (node \ "@name").text
          val step = (node \ "@step").text
          val className = (node \ "@class").text
          val methodName = (node \ "@method").text
          val formName = (node \ "@form").text
          val body = node.text
          // 流程节点
          val n = Node(name, step, className, methodName, formName, body)
          // 执行流程处理
          val processText = DynamicCode.process(className, methodName)
          val processEval = (new Eval).apply[(Any*) => Boolean](s"$processText \n new ClassExecutor {}")
          // 控制
          val controlStructureText = DynamicCode.controlStructure(formName, body)
          val controlStructureEval = (new Eval).apply[(Any) => String](s"$controlStructureText \n new FlowControlStructure {}")
          NodeExecutor(n, processEval, controlStructureEval)
        }
    }.toList
    new WorkFlow(workFlowName, nodeExecutorList)
  }

  private var workFlows: Map[String, WorkFlow] = _

  /**
   * 初始化流程
   * @workFlowDir 流程文件目录路径
   */
  def init(workFlowDir: String) {
    val workFlowFiles = getWorkFlowFiles(workFlowDir)
    workFlows = workFlowFiles.filter(_.getName().endsWith(".xml")).map { e =>
      val workFlow = instanceWorkFlow(readXml(e))
      workFlow.name -> workFlow
    } toMap
  }

  /**
   * 通过流程名称获取流程对象
   */
  def get(name: String): Option[WorkFlow] = {
    workFlows.get(name)
  }

}