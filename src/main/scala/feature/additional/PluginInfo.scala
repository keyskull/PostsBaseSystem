package feature.additional

import java.security.Signature
import java.util

/**
  * Created by Cullen Lee on 2016/10/12.
  */

/**
  * @param name
  * @param version
  * @param signature
  * @param src
  *
  */
case class PluginInfo(name:String, version:String, signature: Signature, src:java.util.List[String]= new util.ArrayList[String]()){
  def checkIsAuthStatus() ={}
}

