package es.um.unosql.xtext.athena.m2t

import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.athena.RangedNumber
import es.um.unosql.xtext.athena.athena.RegexpRestrictedString
import es.um.unosql.xtext.athena.athena.EnumRestrictedNumber
import es.um.unosql.xtext.athena.athena.EnumRestrictedString
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.OptionPrimitiveType
import es.um.unosql.xtext.athena.validation.m2t.MongoDBValidator

class Athena2MongoDBSchemaValidator
{
  val handler = new AthenaHandler()
  String dbName = null

  def String m2t(AthenaSchema schema)
  {
    val nSchema = new AthenaNormalizer().m2m(schema)
    new MongoDBValidator().validate(nSchema)
    dbName = nSchema.id.name

    val result = new StringBuilder()

    result.append(genHeader())

    for (EntityDecl e : nSchema.entities.filter[e | e.root])
      result.append(generateEntityDecl(e))

    return result.toString
  }

  def genHeader()
  '''
    «dbName» = db.getSiblingDB("«dbName»")

  '''

  def dispatch generateEntityDecl(ShortEntityDecl entity)
  '''
  «dbName».createCollection("«entity.name»",
  {
    validator:
    {
      $jsonSchema:
      {
        bsonType: "object",
        «IF !handler.getFeaturesInSchemaType(entity).empty»required: [ «FOR feat : handler.getFeaturesInSchemaType(entity).filter(SimpleFeature).filter[f | !f.optional] SEPARATOR ", "»"«feat.name»"«ENDFOR» ],«ENDIF»
        properties:
        {
          «FOR feat : handler.getFeaturesInSchemaType(entity) SEPARATOR ", "»
            «generateFeature(feat)»
          «ENDFOR»
        }
      }
    }
  });

  '''

  def dispatch generateEntityDecl(RegularEntityDecl entity)
  '''
  «dbName».createCollection("«entity.name»",
  {
    validator:
    {
      bsonType: "object",
      «IF !handler.getFeaturesInSchemaType(entity).empty»required: [ «FOR feat : handler.getFeaturesInSchemaType(entity).filter(SimpleFeature) SEPARATOR ", "»"«feat.name»"«ENDFOR» ],«ENDIF»
      properties:
      {
        «FOR feat : handler.getReducedFeaturesInSchemaTypeAndVariations(entity) SEPARATOR ", "»
          «generateFeature(feat)»
        «ENDFOR»
      }
    }
  });

  '''

  def dispatch generateFeature(ComposedReference ref)
  {
    // Ignore. Just to allow the dispatch method work properly.
  }

  def dispatch generateFeature(SimpleFeature feat)
  '''
  «feat.name»:
  {
    «generateType(feat.type, feat.name, feat.isOptional)»
  }
  '''

  private def dispatch CharSequence generateType(SimpleReferenceTarget type, String name, boolean optional)
  '''
    bsonType: "«IF type.multiplicity.equals("*") || type.multiplicity.equals("+")»array«ELSE»«athenaTypeToMongoDBType(type.type)»«ENDIF»",
    «IF type.multiplicity.equals("*") || type.multiplicity.equals("+")»items: { bsonType: "«athenaTypeToMongoDBType(type.type)»" } ,«ENDIF»
    description: "Field «name» must be of type «IF type.multiplicity.equals("*") || type.multiplicity.equals("+")»array«ELSE»«athenaTypeToMongoDBType(type.type)»«ENDIF»«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch CharSequence generateType(SimpleAggregateTarget type, String name, boolean optional)
  '''
    «val aggregatedFeatures = handler.getFeaturesInAggregate(type.aggr).filter(SimpleFeature)»
    bsonType: "«IF type.multiplicity.equals("*") || type.multiplicity.equals("+")»array«ELSE»object«ENDIF»",
    «IF type.multiplicity.equals("*") || type.multiplicity.equals("+")»items: { bsonType: "object" } ,«ENDIF»
    «IF aggregatedFeatures.exists[f | !f.isOptional]»required: [ «FOR feat : aggregatedFeatures.filter[f | !f.isOptional] SEPARATOR ", "»"«feat.name»"«ENDFOR» ],«ENDIF»
    properties:
    {
      «FOR feat : aggregatedFeatures SEPARATOR ", "»
        «generateFeature(feat)»
      «ENDFOR»
    },
    description: "Field «name» must be of type «IF type.multiplicity.equals("*") || type.multiplicity.equals("+")»array«ELSE»object«ENDIF»«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch generateType(List type, String name, boolean optional)
  '''
    bsonType: "«athenaTypeToMongoDBType(type)»",
    «IF type.elementType instanceof PrimitiveType»items: { bsonType: "«athenaTypeToMongoDBType(type.elementType)»" } ,«ENDIF»
    description: "Field «name» must be of type array«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch generateType(Set type, String name, boolean optional)
  '''
    bsonType: "«athenaTypeToMongoDBType(type)»",
    «IF type.elementType instanceof PrimitiveType»items: { bsonType: "«athenaTypeToMongoDBType(type)»" } ,«ENDIF»
    description: "Field «name» must be of type array«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch generateType(Map type, String name, boolean optional)
  '''
    bsonType: "«athenaTypeToMongoDBType(type)»",
    description: "Field «name» must be of type object«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch generateType(Tuple type, String name, boolean optional)
  '''
    bsonType: "«athenaTypeToMongoDBType(type)»",
    «IF type.elements.forall[t | t instanceof PrimitiveType]»items: { bsonType: [ «FOR e : type.elements SEPARATOR ", "»"«athenaTypeToMongoDBType(e)»" «ENDFOR» ] } ,«ENDIF»
    description: "Field «name» must be of type array«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch generateType(OptionPrimitiveType type, String name, boolean optional)
  '''
    bsonType: [ «FOR o : type.options SEPARATOR ", "»"«athenaTypeToMongoDBType(o)»"«ENDFOR» ],
    description: "Field «name» must be of type [ «FOR o : type.options SEPARATOR ", "»«athenaTypeToMongoDBType(o)»«ENDFOR» ]«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch generateType(SinglePrimitiveType type, String name, boolean optional)
  '''
    bsonType: "«athenaTypeToMongoDBType(type)»",
    description: "Field «name» must be of type «athenaTypeToMongoDBType(type)»«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch CharSequence generateType(RangedNumber type, String name, boolean optional)
  '''
    bsonType: "«athenaTypeToMongoDBType(type)»",
    minimum: «type.from»,
    maximum: «type.to»,
    description: "Field «name» must be of type «athenaTypeToMongoDBType(type)» in range [ «type.from»..«type.to» ]«IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch CharSequence generateType(RegexpRestrictedString type, String name, boolean optional)
  '''
    bsonType : "«athenaTypeToMongoDBType(type)»",
    pattern : "«type.regexp»",
    description: "Field «name» must be of type string «IF !optional», IS required «ENDIF» and match the regular expression pattern: «type.regexp»."
  '''

  private def dispatch CharSequence generateType(EnumRestrictedNumber type, String name, boolean optional)
  '''
    enum: [ «FOR v : type.enumVals SEPARATOR ", "»«v»«ENDFOR» ],
    description: "Field «name» can only be one of the ENUM values: [ «FOR v : type.enumVals SEPARATOR ", "»«v»«ENDFOR» ] «IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch CharSequence generateType(EnumRestrictedString type, String name, boolean optional)
  '''
    enum: [ «FOR v : type.enumVals SEPARATOR ", "»"«v»"«ENDFOR» ],
    description: "Field «name» can only be one of the ENUM values: [ «FOR v : type.enumVals SEPARATOR ", "»«v»«ENDFOR» ] «IF !optional» and IS required«ENDIF»."
  '''

  private def dispatch CharSequence athenaTypeToMongoDBType(Null type)
  '''null'''

  private def dispatch CharSequence athenaTypeToMongoDBType(List type)
  '''array'''

  private def dispatch CharSequence athenaTypeToMongoDBType(Set type)
  '''array'''

  private def dispatch CharSequence athenaTypeToMongoDBType(Map type)
  '''object'''

  private def dispatch CharSequence athenaTypeToMongoDBType(Tuple type)
  '''array'''

  private def dispatch CharSequence athenaTypeToMongoDBType(SinglePrimitiveType type)
  {
    switch (type.typename.toLowerCase)
    {
      case "integer", case "int":  "int"
      case "number":               "number"
      case "float", case "double": "float"
      case "bool", case "boolean": "bool"
      case "identifier":           "objectId"
      case "timestamp":            "timestamp"
      default:                     "string"
    }
  }
}
