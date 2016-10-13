package feature


/**
  * Created by Cullen Lee on 2016/10/10.
  */
trait Additional {
  import scala.collection.mutable
  val dependFeature: mutable.MutableList[(String, Int)] = new mutable.MutableList[(String, Int)]
  val optionalFeature: mutable.MutableList[(String, Int)] = new mutable.MutableList[(String, Int)]
}

trait Depend[T <: Feature[_]] extends Additional {
  private val c = classOf[Class[T]]
  dependFeature += ((c.getName, c.hashCode()))
}

trait Optional[T <: Feature[_]] extends Additional {
  private val c = classOf[Class[T]]
  optionalFeature += ((c.getName, c.hashCode()))
}
