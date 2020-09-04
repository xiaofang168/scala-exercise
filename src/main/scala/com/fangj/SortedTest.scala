package com.fangj

import java.lang.reflect.Method

import scala.reflect.ClassTag

/**
 * 支持动态多字段同时排序
 */
object SortedTest {

  case class Book(a: Int, b: Option[Long], c: String, z: String)

  case class SortingField[T](field: String, ord: Ordering[T]) {
    type FieldType = T
  }

  def sort[T](unsorted: Seq[T], fields: Seq[SortingField[_]])(implicit tag: ClassTag[T]): Seq[T] = {
    @inline def invokeGetter[A](field: Method, obj: T): A = field.invoke(obj).asInstanceOf[A]

    @inline def orderingByField[A](field: Method)(implicit ord: Ordering[A]): Ordering[T] = {
      Ordering.by[T, A](invokeGetter[A](field, _))
    }

    val clazz = tag.runtimeClass
    if (fields.nonEmpty) {
      val field = clazz.getMethod(fields.head.field)

      implicit val composedOrdering: Ordering[T] = fields.tail.foldLeft {
        orderingByField(field)(fields.head.ord)
      } { case (ordering, currentField) =>
        val field = clazz.getMethod(currentField.field)
        val subOrdering: Ordering[T] = orderingByField(field)(currentField.ord)

        new Ordering[T] {
          def compare(x: T, y: T): Int = {
            val upperLevelOrderingResult = ordering.compare(x, y)

            if (upperLevelOrderingResult == 0) {
              subOrdering.compare(x, y)
            } else {
              upperLevelOrderingResult
            }
          }
        }
      }

      unsorted.sorted(composedOrdering)
    } else {
      unsorted
    }
  }

  def main(args: Array[String]): Unit = {
    val r = sort(
      Seq[Book](
        Book(1, Some(5L), "foo1", "bar1"),
        Book(10, Some(50L), "foo10", "bar15"),
        Book(2, Some(3L), "foo3", "bar3"),
        Book(100, Some(52L), "foo4", "bar6"),
        Book(100, Some(51L), "foo4", "bar6"),
        Book(100, Some(51L), "foo3", "bar6"),
        Book(11, Some(15L), "foo5", "bar7"),
        Book(22, Some(45L), "foo6", "bar8")
      ),
      Seq(
        SortingField("a", implicitly[Ordering[Int]].reverse),
        SortingField[Option[Long]]("b", implicitly[Ordering[Option[Long]]].reverse),
        SortingField("c", implicitly[Ordering[String]].reverse)
      )
    )
    r.foreach(println)
  }

}
