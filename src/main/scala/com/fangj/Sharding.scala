/*
 * Prject: dbproxy-services
 * Description: Sharding.scala
 * created at: 2014年9月23日
 */
package com.fangj

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年9月23日 下午6:03:33
 * @version: $Rev$
 */
object Sharding {

  import java.math.{BigDecimal, BigInteger}


  implicit def toStr[T](x: T)(implicit ev: (Int with Float with Double with Long) <:< T) = x.toString

  implicit def toDouble[T](x: T)(implicit ev: (BigInteger with BigDecimal) <:< T) = x.toString.toDouble

  def apply(id: Double): Boolean = {
    id > 300 && id < 400
  }


}