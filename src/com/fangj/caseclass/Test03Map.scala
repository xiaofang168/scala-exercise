/*
 * @FileName: Test03Map.scala
 * @Prject:scala-exercise
 * @Package: com.fangj.caseclass
 * @date: 2014-7-8
 */
package com.fangj.caseclass

class MakeFoo[A](implicit manifest: Manifest[A]) {
  def make: A = manifest.erasure.newInstance.asInstanceOf[A]
}

object Registry {
  import scala.reflect.Manifest

  private var map = Map.empty[Any, (Manifest[_], Any)]

  def register[T](name: Any, item: T)(implicit m: Manifest[T]) {
    map = map.updated(name, m -> item)
  }

  def get[T](key: Any)(implicit m: Manifest[T]): Option[T] = {
    map get key flatMap {
      case (om, s) => if (om <:< m) Some(s.asInstanceOf[T]) else None
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

  def matchList[T: Manifest](list: List[T]) {
    if (manifest[T] <:< manifest[String])
      println("String list")
    else (manifest[T] <:< manifest[Int])
    println("Int list")
  }

  def matchMap[T: Manifest](map: Map[String, T]) {
    if (manifest[T] <:< manifest[String])
      println("String Map")
    else if (manifest[T] <:< manifest[Int])
      println("Int Map")
    else if (manifest[T] <:< manifest[List[String]])
      println("List Map")
  }

  def matchObject[T: Manifest](o:  T) {
    if (manifest[T] <:< manifest[String])
      println("String obj")
    else if (manifest[T] <:< manifest[Int])
      println("Int obj")
    else if (manifest[T] <:< manifest[List[_]])
      println("List obj")
  }

  
  def main(args: Array[String]) {
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