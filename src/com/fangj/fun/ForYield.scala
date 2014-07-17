/*
 * @FileName: ForYield.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.fun
 * @date: 2014-7-17
 */
package com.fangj.fun

import sun.org.mozilla.javascript.internal.ast.Yield

/**
 * @ClassName: ForYield
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 上午12:19:33
 * @version: V1.0
 */
object ForYield {
  def main(args: Array[String]) {
    val map = Map("sort" -> "+id,age-")
    val sort = (for ((k, v) <- map if k contains "sort") yield ({
      (for {
        field <- v.toString.split(",")
        val s = if (field.toString.contains("-")) " desc" else " asc"
      } yield field.toString.replaceAll("\\-|\\+", "") + s) mkString (",")

    } foldLeft ("order by "))((a, b) => a + b)) mkString ("")

    Console println (sort)
    aa
    bb
    cc
  }

  //习惯性的拆分关注点,不像虾爬子(代码长得跟虾耙子一样,十只脚抓了10个关注点，逻辑不清晰)
  def aa() {
    //不想把函数定义成val，定义成def也行
    val sortItem: String => String = in => in.replaceAll("[+-]$", "") + (if (in.endsWith("-")) " desc" else " asc")
    val sortItems: String => String = _.split(",") map sortItem mkString (", ")
    val parseSort: Map[String, String] => String = _.get("sort") map sortItems map ("order by " + _) getOrElse ""
    val map = Map("sort" -> "id,age-")
    Console println parseSort(map)
  }

  // 整合的写法
  def bb() {
    val map = Map("sort" -> "id,age-")
    val sort = map.get("sort") map {
      _.split(",") map {
        (in) =>
          {
            in.replaceAll("[+-]$", "") + (if (in.endsWith("-")) " desc" else " asc")
          }
      } mkString (", ")
    } map ("order by " + _) getOrElse ""

    println(sort)
  }

  def cc() {
    val m = Map[String, String]("sort" -> "id+,age-", "id" -> "1", "age" -> "20")
    val parseSort: Map[String, String] => String = _.get("sort") getOrElse ("")
    val whereField: Map[String, String] => Map[String, String] = _.filterKeys(!_.contains("sort"))
    val joinOpField: Map[String, String] => Iterable[String] = for ((k: String, v: String) <- _) yield (k + "=" + v)

    Console println parseSort(m)
    val e = for ((k: String, v: String) <- m) yield (k + "=" + v)
    val a = joinOpField(whereField(m)) mkString (" and ")
    println(a)
    println(e)
    //Console println parsejoinExpression(Map("id" -> "1", ("age" -> "20")))
  }
}