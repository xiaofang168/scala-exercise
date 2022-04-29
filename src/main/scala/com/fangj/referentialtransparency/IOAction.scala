/*
 * Prject: scala-exercise
 * Description: IOAction.scala
 * created at: 2014年9月17日
 */
package com.fangj.referentialtransparency

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2014年9月17日 下午4:23:29
 * @version: $Rev$
 */
class IOAction[A](expression: => A) extends Function1[State, (State, A)] {

  def apply(s: State) = (s.next, expression)

}
