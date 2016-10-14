package feature

import feature.additional.Plugin

/**
  * Created by Jane on 2016/10/14.
  */

case class PluginClassBox[T <:Plugin](){
  def apply(pluginBox:PluginClassBox[_]):Boolean = this eq pluginBox
}

object PluginClassBox {
  def apply[T <:Plugin](t:T):PluginClassBox[_ <: Plugin] = PluginClassBox[t.type]()
}
