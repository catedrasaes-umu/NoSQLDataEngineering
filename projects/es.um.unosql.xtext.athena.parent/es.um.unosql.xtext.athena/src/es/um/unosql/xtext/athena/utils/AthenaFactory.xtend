package es.um.unosql.xtext.athena.utils

import es.um.unosql.xtext.athena.athena.StructureLiteral
import es.um.unosql.xtext.athena.athena.FeatureSet
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.Type
import es.um.unosql.xtext.athena.athena.ComposedReference
import org.eclipse.emf.common.util.EList
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.StructureExpr
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.athena.Feature
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import org.eclipse.emf.ecore.EObject
import es.um.unosql.xtext.athena.athena.RegularRelationshipDecl
import es.um.unosql.xtext.athena.athena.CommonSpec
import es.um.unosql.xtext.athena.athena.Import
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.FeatureSetDecl
import es.um.unosql.xtext.athena.athena.UnrestrictedPrimitiveType
import es.um.unosql.xtext.athena.athena.SQLStructure
import es.um.unosql.xtext.athena.athena.SQLDefinition
import es.um.unosql.xtext.athena.athena.SQLType
import es.um.unosql.xtext.athena.athena.SQLColumnDefinition
import es.um.unosql.xtext.athena.athena.SQLReferenceTarget
import es.um.unosql.xtext.athena.athena.SQLPrimaryConstraintDefinition
import es.um.unosql.xtext.athena.athena.SQLForeignConstraintDefinition
import es.um.unosql.xtext.athena.athena.SQLUniqueConstraintDefinition
import org.eclipse.xtext.EcoreUtil2

class AthenaFactory
{
  val factory = es.um.unosql.xtext.athena.athena.AthenaFactory.eINSTANCE
  static val INITIAL_SCHEMA_VERSION_ID = 1

  def AthenaSchema createAthenaSchema(String name)
  {
    return createAthenaSchema(name, INITIAL_SCHEMA_VERSION_ID)
  }

  def AthenaSchema createAthenaSchema(String name, int versionId)
  {
    if (name === null)
      throw new IllegalArgumentException("Create AthenaSchema: Name cannot be null")

    val result = factory.createAthenaSchema
    result.id = factory.createSchemaId
    result.id.name = name
    result.id.version = versionId

    return result
  }

  def Import createImport(AthenaSchema schema)
  {
    val result = factory.createImport

    if (schema !== null)
      result.importedNamespace = schema

    return result
  }

  def CommonSpec createCommonSpec(StructureLiteral struct)
  {
    val result = factory.createCommonSpec

    if (struct === null)
      throw new IllegalArgumentException("Create CommonSpec: Struct cannot be null")

    result.structure = struct

    return result
  }

  def FeatureSetDecl createFeatureSetDecl(String name)
  {
    val result = factory.createFeatureSetDecl
    result.name = adaptId(name)

    return result
  }

  def FeatureSetDecl createFeatureSetDecl(String name, StructureExpr struct)
  {
    val result = this.createFeatureSetDecl(name)
    result.structure = struct

    return result
  }

  def FeatureSet createFeatureSet()
  {
    return factory.createFeatureSet
  }

  def FeatureSet createFeatureSet(Feature... feats)
  {
    val result = this.createFeatureSet

    if (feats !== null && !feats.empty)
      result.features.addAll(EcoreUtil2.copyAll(feats))

    return result
  }

  def ShortEntityDecl createShortEntityDecl(String name, Boolean isRoot)
  {
    if (name === null)
      throw new IllegalArgumentException("Create ShortEntityDecl: Name can't be null")

    if (isRoot === null)
      throw new IllegalArgumentException("Create ShortEntityDecl: isRoot can't be null")

    val result = factory.createShortEntityDecl
    result.name = adaptId(name)
    result.root = isRoot

    return result
  }

  def RegularEntityDecl createRegularEntityDecl(String name, Boolean isRoot)
  {
    if (name === null)
      throw new IllegalArgumentException("Create RegularEntityDecl: Name can't be null")

    if (isRoot === null)
      throw new IllegalArgumentException("Create RegularEntityDecl: isRoot can't be null")

    val result = factory.createRegularEntityDecl
    result.name = adaptId(name)
    result.root = isRoot

    return result
  }

  def RegularRelationshipDecl createRegularRelationshipDecl(String name)
  {
    if (name === null)
      throw new IllegalArgumentException("Create RegularEntityDecl: Name can't be null")

    val result = factory.createRegularRelationshipDecl
    result.name = adaptId(name)

    return result
  }

  def VariationDecl createVariationDecl(Integer varId)
  {
    if (varId === null)
      throw new IllegalArgumentException("Create VariationDecl: VarId can't be null")

    return createVariationDecl(String.valueOf(varId))
  }

  def VariationDecl createVariationDecl(String name, StructureExpr struct)
  {
    val result = createVariationDecl(name)

    if (struct !== null)
      result.structure = struct

    return result
  }

  def VariationDecl createVariationDecl(String name)
  {
    if (name === null)
      throw new IllegalArgumentException("Create VariationDecl: VarId can't be null")

    val result = factory.createVariationDecl
    result.name = adaptId(name)

    return result
  }

  def ComposedReference createComposedReference(EList<String> names, EntityDecl entity)
  {
    if (names === null || names.isEmpty)
      throw new IllegalArgumentException("Create ComposedReference: names must have at least one element")

    if (entity === null)
      throw new IllegalArgumentException("Create ComposedReference: entity can't be null")

    val result = factory.createComposedReference
    result.names.addAll(names)
    result.target = factory.createComposedReferenceTarget
    result.target.ref = entity

    return result
  }

  def StructureExpr createStructureExpr(StructureExpr expr1, StructureExpr expr2, String operator)
  {
    if (expr1 === null || expr2 === null || operator === null)
      throw new IllegalArgumentException("Create StructureExpr: expr and operators cannot be null")

    val result = factory.createStructureExpr
    result.left = EcoreUtil2.copy(expr1)
    result.right = EcoreUtil2.copy(expr2)
    result.operator = operator

    return result
  }

  def StructureLiteral createStructureLiteral(FeatureSet fset)
  {
    if (fset === null)
      throw new IllegalArgumentException("Create StructureLiteral: fset can't be null")

    val result = factory.createStructureLiteral
    result.spec = EcoreUtil2.copy(fset)

    return result
  }

  def SimpleFeature createSimpleFeature(String name)
  {
    if (name === null)
      throw new IllegalArgumentException("Create SimpleFeature: name can't be null")

    val result = factory.createSimpleFeature
    result.name = adaptId(name)

    return result
  }

  def SimpleFeature createSimpleFeature(String name, Type type)
  {
    val result = createSimpleFeature(adaptId(name))

    if (type !== null)
      result.type = type

    return result
  }

  def SimpleFeature createSimpleFeature(String name, Type type, Boolean isOptional)
  {
    val result = createSimpleFeature(adaptId(name), type)
    result.optional = isOptional

    return result
  }

  def SimpleReferenceTarget createSimpleRef(EntityDecl entityRef, String multiplicity)
  {
    if (entityRef === null)
      throw new IllegalArgumentException("Create SimpleReferenceTarget: entityRef can't be null")

    val result = factory.createSimpleReferenceTarget
    result.ref = entityRef

    if (multiplicity !== null)
      result.multiplicity = multiplicity

    return result
  }

  def SimpleReferenceTarget createSimpleRef(EntityDecl entityRef, String multiplicity, PrimitiveType pType)
  {
    val result = this.createSimpleRef(entityRef, multiplicity)

    if (pType !== null)
      result.type = pType

    return result
  }

  def SimpleReferenceTarget createSimpleRef(EntityDecl entityRef, String multiplicity, EList<EObject> featBy)
  {
    val result = this.createSimpleRef(entityRef, multiplicity)

    if (featBy !== null && !featBy.isEmpty)
      result.featuredBy.addAll(featBy)

    return result
  }

  def SimpleAggregateTarget createSimpleAggr(EObject aggrType, String multiplicity)
  {
    if (aggrType === null)
      throw new IllegalArgumentException("Create SimpleAggregate: aggrType can't be null")

    val result = factory.createSimpleAggregateTarget
    result.aggr.add(aggrType)

    if (multiplicity !== null)
      result.multiplicity = multiplicity

    return result
  }

  def SimpleAggregateTarget createSimpleAggr(EList<VariationDecl> aggrs, String multiplicity)
  {
    if (aggrs === null || aggrs.isEmpty)
      throw new IllegalArgumentException("Create SimpleAggregate: aggrs must contain at least one element")

    val result = factory.createSimpleAggregateTarget
    result.aggr.addAll(aggrs)

    if (multiplicity !== null)
      result.multiplicity = multiplicity

    return result
  }

  def Null createNull()
  {
    return factory.createNull
  }

  def UnrestrictedPrimitiveType createUnrestrictedPrimitiveType(String type)
  {
    if (type === null)
      throw new IllegalArgumentException("Create UnrestrictedPrimitiveTypeLiteral: type can't be null")

    val result = factory.createUnrestrictedPrimitiveType
    result.typename = type

    return result
  }

  def List createList()
  {
    return factory.createList
  }

  def List createList(DataType dType)
  {
    val result = createList

    if (dType !== null)
      result.elementType = dType

    return result
  }

  def Set createSet()
  {
    return factory.createSet
  }

  def Set createSet(DataType dType)
  {
    val result = createSet

    if (dType !== null)
      result.elementType = dType

    return result
  }

  def Map createMap()
  {
    return factory.createMap
  }

  def Map createMap(PrimitiveType key, DataType value)
  {
    val result = createMap

    if (key !== null)
      result.keyType = key

    if (value !== null)
      result.valueType = value

    return result
  }

  def Tuple createTuple()
  {
    return factory.createTuple
  }

  def Tuple createTuple(DataType... datatypes)
  {
    val result = createTuple

    if (datatypes !== null && !datatypes.empty)
      result.elements.addAll(datatypes)

    return result
  }

  def SQLStructure createSQLStructure(String name, EList<SQLDefinition> definitions)
  {
    if (name === null)
      throw new IllegalArgumentException("Create SQLStructure: name can't be null")

    if (definitions === null || definitions.empty)
      throw new IllegalArgumentException("Create SQLStructure: definitions must contain at least one definition")

    val result = factory.createSQLStructure
    result.name = name
    result.definitions.addAll(definitions)

    return result 
  }

  def SQLColumnDefinition createSQLColumnDefinition(String name, SQLType type)
  {
    if (name === null)
      throw new IllegalArgumentException("Create SQLColumnDefinition: name can't be null")

    if (type === null)
      throw new IllegalArgumentException("Create SQLColumnDefinition: type can't be null")

    val result = factory.createSQLColumnDefinition
    result.name = name
    result.type = type

    return result
  }

  def SQLColumnDefinition createSQLColumnDefinition(String name, SQLType type, String constraint, SQLReferenceTarget reference)
  {
    val result = createSQLColumnDefinition(name, type)

    if (constraint !== null)
      result.constraint = constraint

    if (reference !== null)
      result.refs = reference

    return result
  }

  def SQLPrimaryConstraintDefinition createSQLPrimaryConstraintDefinition(String name, EList<String> colNames)
  {
    if (colNames === null || colNames.empty)
      throw new IllegalArgumentException("Create SQLPrimaryConstraintDefinition: colnames must contain at least one name")

    val result = factory.createSQLPrimaryConstraintDefinition
    result.colNames.addAll(colNames)

    if (name !== null)
      result.name = name

    return result
  }

  def SQLForeignConstraintDefinition createSQLForeignConstraintDefinition(String name, EList<String> colNames, SQLReferenceTarget reference)
  {
    if (colNames === null || colNames.empty)
      throw new IllegalArgumentException("Create SQLForeignConstraintDefinition: colnames must contain at least one name")

    if (reference === null)
      throw new IllegalArgumentException("Create SQLForeignConstraintDefinition: reference can't be null")

    val result = factory.createSQLForeignConstraintDefinition
    result.colNames.addAll(colNames)
    result.refs = reference

    if (name !== null)
      result.name = name

    return result
  }

  def SQLUniqueConstraintDefinition createSQLUniqueConstraintDefinition(String name, EList<String> colNames)
  {
    if (colNames === null || colNames.empty)
      throw new IllegalArgumentException("Create SQLUniqueConstraintDefinition: colnames must contain at least one name")

    val result = factory.createSQLUniqueConstraintDefinition
    result.colNames.addAll(colNames)

    if (name !== null)
      result.name = name

    return result
  }

  def SQLReferenceTarget createSQLReferenceTarget(EntityDecl ref, EList<String> colNames)
  {
    if (ref === null)
      throw new IllegalArgumentException("Create SQLReferenceTarget: ref can't be null")
    if (colNames === null || colNames.empty)
      throw new IllegalArgumentException("Create SQLReferenceTarget: colnames must contain at least one name")

    val result = factory.createSQLReferenceTarget
    result.ref = ref
    result.colNames.addAll(colNames)

    return result
  }

  def SQLType createSQLType(String name)
  {
    if (name === null)
      throw new IllegalArgumentException("Create SQLType: name can't be null")

    val result = factory.createSQLType
    result.typename = name

    return result
  }

  def String adaptId(String theId)
  {
    return theId.replace(" ", "_").replace("#", "_")
  }
}
