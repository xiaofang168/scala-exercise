/*
 * @FileName: SelectFromDocument.scala
 * @Prject:test03
 * @Package: star.oa.util.parsing
 * @date: 2014-5-16
 */
package star.oa.util.parsing

/**
 * @ClassName: SelectFromDocument
 * @Description: TODO
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @time: 下午7:30:26
 * @version: V1.0
 */


package star.oa.util

/*import star.oa.Document
import star.oa.DocumentItemGroup
import star.oa.Item
import star.oa.ItemLine
import star.oa.util.parsing._

trait SelectFromDocument {
self: star.oa.Document =>

lazy val parser = new SelectParser()
def query(selectStr: String): SelectResult = {
val sel = parser.parse(parser.sql, selectStr).get

sel.from match {
  case Some(Table(groupName, _)) =>
    val grp = this.g(groupName)
    grp.doQuery(sel)
  case None =>
    val values =
      for (column <- sel.columns; field <- column.field) yield {
        Item(column.title, this.v(field), None)
      }

    SelectResult(sel.columns map { _.title }, List(ItemLine("", values)))
}

}
}
*/