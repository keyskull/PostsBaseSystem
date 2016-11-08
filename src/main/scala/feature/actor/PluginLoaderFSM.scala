package feature.actor

import java.feature.Api

import akka.actor.{Actor, ActorLogging, ActorRef, FSM, Props}
import feature.{PluginClass, UseOtherPluginApi}
import feature.additional.{Plugin, PluginInfo}

import scala.collection.mutable

/**
  * Created by Cullen Lee on 2016/10/14.
  *
  */

trait Command
case object Start extends Command
case object Reload extends Command
case object Stop extends Command

sealed trait State
case object NotReady extends State
case object Ready extends State
case object Loading extends State
case object Reloading extends State

sealed trait Data
case object Uninitialized extends Data
case class AddPlugin(rsf:ActorRef) extends Data
case object LoadPlugin extends Data
case class PluginApi(api:Api) extends Data

class PluginLoaderFSM extends FSM[State,Data] with ActorLogging{
  private val enabledFeatureSet: mutable.Set[Plugin] = mutable.Set[Plugin]()
  class PluginLoader extends Actor{
    override def receive = {
      case i: Plugin if !enabledFeatureSet(i) => // enable
        enabledFeatureSet += i
        if (i.isInstanceOf[UseOtherPluginApi]) {
          val hasAdditionalPlugin = i.asInstanceOf[UseOtherPluginApi]
          hasAdditionalPlugin.additionalInit(
            enabledFeatureSet.map { f =>
              hasAdditionalPlugin.getUsedPlugin.find(c => c(PluginClass[f.type])) match {
                case Some(s) => (s, f.getApi)
              }
            }.toSet)
        }
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
  startWith(NotReady,AddPlugin(context.actorOf(Props[PluginLoader],"PluginLoader")))

  when(NotReady){
    case Event(plugin :Plugin,AddPlugin(loader)) =>
      loader ! plugin
      stay()
    case Event(Start,Uninitialized) => goto(Loading) using(LoadPlugin)
  }

  when(Loading){
    case Event(_,LoadPlugin) =>
      enabledFeatureSet map (d => d.init())
      goto(Ready)
  }

  when(Ready){
    case Event(_,_) => stay()
  }

  when(Reloading){
    FSM.NullFunction
  }

  initialize()
}
