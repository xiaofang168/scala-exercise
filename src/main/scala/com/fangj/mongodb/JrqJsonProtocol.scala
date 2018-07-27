package com.fangj.mongodb

import spray.json.DefaultJsonProtocol

/**
  * @author fangjie
  * @date Created in 下午4:21 18/7/17.
  */

case class JrqCash(bankNo: Option[String], cashAmount: Option[String], cashDate: Option[String],
                   phone: Option[String], userName: Option[String])

case class JrqResultValue(cashList: Option[List[JrqCash]])

case class JqrResult(comment: Option[String], error: Option[Boolean], ok: Option[Boolean],
                     status: Option[String], value: Option[JrqResultValue])

object JrqJsonProtocol extends DefaultJsonProtocol {
  implicit val jqrJrqCashFormat = jsonFormat5(JrqCash)
  implicit val jrqResultValueFormat = jsonFormat1(JrqResultValue)
  implicit val jqrResultFormat = jsonFormat5(JqrResult)
}
