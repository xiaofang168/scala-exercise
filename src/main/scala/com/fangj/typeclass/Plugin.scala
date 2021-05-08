package com.fangj.typeclass

/**
 * The "super type" with the common parameters for all plugins
 */
trait Plugin {
  def id: String

  def name: String
}

case class FooPlugin(
                      id: String,
                      name: String,
                      fooSpecificStuff: Double
                    ) extends Plugin

case class BarPlugin(
                      id: String,
                      name: String,
                      barSpecificStuff: Int
                    ) extends Plugin

/**
 * Type-class
 *
 * @tparam A
 */
trait Updateable[A] {
  def updatePlugin(self: A, p: A)
}

object Updateable {

  // Add type class instances
  implicit val FooPluginUpdateable = new Updateable[FooPlugin] {
    override def updatePlugin(self: FooPlugin, p: FooPlugin) = {
      println(self.fooSpecificStuff)
      println(p.fooSpecificStuff)
    }
  }

  implicit val BarPluginUpdateable = new Updateable[BarPlugin] {
    override def updatePlugin(self: BarPlugin, p: BarPlugin) = {
      println(self.barSpecificStuff)
      println(p.barSpecificStuff)
    }
  }

  // Add nice syntax
  implicit class UpdateableOps[A](val a: A) extends AnyVal {
    def updatePlugin(p: A)(implicit updateable: Updateable[A]) = updateable.updatePlugin(a, p)
  }

}



