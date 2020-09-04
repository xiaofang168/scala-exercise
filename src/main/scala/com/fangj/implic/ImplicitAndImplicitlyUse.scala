package com.fangj.implic

/**
 * implicitly的函数，帮助我们找到当前上下文中所需要类型的
 * 需要注意的是，进一步简化之后，代码的可读性有所损失，调用方并不知道startServer需要一个隐式的
 * 配置对象
 * 视界<%被废弃了
 */
object ImplicitAndImplicitlyUse {

  case class SplitPrompt(s: String)

  case class R(width: Int, height: Int) {
    def o: String = s"${width}${implicitly[RMaker](width)}${height}"

    def +(that: R): R = {
      R(this.width + that.width, this.height + that.width)
    }

    def +(that: Int): R = {
      R(that.width + that, that.height + that)
    }

    override def toString: String = s"${width}${implicitly[SplitPrompt].s}${height}"
  }

  case class R2(width: Int, height: Int)(implicit splitPrompt: SplitPrompt) {
    override def toString: String = s"${width}${splitPrompt.s}${height}"
  }

  implicit val prompt: SplitPrompt = SplitPrompt("#")

  implicit class RMaker(width: Int) {
    def x(height: Int) = R(width, height)

    override def toString: String = s"${width}"
  }

  implicit def stringToInt(s: String): Int = Integer.valueOf(s)

  implicit def intToR(i: Int): R = R(i, i)

  // implicit class 可以替换为下面的方式
  // implicit def intToM(i: Int): RMaker = new RMaker(i)


  class BlingString(s: String) {
    def bling = "*" + s + "*"
  }

  //2. 定义隐式转换方法
  implicit def str2BlingString(s: String) = new BlingString(s)

  // 类的替换形式
  /*
  implicit class BlingString(s:String) {
    def bling = "*"+s+"*"
  }
  //implicit def str2BlingString(s:String) = new BlingString(s)

  val hi = "hello"
    hi.bling // *hello*
  */


  def main(args: Array[String]): Unit = {
    println(R(2, 4))
    println(R2(2, 4))
    val r = R(2, 4)
    println((1 + r) + (1 x 2))

    //3. 使用目标方法
    val s = "hello".bling
    print(s)
  }

}
