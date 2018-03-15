package com.squareone.bankiq

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.tree.configuration.{Algo, Strategy}
import org.apache.spark.mllib.tree.impurity.Gini
import org.apache.spark.rdd.RDD

object RFC {
  def runModel(trainingData: RDD[LabeledPoint], testData: RDD[LabeledPoint]) = {
    val algorithm = Algo.Classification
    val impurity = Gini
    val maximumDepth = 5
    val treeCount = 10
    val featureSubsetStrategy = "auto"
    val seed = 5043


    val model = RandomForest.trainClassifier(trainingData, new Strategy(algorithm, impurity, maximumDepth),
      treeCount, featureSubsetStrategy, seed)

    val labeledPredictions = testData.map { labeledPoint =>
      val predictions = model.predict(labeledPoint.features)
      //if (predictions == 1) println((labeledPoint.label, predictions))
      (labeledPoint.label, predictions)
    }

    val precision = new Precision(labeledPredictions)
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

