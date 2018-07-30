package es.um.nosql.s13e.m2t.morphia

import java.io.File
import es.um.nosql.s13e.util.ModelLoader
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation
import es.um.nosql.s13e.m2t.commons.Commons

class DiffMorphiaBaseGen
{
  var importRoute = "";

  // For the base generation we need three items:
  // - The folder to output the generation
  // - The model name to name the files and variables
  // - The root entities (entities with at least one root variation), so we can include some variables and generate base validators.
  def m2t(File modelFile, File outputFolder)
  {
    val loader = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    val diff = loader.load(modelFile, EntityDifferentiation);

    m2t(diff, outputFolder);
  }

  /**
   * Method used to start the generation process from an EntityDifferentiation object
   */
  def void m2t(EntityDifferentiation diff, File outputFolder)
  {
    val modelName = diff.name;
    val outputDir = outputFolder.toPath.resolve(modelName).resolve("commons").toFile;
    outputDir.mkdirs;

    if (outputDir.toString.contains("src"))
    {
      importRoute = outputDir.toString.substring(outputDir.toString.lastIndexOf("src") + 4).replace(File.separatorChar, ".");
    }
    else
    {
      importRoute = outputDir.toString;
    }

    Commons.WRITE_TO_FILE(outputDir, "Commons.java", generateCommonsFile());
  }

  private def generateCommonsFile()
  '''
  package «importRoute»;

  import java.util.ArrayList;
  import java.util.List;

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

    public static <T> T CAST_OBJDB(Class<T> className, Object obj)
    {
      return GET_MORPHIA().fromDBObject(null, className, (DBObject)obj);
    }
    
    public static <T> List<T> CAST_LIST_OBJDB(Class<T> className, Object obj)
    {
      List<T> result = new ArrayList<T>();
    
      for (Object o : (BasicDBList)obj)
        result.add(CAST_OBJDB(className, o));
    
      return result;
    }

    public static boolean IS_CASTABLE_OBJDB(Class<?> className, Object obj)
    {
      if (!(obj instanceof DBObject))
        return false;

      try
      {
        CAST_OBJDB(className, obj);
      } catch(MappingException e)
      {
        return false;
      }
  
      return true;
    }

    public static boolean IS_CASTABLE_LIST_OBJDB(Class<?> className, Object fieldObj)
    {
      if (!(fieldObj instanceof BasicDBList))
        return false;

      for (Object obj : (BasicDBList)fieldObj)
        if (!IS_CASTABLE_OBJDB(className, obj))
          return false;

      return true;
    }

    public static boolean IS_CASTABLE_LIST(Class<?> className, Object obj)
    {
      if (!(obj instanceof List))
        return false;

      for (Object o : (List<?>)obj)
        if (!className.isInstance(o))
          return false;

      return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> CAST_LIST(Class<T> className, Object obj)
    {
      return (List<T>)obj;
    }
  }
  '''
}