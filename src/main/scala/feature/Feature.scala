package feature

import scala.reflect._

/**
  * Created by Jane on 2016/10/9.
  */


abstract class Feature[API : ClassTag] extends ApiInterface {
  protected val baseInfo: BaseInfo
  def init: Unit
  def disable: Unit
  def getDescription: String
  def getFeatureName: String = baseInfo.name
  final def getApi = getApiClass.apply()
  final override def getBaseInfo: BaseInfo = baseInfo
  final override def getApiClass(): Api[API] = new Api[API]{def Api() = classTag[API].runtimeClass.newInstance().asInstanceOf[API]}

}


