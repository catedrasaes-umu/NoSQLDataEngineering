package es.um.nosql.s13e.mongosongs_w_yaml.commons;

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
      MORPHIA_INSTANCE = MORPHIA_INSTANCE.mapPackage("es.um.nosql.s13e.mongosongs");
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
