package com.fangj.enumer

object Category extends Enumeration {
  type Category = Value
  /**
   * 股票
   */
  val STOCK = Value("股票")

  /**
   * 股票基金
   */
  val STOCK_FUND = Value("股票基金")

  /**
   * 指数基金
   */
  val INDEX_FUND = Value("指数基金")

  /**
   * 债券基金
   */
  val BOND_FUND = Value("债券基金")

  /**
   * 货币基金
   */
  val MONETARY_FUND = Value("货币基金")

  /**
   * 保险
   */
  val INSURANCE = Value("保险")

  /**
   * 银行理财
   */
  val BANK = Value("银行理财")

  /**
   * 储蓄
   */
  val SAVING = Value("银行储蓄")
}