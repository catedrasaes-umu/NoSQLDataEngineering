package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap
import org.mongodb.scala.MongoDatabase
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import org.mongodb.scala.model.Filters

class MongoStreamCollection (options: HashMap[String, String]) extends MongoCollection(options) 
{
  protected val batchSize = options("batch").toInt
  protected val batchDelay = options("delay").toInt
  
  protected def removeCollections(db:MongoDatabase) = {
    val collections = Await.result(db.listCollectionNames().toFuture(), Duration(10, TimeUnit.SECONDS))
    collections.foreach(collection => 
      Await.result(db.getCollection(collection).deleteMany(Filters.where("true")).toFuture(), Duration(1000, TimeUnit.SECONDS)))
  }
  
  override def output()
  {
    val db = connect()
    removeCollections(db)
      
    val batches = if (total % batchSize == 0) total/batchSize else total/batchSize+1    
    for (i <- 0 to batches-1)
    {
      insert(db, Math.min(batchSize, total-(batchSize*i)))
      Thread.sleep(batchDelay)
    }
  }
}