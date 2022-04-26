/*
 * @FileName: Test03Map.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.caseclass
 * @date: 2014-7-8
 */
package com.fangj.caseclass

import scala.reflect.runtime.universe._

class MakeFoo[A](implicit manifest: Manifest[A]) {
  def make: A = manifest.runtimeClass.asInstanceOf[A]
}

object Registry {

  import scala.reflect.Manifest

  private var map = Map.empty[Any, (Manifest[_], Any)]

  def register[T](name: Any, item: T)(implicit m: Manifest[T]): Unit = {
    map = map.updated(name, m -> item)
  }

  import scala.reflect.runtime.universe._

  trait InstanceOfFun[T] {
    def apply[U: TypeTag](that: U)(implicit t: TypeTag[T]): Boolean
  }

  def myIsInstanceOf[T] = new InstanceOfFun[T] {
    def apply[U: TypeTag](that: U)(implicit t: TypeTag[T]) =
      typeOf[U] <:< typeOf[T]
  }

  import scala.reflect.runtime.{universe => ru}

  def arrayConformsTo[A: ru.TypeTag](as: Array[_]) = {
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classSym = mirror.classSymbol(as.getClass.getComponentType)
    classSym.toType <:< implicitly[ru.TypeTag[A]].tpe
  }

  def get[T](key: Any)(implicit m: Manifest[T]): Option[T] = {
    map get key flatMap {
      case (om, s) => if (myIsInstanceOf[T](om)) Some(s.asInstanceOf[T]) else None
    }
  }
}

/**
 * @ClassName: Test03Map
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:54:58
 * @version: V1.0
 */
object Test03Map {

  def matchList[T: Manifest](list: List[T]): Unit = {
    import scala.reflect.runtime.universe._

    if (typeOf[T] <:< typeOf[String]) {
      println("String list")
    }

    else if (typeOf[T] <:< typeOf[Int])
      println("Int list")
  }

  def matchMap[T: Manifest](map: Map[String, T]): Unit = {
    if (typeOf[T] <:< typeOf[String])
      println("String Map")
    else if (typeOf[T] <:< typeOf[Int])
      println("Int Map")
    else if (typeOf[T] <:< typeOf[List[_]])
      println("List Map")
  }

  def matchObject[T: Manifest](o: T): Unit = {
    if (typeOf[T] <:< typeOf[String])
      println("String obj")
    else if (typeOf[T] <:< typeOf[Int])
      println("Int obj")
    else if (typeOf[T] <:< typeOf[List[_]])
      println("List obj")
  }


  def main(args: Array[String]): Unit = {
    val mf = (new MakeFoo[String]).make
    println(mf)
    matchList(List(2))
    matchMap(Map("b" -> List[String]("b")))
    val map = Map("a" -> Map("a" -> "a"), "b" -> Map("b" -> List("b")))
    println(map)
    map.values.foreach(matchMap(_))
    map.foreach(m => {

      println(">>" + m._2)
      matchMap(m._2)

    })
    //代码正确后match会始终匹配第一个case（类型擦除）
    map.foreach {
      case (key, value) => {
        value.foreach {
          case (k, v) =>
            if (v.isInstanceOf[List[_]]) {
              println("list")
            } else println("int")
        }
      }
    }
    map.foreach(e => {
      e._2.foreach(a => matchObject(a._2))
    })
  }
}