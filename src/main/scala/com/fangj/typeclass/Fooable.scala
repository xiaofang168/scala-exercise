package com.fangj.typeclass

import shapeless._

/**
 * 隐式转换实现多态(类隐式、函数隐式、shapeless泛型)
 *
 * @tparam A
 */
trait Fooable[A] {
  def whatever(a: A): Unit
}

class Apple {
  def ppp(i: Int): Int = 7
} // not related to any other classes, yet

class Banana {
  def banana() = println("banana")
}

class Pear {
  def ppp(s: String): Unit = println(s)
}

object f extends Poly1 {
  implicit val AppleCase = at[Apple] { x => (y: Int) => x.ppp(y) }
  implicit val PearCase = at[Pear] { x => (y: String) => x.ppp(y) }
}

object f2 extends Poly1 {
  implicit val AppleCase = at[Apple] { x => x.ppp(2) }
  implicit val PearCase = at[Pear] { x => x.ppp("") }
}


object Main {

  implicit val fooableApple = new Fooable[Apple] { // connect the types implicitly
    def whatever(a: Apple): Unit = println("got apple: " + a.ppp(2))
  }

  case class Generic(text: String)

  // 使用def函数和上面类是等同的写法
  implicit def appleToGeneric(a: Apple): Generic = Generic(a.ppp(2).toString)

  def foo(input: Generic): Unit = println(s"got ${input.text}")

  // 类型匹配动态
  def foo2[T](in: T): Unit = in match {
    case a: Apple => a.ppp(2)
    case b: Banana => b.banana()
    case _ => println("wtf")
  }

  def foo[T](x: T)(implicit conv: Fooable[T]): Unit = conv.whatever(x)

  def main(args: Array[String]): Unit = {
    foo(new Apple)

    f(new Apple {}).apply(1)

    f(new Pear {}).apply("Hi")

    f2(new Apple {})

    f2(new Pear {})
  }

}
