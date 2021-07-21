package es.um.unosql.xtext.athena.m2m

import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.FeatureSetDecl
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.StructureLiteral
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.athena.ShortRelationshipDecl
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.RegularRelationshipDecl
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.PrimitiveType
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.utils.ExpressionSimplifier
import es.um.unosql.xtext.athena.athena.EntityDecl
import org.eclipse.emf.common.util.BasicEList
import java.util.Arrays
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.Feature
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.InnerStructureLiteralArray
import es.um.unosql.xtext.athena.athena.InnerStructureLiteral
import es.um.unosql.xtext.athena.utils.compare.CompareFeature
import es.um.unosql.xtext.athena.validation.m2m.NormalizerValidator
import es.um.unosql.xtext.athena.athena.SchemaTypeDecl
import es.um.unosql.xtext.athena.athena.RelationshipDecl

class AthenaNormalizer
{
  val simplifier = new ExpressionSimplifier(true)
  val handler = new AthenaHandler
  val factory = new AthenaFactory

  def AthenaSchema m2m(AthenaSchema schema)
  {
    resolveExtensions(schema)
    normalizeExpressions(schema) // Keep in mind this might lead to stuff without features: struct str1 ${a}, entity e1 str1 - ${a}
    normalizeInnerStructureArrays(schema)
    normalizeInnerStructures(schema)
    insertReferenceTypes(schema)

    // Actually we do not increment schema version because this schema is equivalent to the input one.

    new NormalizerValidator().validate(schema)

    return schema
  }


  //Solves parents. Each parent is solved so Entity1::EntityParent is solved as Entity1 = Entity1 U EntityParent
  private def void resolveExtensions(AthenaSchema schema)
  {
    for (EntityDecl eType : schema.entities.filter[e | !e.parents.empty])
      for (SchemaTypeDecl parent : eType.parents)
        handler.addFeaturesAsUnionToSchemaType(eType, handler.getFeaturesInSchemaType(parent))

    for (RelationshipDecl relType : schema.relationships.filter[e | !e.parents.empty])
      for (SchemaTypeDecl parent : relType.parents)
        handler.addFeaturesAsUnionToSchemaType(relType, handler.getFeaturesInSchemaType(parent))

    schema.entities.forEach[e | e.parents.clear]
  }

  //[{...}, {...}, {...}]
  private def void normalizeInnerStructureArrays(AthenaSchema schema)
  {
    val arrays = EcoreUtil2.getAllContentsOfType(schema, InnerStructureLiteralArray)

    for (InnerStructureLiteralArray array : arrays)
    {
      val simpleFeat = array.eContainer as SimpleFeature

      for (InnerStructureLiteral innerStruct : array.structure)
      {
        val result = transformInnerStructureLiteral(schema, simpleFeat.name.toFirstUpper, innerStruct)

        if (result instanceof VariationDecl)
        {
          if (simpleFeat.type instanceof SimpleAggregateTarget && !((simpleFeat.type as SimpleAggregateTarget).aggr as EList<EObject>).filter(VariationDecl).exists[v | v.name.equals(result.name)])
            ((simpleFeat.type as SimpleAggregateTarget).aggr as EList<EObject>).add(result) // We checked that the Variation was not already aggregated.
          else
            simpleFeat.type = factory.createSimpleAggr(new BasicEList(Arrays.asList(result)), "+")
        }
        if (result instanceof EntityDecl && !(simpleFeat.type instanceof SimpleAggregateTarget)) // The second comparison is to check that the Aggregate was not already initialized
          simpleFeat.type = factory.createSimpleAggr(result, "+")
      }
    }
  }

  //{...}
  private def void normalizeInnerStructures(AthenaSchema schema)
  {
    var InnerStructureLiteral innerStruct = EcoreUtil2.getAllContentsOfType(schema, InnerStructureLiteral).head

    while (innerStruct !== null)
    {
      val simpleFeat = innerStruct.eContainer as SimpleFeature
      val result = transformInnerStructureLiteral(schema, simpleFeat.name.toFirstUpper, innerStruct)

      if (result instanceof VariationDecl)
        simpleFeat.type = factory.createSimpleAggr(new BasicEList(Arrays.asList(result)), "&")
      if (result instanceof EntityDecl)
        simpleFeat.type = factory.createSimpleAggr(result, "&")

      innerStruct = EcoreUtil2.getAllContentsOfType(schema, InnerStructureLiteral).head
    }
  }

  // It may happen in: [{a1: String, a2: Number}, {a1: String, ?a3: Boolean}] results in:
  /*
   * entity Inner_array_aggr
   * {
   *   common { a1: String, a2: Number }
   *   variation 1
   *   variation 2 { a3: Boolean }
   * }
   * But actually a2 was never an option for the second object! Thus it should have been defined as optional.
   * That is just too much. We are not THAT flexible
   */
  private def EObject transformInnerStructureLiteral(AthenaSchema schema, String entityName, InnerStructureLiteral innerStruct)
  {
    val theEntity = handler.getEntityDecl(schema, entityName)

    // Could not find an already created entity. Create a new one, add a variation to it, and return an Aggregate pointing to that.
    if (theEntity === null)
    {
      val newEntity = factory.createRegularEntityDecl(entityName, false)
      schema.entities.add(newEntity)

      val newVariation = factory.createVariationDecl(1)
      (newEntity as RegularEntityDecl).variations.add(newVariation)

      if (!innerStruct.spec.features.empty)
        newVariation.structure = factory.createStructureLiteral(factory.createFeatureSet(innerStruct.spec.features))

      return newVariation
    }
    // Could find a matching Entity
    else
    {
      if (theEntity instanceof ShortEntityDecl)
      {
        for (Feature feat : innerStruct.spec.features.filter[f | f instanceof ComposedReference || (f instanceof SimpleFeature && handler.getSimpleFeatureInSchemaType(theEntity, (f as SimpleFeature).name) === null)])
          handler.addFeatureToSchemaType(theEntity, EcoreUtil2.copy(feat))

        return theEntity
      }
      if (theEntity instanceof RegularEntityDecl)
      {
        if (theEntity.common !== null && !(theEntity.common.structure as StructureLiteral).spec.features.empty)
          throw new IllegalStateException("During InnerStructureLiteral transformation a RegularEntityDecl was found with a common section: " + theEntity.name)

        val variationList = (theEntity as RegularEntityDecl).variations
        val matchingVar = findVariation(variationList, innerStruct.spec.features)

        if (matchingVar === null) // There is a matching RegularEntityDecl, but there are no variations on it or there wasnt any match. Create a new one.
        {
          val newVariation = factory.createVariationDecl((variationList === null || variationList.empty) ? 1 : Integer.parseInt(variationList.last.name) + 1)
          (theEntity as RegularEntityDecl).variations.add(newVariation)

          if (!innerStruct.spec.features.isEmpty)
            newVariation.structure = factory.createStructureLiteral(factory.createFeatureSet(innerStruct.spec.features))

            return newVariation
        }

        return matchingVar
      }
    }
  }

  private def VariationDecl findVariation(EList<VariationDecl> varList, Iterable<Feature> features)
  {
    if (varList === null)
      return null

    val comparer = new CompareFeature()
    for (variation : varList)
    {
      val vFeats = handler.getFeaturesInVariation(variation)
      if (vFeats.size === features.size && (vFeats.size === 0 || vFeats.forall[f1 | features.findFirst[f2 | comparer.compare(f1, f2)] !== null]))
        return variation
    }

    return null
  }

  private def void normalizeExpressions(AthenaSchema schema)
  {
    for (FeatureSetDecl fsetdecl : schema.featureSets)
      fsetdecl.structure = factory.createStructureLiteral(simplifier.simplify(fsetdecl))

    schema.entities.filter(ShortEntityDecl).forEach[e | e.structure = factory.createStructureLiteral(simplifier.simplify(e))]

    for (RegularEntityDecl entity : schema.entities.filter(RegularEntityDecl))
    {
      if (entity.common !== null)
        entity.common.structure = factory.createStructureLiteral(simplifier.simplify(entity))
      for (VariationDecl v : entity.variations.filter[v | v.structure !== null])
        v.structure = factory.createStructureLiteral(simplifier.simplify(v))
    }

    for (ShortRelationshipDecl rel : schema.relationships.filter(ShortRelationshipDecl))
      rel.structure = factory.createStructureLiteral(simplifier.simplify(rel))

    for (RegularRelationshipDecl rel : schema.relationships.filter(RegularRelationshipDecl))
    {
      if (rel.common !== null)
        rel.common.structure = factory.createStructureLiteral(simplifier.simplify(rel))
      for (VariationDecl v : rel.variations.filter[v | v.structure !== null])
        v.structure = factory.createStructureLiteral(simplifier.simplify(v))
    }
  }

  private def void insertReferenceTypes(AthenaSchema schema)
  {
    val references = EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget)

    for (SimpleReferenceTarget ref : references.filter[ref | ref.type === null])
    {
      val keyFeat = handler.getKeyInSchemaType(ref.ref)

      if (keyFeat !== null && keyFeat.type !== null && keyFeat.type instanceof PrimitiveType)
        ref.type = EcoreUtil2.copy(keyFeat.type) as PrimitiveType
    }
  }
}
