import java.security.Signature

import feature.{BaseInfo, Feature}

/**
  * Created by Cullen Lee on 2016/10/10.
  */
class Base extends Feature{
  override def init(): Unit = {

  }

  override def disable(): Unit = {

  }


  override def getDescription: String = "yo"


  override val baseInfo: BaseInfo = BaseInfo(name="Base",version = "1.0",signature = Signature.getInstance("yo"))

}
