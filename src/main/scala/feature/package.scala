


import java.security.Signature

import scala.collection.mutable
import scala.reflect._
import akka.actor.{Actor, Props}
import feature.actor.PluginLoaderActor
import feature.additional.{Plugin, PluginInfo}

/**
  * Created by Cullen Lee on 2016/10/9.
  */

package object feature{
  private val featureActorProps = Props[PluginLoaderActor]
  private val featureActorRef = Launch.system.actorOf(featureActorProps, "FeatureActor")

  private val localFeatureList: mutable.MutableList[Plugin] = new mutable.MutableList[Plugin]

  def load[T <: Plugin : ClassTag](plugin:Class[T]): Unit = {
    try {
      // Auth this class can be instantiation.
      val _feature =  plugin.newInstance.asInstanceOf[Plugin]
      localFeatureList += _feature
    } catch {
      case ex => featureActorProps.actorClass().asInstanceOf[PluginLoaderActor].log.error(ex,"Load class error.")
    }
  }

  def getFeatureInfo():List[(PluginInfo,String)]= localFeatureList.map(d=>(d.getPluginInfo,d.getDescription)).toList

  def enable(number:Int):Unit ={
    featureActorRef ! localFeatureList(number)
  }

  def disable(signature :Signature):Unit ={
    featureActorRef ! signature
  }

}
