package es.um.nosql.streaminginference.spark

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat

import es.um.nosql.streaminginference.json2dbschema
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchema
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage
import es.um.nosql.streaminginference.spark.input.WholeTextInputFormat
import es.um.nosql.streaminginference.json2dbschema.main.util.JSON2Schema
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJAdapter
import org.codehaus.jackson.JsonNode
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.ecore.xmi.XMLResource
import es.um.nosql.streaminginference.util.emf.ResourceManager
import org.eclipse.emf.common.util.URI;
import java.util.HashMap
import java.util.Map
import java.io.FileOutputStream
import java.nio.file.Paths
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import scala.collection.concurrent.Debug
import es.um.nosql.streaminginference.NoSQLSchema.impl.ReferenceImpl
import es.um.nosql.streaminginference.NoSQLSchema.Entity
import org.eclipse.emf.common.util.EList
import scala.collection.JavaConversions._
import es.um.nosql.streaminginference.NoSQLSchema.EntityVersion
import es.um.nosql.streaminginference.NoSQLSchema.Property
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ArraySC
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ObjectSC
import es.um.nosql.streaminginference.NoSQLSchema.Association
import es.um.nosql.streaminginference.NoSQLSchema.Attribute
import es.um.nosql.streaminginference.NoSQLSchema.PrimitiveType
import es.um.nosql.streaminginference.NoSQLSchema.Tuple
import es.um.nosql.streaminginference.NoSQLSchema.Aggregate
import es.um.nosql.streaminginference.NoSQLSchema.Reference
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.emf.ecore.EObject
import es.um.nosql.streaminginference.json2dbschema.main.BuildNoSQLSchema
import java.io.FileInputStream
import java.io.File

import es.um.nosql.streaminginference.spark.utils.IO
import es.um.nosql.streaminginference.spark.utils.EcoreHelper
import es.um.nosql.streaminginference.spark.utils.CrossReferenceMatcher
import es.um.nosql.streaminginference.spark.utils.CrossReferenceMatcher

object Main {
  
  val CheckpointDir = "checkpoint"

  val updateExistingSchema = (schemaName: String, schema: Option[NoSQLSchema], state: State[NoSQLSchema]) => {
      
    val curState:NoSQLSchema = state.getOption().getOrElse(null)
    val curSchema:NoSQLSchema = schema.getOrElse(null)    
    val matcher:CrossReferenceMatcher = new CrossReferenceMatcher()
    if (curState != null && curSchema != null) {
      // Match references between state and schema before merging
      // to avoid the creation of unnecesary versions
      matcher.setCrossReferences(curState, curSchema)
    }
    val acc:NoSQLSchema = EcoreHelper.merge(curState, curSchema)
    state.update(acc)
    (schemaName, acc)
  }
    
  def createContext(inputDir:String, outputDir: String)(): StreamingContext = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("StreamingInference")
    val ssc = new StreamingContext(conf, Seconds(15))
    ssc
      // Based on: https://halfvim.github.io/2016/06/28/FileInputDStream-in-Spark-Streaming/
      .fileStream[Text, Text, WholeTextInputFormat](inputDir)
      .map { case (filePath, content) => 
              (Paths.get(filePath.toString).getFileName.toString.split("\\_")(0), content.toString) }
      .map { case (schemaName, content) => 
              (schemaName, IO.fromJSONString(schemaName, content)) }
      // Merge current state with previous state
      .mapWithState(StateSpec.function(updateExistingSchema))
      // Output XMI schema
      .foreachRDD(rdd => 
        rdd
          .collect()
          .map {
            case (schemaName, schema) => IO.toXMI(schema, outputDir + "/" + schemaName + ".xmi")
          }
      )

    ssc.checkpoint(CheckpointDir)
    ssc
    
  }
  
  def clean(inputDirectory: String, outputDirectory: String, checkPointDirectory: String) = {
    
    def recursiveClean(file: File): Unit = {
      if (file.isDirectory) 
        file.listFiles().foreach(recursiveClean(_))
      file.delete
    }
    
    val input:File = new File(inputDirectory)
    val output:File = new File(outputDirectory)
    val checkpoint:File = new File(checkPointDirectory)
    if (input.isDirectory)
      input.listFiles.foreach(recursiveClean(_))
    if (output.isDirectory)
      output.listFiles.foreach(recursiveClean(_))
    if (checkpoint.isDirectory)
      checkpoint.listFiles.foreach(recursiveClean(_)) 
  }
  
  def main(args: Array[String]) = {
    
    args match {
      case Array(arg1: String, arg2:String) => 
        clean(inputDirectory = arg1,outputDirectory = arg2, checkPointDirectory = CheckpointDir) // Deletes all previous content in directories
        val context = StreamingContext.getOrCreate(CheckpointDir, createContext(arg1, arg2))
        context.start()             // Start the computation
        context.awaitTermination()  // Wait for the computation to terminate
    
//        BuildNoSQLSchema.main(Array("books.json", "out/books.xmi"))
//        val source = scala.io.Source.fromFile("books.json")
//        val lines = try source.mkString finally source.close()
//        IO.toXMI(IO.fromJSONString("books", lines), "out/books.xmi")

      case _ => 
        Console.err.println("At least the JSON file must be specified.");
			  Console.err.println("Usage: BuildNoSQLSchema JSONfile [outputXMIfile]");
    }
    
  }
  
}