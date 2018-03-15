package com.squareone.bankiq

import org.apache.spark.sql.Encoders


object nucleus extends App{
  val file = GetFile.fileName("data").cache()
  //val data = DataPrepare.prepareTransaction(file)
  //val x = LabeledData.createLabeledData(data)
  //val trainingData = x._1
  //val testData = x._2
  val featurePrep = new LabeledData(file)
  val data = featurePrep.featureCreate("Class")
  val splits = data.randomSplit(Array(0.75, 0.25), seed = 5043l)
  val trainingData = splits(0).cache()
  val testData = splits(1).cache()
  println("\n Random Forrest Classifier \n")
  RFC.runModel(trainingData,testData)
  println("\n Logistic Regression \n")
  Logistic.runModel(trainingData,testData)
  //SVM.runModel(trainingData,testData)
  trainingData.unpersist()
  testData.unpersist()

  println("\n\n Analysis Without Time\n\n")
  //Without Time Analysis
  val dataWithoutTime = featurePrep.dropFeature("Class","Time")
  val splitsWithoutTime = dataWithoutTime.randomSplit(Array(0.75, 0.25), seed = 5043l)
  val trainingDataWithoutTime = splitsWithoutTime(0).cache()
  val testDataWithoutTime = splitsWithoutTime(1).cache()
  println("\n Random Forrest Classifier \n")
  RFC.runModel(trainingDataWithoutTime,testDataWithoutTime)
  println("\n Logistic Regression \n")
  Logistic.runModel(trainingDataWithoutTime,testDataWithoutTime)
  trainingDataWithoutTime.unpersist()
  testDataWithoutTime.unpersist()

  println("\n\n Analysis Without Amount \n\n")
  //Without Amount Analysis
  val dataWithoutAmount = featurePrep.dropFeature("Class","Amount")
  val splitsWithoutAmount = dataWithoutAmount.randomSplit(Array(0.75, 0.25), seed = 5043l)
  val trainingDataWithoutAmount = splitsWithoutAmount(0).cache()
  val testDataWithoutAmount = splitsWithoutAmount(1).cache()
  println("\n Random Forrest Classifier \n")
  RFC.runModel(trainingDataWithoutAmount,testDataWithoutAmount)
  println("\n Logistic Regression \n")
  Logistic.runModel(trainingDataWithoutAmount,testDataWithoutAmount)
}
