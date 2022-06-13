package com.fangj.fs2

import _root_.io.circe.Encoder
import _root_.io.circe.generic.extras.Configuration
import _root_.io.circe.generic.extras.semiauto._
import cats.effect.{IO, IOApp}
import fs2.data.json._
import fs2.{Fallible, Stream}
import io.circe.generic.JsonCodec

import scala.concurrent.duration._

@JsonCodec
case class Data(field1: Int, field2: Option[String], field3: List[Int])

@JsonCodec
case class WrappedData(field1: Int, field2: Option[String], field3: List[Wrapped])

@JsonCodec
case class Wrapped(test: Int)

sealed trait Event

case class CreateCounter(name: String, initialValue: Int) extends Event

case class RemoveCounter(name: String) extends Event

case class IncreaseCounter(name: String) extends Event

object Event {
  implicit val configuration = Configuration.default.withDiscriminator("type")

  implicit val encoder: Encoder[Event] = deriveConfiguredEncoder
}

object ReadJsonFile extends IOApp.Simple {

  override def run(): IO[Unit] = {
    val input =
      """{
        |  "field1": 0,
        |  "field2": "test",
        |  "field3": [1, 2, 3]
        |}
        |{
        |  "field1": 2,
        |  "field3": []
        |}""".stripMargin

    import fs2.data.json.circe._
    import io.circe._

    val stream = Stream
      .emit(input)
      .through(tokens[Fallible, String])

    val asts = stream.through(ast.values[Fallible, Json])

    val values = stream.through(codec.deserialize[Fallible, Data])

    val outer = asts ++ values

    Stream.eval(asts.toList
      .fold(error => IO(println(error)),
        success => IO(println(s"$success"))))
      .concurrently(Stream.eval(values.toList
        .fold(error => IO(println(error)),
          success => IO(println(s"$success"))))).interruptAfter(10.seconds)
      .compile
      .drain

    //    outer
    //      .compile
    //      .toList
    //      .fold(error => IO(println(error)),
    //        success => IO(println(s"$success")))

    //    asts
    //      .compile
    //      .toList
    //      .fold(error => IO(println(error)),
    //        success => IO(println(s"$success")))
    //
    //
    //    values
    //      .compile
    //      .toList
    //      .fold(error => IO(println(error)),
    //        success => IO(println(s"$success")))

  }

}
