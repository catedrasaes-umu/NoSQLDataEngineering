package es.um.unosql.xtext.athena.m2t

import java.io.File
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.SimpleAggregationTarget
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.VariationDecl
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.validation.m2t.CassandraValidator
import es.um.unosql.xtext.athena.utils.io.CodeWriter

class Athena2Cassandra
{
  val handler = new AthenaHandler()

  def void m2t(AthenaSchema schema, File outputFolder)
  {
    val nSchema = new AthenaNormalizer().m2m(schema)
    new CassandraValidator().validate(nSchema)

    val result = new StringBuilder()

    result.append(genHeader(nSchema))

    // First generate code for non-root entity types (aggregates) with the least aggregates on its own.
    for (EntityDecl e : nSchema.entities.filter[e | !e.root].sortBy[e | EcoreUtil2.getAllContentsOfType(e, SimpleAggregationTarget).size])
      result.append(generateUserType(e))

    for (EntityDecl e : nSchema.entities.filter[e | e.root])
      result.append(generateEntityDecl(e))

    new CodeWriter().writeToFile(outputFolder, nSchema.name + ".cql", result)
  }

  def genHeader(AthenaSchema schema)
  '''
    CREATE KEYSPACE IF NOT EXISTS «schema.name» WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 3 } ;
    USE «schema.name» ;

  '''

  def dispatch generateUserType(ShortEntityDecl entity)
  '''
    CREATE TYPE IF NOT EXISTS «entity.name»
    (
      «FOR feat : handler.getFeaturesFromSchemaType(entity) SEPARATOR ","»
        «generateFeature(feat, true)»
      «ENDFOR»
    );

  '''

  def dispatch generateUserType(RegularEntityDecl entity)
  '''
    CREATE TYPE IF NOT EXISTS «entity.name»
    (
      «FOR feat : handler.getFeaturesFromSchemaType(entity) + handler.getReducedFeaturesFromVariations(entity.variations) SEPARATOR ","»
        «generateFeature(feat, true)»
      «ENDFOR»
    );

  '''

  def dispatch generateEntityDecl(ShortEntityDecl entity)
  '''
    CREATE TABLE IF NOT EXISTS «entity.name»
    (
      «FOR feat : handler.getFeaturesFromSchemaType(entity)»
        «generateFeature(feat, false)»,
      «ENDFOR»
      PRIMARY KEY («handler.getFeaturesFromSchemaType(entity).filter(SimpleFeature).filter[f | f.key || f.unique].sortBy[f | !f.key].map[f | f.name].join(", ")»)
    );

  '''

  def dispatch generateEntityDecl(RegularEntityDecl entity)
  '''
    CREATE TABLE IF NOT EXISTS «entity.name»
    (
      «FOR feat : handler.getFeaturesFromSchemaType(entity) + handler.getReducedFeaturesFromVariations(entity.variations)»
        «generateFeature(feat, false)»,
      «ENDFOR»
      PRIMARY KEY («handler.getFeaturesFromSchemaType(entity).filter(SimpleFeature).filter[f | f.key || f.unique].sortBy[f | !f.key].map[f | f.name].join(", ")»)
    );

  '''

  def dispatch generateFeature(ComposedReference ref, boolean freeze)
  {
    // Ignore. Just to allow the dispatch method work properly.
  }

  def dispatch generateFeature(SimpleFeature feat, boolean freeze)
  '''«feat.name» «IF freeze && !(feat.type instanceof PrimitiveType || feat.type instanceof SimpleReferenceTarget)»frozen<«ENDIF»«generateType(feat.type)»«IF freeze && !(feat.type instanceof PrimitiveType || feat.type instanceof SimpleReferenceTarget)»>«ENDIF»'''

  private def dispatch CharSequence generateType(SimpleReferenceTarget type)
  '''«IF type.multiplicity.equals("*")»list<«ENDIF»«IF type.type !== null»«generateType(type.type)»«ELSE»«generateType((handler.getFeaturesFromSchemaType(type.ref).findFirst[f | f instanceof SimpleFeature && (f as SimpleFeature).key] as SimpleFeature).type)»«ENDIF»«IF type.multiplicity.equals("*")»>«ENDIF»'''

  private def dispatch CharSequence generateType(SimpleAggregationTarget type)
  '''«IF type.multiplicity.equals("*")»list<frozen<«ENDIF»«type.aggr.head instanceof EntityDecl ? (type.aggr.head as EntityDecl).name : ((type.aggr.head as VariationDecl).eContainer as EntityDecl).name»«IF type.multiplicity.equals("*")»>>«ENDIF»'''

  private def dispatch CharSequence generateType(List type)
  '''list<«IF !(type.elementType instanceof PrimitiveType)»frozen<«ENDIF»«generateType(type.elementType)»«IF !(type.elementType instanceof PrimitiveType)»>«ENDIF»>'''

  private def dispatch CharSequence generateType(Set type)
  '''set<«IF !(type.elementType instanceof PrimitiveType)»frozen<«ENDIF»«generateType(type.elementType)»«IF !(type.elementType instanceof PrimitiveType)»>«ENDIF»>'''

  private def dispatch CharSequence generateType(Map type)
  '''map<«generateType(type.keyType)», «IF !(type.valueType instanceof PrimitiveType)»frozen<«ENDIF»«generateType(type.valueType)»«IF !(type.valueType instanceof PrimitiveType)»>«ENDIF»>'''

  private def dispatch CharSequence generateType(Tuple type)
  '''tuple<«FOR DataType dt : type.elements SEPARATOR ","»«IF !(dt instanceof PrimitiveType)»frozen<«ENDIF»«generateType(dt)»«IF !(dt instanceof PrimitiveType)»>«ENDIF»«ENDFOR»>'''

  private def dispatch generateType(SinglePrimitiveType type)
  '''«athenaTypeToCassandraType(type.typename)»'''

  private def athenaTypeToCassandraType(String type)
  {
    switch (type.toLowerCase)
    {
      case "integer", case "int":  "int"
      case "number":               "varint"
      case "float":                "float"
      case "double":               "double"
      case "bool", case "boolean": "boolean"
      case "identifier":           "uuid"
      case "timestamp":            "timestamp"
      default:                     "text"
    }
  }
}
