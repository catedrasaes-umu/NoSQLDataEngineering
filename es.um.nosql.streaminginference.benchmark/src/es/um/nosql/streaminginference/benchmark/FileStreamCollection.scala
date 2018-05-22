package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap
import java.io._

class FileStreamCollection(options: HashMap[String, String]) extends FileCollection(options) 
{
  protected val batchSize = options("batch").toInt
  protected val batchDelay = options("delay").toInt
  
  override def output() = 
  {
    val batches = if (total % batchSize == 0) total/batchSize else total/batchSize+1    
    for (i <- 0 to batches-1)
    {
      val file = new File(path.dropRight(5)+"_"+(i+1)+".json")
      val buff = new BufferedWriter(new FileWriter(file))
      val elements = Math.min(batchSize, total-(batchSize*i))
      buff.write(toJsonStr(elements))
      buff.close()
      Thread.sleep(batchDelay)
    }
  }

}