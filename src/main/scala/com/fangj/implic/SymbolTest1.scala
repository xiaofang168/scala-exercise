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

class Pair5[T <% Comparable[T]](val first: T, val second: T) {
  def smaller = if (first.compareTo(second) < 0) first else second

  override def toString = "(" + first + "," + second + ")"
}

object SymbolTest1 {

  def main(args: Array[String]): Unit = {
    val l: Int = Foo("333").getStringLength
    println(l)
    println(Foo(2).plus)
    val p = new Pair5(4, 2)
    println(p.smaller)
  }

}
