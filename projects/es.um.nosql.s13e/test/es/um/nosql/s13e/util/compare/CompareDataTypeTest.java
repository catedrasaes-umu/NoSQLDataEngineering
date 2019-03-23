package es.um.nosql.s13e.util.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PList;
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.PSet;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.DataType;

public class CompareDataTypeTest
{
  private ComparePrimitiveType cPrimitiveType;
  private ComparePMap cPMap;
  private ComparePTuple cPTuple;
  private ComparePList cPList;
  private ComparePSet cPSet;

  @Before
  public void setUp()
  {
    cPrimitiveType = new ComparePrimitiveType();
    cPMap = new ComparePMap();
    cPTuple = new ComparePTuple();
    cPList = new ComparePList();
    cPSet = new ComparePSet();
  }

  @Test
  public void testPrimitiveType()
  {
    assertFalse(cPrimitiveType.compare(null, null));

    assertFalse(cPrimitiveType.compare(createPType("string"), null));
    assertFalse(cPrimitiveType.compare(null, createPType("string")));

    assertFalse(cPrimitiveType.compare(createPType(null), createPType("string")));
    assertFalse(cPrimitiveType.compare(createPType("string"), createPType(null)));

    assertTrue(cPrimitiveType.compare(createPType("null"), createPType("null")));
    assertTrue(cPrimitiveType.compare(createPType("string"), createPType("string")));
  }

  @Test
  public void testPMap()
  {
    assertFalse(cPMap.compare(null, null));

    assertFalse(cPMap.compare(createPMap(null, null), null));
    assertFalse(cPMap.compare(null, createPMap(null, null)));

    assertFalse(cPMap.compare(createPMap(createPType("string"), null), createPMap(createPType("int"), null)));
    assertTrue(cPMap.compare(createPMap(createPType("bool"), null), createPMap(createPType("bool"), null)));

    assertFalse(cPMap.compare(createPMap(null, createPSet(createPType("string"))), createPMap(null, createPSet(createPType("int")))));
    assertTrue(cPMap.compare(createPMap(null, createPSet(createPType("bool"))), createPMap(null, createPSet(createPType("bool")))));

    assertFalse(cPMap.compare(createPMap(createPType("bool"), createPSet(createPType("string"))), createPMap(createPType("bool"), createPSet(createPType("int")))));
    assertTrue(cPMap.compare(createPMap(createPType("int"), createPSet(createPType("bool"))), createPMap(createPType("int"), createPSet(createPType("bool")))));
  }

  @Test
  public void testPTuple()
  {
    assertFalse(cPTuple.compare(null, null));

    assertFalse(cPTuple.compare(createPTuple(createPType("string")), null));
    assertFalse(cPTuple.compare(null, createPTuple(createPType("string"))));

    assertFalse(cPTuple.compare(createPTuple(createPType("string")), createPTuple(createPType("string"), createPType("string"))));
    assertTrue(cPTuple.compare(createPTuple(createPType("string")), createPTuple(createPType("string"))));

    assertTrue(cPTuple.compare(createPTuple(createPType("string"), createPType("int")), createPTuple(createPType("string"), createPType("int"))));
    assertTrue(cPTuple.compare(createPTuple(createPType("string"), createPType("int")), createPTuple(createPType("int"), createPType("string"))));

    assertFalse(cPTuple.compare(createPTuple(createPSet(createPList(createPType("bool"))), createPTuple(createPType("string"))),
        createPTuple(createPType("int"), createPSet(createPList(createPType("bool"))), createPType("string"))));
    assertFalse(cPTuple.compare(createPTuple(createPSet(createPList(createPType("bool")))), createPTuple(createPList(createPSet(createPType("bool"))))));
    assertTrue(cPTuple.compare(createPTuple(createPSet(createPList(createPType("bool")))), createPTuple(createPSet(createPList(createPType("bool"))))));
  }

  @Test
  public void testPList()
  {
    assertFalse(cPList.compare(null, null));

    assertFalse(cPList.compare(createPList(createPType("string")), null));
    assertFalse(cPList.compare(null, createPList(createPType("string"))));

    assertFalse(cPList.compare(createPList(null), createPList(createPType("string"))));
    assertFalse(cPList.compare(createPList(createPType("string")), createPList(null)));

    assertFalse(cPList.compare(createPList(createPType(null)), createPList(createPType("string"))));
    assertFalse(cPList.compare(createPList(createPType("string")), createPList(createPType(null))));

    assertTrue(cPList.compare(createPList(createPType("null")), createPList(createPType("null"))));
    assertTrue(cPList.compare(createPList(createPType("string")), createPList(createPType("string"))));

    assertTrue(cPList.compare(createPList(createPSet(createPType("null"))), createPList(createPSet(createPType("null")))));
    assertTrue(cPList.compare(createPList(createPSet(createPType("string"))), createPList(createPSet(createPType("string")))));
  }

  @Test
  public void testPSet()
  {
    assertFalse(cPSet.compare(null, null));

    assertFalse(cPSet.compare(createPSet(createPType("string")), null));
    assertFalse(cPSet.compare(null, createPSet(createPType("string"))));

    assertFalse(cPSet.compare(createPSet(null), createPSet(createPType("string"))));
    assertFalse(cPSet.compare(createPSet(createPType("string")), createPSet(null)));

    assertFalse(cPSet.compare(createPSet(createPType(null)), createPSet(createPType("string"))));
    assertFalse(cPSet.compare(createPSet(createPType("string")), createPSet(createPType(null))));

    assertTrue(cPSet.compare(createPSet(createPType("null")), createPSet(createPType("null"))));
    assertTrue(cPSet.compare(createPSet(createPType("string")), createPSet(createPType("string"))));

    assertTrue(cPSet.compare(createPSet(createPList(createPType("null"))), createPSet(createPList(createPType("null")))));
    assertTrue(cPSet.compare(createPSet(createPList(createPType("string"))), createPSet(createPList(createPType("string")))));
  }

  private PMap createPMap(PrimitiveType key, DataType value)
  {
    PMap pMap = NoSQLSchemaFactory.eINSTANCE.createPMap();
    pMap.setKeyType(key);
    pMap.setValueType(value);

    return pMap;
  }

  private PTuple createPTuple(DataType... elements)
  {
    PTuple pTuple = NoSQLSchemaFactory.eINSTANCE.createPTuple();
    pTuple.getElements().addAll(Arrays.asList(elements));

    return pTuple;
  }

  private PSet createPSet(DataType element)
  {
    PSet pSet = NoSQLSchemaFactory.eINSTANCE.createPSet();
    pSet.setElementType(element);

    return pSet;
  }

  private PList createPList(DataType element)
  {
    PList pList = NoSQLSchemaFactory.eINSTANCE.createPList();
    pList.setElementType(element);

    return pList;
  }

  private PrimitiveType createPType(String type)
  {
    PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
    pType.setName(type);

    return pType;
  }
}
