package com.fangj

import io.estatico.newtype.macros.newtype

/**
 * @newtype 避免运行时实例化对象，减少内存占用
 */
package object newtyp {

  @newtype case class BarCode(code: String)

  @newtype case class Description(descr: String)

  case class Product(code: BarCode, description: Description)

}
