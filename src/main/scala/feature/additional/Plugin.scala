package feature.additional

import java.feature.Api

import feature.{PluginClass, UseOtherPluginApi}

import scala.collection.mutable


/**
  * Created by Cullen Lee on 2016/10/9.
  */

abstract class Plugin extends java.feature.Plugin {
  val useOtherPluginApi = UseOtherPluginApi()
  def getPluginName: String
  final def pluginInit():Unit ={

    this.init()
  }
  final def pluginDisable(): Unit = {

    this.disable()
  }
  final def getPluginInfo: PluginInfo = this.pluginInfo()
}


