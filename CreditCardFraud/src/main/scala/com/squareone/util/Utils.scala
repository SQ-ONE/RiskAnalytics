package com.squareone.util

object Utils {
  def getAbsPathOfResource(resName: String) =
    Utils.getClass.getResource(resName).getPath
}
