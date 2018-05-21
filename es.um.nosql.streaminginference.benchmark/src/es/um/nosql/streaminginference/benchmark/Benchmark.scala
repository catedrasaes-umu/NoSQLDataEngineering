package es.um.nosql.streaminginference.benchmark

import scala.collection.immutable.HashMap


object Benchmark {
  
  val defaultOptions = HashMap[String, String](
      "elements" -> "120",
      "entities" -> "6",
      "versions" -> "2",
      "depth" -> "3",
      "fields" -> "2",
      "host" -> "localhost",
      "port" -> "27017",
      "database" -> "benchmark",
      "mode" -> "file",
      "output" -> "collection.json",
      "flow" -> "stress",
      "batch" -> "10",
      "delay" -> "100"
  )

  def printHelp() 
  {
    println("Usage:")
    println("\t --mode mongo --database [databasename] --host [hostname] --port [portnumber] --user [username] --password [password] --flow (stream|stress)")
    println("\t --mode file --output [path] --flow (stream|stress)")
    println("-------")
    println("--mode: mongo|file -> Sets how program will write data")
    println("\t mongo -> Write rows directly to MongoDB")
    println("\t file -> Write rows to a file")
    println("--elements: sets number of elements to generate") 
    println("--entities: sets global number of entities") 
    println("--versions: sets number of versions per entity") 
    println("--depth: sets nested aggregates depth") 
    println("--fields: sets number of fields per aggregate")
    println("-------")
    println("--database: (mongo) sets database to write out")
    println("--host: (mongo) sets host to connect")
    println("--output: (file) sets file to write")
    println("--port: (mongo) sets port to connect") 
    println("--user: (mongo, optional) sets database username") 
    println("--password: (mongo, optional) sets database password") 
    println("--database: (mongo) sets database to connect") 
    println("-------")
    println("--batch: (stream) sets stream batch size") 
    println("--delay: (stream) sets stream inter-batch delay in milliseconds") 
     
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
  
  def main(args: Array[String]) = 
  {
    try
    {
      val options = parseOptions(args)
      val collection = 
        if (options("mode") == "mongo") 
          if (options("flow") == "stream") new MongoStreamCollection(options)
          else new MongoCollection(options)
        else if (options("flow") == "stream") new FileStreamCollection(options)
          else new FileCollection(options)

      collection.output()
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