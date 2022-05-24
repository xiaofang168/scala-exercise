/*
 * Prject: scala-exercise
 * Description: DynamicCode.scala
 * created at: 2015年2月2日
 */
package com.fangj.workflow

import com.fangj.workflow.annotation.Process

/**
 * 动态逻辑code
 *
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月2日 下午5:11:20
 * @version: $Rev$
 */
protected[this] object DynamicCode {

  /**
   * 调用流程类code
   */
  def process(className: String, methodName: String): String = {

    s"""|trait ClassExecutor extends ((Any*) => Boolean) {
    	|
      	| def apply(params: Any*): Boolean = {
		|      try {
    	|		   val clazz = Class.forName("$className")
		|		   val flowService = clazz.newInstance().asInstanceOf[$className]
		|		   ${
      val clazz = Class.forName(className)
      val flowService = clazz.newInstance()
      val types = getMethodParamTypes(flowService, methodName)
      // 暂时支持三个参数
      if (types.length == 0) {
        s"flowService.$methodName()"
      } else if (types.length == 1) {
        s"flowService.$methodName(params(0).asInstanceOf[${types(0)}])"
      } else if (types.length == 2) {
        s"flowService.$methodName(params(0).asInstanceOf[${types(0)}],params(1).asInstanceOf[${types(1)}])"
      } else if (types.length == 3) {
        s"flowService.$methodName(params(0).asInstanceOf[${types(0)}],params(1).asInstanceOf[${types(1)}],params(2).asInstanceOf[${types(2)}])"
      }
    }
    	|			true
		|      } catch {
		|         case e: Exception => false
		|      }
	    |  }
    	|
    	|}""".stripMargin

  }

  /**
   * 执行者
   */
  def actor(className: String, methodName: String, formName: String): String = {

    s"""|trait FlowActor extends (Any => Either[Boolean, String]) {
    	|
      	| def apply(form: Any): Either[Boolean, String] = {
		|      try {
    	|		   val clazz = Class.forName("$className")
		|		   val flowService = clazz.newInstance().asInstanceOf[$className]
				   Right(flowService.$methodName(form.asInstanceOf[$formName]))
		|      } catch {
		|         case e: Exception => Left(false)
		|      }
	    |  }
		|		   
    	|}""".stripMargin

  }

  /**
   * 流程控制类code
   */
  def controlStructure(formName: String, body: String): String = {

    s"""
	    |trait FlowControlStructure extends (Any => String) {
    	|
    	|	def to(step: String): String = {	
    	|		step
    	|	}
    	|
		|   def apply(formObj: Any): String = {
		|       val form = formObj.asInstanceOf[$formName]
		|		$body
		|    }
		|
		|}
    """.stripMargin

  }

  /**
   * 反射获取方法参数类型
   */
  private def getMethodParamTypes(classInstance: Any, methodName: String) = {
    var methods = classInstance.getClass().getMethods()
    val m = methods.find { e => methodName.equals(e.getName()) }
    m match {
      case Some(m) => {
        val params = m.getParameterTypes()
        if (params.length > 0) {
          val process = m.getAnnotation(classOf[Process])
          if (process == null) {
            //使用反射机制获取
            m.getParameterTypes.map(e => {
              if (isWrapClass(e)) e.getName().capitalize else {
                if (!e.getName().contains("collection")) e.getName() else throw WorkFlowException("", s"类${classInstance.getClass}方法${methodName}参数包含了集合类型,请使用@Process进行注解!")
              }
            })
          } else {
            process.paramTypes()
          }
        } else {
          throw WorkFlowException("", s"类${classInstance.getClass}方法${methodName}未设置参数!")
        }
      }
      case None => throw WorkFlowException("", s"类${classInstance.getClass}方法${methodName}不存在!")
    }
  }

  def isWrapClass(clz: Class[_]) = {
    clz.isPrimitive()
  }

  def main(args: Array[String]): Unit = {
    // Console println actor("com.fangj.flowservice.FlowService", "getBaoPiActor", "com.fangj.flowservice.Form")
    val clazz = Class.forName("com.fangj.flowservice.FlowService")
    getMethodParamTypes(clazz.newInstance(), "approve").foreach(println)

  }

}