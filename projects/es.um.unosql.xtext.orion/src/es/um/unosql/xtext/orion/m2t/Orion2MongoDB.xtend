package es.um.unosql.xtext.orion.m2t

import java.io.File
import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.EntityRenameOp
import es.um.unosql.xtext.orion.orion.EntitySplitOp
import es.um.unosql.xtext.orion.orion.EntityMergeOp
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceCardinalityOp
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.AggregateCardinalityOp
import es.um.unosql.xtext.orion.orion.AttrOrRefCast
import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.FeatureSelector
import java.util.ArrayList
import es.um.unosql.xtext.orion.orion.EntityOp
import es.um.unosql.xtext.orion.orion.RefOrAggrCard
import es.um.unosql.xtext.orion.utils.OrionUtils
import es.um.unosql.xtext.orion.orion.FeatureAllocate
import es.um.unosql.xtext.orion.orion.EntityDeleteOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.orion.orion.EntityExtractOp
import es.um.unosql.xtext.orion.orion.ConditionDecl
import es.um.unosql.xtext.orion.m2t.utils.MongoDBTransactionModule
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.utils.io.CodeWriter
import es.um.unosql.xtext.orion.validation.m2t.MongoDBValidator
import es.um.unosql.xtext.orion.utils.AthenaSchemaUpdater
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.utils.io.ModelWriter
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.SimpleAggregationTarget
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.orion.orion.AttributePromoteOp
import es.um.unosql.xtext.orion.orion.AttributeDemoteOp
import java.util.Arrays

class Orion2MongoDB
{
  String dbName = null
  val schemaUpdater = new AthenaSchemaUpdater()
  val handler = new AthenaHandler()
  val validator = new MongoDBValidator()
  MongoDBTransactionModule tModule

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

    if (orion.evolBlocks !== null && !orion.evolBlocks.isEmpty)
      result.append(generateEBlockOperations(orion))

    validator.summary
    new CodeWriter().writeToFile(outputFolder, dbName + ".js", result)
    new ModelWriter().write(schemaUpdater.schema, outputFolder + "/" + dbName + ".athena")
  }

  def generateIndepOperations(OrionOperations orion)
  {
    tModule = new MongoDBTransactionModule(false)
    val result = new StringBuilder(dbName + " = db.getSiblingDB(\"" + dbName + "\")\n\n")
    result.append(generateOperations(orion.operations))

    return result
  }

  def generateEBlockOperations(OrionOperations orion)
  {
    tModule = new MongoDBTransactionModule(true)
    val result = new StringBuilder()

    if (tModule.isTransactional(orion.evolBlocks))
      result.append(tModule.generateTransactionalBegin(dbName))
    else
      result.append(dbName + " = db.getSiblingDB(\"" + dbName + "\")\n\n")

    for (eblock : orion.evolBlocks)
    {
      if (tModule.isTransactional(eblock.operations.head))
        result.append(tModule.generateTransactionBegin)

      result.append(generateOperations(eblock.operations))

      if (tModule.inTransaction)
        result.append(tModule.generateTransactionEnd)
    }

    if (tModule.isTransactional(orion.evolBlocks))
      result.append(tModule.generateTransactionalEnd())

    return result
  }

  private def generateOperations(java.util.List<BasicOperation> operations)
  {
    val result = new StringBuilder()
    val iter = operations.iterator
    val opBatch = new ArrayList

    while(iter.hasNext)
    {
      val nextOp = iter.next
      if (isIndependent(nextOp))
      {
        if (!opBatch.empty)
        {
          result.append(generateBatchOps(opBatch))
          opBatch.clear
        }
        result.append(generateBatchOps(nextOp))
      }
      // If the current operation is compatible with the last stored operation, we stack them together
      else if (opBatch.empty || OrionUtils.haveSameSelector(opBatch.head, nextOp))
        opBatch.add(nextOp)
      // If not, generate content for the current stack, empty it and store the new operation.
      else
      {
        if (!opBatch.empty)
        {
          result.append(generateBatchOps(opBatch))
          opBatch.clear
        }

        opBatch.add(nextOp)
      }
    }

    // Generate instructions for last batch...
    if (!opBatch.empty)
      result.append(generateBatchOps(opBatch))

    return result
  }

  private def boolean isIndependent(BasicOperation op)
  {
      switch op {
          ReferenceMorphOp: true
          AggregateMorphOp: true
          FeatureCopyOp: true
          FeatureMoveOp: true
          
          default: false
      }
  }

  private def dispatch CharSequence generateBatchOps(BasicOperation op)
  '''«generateBatchOps(Arrays.asList(op))»'''

  private def dispatch CharSequence generateBatchOps(java.util.List<BasicOperation> ops)
  '''
    «IF tModule.inTransaction && !tModule.isTransactional(ops.head)»
      «tModule.generateTransactionEnd»
    «ENDIF»
    «IF !tModule.inTransaction && tModule.isTransactional(ops.head)»
      «tModule.generateTransactionBegin»
    «ENDIF»
    «IF ops.size == 1»
      «validator.checkBasicOperation(schemaUpdater.schema, ops.head)»
      «IF !(ops.head instanceof EntityOp)»«generateSingleBegin(OrionUtils.getSelector(ops.head))»«ENDIF»«generateBasicOp(ops.head, false)»«IF !(ops.head instanceof EntityOp)»«generateSingleEnd(OrionUtils.getSelector(ops.head))»«ENDIF»
      «schemaUpdater.processOperation(ops.head)»
    «ELSE»
      «generateBatchBegin(OrionUtils.getSelector(ops.head))»
      «FOR op : ops SEPARATOR ","»
        «validator.checkBasicOperation(schemaUpdater.schema, op)»
        «generateBasicOp(op, true)»
        «schemaUpdater.processOperation(op)»
      «ENDFOR»
      «generateBatchEnd(OrionUtils.getSelector(ops.head))»
    «ENDIF»

  '''

  private def generateSingleBegin(FeatureSelector selector)
  '''
    «dbName».«IF selector.forAll»getCollectionNames().forEach(function(collName)
    {
      «dbName»[collName]«ELSE»«selector.ref»«ENDIF»'''

  private def generateSingleEnd(FeatureSelector selector)
  '''«IF selector.forAll»})«ENDIF»'''

  private def generateBatchBegin(FeatureSelector selector)
  '''
    «dbName».«IF selector.forAll»getCollectionNames().forEach(function(collName)
    {
      «dbName»[collName]«ELSE»«selector.ref»«ENDIF».bulkWrite([
  '''

  private def generateBatchEnd(FeatureSelector selector)
  '''
  «IF selector.forAll»
    ])
  })
  «ELSE»
  ])
  «ENDIF»'''

  private def dispatch generateBasicOp(EntityAddOp op, boolean inBulk)
  '''
    «dbName».createCollection("«op.spec.name»")
    «IF !op.spec.features.isNullOrEmpty»
      «dbName».«op.spec.name».updateMany({}, [{$addFields: { «FOR f : op.spec.features SEPARATOR ", "»«generateFeature(f.name, f.type, f.defaultValue)»«ENDFOR»}}], {upsert: true})
    «ENDIF»
  '''

  private def dispatch generateBasicOp(EntityDeleteOp op, boolean inBulk)
  '''
    «dbName».«op.spec.ref».drop()
  '''

  private def dispatch generateBasicOp(EntityRenameOp op, boolean inBulk)
  '''
    «dbName».«op.spec.ref».renameCollection("«op.spec.name»")
  '''

  private def dispatch generateBasicOp(EntitySplitOp op, boolean inBulk)
  '''
    «dbName».«op.spec.ref».aggregate([
      { $project: { «FOR feat : op.spec.features1.features SEPARATOR ", "»"«feat»": 1«ENDFOR» }},
      { $out: "«op.spec.name1»" }
    ])
    «dbName».«op.spec.ref».aggregate([
      { $project: { «FOR feat : op.spec.features2.features SEPARATOR ", "»"«feat»": 1«ENDFOR» }},
      { $out: "«op.spec.name2»" }
    ])
    «dbName».«op.spec.ref».drop()
  '''

  private def dispatch generateBasicOp(EntityExtractOp op, boolean inBulk)
  '''
    «dbName».«op.spec.ref».aggregate([
      { $project: { «FOR feat : op.spec.features.features SEPARATOR ", "»"«feat»": 1«ENDFOR» }},
      { $out: "«op.spec.name»" }
    ])
  '''

  private def dispatch generateBasicOp(EntityMergeOp op, boolean inBulk)
  '''
    «dbName».«op.spec.ref2».aggregate([{ $merge: { into: "«op.spec.name»", on: "«IF op.spec.condition.c2.contains(op.spec.ref2)»«op.spec.condition.c2.substring(op.spec.condition.c2.indexOf(".") + 1)»«ELSE»«op.spec.condition.c2»«ENDIF»", whenMatched: "merge", whenNotMatched: "insert" }}])
    «dbName».«op.spec.ref1».aggregate([{ $merge: { into: "«op.spec.name»", on: "«IF op.spec.condition.c1.contains(op.spec.ref1)»«op.spec.condition.c1.substring(op.spec.condition.c1.indexOf(".") + 1)»«ELSE»«op.spec.condition.c1»«ENDIF»", whenMatched: "merge", whenNotMatched: "insert" }}])
    «dbName».«op.spec.ref2».drop()
    «dbName».«op.spec.ref1».drop()
  '''

  private def dispatch CharSequence generateBasicOp(FeatureDeleteOp op, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter:{}, update: {$unset: {"«op.spec.selector.target»": 1 }}}}
    «ELSE»
      .updateMany({}, {$unset: {"«op.spec.selector.target»": 1}})
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(FeatureRenameOp op, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: {$rename: {"«op.spec.selector.target»": "«op.spec.name»" }}}}
    «ELSE»
      .updateMany({}, {$rename: {"«op.spec.selector.target»": "«op.spec.name»"}})
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(FeatureCopyOp op, boolean inBulk)
  '''
    «generateAllocate(op.spec)»
  '''

  private def dispatch CharSequence generateBasicOp(FeatureMoveOp op, boolean inBulk)
  '''
    «generateAllocate(op.spec)»
    «dbName».«op.spec.sourceSelector.ref».updateMany({}, {$unset: {"«op.spec.sourceSelector.target»": 1}})
  '''

  private def dispatch CharSequence generateBasicOp(FeatureNestOp op, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: {$rename: {"«op.spec.selector.target»": "«op.spec.name».«op.spec.selector.target»"«FOR f:op.spec.features», "«f»": "«op.spec.name».«f»"«ENDFOR»}}}}
    «ELSE»
      .updateMany({}, {$rename: {"«op.spec.selector.target»": "«op.spec.name».«op.spec.selector.target»"«FOR f: op.spec.features», "«f»": "«op.spec.name»"«ENDFOR»}})
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(FeatureUnnestOp op, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: {$rename: {"«op.spec.selector.target»": "«op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)»"«FOR f:op.spec.features», "«f»": "«f.substring(f.indexOf(".") + 1)»"«ENDFOR»}}}}
    «ELSE»
      .updateMany({}, {$rename: {"«op.spec.selector.target»": "«op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)»"«FOR f: op.spec.features», "«f»": "«f.substring(f.indexOf(".") + 1)»"«ENDFOR»}})
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(AttributeAddOp op, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: [{$addFields: { «generateFeature(op.spec.selector.target, op.spec.type, op.spec.defaultValue)»}}], "upsert": true}}
    «ELSE»
      .updateMany({}, [{$addFields: { «generateFeature(op.spec.selector.target, op.spec.type, op.spec.defaultValue)»}}], {upsert: true})
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(AttributeCastOp op, boolean inBulk)
  '''
    «generateCast(op.spec, inBulk)»
  '''

  private def dispatch CharSequence generateBasicOp(AttributePromoteOp op, boolean inBulk)
  {
    //TODO: Not implemented yet
  }

  private def dispatch CharSequence generateBasicOp(AttributeDemoteOp op, boolean inBulk)
  {
    //TODO: Not implemented yet
  }

  private def dispatch CharSequence generateBasicOp(ReferenceAddOp op, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: [{$addFields: { «generateReference(op.spec.selector.target, op.spec.type, op.spec.defaultValue, op.spec.cardinality)»}}], "upsert": true}}
    «ELSE»
      .updateMany({}, [{$addFields: { «generateReference(op.spec.selector.target, op.spec.type, op.spec.defaultValue, op.spec.cardinality)»}}], {upsert: true})
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(ReferenceCardinalityOp op, boolean inBulk)
  '''
    «generateCard(op.spec, inBulk)»
  '''

  private def dispatch CharSequence generateBasicOp(ReferenceCastOp op, boolean inBulk)
  '''
    «generateCast(op.spec, inBulk)»
  '''

//TODO:     «val theEntity = (handler.getFirstFeatureNameIn(handler.getEntityType(schemaUpdater.schema, op.spec.selector.ref), Reference, op.spec.selector.target).get as Reference).refsTo»
//       «IF handler.getReferenceUpperBound(handler.getEntityType(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target) == 1»{ $addFields: { "«op.spec.name»": { $arrayElemAt: [ "$«op.spec.name»", 0] }}},«ENDIF»
//     «dbName».«op.spec.selector.ref».updateMany({}, { $unset: {"«IF handler.getReferenceUpperBound(handler.getEntityType(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target) == 1»«op.spec.name»._id«ELSE»«op.spec.name».$[]._id«ENDIF»": 1}})
//TODO: ¿Qué pasa si el morph se hace sobre un campo que no sea referencia?
  private def dispatch CharSequence generateBasicOp(ReferenceMorphOp op, boolean inBulk)
  '''
    «val theReference = handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target).head.type as SimpleReferenceTarget»
    «val theEntity = theReference.ref»
    .aggregate([
      { $lookup: { from: "«theEntity.name»", localField: "«op.spec.selector.target»", foreignField: "_id", as: "«op.spec.name»" }},
      «IF theReference.multiplicity.equals("+")»{ $addFields: { "«op.spec.name»": { $arrayElemAt: [ "$«op.spec.name»", 0] }}},«ENDIF»
      { $out: «IF op.spec.selector.forAll»«dbName»[collName]._shortName«ELSE»"«op.spec.selector.ref»"«ENDIF» }
    ])
    «IF op.spec.deleteId»
    «dbName».«op.spec.selector.ref».updateMany({}, { $unset: {"«IF theReference.multiplicity.equals("+")»«op.spec.name»._id«ELSE»«op.spec.name».$[]._id«ENDIF»": 1}})
    «ENDIF»
    «IF op.spec.deleteEntity»
    «dbName».«theEntity.name».drop()
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(AggregateAddOp op, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: [{$addFields: { "«op.spec.selector.target»": «IF op.spec.cardinality.equals("*")»[«ENDIF»{«IF op.spec.features.empty»$literal: {}«ENDIF»«FOR f : op.spec.features SEPARATOR ", "»«generateFeature(f.name, f.type, f.defaultValue)»«ENDFOR»}«IF op.spec.cardinality.equals("*")»]«ENDIF»}}], upsert: true}}
    «ELSE»
      .updateMany({}, [{$addFields: { "«op.spec.selector.target»": «IF op.spec.cardinality.equals("*")»[«ENDIF»{«IF op.spec.features.empty»$literal: {}«ENDIF»«FOR f : op.spec.features SEPARATOR ", "»«generateFeature(f.name, f.type, f.defaultValue)»«ENDFOR»}«IF op.spec.cardinality.equals("*")»]«ENDIF»}}], {upsert: true})
    «ENDIF»
  '''

  private def dispatch CharSequence generateBasicOp(AggregateCardinalityOp op, boolean inBulk)
  '''
    «generateCard(op.spec, inBulk)»
  '''

  private def dispatch CharSequence generateBasicOp(AggregateMorphOp op, boolean inBulk)
  '''.find().forEach(function(sDoc) {
    «IF (handler.getSimpleFeatureFromSchemaType(handler.getEntityDecl(schemaUpdater.schema, op.spec.selector.ref), op.spec.selector.target).head.type as SimpleAggregationTarget).multiplicity.equals("+")»
      if (!sDoc.«op.spec.selector.target».hasOwnProperty('_id'))
        sDoc.«op.spec.selector.target»._id = ObjectId().str;

      «dbName».«op.spec.selector.target.toFirstUpper».insert(sDoc.«op.spec.selector.target»);
      «dbName».«op.spec.selector.ref».updateMany({}, {$set: {"«op.spec.name»": sDoc.«op.spec.selector.target»._id }})
    «ELSE»
      var ids = [];
      sDoc.«op.spec.selector.target».forEach(function(i)
      {
        if (!i.hasOwnProperty('_id'))
          i._id = ObjectId().str;

        ids.push(i._id);
      });
      «dbName».«op.spec.selector.target.toFirstUpper».insertMany(sDoc.«op.spec.selector.target»);
      «dbName».«op.spec.selector.ref».updateMany({}, {$set: {"«op.spec.name»": ids}});
    «ENDIF»
});
  '''

  private def generateCast(AttrOrRefCast spec, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: [{$set: { "«spec.selector.target»": { $convert: { input: "$«spec.selector.target»", to: «typeToBSONType(spec.type)» }}}}]}}
    «ELSE»
      .updateMany({}, [{$set: { "«spec.selector.target»" : { $convert: { input: "$«spec.selector.target»", to: «typeToBSONType(spec.type)» }}}}])
    «ENDIF»
  '''

  private def generateCard(RefOrAggrCard spec, boolean inBulk)
  '''
    «IF inBulk»
      «"  "»{updateMany: {filter: {}, update: [{$set: { "«spec.selector.target»": «IF spec.cardinality.equals("*")»[ "$«spec.selector.target»" ]«ELSE»{ $arrayElemAt: [ "$«spec.selector.target»", 0] }«ENDIF»}}]}}
    «ELSE»
      .updateMany({}, [{$set: { "«spec.selector.target»": «IF spec.cardinality.equals("*")»[ "$«spec.selector.target»" ]«ELSE»{ $arrayElemAt: [ "$«spec.selector.target»", 0] }«ENDIF»}}])
    «ENDIF»
  '''

  private def generateAllocate(FeatureAllocate spec)
  '''.find().forEach( function(sDoc) { «dbName».«spec.targetSelector.ref».updateMany({ «processCondition(spec.condition, spec.sourceSelector.ref, spec.targetSelector.ref)» }, { $set: { "«spec.targetSelector.target»": sDoc.«spec.sourceSelector.target»} }); })'''

  private def generateFeature(String name, DataType type, String defaultValue)
  '''«IF name.equals("_id") && type === null»«generateId()»«ELSE»"«name»": «IF type === null»null«ELSE»«generateDataType(type, defaultValue)»«ENDIF»«ENDIF»'''

  private def generateReference(String name, PrimitiveType type, String defaultValue, String cardinality)
  '''"«name»": «IF cardinality.equals("*")»[«ENDIF»«IF type === null»«IF cardinality.equals("+")»null«ENDIF»«ELSE»«generateDataType(type, defaultValue)»«ENDIF»«IF cardinality.equals("*")»]«ENDIF»'''

  private def dispatch CharSequence generateDataType(Null type, String defValue)
  '''null'''

  private def dispatch CharSequence generateDataType(List type, String defValue)
  '''[«IF type.elementType !== null»«generateDataType(type.elementType, defValue)»«ENDIF»]'''

  private def dispatch CharSequence generateDataType(Set type, String defValue)
  '''[«IF type.elementType !== null»«generateDataType(type.elementType, defValue)»«ENDIF»]'''

  private def dispatch CharSequence generateDataType(Tuple type, String defValue)
  '''[«IF !type.elements.nullOrEmpty»«FOR DataType dt : type.elements»«generateDataType(dt, defValue)»«ENDFOR»«ENDIF»]'''

  private def dispatch CharSequence generateDataType(Map type, String defValue)
  '''{«IF type.keyType !== null && type.valueType !== null»«generateDataType(type.keyType, defValue)»: «generateDataType(type.valueType, defValue)»«ELSE»$literal: {}«ENDIF»}'''

  //TODO: Option
  private def dispatch generateDataType(SinglePrimitiveType type, String defValue)
  {
    switch (type.typename)
    {
      case "Integer", case "Int", case "Number": "NumberInt(" + generateDefValue(Integer, defValue) + ")"
      case "Float", case "Double":               generateDefValue(Double, defValue)
      case "Bool", case "Boolean":               generateDefValue(Boolean, defValue)
      case "Identifier":                         "ObjectId()"
      default:                                   generateDefValue(String, defValue)
    }
  }

  private def <T> generateDefValue(Class<T> theClass, String defValue)
  {
    try
    {
      if (defValue !== null)
      {
        switch (theClass)
        {
          case Integer: return Integer.parseInt(defValue)
          case Double:  return Double.parseDouble(defValue)
          case Boolean: return Boolean.parseBoolean(defValue)
          default:      return new String(defValue)
        }
      }
    } catch (NumberFormatException e)
    {
      return generateBaseValue(theClass)
    }
    return generateBaseValue(theClass)
  }

  private def generateId()
  '''"_id": ObjectId()'''

  private def <T> generateBaseValue(Class<T> theClass)
  {
    switch (theClass)
    {
      case Integer: "0"
      case Double:  "0.0"
      case Boolean: "false"
      default:      '""'
    }
  }

  private def typeToBSONType(DataType type)
  {
    switch (type)
    {
      case type instanceof Null: "null"
      //TODO: Option
      case type instanceof PrimitiveType:
      {
        switch ((type as SinglePrimitiveType).typename.toLowerCase)
        {
          case "integer", case "int", case "number": 16 //Int
          case "float", case "double":                1 //Double
          case "bool", case "boolean":                8 //Boolean
          case "identifier":                          7 //ObjectId
          case "timestamp":                          17 //Timestamp
          default:                                    2 //String
        }
      }
      case type instanceof List || type instanceof Set || type instanceof Tuple: 4 //Array
      case type instanceof Map: 3 // Object
    }
  }

  private def processCondition(ConditionDecl cond, String sName, String tName)
  '''"«IF cond.c2.contains(tName)»«cond.c2.substring(cond.c2.indexOf(".") + 1)»«ELSE»«cond.c2»«ENDIF»": sDoc.«IF cond.c1.contains(sName)»«cond.c1.substring(cond.c1.indexOf(".") + 1)»«ELSE»«cond.c1»«ENDIF»'''
}
