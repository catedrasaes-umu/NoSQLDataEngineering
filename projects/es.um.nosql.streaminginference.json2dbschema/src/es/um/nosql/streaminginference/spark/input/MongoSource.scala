/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.um.nosql.streaminginference.input

import org.apache.spark.sql.execution.streaming._

import java.io.{BufferedReader, InputStreamReader, IOException}
import java.net.Socket
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.{Calendar, Locale}
import javax.annotation.concurrent.GuardedBy

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

import org.apache.spark.internal.Logging
import org.apache.spark.sql._
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.sources.{DataSourceRegister, StreamSourceProvider}
import org.apache.spark.sql.types.{StringType, StructField, StructType, TimestampType}
import org.apache.spark.unsafe.types.UTF8String
import scala.collection.mutable.HashMap
import org.mongodb.scala.Subscription
import org.mongodb.scala.MongoClient
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.Observer
import org.mongodb.scala.model.Aggregates
import org.mongodb.scala.model.Filters
import com.mongodb.client.model.changestream.ChangeStreamDocument
import org.mongodb.scala.bson.collection.immutable.Document
import com.mongodb.client.model.changestream.FullDocument
import es.um.nosql.streaminginference.json2dbschema.util.inflector.Inflector

class MongoSourceProvider extends StreamSourceProvider with DataSourceRegister with Logging {
  private def parseIncludeTimestamp(params: Map[String, String]): Boolean = {
    Try(params.getOrElse("includeTimestamp", "false").toBoolean) match {
      case Success(bool) => bool
      case Failure(_) =>
        throw new Exception("includeTimestamp must be set to either \"true\" or \"false\"")
    }
  }

  /** Returns the name and schema of the source that can be used to continually read data. */
  override def sourceSchema(
      sqlContext: SQLContext,
      schema: Option[StructType],
      providerName: String,
      parameters: Map[String, String]): (String, StructType) = {
    logWarning("The socket source should not be used for production applications! " +
      "It does not support recovery.")
    if (!parameters.contains("host")) {
      throw new Exception("Set a host to read from with option(\"host\", ...).")
    }
    if (!parameters.contains("port")) {
      throw new Exception("Set a port to read from with option(\"port\", ...).")
    }
    if (schema.nonEmpty) {
      throw new Exception("The socket source does not support a user-specified schema.")
    }

    val sourceSchema =
      if (parseIncludeTimestamp(parameters)) {
        MongoSource.SCHEMA_TIMESTAMP
      } else {
        MongoSource.SCHEMA_REGULAR
      }
    ("mongo", sourceSchema)
  }

  override def createSource(
      sqlContext: SQLContext,
      metadataPath: String,
      schema: Option[StructType],
      providerName: String,
      parameters: Map[String, String]): Source = {
    val host = parameters("host")
    val port = parameters("port").toInt
    val database = parameters("database")
    new MongoSource(host, port, database, parseIncludeTimestamp(parameters), sqlContext)
  }

  /** String that represents the format that this data source provider uses. */
  override def shortName(): String = "mongo"
}


object MongoSource {
  val SCHEMA_REGULAR = StructType(StructField("value", StringType) :: Nil)
  val SCHEMA_TIMESTAMP = StructType(StructField("value", StringType) ::
    StructField("timestamp", TimestampType) :: Nil)
  val DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
}

/**
 * A source that reads text lines through a TCP socket, designed only for tutorials and debugging.
 * This source will *not* work in production applications due to multiple reasons, including no
 * support for fault recovery and keeping all of the text read in memory forever.
 */
class MongoSource(host: String, port: Int, database: String, includeTimestamp: Boolean, sqlContext: SQLContext)
  extends Source with Logging {

  
  private def onDocumentFound(document: Document, entity: String): Unit = 
  {
    
    val json = document
                .append("_type", Inflector.getInstance.singularize(entity))
                .toJson
    MongoSource.this.synchronized {
      val newData = (json,
        Timestamp.valueOf(
          MongoSource.DATE_FORMAT.format(Calendar.getInstance().getTime()))
        )
      currentOffset = currentOffset + 1
      batches.append(newData)
    }              
  }
  
  private def onCollectionsReady(collections: List[String], 
                                 db:MongoDatabase,
                                 subscriptions:HashMap[String,Subscription],
                                 subscribing:HashMap[String,Boolean]) = {
    
    val pipeline = List(Aggregates.filter(
        Filters.or(Filters.eq("operationType", "insert"), Filters.eq("operationType", "update"))))
        
    // Review state of collections
    collections.foreach(name => 
    {
      val subscription = subscriptions.getOrElse(name, null)
      // Unexisting subscription case
      if (subscription == null) 
      {
        // If subscribing were true we would have a pending subscription
        if (!subscribing.getOrElse(name, false)) 
        {
          // Register a new listener to new collection
          subscribing(name) = true
          val collection = db.getCollection(name) 
          collection
            .watch(pipeline)
            .fullDocument(FullDocument.UPDATE_LOOKUP) // Get full document on updates
            .subscribe(new Observer[ChangeStreamDocument[Document]] 
             {
                override def onSubscribe(subscription: Subscription): Unit = 
                {
                  subscriptions += name -> subscription 
                  subscribing(name) = false // Subscription is ready
                  subscription.request(Long.MaxValue)  // Get all possible changes
                  // Get documents previously added
                  collection.find().subscribe(new Observer[Document] 
                  {
                    override def onNext(document: Document): Unit =
                      onDocumentFound(document, name)
                      
                    override def onError(e: Throwable): Unit = 
                      logError("Error during collection retrieval", e)              
                    
                    override def onComplete(): Unit = 
                      logInfo("Collection retrieval completed")
                  })
                }
                
                override def onNext(document: ChangeStreamDocument[Document]): Unit = 
                  onDocumentFound(document.getFullDocument, name) 
                  
                override def onError(e: Throwable): Unit = 
                  logError("Error during Streaming", e)

                override def onComplete(): Unit = 
                  logInfo("Change Streaming Finished")
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

    val checkCollectionsInterval = 3
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
          override def onError(e: Throwable): Unit =  e.printStackTrace()
          override def onComplete(): Unit = collectionsReady = true
        })
      }

      // FIXME: Detect when thread finishes
      while (true) {
        
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
        Thread.sleep(100)
      }
      
      // Free used resources
      subscriptions.clear
      subscribing.clear
      mongoClient.close
      
    } catch {
      
      case t: Throwable =>
        logError("Error during stream processing", t)
    }
 
  }
  
  @GuardedBy("this")
  private var readThread: Thread = null

  /**
   * All batches from `lastCommittedOffset + 1` to `currentOffset`, inclusive.
   * Stored in a ListBuffer to facilitate removing committed batches.
   */
  @GuardedBy("this")
  protected val batches = new ListBuffer[(String, Timestamp)]

  @GuardedBy("this")
  protected var currentOffset: LongOffset = new LongOffset(-1)

  @GuardedBy("this")
  protected var lastOffsetCommitted : LongOffset = new LongOffset(-1)

  initialize()

  private def initialize(): Unit = synchronized {

    readThread = new Thread(s"MongoSource($host, $port)") {
      setDaemon(true)

      override def run(): Unit = {
        try {
          receive()
        } catch {
          case e: IOException =>
        }
      }
    }
    readThread.start()
  }

  /** Returns the schema of the data from this source */
  override def schema: StructType = 
    if (includeTimestamp) MongoSource.SCHEMA_TIMESTAMP
    else MongoSource.SCHEMA_REGULAR

  override def getOffset: Option[Offset] = synchronized {
    if (currentOffset.offset == -1) None
    else Some(currentOffset)
  }

  /** Returns the data that is between the offsets (`start`, `end`]. */
  override def getBatch(start: Option[Offset], end: Offset): DataFrame = synchronized {
    val startOrdinal =
      start.flatMap(LongOffset.convert).getOrElse(LongOffset(-1)).offset.toInt + 1
    val endOrdinal = LongOffset.convert(end).getOrElse(LongOffset(-1)).offset.toInt + 1

    // Internal buffer only holds the batches after lastOffsetCommitted
    val rawList = synchronized {
      val sliceStart = startOrdinal - lastOffsetCommitted.offset.toInt - 1
      val sliceEnd = endOrdinal - lastOffsetCommitted.offset.toInt - 1
      batches.slice(sliceStart, sliceEnd)
    }

    val rdd = sqlContext.sparkContext
      .parallelize(rawList)
      .map { case (v, ts) => Row(/*UTF8String.fromString(v)*/v, ts.getTime) }
    sqlContext.createDataFrame(rdd, schema)
    //sqlContext.internalCreateDataFrame(rdd, schema, isStreaming = true)
  }

  override def commit(end: Offset): Unit = synchronized 
  {
    val newOffset = LongOffset.convert(end).getOrElse(
      sys.error(s"TextSocketStream.commit() received an offset ($end) that did not " +
        s"originate with an instance of this class")
    )

    val offsetDiff = (newOffset.offset - lastOffsetCommitted.offset).toInt

    if (offsetDiff < 0) 
    {
      sys.error(s"Offsets committed out of order: $lastOffsetCommitted followed by $end")
    }

    batches.trimStart(offsetDiff)
    lastOffsetCommitted = newOffset
  }

  /** Stop this source. */
  override def stop(): Unit = synchronized 
  {

  }

  override def toString: String = s"MongoSource[host: $host, port: $port]"
}
