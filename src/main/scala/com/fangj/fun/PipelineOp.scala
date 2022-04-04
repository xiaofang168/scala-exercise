package com.fangj.fun

object PipelineOp {
  implicit class PipeEverything[A](val self: A) extends AnyVal {
    def ->>[B](f: A => B) = f(self)
  }
}


