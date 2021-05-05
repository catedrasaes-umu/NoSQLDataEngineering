package es.um.unosql.xtext.athena;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.athena.FeatureSetDecl;
import es.um.unosql.xtext.athena.athena.RegularEntityDecl;
import es.um.unosql.xtext.athena.athena.ShortEntityDecl;
import es.um.unosql.xtext.athena.athena.VariationDecl;
import es.um.unosql.xtext.athena.utils.AthenaFactory;
import es.um.unosql.xtext.athena.utils.Constants;
import es.um.unosql.xtext.athena.utils.io.ModelLoader;
import es.um.unosql.xtext.athena.utils.io.ModelSerializer;

public class ModelSerializerTest
{
  private AthenaFactory factory;
  private ModelSerializer serializer;
  private String testFile;

  @Before
  public void setUp()
  {
    factory = new AthenaFactory();
    serializer = new ModelSerializer();
    testFile = "SerializerTest.athena";
  }

  @Test
  public void testStringModelSerializer()
  {
    AthenaSchema schema = factory.createAthenaSchema("ModelSerializerTest");

    FeatureSetDecl commons = factory.createFeatureSetDecl("commons", factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("_id", factory.createUnrestrictedPrimitiveType("String")),
        factory.createSimpleFeature("name", factory.createUnrestrictedPrimitiveType("String")))));
    schema.getFeatureSets().add(commons);

    ShortEntityDecl shortEntity = factory.createShortEntityDecl("ShortEmbeddedEntity", false); schema.getEntities().add(shortEntity);
    shortEntity.setStructure(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr1", factory.createUnrestrictedPrimitiveType("Number")),
        factory.createSimpleFeature("attr2", factory.createUnrestrictedPrimitiveType("Timestamp")))));

    RegularEntityDecl regularEntity1 = factory.createRegularEntityDecl("RegularEmbeddedEntity1", false); schema.getEntities().add(regularEntity1);
    regularEntity1.setCommon(factory.createCommonSpec(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr1", factory.createUnrestrictedPrimitiveType("String")),
        factory.createSimpleFeature("attr2", factory.createUnrestrictedPrimitiveType("Number")),
        factory.createSimpleFeature("attr3", factory.createUnrestrictedPrimitiveType("Boolean"))))));

    VariationDecl var = factory.createVariationDecl(1);
    regularEntity1.getVariations().add(var);
    var.setStructure(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr4", factory.createList(factory.createUnrestrictedPrimitiveType("String"))))));

    var = factory.createVariationDecl(2);
    regularEntity1.getVariations().add(var);
    var.setStructure(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr5", factory.createUnrestrictedPrimitiveType("String")),
        factory.createSimpleFeature("attr6", factory.createUnrestrictedPrimitiveType("String")))));

    RegularEntityDecl regularEntity2 = factory.createRegularEntityDecl("RegularEmbeddedEntity2", false); schema.getEntities().add(regularEntity2);
    regularEntity2.setCommon(factory.createCommonSpec(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr1", factory.createUnrestrictedPrimitiveType("String"))))));

    var = factory.createVariationDecl(1);
    regularEntity2.getVariations().add(var);
    var.setStructure(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr2", factory.createUnrestrictedPrimitiveType("Number")),
        factory.createSimpleFeature("attr3", factory.createSimpleAggr(shortEntity, "+")))));

    var = factory.createVariationDecl(2);
    regularEntity2.getVariations().add(var);
    var.setStructure(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr2", factory.createUnrestrictedPrimitiveType("String")))));

    RegularEntityDecl regularRootEntity1 = factory.createRegularEntityDecl("RegularRootEntity1", true); schema.getEntities().add(regularRootEntity1);
    regularRootEntity1.setCommon(factory.createCommonSpec(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr1", factory.createUnrestrictedPrimitiveType("String")),
        factory.createSimpleFeature("attr2", factory.createList(factory.createUnrestrictedPrimitiveType("String"))),
        factory.createSimpleFeature("attr3", factory.createUnrestrictedPrimitiveType("Number")),
        factory.createSimpleFeature("attr4", factory.createUnrestrictedPrimitiveType("Double")),
        factory.createSimpleFeature("attr5", factory.createList())))));

    var = factory.createVariationDecl(1);
    var.setStructure(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("ratings", factory.createSimpleAggr(shortEntity, "+")),
        factory.createSimpleFeature("prizes", factory.createSimpleAggr(regularEntity1.getVariations().get(0), "*")),
        factory.createSimpleFeature("reviews", factory.createSimpleAggr(regularEntity2.getVariations().get(0), "*")))));
    regularRootEntity1.getVariations().add(var);

    RegularEntityDecl regularRootEntity2 = factory.createRegularEntityDecl("RegularRootEntity2", true); schema.getEntities().add(regularRootEntity2);
    regularRootEntity2.setCommon(factory.createCommonSpec(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("attr1", factory.createUnrestrictedPrimitiveType("String")),
        factory.createSimpleFeature("attr2", factory.createUnrestrictedPrimitiveType("Double"))))));

    regularRootEntity2.getCommon().setStructure(factory.createStructureLiteral(factory.createFeatureSet(
        factory.createSimpleFeature("ref1", factory.createSimpleRef(regularRootEntity1, "*")))));
    var = factory.createVariationDecl(1);
    var.setStructure(factory.createStructureLiteral(factory.createFeatureSet(factory.createSimpleFeature("ref2", factory.createSimpleRef(regularRootEntity1, "*")))));
    regularRootEntity2.getVariations().add(var);

    assertEquals("Schema ModelSerializerTest\r\n" + 
        "\r\n" + 
        "fset commons\r\n"
        + "{\r\n"
        + "  +_id: String,\r\n"
        + "  name: String\r\n"
        + "}\r\n" + 
        "\r\n" + 
        "entity ShortEmbeddedEntity\r\n"
        + "{\r\n"
        + "  attr1: Number,\r\n"
        + "  attr2: Timestamp\r\n"
        + "}\r\n" + 
        "\r\n" + 
        "entity RegularEmbeddedEntity1\r\n" + 
        "{\r\n" + 
        "  common\r\n"
        + "  {\r\n"
        + "    attr1: String,\r\n"
        + "    attr2: Number,\r\n"
        + "    attr3: Boolean\r\n"
        + "  }\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    attr4: List<String>\r\n"
        + "  }\r\n" + 
        "  variation 2\r\n"
        + "  {\r\n"
        + "    attr5: String,\r\n"
        + "    attr6: String\r\n"
        + "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "entity RegularEmbeddedEntity2\r\n" + 
        "{\r\n" + 
        "  common\r\n"
        + "  {\r\n"
        + "    attr1: String\r\n"
        + "  }\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    attr2: Number,\r\n"
        + "    attr3: aggr<ShortEmbeddedEntity>+\r\n"
        + "  }\r\n" + 
        "  variation 2\r\n"
        + "  {\r\n"
        + "    attr2: String\r\n"
        + "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "root entity RegularRootEntity1\r\n" + 
        "{\r\n" + 
        "  common\r\n"
        + "  {\r\n"
        + "    attr1: String,\r\n"
        + "    attr2: List<String>,\r\n"
        + "    attr3: Number,\r\n"
        + "    attr4: Double,\r\n"
        + "    attr5: List\r\n"
        + "  }\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    ratings: aggr<ShortEmbeddedEntity>+,\r\n"
        + "    prizes: aggr<RegularEmbeddedEntity1.1>*,\r\n"
        + "    reviews: aggr<RegularEmbeddedEntity2.1>*\r\n"
        + "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "root entity RegularRootEntity2\r\n" + 
        "{\r\n" + 
        "  common\r\n"
        + "  {\r\n"
        + "    ref1: ref<RegularRootEntity1>*\r\n"
        + "  }\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    ref2: ref<RegularRootEntity1>*\r\n"
        + "  }\r\n" + 
        "}\r\n", serializer.serialize(schema));
  }

  @Test
  public void testFileModelSerializer()
  {
    AthenaSchema schema = new ModelLoader().load(Constants.TEST_FOLDER + testFile);

    assertEquals("schema HelloWorld\n" + 
        "\n" + 
        "entity Hello\n" + 
        "{\n" + 
        "  common\n" + 
        "  {\n" + 
        "    +_id: String,\n" + 
        "    hello: String,\n" + 
        "    world: Number\n" + 
        "  }\n" + 
        "\n" + 
        "  Variation 1\n" + 
        "  {\n" + 
        "    in_the: Boolean,\n" + 
        "    morning: List<String>\n" + 
        "  }\n" + 
        "}\n", serializer.serialize(schema));
  }
}
