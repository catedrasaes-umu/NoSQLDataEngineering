package es.um.nosql.streaminginference.spark.input

import java.nio.file.Paths

import scala.collection.immutable.HashMap

import org.apache.hadoop.io.Text
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.rdd.RDD
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchema
import es.um.nosql.streaminginference.spark.utils.IO
import es.um.nosql.streaminginference.spark.utils.CrossReferenceMatcher
import es.um.nosql.streaminginference.spark.utils.EcoreHelper
import org.apache.spark.streaming.State
import org.apache.spark.streaming.StateSpec
import es.um.nosql.streaminginference.spark.utils.EcoreHelper
import org.apache.spark.SparkContext
import es.um.nosql.streaminginference.spark.utils.HDFSHelper

object NoSQLSchemaDSFactory
{
  
  /**
   * Initializes a DStream based on JSON Database files
   */
  private def createFileDS(ssc: StreamingContext, inputDir:String): DStream[(String, String)] =
  {
    ssc
      // Based on: https://halfvim.github.io/2016/06/28/FileInputDStream-in-Spark-Streaming/
      .fileStream[Text, Text, WholeTextInputFormat](inputDir)
      .map { case (filePath, content) => 
              (Paths.get(filePath.toString).getFileName.toString.split("\\_")(0), content.toString) }
  }
  
  /**
   * Builds a DStream based on a database receiver
   */
  private def buildDatabaseDS(ds: DStream[(String, String)]): DStream[(String, String)] = 
  {
      ds
      // Strip entity name from key
      .map { case (key, jsonList) => (key.split('#')(0), jsonList) }
      .groupByKey()
      // Build a json collection using a grouped batch of entities
      .mapValues(jsonList => "{\"rows\": [ " + jsonList.mkString(",") + " ]}")
  } 
  
  /**
   * Initializes a DStream depending on mode value 
   */
  private def initializeDS(ssc: StreamingContext, options: HashMap[String, String]): DStream[(String, String)] =
    
    options("mode") match 
    {
      case "mongo" => 
        val ds = ssc.receiverStream(
            new MongoReceiver(options("host"), 
                              options("port").toInt, 
                              options("database"), 
                              options.get("user"), 
                              options.get("password")))
           buildDatabaseDS(ds)
      case "couch" => 
        
        val ds = ssc.receiverStream(
            new CouchDBReceiver(options("host"), 
                                options("port").toInt, 
                                options.get("user"), 
                                options.get("password")))
        buildDatabaseDS(ds)
        
      case "file" => 
        createFileDS(ssc, options("input"))
      case _ => 
        throw new IllegalArgumentException("Invalid mode")
    }
  
  /**
   * Defines the initial state of a DStream and its update function
   */
  def buildState(ssc: StreamingContext, options: HashMap[String, String]) = {
    
    val updateStateFunc = (schemaName: String, schema: Option[NoSQLSchema], state: State[NoSQLSchema]) => 
    {
      val matcher:CrossReferenceMatcher = new CrossReferenceMatcher()
        // Match references between state and schema before merging
        // to avoid the creation of unnecesary versions
        matcher.setCrossReferences(state.getOption(), schema)
        val acc = EcoreHelper.merge(state.getOption(), schema)
        if (acc.isDefined)
          state.update(acc.get)
        (schemaName, acc)
    }
    
    var initialNoSQLRDD:RDD[(String, NoSQLSchema)] = ssc.sparkContext.emptyRDD
    if (options.contains("load")) 
    {
      // Set xmi file as initial state
      val path = options("load")
      val schema = IO.xmiToNoSQLSchema(path)
      val name = Paths.get(path).getFileName.toString.split("\\.")(0)      
      initialNoSQLRDD = ssc.sparkContext.parallelize(List((name, schema))) 
    }
    
    StateSpec
      .function(updateStateFunc)
      .initialState(initialNoSQLRDD)
  }

  /**
   * Build a DStream based on NoSQLSchema
   */
  def build(ssc: StreamingContext, options: HashMap[String, String]) =
  {
    
    ssc.sparkContext.setLogLevel("ERROR")
    val outputDir = options("output")
    val stateSpec = buildState(ssc, options)
    val ds = 
      initializeDS(ssc, options)
      .map 
      { 
        case (schemaName, content) => 
              (schemaName, IO.toNoSQLSchema(schemaName, content)) 
      }
      .mapWithState(stateSpec)
      .stateSnapshots()
      // Output only when allowed
      .filter { case (schemaName, _) => 
        !HDFSHelper.exists(outputDir + "/" + schemaName + ".xmi")  && 
        HDFSHelper.exists(outputDir + "/_OUTPUT") 
      }
      // Output XMI schema
      .foreachRDD(rdd => 
        rdd
          .foreach 
          {
            case (schemaName, schema) => 
              IO.copyAndWriteXMI(schema, outputDir + "/" + schemaName + ".xmi")
          }
      )
  }
}