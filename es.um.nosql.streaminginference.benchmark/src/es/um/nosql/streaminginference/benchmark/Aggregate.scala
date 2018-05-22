package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap
import org.mongodb.scala.bson.collection.immutable.Document

class Aggregate(fields:Int, version:Int, depth:Int) 
{
  private lazy val primitives:HashMap[String, String] = 
  {
    var prim = HashMap[String,String]()
    for (i <- 1 to fields) 
      prim += "field-" + i -> "test"
    prim
  }
  
  private lazy val aggregate:Option[(String, Aggregate)] = 
  {
    if (depth > 0) 
    {
      val agg = new Aggregate(fields=fields, version = 0, depth = depth-1)
      Some("entity-0", agg)
    }
    else 
      None
  }

  def toDocument():Document = {
    val document: Document = Document("_id" -> 1, "name" -> "test")
    document
  }
  
  def toJsonStr(depth: Int):String = {
    
    val indent = 6+(2*depth)
    var str:String = primitives.map 
    {
      case (name, value) => " "*indent+"\""+name+"\": \"" + value + "\""
    }
    .mkString(",\n")
    if (aggregate.isDefined)
    {
      val agg = aggregate.get
      str += ",\n"+
             " "*indent +
             "\"" + 
             agg._1 + 
             "\":\n"+
             " "*indent +
             "{\n" + 
             agg._2.toJsonStr(depth+1) + 
             "\n" +
             " "*indent +
             "}"
    }
    
    if (version > 0) 
    {
      if (!str.isEmpty()) str += ",\n"
      str += " "*indent + "\"version-"+version+"\": true"
    }
    str
  }
}