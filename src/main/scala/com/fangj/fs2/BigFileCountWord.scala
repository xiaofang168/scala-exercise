package com.fangj.fs2

import cats.effect.{ExitCode, IO, IOApp}
import fs2.io.file.{Files, Path}
import fs2.{Stream, text, _}

/**
 * Difference between flatMap, flatTap, evalMap and evalTap
 */
object BigFileCountWord extends IOApp {

  def readAndWriteFile(dir: String, writeTo: String): Stream[IO, Unit] = {
    //    val source1: Stream[IO, Byte] = Files[IO].readAll(Path(readFrom1))
    //    val source2: Stream[IO, Byte] = Files[IO].readAll(Path(readFrom2))

    val pipe: Pipe[IO, Byte, Byte] = src =>
      src.through(text.utf8.decode)
        .through(text.lines)
        .flatMap(line => Stream.apply(line.toLowerCase.split("\\W+"): _*))
        .fold(Map.empty[String, Int]) {
          (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))
        }
        .map(e => e.toSeq.sortBy(_._2).reverse)
        .map(_.foldLeft("") {
          case (accumulator, (word, count)) =>
            accumulator + s"$word = $count\n"
        })
        .through(text.utf8.encode)

    // 合并数据流
    // val source = source1.merge(source2)

    val source = Files[IO].walk(Path(dir)).filter(_.extName == ".txt").flatMap(Files[IO].readAll(_))

    source
      .through(pipe)
      .through(Files[IO].writeAll(Path(writeTo)))

  }

  override def run(args: List[String]): IO[ExitCode] = {
    readAndWriteFile("/Users/fangjie/Downloads/fs2", "/Users/fangjie/Downloads/fs2_count_data.txt")
      .compile
      .drain
      .as(ExitCode.Success)
  }

}
