package com.squareone.bankiq

import com.typesafe.config.{Config, ConfigFactory}
import com.squareone.util._

trait FileNameServices

class FileNameService extends FileNameServices{
}

object FileNameService{
  val path1 = ConfigFactory.load("application.conf")
  def getPath(key: String,path: Config = path1): String= {
    val entityPath = path.getString(key)
    Utils.getAbsPathOfResource(entityPath)
  }
}