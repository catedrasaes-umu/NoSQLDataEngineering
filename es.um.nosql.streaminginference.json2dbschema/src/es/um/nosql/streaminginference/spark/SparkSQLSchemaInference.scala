package es.um.nosql.streaminginference.spark

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.mapAsScalaMap

import org.apache.spark.SparkConf
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.ArrayType
import org.apache.spark.sql.types.BooleanType
import org.apache.spark.sql.types.DataType
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.codehaus.jackson.JsonFactory
import org.codehaus.jackson.JsonNode
import org.codehaus.jackson.map.ObjectMapper

import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ArraySC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.BooleanSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NumberSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ObjectSC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.SchemaComponent
import es.um.nosql.streaminginference.json2dbschema.process.SchemaInference
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter
import es.um.nosql.streaminginference.spark.implicits.InferenceHelpers.NoSQLSchemaExtend
import es.um.nosql.streaminginference.spark.input.MongoReceiver
import es.um.nosql.streaminginference.spark.utils.IO

object SparkSQLSchemaInference 
{ 
  def toSQLSchema(jsonStr: String): StructType = 
  {
    def parse(sc: SchemaComponent): DataType = 
    {
      if (sc.isInstanceOf[ObjectSC])
      {
        val obj = sc.asInstanceOf[ObjectSC]
        StructType(
           obj.getInners.map(pair => StructField(pair.getKey, parse(pair.getValue)))   
        )
      }
      else if (sc.isInstanceOf[ArraySC])
      {
        val arr = sc.asInstanceOf[ArraySC]
        if (arr.isHomogeneous) 
        {
          ArrayType(parse(arr.getInners.head))
        }
        else 
        {
          StructType(
            arr.getInners.zipWithIndex.map { case (value, index) => StructField(index.toString, parse(value)) }.toSeq  
          )
        }
      }
      else if (sc.isInstanceOf[BooleanSC])
        BooleanType
      else if (sc.isInstanceOf[NumberSC])
        DoubleType
      else
        StringType
    }
    
    val mapper:ObjectMapper = new ObjectMapper
    val factory:JsonFactory = new JsonFactory
    val parser = factory.createJsonParser(jsonStr)
    val root: JsonNode = mapper.readTree(parser)
    val elem = new JacksonAdapter().readFromString("[" + jsonStr + "]")  
    val si:SchemaInference = new SchemaInference(elem.asArray);
    parse(si.infer.head._2.head).asInstanceOf[StructType]
  }
  
  def printGenericSQLSchema(): Unit = 
  {
    val spark = SparkSession
                  .builder
                  .master("local[*]")
                  .appName("Schema Inference")
                  .getOrCreate()
                  
    spark.sparkContext.setLogLevel("ERROR")
    // The path can be either a single text file or a directory storing text files
    val path = "examples/movies.json"
    val moviesDF = spark.read.option("multiline", true).option("mode", "PERMISIVE").json(path)
    // Spark < 2.2
    // val booksDF = spark.read.json(spark.sparkContext.wholeTextFiles(path).values)
    moviesDF.printSchema
  }
  
  def printInferredSQLSchema(): Unit = 
  {
    val spark = SparkSession
                  .builder
                  .master("local[*]")
                  .appName("Schema Inference")
                  .getOrCreate()
                  
    spark.sparkContext.setLogLevel("ERROR")
    val input = "output/movies.xmi"
    val schema = IO.fromXMIFile(input)
    schema.printSQLSchema(spark)
  }
  
  // Attempt of validation
  // We receive Mongo entity versions and check them against loaded schemas
  def printValidatedSQLSchemas(): Unit = 
  {
    val input = "output/movies.xmi"
    val schemas = IO.fromXMIFile(input).toSparkSQLVersions()
    // Print loaded versions
    // schemas.filter(schema => schema._1 == "Movie").foreach(schema => schema._2.foreach(version => println(version)))
    val conf = new SparkConf().setMaster("local[*]").setAppName("StreamingInference")
    val ssc = new StreamingContext(conf, Seconds(15))    
    ssc.sparkContext.setLogLevel("ERROR")
    ssc
      .receiverStream(new MongoReceiver("localhost", 27017, "test"))
      // FIXME: this is a match to map by entity instead of database
      .map
      {
        case (database, json) => 
        {
          val search = "_type\" : \""
          val pos = json.indexOf(search)
          val start = pos + search.length()
          val end = json.indexOf('"', start)
          val sub = json.substring(start, end)
          (sub.capitalize, json)
        }
      }
      // Filter out entities without defined entities
      .filter { case (entity, json) => schemas.get(entity).isDefined }
      .map { case (entity, json) => (entity, toSQLSchema(json)) }
      // Filter out entities whose version doesn't match defined versions
      .filter { case (entity, schema) => schemas(entity).contains(schema) }
      .foreachRDD
      {
        // Print on Driver Validated Entities
        rdd => rdd.collect().foreach 
        { 
          case (entity, schema) => 
          {
            val spark = SparkSession.builder.config(rdd.sparkContext.getConf).getOrCreate()
            import spark.implicits._
            println("*****" + entity + "******")
            spark.createDataFrame(rdd.sparkContext.emptyRDD[Row], schema).printSchema()
          }
        }
      }
      
    ssc.start()
    ssc.awaitTermination()
  }
  
  def main(args: Array[String]): Unit = {
    
    // Basic Spark SQL Schema inference
    //printGenericSQLSchema
    // Custom Schema Inference -> HashMap[Entity, ListOfVersionSchemas]
    //printInferredSQLSchema
    // Use an xmi model to validate entity versions from input
    printValidatedSQLSchemas

  }
  
}