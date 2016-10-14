package feature

import java.security.Signature

import feature.additional.{Plugin, PluginInfo}

/**
  * Created by Cullen Lee on 2016/10/10.
  */
class Base extends Plugin {
  override def init(): Unit = {

  }

  override def disable(): Unit = {

  }

  override def getDescription: String = "This is Base feature."

  override val pluginInfo: PluginInfo = PluginInfo(name="feature.Base",version = "1.0",signature = Signature.getInstance("s"))

  override def getPluginName: String = pluginInfo.name
}
