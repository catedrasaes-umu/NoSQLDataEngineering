package es.um.nosql.schemainference.harvard.commons;

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
      MORPHIA_INSTANCE = MORPHIA_INSTANCE.mapPackage("es.um.nosql.schemainference.harvard");
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
