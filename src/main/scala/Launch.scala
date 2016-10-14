import java.io.{File, FileFilter}
import java.net.URLClassLoader

import akka.actor.ActorSystem
import feature.additional.Plugin

/**
  * Created by Cullen Lee on 2016/10/10.
  */
object Launch {
  val system = ActorSystem("PostBaseSystem")

}
