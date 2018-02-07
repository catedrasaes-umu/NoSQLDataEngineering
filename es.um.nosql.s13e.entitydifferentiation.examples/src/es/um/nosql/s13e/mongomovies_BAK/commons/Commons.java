package es.um.nosql.s13e.mongomovies_BAK.commons;

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
      MORPHIA_INSTANCE = MORPHIA_INSTANCE.mapPackage("es.um.nosql.s13e.mongomovies");
    }

    return MORPHIA_INSTANCE;
  }

  public static Object CAST(Class<?> className, Object obj)
  {
    return GET_MORPHIA().fromDBObject(null, className, (DBObject)obj);
  }

  public static Object CAST_LIST(Class<?> className, List<Object> obj)
  {
    List<Object> result = new ArrayList<Object>(obj.size());

    for (Object o : obj)
      result.add(CAST(className, o));

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

  public static boolean IS_CASTABLE_LIST(Class<?> className, Object object)
  {
    if (!(object instanceof List))
      return false;

    for (Object o : (List<?>)object)
      if (!className.isInstance(o))
        return false;

    return true;
  }
}
