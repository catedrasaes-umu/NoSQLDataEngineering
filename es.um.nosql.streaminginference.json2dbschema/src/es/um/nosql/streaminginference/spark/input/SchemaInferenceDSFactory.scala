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
import es.um.nosql.streaminginference.json2dbschema.process.SchemaInference
import es.um.nosql.streaminginference.spark.utils.HDFSHelper


object SchemaInferenceDSFactory
{  

  /**
   * Initializes a DStream based on JSON Database files
   */
  private def createFileDS(ssc: StreamingContext, inputDir:String): DStream[((String, String), String)] =
  {
    
    System.err.println("[WARNING] Entity parallelization with Whole Text Files is currently not supported")
        
    ssc
      // Based on: https://halfvim.github.io/2016/06/28/FileInputDStream-in-Spark-Streaming/
      .fileStream[Text, Text, WholeTextInputFormat](inputDir)
      .map { case (filePath, content) => 
              ((Paths.get(filePath.toString).getFileName.toString.split("\\_")(0), "any"), content.toString) }
  }
  
  /**
   * Builds a DStream based on a database receiver
   */
  private def buildDatabaseDS(ds: DStream[(String, String)]): DStream[((String, String), String)] = 
  {
      ds
      // Convert string key to a tuple
      .map { 
        case (key, jsonList) => {
          val keyComponents = key.split('#')
          ((keyComponents(0), keyComponents(1)), jsonList) 
        }
      }
      .groupByKey()
      // Build a json collection using a grouped batch of entities
      .mapValues (jsonList => "{\"rows\": [ " + jsonList.mkString(",") + " ]}")   
  } 
  
  /**
   * Initializes a DStream depending on mode value 
   */
  private def initializeDS(ssc: StreamingContext, options: HashMap[String, String]): DStream[((String, String), String)] =
    
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
    
    val updateStateFunc = (keyPair: (String, String), schema: Option[SchemaInference], state: State[SchemaInference]) => 
    {
      val acc: Option[SchemaInference] = (state.getOption, schema) match {
        case (None, None) => None
        case (None, schema) => schema
        case (state, None) => state
        case (Some(state), Some(schema)) => Option(state.merge(schema)) 
      }
      
      if (acc.isDefined)
        state.update(acc.get)
      
      (keyPair, acc)
    }
    
    // TODO: Test Load from XMI
    var initialSchemaInferenceRDD:RDD[((String, String), SchemaInference)] = ssc.sparkContext.emptyRDD
    if (options.contains("load")) 
    {
      // Set xmi file as initial state
      val path = options("load")
      val schemas = EcoreHelper.toSchemaInferenceState(IO.xmiToNoSQLSchema(path))
      val name = Paths.get(path).getFileName.toString.split("\\.")(0) 
      val initialState = schemas.toSeq.map { case (entity, schema) => ((name, entity), schema) }  
      initialSchemaInferenceRDD = ssc.sparkContext.parallelize(initialState) 
    }
    
    StateSpec
      .function(updateStateFunc)
      .initialState(initialSchemaInferenceRDD)
  }

  
  
  def build(ssc: StreamingContext, options: HashMap[String, String]) =
  {
    val outputDir = options("output")
    val stateSpec = buildState(ssc, options)
    val ds = 
      initializeDS(ssc, options)
      .mapValues(IO.toSchemaInference(_))
      .mapWithState(stateSpec)
      .stateSnapshots()
      .filter { case ((schema, entity), _) => 
        HDFSHelper.exists(outputDir + "/_OUTPUT") && 
        !HDFSHelper.exists(outputDir + "/" + schema + ".xmi") 
      }
      // Strip entity from key
      .map { case ((schema, entity), inference) => (schema, inference) }
      .reduceByKey(_.merge(_))
      .foreachRDD(rdd => 
        rdd
          .foreach 
          {
            case (schema, inference) => 
              IO.writeXMI(inference, schema, outputDir + "/" + schema + ".xmi")
          }
      )
  }
}