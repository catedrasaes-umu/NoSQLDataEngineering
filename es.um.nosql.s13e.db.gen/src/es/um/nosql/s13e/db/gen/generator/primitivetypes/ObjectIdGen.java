package es.um.nosql.s13e.db.gen.generator.primitivetypes;

import org.bson.types.ObjectId;

public class ObjectIdGen
{
  private static ObjectIdGen THE_INSTANCE;

  private ObjectIdGen() {}

  public static ObjectIdGen GET_INSTANCE()
  {
    if (THE_INSTANCE == null)
      THE_INSTANCE = new ObjectIdGen();

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