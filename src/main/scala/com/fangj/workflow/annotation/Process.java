/*
 * Prject: scala-exercise
 * Description: Process.scala
 * created at: 2015年2月2日
 */
package com.fangj.workflow.annotation;

import java.lang.annotation.*;

/**
 * 流程处理注解
 *
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年2月2日 下午8:25:27
 * @version: $Rev$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Process {
    /**
     * 参数类型
     */
    public String[] paramTypes();
}