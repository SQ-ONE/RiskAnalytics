package com.squareone.bankiq

import org.apache.spark.sql.SQLContext

trait GetFiles

class GetFile extends GetFiles{
}

object GetFile{
  val session = SparkService.getSparkSession()
  val sqlContext = session.sqlContext

  def fileName(k: String, sqlContext: SQLContext = sqlContext ) = {
    sqlContext.read.option("header","true").csv(FileNameService.getPath(k))
  }
  def fileNameLibsvm(k: String, sqlContext: SQLContext = sqlContext)= {
    sqlContext.read.format("libsvm").load(FileNameService.getPath(k))
  }
}
