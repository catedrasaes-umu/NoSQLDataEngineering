package es.um.nosql.schemainference.opensanctions.commons;

import org.mongodb.morphia.Morphia;
import com.mongodb.DBObject;

public class Commons
{
  private static Morphia MORPHIA_INSTANCE = null;

  private static Morphia GET_MORPHIA()
  {
    if (MORPHIA_INSTANCE == null)
    {
      MORPHIA_INSTANCE = new Morphia();
      MORPHIA_INSTANCE = MORPHIA_INSTANCE.mapPackage("es.um.nosql.schemainference.mongoMovies3");
    }

    return MORPHIA_INSTANCE;
  }

  public static Object CAST(Class<?> className, Object obj)
  {
    return GET_MORPHIA().fromDBObject(null, className, (DBObject)obj);
  }
}
