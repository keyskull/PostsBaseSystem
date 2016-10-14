package feature

import feature.additional.Plugin

import scala.reflect._
/**
  * Created by Jane on 2016/10/14.
  */

case class PluginClass[T <:Any :ClassTag](){
  def apply[A <:Any :ClassTag](pluginClass:PluginClass[A]):Boolean = classTag[A].runtimeClass equals (classTag[T].runtimeClass)
}

object PluginClass {
  def apply[T <:Plugin](t:T):PluginClass[_ <: Plugin] = PluginClass[t.type]()
}
