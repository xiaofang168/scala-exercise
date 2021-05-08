package com.fangj.typeclass

object TypeClassTest {

  def main(args: Array[String]): Unit = {
    import Updateable.UpdateableOps
    BarPlugin("1", "bar", 42).updatePlugin(BarPlugin("foo2", "bar2", 43))
    FooPlugin("2", "foo", 43).updatePlugin(FooPlugin("2", "foo2", 45))
  }

}
