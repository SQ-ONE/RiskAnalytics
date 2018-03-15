package com.squareone.bankiq

import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics}
import org.apache.spark.rdd.RDD

class Precision(predictionAndLabels: RDD[(Double,Double)]) {
  val metrics = new BinaryClassificationMetrics(predictionAndLabels)
  val multiMetrics = new MulticlassMetrics(predictionAndLabels)

  def getPrecision = {
    val precision = metrics.precisionByThreshold
    precision.foreach { case (t, p) =>
      println(s"Threshold: $t, Precision: $p")
    }
  }
  def getPRR = {
    val PRC = metrics.pr
    PRC.foreach(x => println(s"PRC = $x"))
  }
  def getF1Score = {
    val f1Score = metrics.fMeasureByThreshold
    f1Score.foreach { case (t, f) =>
      println(s"F1ScoreThreshold: $t, F-score: $f, Beta = 1")
    }
  }
  def getFScore = {
    val beta = 0.5
    val fScore = metrics.fMeasureByThreshold(beta)
    fScore.foreach { case (t, f) =>
      println(s"FScore Threshold: $t, F-score: $f, Beta = $beta")
    }
  }
  def getAUPRC = {
    val auPRC = metrics.areaUnderPR
    println("Area under precision-recall curve = " + auPRC)
  }

  def areaUnderROC= {
    val auROC = metrics.areaUnderROC()
    println("Area under ROC = " + auROC)
  }
  def getAccuracy = {
    val accuracy = multiMetrics.accuracy
    println(s"Accuracy = $accuracy")
  }
  def getRecall = {
    val recall = metrics.recallByThreshold
    recall.foreach(x => println(s"Threshold = ${x._1} Recall = ${x._2}"))
  }
}
