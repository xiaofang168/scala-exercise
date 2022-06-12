package com.fangj.fs2

import cats.effect.{IO, IOApp}
import fs2.io.file.{Files, Path}
import fs2.{Stream, text}

object TempFile extends IOApp.Simple {

  override def run(): IO[Unit] = {

    val dir = "/Users/fangjie/Downloads/fs2"

    val acquire: IO[Path] = Files[IO].createTempFile(Some(Path(dir)), "aa", ".tmp", None)

    val release = (p: Path) => {
      //IO(println("删除临时文件"))
      Files[IO].delete(p)
    }

    Stream.bracket(acquire)(release)
      .flatMap(p => {
        println(p.toString)
        (Stream.apply("fs2临时文件写入内容测试")
          ++
          Stream.eval(IO {
            Thread.sleep(8000)
            println("休眠8秒后来自其他流数据")
            "休眠8秒后来自其他流数据"
          })).intersperse("\n")
          .through(text.utf8.encode)
          .through(Files[IO].writeAll(Path(p.toString)))
      })
      .compile
      .drain
  }

}
