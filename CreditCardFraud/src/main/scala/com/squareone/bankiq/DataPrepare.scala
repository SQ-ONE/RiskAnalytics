package com.squareone.bankiq

import org.apache.spark.sql.{DataFrame, Dataset, Encoders}

object DataPrepare {
  val session = SparkService.getSparkSession()
  import session.implicits._

  def prepareTransaction(data: DataFrame): Dataset[Transaction] = {
    data.map(row => Transaction(row.getString(0).toDouble, row.getString(1).toDouble,row.getString(2).toDouble,row.getString(3).toDouble,
      row.getString(4).toDouble,row.getString(5).toDouble,row.getString(6).toDouble,row.getString(7).toDouble,row.getString(8).toDouble,
      row.getString(9).toDouble,row.getString(10).toDouble,row.getString(11).toDouble,row.getString(12).toDouble,row.getString(13).toDouble,
      row.getString(14).toDouble,row.getString(15).toDouble,row.getString(16).toDouble,row.getString(17).toDouble,row.getString(18).toDouble,
      row.getString(19).toDouble,row.getString(20).toDouble,row.getString(21).toDouble,row.getString(22).toDouble,row.getString(23).toDouble,
      row.getString(24).toDouble,row.getString(25).toDouble,row.getString(26).toDouble,row.getString(27).toDouble,row.getString(28).toDouble,
      row.getString(29).toDouble,row.getString(30).toInt))
  }
/*  def prepareTran(file: DataFrame): Dataset[Transaction] = {
    val dataEncoder = Encoders.bean[Transaction]
    //file.as[Transaction](dataEncoder)
      ???
  }*/
}
