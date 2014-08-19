/*
 * @FileName: Test.scala
 * @Prject:test03
 * @Package: star.oa.util.parsing
 * @date: 2014-5-16
 */
package star.oa.util.parsing

/**
 * @ClassName: Test
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午7:37:11
 * @version: V1.0
 */
object Test {
  def main(args: Array[String]) {
    lazy val parser = new SelectParser()
    val sel =parser.parse(parser.sql, "select name,sex from User u where u.name='a'").get
    
    Console println sel.from
  }
}