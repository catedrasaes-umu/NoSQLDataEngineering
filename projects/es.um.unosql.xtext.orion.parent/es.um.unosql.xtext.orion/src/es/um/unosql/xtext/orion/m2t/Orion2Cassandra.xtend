package es.um.unosql.xtext.orion.m2t

import es.um.unosql.xtext.orion.orion.OrionOperations
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
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceMultiplicityOp
import es.um.unosql.xtext.orion.orion.AggregateMultiplicityOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp
import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.orion.validation.m2t.CassandraValidator
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.orion.orion.AttributePromoteOp
import es.um.unosql.xtext.orion.orion.AttributeDemoteOp
import es.um.unosql.xtext.athena.athena.AthenaSchema
import java.util.ArrayList
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.orion.utils.costs.CassandraModelCost
import es.um.unosql.xtext.orion.utils.updater.AthenaSchemaUpdater
import es.um.unosql.xtext.orion.orion.FeatureAllocateSpec
import es.um.unosql.xtext.orion.orion.AttributeOrReferenceCastSpec
import es.um.unosql.xtext.orion.orion.ReferenceOrAggregateMultiplicitySpec
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.orion.orion.EntityDelVarOp
import es.um.unosql.xtext.orion.orion.EntityAdaptOp
import es.um.unosql.xtext.orion.orion.EntityUnionOp

class Orion2Cassandra
{
  AthenaSchemaUpdater schemaUpdater
  AthenaHandler aHandler
  CassandraValidator validator
  CassandraModelCost modelCost
  java.util.List<String> scripts
  java.util.List<AthenaSchema> schemas

  new()
  {
    this.schemaUpdater = null
    this.aHandler = new AthenaHandler()
    this.validator = new CassandraValidator()
    this.modelCost = new CassandraModelCost()
    this.scripts = new ArrayList<String>()
    this.schemas = new ArrayList<AthenaSchema>()
  }

  def java.util.List<String> m2t(OrionOperations orion)
  {
    this.schemas.clear()
    this.scripts.clear()

    val result = new StringBuilder()
    val schema = orion.imports !== null ?
      new AthenaNormalizer().m2m(orion.imports.importedNamespace)
      :
    // If not, we create a new brand schema but with VersionId = 0
      new AthenaFactory().createAthenaSchema(orion.name, 0)

    // If the schema was created from scratch, disable validation
    // Else, if a schema was provided, activate validation
    schemaUpdater = new AthenaSchemaUpdater(schema, orion.imports !== null)

    // Sequence of operations
    if (!orion.operations.empty)
    {
      result.append(generateHeader(schema.id.name))
      result.append(generateOperations(orion.operations))

      // Now we increment the schema version. Also version 0 to 1 if no schema was provided
      schema.id.version = schema.id.version + 1
      schemas.add(schemaUpdater.schema)
      scripts.add(result.toString)
    }
    // Sequence of evolution blocks
    else
    {
      for (eBlock : orion.evolBlocks)
      {
        result.append(generateHeader(schema.id.name))
        result.append(generateOperations(eBlock.operations))

        // Now we increment the schema version: 0 to 1 if no schema was provided
        schema.id.version = schema.id.version + 1
        schemas.add(EcoreUtil2.copy(schemaUpdater.schema))
        scripts.add(result.toString)
        result.length = 0
      }
    }

    validator.summary

    println(this.modelCost.showCostMap)

    return this.scripts
  }

  def java.util.List<AthenaSchema> getSchemas()
  {
    return this.schemas
  }

  def generateHeader(String dbName)
  '''
    CREATE KEYSPACE IF NOT EXISTS «dbName» WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
    USE «dbName» ;

  '''

  def generateOperations(java.util.List<BasicOperation> operations)
  '''
    «FOR op : operations SEPARATOR "\n"»
      « { validator.checkBasicOperation(schemaUpdater.schema, op); "" } »
      «generateBasicOp(op)»
      «schemaUpdater.processOperation(op)»
      «modelCost.addToCosts(op)»
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
      «generateTableContent(aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature))»
      PRIMARY KEY («aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name» FROM 'tmp.csv' WITH HEADER = TRUE;
  '''

  private def dispatch generateBasicOp(EntitySplitOp op)
  '''
    COPY «op.spec.ref» ( «FOR f : op.spec.features1.features SEPARATOR ", "»«f»«ENDFOR» ) TO 'tmp.csv' WITH HEADER = TRUE;
    CREATE TABLE «op.spec.name1»
    (
      «generateTableContent(op.spec.features1.features.map[f | aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref), f)])»
      PRIMARY KEY («aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey && op.spec.features1.features.contains(f.name)].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name1» FROM 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.ref» ( «FOR f : op.spec.features2.features SEPARATOR ", "»«f»«ENDFOR» )  TO 'tmp.csv' WITH HEADER = TRUE;
    CREATE TABLE «op.spec.name2»
    (
      «generateTableContent(op.spec.features2.features.map[f | aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref), f)])»
      PRIMARY KEY («aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey && op.spec.features2.features.contains(f.name)].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name2» FROM 'tmp.csv' WITH HEADER = TRUE;
    DROP TABLE «op.spec.ref»;
  '''

  private def dispatch generateBasicOp(EntityExtractOp op)
  '''
    COPY «op.spec.ref» ( «FOR feat : op.spec.features.features SEPARATOR ", "»«feat»«ENDFOR» ) TO 'tmp.csv' WITH HEADER = TRUE;
    CREATE TABLE «op.spec.name»
    (
      «generateTableContent(op.spec.features.features.map[f | aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref), f)])»
      PRIMARY KEY («aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref)).filter(SimpleFeature).filter[f | f.isKey && op.spec.features.features.contains(f.name)].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.name» FROM 'tmp.csv' WITH HEADER = TRUE;
  '''

  private def dispatch generateBasicOp(EntityMergeOp op)//TODO: Meter la generación de References
  '''
    CREATE TABLE «op.spec.name»
    (
      «val lFeatures = aHandler.getReducedSimpleFeaturesIn(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref1), aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref2))»
      «generateTableContent(lFeatures)»
      PRIMARY KEY («lFeatures.filter[f | f.isKey].map[f | f.name].join(", ")»)
    );
    COPY «op.spec.ref2» TO 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.name» ( «FOR feat : aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref2)).filter(SimpleFeature) SEPARATOR ", "»«feat.name»«ENDFOR» ) FROM 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.ref1» TO 'tmp.csv' WITH HEADER = TRUE;
    COPY «op.spec.name» ( «FOR feat : aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.ref1)).filter(SimpleFeature) SEPARATOR ", "»«feat.name»«ENDFOR» ) FROM 'tmp.csv' WITH HEADER = TRUE;
    DROP TABLE «op.spec.ref2»;
    DROP TABLE «op.spec.ref1»;
  '''

  private def dispatch generateBasicOp(EntityDelVarOp op)
  ''''''

  private def dispatch generateBasicOp(EntityAdaptOp op)
  ''''''

  private def dispatch generateBasicOp(EntityUnionOp op)
  ''''''

  private def dispatch generateBasicOp(FeatureDeleteOp op)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector).filter[e | op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(e, t) !== null]]»
      ALTER TABLE «e.name» DROP ( «FOR t : op.spec.selector.targets.reject[t | aHandler.getSimpleFeatureInSchemaType(e, t) === null] SEPARATOR ", "»«t»«ENDFOR» );
    «ENDFOR»
  '''

  private def dispatch generateBasicOp(FeatureRenameOp op)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector).filter[e | aHandler.getSimpleFeatureInSchemaType(e, op.spec.selector.target) !== null]»
      «val featToRename = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target)»
      «IF featToRename.key»
        ALTER TABLE «e.name» RENAME «op.spec.selector.target» TO «op.spec.name»
      «ELSE»
        COPY «op.spec.selector.ref» ( «aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref)).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")», «op.spec.selector.target» ) TO 'tmp.csv' WITH HEADER = TRUE;
        ALTER TABLE «e.name» DROP ( «op.spec.selector.target» );
        ALTER TABLE «e.name» ADD «generateSimpleFeature(op.spec.name, featToRename.type, false)»;
        COPY «op.spec.selector.ref» ( «aHandler.getReducedFeaturesInSchemaTypeAndVariations(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref)).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")», «op.spec.name» ) FROM 'tmp.csv' WITH HEADER = TRUE;
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
      «FOR featToNest : op.spec.selector.targets SEPARATOR ", "»
        «val featureToGenerate = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), featToNest)»
        «generateSimpleFeature(featToNest, featureToGenerate.type, true)»
      «ENDFOR»
    );
    ALTER TABLE «op.spec.selector.ref» ADD «op.spec.name» «op.spec.name.toFirstUpper»;
    ALTER TABLE «op.spec.selector.ref» DROP ( «op.spec.selector.targets.join(", ")» );
  '''

  private def dispatch generateBasicOp(FeatureUnnestOp op)//TODO: Debería destruir el tipo y recrearlo sin el campo.
  '''
    ALTER TABLE «op.spec.selector.ref» ADD
    (
      «FOR featToUnnest : op.spec.selector.targets SEPARATOR ", "»
        «val featName = featToUnnest.substring(featToUnnest.indexOf(".") + 1)»
        «val featureToGenerate = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, featToUnnest.substring(0, featToUnnest.indexOf(".")).toFirstUpper), featName)»
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
    «generateCastSpec(op.spec)»
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
      ALTER «IF op.spec.selector.target.contains(".")»TYPE «op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper» ADD «op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") +1)» «ELSE»TABLE «e.name» ADD «op.spec.selector.target» «ENDIF»«IF op.spec.multiplicity.equals("*") || op.spec.multiplicity.equals("+")»list<«ENDIF»«generateDataType(op.spec.type)»«IF op.spec.multiplicity.equals("*") || op.spec.multiplicity.equals("+")»>«ENDIF»;
    «ENDFOR»
  '''

  private def dispatch generateBasicOp(ReferenceCastOp op)
  '''
    «generateCastSpec(op.spec)»
  '''

  private def dispatch generateBasicOp(ReferenceMultiplicityOp op)
  '''
    «generateMultiplicitySpec(op.spec)»
  '''

  private def dispatch generateBasicOp(ReferenceMorphOp op)
  '''
    «val theReference = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target).type as SimpleReferenceTarget»
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector).filter[e | aHandler.getSimpleFeatureInSchemaType(e, op.spec.selector.target) !== null]»
      CREATE TYPE IF NOT EXISTS «op.spec.selector.target.toFirstUpper»
      (
        «generateTypeContent(aHandler.getReducedFeaturesInSchemaTypeAndVariations(theReference.ref).filter(SimpleFeature))»
      );
      ALTER TABLE «e.name» ADD «op.spec.name» «IF theReference.multiplicity.equals("*") || theReference.multiplicity.equals("+")»list<frozen<«ENDIF»«op.spec.selector.target.toFirstUpper»«IF theReference.multiplicity.equals("*") || theReference.multiplicity.equals("+")»>>«ENDIF»;
    «ENDFOR»
    «IF op.spec.deleteEntity»
      DROP TABLE IF EXISTS «theReference.ref.name»;
    «ENDIF»
  '''

  private def dispatch generateBasicOp(AggregateAddOp op)
  '''
    «IF op.spec.selector.target.contains(".")»
      CREATE TYPE IF NOT EXISTS «op.spec.name !== null ? op.spec.name : op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1).toFirstUpper»
      (
        «FOR feat : op.spec.features SEPARATOR ", "»
          «generateSimpleFeature(feat.name, feat.type, true)»
        «ENDFOR»
      );
      ALTER TYPE «op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper» ADD «op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)» frozen<«IF op.spec.multiplicity.equals("*") || op.spec.multiplicity.equals("+")»list<«ENDIF»«op.spec.name !== null ? op.spec.name : op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1).toFirstUpper»«IF op.spec.multiplicity.equals("*") || op.spec.multiplicity.equals("+")»>«ENDIF»>;
    «ELSE»
      CREATE TYPE IF NOT EXISTS «op.spec.name !== null ? op.spec.name : op.spec.selector.target.toFirstUpper»
      (
        «FOR feat : op.spec.features SEPARATOR ", "»
          «generateSimpleFeature(feat.name, feat.type, true)»
        «ENDFOR»
      );
      «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector)»
        ALTER TABLE «e.name» ADD «op.spec.selector.target» «IF op.spec.multiplicity.equals("*") || op.spec.multiplicity.equals("+")»list<frozen<«ENDIF»«op.spec.name !== null ? op.spec.name : op.spec.selector.target.toFirstUpper»«IF op.spec.multiplicity.equals("*") || op.spec.multiplicity.equals("+")»>>«ENDIF»;
      «ENDFOR»
    «ENDIF»
  '''

  private def dispatch generateBasicOp(AggregateMultiplicityOp op)
  '''
    «generateMultiplicitySpec(op.spec)»
  '''

  private def dispatch generateBasicOp(AggregateMorphOp op)
  '''
    «val theAggregate = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target).type as SimpleAggregateTarget»
    «val feats = aHandler.getFeaturesInAggregate(theAggregate.aggr).filter(SimpleFeature)»
    CREATE TABLE IF NOT EXISTS «op.spec.selector.target.toFirstUpper»
    (
      «IF (!feats.exists[f | f.key])»id uuid,«ENDIF»
      «generateTableContent(feats)»
      PRIMARY KEY («IF feats.exists[f | f.key]»«feats.filter[f | f.key].map[f | f.name].join(", ")»«ELSE»id«ENDIF»)
    );
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, op.spec.selector)»
      ALTER TABLE «e.name» ADD «op.spec.name» «IF theAggregate.multiplicity.equals("*") || theAggregate.multiplicity.equals("+")»list<«ENDIF»«IF feats.exists[f | f.key]»«generateDataType(feats.findFirst[f | f.key].type as DataType)»«ELSE»uuid«ENDIF»«IF theAggregate.multiplicity.equals("*") || theAggregate.multiplicity.equals("+")»>«ENDIF»;
    «ENDFOR»
  '''

  private def generateAllocate(FeatureAllocateSpec spec)
  '''
    «val featToCopy = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schemaUpdater.schema, spec.sourceSelector.ref), spec.sourceSelector.target)»
    COPY «spec.sourceSelector.ref» ( «processCondition(spec.condition.c1, spec.sourceSelector.target)» ) TO 'tmp.csv' WITH HEADER = TRUE;
    ALTER TABLE «spec.targetSelector.ref» ADD «generateSimpleFeature(spec.targetSelector.target, featToCopy.type, false)»;
    COPY «spec.targetSelector.ref» ( «processCondition(spec.condition.c2, spec.targetSelector.target)» ) FROM 'tmp.csv' WITH HEADER = TRUE;
  '''

  private def generateCastSpec(AttributeOrReferenceCastSpec spec)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, spec.selector).filter[e | spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(e, t) !== null]]»
      COPY «e.name» TO 'tmp.csv' WITH HEADER = TRUE;
      DROP TABLE «e.name»;
      CREATE TABLE «e.name»
      (
        «generateUpdatedTable(e, spec)»
      );
      COPY «e.name» FROM 'tmp.csv' WITH HEADER = TRUE;
    «ENDFOR»
  '''

  private def generateMultiplicitySpec(ReferenceOrAggregateMultiplicitySpec spec)
  '''
    «FOR e : OrionUtils.getEntityTypesFromSelector(schemaUpdater.schema, spec.selector).filter[e | spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(e, t) !== null]]»
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
  private def generateUpdatedTable(EntityDecl entity, AttributeOrReferenceCastSpec spec)
  '''
    «generateTableContent(aHandler.getReducedFeaturesInSchemaTypeAndVariations(entity).filter(SimpleFeature).reject[f | spec.selector.targets.exists[t | t.equals(f.name)]])»
    «FOR t : spec.selector.targets SEPARATOR ","»
      «generateSimpleFeature(t, spec.type, false)»
    «ENDFOR»
    PRIMARY KEY («aHandler.getReducedFeaturesInSchemaTypeAndVariations(entity).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")»)
  '''

  private def generateUpdatedTable(EntityDecl entity, ReferenceOrAggregateMultiplicitySpec spec)//TODO:
  '''
    «val features = spec.selector.targets.map[t | aHandler.getSimpleFeatureInSchemaType(entity, t)]»
    «generateTableContent(aHandler.getReducedFeaturesInSchemaTypeAndVariations(entity).filter(SimpleFeature).reject[f | spec.selector.targets.exists[t | t.equals(f.name)]])»
    «FOR f : features SEPARATOR ", "»
      «f.name» «IF spec.multiplicity.equals("*") || spec.multiplicity.equals("+")»list<«IF f.type instanceof SimpleAggregateTarget»frozen<«f.name.toFirstUpper»>«ELSE»«IF f.type instanceof SimpleReferenceTarget»«generateDataType((f.type as SimpleReferenceTarget).type)»«ELSE»«generateDataType(f.type as DataType)»«ENDIF»«ENDIF»>«ELSE»«IF f.type instanceof SimpleAggregateTarget»«f.name.toFirstUpper»«ELSE»«IF f.type instanceof SimpleReferenceTarget»«generateDataType((f.type as SimpleReferenceTarget).type)»«ELSE»«generateDataType(getInnerDataType(f.type as DataType))»«ENDIF»«ENDIF»«ENDIF»,
    «ENDFOR»
    PRIMARY KEY («aHandler.getReducedFeaturesInSchemaTypeAndVariations(entity).filter(SimpleFeature).filter[f | f.isKey].map[f | f.name].join(", ")»)
  '''

  private def generateTableContent(Iterable<SimpleFeature> features)
  '''«generateInnerContent(features, false)»'''

  private def generateTypeContent(Iterable<SimpleFeature> features)
  '''«generateInnerContent(features, true)»'''

  private def generateInnerContent(Iterable<SimpleFeature> features, boolean freeze)//TODO: ¿Faltan referencias? Algo falla con Rename a la hora de recrear una tabla.
  '''
    «FOR simpleFeat : features»
      «generateSimpleFeature(simpleFeat.name, simpleFeat.type, freeze)»,
    «ENDFOR»
  '''

  private def dispatch generateSimpleFeature(String name, DataType type, boolean freeze)
  '''«name» «IF freeze && !(type instanceof SinglePrimitiveType)»frozen<«ENDIF»«generateDataType(type)»«IF freeze && !(type instanceof SinglePrimitiveType)»>«ENDIF»'''

  private def dispatch generateSimpleFeature(String name, SimpleAggregateTarget aggr, boolean freeze)
  '''«name» «IF freeze»frozen<«ENDIF»«IF aggr.multiplicity.equals("*") || aggr.multiplicity.equals("+")»list<frozen<«ENDIF»«aHandler.getSimpleAggregateTargetName(aggr).toFirstUpper»«IF aggr.multiplicity.equals("*") || aggr.multiplicity.equals("+")»>>«ENDIF»«IF freeze»>«ENDIF»'''

  private def dispatch generateSimpleFeature(String name, SimpleReferenceTarget ref, boolean freeze)
  '''«name» «IF freeze»frozen<«ENDIF»«IF ref.multiplicity.equals("*") || ref.multiplicity.equals("+")»list<«ENDIF»«generateDataType(ref.type)»«IF ref.multiplicity.equals("*") || ref.multiplicity.equals("+")»>«ENDIF»«IF freeze»>«ENDIF»'''

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
