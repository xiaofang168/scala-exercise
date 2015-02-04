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
    val flowActors = getfLowActors(xml)
    val wf = scala.xml.XML.loadString(xml)
    val workFlowName = (wf \\ "workflow" \ "@name").text
    try {
      val nodeExecutorList: List[NodeExecutor] = (wf \ "node").map {
        node =>
          {
            val name = (node \ "@name").text
            val step = (node \ "@step").text
            val className = (node \ "@class").text
            val methodName = (node \ "@method").text
            val formName = (node \ "@form").text
            // 替换非结构的正则表达式
            val replaceNotToRegex = """,.*\)""".r
            val body = replaceNotToRegex.replaceAllIn(node.text, ")")
            // 流程节点
            val n = Node(name, step, className, methodName, formName, body)
            // 执行流程处理
            val processText = DynamicCode.process(className, methodName)
            val processEval = (new Eval).apply[(Any*) => Boolean](s"$processText \n new ClassExecutor {}")
            // 控制
            val controlStructureText = DynamicCode.controlStructure(formName, body)
            val controlStructureEval = (new Eval).apply[(Any) => String](s"$controlStructureText \n new FlowControlStructure {}")
            // 流程执行者
            val actorEval = flowActors.get(step) match {
              case Some(actor) => {
                actor match {
                  case Some(actor) => {
                    val actorText = DynamicCode.actor(actor.className, actor.methodName, actor.formName)
                    Some((new Eval).apply[Any => Either[Boolean, String]](s"$actorText \n new FlowActor {}"))
                  }
                  case None => None
                }
              }
              case None => None
            }
            NodeExecutor(n, processEval, controlStructureEval, actorEval)
          }
      }.toList
      new WorkFlow(workFlowName, nodeExecutorList)
    } catch {
      case e: Exception => throw WorkFlowException("999", s"${workFlowName}初始化失败")
    }
  }

  private var workFlows: Map[String, WorkFlow] = _

  /**
   * 流程节点对应的执行者
   */
  private def getfLowActors(xml: String): Map[String, Option[FlowActor]] = {
    val wf = scala.xml.XML.loadString(xml)
    val workFlowName = (wf \\ "workflow" \ "@name").text
    (wf \ "node").map {
      node =>
        {
          val className = (node \ "@class").text
          val formName = (node \ "@form").text
          val body = node.text
          // 从body中寻找step及对应的Actor
          val actorMap = getStepAndActorMap(body)
          actorMap.map {
            case (k, v) => {
              if (!"".equals(v)) k -> Some(FlowActor(k, className, v, formName)) else k -> None
            }
          } toMap
        }
    }.reduceLeft(_ ++ _)
  }

  /**
   * get step and Actor map by parse body
   */
  private def getStepAndActorMap(body: String): Map[String, String] = {
    val mathToMethodRegex = """to\(.*\)""".r
    val mathToParamTegex = """to\((.*)\)""".r
    // 所有执行者方法
    val toMethodList = mathToMethodRegex.findAllIn(body).toList.distinct
    // 从方法中获取对应的step和actor
    toMethodList.map { e =>
      {
        val mathToParamTegex(paramStr) = e
        val params = paramStr.split(",")
        val step = params(0).replaceAll("\"", "")
        step -> (if (params.length > 1) params(1) else "")
      }
    } toMap
  }

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