/*
 * Copyright 2014 The Hikvision CO.Ltd
 * site: http://www.hikvision.com
 * Prject: scala-exercise
 * Description: Drawing.scala
 * created at: 2014年9月18日
 */
package com.fangj.caseclass

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年9月18日 下午5:23:20
 * @version: $Rev$
 */
//sealed 使用 case 时必须列出所有的类
sealed abstract class Drawing
case class Point(x: Int, y: Int) extends Drawing
case class Circle(p: Point, r: Int) extends Drawing
case class Cylinder(c: Circle, h: Int) extends Drawing