package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap
import java.io._

class FileCollection(options: HashMap[String, String]) extends Collection(options) 
{
  protected val path = options("output")
  
  def toJsonFileStr(elements:Int=total, filePath:String = path): Unit = 
  {
    val file = new File(filePath)
    val buff = new BufferedWriter(new FileWriter(file))
    
    val heading =
"""{
  "rows": [
"""; 
    
    buff.write(heading)
    for (i <- 0 to elements-1) 
    {
      val name = "entity-" + (i*entities/elements)
      val agg = new Aggregate(fields=fields, version=(i%versions) + 1, depth=depth-1)
      var elementStr = 
"""
      {
        "_id": """"+currentId+"""",
        "_type": """"+name+"""",
""" + 
        agg.toJsonStr(1) +
"""
      }"""
        
      if (i < elements-1) 
        elementStr += ","

      currentId += 1
      buff.write(elementStr)
    }
    
    buff.write("\n  ]\n}")
    buff.close()
  }
  
  override def output() = 
  {
    toJsonFileStr()
    
  }

}