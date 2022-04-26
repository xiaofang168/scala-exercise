/*
 * @FileName: EqualImplic.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.implic
 * @date: 2014-7-12
 */
package com.fangj.implic

/**
 * @ClassName: EqualImplic
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午5:20:43
 * @version: V1.0
 */
//class Container[A](value: A) { def addIt(implicit evidence: A =:= Int) = 123 + value }

trait Container[M[_]] { def put[A](x: A): M[A]; def get[A](m: M[A]): A }

trait Show[T] { def show(t: T): String }
object Show {
  implicit def IntShow: Show[Int] = new Show[Int] { def show(i: Int) = i.toString }
  implicit def StringShow: Show[String] = new Show[String] { def show(s: String) = s }

  def ShoutyStringShow: Show[String] = new Show[String] { def show(s: String) = s.toUpperCase }
}

case class Person(name: String, age: Int)
object Person {
  implicit def PersonShow(implicit si: Show[Int], ss: Show[String]): Show[Person] = new Show[Person] {
    def show(p: Person) = "Person(name=" + ss.show(p.name) + ", age=" + si.show(p.age) + ")"
  }
}

object EqualImplic {
  def square[T: Numeric](n: T) = implicitly[Numeric[T]].times(n, n)

  def sum(xs: List[Int])(implicit m: List[Int]): Int = xs.foldLeft(m.size)(_ + _)

  def sum2[T: List](xs: List[Int]): Int = {
    val m = implicitly[List[T]]
    xs.foldLeft(m.size)((a, b) => a + b)
  }

  def findAnInt(implicit x: Int) = x

  def max[T](a: T, b: T)(implicit m: Numeric[T]): T = {
    import m._
    if (a >= b) a else b
  }

  def computer[T: Numeric](a: T, b: T) = implicitly[Numeric[T]]

  def tupleize[M[_]: Container, A, B](fst: M[A], snd: M[B]) = {
    //Implicit的值可以通过implicitly来进行访问。
    val c = implicitly[Container[M]]
    c.put(c.get(fst), c.get(snd))
  }

  def main(args: Array[String]): Unit = {
    square(2)
    //(new Container(123)).addIt
    implicit val a = List[Int](3)
    Console println sum(List(2, 3))
    implicit val x = 5
    Console println findAnInt
    max(3, 5)
    val p = Person("bob", 25)
    implicitly[Show[Person]].show(p)
    implicit val listContainer = new Container[List] { def put[A](x: A) = List(x); def get[A](m: List[A]) = m.head }
    tupleize(List(1), List(2))
  }
}