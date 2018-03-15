name := "CreditCardFraud"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.2.0",
  "org.apache.spark" % "spark-sql_2.11" % "2.2.0"
)

libraryDependencies += "com.typesafe" % "config" % "1.3.2"

libraryDependencies +=   "org.apache.spark" % "spark-mllib_2.11" % "2.2.0"