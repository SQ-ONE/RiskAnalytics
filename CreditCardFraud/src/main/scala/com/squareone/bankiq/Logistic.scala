package com.squareone.bankiq

import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD

object Logistic {
  def runModel(trainingData: RDD[LabeledPoint], testData: RDD[LabeledPoint]) = {
    val model = new LogisticRegressionWithLBFGS()
      .setNumClasses(2)
      .run(trainingData)

    val predictionAndLabels = testData.map { case LabeledPoint(label, features) =>
      val prediction = model.predict(features)
      (prediction, label)
    }
    val precision = new Precision(predictionAndLabels)
    precision.getAccuracy
    precision.getPrecision
    precision.getRecall
    precision.getPRR
    precision.getF1Score
    precision.getFScore
    precision.getAUPRC
    precision.areaUnderROC
  }
}
