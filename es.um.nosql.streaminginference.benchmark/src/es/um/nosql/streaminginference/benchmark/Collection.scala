package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap

abstract class Collection(options: HashMap[String, String]) 
{

  protected val total = options("elements").toInt
  protected val entities = options("entities").toInt
  protected val versions = options("versions").toInt
  protected val depth = options("depth").toInt
  protected val fields = options("fields").toInt
  protected var currentId = 0 
  
  def output()

}