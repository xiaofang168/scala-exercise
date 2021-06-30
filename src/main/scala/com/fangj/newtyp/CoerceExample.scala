package com.fangj.newtyp

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._

/**
 * 必须放在一个类中
 */
object CoerceExample {

  @newtype class BarCodeWithCompanion(code: String)

  object BarCodeWithCompanion {
    def mkBarCode(code: String): Either[String, BarCodeWithCompanion] =
      Either.cond(
        code.matches("\\d-\\d{6}-\\d{6}"),
        code.coerce,
        s"The given code $code has not the right format")
  }

}
