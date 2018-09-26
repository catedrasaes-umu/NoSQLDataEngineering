package es.um.nosql.s13e.util.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.Null;

public class CompareNullTest
{
  private CompareNull comparer;

  @Before
  public void setUp() throws Exception
  {
    comparer = new CompareNull();
  }

  @Test
  public void test()
  {
    assertFalse(comparer.compare(null, null));

    assertFalse(comparer.compare(createNull("name"), null));
    assertFalse(comparer.compare(null, createNull("name")));

    assertTrue(comparer.compare(createNull("name"), createNull("name")));
    assertTrue(comparer.compare(createNull(null), createNull("name")));
    assertTrue(comparer.compare(createNull("aaa"), createNull("bbb")));
  }

  private Null createNull(String name)
  {
    Null theNull = NoSQLSchemaFactory.eINSTANCE.createNull();
    theNull.setName(name);

    return theNull;
  }
}
