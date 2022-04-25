package com.fangj.http

final case class CommitBatchData(day: String, rc: SourceData, packages: List[SourceData], fa: SourceData, ip: String)