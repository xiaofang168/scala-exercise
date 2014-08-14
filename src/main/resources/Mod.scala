trait Mod extends (String => String) {
  import java.text.SimpleDateFormat
  import java.util.Date
  import java.sql.Connection
  def apply(a: String): String = {
    val c = List(2,3,5,5)
    c.foreach(println(_))
    val d2 = new Date()
    new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(d2)
  }
}