package es.um.nosql.orchestrator.test;

import java.io.File;
import java.io.IOException;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.db.interfaces.Comp2Db;
import es.um.nosql.schemainference.db.interfaces.EPol2Db;
import es.um.nosql.schemainference.db.interfaces.Facebook2Db;
import es.um.nosql.schemainference.db.interfaces.Harvard2Db;
import es.um.nosql.schemainference.db.interfaces.Publications2Db;
import es.um.nosql.schemainference.db.interfaces.Link2Db;
import es.um.nosql.schemainference.db.interfaces.Model2Db;
import es.um.nosql.schemainference.db.interfaces.OSanctions2Db;
import es.um.nosql.schemainference.db.interfaces.Pleiades2Db;
import es.um.nosql.schemainference.db.interfaces.Proteins2Db;
import es.um.nosql.schemainference.db.interfaces.SOF2Db;
import es.um.nosql.schemainference.db.interfaces.Urban2Db;
import es.um.nosql.schemainference.db.interfaces.Webclick2Db;
import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.db.couchdb.CouchDBImport;
import es.um.nosql.schemainference.nosqlimport.db.mongodb.MongoDBImport;

@SuppressWarnings("unused")
public class InferenceTest
{
  private static final String DATABASE_IP = "localhost";
  private static final String MODELS_FOLDER = "models/";
  private static final String COUCHDB_MAPREDUCE_FOLDER = "mapreduce/couchdb/v1";
  private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";

  private static final boolean FILL_ONLY = true;
  private static final boolean FILL_AND_INFER = false;

  private static final String FILE_MODEL = "models/mongoMovies3.xmi";
  private static final String FOLDER_SOF = "F:\\Informatica\\datasets\\stackoverflow\\";
  private static final String FOLDER_EPOL = "F:\\Informatica\\datasets\\everypolitician\\";
  private static final String FILE_URBAN = "F:\\Informatica\\datasets\\urban\\words.json";
  private static final String FILE_COMPANY = "F:\\Informatica\\datasets\\companies\\companies.json";
  private static final String FOLDER_LINK = "/media/alberto/tarsonis/datasets/givealink/";
  private static final String FILE_HARVARD = "F:\\Informatica\\datasets\\harvard\\HMXPC13_DI_v2_5-14-14.csv";
  private static final String FOLDER_FACEBOOK = "F:\\Informatica\\datasets\\facebook\\";
  private static final String FOLDER_PROTEIN = "F:\\Informatica\\datasets\\proteins\\";
  private static final String FILE_PUBLICATIONS = "/media/alberto/tarsonis/datasets/publications/publications-nov-20132.csv";
  private static final String FOLDER_WEBCLICKS = "F:\\Informatica\\datasets\\webclicks\\";
  private static final String FILE_SANCTIONS = "F:\\Informatica\\datasets\\opensanctions\\master.ijson";
  private static final String FILE_PLEIDADES = "/media/alberto/tarsonis/datasets/pleiades/pleiades-places.json";

  public static void main(String[] args) throws IOException
  {//TODO: Before checking more datasets, we need to make sure "ObjectMapper oMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);"
    // Is in each interface. Thing is, this is only working por POJO objects and not readTree interfaces.
    // So tldr; datasets loaded without POJO objects are inserting NULL and empty values.
    // prepareModelExample(DbType.MONGODB, FILL_AND_INFER, FILE_MODEL);
    // prepareSOFExample(DbType.MONGODB, FILL_ONLY, FOLDER_SOF);
    // prepareEPolExample(DbType.MONGODB, FILL_AND_INFER, FOLDER_EPOL);
     prepareUrbanExample(DbType.MONGODB, FILL_AND_INFER, FILE_URBAN);                  //POJO
    // Problem with this dataset is that it contains A LOT of aggregated objects and null values.
    // Aggregated objects tend to make mongodb run out of memory during the reduce process.
    // Null values tend to abort the inference process. Until the inference process is fixed (TODO(tm)),
    // we will make use of POJO objects and ignore problematic fields. Thing is, then we have a lot of options...
    //  prepareCompanyExample(DbType.MONGODB, FILL_AND_INFER, FILE_COMPANY);              //POJO
    //  prepareLinkExample(DbType.MONGODB, FILL_AND_INFER, FOLDER_LINK);                  //POJO
    //  prepareHarvardExample(DbType.MONGODB, FILL_AND_INFER, FILE_HARVARD);              //POJO
    //  prepareFacebookExample(DbType.MONGODB, FILL_AND_INFER, FOLDER_FACEBOOK);          //POJO
    //  prepareProteinExample(DbType.MONGODB, FILL_AND_INFER, FOLDER_PROTEIN);            //POJO
    //  preparePublicationsExample(DbType.MONGODB, FILL_AND_INFER, FILE_PUBLICATIONS);    //POJO
    //  prepareWebclickExample(DbType.MONGODB, FILL_AND_INFER, FOLDER_WEBCLICKS);         //POJO
    //  prepareSanctionsExample(DbType.MONGODB, FILL_AND_INFER, FILE_SANCTIONS);
    //  preparePleiadesExample(DbType.MONGODB, FILL_AND_INFER, FILE_PLEIDADES);
  }

  public static void prepareModelExample(DbType dbType, boolean FILL_ONLY, String sourceFile)
  {
    File source = new File(sourceFile);
    String dbName = source.getName().substring(0, source.getName().indexOf("."));
    String outputModel = MODELS_FOLDER + dbName + "_RESULT.xmi";
    int minInstances = 20;
    int maxInstances = 50;

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");

    Model2Db controller = new Model2Db(dbType, DATABASE_IP);
    controller.run(sourceFile, minInstances, maxInstances);
    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName.toLowerCase(), outputModel);
  }

  public static void prepareSOFExample(DbType dbType, boolean FILL_ONLY, String source)
  {
    String dbName = "stackoverflow";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    String[] files = new String[]{"Users.xml", "Votes.xml", "Comments.xml", /*"Posts.xml", "Tags.xml", "PostLinks.xml", "Badges.xml"*/};
		// Users.xml: 6438660 filas => 38 minutos
		// Votes.xml: 116720227 filas => 10 horas
		// Comments.xml: 53566720 filas => 5 horas
		// Posts.xml: ???
		// Tags.xml: 48375 filas
		// PostLinks.xml: 3993518 filas
		// Badges.xml: 21882069 filas

    SOF2Db controller = new SOF2Db(dbType, DATABASE_IP);
    for (String fileName : files)
      controller.run(source + fileName, dbName);

    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareEPolExample(DbType dbType, boolean FILL_ONLY, String source)
  {
    String dbName = "everypolitician";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    EPol2Db controller = new EPol2Db(dbType, DATABASE_IP);
    File theFile = new File(source);
    if (theFile.isDirectory())
    {
      for (String countryRoute : theFile.list())
        controller.run(source + countryRoute, dbName);
    }
    else
      controller.run(source, dbName);

    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareUrbanExample(DbType dbType, boolean FILL_ONLY, String sourceFile)
  {
    String dbName = "urban";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    Urban2Db controller = new Urban2Db(dbType, DATABASE_IP);
    controller.run(sourceFile, dbName);
    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareCompanyExample(DbType dbType, boolean FILL_ONLY, String sourceFile)
  {
    String dbName = "companies";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    Comp2Db controller = new Comp2Db(dbType, DATABASE_IP);
    controller.run(sourceFile, dbName);
    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareLinkExample(DbType dbType, boolean FILL_ONLY, String source)
  {
    String dbName = "links";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    Link2Db controller = new Link2Db(dbType, DATABASE_IP);
    File theFile = new File(source);
    if (theFile.isDirectory())
    {
      for (String countryRoute : theFile.list())
        controller.run(source + countryRoute, dbName);
    }
    else
      controller.run(source, dbName);

    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareHarvardExample(DbType dbType, boolean FILL_ONLY, String sourceFile)
  {
    String dbName = "harvard";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    Harvard2Db controller = new Harvard2Db(dbType, DATABASE_IP);
    controller.run(sourceFile, dbName);
    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareFacebookExample(DbType dbType, boolean FILL_ONLY, String source)
  {
    String dbName = "facebook";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();
    System.out.println("Filling the " + dbType.toString() + " database...");

    Facebook2Db controller = new Facebook2Db(dbType, DATABASE_IP);
    for (String fileName : new File(source).list())
      if (fileName.endsWith(".csv"))
        controller.run(source + fileName, dbName);

    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareProteinExample(DbType dbType, boolean FILL_ONLY, String source)
  {
    String dbName = "proteins";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();
    System.out.println("Filling the " + dbType.toString() + " database...");

    Proteins2Db controller = new Proteins2Db(dbType, DATABASE_IP);
    for (String fileName : new File(source).list())
      if (fileName.endsWith(".csv"))
        controller.run(source + fileName, dbName);

    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void preparePublicationsExample(DbType dbType, boolean FILL_ONLY, String sourceFile)
  {
    String dbName = "publications";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    Publications2Db controller = new Publications2Db(dbType, DATABASE_IP);
    controller.run(sourceFile, dbName);
    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareWebclickExample(DbType dbType, boolean FILL_ONLY, String source)
  {
    String dbName = "webclicks";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    Webclick2Db controller = new Webclick2Db(dbType, DATABASE_IP);
    File theFile = new File(source);
    if (theFile.isDirectory())
    {
      for (String countryRoute : theFile.list())
        controller.run(source + countryRoute, dbName);
    }
    else
      controller.run(source, dbName);

    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void prepareSanctionsExample(DbType dbType, boolean FILL_ONLY, String sourceFile)
  {
    String dbName = "opensanctions";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    OSanctions2Db controller = new OSanctions2Db(dbType, DATABASE_IP);
    controller.run(sourceFile, dbName);
    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  public static void preparePleiadesExample(DbType dbType, boolean FILL_ONLY, String sourceFile)
  {
    String dbName = "pleiades";
    String outputModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    System.out.println("Filling the " + dbType.toString() + " database...");
    Pleiades2Db controller = new Pleiades2Db(dbType, DATABASE_IP);
    controller.run(sourceFile, dbName);
    controller.shutdown();

    System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");

    if (FILL_ONLY)
      return;
    else
      performInference(dbType, dbName, outputModel);
  }

  private static void performInference(DbType dbType, String dbName, String outputModel)
  {
    long startTime = System.currentTimeMillis();

    System.out.println("Starting inference...");
    JsonArray jArray = null;
    switch (dbType)
    {
      case MONGODB:
      {
        MongoDBImport inferrer = new MongoDBImport();
        jArray = inferrer.mapRed2Array(DATABASE_IP, dbName, MONGODB_MAPREDUCE_FOLDER);
        break;
      }
      case COUCHDB:
      {
        CouchDBImport inferrer = new CouchDBImport();
        jArray = inferrer.mapRed2Array(DATABASE_IP, dbName, COUCHDB_MAPREDUCE_FOLDER);
        break;
      }
    }

    System.out.println("Inference finished.");
    System.out.println("Starting BuildNoSQLSchema...");
    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromGsonArray(dbName, jArray, outputModel);

    System.out.println("BuildNoSQLSchema created: " + outputModel + " in " + (System.currentTimeMillis() - startTime) + " ms");
  }
}