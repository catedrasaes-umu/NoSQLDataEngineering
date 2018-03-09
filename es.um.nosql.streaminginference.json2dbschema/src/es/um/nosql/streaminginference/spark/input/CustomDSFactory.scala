package es.um.nosql.streaminginference.spark.input

import java.nio.file.Paths

import scala.collection.immutable.HashMap

import org.apache.hadoop.io.Text
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream


object CustomDSFactory
{
    
  private def createFileDS(ssc: StreamingContext, inputDir:String): DStream[(String, String)] =
  {
    ssc
      // Based on: https://halfvim.github.io/2016/06/28/FileInputDStream-in-Spark-Streaming/
      .fileStream[Text, Text, WholeTextInputFormat](inputDir)
      .map { case (filePath, content) => 
              (Paths.get(filePath.toString).getFileName.toString.split("\\_")(0), content.toString) }
  }
  
  private def createMongoDS(ssc: StreamingContext, host:String, port: Int, database: String, user: Option[String], password: Option[String]): DStream[(String, String)] = 
  {
    ssc
      .receiverStream(new MongoReceiver(host, port, database, user, password))
      .groupByKey()
      // Build a json collection using a grouped batch of entities
      .map { 
        case (key, jsonList) => {
          val collection = "{\"rows\": [ " + jsonList.mkString(",") + " ]}"
          (key, collection) 
        }
      }
  }
  
  private def createCouchDBDS(ssc: StreamingContext, host:String, port: Int, user: Option[String], password: Option[String]): DStream[(String, String)] = 
  {
    ssc.receiverStream(new CouchDBReceiver(host, port, user, password))
    .groupByKey()
    // Build a json collection using a grouped batch of entities
    .map { 
      case (key, jsonList) => 
      {
        val collection = "{\"rows\": [ " + jsonList.mkString(",") + " ]}"
        (key, collection) 
      }
    }
  }
  
  def create(ssc: StreamingContext, options: HashMap[String, String]) =
  {
    options("mode") match 
    {
      case "mongo" => 
        createMongoDS(ssc, options("host"), options("port").toInt, options("database"), options.get("user"), options.get("password"))
      case "couch" => 
        createCouchDBDS(ssc, options("host"), options("port").toInt, options.get("user"), options.get("password"))
      case "file" => 
        createFileDS(ssc, options("input"))
      case _ => throw new IllegalArgumentException("Invalid mode")
    }
  }
  
  

}