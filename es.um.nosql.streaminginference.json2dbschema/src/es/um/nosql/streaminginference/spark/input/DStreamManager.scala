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
import es.um.nosql.streaminginference.spark.utils.HDFSHelper
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.SparkContext
import es.um.nosql.streaminginference.spark.utils.SchemasAccumulator

object DStreamManager
{
  final val NO_SQL_SCHEMA_MODE = "1"
  final val SCHEMA_INFERENCE_MODE = "2"
  
  private val mode = NO_SQL_SCHEMA_MODE
    
  def startInteractions(ssc: StreamingContext, options: HashMap[String,String]) = 
  {
    val watcher = new Thread
    {
      override def run
      {
        val donePath = options("output") + "/_DONE"
        val outputPath = options("output") + "/_OUTPUT"
        val interval = options("interval").toInt
        
        while (!HDFSHelper.exists(donePath)) 
        {    
            if (HDFSHelper.exists(outputPath))
            {
              val filePaths = HDFSHelper.getPatternFiles(options("output") + "/*.xmi")
              filePaths.foreach(filePath => HDFSHelper.delete(filePath))
              // Wait 5 seconds extra because output can be slow
              Thread.sleep(interval + 5000)
              HDFSHelper.delete(outputPath)
            }
            Thread.sleep(interval)
        }
        HDFSHelper.delete(donePath)
        ssc.stop(true)
      }
    }
    
    watcher.start
  }
  
  def init(ssc: StreamingContext, options: HashMap[String, String]) = 
  {
    ssc.sparkContext.setLogLevel("ERROR")
    options("version") match 
    {
      case NO_SQL_SCHEMA_MODE =>
        NoSQLSchemaDSFactory.build(ssc, options)
      case SCHEMA_INFERENCE_MODE =>
        SchemaInferenceDSFactory.build(ssc, options)
      case _  =>
        throw new IllegalStateException("Invalid mode")
    }
    
    startInteractions(ssc, options)
  }
}