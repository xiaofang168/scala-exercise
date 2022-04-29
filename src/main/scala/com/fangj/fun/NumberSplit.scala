/*
 * Prject: scala-exercise
 * Description: NumberSplit.scala
 * created at: 2014年7月29日
 */
package com.fangj.fun

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年7月29日 下午12:19:08
 * @version: $Rev$
 */
object NumberSplit extends App {
  val count = 101
  val pageList = for (i <- 1 to count if (i % 100 == 0)) yield (i)
  val list: List[Int] = (1 to count) toList
  // 总页数
  val (o, p) = list.partition(_ % 10 == 0)
  println(o)
  println(p.zipAll(o, "for missing values", 100))
  val input = List(5, 2, 3, 3, 3, 5, 5, 3, 3, 2, 2, 2)
  //val s = list.groupBy(a => a % 10 == 0)
  //val a = s(false).toList
  //val b = s(true).toList
  //println(((a ,b) zipped))
  val t = for {
    (offset, limit) <- p zip o
  } yield (Map("offset" -> offset, "limit" -> limit))
  Console println (t)

  page()

  def page(): Unit = {

    val rowCount = 101
    // page
    val pageSize = 20

    val pageCount = if (rowCount % pageSize == 0) rowCount / pageSize else rowCount / pageSize + 1

    val s = for (i <- 1 to pageCount) yield {
      // query.setFirstResult(0);//从第一条记录开始
      // query.setMaxResults(4);//取出四条记录
      val startRow = pageSize * (i - 1)
      val endRow = if (pageSize * i > rowCount) rowCount else pageSize * i
      Map("offset" -> startRow, "limit" -> (endRow - startRow))
    }
    println(s)
  }
}