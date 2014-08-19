package star.oa.util.parsing
import scala.util.parsing.combinator._
import scala.util.matching.Regex

case class Table(name: String, alias: Option[String])
case class Join(tableName: String, alias: Option[String], leftTable: String, leftField: String, rightTable: String, rightField: String)
case class Column(table: Option[String] = None,
  field: Option[String] = None,
  formula: Option[String] = None,
  asTitle: Option[String] = None) {
  lazy val title = asTitle orElse field getOrElse "--"
}
case class Formula(text: String)
case class Condition(left: String, op: Op, right: String)
abstract class Op
case object Gt extends Op { def format = ">" }
case object Gte extends Op { def format = ">=" }
case object Lt extends Op { def format = "<" }
case object Lte extends Op { def format = "<=" }
case object Eq extends Op { def format = "=" }

case class Select(columns: List[Column], from: Option[Table], joins: List[Join], where: List[Condition])

class SqlParser extends JavaTokenParsers {
  val INSERT_ = "INSERT" | "insert"
  val INTO_ = "INTO" | "into"
  val VALUES_ = "VALUES" | "values"
  val UPDATE_ = "UPDATE" | "update"
  val SELECT_ = "SELECT" | "select"
  val FROM_ = "FROM" | "from"
  val JOIN_ = "JOIN" | "join"
  val WHERE_ = "WHERE" | "where"
  val AS_ = "AS" | "as"
  val ON_ = "ON" | "on"
  val AND_ = "AND" | "and"
  val SET_ = "SET" | "set"

  def column: Parser[Column] = columnFormula ~ opt(AS_ ~> ident) ^^ {
    case columnDef ~ optTitle => columnDef.copy(asTitle = optTitle)
  }

  def columnFormula: Parser[Column] = tableAndField | fieldName | formula

  def tableAndField: Parser[Column] = ident ~ "." ~ ident ^^ {
    case table ~ "." ~ field => Column(table = Some(table), field = Some(field))
  }

  def fieldName: Parser[Column] = ident ^^ {
    case field => Column(field = Some(field))
  }

  def formula: Parser[Column] = regex("##.*?##".r) ^^ {
    case f => Column(formula = Some(f))
  }

  def conditions: Parser[List[Condition]] = repsep(condition, AND_)

  def condition: Parser[Condition] = ident ~ op ~ stringLiteral ^^ {
    case left ~ op ~ right => Condition(left, op, right)
  }

  def op: Parser[Op] =
    "<" ^^ { case _ => Lt } |
      "<=" ^^ { case _ => Lte } |
      ">" ^^ { case _ => Gt } |
      ">=" ^^ { case _ => Gte } |
      "=" ^^ { case _ => Eq }

  def table: Parser[Table] = ident ~ opt(AS_ ~> ident) ^^ {
    case tableName ~ optAlias => Table(tableName, optAlias)
  }

}

class SelectParser extends SqlParser {
  def sql: Parser[Select] = SELECT_ ~> repsep(column, ",") ~ opt(from) ~ rep(join) ~ opt(WHERE_ ~> conditions) ^^ {
    case columns ~ optFrom ~ joins ~ optWhere =>
      val where = optWhere getOrElse Nil
      Select(columns, optFrom, joins, where)
  }

  def from: Parser[Table] = FROM_ ~> table
  
  def join: Parser[Join] =
    JOIN_ ~> ident ~ opt(AS_ ~> ident) ~ ON_ ~ ident ~ "." ~ ident ~ "=" ~ ident ~ "." ~ ident ^^ {
      case tableName ~ optAlias ~ on ~ leftTable ~ "." ~ leftField ~ "=" ~ rightTable ~ "." ~ rightField =>
        Join(tableName, optAlias, leftTable, leftField, rightTable, rightField)
    }
}
