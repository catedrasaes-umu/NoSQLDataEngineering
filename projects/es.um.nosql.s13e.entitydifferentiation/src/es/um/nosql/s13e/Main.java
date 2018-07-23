package es.um.nosql.s13e;

import java.io.File;

import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.m2m.NoSQLSchemaToEntityDiff;
import es.um.nosql.s13e.m2t.mongoose.DiffMongooseBaseGen;
import es.um.nosql.s13e.m2t.mongoose.DiffToMongoose;
import es.um.nosql.s13e.m2t.morphia.DiffMorphiaBaseGen;
import es.um.nosql.s13e.m2t.morphia.DiffToMorphia;
import es.um.nosql.s13e.util.EntityDifferentiationWriter;

/**
 * Usage: Just change the following parameters to the routes you prefer, and execute it freely.
 * The main method provided is more than enough to execute all the workflow.
 * @dependencies: es.um.nosql.examples (for input models), es.um.nosql.s13e (for the NoSQLSchema metamodel)
 * @param INPUT_FOLDER The folder in which NoSQLSchema xmi models are contained.
 * @param OUTPUT_FOLDER The folder in which EntityDiff xmi models will be stored.
 * @param MONGOOSE_OUTPUT_GEN_BASE_FOLDER The folder in which Mongoose code will be generated.
 * @param MORPHIA_OUTPUT_GEN_BASE_FOLDER The folder in which Morphia code will be generated.
 * @param YAML_CONFIG_ROUTE The yaml configuration file location.
 * @param GENERATE_BASE_FILES True if base files should be generated. False otherwise.
 */
public class Main
{
  private static final String INPUT_FOLDER = "../es.um.nosql.examples/";
  private static final String OUTPUT_FOLDER = "../es.um.nosql.examples/";
  private static final String MONGOOSE_OUTPUT_GEN_BASE_FOLDER = OUTPUT_FOLDER + "mongoose/";
  private static final String MORPHIA_OUTPUT_GEN_BASE_FOLDER = "src/es/um/nosql/s13e/";
  private static final String YAML_CONFIG_ROUTE = "config/config.yaml";
  private static final boolean GENERATE_BASE_FILES = true;

  public static void main(String[] args)
  {
    String[] input_models = new String[] {"everypolitician_sweden", "facebook", "harvard", "links", "mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks", "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + ".xmi";
      String outputFile = OUTPUT_FOLDER + input_model + "/" + input_model + "_Diff.xmi";
      prepareM2MExample(new File(inputFile), new File(outputFile));
      prepareM2MongooseExample(new File(outputFile), new File(MONGOOSE_OUTPUT_GEN_BASE_FOLDER + input_model), new File(YAML_CONFIG_ROUTE));
      prepareM2MorphiaExample(new File(outputFile), new File(MORPHIA_OUTPUT_GEN_BASE_FOLDER), new File(YAML_CONFIG_ROUTE));
    }
  }

  public static void prepareM2MExample(File inputFile, File outputFile)
  {
    System.out.println("Generating EntityDiff model for " + inputFile.getName() + " in " + outputFile.getPath());

    NoSQLSchemaToEntityDiff transformer = new NoSQLSchemaToEntityDiff();
    EntityDifferentiation diffModel = transformer.m2m(inputFile);

    EntityDifferentiationWriter writer = new EntityDifferentiationWriter();
    writer.write(diffModel, outputFile.getAbsolutePath());

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
