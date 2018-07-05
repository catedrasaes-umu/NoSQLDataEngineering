package es.um.nosql.orchestrator.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.db.interfaces.Facebook2Db;
import es.um.nosql.s13e.db.utils.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.emf.ModelLoader;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FacebookTest
{
  private static final String DATABASE_IP = "localhost";
  private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";
  private static String INPUT_FOLDER = "testSources/facebook/";
  private static String DBNAME = "DEBUG_facebook";
  private static String OUTPUT_MODEL = "testOutput/" + DBNAME + ".xmi";

  private static Facebook2Db controller;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
    controller = new Facebook2Db(DbType.MONGODB, DATABASE_IP);
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
    controller.getClient().cleanDb(DBNAME);
    controller.shutdown();
  }

  @Test
  public void test()
  {
    for (String fileName : new File(INPUT_FOLDER).list())
      controller.run(INPUT_FOLDER + fileName, DBNAME);

    MongoDBImport inferrer = new MongoDBImport();
    JsonArray jArray = inferrer.mapRed2Array(DATABASE_IP, DBNAME, MONGODB_MAPREDUCE_FOLDER);
    System.out.println("Inference finished.");

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromGsonArray(DBNAME, jArray, OUTPUT_MODEL);

    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(OUTPUT_MODEL), NoSQLSchema.class);

    assertNotNull("Schema can't be null", schema);
    assertNotNull("Schema should have entities", schema.getEntities());
    assertEquals("Schema should have three entities", schema.getEntities().size(), 3);

    Entity comments = null;
    Entity pages = null;
    Entity posts = null;

    for (Entity e : schema.getEntities())
    {
      if (e.getName().equals("Comments"))
        comments = e;
      else if (e.getName().equals("Pages"))
        pages = e;
      else if (e.getName().equals("Posts"))
        posts = e;
    }

    // Check Entities are not null, and there is only one variation of each Entity
    assertNotNull("Comments can't be null", comments);
    assertEquals("Only one Comment variation", comments.getEntityVariations().size(), 1);
    assertNotNull("Pages can't be null", pages);
    assertEquals("Only one Page variation", pages.getEntityVariations().size(), 1);
    assertNotNull("Posts can't be null", posts);
    assertEquals("Only one Post variation", posts.getEntityVariations().size(), 1);

    List<Property> commentProps = comments.getEntityVariations().get(0).getProperties();
    List<Property> pagesProps = pages.getEntityVariations().get(0).getProperties();
    List<Property> postsProps = posts.getEntityVariations().get(0).getProperties();

    // Check Comment properties
    assertNotNull("Comment property list can't be null", commentProps);
    assertEquals("Comment property list must have 6 elements", commentProps.size(), 6);
    assertTrue("String \"created_time\" in Comment list not found",
        commentProps.stream().filter(x -> checkAttribute(x, "created_time", "String")).findFirst().isPresent());
    assertTrue("String \"from_id\" in Comment list not found", 
        commentProps.stream().filter(x -> checkAttribute(x, "from_id", "String")).findFirst().isPresent());
    assertTrue("String \"from_name\" in Comment list not found", 
        commentProps.stream().filter(x -> checkAttribute(x, "from_name", "String")).findFirst().isPresent());
    assertTrue("String \"message\" in Comment list not found", 
        commentProps.stream().filter(x -> checkAttribute(x, "message", "String")).findFirst().isPresent());
    assertTrue("Reference \"post_id\" in Comment list not found",
        commentProps.stream().filter(x -> checkReference(x, "post_id", "String", "Posts", 1, 1)).findFirst().isPresent());

    // Check Page properties
    assertNotNull("Pages property list can't be null", pagesProps);
    assertEquals("Page property list must have 3 elements", pagesProps.size(), 3);
    assertTrue("String \"page_name\" in Page list not found",
        pagesProps.stream().filter(x -> checkAttribute(x, "page_name", "String")).findFirst().isPresent());
    assertTrue("String \"page_id\" in Page list not found",
        pagesProps.stream().filter(x -> checkAttribute(x, "page_id", "String")).findFirst().isPresent());

    // Check Post properties
    assertNotNull("Posts property list can't be null", postsProps);
    assertEquals("Post property list must have 15 elements", postsProps.size(), 15);
    assertTrue("String \"created_time\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "created_time", "String")).findFirst().isPresent());
    assertTrue("String \"description\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "description", "String")).findFirst().isPresent());
    assertTrue("String \"link\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "link", "String")).findFirst().isPresent());
    assertTrue("String \"message\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "message", "String")).findFirst().isPresent());
    assertTrue("String \"scrape_time\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "scrape_time", "String")).findFirst().isPresent());
    assertTrue("Number \"react_angry\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_angry", "Number")).findFirst().isPresent());
    assertTrue("Number \"react_haha\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_haha", "Number")).findFirst().isPresent());
    assertTrue("Number \"react_like\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_like", "Number")).findFirst().isPresent());
    assertTrue("Number \"react_love\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_love", "Number")).findFirst().isPresent());
    assertTrue("Number \"react_sad\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_sad", "Number")).findFirst().isPresent());
    assertTrue("Number \"react_wow\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_wow", "Number")).findFirst().isPresent());
    assertTrue("Number \"shares\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "shares", "Number")).findFirst().isPresent());
    assertTrue("Reference \"page_id\" in Page list not found",
        postsProps.stream().filter(x -> checkReference(x, "page_id", "String", "Pages", 1, 1)).findFirst().isPresent());
    assertTrue("String \"post_id\" in Page list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "post_id", "String")).findFirst().isPresent());
  }

  private boolean checkAttribute(Property p, String attrName, String attrType)
  {
    return p.getName().equals(attrName) && p instanceof Attribute
        && ((Attribute)p).getType() instanceof PrimitiveType && ((PrimitiveType)(((Attribute)p).getType())).getName().equals(attrType);
  }

  private boolean checkReference(Property p, String refName, String origType, String eName, int lBound, int uBound)
  {
    if (!p.getName().equals(refName) || !(p instanceof Reference))
      return false;

    Reference ref = (Reference)p;

    return (ref.getOriginalType().equals(origType) && ref.getRefTo().getName().equals(eName)
        && ref.getOpposite() == null && ref.getLowerBound() == lBound && ref.getUpperBound() == uBound);
  }
}
