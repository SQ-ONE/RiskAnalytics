package com.squareone.bankiq

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.sql.{DataFrame, Dataset}

class LabeledData(df: DataFrame) {
  val session = SparkService.getSparkSession()
  import session.implicits._

  def createLabeledData(data: Dataset[Transaction]) = {
    val labeledPoints = data.map(x => LabeledPoint(x.Label,
      Vectors.dense(x.Amount, x.V1, x.V2, x.V3, x.V4, x.V5, x.V6, x.V7,
        x.V8, x.V9, x.V10, x.V11, x.V12, x.V13, x.V14, x.V15, x.V16, x.V17,
        x.V18, x.V19, x.V20, x.V21, x.V22, x.V23, x.V24, x.V25, x.V26, x.V27, x.V28)))
    val splits = labeledPoints.rdd.randomSplit(Array(0.7, 0.3), seed = 5043l)
    (splits(0),splits(1))
  }

  def featureCreate(label: String) = {
    val featInd = df.drop(label).columns.map(df.columns.indexOf(_))
    val data = df.rdd.map(r => LabeledPoint(
      r.getAs(label).toString.toDouble,Vectors.dense(featInd.map(r.getString(_).toDouble))
    ))
    data
  }
  def dropFeature(label: String, colName: String) = {
    val featInd = df.drop(label).drop(colName).columns.map(df.columns.indexOf(_))
    val data = df.rdd.map(r => LabeledPoint(
      r.getAs(label).toString.toDouble, Vectors.dense(featInd.map(r.getString(_).toDouble))
    ))
    data
  }
}
