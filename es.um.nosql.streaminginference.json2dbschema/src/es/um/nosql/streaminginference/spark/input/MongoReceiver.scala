package es.um.nosql.streaminginference.spark.input

import scala.collection.mutable.HashMap

import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.mongodb.scala.MongoClient
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.Observer
import org.mongodb.scala.Subscription
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.documentToUntypedDocument
import org.mongodb.scala.model.Aggregates
import org.mongodb.scala.model.Filters

import com.mongodb.client.model.changestream.ChangeStreamDocument
import com.mongodb.client.model.changestream.FullDocument

import es.um.nosql.streaminginference.json2dbschema.util.inflector.Inflector

class MongoReceiver (host: String, port: Int, database: String)
  extends Receiver[(String, String)](StorageLevel.MEMORY_AND_DISK_2) with Logging {
  
  def onStart() {

    new Thread("Mongo Receiver") {
      override def run() { receive() }
    }.start()
    
  }
  
  private def onCollectionsReady(collections: List[String], 
                                 db:MongoDatabase,
                                 subscriptions:HashMap[String,Subscription],
                                 subscribing:HashMap[String,Boolean]) = {
    
    
    val pipeline = List(Aggregates.filter(
        Filters.or(Filters.eq("operationType", "insert"), Filters.eq("operationType", "update"))))
        
    // Review state of collections
    collections.foreach(name => {
      val subscription = subscriptions.getOrElse(name, null)
      // Unexisting subscription case
      if (subscription == null) {
        // If subscribing were true we would have a pending subscription
        if (!subscribing.getOrElse(name, false)) {
          // Register a new listener to new collection
          subscribing(name) = true
          val collection = db.getCollection(name) 
          collection
            .watch(pipeline)
            .fullDocument(FullDocument.UPDATE_LOOKUP) // Get full document on updates
            .subscribe(new Observer[ChangeStreamDocument[Document]] {
                override def onSubscribe(subscription: Subscription): Unit = {
                  subscriptions += name -> subscription 
                  subscribing(name) = false // Subscription is ready
                  subscription.request(Long.MaxValue)  // Get all possible changes
                }
                override def onNext(document: ChangeStreamDocument[Document]): Unit = { 
                  val json = document
                              .getFullDocument
                              .append("_type", Inflector.getInstance.singularize(name))
                              .toJson

                  store((database, json))  // Directly send content to nodes
                }
                override def onError(e: Throwable): Unit = restart("Could not get collection " + name + " changes ", e)
                // We will never reach this point
                override def onComplete(): Unit = println("Completed")
          })
        }
        
      } else if (subscription.isUnsubscribed()) {
        // Subscription has been cancelled -> remove subscription
        subscriptions -= name
        subscribing -= name
      }
    })

  }
  
  private def receive() {

    val checkCollectionsInterval = 100
    var collectionsReady = true
    var collectionsChecked = false
    var collections:List[String] = List()
    val subscriptions:HashMap[String,Subscription] = HashMap()
    val subscribing:HashMap[String,Boolean] = HashMap()
    var it = checkCollectionsInterval
 
    
    try {
    
      val mongoClient: MongoClient = MongoClient("mongodb://"+host+":"+port)
      val db:MongoDatabase = mongoClient.getDatabase(database)
      // Periodically check collections from database to see if a new collection is added or deleted
      def checkCollections() {
  
        collectionsReady = false
        collectionsChecked = false
        collections = List()
        // Fill collection names
        db
        .listCollectionNames()
        .subscribe(new Observer[String] {
          override def onNext(collection: String): Unit = collections = collection :: collections
          override def onError(e: Throwable): Unit = restart("Could not retrieve collection names", e)
          override def onComplete(): Unit = collectionsReady = true
        })
      }

      while (!isStopped) {
        
        // Wait until checkCollections is Completed
        if (collectionsReady && !collectionsChecked) {
          // Refresh state of collections        
          onCollectionsReady(collections, db, subscriptions, subscribing)
          collectionsChecked = true
        }
  
        // Check periodically existing collections in database
        if (collectionsChecked) {
          if (it >= checkCollectionsInterval) {
            it = 0
            checkCollections()
          }
          it+=1  
        }
  
        // Thread waiting
        Thread.sleep(200)
      }
      
      // Free used resources
      subscriptions.foreach {
        case (name, subscription) => if (!subscription.isUnsubscribed()) subscription.unsubscribe()
      }
      subscriptions.clear
      subscribing.clear
      mongoClient.close
      
    } catch {
      case e: java.net.ConnectException =>
        // restart if could not connect to server
        restart("Error connecting to " + host + ":" + port, e)
      case t: Throwable =>
        // restart if there is any other error
        restart("Error receiving data", t)
    }
 
  }

  def onStop() {
    // There is nothing much to do as the thread calling receive()
    // is designed to stop by itself if isStopped() returns false
  }

}