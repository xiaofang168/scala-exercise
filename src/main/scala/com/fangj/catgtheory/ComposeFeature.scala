package com.fangj.catgtheory

/**
  * @author fangjie
  **/
object ComposeFeature {

  def compose(f: String => String, g: String => String) = (x: String) => f(g(x))

  val compose2: (String => String, String => String) => (String => String) = (f, g) => x => f(g(x))

  val toUpperCase: String => String = (x) => x.toUpperCase()

  val toLowerCase: String => String = (x) => x.toLowerCase()

  val exclaim: String => String = (x) => x + "!";


  val head: List[String] => String = (x) => x.head


  def main(args: Array[String]): Unit = {
    val str = compose((s: String) => "a", (s: String) => "b")("c");
    Console.println(str);
    val shout = compose(exclaim, toUpperCase)
    Console.println(shout("“send in the clowns"))

    compose(toUpperCase, compose(exclaim, toLowerCase));


    compose(compose(toUpperCase, exclaim), toLowerCase);

    toLowerCase compose toUpperCase compose exclaim


  }

}
