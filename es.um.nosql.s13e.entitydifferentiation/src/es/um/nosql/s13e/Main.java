package es.um.nosql.s13e;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage;
import es.um.nosql.s13e.m2m.NoSQLSchemaToEntityDiff;
import es.um.nosql.s13e.m2t.mongoose.DiffMongooseBaseGen;
import es.um.nosql.s13e.m2t.mongoose.DiffToMongoose;
import es.um.nosql.s13e.m2t.morphia.DiffMorphiaBaseGen;
import es.um.nosql.s13e.m2t.morphia.DiffToMorphia;
import es.um.nosql.s13e.util.emf.ResourceManager;

public class Main
{
  public static String INPUT_FOLDER = "testSources/";
  public static String OUTPUT_FOLDER = "testOutput/";
  public static String MONGOOSE_OUTPUT_GEN_BASE_FOLDER = OUTPUT_FOLDER + "mongoose/";
  public static String MORPHIA_OUTPUT_GEN_BASE_FOLDER = "src/es/um/nosql/s13e/";
  private static String YAML_CONFIG_ROUTE = "config.yaml";
  public static boolean GENERATE_BASE_FILES = true;

  public static void main(String[] args)
  {
    String[] input_models = new String[] {"test3"/*"everypolitician_sweden", "facebook", "harvard", "links", "mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks"*/};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + ".xmi";
      String configFile = INPUT_FOLDER + YAML_CONFIG_ROUTE;
      String outputFile = OUTPUT_FOLDER + input_model + "_Diff.xmi";
      prepareM2MExample(inputFile, outputFile);
      prepareM2MongooseExample(new File(outputFile), new File(MONGOOSE_OUTPUT_GEN_BASE_FOLDER + input_model), new File(configFile));
      prepareM2MorphiaExample(new File(outputFile), new File(MORPHIA_OUTPUT_GEN_BASE_FOLDER), new File(configFile));      
    }
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

    nosqlschemaPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));
    entitydiffPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e.entitydifferentiation/model/entitydifferentiation.ecore", true));

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

  public static void prepareM2MongooseExample(File inputFile, File outputFolder, File configFile)
  {
    System.out.println("Generating Mongoose code for " + inputFile.getName() + " in " + outputFolder.getPath());

    if (!outputFolder.exists())
      outputFolder.mkdirs();

    if (GENERATE_BASE_FILES)
    {
      DiffMongooseBaseGen baseGen = new DiffMongooseBaseGen();
      baseGen.m2t(inputFile, outputFolder);
    }

    DiffToMongoose diff2Mongoose = new DiffToMongoose();
    diff2Mongoose.m2t(inputFile, outputFolder, configFile);

    System.out.println("Code generation finished");
  }

  public static void prepareM2MorphiaExample(File inputFile, File outputFolder, File configFile)
  {
    System.out.println("Generating Morphia code for " + inputFile.getName() + " in " + outputFolder.getPath());

    if (!outputFolder.exists())
      outputFolder.mkdirs();

    // Morphia instance and static cast method
    if (GENERATE_BASE_FILES)
    {
      DiffMorphiaBaseGen baseGen = new DiffMorphiaBaseGen();
      baseGen.m2t(inputFile, outputFolder);
    }

    DiffToMorphia diff2Morphia = new DiffToMorphia();
    diff2Morphia.m2t(inputFile, outputFolder, configFile);

    System.out.println("Code generation finished");
  }
}