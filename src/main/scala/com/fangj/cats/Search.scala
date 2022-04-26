package com.fangj.cats

import cats.data.EitherT
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
 * Cats（用于函数式编程的 Scala 库）可让您连接两个 Future，有效地并行运行它们
 * https://www.ibm.com/developerworks/cn/cloud/library/cl-model-first-microservices-scala-cats/index.html
 *
 * @author fangjie
 * @date Created in 下午5:34 18/8/17.
 */
case class Author(id: Long, name: String)

case class Publication(id: Long, authorId: Long, title: String)

case class AuthorPublications(author: Author, publications: List[Publication])


sealed trait ServiceError

case object InvalidQuery extends ServiceError

case object NotFound extends ServiceError

object Search {

  // bind Future and SearchError together while leaving the inner result type unbound
  type Result[A] = EitherT[Future, ServiceError, A]

  // this allows you to invoke the companion object as "Result"
  val Result = EitherT

  def findAuthor(query: String): Future[Int] = {
    Future {
      2
    }

  }


  def getAuthor(id: Long): Future[Author] = {
    Future {
      Author(1, "test")
    }
  }


  def getPublications(authorId: Long): Future[List[Publication]] = {
    Future {
      List(Publication(1, 1, "test"))
    }
  }

  def findPublications(query: String): Future[AuthorPublications] = {
    for {
      authorId <- findAuthor(query)
      author <- getAuthor(authorId)
      pubs <- getPublications(authorId)
    } yield AuthorPublications(author, pubs)
  }

  /**
   * Future 倍增：Cats 和 Semigroupal 类型
   *
   * @param query
   * @return
   */
  def findPublicationsCat(query: String): Future[AuthorPublications] = {
    for {
      authorId <- findAuthor(query)
      (author, pubs) <- getAuthor(authorId) product getPublications(authorId)
    } yield AuthorPublications(author, pubs)
  }

  def findAuthorEither(query: String): Future[Either[ServiceError, Long]] = {
    Future.successful(
      if (query == "test") 42L.asRight
      else if (query.isEmpty) InvalidQuery.asLeft
      else NotFound.asLeft
    )
  }

  def findAuthorEitherD(query: String): Result[Long] = {
    if (query == "test") Result.rightT(42L)
    else if (query.isEmpty) Result.leftT(InvalidQuery)
    else Result.leftT(NotFound)
  }

  def main(args: Array[String]): Unit = {
    val query = "test"
    val search: Future[Future[Unit]] = findPublications(query) map { authorPubs =>
      Future {
        println(s"Found $authorPubs")
      }
    }
    findAuthorEither("test") map { idOrError => idOrError map { id => println(s"${id}>>>>") } }
    Await.result(search, Duration.Inf)
  }

}