


import java.io.{File, FileFilter}
import java.net.URLClassLoader
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
  private val pluginPath =new File("plugin")
  private def localFeatureList: List[Plugin] =try {
    if (!pluginPath.mkdir()) {
      val fileArray = pluginPath.listFiles(new FileFilter {
        override def accept(pathname: File): Boolean = pathname.isFile && pathname.getName.lastIndexOf(".jar") != -1
      })
      val classLoader = new URLClassLoader(fileArray map { file => file.toURI.toURL })
      fileArray
        .map { file => classLoader.loadClass(file.getName.substring(file.getName.lastIndexOf(".jar"))) }
        .filter(c => c.isInstanceOf[Plugin]).toSet[Plugin].toList
    }
    else List[Plugin]()
  } catch {
    case ex =>
      featureActorProps.actorClass().asInstanceOf[PluginLoaderActor].log.error(ex,"Load local file error.")
      List[Plugin]()
  }

  def getFeatureInfo():List[(PluginInfo,String)]= localFeatureList.map(d=>(d.getPluginInfo,d.getDescription)).toList

  def enable(number:Int):Unit ={
    featureActorRef ! localFeatureList(number)
  }

  def disable(signature :Signature):Unit ={
    featureActorRef ! signature
  }

}
