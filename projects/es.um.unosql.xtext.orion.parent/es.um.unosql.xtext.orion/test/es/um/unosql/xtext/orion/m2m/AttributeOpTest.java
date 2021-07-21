package es.um.unosql.xtext.orion.m2m;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.utils.io.AthenaIO;
import es.um.unosql.xtext.orion.orion.OrionOperations;
import es.um.unosql.xtext.orion.utils.io.OrionIO;

class AttributeOpTest
{
  private Path testPath_1 = Path.of("models/tests/attribute_ops/ImportAttribute_Ops.orion");
  private Path testPath_2 = Path.of("models/tests/attribute_ops/ScriptAttribute_Ops.orion");
  private OrionIO orionIO          = new OrionIO();
  private AthenaIO athenaIO        = new AthenaIO();

  @Test
  void testAttributeOperations_Import()
  {
    OrionOperations orion = orionIO.load(testPath_1);
    AthenaSchema schema = new Orion2Athena().m2m(orion, false).get(0);

    assertEquals("Schema AttributeSchema:2\r\n"
        + "\r\n"
        + "root entity EntityAddAttribute_1\r\n"
        + "{\r\n"
        + "  +id: Identifier,\r\n"
        + "  attr1: String,\r\n"
        + "  attr2: List<Option < Bool , String >>,\r\n"
        + "  attr3\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityAddAttribute_2\r\n"
        + "{\r\n"
        + "  common\r\n"
        + "  {\r\n"
        + "    +id: Identifier,\r\n"
        + "    attr33\r\n"
        + "  }\r\n"
        + "  variation 1\r\n"
        + "  {\r\n"
        + "    attr1,\r\n"
        + "    attr22: List<Option < Bool , String >>\r\n"
        + "  }\r\n"
        + "  variation 2\r\n"
        + "  {\r\n"
        + "    attr2,\r\n"
        + "    attr11: String\r\n"
        + "  }\r\n"
        + "  variation 3\r\n"
        + "  {\r\n"
        + "    attr3,\r\n"
        + "    attr11: String\r\n"
        + "  }\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityCastAttribute_1\r\n"
        + "{\r\n"
        + "  +id: Identifier,\r\n"
        + "  attr1: String,\r\n"
        + "  attr2: String,\r\n"
        + "  attr3: String,\r\n"
        + "  attr4: String,\r\n"
        + "  attr5: Number,\r\n"
        + "  attr6: Number\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityCastAttribute_2\r\n"
        + "{\r\n"
        + "  common\r\n"
        + "  {\r\n"
        + "    +id: Identifier\r\n"
        + "  }\r\n"
        + "  variation 1\r\n"
        + "  {\r\n"
        + "    attr1: Timestamp\r\n"
        + "  }\r\n"
        + "  variation 2\r\n"
        + "  {\r\n"
        + "    attr2: String\r\n"
        + "  }\r\n"
        + "  variation 3\r\n"
        + "  {\r\n"
        + "    attr3: String\r\n"
        + "  }\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityToPromote_1\r\n"
        + "{\r\n"
        + "  +id: Identifier,\r\n"
        + "  +key1,\r\n"
        + "  +key2,\r\n"
        + "  +key3\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityToPromote_2\r\n"
        + "{\r\n"
        + "  common\r\n"
        + "  {\r\n"
        + "    +id: Identifier\r\n"
        + "  }\r\n"
        + "  variation 1\r\n"
        + "  {\r\n"
        + "    +key1\r\n"
        + "  }\r\n"
        + "  variation 2\r\n"
        + "  {\r\n"
        + "    +key2\r\n"
        + "  }\r\n"
        + "  variation 3\r\n"
        + "  {\r\n"
        + "    +key3\r\n"
        + "  }\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityToDemote_1\r\n"
        + "{\r\n"
        + "  +id: Identifier,\r\n"
        + "  key1,\r\n"
        + "  key2,\r\n"
        + "  key3\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityToDemote_2\r\n"
        + "{\r\n"
        + "  common\r\n"
        + "  {\r\n"
        + "    +id: Identifier\r\n"
        + "  }\r\n"
        + "  variation 1\r\n"
        + "  {\r\n"
        + "    key1\r\n"
        + "  }\r\n"
        + "  variation 2\r\n"
        + "  {\r\n"
        + "    key2\r\n"
        + "  }\r\n"
        + "  variation 3\r\n"
        + "  {\r\n"
        + "    +key2,\r\n"
        + "    key3\r\n"
        + "  }\r\n"
        + "}\r\n", athenaIO.serialize(schema));
  }

  @Test
  void testAttributeOperations_Script()
  {
    OrionOperations orion = orionIO.load(testPath_2);
    AthenaSchema schema = new Orion2Athena().m2m(orion).get(0);

    assertEquals("Schema OneOfEach_Attributes:1\r\n"
        + "\r\n"
        + "root entity EntityToAdd\r\n"
        + "{\r\n"
        + "  +id: String,\r\n"
        + "  addAttrSinTipo,\r\n"
        + "  addAttrStr: String,\r\n"
        + "  addAttrNum: Number,\r\n"
        + "  addAttrDouble: Double,\r\n"
        + "  addAttrBool: Boolean,\r\n"
        + "  addAttrSet: Set,\r\n"
        + "  addAttrList: List,\r\n"
        + "  addAttrTuple: Tuple,\r\n"
        + "  addAttrMap: Map,\r\n"
        + "  addAttrNull: Null,\r\n"
        + "  addAttrStrVal: String,\r\n"
        + "  addAttrNumVal: Number,\r\n"
        + "  addAttrDoubleVal: Double,\r\n"
        + "  addAttrBoolVal: Boolean\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityToCast\r\n"
        + "{\r\n"
        + "  +id: Identifier,\r\n"
        + "  +attrToCast: Bool,\r\n"
        + "  attrToCastNull: Number,\r\n"
        + "  attrToCastStr: Bool,\r\n"
        + "  attrToCastNum: String,\r\n"
        + "  attrToCastDouble: Bool,\r\n"
        + "  attrToCastBool: String\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityToPromote\r\n"
        + "{\r\n"
        + "  +id: Identifier,\r\n"
        + "  +attr1,\r\n"
        + "  +attr2\r\n"
        + "}\r\n"
        + "\r\n"
        + "root entity EntityToDemote\r\n"
        + "{\r\n"
        + "  +id: Identifier,\r\n"
        + "  key1,\r\n"
        + "  key2\r\n"
        + "}\r\n", athenaIO.serialize(schema));
  }
}
