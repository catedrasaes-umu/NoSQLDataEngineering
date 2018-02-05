package es.um.nosql.s13e.m2t.morphia

import java.io.File
import es.um.nosql.s13e.util.emf.ModelLoader
import es.um.nosql.s13e.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation
import java.io.PrintStream

class DiffMorphiaBaseGen
{
  var modelName = "";
  var importRoute = "";
  static File outputDir;

  // For the base generation we need three items:
  // - The folder to output the generation
  // - The model name to name the files and variables
  // - The root entities (entities with at least one root version), so we can include some variables and generate base validators.
  def m2t(File modelFile, File outputFolder)
  {
    val loader = new ModelLoader(EntitydifferentiationPackage.eINSTANCE);
    val diff = loader.load(modelFile, EntityDifferentiation);

    m2t(diff, outputFolder);
  }

  /**
   * Method used to start the generation process from an EntityDifferentiation object
   */
  def void m2t(EntityDifferentiation diff, File outputFolder)
  {
    modelName = diff.name;
    outputDir = outputFolder.toPath.resolve(modelName).resolve("commons").toFile;
    outputDir.mkdirs;

    if (outputDir.toString.contains("src"))
    {
      importRoute = outputDir.toString.substring(outputDir.toString.lastIndexOf("src") + 4).replace(File.separatorChar, ".");
    }
    else
    {
      importRoute = outputDir.toString;
    }

    modelName = diff.name;

    writeToFile("Commons.java", generateCommonsFile());
  }

  def generateCommonsFile()
  '''
  package «importRoute»;

  import org.mongodb.morphia.Morphia;
  import org.mongodb.morphia.mapping.MappingException;
  import com.mongodb.BasicDBList;
  import com.mongodb.DBObject;

  public class Commons
  {
    private static Morphia MORPHIA_INSTANCE = null;

    private static Morphia GET_MORPHIA()
    {
      if (MORPHIA_INSTANCE == null)
      {
        MORPHIA_INSTANCE = new Morphia();
        MORPHIA_INSTANCE = MORPHIA_INSTANCE.mapPackage("«importRoute.subSequence(0, importRoute.indexOf(".commons"))»");
      }

      return MORPHIA_INSTANCE;
    }

    public static Object CAST(Class<?> className, Object obj)
    {
      return GET_MORPHIA().fromDBObject(null, className, (DBObject)obj);
    }
  
    public static Object CAST_ARRAY(Class<?> className, Object[] obj)
    {
      Object[] result = new Object[obj.length];
      for (int i = 0; i < obj.length; i++)
        result[i] = CAST(className, obj[i]);
    
      return result;
    }
  
    public static boolean IS_CASTABLE(Class<?> className, Object obj)
    {
      try
      {
        CAST(className, obj);
      } catch(MappingException e)
      {
        return false;
      }
  
      return true;
    }
  
    public static boolean IS_CASTABLE_ARRAY(Class<?> className, BasicDBList fieldObj)
    {
      boolean result = true;
    
      for (Object obj : fieldObj)
        result = IS_CASTABLE(className, obj);
  
      return result;
    }
  }
  '''

  /**
   * Method used to write a generated CharSequence to a file
   */
  private def writeToFile(String filename, CharSequence toWrite)
  {
    val outFile = outputDir.toPath().resolve(filename).toFile()
    val outFileWriter = new PrintStream(outFile)
    outFileWriter.print(toWrite)
    outFileWriter.close()
  }
}