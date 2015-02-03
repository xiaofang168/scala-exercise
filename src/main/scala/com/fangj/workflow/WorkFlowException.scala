/*
 * Copyright 2015 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: WorkFlowException.scala
 * created at: 2015年2月2日
 */
package com.fangj.workflow

/**
 * 流程异常
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月2日 下午3:16:49
 * @version: $Rev$
 */
protected[this] class WorkFlowException private(val code: String, val message: String, throwable: Throwable) extends RuntimeException(message, throwable)

object WorkFlowException {
  def apply(code: String, message: String) = new WorkFlowException(code, message, null)
  def apply(code: String, message: String, throwable: Throwable) = new WorkFlowException(code, message, throwable)
}
