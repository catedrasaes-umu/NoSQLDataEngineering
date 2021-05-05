package es.um.unosql.xtext.athena;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.utils.Constants;
import es.um.unosql.xtext.athena.utils.io.ModelLoader;
import es.um.unosql.xtext.athena.utils.io.ModelSerializer;

public class CrossReferenceTest
{
  private ModelLoader loader;
  private ModelSerializer serializer;
  private String testFile;

  @Before
  public void setUp()
  {
    loader = new ModelLoader();
    serializer = new ModelSerializer();
    testFile = "CrossReferenceTest1.athena";
  }

  @Test
  public void testCrossreferences()
  {
    AthenaSchema schema = loader.loadAll(Constants.TEST_FOLDER + testFile);

    assertEquals("Schema CrossRef1\r\n" +
        "\r\n" +
        "import CrossRef2\r\n" +
        "\r\n" +
        "FSet fSet1 { attr1, attr2 }\r\n" +
        "FSet fSet2 CrossRef2.Hello\r\n" +
        "FSet fSet3 CrossRef2.Hello.1\r\n" +
        "FSet fSet4 CrossRef2.World\r\n", serializer.serialize(schema));
  }
  /*
  @Test
  public void testCrossreferencesAndNormalizer()
  {
    AthenaSchema schema = loader.loadAll(Constants.TEST_FOLDER + testFile, AthenaSchema.class);
    schema = normalizer.m2m(schema);

    System.out.println(serializer.serialize(schema));
  }*/
}
