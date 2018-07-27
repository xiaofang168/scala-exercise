package com.fangj.mongodb

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.fangj.mongodb.JrqJsonProtocol._
import spray.json._

import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * @author fangjie
  * @date Created in 下午6:03 18/7/17.
  */
object JrqCheck extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  // chrome debug console: cashTable.children.length
  val responseFuture: Future[HttpResponse] = post("http://process.jrq.com/wealth/todayCashList")

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
