package com.fangj.newtyp

import com.fangj.newtyp.CoerceExample.BarCodeWithCompanion


object NewTypeTest {

  def main(args: Array[String]): Unit = {
    val iPhoneBarCode: BarCode = BarCode("1-234567-890123")
    val iPhoneDescription: Description = Description("Apple iPhone 12 Pro")
    val iPhone12Pro: Product = Product(iPhoneBarCode, iPhoneDescription)
    println(iPhone12Pro)
    println(BarCodeWithCompanion.mkBarCode("134393583"))
  }

}
