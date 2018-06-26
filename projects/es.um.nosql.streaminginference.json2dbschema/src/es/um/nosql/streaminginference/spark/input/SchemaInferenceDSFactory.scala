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
import org.apache.spark.streaming.Time
import org.apache.spark.streaming.Milliseconds
import org.apache.spark.metrics.MetricsSystem
import scala.collection.JavaConverters._
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson.JacksonArray
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson.JacksonElement
import org.apache.hadoop.fs.Path


object SchemaInferenceDSFactory
{  

  /**
   * Initializes a DStream based on JSON Database files
   */
  private def createFileDS(ssc: StreamingContext, inputDir:String): DStream[((String, String), SchemaInference)] =
  {

    // Update modification time of files in inputDir to force spark to check them
    HDFSHelper.updateModificationTime(inputDir+"/*", System.currentTimeMillis())
    
    ssc
      // Based on: https://halfvim.github.io/2016/06/28/FileInputDStream-in-Spark-Streaming/
      .fileStream[Text, Text, WholeTextInputFormat](inputDir, (f: Path) => true, newFilesOnly = false)
      .mapPartitions (partition => {
          val adapter = new JacksonAdapter
          partition.flatMap 
          { 
            case (filePath, content) => 
            {
              val root = adapter.readFromString(content.toString)
              val rows = root.get("rows").asArray()
              rows.asScala.groupBy(element => 
              {
                val entityType = element.asObject().get("_type")
                if (entityType != null) entityType.asString() else "unknown"
              }).map {
                case (key, values) => 
                {
                  val sequence = values.map(_.asInstanceOf[JacksonElement]).asJava
                  val subRows = new JacksonArray(sequence)
                  ((Paths.get(filePath.toString).getFileName.toString.split("\\_")(0), key), IO.toSchemaInference(subRows))
                }
              }
            }
          }  
      })
      .reduceByKey(_.merge(_))
  }
  
  /**
   * Builds a DStream based on a database receiver
   */
  private def buildDatabaseDS(ds: DStream[((String, String), String)]): DStream[((String, String), SchemaInference)] = 
  {
      ds
//    This alternative is computationaly very expensive
//      .mapValues (jsonStr => IO.toSchemaInference("{\"rows\": [ " + jsonStr + " ]}"))   
//      .reduceByKey((sch1:SchemaInference, sch2:SchemaInference) => sch1.merge(sch2), 1)
//    This one is network communication expensive
      .groupByKey()
//       Build a json collection using a grouped batch of entities
      .mapValues (jsonList => "{\"rows\": [ " + jsonList.mkString(",") + " ]}")   
      .mapValues(IO.toSchemaInference(_))
  } 
  
  /**
   * Initializes a DStream depending on mode value 
   */
  private def initializeDS(ssc: StreamingContext, options: HashMap[String, String]): DStream[((String, String), SchemaInference)] =
    
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
      
      acc.foreach(state.update(_))
      
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
    val outputInterval = options("output-interval").toLong
    val outputDir = options("output")
    val benchmarking = options("benchmark").toBoolean
    val stateSpec = buildState(ssc, options)
//    val interval = options("interval").toInt
    var lastCheck:Long = 0
    
    val ds = 
      initializeDS(ssc, options)
//      .checkpoint(Milliseconds(options("interval").toInt*100))
      .mapWithState(stateSpec)
      .stateSnapshots()
      // Compute window at OUTPUT_INTERVAL_MS interval rate      
      // .window(Milliseconds(interval), Milliseconds(Math.max(interval, OUTPUT_INTERVAL_MS)))
      // Strip entity from key
      .map { case ((schema, entity), inference) => (schema, inference) }
      //.reduceByKey(_.merge(_))
      
      if (options("benchmark").toBoolean)
        ds.foreachRDD(rdd => 
          rdd
            .reduceByKey(_.merge(_))
            // Do SchemaInference to NoSQL conversion but don't output xmi file
//            .foreach(p => {})
            .foreach { case (schema, inference) => IO.toNoSQLSchema(schema, inference) }
        )
      else
        ds.foreachRDD((rdd, timestamp) => 
          if (timestamp.milliseconds - lastCheck >= outputInterval)
          {
            lastCheck = timestamp.milliseconds
            rdd
              .reduceByKey(_.merge(_))
              .foreach 
              {
                case (schema, inference) => 
                  IO.writeXMI(inference, schema, outputDir + "/" + schema + "-" + timestamp.milliseconds + ".xmi")
              }
          } 
          else  // Empty action to force RDD execution
            rdd.foreach(p => {})
      )
  }
}