


import java.security.Signature

import scala.reflect._
import akka.event.Logging
import akka.actor.{Actor, Props}
/**
  * Created by Cullen Lee on 2016/10/9.
  */


package object feature{
  import scala.collection.mutable
  import feature.Feature
  private class FeatureActor extends Actor {
    val log = Logging(context.system, this)
    private val enabledFeatureSet: mutable.Set[Feature[_]] = mutable.Set[Feature[_]]()
    import java.security.Signature
    def receive = {
      case i: Feature[_]  => // enable
        i.init
        enabledFeatureSet += i
        log.info(s"enabled ${i.getBaseInfo.name}.")
      case signature :Signature => // disable
        enabledFeatureSet.find(f => f.getBaseInfo.signature equals signature) match {
          case Some(s) =>
            enabledFeatureSet.remove(s)
            s.disable
            log.debug(s"disable ${s.getBaseInfo.name} success.")
        }
    }
  }
  private val featureActor = Launch.system.actorOf(Props[FeatureActor], "FeatureActor")
  private val featureList: mutable.MutableList[Feature[_]] = new mutable.MutableList[Feature[_]]

  def add[T <: Feature[_] : ClassTag]: Unit = {
    try {
      val _feature =  classTag[T].runtimeClass.newInstance.asInstanceOf[Feature[_]]
      featureList += _feature
    } catch {
      case ex =>
        return
    }
  }

  def getFeatureInfo():List[(BaseInfo,String)]= featureList.map(d=>(d.getBaseInfo,d.getDescription)).toList

  def enable(number:Int):Unit ={
    featureActor ! featureList(number)
  }

  def disable(signature :Signature):Unit ={
    featureActor ! signature
  }

}
