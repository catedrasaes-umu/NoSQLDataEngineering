package es.um.nosql.streaminginference.spark

import scala.collection.immutable.HashMap

import org.apache.spark.SparkConf
import org.apache.spark.streaming.Milliseconds
import org.apache.spark.streaming.StreamingContext

import es.um.nosql.streaminginference.spark.input.DStreamManager
import es.um.nosql.streaminginference.spark.utils.HDFSHelper
import es.um.nosql.streaminginference.spark.utils.KryoHelper

object Streaming
{  
  val CheckpointDir = "checkpoint"

  val defaultOptions = HashMap[String, String](
      "input" -> "input",
      "output" -> "output",
      "interval" -> "1000",
      "version" -> DStreamManager.SCHEMA_INFERENCE_MODE,
      "benchmark" -> "false",
      "block-interval" -> "1000",
      "kryo" -> "false"
  )
  
  def printHelp() 
  {
    println("Usage:")
    println("\t --mode mongo --database [databasename] --host [hostname] --port [portnumber] --output [outputDir] --load [xmi file] --user [username] --password [password]")
    println("\t --mode couch --host [hostname] --port [portnumber] --output [outputDir] --load [xmi file] --user [username] --password [password]")
    println("\t --mode file --input [inputDir] --output [outputDir] --load [xmi file]")
    println("-------")
    println("--mode: mongo|file -> Sets how spark will read input streams")
    println("\t mongo -> Read data through mongo change streams")
    println("\t mongo -> Read data through couchdb change notifications")
    println("\t file -> Read data through hdfs files")
    println("-------")
    println("--load: (optional) -> Specifies comma separated list of xmi models to load as initial state [file name must match database name]")
    println("-------")
    println("--database: (mongo only) sets database to watch")
    println("--host: (mongo,couchdb only) sets host to connect")
    println("--input: (file only) sets input directory to watch")
    println("--output: sets output directory to write results")
    println("--port: (mongo,couchdb only) sets port to connect") 
    println("--user: (mongo,couchdb only, optional) sets database username") 
    println("--password: (mongo,couchdb only, optional) sets database password") 
  }
  
  def parseOptions(args: Array[String]): HashMap[String, String] = 
  {  
    var options:HashMap[String, String] = HashMap()
    args
      .zipWithIndex
      .foreach 
      { 
        case (arg, index) => 
        {
          if (arg.startsWith("--") && args.size > index + 1)
            options += arg.substring(2) -> args(index+1) 
        }
      }
    
    // Initialize default options
    defaultOptions.foreach { case (key, value) => if (!options.contains(key)) options += key -> value }
    options
  }
  
    
  def createContext(args: Array[String])(): StreamingContext =
  {
    val options = parseOptions(args)
    val conf = new SparkConf()
                  .setAppName("StreamingInference")
                  .set("spark.streaming.blockInterval", options("block-interval") + "ms")
    
    // Enable Kryo Serialization
    if (options("kryo").toBoolean && options("version") == DStreamManager.SCHEMA_INFERENCE_MODE)
      KryoHelper.enable(conf)

    val ssc = new StreamingContext(conf, Milliseconds(options("interval").toInt))
    DStreamManager.init(ssc, options)
    ssc.checkpoint(CheckpointDir)
    ssc
  }
  
  def clean(directories: String*) = directories.foreach(HDFSHelper.delete(_))
  
  def main(args: Array[String]) = 
  {
    try 
    {
      clean(CheckpointDir)
      val context = StreamingContext.getOrCreate(CheckpointDir, createContext(args))
      context.start()             // Start the computation
      context.awaitTermination()  // Wait for the computation to terminate
    
    } 
    catch 
    {
      case e: Exception => 
      {
        printHelp
        e.printStackTrace
      }
    }
  }
  
}