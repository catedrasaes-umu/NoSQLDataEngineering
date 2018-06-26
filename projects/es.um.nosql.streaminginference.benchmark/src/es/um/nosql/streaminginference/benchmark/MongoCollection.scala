package es.um.nosql.streaminginference.benchmark

import java.util.concurrent.TimeUnit

import scala.collection.immutable.HashMap
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import org.mongodb.scala.MongoClient
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document

class MongoCollection (options: HashMap[String, String]) extends Collection(options) 
{
  protected val username = options.get("user")
  protected val password = options.get("password")
  protected val host = options.get("host")
  protected val port = options.get("port")
  protected val database = options("database")
  
  protected def connect(): MongoDatabase =
  {
    var connectionStr = host.get+":"+port.get
    if (username.isDefined && password.isDefined)
    {
      connectionStr = username.get + ":" + password.get + "@" + connectionStr
    }
    val client: MongoClient = MongoClient("mongodb://"+connectionStr)
    client.getDatabase(database)
  }
    
  protected def insert(db:MongoDatabase, total:Int) = {
    for (i <- 0 to total-1)
    {
      val name = "entity-" + (i*entities/total)
      val version = (i%versions) + 1
      val collection = db.getCollection(name)
      val agg = new Aggregate(fields=fields, version=version, depth=depth-1)
      val doc = Document("{\"_id\": \""+currentId+"\",\"_type\": \""+name+"\","+agg.toJsonStr(1)+"})")
      Await.result(collection.insertOne(doc).toFuture(), Duration(10, TimeUnit.SECONDS))
      currentId += 1
    }
  }
  
  override def output()
  {
    val db = connect()
    Await.result(db.drop().toFuture(), Duration(10, TimeUnit.SECONDS))
    
    insert(db, total)
  }
}