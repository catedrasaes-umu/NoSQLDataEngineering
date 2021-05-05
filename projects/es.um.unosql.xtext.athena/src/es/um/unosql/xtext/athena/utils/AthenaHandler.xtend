package es.um.unosql.xtext.athena.utils

import es.um.unosql.xtext.athena.athena.Feature
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.athena.StructureLiteral
import es.um.unosql.xtext.athena.athena.StructureExpr
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.ShortRelationshipDecl
import es.um.unosql.xtext.athena.athena.RegularRelationshipDecl
import es.um.unosql.xtext.athena.athena.VariationDecl
import org.eclipse.emf.common.util.EList
import java.util.ArrayList
import org.eclipse.emf.common.util.BasicEList
import es.um.unosql.xtext.athena.athena.SchemaTypeDecl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.EObject
import es.um.unosql.xtext.athena.athena.EntityDecl
import java.util.List
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.SimpleAggregationTarget
import es.um.unosql.xtext.athena.athena.AthenaSchema

class AthenaHandler
{
  val factory = new AthenaFactory

  def dispatch boolean addFeatureToSchemaType(ShortEntityDecl entity, Feature feature, boolean copy)
  {
    if (entity.structure === null)
      entity.structure = factory.createStructureLiteral(factory.createFeatureSet)

    addFeatureToStructureExpr(entity.structure, feature, copy)
  }

  def dispatch boolean addFeatureToSchemaType(RegularEntityDecl entity, Feature feature, boolean copy)
  {
    if (feature instanceof SimpleFeature && (feature as SimpleFeature).isOptional)
      throw new IllegalArgumentException("Cannot add an optional feature to a regular Entity")

    if (entity.common === null)
      entity.common = factory.createCommonSpec(factory.createStructureLiteral(factory.createFeatureSet))

    addFeatureToStructureExpr(entity.common.structure, feature, copy)
  }

  def dispatch boolean addFeatureToSchemaType(ShortRelationshipDecl relationship, Feature feature, boolean copy)
  {
    if (relationship.structure === null)
      relationship.structure = factory.createStructureLiteral(factory.createFeatureSet)

    addFeatureToStructureExpr(relationship.structure, feature, copy)
  }

  def dispatch boolean addFeatureToSchemaType(RegularRelationshipDecl relationship, Feature feature, boolean copy)
  {
    if (feature instanceof SimpleFeature && (feature as SimpleFeature).isOptional)
      throw new IllegalArgumentException("Cannot add an optional feature to a regular Relationship")

    if (relationship.common === null)
      relationship.common = factory.createCommonSpec(factory.createStructureLiteral(factory.createFeatureSet))

    addFeatureToStructureExpr(relationship.common.structure, feature, copy)
  }

  private def boolean addFeatureToStructureExpr(StructureExpr struct, Feature feature, boolean copy)
  {
    if (!(struct instanceof StructureLiteral))
      throw new IllegalArgumentException("Add Feature to StructureExpr: Cannot add a Feature to something that is not a StructureLiteral")

    (struct as StructureLiteral).spec.features.add(
      copy ?
        EcoreUtil.copy(feature)
       :
        feature
    )
  }

  def dispatch List<Feature> getFeaturesFromSchemaType(ShortEntityDecl entity)
  {
    if (entity.structure !== null)
      return (entity.structure as StructureLiteral).spec.features

    return new BasicEList<Feature>()
  }

  def dispatch List<Feature> getFeaturesFromSchemaType(RegularEntityDecl entity)
  {
    if (entity.common !== null)
      return ((entity.common).structure as StructureLiteral).spec.features

    return new BasicEList<Feature>()
  }

  def dispatch List<Feature> getFeaturesFromSchemaType(ShortRelationshipDecl relationship)
  {
    if (relationship.structure !== null)
      return (relationship.structure as StructureLiteral).spec.features

    return new BasicEList<Feature>()
  }

  def dispatch List<Feature> getFeaturesFromSchemaType(RegularRelationshipDecl relationship)
  {
    if (relationship.common !== null)
      return ((relationship.common).structure as StructureLiteral).spec.features

    return new BasicEList<Feature>()
  }

  def List<Feature> getFeaturesFromVariation(VariationDecl variation)
  {
    if (variation.structure !== null)
      return (variation.structure as StructureLiteral).spec.features

    return new BasicEList<Feature>()
  }

  def dispatch List<SimpleFeature> getSimpleFeatureFromSchemaType(ShortEntityDecl schemaType, String name)
  {
    return getFeaturesFromSchemaType(schemaType).filter(SimpleFeature).filter[f | f.name.equals(name)].toList
  }

  def dispatch List<SimpleFeature> getSimpleFeatureFromSchemaType(ShortRelationshipDecl schemaType, String name)
  {
    return getFeaturesFromSchemaType(schemaType).filter(SimpleFeature).filter[f | f.name.equals(name)].toList
  }

  def dispatch List<SimpleFeature> getSimpleFeatureFromSchemaType(RegularEntityDecl schemaType, String name)
  {
    return getFeaturesFromSchemaTypeAndVariations(schemaType).filter(SimpleFeature).filter[f | f.name.equals(name)].toList
  }

  def dispatch List<SimpleFeature> getSimpleFeatureFromSchemaType(RegularRelationshipDecl schemaType, String name)
  {
    return getFeaturesFromSchemaTypeAndVariations(schemaType).filter(SimpleFeature).filter[f | f.name.equals(name)].toList
  }

  // The aim is to get, from a List of Variations, the Set of Features made by the Union of the Variation features
  // That means taking a SimpleFeature only if there is not any features with the same name on the resulting list.
  def List<Feature> getReducedFeaturesFromVariations(List<VariationDecl> list)
  {
    val result = new ArrayList<Feature>()

    for (variation : list.filter[v | v.structure !== null])
      for (feat : (variation.structure as StructureLiteral).spec.features.filter(SimpleFeature))
        if (feat instanceof ComposedReference || !result.stream.filter(f | f instanceof SimpleFeature).map(f | (f as SimpleFeature).name).anyMatch(f | f.equals(feat.name)))
          result.add(feat)

    return result
  }

  def List<Feature> getFeaturesFromSchemaTypeAndVariations(SchemaTypeDecl schemaType)
  {
    switch (schemaType)
    {
      case schemaType instanceof RegularEntityDecl:       return (getFeaturesFromSchemaType(schemaType)
        + (schemaType as RegularEntityDecl).variations.flatMap[v | getFeaturesFromVariation(v)].map[feat | feat instanceof SimpleFeature ? { (feat as SimpleFeature).optional = true; return feat } : feat]).toList
      case schemaType instanceof RegularRelationshipDecl: return (getFeaturesFromSchemaType(schemaType)
        + (schemaType as RegularRelationshipDecl).variations.flatMap[v | getFeaturesFromVariation(v)].map[feat | feat instanceof SimpleFeature ? { (feat as SimpleFeature).optional = true; return feat } : feat]).toList
      default:                                            return getFeaturesFromSchemaType(schemaType)
    }
  }

  def List<Feature> getReducedFeaturesFromSchemaTypeAndVariations(SchemaTypeDecl schemaType)
  {
    switch (schemaType)
    {
      case schemaType instanceof RegularEntityDecl:       return (getFeaturesFromSchemaType(schemaType)
        + getReducedFeaturesFromVariations((schemaType as RegularEntityDecl).variations).map[feat | feat instanceof SimpleFeature ? { (feat as SimpleFeature).optional = true; return feat } : feat]).toList
      case schemaType instanceof RegularRelationshipDecl: return (getFeaturesFromSchemaType(schemaType)
        + getReducedFeaturesFromVariations((schemaType as RegularRelationshipDecl).variations).map[feat | feat instanceof SimpleFeature ? { (feat as SimpleFeature).optional = true; return feat } : feat]).toList
      default:                                            return getFeaturesFromSchemaType(schemaType)
    }
  }

  def List<Feature> getFeaturesFromAggregation(EList<EObject> obj)
  {
    if (obj.head instanceof EntityDecl)
      return getFeaturesFromSchemaType(obj.head as EntityDecl)
    else
      return (getFeaturesFromSchemaType(obj.head.eContainer as EntityDecl)
        + getReducedFeaturesFromVariations(obj.map[o | o as VariationDecl]).map[feat | feat instanceof SimpleFeature ? { (feat as SimpleFeature).optional = true; return feat } : feat]).toList
  }

  def SimpleFeature getKeyFromSchemaType(SchemaTypeDecl schemaType)
  {
    return getFeaturesFromSchemaType(schemaType).filter(SimpleFeature).findFirst[feat | feat.key]
  }

  def dispatch boolean removeSimpleFeatureFromSchemaType(ShortEntityDecl entity, String name)
  {
    getFeaturesFromSchemaType(entity).removeIf(f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name))
  }

  def dispatch boolean removeSimpleFeatureFromSchemaType(RegularEntityDecl entity, String name)
  {
    for (variation : entity.variations)
      getFeaturesFromVariation(variation).removeIf(f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name))

    getFeaturesFromSchemaType(entity).removeIf(f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name))
  }

  def dispatch boolean removeSimpleFeatureFromSchemaType(ShortRelationshipDecl relationship, String name)
  {
    getFeaturesFromSchemaType(relationship).removeIf(f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name))
  }

  def dispatch boolean removeSimpleFeatureFromSchemaType(RegularRelationshipDecl relationship, String name)
  {
    for (variation : relationship.variations)
      getFeaturesFromVariation(variation).removeIf(f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name))

    getFeaturesFromSchemaType(relationship).removeIf(f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name))
  }

  def String getSimpleAggregationTargetName(SimpleAggregationTarget aggr)
  {
    aggr.aggr.head instanceof EntityDecl ? (aggr.aggr.head as EntityDecl).name : ((aggr.aggr.head as VariationDecl).eContainer as EntityDecl).name
  }

  def List<SimpleFeature> getReducedSimpleFeaturesFrom(SchemaTypeDecl sch1, SchemaTypeDecl sch2)
  {
    val result = new ArrayList<SimpleFeature>()

    result.addAll(getReducedFeaturesFromSchemaTypeAndVariations(sch1).filter(SimpleFeature))
    result.addAll(getReducedFeaturesFromSchemaTypeAndVariations(sch2).filter(SimpleFeature).filter[feat | !result.stream.map(f | f.name).anyMatch(f | f.equals(feat.name))])

    return result
  }

  def EntityDecl getEntityDecl(AthenaSchema schema, String name)
  {
    return schema.entities.findFirst[e | e.name.equals(name)]
  }
}
