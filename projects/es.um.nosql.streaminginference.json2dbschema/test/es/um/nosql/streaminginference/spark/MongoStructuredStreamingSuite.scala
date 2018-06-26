package es.um.nosql.streaminginference.spark

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.apache.spark.sql.SparkSession
import org.mongodb.scala.bson.collection.immutable.Document
import es.um.nosql.streaminginference.spark.implicits.MongoHelpers._
import org.mongodb.scala.MongoDatabase

@RunWith(classOf[JUnitRunner])
class MongoStructuredStreamingSuite extends MongoDBSuite 
{

  private val mongoDataSource:String = "mongo"
  
  test("Test Structured Streaming over Mongo DataSource") 
  {
    withDatabase 
    {
      db => 
      {
        
        val spark = SparkSession
                    .builder
                    .master("local[*]")
                    .appName("StructuredStreamingTest")
                    .getOrCreate()
                    
        // Initialize Streamed Dataframe            
        val counts = spark
                      .readStream
                      .format(mongoDataSource)
                      .option("host", MongoDB.host)
                      .option("port", MongoDB.port)
                      .option("database", MongoDB.dbName)
                      .load()
                      .groupBy("value")
                      .count()
        // Write output to an in-memory table called output 
        val query = 
          counts    
          .writeStream
          .outputMode("complete")
          .format("memory")
          .queryName("output")
          .start()
        // Start thread which will insert 100 records
        val changer = new MongoChanger("Mongo Streaming Changer", db)
        changer.start() 
        // Wait for initialization & stream processing
        Thread.sleep(20000)
        // Check if count matches number of insertions
        assert(spark.sql("SELECT * FROM output").count() == 100, "Changes weren't succesfully loaded")
        query.stop          
      }
    }
  }
}
