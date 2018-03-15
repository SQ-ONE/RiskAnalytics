package com.squareone.bankiq

import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.optimization.L1Updater

object SVM {
  def runModel(trainingData: RDD[LabeledPoint],testData: RDD[LabeledPoint]) = {
    val numIterations = 100

    val svmAlg = new SVMWithSGD()
    svmAlg.optimizer
      .setNumIterations(200)
      .setRegParam(0.1)
      .setUpdater(new L1Updater)

    val modelL1 = svmAlg.run(trainingData)

    val model = SVMWithSGD.train(trainingData, numIterations)

    // Clear the default threshold.
    model.clearThreshold()
    modelL1.clearThreshold()

    // Compute raw scores on the test set.
    val scoreAndLabels = testData.map { point =>
      val score = model.predict(point.features)
      (score, point.label)
    }

    val scoreAndLabelsL1 = testData.map { point =>
      val score = modelL1.predict(point.features)
      (score, point.label)
    }
    val precision = new Precision(scoreAndLabels)
    precision.areaUnderROC

    val precisionL1 = new Precision(scoreAndLabelsL1)
    precisionL1.areaUnderROC
  }
}
