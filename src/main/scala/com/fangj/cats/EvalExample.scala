package com.fangj.cats

import cats.Eval

/**
 * 更精确的惰性控制
 */
object MutualRecursion {
  def even(n: Int): Eval[Boolean] = {
    println("even...")
    Eval.always(n == 0).flatMap {
      case true => Eval.True
      case false => odd(n - 1)
    }
  }

  def odd(n: Int): Eval[Boolean] = {
    println("odd...")
    Eval.defer(Eval.always(n == 0)).flatMap {
      case true => Eval.False
      case false => even(n - 1)
    }
  }
}

object EvalExample extends App {

  val eager = Eval.now {
    println("Running expensive calculation now...")
    1 + 2 * 3
  }

  println(eager)

  val lazyEval = Eval.later {
    println("Running expensive calculation lazy...")
    1 + 2 * 3
  }

  println(lazyEval)
  println(lazyEval.value)
  println(lazyEval.value)
  println(MutualRecursion.odd(11).value)
}
