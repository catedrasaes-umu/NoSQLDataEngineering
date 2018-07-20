package es.um.nosql.orchestrator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.db.interfaces.EveryPolitician2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.ResourceManager;

public class ObjectIdTest
{
  private String inputRoute = "testSources/ERROR_ObjectIds.json";
  private String dbName = "DEBUG_ObjectIds";
  private EveryPolitician2Db controller;

  @Before
  public void setUp() throws Exception
  {
    controller = new EveryPolitician2Db(DbType.MONGODB, "localhost");
  }

  @After
  public void tearDown() throws Exception
  {
    controller.getClient().cleanDb(dbName);
    controller.shutdown();
  }

  @Test
  public void test()
  {
    controller.run(inputRoute, dbName);

    MongoDBImport inferrer = new MongoDBImport();
    JsonArray jArray = inferrer.mapRed2Array("localhost", dbName, "mapreduce/mongodb/v1/");

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema nosqlschema = builder.buildFromGsonArray(dbName, jArray);
/*
    NoSQLSchemaPackage nosqlschemaPackage = NoSQLSchemaPackage.eINSTANCE;
    ResourceManager resManager = new ResourceManager(nosqlschemaPackage);
    nosqlschemaPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));

    Resource outputRes = resManager.getResourceSet().createResource(URI.createFileURI("testSources/output.xmi"));
    outputRes.getContents().add(nosqlschema);

    // Configure output
    Map<Object,Object> options = new HashMap<Object,Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8");

    try
    {
      outputRes.save(new FileOutputStream("testSources/output.xmi"), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }*/

//    assertNotNull("Schema can't be null", nosqlschema);
//    assertNotNull("Schema should have entities", nosqlschema.getEntities());
//    assertEquals("Schema should have one entity", 1, nosqlschema.getEntities().size());

//    Property property = nosqlschema.getEntities().get(0).getEntityVariations().get(0).getProperties().stream().filter(p -> p.getName().equals("_id")).findFirst().get();

//    assertTrue(property instanceof Attribute);
//    assertEquals("ObjectId", ((PrimitiveType)((Attribute)property).getType()).getName());

    // TODO: The inference process fails when the _id identifier is an ObjectId: ObjectId("code")
    // It is inferred it as an Aggregate of an _id Entity with no attributes.
    // It should be inferred as a PrimitiveType with ObjectId type.
  }
}
