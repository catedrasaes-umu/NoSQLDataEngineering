package es.um.nosql.streaminginference.spark

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.apache.spark.sql.SparkSession
import org.mongodb.scala.bson.collection.immutable.Document
import es.um.nosql.streaminginference.spark.implicits.MongoHelpers._
import org.mongodb.scala.MongoDatabase
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf


import org.apache.spark.streaming.Seconds
import es.um.nosql.streaminginference.spark.input.MongoReceiver
import es.um.nosql.streaminginference.spark.input.CustomDSFactory
import scala.collection.immutable.HashMap

@RunWith(classOf[JUnitRunner])
class MongoStreamingSuite extends MongoDBSuite 
{

  private val mongoDataSource:String = "mongo"
  
  test("Test Streaming over Mongo DataSource") 
  {
    withDatabase 
    {
      db => 
      {
        var total = List[String]()
        val conf = new SparkConf()
                        .setMaster("local[*]")
                        .setAppName("StreamingInference")
        val ssc = new StreamingContext(conf, Seconds(1))
        val stream = ssc
                      .receiverStream(new MongoReceiver(
                          host = MongoDB.host, 
                          port = MongoDB.port, 
                          database = MongoDB.dbName))
                          
        stream.foreachRDD(rdd => 
          rdd
            .collect()
            .map { case (path, value) => total = value :: total }
        )
        
        ssc.start()
        // Start thread which will insert 100 records
        val changer = new MongoChanger("Mongo Streaming Changer", db)
        changer.start() 
        // Wait for initialization & stream processing
        Thread.sleep(20000)
        // Check if count matches number of insertions
        assert(total.size == 100, "Changes weren't succesfully loaded")
        ssc.stop(true)          
      }
    }
  }
}
