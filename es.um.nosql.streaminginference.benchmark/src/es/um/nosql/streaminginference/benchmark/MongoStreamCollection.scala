package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap

class MongoStreamCollection (options: HashMap[String, String]) extends MongoCollection(options) 
{
  protected val batchSize = options("batch").toInt
  protected val batchDelay = options("delay").toInt
  
  override def output()
  {
    val db = connect()
    //Await.result(db.drop().toFuture(), Duration(10, TimeUnit.SECONDS))
    removeCollections(db)
    val batches = if (total % batchSize == 0) total/batchSize else total/batchSize+1    
    for (i <- 0 to batches-1)
    {
      insert(db, Math.min(batchSize, total-(batchSize*i)))
      Thread.sleep(batchDelay)
    }
  }
}