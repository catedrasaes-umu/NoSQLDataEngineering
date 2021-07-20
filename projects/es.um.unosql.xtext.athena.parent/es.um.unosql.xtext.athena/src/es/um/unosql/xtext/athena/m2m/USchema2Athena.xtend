package es.um.unosql.xtext.athena.m2m

import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.uNoSQLSchema.uNoSQLSchema
import es.um.unosql.uNoSQLSchema.EntityType
import es.um.unosql.uNoSQLSchema.StructuralVariation
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.StructureLiteral
import es.um.unosql.uNoSQLSchema.Attribute
import es.um.unosql.uNoSQLSchema.Reference
import es.um.unosql.uNoSQLSchema.Aggregate
import es.um.unosql.uNoSQLSchema.Key
import es.um.unosql.uNoSQLSchema.Feature
import es.um.unosql.uNoSQLSchema.Null
import es.um.unosql.uNoSQLSchema.PrimitiveType
import es.um.unosql.uNoSQLSchema.PList
import es.um.unosql.uNoSQLSchema.PSet
import es.um.unosql.uNoSQLSchema.PTuple
import es.um.unosql.uNoSQLSchema.PMap
import es.um.unosql.uNoSQLSchema.SchemaType
import java.util.HashMap
import java.util.Map
import es.um.unosql.uNoSQLSchema.RelationshipType
import es.um.unosql.xtext.athena.athena.EntityDecl
import org.eclipse.emf.common.util.BasicEList
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.uNoSQLSchema.StructuralFeature
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.SchemaTypeDecl
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.DataType

class USchema2Athena
{
  AthenaFactory factory
  Map<SchemaType, SchemaTypeDecl> schemaTypeMap
  Map<StructuralVariation, VariationDecl> variationMap

  new()
  {
    factory = new AthenaFactory
    schemaTypeMap = new HashMap<SchemaType, SchemaTypeDecl>
    variationMap = new HashMap<StructuralVariation, VariationDecl>
  }

  /**
   * M2M transformation consists on these steps:
   *   1-Create USchema EntityTypes and RelationshipTypes with its variations. No Features are created.
   *   2-Create the parent hierarchy between SchemaTypes.
   *   3-Catch all common features, and copy them to the Common section of each SchemaType.
   *   4- Rinse and repeat for each non-common features.
   */
  def AthenaSchema m2m(uNoSQLSchema schema)
  {
    val newSchema = factory.createAthenaSchema(schema.name)

    for (EntityType e : schema.entities)
      newSchema.entities.add(createNewEntityDecl(e))

    for (RelationshipType r : schema.relationships)
      newSchema.relationships.add(createNewRelationshipDecl(r))

    // Now parents structure
    for (SchemaType sType : (schema.entities + schema.relationships).filter[st | !st.parents.isEmpty])
      schemaTypeMap.get(sType).parents.addAll(sType.parents.map[p | schemaTypeMap.get(p)])

    for (SchemaType sType : schema.entities + schema.relationships)
    {
      // Now about the common features: Grab non-optional StructuralFeatures, and References and Keys binded to a non-optional Attribute
      // Now reject from there the binded Attributes, and only keep References and Keys.
      val commonFeatures = sType.variations.head.features.filter[feat |
        (feat instanceof StructuralFeature && !(feat as StructuralFeature).isOptional) ||
        (feat instanceof Reference && (!(feat as Reference).isFeaturedBy.empty || !(feat as Reference).attributes.head.isOptional)) ||
        (feat instanceof Key && !(feat as Key).attributes.head.isOptional)]
        .reject[feat | feat instanceof Attribute && ((feat as Attribute).key !== null || !(feat as Attribute).references.empty)]

      if (!commonFeatures.isEmpty)
      {
        val entityDecl = schemaTypeMap.get(sType) as RegularEntityDecl
        entityDecl.common = factory.createCommonSpec(factory.createStructureLiteral(factory.createFeatureSet))

        for (Feature feat : commonFeatures)
          (entityDecl.common.structure as StructureLiteral).spec.features.add(transformFeature(feat))
      }

      // Finally onto the optional features...
      for (StructuralVariation v : sType.variations)
      // Process each non-reference, non-key attribute, then aggregates, references and keys.
      {
        val optionalFeatures = v.features.reject[f |
          (f instanceof Attribute && (!(f as Attribute).references.isEmpty || (f as Attribute).key !== null)) ||
          (f instanceof StructuralFeature && !(f as StructuralFeature).isOptional) ||
          (f instanceof Reference && (!(f as Reference).isFeaturedBy.empty || !(f as Reference).attributes.head.isOptional)) ||
          (f instanceof Key && !(f as Key).attributes.head.isOptional)]
          .reject[feat | feat instanceof Attribute && ((feat as Attribute).key !== null || !(feat as Attribute).references.empty)]

        if (!optionalFeatures.isEmpty)
        {
          val varDecl = variationMap.get(v)
          varDecl.structure = factory.createStructureLiteral(factory.createFeatureSet)

          for (Feature feat : optionalFeatures)
          {
            var transformedFeature = transformFeature(feat)
            if (transformedFeature instanceof SimpleFeature)
              (transformedFeature as SimpleFeature).optional = false
            // We set the optional property to false because each feature defined in a variation is optional by default.
            (varDecl.structure as StructureLiteral).spec.features.add(transformedFeature)
          }
        }
      }
    }

    return newSchema
  }

  private def createNewEntityDecl(EntityType entity)
  {
    val result = factory.createRegularEntityDecl(entity.name, entity.root)
    schemaTypeMap.put(entity, result)

    for (StructuralVariation v : entity.variations)
    {
      val newVar = factory.createVariationDecl(v.variationId.toString)
      result.variations.add(newVar)
      variationMap.put(v, newVar)
    }

    return result
  }

  private def createNewRelationshipDecl(RelationshipType rel)
  {
    val result = factory.createRegularRelationshipDecl(rel.name)
    schemaTypeMap.put(rel, result)

    for (StructuralVariation v : rel.variations)
    {
      val newVar = factory.createVariationDecl(v.variationId.toString)
      result.variations.add(newVar)
      variationMap.put(v, newVar)
    }

    return result
  }

  private def dispatch es.um.unosql.xtext.athena.athena.Feature transformFeature(Attribute attr)
  {
    val result = factory.createSimpleFeature(attr.name, transformDataType(attr.type), attr.isOptional)

    return result
  }

  private def dispatch es.um.unosql.xtext.athena.athena.Feature transformFeature(Reference ref)
  {
    if (ref.attributes.size > 1)
      return factory.createComposedReference(new BasicEList(ref.attributes.map[attr | attr.name]), schemaTypeMap.get(ref.refsTo) as EntityDecl)
    else
    {
      val refName = ref.name !== null && !ref.name.empty ? ref.name : ref.attributes.head.name

      if (ref.isFeaturedBy.empty)
      {
        val refType = ref.attributes.head.type instanceof PList ? transformDataType((ref.attributes.head.type as PList).elementType) : transformDataType(ref.attributes.head.type)
        val result = factory.createSimpleFeature(refName, factory.createSimpleRef(
          schemaTypeMap.get(ref.refsTo) as EntityDecl,
          getMultiplicity(ref.lowerBound, ref.upperBound),
          refType as es.um.unosql.xtext.athena.athena.PrimitiveType), ref.attributes.head.isOptional)

        return result
      }
      else
      {
        return factory.createSimpleFeature(refName, factory.createSimpleRef(
          schemaTypeMap.get(ref.refsTo) as EntityDecl,
          getMultiplicity(ref.lowerBound, ref.upperBound),
          new BasicEList(ref.isFeaturedBy.map[f | variationMap.get(f)])))
      }
    }
  }

  private def dispatch es.um.unosql.xtext.athena.athena.Feature transformFeature(Aggregate aggr)
  {
    val result = factory.createSimpleFeature(aggr.name, factory.createSimpleAggr(
      new BasicEList(aggr.aggregates.map[v | variationMap.get(v)]),
      getMultiplicity(aggr.lowerBound, aggr.upperBound)), aggr.isOptional)

    return result
  }

  private def dispatch es.um.unosql.xtext.athena.athena.Feature transformFeature(Key key)
  {
    if (key.attributes.size != 1)
      throw new IllegalArgumentException("Error: Keys of multiple attributes are not supported right now.")
    else
    {
      val result = transformFeature(key.attributes.head) as SimpleFeature
      result.key = true

      return result
    }
  }

  private def dispatch DataType transformDataType(Null aNull)
  {
    return factory.createNull
  }

  private def dispatch DataType transformDataType(PrimitiveType type)
  {
    return factory.createUnrestrictedPrimitiveType(uNoSQLTypeToAthenaType(type))
  }

  private def dispatch DataType transformDataType(PList list)
  {
    return factory.createList(transformDataType(list.elementType))
  }

  private def dispatch DataType transformDataType(PSet set)
  {
    return factory.createSet(transformDataType(set.elementType))
  }

  private def dispatch DataType transformDataType(PTuple tuple)
  {
    return factory.createTuple(tuple.elements.map[e | transformDataType(e)])
  }

  private def dispatch DataType transformDataType(PMap map)
  {
    return factory.createMap(transformDataType(map.keyType) as es.um.unosql.xtext.athena.athena.PrimitiveType, transformDataType(map.valueType))
  }

  private def String getMultiplicity(int lBound, int uBound)
  {
    if (lBound == 1 && uBound == 1)
      return "+"
    else if (uBound == 1 || uBound == -1)
      return "*"
    else
      throw new IllegalArgumentException("Error: Can't create multiplicity for upper bound different of 1 and -1")
    // Lower bound == 1 ? return "+" : return "*"
  }

  private def uNoSQLTypeToAthenaType(PrimitiveType type)
  {
    switch (type.name.toLowerCase)
    {
      case "objectid": return "Identifier"
      default:         return type.name
    }
  }
}
