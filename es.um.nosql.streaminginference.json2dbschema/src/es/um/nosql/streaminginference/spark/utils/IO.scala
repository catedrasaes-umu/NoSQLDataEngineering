package es.um.nosql.streaminginference.spark.utils

import java.io.FileOutputStream
import java.util.HashMap
import java.util.Map

import org.codehaus.jackson.JsonNode
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.emf.ecore.xmi.XMLResource

import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchema
import es.um.nosql.streaminginference.NoSQLSchema.NoSQLSchemaPackage
import es.um.nosql.streaminginference.json2dbschema.main.util.JSON2Schema
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJAdapter
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter
import es.um.nosql.streaminginference.util.emf.ResourceManager

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path


object IO 
{
  def fromJSONString(schemaName: String, jsonString: String): NoSQLSchema = 
  {
    
    val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE
    val json2schema = new JSON2Schema[JsonNode, IAJAdapter[JsonNode]](packageInstance.getNoSQLSchemaFactory, new JacksonAdapter)
		val schema: NoSQLSchema = json2schema.fromJSONString(schemaName, jsonString)
		schema
	}
  
  def fromXMIFile(inputFile: String): NoSQLSchema =
	{
		val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE
		val resource:Resource = new ResourceManager(packageInstance)
                                  .getResourceSet()
                                  // Second argument = true forces loading of the model
                                  .getResource(URI.createFileURI(inputFile), true)
    val schema: NoSQLSchema = resource.getContents.get(0).asInstanceOf[NoSQLSchema]
    schema
  }
  
  
  def toXMI(schema: NoSQLSchema, filePath: String) = 
  {
    val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE 
    // Create a new resource to serialize the ecore model
    val outputRes:Resource = new ResourceManager(packageInstance)
                                  .getResourceSet()
                                  .createResource(URI.createFileURI(filePath))

    // Initialize EcoreUtil.Copier to store copies from non-contained elements
    val copier:Copier = new Copier(true,false)
    // Copy Attributes & contained elements
    val copied:EObject = copier.copy(schema)
    // Copy non-contained elements
    copier.copyReferences()
    // Add our copied package to resource contents
    outputRes.getContents().add(copied);
		//outputRes.setURI(URI.createPlatformResourceURI("es.um.nosql.streaminginference/model/nosqlschema.ecore", true))
		// Make the actual URI to be exported in the generated models. This
		// allows using the models without having to register them.
    // FIXME: "java.lang.IllegalArgumentException: The class 'Serializable' is not a valid classifier" 
    // in es.um.nosql.streaminginference/model/nosqlschema.ecore generated model
		packageInstance.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));
		val options: Map[Object,Object] = new HashMap[Object,Object]
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, java.lang.Boolean.TRUE);
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		val outputPath:Path = new Path(filePath);
		val conf: Configuration = new Configuration
		val fs: FileSystem = FileSystem.get(conf)
		// Delete previous file
		// http://apache-spark-user-list.1001560.n3.nabble.com/How-can-I-make-Spark-1-0-saveAsTextFile-to-overwrite-existing-file-td6696.html
		// https://community.hortonworks.com/questions/59140/apache-spark-overwrite-data-file.html
		try { fs.delete(outputPath, true) } catch { case _ : Throwable => { } }
		val outputStream = fs.create(outputPath)
		outputRes.save(outputStream, options);
		outputStream.close()
  }
  
  
}