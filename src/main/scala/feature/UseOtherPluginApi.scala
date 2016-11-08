package feature

import java.feature.Api

import feature.additional.Plugin

import scala.collection.mutable
import scala.reflect.ClassTag

/**
  * Created by Cullen Lee on 2016/10/14.
  */


/**
  * @note They Trait support for Scala Plugin develop.
  */
class UseOtherPluginApi(pluginClass: PluginClass[Plugin]*) {
  private val pluginSet: Set[PluginClass[_ <: Plugin]] = pluginClass.toSet
  private val apiSet: mutable.Set[(PluginClass[_ <: Plugin],_ <: Api)] = mutable.Set[(PluginClass[_ <: Plugin],_ <: Api)]()
  def getUsedPlugin:Set[PluginClass[_ <: Plugin]] = pluginSet
  def getPluginApi[T <: Plugin :ClassTag]():Option[Api] = apiSet.find(d => d._1.apply(PluginClass[T])) map (d => d._2)
  def additionalInit(apiSet: Set[(PluginClass[_ <: Plugin],_ <: Api)]) = this.apiSet ++= apiSet
}

object UseOtherPluginApi{
  def apply(): UseOtherPluginApi = new UseOtherPluginApi()
  def apply(pluginClass: PluginClass[Plugin]*): UseOtherPluginApi = new UseOtherPluginApi(pluginClass:_*)
}