package feature

import java.feature.Api

import feature.additional.Plugin

/**
  * Created by Cullen Lee on 2016/10/14.
  */


/**
  * @note They Trait use for Scala Plugin develop.
  */
trait Additional {
  import scala.collection.mutable
  protected val dependFeature: mutable.Set[PluginClassBox[_ <: Plugin]] =  mutable.Set[PluginClassBox[_ <: Plugin]]()
  protected val optionalFeature: mutable.Set[PluginClassBox[_ <: Plugin]] = mutable.Set[PluginClassBox[_ <: Plugin]]()
}

trait Depend[T <: Plugin] extends Additional {
  this.dependFeature += PluginClassBox[T]
}

trait Optional[T <: Plugin] extends Additional {
  this.optionalFeature += PluginClassBox[T]
}