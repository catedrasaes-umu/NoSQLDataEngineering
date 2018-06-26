package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.MongoClient
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.Observer
import org.mongodb.scala.Subscription

class MongoStressFind(options: HashMap[String, String]) {
  
  protected val username = options.get("user")
  protected val password = options.get("password")
  protected val host = options.get("host")
  protected val port = options.get("port")
  protected val database = options("database")
  
  protected def connect(db:String = ""): MongoDatabase =
  {
    var connectionStr = host.get+":"+port.get
    if (username.isDefined && password.isDefined)
    {
      connectionStr = username.get + ":" + password.get + "@" + connectionStr
    }
    val client: MongoClient = MongoClient("mongodb://"+connectionStr)
    client.getDatabase(db)
  }
  
  private def findDB(dbName: String) = 
  {
    println("Connecting to " + dbName)
    val db:MongoDatabase = connect(dbName)
    var collections:List[String] = List()
    
    db
    .listCollectionNames()
    .subscribe(new Observer[String] 
    {
      override def onNext(collection: String): Unit = collections = collection :: collections
      override def onError(e: Throwable): Unit = {}
      override def onComplete(): Unit = 
      {
        collections.foreach(collection => 
        {
          db.getCollection(collection).find().subscribe(new Observer[Document] 
          {
            var batchSize: Long = 10
            var seen: Long = 0
            var subscription: Option[Subscription] = None
            
            override def onSubscribe(subscription: Subscription): Unit = {
              this.subscription = Some(subscription)
              subscription.request(batchSize)
            }
            
            override def onNext(document: Document): Unit = 
            {
              val json = document.append("_type", collection).toJson
//              println(json)
              seen += 1
              if (seen == batchSize) 
              {
                seen = 0
                subscription.get.request(batchSize)
              }
            }
            
            override def onError(e: Throwable): Unit = {}             
            override def onComplete(): Unit = {}
          })  
        })
      }
    })
  }
  
  def findAll() = {
    
    database.split(',').map(_.trim).foreach(db => 
    {
      new Thread("Mongo Finder") 
      {
        private val database = db
        override def run() 
        { 
          findDB(db) 
        }
      }
      .start()  
    })
  }
  
}