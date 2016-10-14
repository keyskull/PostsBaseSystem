package feature.additional


import feature.Additional
/**
  * Created by Cullen Lee on 2016/10/9.
  */

abstract class Plugin extends java.feature.Plugin with Additional{
  def pluginDisable(): Unit = {

    disable()
  }
  def getFeatureName: String = this.pluginInfo.name
  final def getPluginInfo: PluginInfo = this.pluginInfo
}


