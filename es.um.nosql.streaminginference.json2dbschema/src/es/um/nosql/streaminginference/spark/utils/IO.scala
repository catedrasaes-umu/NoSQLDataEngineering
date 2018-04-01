package es.um.nosql.streaminginference.spark.utils

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
import es.um.nosql.streaminginference.json2dbschema.process.NoSQLModelBuilder
import es.um.nosql.streaminginference.json2dbschema.process.SchemaInference
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJAdapter
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter
import es.um.nosql.streaminginference.util.emf.ResourceManager;


object IO 
{
  
  def toNoSQLSchema(schemaName: String, schema: SchemaInference) = 
  {
    val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE
		val builder:NoSQLModelBuilder = new NoSQLModelBuilder(packageInstance.getNoSQLSchemaFactory, schemaName);
    builder.build(schema.getEntities);
  }
  
  def toNoSQLSchema(schemaName: String, jsonString: String): NoSQLSchema = 
  {
    val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE
    val json2schema = new JSON2Schema[JsonNode, IAJAdapter[JsonNode]](packageInstance.getNoSQLSchemaFactory, new JacksonAdapter)
		val schema: NoSQLSchema = json2schema.fromJSONString(schemaName, jsonString)
		schema
	}
  
  // TODO: implement this
  def xmiToSchemaInference(inputFile: String): SchemaInference = ???
  
  def xmiToNoSQLSchema(inputFile: String): NoSQLSchema =
	{
		val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE
		val resource:Resource = new ResourceManager(packageInstance)
                                  .getResourceSet()
                                  // Second argument = true forces loading of the model
                                  .getResource(URI.createFileURI(inputFile), true)
    val schema: NoSQLSchema = resource.getContents.get(0).asInstanceOf[NoSQLSchema]
    schema
  }
  
  def toSchemaInference(jsonString: String): SchemaInference = 
  {
    val adapter = new JacksonAdapter
    val root = adapter.readFromString(jsonString)
    val si = new SchemaInference(root.get("rows").asArray())
    si.infer()
    si
  }  
  
  def writeXMI(schema: NoSQLSchema, filePath: String): Unit = 
  {
    val packageInstance: NoSQLSchemaPackage = NoSQLSchemaPackage.eINSTANCE 
    // Create a new resource to serialize the ecore model
    val outputRes:Resource = new ResourceManager(packageInstance)
                                  .getResourceSet()
                                  .createResource(URI.createFileURI(filePath))
    // Add our copied package to resource contents
    outputRes.getContents().add(schema);
		//outputRes.setURI(URI.createPlatformResourceURI("es.um.nosql.streaminginference/model/nosqlschema.ecore", true))
		// Make the actual URI to be exported in the generated models. This
		// allows using the models without having to register them.
    // FIXME: "java.lang.IllegalArgumentException: The class 'Serializable' is not a valid classifier" 
    // in es.um.nosql.streaminginference/model/nosqlschema.ecore generated model
		packageInstance.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));
		val options: Map[Object,Object] = new HashMap[Object,Object]
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, java.lang.Boolean.TRUE);
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		// HDFSHelper.delete(filePath)
    val outputStream = HDFSHelper.getOutputStream(filePath)
		outputRes.save(outputStream, options);
		outputStream.close()
  }
  
  def writeXMI(schema: SchemaInference, name: String, filePath: String): Unit = 
  {
    val noSqlModel = toNoSQLSchema(name, schema)
    writeXMI(noSqlModel, filePath)
  }
  
  def copyAndWriteXMI(schema: NoSQLSchema, filePath: String) = 
  {
    // Initialize EcoreUtil.Copier to store copies from non-contained elements
    val copier:Copier = new Copier(true,false)
    // Copy Attributes & contained elements
    val copied:EObject = copier.copy(schema)
    // Copy non-contained elements
    copier.copyReferences()
    writeXMI(copied.asInstanceOf[NoSQLSchema], filePath)
  }
}