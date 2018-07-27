package test;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.db.interfaces.Facebook2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

public class FacebookTest
{
  private static String inputFolder = "testSources/facebook/";
  private static String dbName = "DEBUG_facebook";
  private static Facebook2Db controller;

  @Before
  public void setUp() throws Exception
  {
    controller = new Facebook2Db(DbType.MONGODB, "localhost");
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
    for (String fileName : new File(inputFolder).list())
      controller.run(inputFolder + fileName, dbName);

    MongoDBImport inferrer = new MongoDBImport();
    JsonArray jArray = inferrer.mapRed2Array("localhost", dbName, "mapreduce/mongodb/v1/");

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema nosqlschema = builder.buildFromGsonArray(dbName, jArray);

    Entity comments = null;
    Entity pages = null;
    Entity posts = null;

    for (Entity e : nosqlschema.getEntities())
    {
      if (e.getName().equals("Comments"))
        comments = e;
      else if (e.getName().equals("Pages"))
        pages = e;
      else if (e.getName().equals("Posts"))
        posts = e;
    }

    // Check Entities are not null, and there is only one variation of each Entity
    Assert.assertNotNull("Comments can't be null", comments);
    Assert.assertEquals("Only one Comment variation", comments.getVariations().size(), 1);
    Assert.assertNotNull("Pages can't be null", pages);
    Assert.assertEquals("Only one Page variation", pages.getVariations().size(), 1);
    Assert.assertNotNull("Posts can't be null", posts);
    Assert.assertEquals("Only one Post variation", posts.getVariations().size(), 1);

    List<Property> commentProps = comments.getVariations().get(0).getProperties();
    List<Property> pagesProps = pages.getVariations().get(0).getProperties();
    List<Property> postsProps = posts.getVariations().get(0).getProperties();

    // Check Comment properties
    Assert.assertNotNull("Comment property list can't be null", commentProps);
    Assert.assertEquals("Comment property list must have 7 elements", commentProps.size(), 7);
    Assert.assertTrue("String \"created_time\" in Comment list not found",
        commentProps.stream().filter(x -> checkAttribute(x, "created_time", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"from_id\" in Comment list not found", 
        commentProps.stream().filter(x -> checkAttribute(x, "from_id", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"from_name\" in Comment list not found", 
        commentProps.stream().filter(x -> checkAttribute(x, "from_name", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"message\" in Comment list not found", 
        commentProps.stream().filter(x -> checkAttribute(x, "message", "String")).findFirst().isPresent());
    Assert.assertTrue("Reference \"post_id\" in Comment list not found",
        commentProps.stream().filter(x -> checkReference(x, "post_id", "String", "Posts", 1, 1)).findFirst().isPresent());

    // Check Page properties
    Assert.assertNotNull("Pages property list can't be null", pagesProps);
    Assert.assertEquals("Page property list must have 3 elements", pagesProps.size(), 3);
    Assert.assertTrue("String \"page_name\" in Page list not found",
        pagesProps.stream().filter(x -> checkAttribute(x, "page_name", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"page_id\" in Page list not found",
        pagesProps.stream().filter(x -> checkAttribute(x, "page_id", "String")).findFirst().isPresent());

    // Check Post properties
    Assert.assertNotNull("Posts property list can't be null", postsProps);
    Assert.assertEquals("Post property list must have 15 elements", postsProps.size(), 15);
    Assert.assertTrue("String \"created_time\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "created_time", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"description\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "description", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"link\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "link", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"message\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "message", "String")).findFirst().isPresent());
    Assert.assertTrue("String \"scrape_time\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "scrape_time", "String")).findFirst().isPresent());
    Assert.assertTrue("Number \"react_angry\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_angry", "Number")).findFirst().isPresent());
    Assert.assertTrue("Number \"react_haha\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_haha", "Number")).findFirst().isPresent());
    Assert.assertTrue("Number \"react_like\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_like", "Number")).findFirst().isPresent());
    Assert.assertTrue("Number \"react_love\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_love", "Number")).findFirst().isPresent());
    Assert.assertTrue("Number \"react_sad\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_sad", "Number")).findFirst().isPresent());
    Assert.assertTrue("Number \"react_wow\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "react_wow", "Number")).findFirst().isPresent());
    Assert.assertTrue("Number \"shares\" in Post list not found",
        postsProps.stream().filter(x -> checkAttribute(x, "shares", "Number")).findFirst().isPresent());
    Assert.assertTrue("Reference \"page_id\" in Page list not found",
        postsProps.stream().filter(x -> checkReference(x, "page_id", "String", "Pages", 1, 1)).findFirst().isPresent());
    Assert.assertTrue("String \"post_id\" in Page list not found",
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
