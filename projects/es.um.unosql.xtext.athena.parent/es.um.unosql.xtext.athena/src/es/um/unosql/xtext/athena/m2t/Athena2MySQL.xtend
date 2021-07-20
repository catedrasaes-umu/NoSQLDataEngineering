package es.um.unosql.xtext.athena.m2t

import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.validation.m2t.MySQLValidator
import es.um.unosql.xtext.athena.athena.Feature
import java.util.Collection
import es.um.unosql.xtext.athena.athena.SchemaTypeDecl
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.RangedNumber
import es.um.unosql.xtext.athena.athena.RegexpRestrictedString
import es.um.unosql.xtext.athena.athena.EnumRestrictedNumber
import es.um.unosql.xtext.athena.athena.EnumRestrictedString
import es.um.unosql.xtext.athena.m2m.Athena2MySQLAthena
import es.um.unosql.xtext.athena.athena.OptionPrimitiveType

class Athena2MySQL
{
  val handler = new AthenaHandler()
  val factory = new AthenaFactory()

  def String m2t(AthenaSchema schema, boolean mysqlNormalizeRelations, boolean mysqlNormalizeCollections)
  {
    val nSchema = new Athena2MySQLAthena().m2m(new AthenaNormalizer().m2m(schema), mysqlNormalizeRelations, mysqlNormalizeCollections)

    val validator = new MySQLValidator()
    validator.validate(nSchema)
    validator.getSummary()

    val result = new StringBuilder()

    result.append(genHeader(nSchema))

    for (EntityDecl e : nSchema.entities.sortBy[e | e.root])
      result.append(generateEntityDecl(e, handler.getReducedFeaturesInSchemaTypeAndVariations(e)))

    return result.toString
  }

  def genHeader(AthenaSchema schema)
  '''
    CREATE SCHEMA «schema.id.name»;
    USE «schema.id.name»;

  '''

  def generateEntityDecl(EntityDecl entity, Collection<Feature> features)
  '''
    CREATE TABLE «entity.name»
    (
      «FOR feat : features.filter(SimpleFeature)»
        «generateFeature(feat)»,
      «ENDFOR»
      «IF !features.filter(SimpleFeature).exists[f | f.key]»
        «generateFeature(factory.createSimpleFeature("id", factory.createUnrestrictedPrimitiveType("INTEGER")))»,
      «ENDIF»
      «FOR feat : features.filter(SimpleFeature).filter[f | f.unique]»
        UNIQUE KEY ( «feat.name» ),
      «ENDFOR»
      «FOR feat : features.filter(ComposedReference)»
        FOREIGN KEY ( «feat.names.join(", ")» ) REFERENCES «feat.target.ref.name» ( «FOR feat2 : handler.getFeaturesInSchemaType(feat.target.ref).filter(SimpleFeature).filter[f | f.key] SEPARATOR ", "»«feat2.name»«ENDFOR» ),
      «ENDFOR»
      «FOR feat : features.filter(SimpleFeature).filter[f | f.type instanceof SimpleReferenceTarget]»
        FOREIGN KEY ( «feat.name» ) REFERENCES «(feat.type as SimpleReferenceTarget).ref.name» ( «getFirstSQLKeyFeature((feat.type as SimpleReferenceTarget).ref).name» ),
      «ENDFOR»
      «FOR feat : features.filter(SimpleFeature).filter[f | f.type instanceof SimpleAggregateTarget]»
        FOREIGN KEY ( «feat.name» ) REFERENCES «(feat.type as SimpleAggregateTarget).aggr.head instanceof EntityDecl ? ((feat.type as SimpleAggregateTarget).aggr.head as EntityDecl).name : ((feat.type as SimpleAggregateTarget).aggr.head.eContainer as EntityDecl).name» ( «getFirstSQLKeyFeature((feat.type as SimpleAggregateTarget).aggr.head instanceof EntityDecl ? (feat.type as SimpleAggregateTarget).aggr.head as EntityDecl : (feat.type as SimpleAggregateTarget).aggr.head.eContainer as EntityDecl).name» ),
      «ENDFOR»
      PRIMARY KEY ( «IF !features.filter(SimpleFeature).exists[f | f.key]»id«ELSE»«FOR feat : features.filter(SimpleFeature).filter[f | f.key] SEPARATOR ", "»«feat.name»«ENDFOR»«ENDIF» )
    );

  '''

  def dispatch generateFeature(ComposedReference ref)
  {
    // Ignore
  }

  def dispatch generateFeature(SimpleFeature feat)
  '''«feat.name» «generateType(feat.type)»«IF !feat.optional && !belongsToVariation(feat)» NOT NULL«ENDIF»'''
  // The reference is generated later on as a foreign key.
  private def dispatch CharSequence generateType(SimpleReferenceTarget type)
  '''«generateType(type.type)»'''

  private def dispatch CharSequence generateType(SimpleAggregateTarget type)
  '''«generateType(getFirstSQLKeyFeature(type.aggr.head instanceof EntityDecl ? type.aggr.head as EntityDecl : type.aggr.head.eContainer as EntityDecl).type)»''' // The aggregate was already generated and the foreign key will be generated later on.

  // Lists might have been translated to m..n relationships beforehand.
  // If there is a List type at this point, we encode it as a String.
  private def dispatch CharSequence generateType(List type)
  '''JSON'''

  // Set might have been translated to m..n relationships beforehand.
  // If there is a List type at this point, we encode it as a String.
  private def dispatch CharSequence generateType(Set type)
  '''JSON'''

  // Map might have been translated to m..n relationships beforehand.
  // If there is a List type at this point, we encode it as a String.
  private def dispatch CharSequence generateType(Map type)
  '''JSON'''

  // Tuples might have been translated to m..n relationships beforehand.
  // If there is a List type at this point, we encode it as a String.
  private def dispatch CharSequence generateType(Tuple type)
  '''JSON'''

  private def dispatch CharSequence generateType(OptionPrimitiveType type)
  '''«generateType(handler.getMostSuitableType(type.options))»'''

  private def dispatch CharSequence generateType(SinglePrimitiveType type)
  '''«athenaTypeToMySQLType(type.typename)»'''

  private def dispatch CharSequence generateType(RangedNumber type)
  '''«athenaTypeToMySQLType(type.typename)» CHECK ( «(type.eContainer as SimpleFeature).name» BETWEEN «type.from» AND «type.to» )'''

  private def dispatch CharSequence generateType(RegexpRestrictedString type)
  '''«athenaTypeToMySQLType(type.typename)» CHECK ( «(type.eContainer as SimpleFeature).name» LIKE '«type.regexp»' )'''

  private def dispatch CharSequence generateType(EnumRestrictedNumber type)
  '''ENUM ( «FOR value : type.enumVals SEPARATOR ", "»'«value»'«ENDFOR» )'''

  private def dispatch CharSequence generateType(EnumRestrictedString type)
  '''ENUM ( «FOR value : type.enumVals SEPARATOR ", "»'«value»'«ENDFOR» )'''

  private def getFirstSQLKeyFeature(SchemaTypeDecl schemaType)
  {
    val result = handler.getKeyInSchemaType(schemaType)

    if (result === null)
      return factory.createSimpleFeature("id", factory.createUnrestrictedPrimitiveType("INTEGER"))

    return result
  }

  private def belongsToVariation(SimpleFeature feat)
  {
    return feat.eContainer !== null && feat.eContainer.eContainer.eContainer instanceof VariationDecl
  }

  private def athenaTypeToMySQLType(String type)
  {
    switch (type.toLowerCase)
    {
      case "integer", case "int":  "INTEGER"
      case "number":               "NUMERIC"
      case "float":                "FLOAT"
      case "double":               "DOUBLE"
      case "bool", case "boolean": "BOOLEAN"
      case "identifier":           "VARCHAR(255)"
      case "timestamp":            "TIMESTAMP"
      default:                     "VARCHAR(255)"
    }
  }
}
