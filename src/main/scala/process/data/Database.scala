package process.data

/**
  * Created by Cullen Lee on 2016/10/9.
  */
abstract class Database {
  def add(key:String,value:String):Long
  def edit(key:String,value:String):Unit
  def edit(id:Long,value:String):Unit
  def remove(id:Long):Unit
  def check(id:Long):String
  def check(key:String):String

}
