package es.um.nosql.orchestrator;

import java.io.File;

import es.um.nosql.orchestrator.process.FillInferDb;
import es.um.nosql.orchestrator.util.InferenceMode;
import es.um.nosql.orchestrator.util.constants.ConfigConstants;
import es.um.nosql.s13e.db.interfaces.Companies2Db;
import es.um.nosql.s13e.db.interfaces.EveryPolitician2Db;
import es.um.nosql.s13e.db.interfaces.Facebook2Db;
import es.um.nosql.s13e.db.interfaces.Harvard2Db;
import es.um.nosql.s13e.db.interfaces.Json2Db;
import es.um.nosql.s13e.db.interfaces.Publications2Db;
import es.um.nosql.s13e.db.interfaces.Links2Db;
import es.um.nosql.s13e.db.interfaces.Model2Db;
import es.um.nosql.s13e.db.interfaces.OpenSanctions2Db;
import es.um.nosql.s13e.db.interfaces.Pleiades2Db;
import es.um.nosql.s13e.db.interfaces.Proteins2Db;
import es.um.nosql.s13e.db.interfaces.StackOverflow2Db;
import es.um.nosql.s13e.db.interfaces.Source2Db;
import es.um.nosql.s13e.db.interfaces.UrbanDictionary2Db;
import es.um.nosql.s13e.db.interfaces.Webclick2Db;
import es.um.nosql.s13e.db.utils.DbType;

public class Orchestrator
{
  private DbType dbType;

  private FillInferDb dbFillInfer;

  public Orchestrator(DbType dbType)
  {
    this.dbType = dbType;
    this.dbFillInfer = new FillInferDb(this.dbType);
  }

  public void runCompaniesExample(InferenceMode option, String sourceFile)
  {
    String dbName = "companies";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Companies2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runEveryPoliticianExample(InferenceMode option, String sourceFile)
  {
    String dbName = "everypolitician";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new EveryPolitician2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runFacebookExample(InferenceMode option, String sourceFolder)
  {
    String dbName = "facebook";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Facebook2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFolder, outputModel);
  }

  public void runHarvardExample(InferenceMode option, String sourceFile)
  {
    String dbName = "harvard";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Harvard2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runJsonExample(InferenceMode option, String sourceFile)
  {
    File source = new File(sourceFile);
    String dbName = source.getName().substring(0, source.getName().indexOf("."));
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + "_RESULT.xmi";

    runExample(option, new Json2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runLinksExample(InferenceMode option, String sourceFolder)
  {
    String dbName = "links";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Links2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFolder, outputModel);
  }

  public void runModelExample(InferenceMode option, String sourceFile)
  {
    String fileName = new File(sourceFile).getName();
    String dbName = fileName.substring(0, fileName.lastIndexOf("."));
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + "_RESULT.xmi";

    runExample(option, new Model2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runOpenSanctionsExample(InferenceMode option, String sourceFile)
  {
    String dbName = "opensanctions";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new OpenSanctions2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runPleiadesExample(InferenceMode option, String sourceFile)
  {
    String dbName = "pleiades";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Pleiades2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runProteinsExample(InferenceMode option, String sourceFolder)
  {
    String dbName = "proteins";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Proteins2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFolder, outputModel);
  }

  public void runPublicationsExample(InferenceMode option, String sourceFile)
  {
    String dbName = "publications";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Publications2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runStackOverflowExample(InferenceMode option, String sourceFolder)
  {
    String dbName = "stackoverflow";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    // Users.xml: 6438660 files => 38 minutes
    // Votes.xml: 116720227 files => 10 hours
    // Comments.xml: 53566720 files => 5 hours
    // Posts.xml: 33566854 files  => ???
    // Tags.xml: 48375 files
    // PostLinks.xml: 3993518 files
    // Badges.xml: 21882069 files
    runExample(option, new StackOverflow2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFolder, outputModel);
  }

  public void runUrbanDictionaryExample(InferenceMode option, String sourceFile)
  {
    String dbName = "urban2";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new UrbanDictionary2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFile, outputModel);
  }

  public void runWebclickExample(InferenceMode option, String sourceFolder)
  {
    String dbName = "webclicks";
    String outputModel = ConfigConstants.OUTPUT_FOLDER + dbName + ".xmi";

    runExample(option, new Webclick2Db(dbType, ConfigConstants.DATABASE_IP), dbName, sourceFolder, outputModel);
  }

  private void runExample(InferenceMode option, Source2Db source2Db, String dbName, String source, String outputModel)
  {
    if (option != InferenceMode.INFER_ONLY)
    {
      if (new File(source).isDirectory())
        dbFillInfer.fillDbFromFolder(source2Db, dbName, source);
      else
        dbFillInfer.fillDbFromFile(source2Db, dbName, source);
    }

    if (option != InferenceMode.FILL_ONLY)
      dbFillInfer.inferFromDb(dbName, outputModel);
  }
}
