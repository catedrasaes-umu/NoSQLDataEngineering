package es.um.nosql.orchestrator.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.junit.Test;

import es.um.nosql.orchestrator.Orchestrator;
import es.um.nosql.orchestrator.process.FillInferDb;
import es.um.nosql.orchestrator.util.InferenceMode;
import es.um.nosql.orchestrator.util.constants.ConfigConstants;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.db.interfaces.EveryPolitician2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.ResourceManager;

public class MergeEVTest
{
  String inputRoute = "testSources/ERROR_MergeEV.json";
  String dbName = "DEBUG_everypolitician";

  @Test
  public void test()
  {
    EveryPolitician2Db controller = new EveryPolitician2Db(DbType.MONGODB, "localhost");
    controller.run(inputRoute, dbName);
    controller.shutdown();
/*
    MongoDBImport inferrer = new MongoDBImport();
    jArray = inferrer.mapRed2Array(ConfigConstants.DATABASE_IP, dbName, ConfigConstants.MONGODB_MAPREDUCE_FOLDER);

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromGsonArray(dbName, jArray, outputModel);

    dbFillInfer.inferFromDb(dbName, outputModel);

    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(inputRoute), NoSQLSchema.class);*/
  }
}