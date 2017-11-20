package es.um.nosql.schemainference.m2m;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test1
{
  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void test()
  {
    // A type should never be a 'required' property. Since we are working w mongoDB, objects won't usually have a type, because its type is the collection name.
    // Also: The 'required' clause on mongoose requires a field to exist and also, if it is an array, to NOT be empty
    // This may cause problems when an association has a lowerBound = 0. To shortcut this, for the moment we wont add the required option to a field
    // if it is an association w lowerBound == 0. Proof: https://stackoverflow.com/questions/27268172/mongoose-schema-to-require-array-that-can-be-empty

    // Comprobar lo de las propiedades comunes con needsTypeCheck si aparece en todas...solo hay que cambiar la estrategia de hashing
  }
}