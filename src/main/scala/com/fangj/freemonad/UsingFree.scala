package com.fangj.freemonad

import java.util.UUID

import cats.free.Free
import cats.implicits._
import cats.~>

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

case class User(id: UUID, email: String, loyaltyPoints: Int) {
  def serialize: String = id.toString + "," + loyaltyPoints + "," + email
}

object User {
  def parse(s: String): User = {
    val parts = s.split(",")
    User(UUID.fromString(parts(0)), parts(2), parts(1).toInt)
  }
}

object UsingFree {

  /**
   * 要用Free, 必须得有F[A]
   *
   * @tparam T
   */
  sealed trait UserRepositoryAlg[T]

  case class FindUser(id: UUID) extends UserRepositoryAlg[Option[User]]

  case class UpdateUser(u: User) extends UserRepositoryAlg[Unit]

  type UserRepository[T] = Free[UserRepositoryAlg, T]

  def findUser(id: UUID): UserRepository[Option[User]] = Free.liftF(FindUser(id))

  def updateUser(u: User): UserRepository[Unit] = Free.liftF(UpdateUser(u))

  def addPoints(userId: UUID, pointsToAdd: Int): UserRepository[Either[String, Unit]] = {
    findUser(userId).flatMap {
      case None => Free.pure(Left("User not found"))
      case Some(user) =>
        val updated = user.copy(loyaltyPoints = user.loyaltyPoints + pointsToAdd)
        updateUser(updated).map(_ => Right(()))
    }
  }

  sealed trait KVAlg[T]

  case class Get(k: String) extends KVAlg[Option[String]]

  case class Put(k: String, v: String) extends KVAlg[Unit]

  type KV[T] = Free[KVAlg, T]

  def get(k: String): KV[Option[String]] = Free.liftF(Get(k))

  def put(k: String, v: String): KV[Unit] = Free.liftF(Put(k, v))

  val userToKvInterpreter = new (UserRepositoryAlg ~> KV) {
    override def apply[A](fa: UserRepositoryAlg[A]): KV[A] = fa match {
      case FindUser(id) =>
        get(id.toString).map(_.map(User.parse))
      case UpdateUser(u) =>
        val serialized = u.serialize
        for {
          _ <- put(u.id.toString, serialized)
          _ <- put(u.email, serialized) // we also maintain a by-email index
        } yield ()
    }
  }

  // "~>" 表示将F[A]转换为G[A]的函数，而G是个Monad
  val kvToFutureInterpreter = new (KVAlg ~> Future) {
    override def apply[A](fa: KVAlg[A]): Future[A] = fa match {
      case Get(k) => /* go and talk to a database */ Future.successful(None)
      case Put(k, v) => /* as above */ Future.successful(())
    }
  }

  // 执行
  val result: Future[Either[String, Unit]] =
    addPoints(UUID.randomUUID(), 10)
      .foldMap(userToKvInterpreter)
      .foldMap(kvToFutureInterpreter)

  def main(args: Array[String]): Unit = {
    result onComplete {
      case Success(value) => println(value)
      case Failure(exception) => println(exception)
    }
    Thread.sleep(5000)
  }

}
