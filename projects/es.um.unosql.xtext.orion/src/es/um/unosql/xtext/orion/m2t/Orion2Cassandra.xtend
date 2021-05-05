package es.um.unosql.xtext.orion.m2t

import java.io.File
import es.um.unosql.xtext.orion.orion.OrionOperations
import java.util.Arrays
import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.orion.orion.EntityDeleteOp
import es.um.unosql.xtext.orion.orion.EntityRenameOp
import es.um.unosql.xtext.orion.utils.OrionUtils
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.EntitySplitOp
import es.um.unosql.xtext.orion.orion.EntityMergeOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.EntityExtractOp
import es.um.unosql.xtext.orion.orion.AttrOrRefCast
import es.um.unosql.xtext.orion.orion.FeatureAllocate
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceCardinalityOp
import es.um.unosql.xtext.orion.orion.RefOrAggrCard
import es.um.unosql.xtext.orion.orion.AggregateCardinalityOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp
import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.athena.utils.io.CodeWriter
import es.um.unosql.xtext.orion.validation.m2t.CassandraValidator
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.orion.utils.AthenaSchemaUpdater
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.utils.io.ModelWriter
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.SimpleAggregationTarget
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.orion.orion.AttributePromoteOp
import es.um.unosql.xtext.orion.orion.AttributeDemoteOp

class Orion2Cassandra
{
  String dbName = null
  val schemaUpdater = new AthenaSchemaUpdater()
  val handler = new AthenaHandler()
  val validator = new CassandraValidator()

  def m2t(OrionOperations orion, File outputFolder)
  {
    val result = new StringBuilder()

    if (orion.imports !== null)
      schemaUpdater.schema = orion.imports.importedNamespace
    else
      schemaUpdater.schema = new AthenaFactory().createAthenaSchema(orion.name)

    dbName = schemaUpdater.schema.name

    if (orion.operations !== null && !orion.operations.isEmpty)
      result.append(generateIndepOperations(orion))
    else
      result.append(generateEBlockOperations(orion))

    validator.summary
    new CodeWriter().writeToFile(outputFolder, dbName + ".cql", result)
    new ModelWriter().write(schemaUpdater.schema, outputFolder + "/" + dbName + ".athena")
  }

  def generateIndepOperations(OrionOperations orion)
  '''
    CREATE KEYSPACE IF NOT EXISTS «dbName» WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
    USE «dbName» ;

    «generateOperations(orion.operations)»
  '''

  def generateEBlockOperations(OrionOperations orion)
  '''
    CREATE KEYSPACE IF NOT EXISTS «dbName» WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
    USE «dbName» ;

    «FOR block : orion.evolBlocks SEPARATOR "\n"»
      «generateOperations(block.operations)»
    «ENDFOR»
  '''

  def generateOperations(java.util.List<BasicOperation> operations)
  '''
    «FOR op : operations SEPARATOR "\n"»
      « { validator.checkBasicOperation(schemaUpdater.schema, op); "" } »
      «generateBasicOp(op)»
      «schemaUpdater.processOperation(op)»
    «ENDFOR»
  '''

  private def dispatch generateBasicOp(EntityAddOp op)
  '''
    CREATE TABLE IF NOT EXISTS «op.spec.name»
    (
      «FOR feat : op.spec.features»
        «generateSimpleFeature(feat.name, feat.type, false)»,
      «ENDFOR»
      PRIMARY KEY («op.spec.features.filter[f | f.isKey].map[f | f.name].join(", ")»)
    );
    «IF !op.spec.features.isEmpty && op.spec.features.exists[f | f.defaultValue !== null]»
    INSERT INTO «op.spec.name» («op.spec.features.filter[f | f.defaultValue !== null].map[f | f.name].join(", ")») VALUES («op.spec.features.filter[f | f.defaultValue !== null].map[f | f.defaultValue.replace("\"", "'")].join(", ")»);
    «ENDIF»
  '''

  private def dispatch generateBasicOp(EntityDeleteOp op)
  '''
    DROP TABLE IF EXISTS «op.spec.ref»;
  '''

  private def dispatch generateBasicOp(EntityRenameOp op)
  '''
    COPY «op.spec.ref» TO 'tmp.csv' WITH HEADER = TRUE;
    DROP TABLE «op.spec.ref»;
    CREATE TABLE «op.spec.name»
    (
      «generateTableContent(handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature))»
      PRIMARY KEY («handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name» FROM 'tmp.csv' WITH HEADER = TRUE;
  '''

  private def dispatch generateBasicOp(EntitySplitOp op)
  '''
    COPY «op.spec.ref» ( «FOR f : op.spec.features1.features SEPARATOR ", "»«f»«ENDFOR» ) TO 'tmp.csv' WITH HEADER = TRUE;
    CREATE TABLE «op.spec.name1»
    (
      «generateTableContent(op.spec.features1.features.map[f | handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref), f).head])»
      PRIMARY KEY («handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey && op.spec.features1.features.contains(f.name)].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name1» FROM 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.ref» ( «FOR f : op.spec.features2.features SEPARATOR ", "»«f»«ENDFOR» )  TO 'tmp.csv' WITH HEADER = TRUE;
    CREATE TABLE «op.spec.name2»
    (
      «generateTableContent(op.spec.features2.features.map[f | handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref), f).head])»
      PRIMARY KEY («handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey && op.spec.features2.features.contains(f.name)].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name2» FROM 'tmp.csv' WITH HEADER = TRUE;
    DROP TABLE «op.spec.ref»;
  '''

  private def dispatch generateBasicOp(EntityExtractOp op)
  '''
    COPY «op.spec.ref» ( «FOR feat : op.spec.features.features SEPARATOR ", "»«feat»«ENDFOR» ) TO 'tmp.csv' WITH HEADER = TRUE;
    CREATE TABLE «op.spec.name»
    (
      «generateTableContent(op.spec.features.features.map[f | handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref), f).head])»
      PRIMARY KEY («handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey && op.spec.features.features.contains(f.name)].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name» FROM 'tmp.csv' WITH HEADER = TRUE;
  '''

  private def dispatch generateBasicOp(EntityMergeOp op)
  '''
    CREATE TABLE «op.spec.name»
    (
      «val lFeatures = handler.getReducedSimpleFeaturesFrom(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref1), handler.getEntityDecl(schemaUpdater.schema, op.spec.ref2))»
      «generateTableContent(lFeatures)»
      PRIMARY KEY («lFeatures.filter[f | f.isKey].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.ref2» TO 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.name» ( «FOR feat : handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref2)).filter(SimpleFeature) SEPARATOR ", "»«feat.name»«ENDFOR» ) FROM 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.ref1» TO 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.name» ( «FOR feat : handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.ref1)).filter(SimpleFeature) SEPARATOR ", "»«feat.name»«ENDFOR» ) FROM 'tmp.csv' WITH HEADER = TRUE;
    DROP TABLE «op.spec.ref2»;
    DROP TABLE «op.spec.ref1»;
  '''

  private def dispatch generateBasicOp(FeatureDeleteOp op)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector).filter[e | !handler.getSimpleFeatureFromSchemaType(e, op.spec.selector.target).empty]»
      ALTER TABLE «e.name» DROP ( «op.spec.selector.target» );
    «ENDFOR»
  '''

  private def dispatch generateBasicOp(FeatureRenameOp op)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector).filter[e | !handler.getSimpleFeatureFromSchemaType(e, op.spec.selector.target).empty]»
      «val featToRename = handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target).head»
      «IF featToRename.key»
        ALTER TABLE «e.name» RENAME «op.spec.selector.target» TO «op.spec.name»
      «ELSE»
        COPY «op.spec.selector.ref» ( «handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref)).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")», «op.spec.selector.target» ) TO 'tmp.csv' WITH HEADER = TRUE;
        ALTER TABLE «e.name» DROP ( «op.spec.selector.target» );
        ALTER TABLE «e.name» ADD «generateSimpleFeature(op.spec.name, featToRename.type, false)»;
        COPY «op.spec.selector.ref» ( «handler.getReducedFeaturesFromSchemaTypeAndVariations(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref)).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")», «op.spec.name» ) FROM 'tmp.csv' WITH HEADER = TRUE;
      «ENDIF»
    «ENDFOR»
  '''

  private def dispatch generateBasicOp(FeatureCopyOp op)
  '''
    «generateAllocate(op.spec)»
  '''

  private def dispatch generateBasicOp(FeatureMoveOp op)
  '''
    «generateAllocate(op.spec)»
    ALTER TABLE «op.spec.sourceSelector.ref» DROP ( «op.spec.sourceSelector.target» );
  '''

  private def dispatch generateBasicOp(FeatureNestOp op)
  '''
    CREATE TYPE IF NOT EXISTS «op.spec.name.toFirstUpper»
    (
      «FOR featToNest : op.spec.features SEPARATOR ", "»
        «val featureToGenerate = handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), featToNest).head»
        «generateSimpleFeature(featToNest, featureToGenerate.type, true)»
      «ENDFOR»
    );
    ALTER TABLE «op.spec.selector.ref» ADD «op.spec.name» «op.spec.name.toFirstUpper»;
    ALTER TABLE «op.spec.selector.ref» DROP ( «FOR feat : op.spec.features SEPARATOR ", "»«feat»«ENDFOR» );
  '''

  private def dispatch generateBasicOp(FeatureUnnestOp op)
  '''
    ALTER TABLE «op.spec.selector.ref» ADD
    (
      «FOR featToUnnest : Arrays.asList(op.spec.selector.target) + op.spec.features SEPARATOR ", "»
        «val featName = featToUnnest.substring(featToUnnest.indexOf(".") + 1)»
        «val featureToGenerate = handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, featToUnnest.substring(0, featToUnnest.indexOf(".")).toFirstUpper), featName).head»
        «generateSimpleFeature(featName, featureToGenerate.type, false)»
      «ENDFOR»
    );
  '''
  // ALTER TABLE «op.spec.selector.ref» DROP ( «(Arrays.asList(op.spec.selector.target) + op.spec.features).map[feat | feat.substring(0, feat.indexOf("."))].toSet.join(", ")» );

  private def dispatch generateBasicOp(AttributeAddOp op)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector)»
      ALTER «IF op.spec.selector.target.contains(".")»TYPE «op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper» ADD «generateSimpleFeature(op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1), op.spec.type, true)»«ELSE»TABLE «e.name» ADD «generateSimpleFeature(op.spec.selector.target, op.spec.type, false)»«ENDIF»;
    «ENDFOR»
  '''

  private def dispatch generateBasicOp(AttributeCastOp op)
  '''
    «generateCast(op.spec)»
  '''

  private def dispatch generateBasicOp(AttributePromoteOp op)
  {
    //TODO: Not implemented yet
  }

  private def dispatch generateBasicOp(AttributeDemoteOp op)
  {
    //TODO: Not implemented yet
  }

  private def dispatch generateBasicOp(ReferenceAddOp op)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector)»
      ALTER «IF op.spec.selector.target.contains(".")»TYPE «op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper» ADD «op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") +1)» «ELSE»TABLE «e.name» ADD «op.spec.selector.target» «ENDIF»«IF op.spec.cardinality.equals("*")»list<«ENDIF»«generateDataType(op.spec.type)»«IF op.spec.cardinality.equals("*")»>«ENDIF»;
    «ENDFOR»
  '''

  private def dispatch generateBasicOp(ReferenceCastOp op)
  '''
    «generateCast(op.spec)»
  '''

  private def dispatch generateBasicOp(ReferenceCardinalityOp op)
  '''
    «generateCard(op.spec)»
  '''

  private def dispatch generateBasicOp(ReferenceMorphOp op)
  '''
    «val theReference = handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target).head.type as SimpleReferenceTarget»
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector).filter[e | !handler.getSimpleFeatureFromSchemaType(e, op.spec.selector.target).empty]»
      CREATE TYPE IF NOT EXISTS «op.spec.selector.target.toFirstUpper»
      (
        «generateTypeContent(handler.getReducedFeaturesFromSchemaTypeAndVariations(theReference.ref).filter(SimpleFeature))»
      );
      ALTER TABLE «e.name» ADD «op.spec.name» «IF theReference.multiplicity.equals("*")»list<frozen<«ENDIF»«op.spec.selector.target.toFirstUpper»«IF theReference.multiplicity.equals("*")»>>«ENDIF»;
    «ENDFOR»
    «IF op.spec.deleteEntity»
      DROP TABLE IF EXISTS «theReference.ref.name»;
    «ENDIF»
  '''

  private def dispatch generateBasicOp(AggregateAddOp op)
  '''
    «IF op.spec.selector.target.contains(".")»
      CREATE TYPE IF NOT EXISTS «op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1).toFirstUpper»
      (
        «FOR feat : op.spec.features SEPARATOR ", "»
          «generateSimpleFeature(feat.name, feat.type, true)»
        «ENDFOR»
      );
      ALTER TYPE «op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper» ADD «op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)» frozen<«IF op.spec.cardinality.equals("*")»list<«ENDIF»«op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1).toFirstUpper»«IF op.spec.cardinality.equals("*")»>«ENDIF»>;
    «ELSE»
      CREATE TYPE IF NOT EXISTS «op.spec.selector.target.toFirstUpper»
      (
        «FOR feat : op.spec.features SEPARATOR ", "»
          «generateSimpleFeature(feat.name, feat.type, true)»
        «ENDFOR»
      );
      «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector)»
        ALTER TABLE «e.name» ADD «op.spec.selector.target» «IF op.spec.cardinality.equals("*")»list<frozen<«ENDIF»«op.spec.selector.target.toFirstUpper»«IF op.spec.cardinality.equals("*")»>>«ENDIF»;
      «ENDFOR»
    «ENDIF»
  '''

  private def dispatch generateBasicOp(AggregateCardinalityOp op)
  '''
    «generateCard(op.spec)»
  '''

  private def dispatch generateBasicOp(AggregateMorphOp op)
  '''
    «val theAggregation = handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target).head.type as SimpleAggregationTarget»
    «val feats = handler.getFeaturesFromAggregation(theAggregation.aggr).filter(SimpleFeature)»
    CREATE TABLE IF NOT EXISTS «op.spec.selector.target.toFirstUpper»
    (
      «IF (!feats.exists[f | f.key])»id uuid,«ENDIF»
      «generateTableContent(feats)»
      PRIMARY KEY («IF feats.exists[f | f.key]»«feats.filter[f | f.key].map[f | f.name].join(", ")»«ELSE»id«ENDIF»)
    );
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector)»
      ALTER TABLE «e.name» ADD «op.spec.name» «IF theAggregation.multiplicity.equals("*")»list<«ENDIF»«IF feats.exists[f | f.key]»«generateDataType(feats.findFirst[f | f.key].type as DataType)»«ELSE»uuid«ENDIF»«IF theAggregation.multiplicity.equals("*")»>«ENDIF»;
    «ENDFOR»
  '''

  private def generateAllocate(FeatureAllocate spec)
  '''
    «val featToCopy = handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, spec.sourceSelector.ref), spec.sourceSelector.target).head»
    COPY «spec.sourceSelector.ref» ( «processCondition(spec.condition.c1, spec.sourceSelector.target)» ) TO 'tmp.csv' WITH HEADER = TRUE;
    ALTER TABLE «spec.targetSelector.ref» ADD «generateSimpleFeature(spec.targetSelector.target, featToCopy.type, false)»;
    COPY «spec.targetSelector.ref» ( «processCondition(spec.condition.c2, spec.targetSelector.target)» ) FROM 'tmp.csv' WITH HEADER = TRUE;
  '''

  private def generateCast(AttrOrRefCast spec)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, spec.selector).filter[e | !handler.getSimpleFeatureFromSchemaType(e, spec.selector.target).empty]»
      COPY «e.name» TO 'tmp.csv' WITH HEADER = TRUE;
      DROP TABLE «e.name»;
      CREATE TABLE «e.name»
      (
        «generateUpdatedTable(e, spec)»
      );
      COPY «e.name» FROM 'tmp.csv' WITH HEADER = TRUE;
    «ENDFOR»
  '''

  private def generateCard(RefOrAggrCard spec)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, spec.selector).filter[e | !handler.getSimpleFeatureFromSchemaType(e, spec.selector.target).empty]»
      COPY «e.name» TO 'tmp.csv' WITH HEADER = TRUE;
      DROP TABLE «e.name»;
      CREATE TABLE «e.name»
      (
        «generateUpdatedTable(e, spec)»
      );
      COPY «e.name» FROM 'tmp.csv' WITH HEADER = TRUE;
    «ENDFOR»
  '''

  // Careful! We generate the casted property, then the rest of the entity as it was.
  private def generateUpdatedTable(EntityDecl entity, AttrOrRefCast spec)
  '''
    «generateTableContent(handler.getReducedFeaturesFromSchemaTypeAndVariations(entity).filter(SimpleFeature).reject[f | f.name.equals(spec.selector.target)])»
    «generateSimpleFeature(spec.selector.target, spec.type, false)»,
    PRIMARY KEY («handler.getReducedFeaturesFromSchemaTypeAndVariations(entity).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")»)
  '''

  private def generateUpdatedTable(EntityDecl entity, RefOrAggrCard spec)
  '''
    «val feature = handler.getSimpleFeatureFromSchemaType(entity, spec.selector.target).head»
    «generateTableContent(handler.getReducedFeaturesFromSchemaTypeAndVariations(entity).filter(SimpleFeature).reject[f | f.name.equals(spec.selector.target)])»
    «spec.selector.target» «IF spec.cardinality.equals("*")»list<«IF feature.type instanceof SimpleAggregationTarget»frozen<«feature.name.toFirstUpper»>«ELSE»«IF feature.type instanceof SimpleReferenceTarget»«generateDataType((feature.type as SimpleReferenceTarget).type)»«ELSE»«generateDataType(feature.type as DataType)»«ENDIF»«ENDIF»>«ELSE»«IF feature.type instanceof SimpleAggregationTarget»«feature.name.toFirstUpper»«ELSE»«IF feature.type instanceof SimpleReferenceTarget»«generateDataType((feature.type as SimpleReferenceTarget).type)»«ELSE»«generateDataType(getInnerDataType(feature.type as DataType))»«ENDIF»«ENDIF»«ENDIF»,
    PRIMARY KEY («handler.getReducedFeaturesFromSchemaTypeAndVariations(entity).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")»)
  '''

  private def generateTableContent(Iterable<SimpleFeature> features)
  '''«generateInnerContent(features, false)»'''

  private def generateTypeContent(Iterable<SimpleFeature> features)
  '''«generateInnerContent(features, true)»'''

  private def generateInnerContent(Iterable<SimpleFeature> features, boolean freeze)
  '''
    «FOR simpleFeat : features»
      «generateSimpleFeature(simpleFeat.name, simpleFeat.type, freeze)»,
    «ENDFOR»
  '''

  private def dispatch generateSimpleFeature(String name, DataType type, boolean freeze)
  '''«name» «IF freeze && !(type instanceof SinglePrimitiveType)»frozen<«ENDIF»«generateDataType(type)»«IF freeze && !(type instanceof SinglePrimitiveType)»>«ENDIF»'''

  private def dispatch generateSimpleFeature(String name, SimpleAggregationTarget aggr, boolean freeze)
  '''«name» «IF freeze»frozen<«ENDIF»«IF aggr.multiplicity.equals("*")»list<frozen<«ENDIF»«handler.getSimpleAggregationTargetName(aggr).toFirstUpper»«IF aggr.multiplicity.equals("*")»>>«ENDIF»«IF freeze»>«ENDIF»'''

  private def dispatch generateSimpleFeature(String name, SimpleReferenceTarget ref, boolean freeze)
  '''«name» «IF freeze»frozen<«ENDIF»«IF ref.multiplicity.equals("*")»list<«ENDIF»«generateDataType(ref.type)»«IF ref.multiplicity.equals("*")»>«ENDIF»«IF freeze»>«ENDIF»'''

  private def dispatch CharSequence generateDataType(List type)
  '''list<«IF !(type.elementType instanceof SinglePrimitiveType)»frozen<«ENDIF»«generateDataType(type.elementType)»«IF !(type.elementType instanceof SinglePrimitiveType)»>«ENDIF»>'''

  private def dispatch CharSequence generateDataType(Set type)
  '''set<«IF !(type.elementType instanceof SinglePrimitiveType)»frozen<«ENDIF»«generateDataType(type.elementType)»«IF !(type.elementType instanceof SinglePrimitiveType)»>«ENDIF»>'''

  private def dispatch CharSequence generateDataType(Map type)
  '''map<«generateDataType(type.keyType)», «IF !(type.valueType instanceof SinglePrimitiveType)»frozen<«ENDIF»«generateDataType(type.valueType)»«IF !(type.valueType instanceof SinglePrimitiveType)»>«ENDIF»>'''

  private def dispatch CharSequence generateDataType(Tuple type)
  '''tuple<«FOR DataType dt : type.elements SEPARATOR ","»«IF !(dt instanceof SinglePrimitiveType)»frozen<«ENDIF»«generateDataType(dt)»«IF !(dt instanceof SinglePrimitiveType)»>«ENDIF»«ENDFOR»>'''

  private def dispatch generateDataType(SinglePrimitiveType type)
  {
    return typeToCassandraType(type)
  }

  private def processCondition(String cond, String name)
  '''«IF cond.contains(name)»«cond.substring(cond.indexOf(".") + 1)»«ELSE»«cond»«ENDIF», «name»'''

  private def typeToCassandraType(SinglePrimitiveType type)
  {
    switch (type.typename.toLowerCase)
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

  private def DataType getInnerDataType(DataType dType)
  {
    if (dType instanceof List)
      return (dType as List).getElementType();
    if (dType instanceof Set)
      return (dType as Set).getElementType();
    if (dType instanceof Tuple)
      return (dType as Tuple).getElements().get(0);
    if (dType instanceof SinglePrimitiveType || dType instanceof Null)
      return dType;

    throw new IllegalArgumentException("Can't get inner data type of a PMap");
  }
}
