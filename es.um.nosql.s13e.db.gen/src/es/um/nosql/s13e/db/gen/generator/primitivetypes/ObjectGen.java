package es.um.nosql.s13e.db.gen.generator.primitivetypes;

import org.bson.types.ObjectId;

public class ObjectGen
{
  private static ObjectGen THE_INSTANCE;

  private ObjectGen() {}

  public static ObjectGen GET_INSTANCE()
  {
    if (THE_INSTANCE == null)
      THE_INSTANCE = new ObjectGen();

    return THE_INSTANCE;
  }

  public ObjectId getRandomObjectId()
  {
    return new ObjectId();
  }

  public Object getNull()
  {
    return null;
  }
}