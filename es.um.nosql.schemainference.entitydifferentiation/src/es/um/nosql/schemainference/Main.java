package es.um.nosql.schemainference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.schemainference.m2m.NoSQLSchemaToEntityDiff;

public class Main
{
  public static String INPUT_FOLDER = "testSources/";
  public static String OUTPUT_FOLDER = "testOutput/";

  public static void main(String[] args)
  {
    String input_model = "mongoMovies3";
    String inputFile = INPUT_FOLDER + input_model + ".xmi";
    String outputFile = OUTPUT_FOLDER + input_model + "_Diff.xmi";
    prepareM2MExample(inputFile, outputFile);
  }

  private static void prepareM2MExample(String inputFile, String outputFile)
  {
    File INPUT_MODEL = new File(inputFile);
    File OUTPUT_MODEL = new File(outputFile);

    NoSQLSchemaToEntityDiff transformer = new NoSQLSchemaToEntityDiff();
    EntityDifferentiation diffModel = transformer.m2m(INPUT_MODEL);

    Resource outputRes = transformer.getResourceManager().getResourceSet().createResource(URI.createFileURI(OUTPUT_MODEL.getAbsolutePath()));
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
  }
/*
  private static void prepareM2TExample(String inputFile, String outputFolder)
  {
    DiffToMongoose diff2Mongoose = new DiffToMongoose
  }
*/
/*
  def static void main(String[] args)
  {
    if (args.length < 1)
    {
      System.out.println("Usage: DiffToMongoose model [outdir]")
      System.exit(-1)
    }

        val inputModel = new File(args.head)
        val ResourceManager rm = new ResourceManager(EntitydifferentiationPackage.eINSTANCE,
          NoSQLSchemaPackage.eINSTANCE)
        rm.loadResourcesAsStrings(inputModel.getPath())
        val EntityDifferentiation td = rm.resources.head.contents.head as EntityDifferentiation

    outputDir = new File(if (args.length > 1) args.get(1) else ".")
                .toPath().resolve(td.name).toFile()
    // Create destination directory if it does not exist
    outputDir.mkdirs()
        System.out.println("Generating Javascript for "
                  + inputModel.getPath()
                  + " in "
                  + outputDir.getPath())

    val diff_to_mongoose = new DiffToMongoose()
    diff_to_mongoose.generate(td)
    
        System.exit(0)
    }*/
}
