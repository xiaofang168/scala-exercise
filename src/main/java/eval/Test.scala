/*
 * @FileName: Test.scala
 * @Prject:scala-exercise
 * @Package: eval
 * @date: 2014-8-14
 */
package eval

import eval.fun.Fun
import java.io.File
import com.twitter.util.Eval
import javax.script.ScriptEngineManager

/**
 * @ClassName: Test
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午9:59:21
 * @version: V1.0
 */
object Test {
  def main(args: Array[String]) {
    val file = new File("/home/fangjie/git/scala-exercise/src/main/resources/Mod.scala")
    val file1 = new File("/home/fangjie/git/scala-exercise/src/main/resources/Derived.scala")
    //Console println Eval("1+1")
    val sum = Eval[Int]("1 + 1")
    println(sum)
    val derived = (new Eval).apply[(String) => String](file)
    println(derived("ddd"))
  }

}