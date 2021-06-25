package com.fangj.zio

import zio.clock.Clock
import zio.{Exit, UIO, ZIO}

trait ZioPrintable[A] {
  def printPlugin(self: A): A
}

object ZioPrintable {

  implicit val printAble = new ZioPrintable[UIO[String]] {
    override def printPlugin(self: UIO[String]) = {
      self.map(x => {
        println(s"[${Thread.currentThread().getName}] $x")
        x
      })
    }
  }

  implicit val tuplePrintAble = new ZioPrintable[UIO[(String, String)]] {
    override def printPlugin(self: UIO[(String, String)]) = {
      self.map(x => {
        println(s"[${Thread.currentThread().getName}] (${x._1},${x._2})")
        (x._1, x._2)
      })
    }
  }

  implicit val exitPrintAble = new ZioPrintable[UIO[Exit[Any, String]]] {
    override def printPlugin(self: UIO[Exit[Any, String]]) = {
      self.map {
        case Exit.Success(value) => println(s"[${Thread.currentThread().getName}] $value"); Exit.Success(value)
        case e => println(e); e
      }
    }
  }

  implicit val zioPrintAble = new ZioPrintable[ZIO[Clock, Any, String]] {
    override def printPlugin(self: ZIO[Clock, Any, String]) = {
      self.fold(e => s"[${Thread.currentThread().getName}] Failure($e)", s => {
        println(s"[${Thread.currentThread().getName}] Success($s)");
        s
      })
    }
  }

  implicit class PrintableOps[A](val a: A) extends AnyVal {
    def debug()(implicit printable: ZioPrintable[A]) = printable.printPlugin(a)
  }

}
