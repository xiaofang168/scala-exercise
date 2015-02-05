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
import scala.xml.Elem

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
   * 根据xml文件内容实例化流程
   */
  private def instanceWorkFlow(xml: String): WorkFlow = {
    val wf = scala.xml.XML.loadString(xml)
    val workFlowName = (wf \\ "workflow" \ "@name").text
    val nodes = getAllNode(wf)
    val flowActors = getfLowActors(nodes)
    try {
      val nodeExecutorList: List[NodeExecutor] = nodes.map {
        node =>
          {
            // 流程节点
            val n = Node(node.name, node.step, node.className, node.methodName, node.formName, node.body)
            // 执行流程处理
            val processText = DynamicCode.process(node.className, node.methodName)
            val processEval = (new Eval).apply[(Any*) => Boolean](s"$processText \n new ClassExecutor {}")
            if (node.body.isEmpty()) {
              NodeExecutor(n, processEval, None, None)
            } else {
              // 只保留跳转的节点号
              val replaceNotToRegex = """,.*\)""".r
              val body = replaceNotToRegex.replaceAllIn(node.body, ")")
              // 控制
              val controlStructureText = DynamicCode.controlStructure(node.formName, body)
              val controlStructureEval = (new Eval).apply[(Any) => String](s"$controlStructureText \n new FlowControlStructure {}")
              // 流程执行者
              val actorEvals = flowActors.get(node.step) match {
                case Some(actor) => {
                  val actorMap = actor.map {
                    case (classAndform, actor) => {
                      val actorText = DynamicCode.actor(actor.className, actor.methodName, actor.formName)
                      classAndform -> (new Eval).apply[Any => Either[Boolean, String]](s"$actorText \n new FlowActor {}")
                    }
                  }.toMap
                  Some(actorMap)
                }
                case None => None
              }
              NodeExecutor(n, processEval, Some(controlStructureEval), actorEvals)
            }
          }
      }.toList
      new WorkFlow(workFlowName, nodeExecutorList)
    } catch {
      case e: Exception => throw WorkFlowException("999", s"${workFlowName}初始化失败")
    }
  }

  // 工作流程结合
  private var workFlows: Map[String, WorkFlow] = _

  /**
   * 流程节点对应的执行者
   */
  private def getfLowActors(nodes: List[Node]): Map[String, Map[String, FlowActor]] = {
    nodes.map {
      node =>
        {
          val className = node.className
          val formName = node.className
          val body = node.body
          val step = node.step
          val fromNodeList = findNodeByToStep(step, nodes)
          val nodeActors = getNodeActor(step, fromNodeList)
          step -> nodeActors
        }
    }.toMap
  }

  /**
   * 获取流程所有节点
   */
  private def getAllNode(wf: Elem): List[Node] = {
    (wf \ "node").map {
      node =>
        {
          val name = (node \ "@name").text
          val step = (node \ "@step").text
          val className = (node \ "@class").text
          val methodName = (node \ "@method").text
          val formName = (node \ "@form").text
          val body = node.text
          Node(name, step, className, methodName, formName, body)
        }
    }.toList
  }

  /**
   * 获取节点的执行者
   */
  private def getNodeActor(step: String, fromNodeList: List[Node]): Map[String, FlowActor] = {
    val includeStepNodes = fromNodeList.filter(e => {
      val className = e.className
      val formName = e.formName
      val body = e.body
      !"".equals(body) && !"".equals(getActorMethod(step, body))
    })
    includeStepNodes.map(e => {
      val className = e.className
      val formName = e.formName
      val body = e.body
      // 获取执行者方法
      val method = getActorMethod(step, body)
      className + "+" + formName -> FlowActor(step, className, method, formName)
    }) toMap
  }

  /**
   * 通过包含跳转节点号查找节点
   */
  private def findNodeByToStep(step: String, nodeList: List[Node]): List[Node] = {
    nodeList.filter(e => {
      e.body.contains("to(\"" + step + "\"")
    })
  }

  /**
   * 根据node正文获取执行者方法名称
   */
  private def getActorMethod(step: String, body: String): String = {
    val mathToMethodRegex = """to\(.*\)""".r
    val mathToParamTegex = """to\((.*)\)""".r
    // 所有执行者方法
    val toMethodList: List[String] = mathToMethodRegex.findAllIn(body).toList
    // 去往该节点的方法
    val method: String = toMethodList.filter(_.contains("to(\"" + step + "\""))(0)
    val mathToParamTegex(paramStr) = method
    val params = paramStr.split(",")
    if (params.length > 0) params(1) else ""
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

  def main(args: Array[String]) {
    val xml: String = Source.fromFile(new File("C:\\Users\\fangjie1.HIK\\git\\scala-exercise\\src\\main\\resources\\workflow\\wf.xml")).mkString
    val wf = scala.xml.XML.loadString(xml)
    val nodeList = getAllNode(wf)
    //println(nodeList)
    val res = findNodeByToStep("1", nodeList)
    val method = getActorMethod("1", """to("1",getBaoPiActor)""")
    // println(method)
    val fromNodeList = findNodeByToStep("1", nodeList)
    println(fromNodeList)
    val s = getNodeActor("1", fromNodeList)
    println(s)
  }

}