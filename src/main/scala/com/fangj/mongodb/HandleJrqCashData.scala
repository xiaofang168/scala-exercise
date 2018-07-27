package com.fangj.mongodb

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.fangj.mongodb.JrqJsonProtocol._
import com.mongodb.casbah.Imports.MongoClient
import com.mongodb.casbah.commons.MongoDBObject
import spray.json._

import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * @author fangjie
  * @date Created in 下午12:01 18/7/17.
  */
object HandleJrqCashData extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  implicit def cashAsDBObject(in: JrqCash) = MongoDBObject("bankNo" -> in.bankNo,
    "cashAmount" -> in.cashAmount,
    "cashDate" -> in.cashDate,
    "phone" -> in.phone,
    "userName" -> in.userName
  )

  // db.getCollection('cash_2018-07-17').find({"userName":/^方/,"phone":/^134/})

  val mongoClient = MongoClient("localhost", 27017)
  val db = mongoClient("jrq")
  // val date = DateTime.now.toString("yyyy-MM-dd");

  val date = "2018-07-26";

  val coll = db("cash_" + date)
  coll.drop();

  val responseFuture: Future[HttpResponse] = post("http://process.jrq.com/wealth/todayCashList?date=" + date)

  responseFuture
    .onComplete {
      case Success(res) => {
        val responseAsString: Future[String] = Unmarshal(res.entity).to[String]
        responseAsString.onComplete {
          case Success(str) => {
            val myObject = str.parseJson.convertTo[JqrResult]
            myObject.value match {
              case Some(value) => {
                value.cashList match {
                  case Some(cashList) => {
                    println("cashList size:" + cashList.size)
                    cashList.foreach(cash => {
                      coll.insert(cash)
                    })
                  }
                }
              }
            }
          }
        }
      }
      case Failure(_) => sys.error("something wrong")
    }

  def post(url: String): Future[HttpResponse] = {
    Http(system).singleRequest(
      HttpRequest(
        HttpMethods.POST,
        url
      ).withHeaders(RawHeader("X-Access-Token", "access token"))
    )
  }

}
