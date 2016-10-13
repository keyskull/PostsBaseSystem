package feature

import java.security.Signature

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
case class BaseInfo(name:String,version:String,signature: Signature,src:List[String]=List[String]()){

}

