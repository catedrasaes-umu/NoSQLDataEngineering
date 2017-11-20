package es.um.nosql.schemainference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage;
import es.um.nosql.schemainference.m2m.NoSQLSchemaToEntityDiff;
import es.um.nosql.schemainference.m2t.mongoose.DiffMongooseBaseGen;
import es.um.nosql.schemainference.m2t.mongoose.DiffToMongoose;
import es.um.nosql.schemainference.m2t.morphia.DiffToMorphia;
import es.um.nosql.schemainference.util.emf.ResourceManager;

public class Main
{
  public static String INPUT_FOLDER = "testSources/";
  public static String OUTPUT_FOLDER = "testOutput/";
  public static String MONGOOSE_OUTPUT_GEN_BASE_FOLDER = OUTPUT_FOLDER + "gen/";
  public static String MORPHIA_OUTPUT_GEN_BASE_FOLDER = "src/es/um/nosql/schemainference/";
  public static boolean GENERATE_BASE_FILES = true;

  public static void main(String[] args)
  {
    String input_model = "mongoMovies3";
    String inputFile = INPUT_FOLDER + input_model + ".xmi";
    String outputFile = OUTPUT_FOLDER + input_model + "_Diff.xmi";
//    prepareM2MExample(inputFile, outputFile);
//    prepareM2MongooseExample(outputFile, MONGOOSE_OUTPUT_GEN_BASE_FOLDER);
    prepareM2MorphiaExample(outputFile, MORPHIA_OUTPUT_GEN_BASE_FOLDER);
  }

  public static void prepareM2MExample(String inputFile, String outputFile)
  {
    File INPUT_MODEL = new File(inputFile);
    File OUTPUT_MODEL = new File(outputFile);

    System.out.println("Generating EntityDiff model for " + INPUT_MODEL.getName() + " in " + OUTPUT_MODEL.getPath());

    NoSQLSchemaToEntityDiff transformer = new NoSQLSchemaToEntityDiff();
    EntityDifferentiation diffModel = transformer.m2m(INPUT_MODEL);

    NoSQLSchemaPackage nosqlschemaPackage = NoSQLSchemaPackage.eINSTANCE;
    EntitydifferentiationPackage entitydiffPackage = EntitydifferentiationPackage.eINSTANCE;
    ResourceManager resManager = new ResourceManager(nosqlschemaPackage, entitydiffPackage);

    nosqlschemaPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.schemainference/model/nosqlschema.ecore", true));
    entitydiffPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.schemainference.entitydifferentiation/model/entitydifferentiation.ecore", true));

    Resource outputRes = resManager.getResourceSet().createResource(URI.createFileURI(OUTPUT_MODEL.getAbsolutePath()));
    outputRes.getContents().add(diffModel);

    // Configure output
    Map<Object,Object> options = new HashMap<Object,Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8"); 

    try
    {
      outputRes.save(new FileOutputStream(OUTPUT_MODEL), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    System.out.println("Transformation model finished");
  }

  public static void prepareM2MongooseExample(String inputFile, String outputFolder)
  {
    File INPUT_MODEL = new File(inputFile);
    File OUTPUT_M2T_FOLDER = new File(outputFolder);

    System.out.println("Generating Mongoose code for " + INPUT_MODEL.getName() + " in " + OUTPUT_M2T_FOLDER.getPath());

    if (!OUTPUT_M2T_FOLDER.exists())
      OUTPUT_M2T_FOLDER.mkdirs();

    if (GENERATE_BASE_FILES)
    {
      DiffMongooseBaseGen baseGen = new DiffMongooseBaseGen();
      baseGen.m2t(INPUT_MODEL, OUTPUT_M2T_FOLDER);
    }

    DiffToMongoose diff2Mongoose = new DiffToMongoose();
    diff2Mongoose.m2t(INPUT_MODEL, OUTPUT_M2T_FOLDER);

    System.out.println("Code generation finished");
  }

  public static void prepareM2MorphiaExample(String inputFile, String outputFolder)
  {
    File INPUT_MODEL = new File(inputFile);
    File OUTPUT_M2T_FOLDER = new File(outputFolder);

    System.out.println("Generating Morphia code for " + INPUT_MODEL.getName() + " in " + OUTPUT_M2T_FOLDER.getPath());

    if (!OUTPUT_M2T_FOLDER.exists())
      OUTPUT_M2T_FOLDER.mkdirs();
/*
    // UnionType and some Main stuff..
    if (GENERATE_BASE_FILES)
    {
      DiffMongooseBaseGen baseGen = new DiffMongooseBaseGen();
      baseGen.m2t(INPUT_MODEL, OUTPUT_M2T_FOLDER);
    }
*/
    DiffToMorphia diff2Morphia = new DiffToMorphia();
    diff2Morphia.m2t(INPUT_MODEL, OUTPUT_M2T_FOLDER);

    System.out.println("Code generation finished");
  }
}