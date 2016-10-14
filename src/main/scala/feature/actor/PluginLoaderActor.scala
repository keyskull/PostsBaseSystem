package feature.actor

import akka.actor.Actor
import akka.event.Logging
import feature.{PluginClass, UseOtherPluginApi}
import feature.additional.{Plugin, PluginInfo}

import scala.collection.mutable

/**
  * Created by Cullen Lee on 2016/10/14.
  *
  */
class PluginLoaderActor extends Actor {
  val log = Logging(context.system, this)
  private val enabledFeatureSet: mutable.Set[Plugin] = mutable.Set[Plugin]()

  def receive = {
    case i: Plugin if !enabledFeatureSet(i) => // enable
      enabledFeatureSet += i
      if (i.isInstanceOf[UseOtherPluginApi]) {
        val hasAdditionalPlugin = i.asInstanceOf[UseOtherPluginApi]
        hasAdditionalPlugin.additionalInit(
        enabledFeatureSet.map { f =>
            hasAdditionalPlugin.getUsedPlugin.find(c => c(PluginClass[f.type])) match{
              case Some(s)  => (s, f)
            }
        }.toSet)}
      i.pluginInit()
      log.info(s"Enabled ${i.getPluginInfo.name}.")
    case baseInfo: PluginInfo => // disable
      enabledFeatureSet.find(f => f.getPluginInfo equals baseInfo) match {
        case Some(s) =>
          s.pluginDisable()
          enabledFeatureSet.remove(s)
          java.lang.System.gc()
          log.debug(s"Disable ${s.getPluginInfo.name} success.")
        case _ => log.debug("Not found this class.")
      }
    case _ =>
      log.info("Received unknown message")
  }
}
