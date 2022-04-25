package com.fangj.http

import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec}

@ConfiguredJsonCodec
case class CommitData(source: String, ext: String, createTime: Long, ip: String)

object CommitData {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames
}
