/*
 * Prject: scala-exercise
 * Description: Test0126.scala
 * created at: 2015年1月26日
 */
package com.fangj.pdfbox

import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFTextStripper

/**
 * @author: <a href="mailto:hbxffj@163.com">方杰</a>
 * @Date: 2015年1月26日 下午4:13:36
 * @version: $Rev$
 */
object Test0126 {
  def main(args: Array[String]): Unit = {


    val file = new File("d:/aa.pdf")

    val pdfStripper: PDFTextStripper = new PDFTextStripper()

    val pdf = PDDocument.load(file)


    val parsedText = pdfStripper.getText(pdf)
    println(parsedText + ">>")

  }

}