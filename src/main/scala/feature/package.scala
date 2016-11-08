


import java.io.{File, FileFilter}
import java.net.URLClassLoader
import java.security.Signature

import akka.actor.Props
import feature.actor.PluginLoaderFSM
import feature.additional.{Plugin, PluginInfo}

/**
  * Created by Cullen Lee on 2016/10/9.
  */

package object feature{
  val featureActorProps = Props[PluginLoaderFSM]
  val featureActorRef = Launch.system.actorOf(featureActorProps, "FeatureActor")
  val pluginPath =new File("plugin")
   def localFeatureList: List[Plugin] =try {
    if (!pluginPath.mkdir()) {
      val fileArray = pluginPath.listFiles(new FileFilter {
        override def accept(pathname: File): Boolean = pathname.isFile && pathname.getName.lastIndexOf(".jar") != -1
      })
      val classLoader = new URLClassLoader(fileArray map { file => file.toURI.toURL })
      fileArray
        .map { file => classLoader.loadClass(file.getName.substring(file.getName.lastIndexOf(".jar"))) }
        .filter(c => c.isInstanceOf[Plugin]).map(c=> c.asInstanceOf[Plugin]).toList
    }
    else List[Plugin]()
  } catch {
    case ex =>
      featureActorProps.actorClass().asInstanceOf[PluginLoaderFSM].log.error(ex,"Load local file error.")
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
