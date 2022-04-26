package com.fangj.implic

/**
 * 类型化约束参数
 * <pre>
 * A =:= B means A must be exactly B
 * A <:< B means A must be a subtype of B (analogous to the simple type constraint <:)
 * A <%< B (视图界定,要求必须存在一个从A到B的隐式转换) means A must be viewable as B, possibly via implicit conversion (analogous to the simple type constraint <%)
 * </pre>
 *
 * @param a
 * @tparam A
 */
case class Foo[A](a: A) { // 'A' can be substituted with any type
  // getStringLength can only be used if this is a Foo[String]
  def getStringLength(implicit evidence: A =:= String) = a.length

  def plus(implicit evidence: A =:= Int) = a + 2
}


object SymbolTest1 {

  type or[L, R] = Either[L, R]

  implicit def l2Or[L, R](l: L): L or R = Left(l)

  implicit def r2Or[L, R](r: R): L or R = Right(r)

  object Bar {
    def foo(xs: (String or Int)) = xs match {
      case Left(l) => println("str")
      case Right(r) => println("int")
    }
  }

  /**
   * 限定参数类型，scala3中使用union types
   *
   * @param a
   * @param ev
   * @tparam A
   */
  def f[A](a: A)(implicit ev: (Int with String) <:< A) = println("OK")

  def main(args: Array[String]): Unit = {
    val l: Int = Foo("333").getStringLength
    println(l)
    println(Foo(2).plus)
    Bar.foo(2)
    Bar.foo(">>>")
  }

}
