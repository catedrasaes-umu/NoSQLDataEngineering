package es.um.nosql.streaminginference.spark

import es.um.nosql.streaminginference.spark.implicits.MongoHelpers._

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.mongodb.scala.MongoClient
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.Filters._



object MongoDB 
{
  
  private[spark] val port = 27017
  private[spark] val host = "localhost"
  private[spark] val dbName = "testSuite"
  private[spark] var client:MongoClient = null
  private[spark] val collectionName:String = "testCol"
  
  def setUp: MongoDatabase = 
  {
    client = MongoClient("mongodb://"+host+":"+port)
    client.getDatabase(dbName)
  }
  
  def tearDown: Unit = 
  { 
    client.getDatabase(dbName).drop().result()
    client.close
  }

}

// Thread which will do background changes on selected database
class MongoChanger(name: String, db: MongoDatabase) extends Thread(name) {
  override def run() { 
    Thread.sleep(5000)
    val collection = db.getCollection(MongoDB.collectionName)
    for (i <- 1 to 100) {
      Thread.sleep(50)
      collection.insertOne(Document("_id" -> i, "name" -> ("test"+i))).results()
    }
  }
}

@RunWith(classOf[JUnitRunner])
class MongoDBSuite extends StreamingInferenceSuite 
{

  def withDatabase(testCode: MongoDatabase => Any) 
  {
    val db = MongoDB.setUp
    try testCode(db)
    finally MongoDB.tearDown
  }
  
  test("Test connection") 
  {
    withDatabase 
    {
      db => 
      {
        db.listCollectionNames().results() 
        assert(true)
      }
    }
  }
  
  test("Creation of a collection is a lazy process") 
  { 
    withDatabase 
    { 
      db => 
        {
          db.createCollection(MongoDB.collectionName)
          // Collection creation is lazy
          val collections = db.listCollectionNames().results()
          assert(collections.isEmpty)
        }
    } 
  }
  
  test("CRUD over MongoDB") 
  {
    withDatabase
    {
      db => 
        {
          val document: Document = Document("_id" -> 1, "name" -> "test")
          val collection: MongoCollection[Document] = db.getCollection(MongoDB.collectionName)
          collection.insertOne(document).results()
          val collections = db.listCollectionNames().results()
          assert(collections.contains(MongoDB.collectionName), "Collection has not been created")
          var result:Document = collection.find(equal("_id", 1)).first().result()
          assert(result != null && !result.isEmpty, "Element has not been inserted")
          collection.updateOne(equal("_id", 1), set("name", "test2")).results()
          result = collection.find(equal("name", "test2")).first().result()
          assert(result != null && !result.isEmpty, "Element has not been updated")
          collection.deleteOne(equal("_id", 1)).results()
          result = collection.find(equal("_id", 1)).first().result() 
          assert(result == null, "Element has not been deleted")
        }
    }
  }  
  
}
