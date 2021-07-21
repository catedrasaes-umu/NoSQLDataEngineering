package es.um.unosql.xtext.athena.m2m

import es.um.unosql.uNoSQLSchema.Attribute
import es.um.unosql.uNoSQLSchema.EntityType
import es.um.unosql.uNoSQLSchema.SchemaType
import es.um.unosql.uNoSQLSchema.StructuralVariation
import es.um.unosql.uNoSQLSchema.uNoSQLSchema
import es.um.unosql.utils.UNoSQLFactory
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.Feature
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.OptionPrimitiveType
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.RegularRelationshipDecl
import es.um.unosql.xtext.athena.athena.RelationshipDecl
import es.um.unosql.xtext.athena.athena.SchemaTypeDecl
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.athena.ShortRelationshipDecl
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.athena.StructureLiteral
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.VariationDecl
import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap
import java.util.Map

class Athena2USchema
{
  UNoSQLFactory factory
  Map<SchemaTypeDecl, SchemaType> schemaTypeMap
  Map<VariationDecl, StructuralVariation> variationMap

  new()
  {
    factory = new UNoSQLFactory
    schemaTypeMap = new HashMap<SchemaTypeDecl, SchemaType>
    variationMap = new HashMap<VariationDecl, StructuralVariation>
  }

  def uNoSQLSchema m2m(AthenaSchema schema)
  {
    val nSchema = new AthenaNormalizer().m2m(schema)
    val newSchema = factory.createUNoSQLSchema(nSchema.id.name)

    for (EntityDecl e : nSchema.entities)
      newSchema.entities.add(createNewEntityType(e))

    for (RelationshipDecl r : nSchema.relationships)
      newSchema.relationships.add(createNewRelationshipType(r))

    // Now parents structure
    for (SchemaTypeDecl sType : (nSchema.entities + nSchema.relationships).filter[st | !st.parents.isEmpty])
      schemaTypeMap.get(sType).parents.addAll(sType.parents.map[p | schemaTypeMap.get(p)])

    for (SchemaTypeDecl sType : nSchema.entities + nSchema.relationships)
    {
      fillCommonFeatures(sType)
      fillOptionalFeatures(sType)
    }

    return newSchema
  }

  private def createNewEntityType(EntityDecl entity)
  {
    val result = factory.createEntityType(entity.name, entity.root)
    schemaTypeMap.put(entity, result)

    if (entity instanceof ShortEntityDecl)
      result.variations.add(factory.createStructuralVariation(1))
    else
    {
      for (VariationDecl v : (entity as RegularEntityDecl).variations)
      {
        val newVar = factory.createStructuralVariation(Integer.parseInt(v.name))
        result.variations.add(newVar)
        variationMap.put(v, newVar)
      }
    }

    return result
  }

  private def createNewRelationshipType(RelationshipDecl rel)
  {
    val result = factory.createRelationshipType(rel.name)
    schemaTypeMap.put(rel, result)

    if (rel instanceof ShortRelationshipDecl)
      result.variations.add(factory.createStructuralVariation(1))
    else
    {
      for (VariationDecl v : (rel as RegularRelationshipDecl).variations)
      {
        val newVar = factory.createStructuralVariation(Integer.parseInt(v.name))
        result.variations.add(newVar)
        variationMap.put(v, newVar)
      }
    }

    return result
  }

  private def dispatch fillCommonFeatures(ShortEntityDecl entity)
  {
    if (entity.structure === null)
      return

    val sType = schemaTypeMap.get(entity)
    for (Feature feat : (entity.structure as StructureLiteral).spec.features.filter[feat | feat instanceof ComposedReference || !(feat as SimpleFeature).optional])
      sType.variations.forEach[v | insertFeatureInVariation(feat, v, false)]
  }

  private def dispatch fillCommonFeatures(RegularEntityDecl entity)
  {
    if (entity.common === null || entity.common.structure === null)
      return

    val sType = schemaTypeMap.get(entity)
    for (Feature feat : (entity.common.structure as StructureLiteral).spec.features)
      sType.variations.forEach[v | insertFeatureInVariation(feat, v, false)]
  }

  private def dispatch fillCommonFeatures(ShortRelationshipDecl rel)
  {
    if (rel.structure === null)
      return

    val sType = schemaTypeMap.get(rel)
    for (Feature feat : (rel.structure as StructureLiteral).spec.features.filter[feat | feat instanceof ComposedReference || !(feat as SimpleFeature).optional])
      sType.variations.forEach[v | insertFeatureInVariation(feat, v, false)]
  }

  private def dispatch fillCommonFeatures(RegularRelationshipDecl rel)
  {
    if (rel.common === null || rel.common.structure === null)
      return

    val sType = schemaTypeMap.get(rel)
    for (Feature feat : (rel.common.structure as StructureLiteral).spec.features)
      sType.variations.forEach[v | insertFeatureInVariation(feat, v, false)]
  }

  private def dispatch fillOptionalFeatures(ShortEntityDecl entity)
  {
    val optionalFeatures = (entity.structure as StructureLiteral).spec.features.filter(SimpleFeature).filter[feat | feat.optional].toList

    for (Feature feat : optionalFeatures)
      schemaTypeMap.get(entity).variations.forEach[v | insertFeatureInVariation(feat, v, true)]
  }

  private def dispatch fillOptionalFeatures(RegularEntityDecl entity)
  {
    for (VariationDecl v : entity.variations.filter[v | v.structure !== null])
      for (Feature feat : (v.structure as StructureLiteral).spec.features)
        insertFeatureInVariation(feat, variationMap.get(v), true)
  }

  private def dispatch fillOptionalFeatures(ShortRelationshipDecl rel)
  {
    val optionalFeatures = (rel.structure as StructureLiteral).spec.features.filter(SimpleFeature).filter[feat | feat.optional].toList

    for (Feature feat : optionalFeatures)
      schemaTypeMap.get(rel).variations.forEach[v | insertFeatureInVariation(feat, v, true)]
  }

  private def dispatch fillOptionalFeatures(RegularRelationshipDecl rel)
  {
    for (VariationDecl v : rel.variations.filter[v | v.structure !== null])
      for (Feature feat : (v.structure as StructureLiteral).spec.features)
        insertFeatureInVariation(feat, variationMap.get(v), true)
  }

  private def dispatch void insertFeatureInVariation(ComposedReference cRef, StructuralVariation v, boolean optional)
  {
    val namesList = new ArrayList<Attribute>
    for (String name : cRef.names)
      namesList.add(factory.createAttribute(name, factory.createPrimitiveType("String"), false))
    val composedRef = factory.createReference(null, 1, 1, schemaTypeMap.get(cRef.target.ref) as EntityType)
    composedRef.attributes.addAll(namesList)

    for (Attribute attr : namesList)
    {
      attr.references.add(composedRef)
      v.features.add(attr)
      v.structuralFeatures.add(attr)
    }

    v.features.add(composedRef)
    v.logicalFeatures.add(composedRef)
  }

  private def dispatch void insertFeatureInVariation(SimpleFeature sFeat, StructuralVariation v, boolean optional)
  {
    switch (sFeat)
    {
      case sFeat.isKey && (sFeat.type === null || sFeat.type instanceof DataType):
      {
        val newFeat = factory.createAttribute(sFeat.name, sFeat.type === null ? factory.createNull : transformDataType(sFeat.type as DataType), false)
        val newKey = factory.createKey(null)
        newFeat.key = newKey
        newKey.attributes.add(newFeat)
  
        v.features.add(newFeat)
        v.structuralFeatures.add(newFeat)
        v.features.add(newKey)
        v.logicalFeatures.add(newKey)
      }
      case sFeat.type instanceof SimpleAggregateTarget:
      {
        val simpleAggr = sFeat.type as SimpleAggregateTarget
        val aggregated = (simpleAggr.aggr.head instanceof VariationDecl)? simpleAggr.aggr.map[x | variationMap.get(x)] : Arrays.asList(schemaTypeMap.get(simpleAggr.aggr.head).variations.head)
        val newFeat = factory.createAggregate(sFeat.name, this.getLowerBound(simpleAggr.multiplicity), this.getUpperBound(simpleAggr.multiplicity), aggregated)
        newFeat.optional = optional

        v.features.add(newFeat)
        v.structuralFeatures.add(newFeat)
      }
      case sFeat.type === null || sFeat.type instanceof DataType:
      {
        val newFeat = factory.createAttribute(sFeat.name, sFeat.type === null ? factory.createNull : transformDataType(sFeat.type as DataType), optional)
        v.features.add(newFeat)
        v.structuralFeatures.add(newFeat)
      }
      case sFeat.type instanceof SimpleReferenceTarget:
      {
        val simpleRef = sFeat.type as SimpleReferenceTarget
        val theType = this.getUpperBound(simpleRef.multiplicity) == -1 ? factory.createPList(transformDataType(simpleRef.type)) : transformDataType(simpleRef.type)
        val newFeat = factory.createAttribute(sFeat.name, theType, optional)
        val newRef = factory.createReference(null,
          this.getLowerBound(simpleRef.multiplicity),
          this.getUpperBound(simpleRef.multiplicity),
          schemaTypeMap.get(simpleRef.ref) as EntityType)
  
        newFeat.references.add(newRef)
        newRef.attributes.add(newFeat)
        
        if (simpleRef.featuredBy !== null)
          simpleRef.featuredBy.forEach[o | (o instanceof VariationDecl)?
            newRef.isFeaturedBy.add(variationMap.get(o))
            :
            newRef.isFeaturedBy.add(schemaTypeMap.get(o).variations.head)]

        v.features.add(newFeat)
        v.structuralFeatures.add(newFeat)
        v.features.add(newRef)
        v.logicalFeatures.add(newRef)
      }
      default: throw new IllegalArgumentException("Error: Couldn't find what is: " + sFeat)
    }
  }

  private def dispatch es.um.unosql.uNoSQLSchema.DataType transformDataType(PrimitiveType pType)
  {
    pType instanceof SinglePrimitiveType ?
      return factory.createPrimitiveType(pType.typename)
      :
      return factory.createPrimitiveType((pType as OptionPrimitiveType).options.head.typename)
  }

  private def dispatch es.um.unosql.uNoSQLSchema.DataType transformDataType(Null aNull)
  {
    return factory.createNull
  }

  private def dispatch es.um.unosql.uNoSQLSchema.DataType transformDataType(List list)
  {
    list.elementType === null ?
      return factory.createPList(factory.createNull)
      :
      return factory.createPList(transformDataType(list.elementType))
  }

  private def dispatch es.um.unosql.uNoSQLSchema.DataType transformDataType(Set set)
  {
    set.elementType === null ?
      return factory.createPSet(factory.createNull)
      :
      return factory.createPSet(transformDataType(set.elementType))
  }

  private def dispatch es.um.unosql.uNoSQLSchema.DataType transformDataType(Tuple tuple)
  {
    tuple.elements === null || tuple.elements.isEmpty ?
      return factory.createPTuple(factory.createNull)
      :
      return factory.createPTuple(tuple.elements.map[e | transformDataType(e)])
  }

  private def dispatch es.um.unosql.uNoSQLSchema.DataType transformDataType(es.um.unosql.xtext.athena.athena.Map map)
  {
    val theKey = (map.keyType === null) ? factory.createPrimitiveType("String") : transformDataType(map.keyType) as es.um.unosql.uNoSQLSchema.PrimitiveType
    val theValue = (map.valueType === null) ? factory.createNull : transformDataType(map.valueType)

    return factory.createPMap(theKey, theValue)
  }

  private def int getUpperBound(String multiplicity)
  {
    if (multiplicity === null || multiplicity.equals("*") || multiplicity.equals("+"))
      return -1
    else if (multiplicity.equals("?") || multiplicity.equals("&"))
      return 1
    else
      return 0 // Uh-oh
  }

  private def int getLowerBound(String multiplicity)
  {
    if (multiplicity === null || multiplicity.equals("?") || multiplicity.equals("*"))
      return 0
    else if (multiplicity.equals("&") || multiplicity.equals("+"))
      return 1
    else
      return 0
  }
}
