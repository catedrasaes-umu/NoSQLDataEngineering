package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap
import java.io._

class FileCollection(options: HashMap[String, String]) extends Collection(options) 
{
  protected val path = options("output")
  
  def toJsonStr(elements:Int=total):String = 
  {
    var str =
"""{
  "rows": [
"""; 
    
    for (i <- 0 to elements-1) 
    {
      val name = "entity-" + (i*entities/elements)
      val agg = new Aggregate(fields=fields, version=(i%versions) + 1, depth=depth-1)
      str += 
"""
      {
        "_id": """"+currentId+"""",
        "_type": """"+name+"""",
""" + 
        agg.toJsonStr(1) +
"""
      },"""
      currentId += 1
    }
    str = str.dropRight(1)
    str += "\n  ]\n}"
    str
  }
  
  override def output() = 
  {
    val file = new File(path)
    val buff = new BufferedWriter(new FileWriter(file))
    buff.write(toJsonStr())
    buff.close()
  }

}