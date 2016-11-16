
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
  * Created by Cullen Lee on 2016/10/10.
  */
object Launch {
  val system = ActorSystem("PostBaseSystem")
  def main(arge:Array[String]) = {
    ConfigFactory.parseString("""akka.remote.netty.tcp.hostname="keyskull.me"""")
      .withFallback(ConfigFactory.load());
  }
}
