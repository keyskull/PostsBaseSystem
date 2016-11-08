package feature

import feature.additional.Plugin

import scala.reflect._
/**
  * Created by Jane on 2016/10/14.
  */

case class PluginClass[T <:Plugin :ClassTag](){
  def apply[A <:Plugin :ClassTag](pluginClass:PluginClass[A]):Boolean = classTag[A].runtimeClass equals (classTag[T].runtimeClass)
}

object PluginClass {
  def apply[T <:Plugin : ClassTag](t:T):PluginClass[_ <: Plugin] = PluginClass[t.type]()
}
