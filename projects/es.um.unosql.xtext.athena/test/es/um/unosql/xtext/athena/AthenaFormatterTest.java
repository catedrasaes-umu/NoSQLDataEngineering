package es.um.unosql.xtext.athena;

import static org.junit.Assert.*;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.athena.FeatureSetDecl;
import es.um.unosql.xtext.athena.athena.RegularEntityDecl;
import es.um.unosql.xtext.athena.athena.SQLDefinition;
import es.um.unosql.xtext.athena.athena.ShortEntityDecl;
import es.um.unosql.xtext.athena.athena.VariationDecl;
import es.um.unosql.xtext.athena.utils.AthenaFactory;
import es.um.unosql.xtext.athena.utils.io.ModelSerializer;

public class AthenaFormatterTest
{
  private AthenaFactory factory;
  private ModelSerializer serializer;

  @Before
  public void setUp()
  {
    factory = new AthenaFactory();
    serializer = new ModelSerializer();
  }

  @Test
  public void testAthenaFormatter()
  {
    AthenaSchema schema = factory.createAthenaSchema("AthenaFormatterTest");

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

    assertEquals("Schema AthenaFormatterTest\r\n" + 
        "\r\n" + 
        "fset commons\r\n" + 
        "{\r\n" + 
        "  +_id: String,\r\n" + 
        "  name: String\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "entity ShortEmbeddedEntity\r\n" + 
        "{\r\n" + 
        "  attr1: Number,\r\n" + 
        "  attr2: Timestamp\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "entity RegularEmbeddedEntity1\r\n" + 
        "{\r\n" + 
        "  common\r\n" + 
        "  {\r\n" + 
        "    attr1: String,\r\n" + 
        "    attr2: Number,\r\n" + 
        "    attr3: Boolean\r\n" + 
        "  }\r\n" + 
        "  variation 1\r\n" + 
        "  {\r\n" + 
        "    attr4: List<String>\r\n" + 
        "  }\r\n" + 
        "  variation 2\r\n" + 
        "  {\r\n" + 
        "    attr5: String,\r\n" + 
        "    attr6: String\r\n" + 
        "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "entity RegularEmbeddedEntity2\r\n" + 
        "{\r\n" + 
        "  common\r\n" + 
        "  {\r\n" + 
        "    attr1: String\r\n" + 
        "  }\r\n" + 
        "  variation 1\r\n" + 
        "  {\r\n" + 
        "    attr2: Number,\r\n" + 
        "    attr3: aggr<ShortEmbeddedEntity>+\r\n" + 
        "  }\r\n" + 
        "  variation 2\r\n" + 
        "  {\r\n" + 
        "    attr2: String\r\n" + 
        "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "root entity RegularRootEntity1\r\n" + 
        "{\r\n" + 
        "  common\r\n" + 
        "  {\r\n" + 
        "    attr1: String,\r\n" + 
        "    attr2: List<String>,\r\n" + 
        "    attr3: Number,\r\n" + 
        "    attr4: Double,\r\n" + 
        "    attr5: List\r\n" + 
        "  }\r\n" + 
        "  variation 1\r\n" + 
        "  {\r\n" + 
        "    ratings: aggr<ShortEmbeddedEntity>+,\r\n" + 
        "    prizes: aggr<RegularEmbeddedEntity1.1>*,\r\n" + 
        "    reviews: aggr<RegularEmbeddedEntity2.1>*\r\n" + 
        "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "root entity RegularRootEntity2\r\n" + 
        "{\r\n" + 
        "  common\r\n" + 
        "  {\r\n" + 
        "    ref1: ref<RegularRootEntity1>*\r\n" + 
        "  }\r\n" + 
        "  variation 1\r\n" + 
        "  {\r\n" + 
        "    ref2: ref<RegularRootEntity1>*\r\n" + 
        "  }\r\n" + 
        "}\r\n", serializer.serialize(schema));
  }

  @Test
  public void testSQLSakilaFormatter()
  {
    AthenaSchema schema = factory.createAthenaSchema("SQLExample_Sakila");

    BasicEList<String> keyList = new BasicEList<String>();
    BasicEList<String> refList = new BasicEList<String>();

    EList<SQLDefinition> definitions = new BasicEList<SQLDefinition>();
    keyList.add("actor_id");
    definitions.add(factory.createSQLColumnDefinition("actor_id", factory.createSQLType("SMALLINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("first_name", factory.createSQLType("VARCHAR"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("last_name", factory.createSQLType("VARCHAR"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("last_update", factory.createSQLType("TIMESTAMP"), "NOT NULL", null));
    definitions.add(factory.createSQLPrimaryConstraintDefinition(null, keyList));
    ShortEntityDecl entity1 = factory.createShortEntityDecl("Actor", true);
    entity1.setStructure(factory.createSQLStructure("actor", definitions));
    schema.getEntities().add(entity1);

    definitions.clear();
    keyList.clear();
    refList.clear();
    keyList.add("language_id");
    definitions.add(factory.createSQLColumnDefinition("language_id", factory.createSQLType("TINYINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("name", factory.createSQLType("CHAR"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("last_update", factory.createSQLType("TIMESTAMP"), "NOT NULL", null));
    definitions.add(factory.createSQLPrimaryConstraintDefinition(null, keyList));
    ShortEntityDecl entity2 = factory.createShortEntityDecl("Language", true);
    entity2.setStructure(factory.createSQLStructure("language", definitions));
    schema.getEntities().add(entity2);

    definitions.clear();
    keyList.clear();
    refList.clear();
    keyList.add("film_id");
    definitions.add(factory.createSQLColumnDefinition("film_id", factory.createSQLType("SMALLINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("title", factory.createSQLType("VARCHAR"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("description", factory.createSQLType("TEXT")));
    definitions.add(factory.createSQLColumnDefinition("release_year", factory.createSQLType("YEAR")));
    definitions.add(factory.createSQLColumnDefinition("language_id", factory.createSQLType("TINYINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("original_language_id", factory.createSQLType("TINYINT")));
    definitions.add(factory.createSQLColumnDefinition("rental_duration", factory.createSQLType("TINYINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("rental_rate", factory.createSQLType("DECIMAL"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("length", factory.createSQLType("SMALLINT")));
    definitions.add(factory.createSQLColumnDefinition("replacement_cost", factory.createSQLType("DECIMAL"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("rating", factory.createSQLType("TEXT")));
    definitions.add(factory.createSQLColumnDefinition("special_features", factory.createSQLType("TEXT")));
    definitions.add(factory.createSQLColumnDefinition("last_update", factory.createSQLType("TIMESTAMP"), "NOT NULL", null));
    definitions.add(factory.createSQLPrimaryConstraintDefinition(null, keyList));
    keyList.clear(); keyList.add("language_id");
    refList.add("language_id");
    definitions.add(factory.createSQLForeignConstraintDefinition("fk_film_language", keyList, factory.createSQLReferenceTarget(entity2, refList)));
    keyList.clear(); keyList.add("original_language_id");
    definitions.add(factory.createSQLForeignConstraintDefinition("fk_film_language_original", keyList, factory.createSQLReferenceTarget(entity2, refList)));

    ShortEntityDecl entity3 = factory.createShortEntityDecl("Film", true);
    entity3.setStructure(factory.createSQLStructure("film", definitions));
    schema.getEntities().add(entity3);

    definitions.clear();
    keyList.clear();
    refList.clear();
    keyList.add("actor_id"); keyList.add("film_id");
    definitions.add(factory.createSQLColumnDefinition("actor_id", factory.createSQLType("SMALLINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("film_id", factory.createSQLType("SMALLINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("last_update", factory.createSQLType("TIMESTAMP"), "NOT NULL", null));
    definitions.add(factory.createSQLPrimaryConstraintDefinition(null, keyList));
    keyList.clear(); keyList.add("actor_id");
    refList.add("actor_id");
    definitions.add(factory.createSQLForeignConstraintDefinition("fk_film_actor_actor", keyList, factory.createSQLReferenceTarget(entity1, refList)));
    keyList.clear(); keyList.add("film_id");
    refList.clear(); refList.add("film_id");
    definitions.add(factory.createSQLForeignConstraintDefinition("fk_film_actor_film", keyList, factory.createSQLReferenceTarget(entity3, refList)));
    ShortEntityDecl entity4 = factory.createShortEntityDecl("Film_actor", true);
    entity4.setStructure(factory.createSQLStructure("film_actor", definitions));
    schema.getEntities().add(entity4);


    definitions.clear();
    keyList.clear();
    refList.clear();
    keyList.add("store_id");
    definitions.add(factory.createSQLColumnDefinition("store_id", factory.createSQLType("TINYINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("manager_staff_id", factory.createSQLType("TINYINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("address_id", factory.createSQLType("SMALLINT"), "NOT NULL", null));
    definitions.add(factory.createSQLColumnDefinition("last_update", factory.createSQLType("TIMESTAMP"), "NOT NULL", null));
    definitions.add(factory.createSQLPrimaryConstraintDefinition(null, keyList));
    keyList.clear(); keyList.add("manager_staff_id");
    definitions.add(factory.createSQLUniqueConstraintDefinition("idx_unique_manager", keyList));
    ShortEntityDecl entity5 = factory.createShortEntityDecl("Store", true);
    entity5.setStructure(factory.createSQLStructure("store", definitions));
    schema.getEntities().add(entity5);

    assertEquals("Schema SQLExample_Sakila\r\n" + 
        "\r\n" + 
        "root entity Actor\r\n" + 
        "  SQL CREATE TABLE actor (\r\n" + 
        "    actor_id SMALLINT NOT NULL,\r\n" + 
        "    first_name VARCHAR NOT NULL,\r\n" + 
        "    last_name VARCHAR NOT NULL,\r\n" + 
        "    last_update TIMESTAMP NOT NULL,\r\n" + 
        "    PRIMARY KEY (actor_id)\r\n" + 
        "  );\r\n" + 
        "\r\n" + 
        "root entity Language\r\n" + 
        "  SQL CREATE TABLE language (\r\n" + 
        "    language_id TINYINT NOT NULL,\r\n" + 
        "    name CHAR NOT NULL,\r\n" + 
        "    last_update TIMESTAMP NOT NULL,\r\n" + 
        "    PRIMARY KEY (language_id)\r\n" + 
        "  );\r\n" + 
        "\r\n" + 
        "root entity Film\r\n" + 
        "  SQL CREATE TABLE film (\r\n" + 
        "    film_id SMALLINT NOT NULL,\r\n" + 
        "    title VARCHAR NOT NULL,\r\n" + 
        "    description TEXT,\r\n" + 
        "    release_year YEAR,\r\n" + 
        "    language_id TINYINT NOT NULL,\r\n" + 
        "    original_language_id TINYINT,\r\n" + 
        "    rental_duration TINYINT NOT NULL,\r\n" + 
        "    rental_rate DECIMAL NOT NULL,\r\n" + 
        "    length SMALLINT,\r\n" + 
        "    replacement_cost DECIMAL NOT NULL,\r\n" + 
        "    rating TEXT,\r\n" + 
        "    special_features TEXT,\r\n" + 
        "    last_update TIMESTAMP NOT NULL,\r\n" + 
        "    PRIMARY KEY (film_id),\r\n" + 
        "    CONSTRAINT fk_film_language FOREIGN KEY (language_id) REFERENCES Language (language_id),\r\n" + 
        "    CONSTRAINT fk_film_language_original FOREIGN KEY (original_language_id) REFERENCES Language (language_id)\r\n" + 
        "  );\r\n" + 
        "\r\n" + 
        "root entity Film_actor\r\n" + 
        "  SQL CREATE TABLE film_actor (\r\n" + 
        "    actor_id SMALLINT NOT NULL,\r\n" + 
        "    film_id SMALLINT NOT NULL,\r\n" + 
        "    last_update TIMESTAMP NOT NULL,\r\n" + 
        "    PRIMARY KEY (actor_id, film_id),\r\n" + 
        "    CONSTRAINT fk_film_actor_actor FOREIGN KEY (actor_id) REFERENCES Actor (actor_id),\r\n" + 
        "    CONSTRAINT fk_film_actor_film FOREIGN KEY (film_id) REFERENCES Film (film_id)\r\n" + 
        "  );\r\n" + 
        "\r\n" + 
        "root entity Store\r\n" + 
        "  SQL CREATE TABLE store (\r\n" + 
        "    store_id TINYINT NOT NULL,\r\n" + 
        "    manager_staff_id TINYINT NOT NULL,\r\n" + 
        "    address_id SMALLINT NOT NULL,\r\n" + 
        "    last_update TIMESTAMP NOT NULL,\r\n" + 
        "    PRIMARY KEY (store_id),\r\n" + 
        "    CONSTRAINT idx_unique_manager UNIQUE KEY (manager_staff_id)\r\n" + 
        "  );\r\n", serializer.serialize(schema));
  }
}
