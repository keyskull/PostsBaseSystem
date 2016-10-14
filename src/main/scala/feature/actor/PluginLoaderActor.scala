package feature.actor

import akka.actor.Actor
import akka.event.Logging
import feature.additional.{Plugin, PluginInfo}

import scala.collection.mutable

/**
  * Created by Cullen Lee on 2016/10/14.
  */
class PluginLoaderActor extends Actor {
  val log = Logging(context.system, this)
  private val enabledFeatureSet: mutable.Set[Plugin] = mutable.Set[Plugin]()
  def receive = {
    case i: Plugin if !enabledFeatureSet(i) => // enable
      enabledFeatureSet += i
      i.init
      log.info(s"enabled ${i.getPluginInfo.name}.")
    case baseInfo :PluginInfo => // disable
      enabledFeatureSet.find(f => f.getPluginInfo equals baseInfo) match {
        case Some(s) =>
          s.pluginDisable()
          enabledFeatureSet.remove(s)
          java.lang.System.gc()
          log.debug(s"disable ${s.getPluginInfo.name} success.")
        case _ => log.debug("Not found this class.")
  }
    case _ =>
      log.info("received unknown message")
  }
}
