package es.um.nosql.s13e.util.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;

public class ComparePrimitiveTypeTest
{
  private ComparePrimitiveType comparer;

  @Before
  public void setUp()
  {
    comparer = new ComparePrimitiveType();
  }

  @Test
  public void test()
  {
    assertFalse(comparer.compare(null, null));

    assertFalse(comparer.compare(createPType("string"), null));
    assertFalse(comparer.compare(null, createPType("string")));

    assertFalse(comparer.compare(createPType(null), createPType("string")));
    assertFalse(comparer.compare(createPType("string"), createPType(null)));

    assertTrue(comparer.compare(createPType("null"), createPType("null")));
    assertTrue(comparer.compare(createPType("string"), createPType("string")));
  }

  private PrimitiveType createPType(String type)
  {
    PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
    pType.setName(type);

    return pType;
  }
}
