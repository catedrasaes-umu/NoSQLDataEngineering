package es.um.unosql.xtext.athena.utils

import es.um.unosql.xtext.athena.athena.FeatureSet
import es.um.unosql.xtext.athena.athena.StructureExpr
import es.um.unosql.xtext.athena.utils.compare.CompareFeature
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.utils.compare.CompareComposedReference
import es.um.unosql.xtext.athena.utils.compare.CompareSimpleFeature
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.StructureTerm
import java.util.ArrayList
import es.um.unosql.xtext.athena.athena.Feature
import es.um.unosql.xtext.athena.athena.FeatureSetDecl
import es.um.unosql.xtext.athena.athena.TopLevelStructureDefiningElementDeclRef
import java.util.HashMap
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.RelationshipDecl
import es.um.unosql.xtext.athena.athena.ShortRelationshipDecl
import es.um.unosql.xtext.athena.athena.RegularRelationshipDecl
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.VariationDeclRef
import es.um.unosql.xtext.athena.athena.StructureLiteral
import java.util.Map
import es.um.unosql.xtext.athena.athena.SQLStructure
import es.um.unosql.xtext.athena.athena.SQLColumnDefinition
import es.um.unosql.xtext.athena.athena.SQLType
import es.um.unosql.xtext.athena.athena.SQLConstraintDefinition
import es.um.unosql.xtext.athena.athena.SQLUniqueConstraintDefinition
import es.um.unosql.xtext.athena.athena.SQLPrimaryConstraintDefinition
import es.um.unosql.xtext.athena.athena.SQLForeignConstraintDefinition
import org.eclipse.xtext.EcoreUtil2

class ExpressionSimplifier
{
  val factory = new AthenaFactory

  boolean useCache

  Map<FeatureSetDecl, FeatureSet> fsMap
  Map<VariationDecl, FeatureSet> varMap
  Map<EntityDecl, FeatureSet> entMap
  Map<RelationshipDecl, FeatureSet> relMap

  new(boolean useCache)
  {
    this.useCache = useCache

    if (useCache)
    {
      fsMap = new HashMap<FeatureSetDecl, FeatureSet>
      varMap = new HashMap<VariationDecl, FeatureSet>
      entMap = new HashMap<EntityDecl, FeatureSet>
      relMap = new HashMap<RelationshipDecl, FeatureSet>
    }
  }

  def dispatch FeatureSet simplify(FeatureSetDecl fset)
  {
    if (useCache)
    {
      if (!fsMap.containsKey(fset))
        fsMap.put(fset, simplify(fset.structure))

      return fsMap.get(fset)
    }
    else
      return simplify(fset.structure)
  }

  def dispatch FeatureSet simplify(ShortRelationshipDecl relationship)
  {
    if (useCache)
    {
      if (!relMap.containsKey(relationship))
        relMap.put(relationship, simplify(relationship.structure))

      return relMap.get(relationship)
    }
    else
      return simplify(relationship.structure)
  }

  def dispatch FeatureSet simplify(RegularRelationshipDecl relationship)
  {
    if (relationship.common === null || relationship.common.structure === null)
      return factory.createFeatureSet

    if (useCache)
    {
      if (!relMap.containsKey(relationship))
        relMap.put(relationship, simplify(relationship.common.structure))

      return relMap.get(relationship)
    }
    else
      return simplify(relationship.common.structure)
  }

  def dispatch FeatureSet simplify(ShortEntityDecl entity)
  {
    if (entity.structure === null)
      return factory.createFeatureSet

    if (useCache)
    {
      if (!entMap.containsKey(entity))
        entMap.put(entity, simplify(entity.structure))
  
      return entMap.get(entity)
    }
    else
      return simplify(entity.structure)
  }

  def dispatch FeatureSet simplify(RegularEntityDecl entity)
  {
    if (entity.common === null || entity.common.structure === null)
      return factory.createFeatureSet

    if (useCache)
    {
      if (!entMap.containsKey(entity))
        entMap.put(entity, simplify(entity.common.structure))
  
      return entMap.get(entity)
    }
    else
      return simplify(entity.common.structure)
  }

  def dispatch FeatureSet simplify(VariationDecl variation)
  {
    if (variation === null || variation.structure === null)
      return factory.createFeatureSet

    if (useCache)
    {
      if (!varMap.containsKey(variation))
        varMap.put(variation, simplify(variation.structure))
  
      return varMap.get(variation)
    }
    else
      return simplify(variation.structure)
  }

  def dispatch FeatureSet simplify(StructureExpr expr)
  {
    val leftExpr = if (expr.left !== null) EcoreUtil2.copy(simplify(expr.left)) else factory.createFeatureSet
    val rightExpr = if (expr.right !== null) EcoreUtil2.copy(simplify(expr.right)) else factory.createFeatureSet

    val result = factory.createFeatureSet

    switch expr.operator
    {
      case 'U',
      case '+':
      {
        val toBeAdded = rightExpr.features

        // Map all the features to an Option if they collide in name
        val finalFeatures = leftExpr.features.map[feat |
          if (toBeAdded.exists[feat2 | new CompareFeature().compare(feat, feat2)])
          {
            toBeAdded.remove(feat)
            return feat
          }

          val collide = toBeAdded.findFirst[feat2 | FeatureUtils.getName(feat).equals(FeatureUtils.getName(feat2))]
          if (collide !== null)
          {
            toBeAdded.remove(collide)

            if (feat instanceof ComposedReference || collide instanceof ComposedReference)
              throw new IllegalAthenaOperationException("Feature \"" + FeatureUtils.getName(feat) + "\": Can't merge \"Composed references\"")

            return FeatureUtils.mergeFeaturesWithSameName(feat as SimpleFeature, collide as SimpleFeature)
          }
          else
            return feat
        ]

        result.features.addAll(finalFeatures)

        // Finally add those that didn't collide by name, so are not merged
        result.features.addAll(toBeAdded)
      }
      case '-':
      {
        // For each feature on the right, delete a feature on the left:
        result.features.addAll(leftExpr.features)

        for (Feature feat : rightExpr.features)
        {
          // Watch out: Here candidate is an attribute on the right. So if decided, candidate should be removed.
          val candidate = result.features.findFirst[feat2 | FeatureUtils.getName(feat).equals(FeatureUtils.getName(feat2))]

          if (candidate !== null)
          {
            if (feat instanceof ComposedReference && candidate instanceof ComposedReference)
            {
              if (new CompareComposedReference().compare(candidate as ComposedReference, feat as ComposedReference))
                result.features.remove(candidate)
            }
            else if (feat instanceof SimpleFeature && candidate instanceof SimpleFeature)
            {
              val sCandidate = candidate as SimpleFeature
              val sFeat = feat as SimpleFeature

              if (sFeat.type === null || sCandidate.type === null || new CompareSimpleFeature().compare(sCandidate, sFeat))
                result.features.remove(candidate)
              else if (sCandidate.type instanceof PrimitiveType && sFeat.type instanceof PrimitiveType) // They are not the same exact type.
              {
                FeatureUtils.subtractFeaturesWithSameName(sCandidate, sFeat)
                if (sCandidate.type === null)
                  result.features.remove(candidate)
              }
              else
                throw new IllegalAthenaOperationException("Features \"" + FeatureUtils.getName(candidate) + "\" - \"" + FeatureUtils.getName(feat) + "\": Incompatible types")
            }
          }
        }
      }
    }

    return result
  }

  def dispatch FeatureSet simplify(StructureTerm expr)
  {
    val result = if (expr.left !== null) EcoreUtil2.copy(simplify(expr.left)) else factory.createFeatureSet
    val rightExpr = if (expr.right !== null) EcoreUtil2.copy(simplify(expr.right)) else factory.createFeatureSet
    val toBeRemoved = new ArrayList<Feature>()

    if (expr.operator.equals('I'))
    {
      for (Feature feat1 : result.features)
      {
        val feat2 = rightExpr.features.findFirst[feat2 | FeatureUtils.getName(feat1).equals(FeatureUtils.getName(feat2))]

        if (feat2 === null || !feat1.class.name.equals(feat2.class.name))
        {
          toBeRemoved.add(feat1)
        }
        else
        {
          if (feat1 instanceof ComposedReference && feat2 instanceof ComposedReference)
          {
            if (!new CompareComposedReference().compare(feat1 as ComposedReference, feat2 as ComposedReference))
              toBeRemoved.add(feat1)
          }
          else if (feat1 instanceof SimpleFeature && feat2 instanceof SimpleFeature)
          {
            val sFeat1 = feat1 as SimpleFeature
            val sFeat2 = feat2 as SimpleFeature

            if (sFeat1.type === null)
            {
              if (sFeat2.type !== null)
                sFeat1.type = EcoreUtil2.copy(sFeat2.type)
              // else we keep sFeat1 as is.
            }
            else if (sFeat2.type !== null)
            {
              if (sFeat1.type instanceof PrimitiveType && sFeat2.type instanceof PrimitiveType)
              {
                FeatureUtils.intersectFeaturesWithSameName(sFeat1, sFeat2)
                if (sFeat1.type === null)
                  toBeRemoved.add(feat1)
              } else if (!new CompareSimpleFeature().compare(sFeat1, sFeat2))
                toBeRemoved.add(sFeat1)
            }
          }
          else
            throw new IllegalAthenaOperationException("Features \"" + FeatureUtils.getName(feat1) + "\" I \"" + FeatureUtils.getName(feat2) + "\": Incompatible types")
        }
      }
      result.features.removeAll(toBeRemoved)

      return result
    }

    result.features.removeAll(toBeRemoved)

    return result
  }

  def dispatch FeatureSet simplify(TopLevelStructureDefiningElementDeclRef ref)
  {
    return simplify(ref.ref)
  }

  def dispatch FeatureSet simplify(VariationDeclRef ref)
  {
    return simplify(ref.ref)
  }

  def dispatch FeatureSet simplify(StructureLiteral struct)
  {
    return struct.spec as FeatureSet
  }

  def dispatch FeatureSet simplify(SQLStructure sql)
  {
    val result = factory.createFeatureSet

    for (element : sql.definitions.filter(SQLColumnDefinition))
    {
      val newFeature = factory.createSimpleFeature(element.name)

      if (element.refs !== null)
        newFeature.type = factory.createSimpleRef(element.refs.ref, "&", sqlTypeToAthenaType(element.type))
      else
        newFeature.type = sqlTypeToAthenaType(element.type)

      result.features.add(newFeature)

      if (element.constraint !== null)
      {
        switch (element.constraint.toLowerCase)
        {
          case "primary key": newFeature.key = true
          case "unique key": newFeature.unique = true
          case "null": newFeature.optional = true
          case "not null": newFeature.optional = false
        }
      }
    }

    for (element : sql.definitions.filter(SQLConstraintDefinition))
    {
      switch (element)
      {
        SQLUniqueConstraintDefinition:
        {
          for (name : element.colNames)
            (result.features.findFirst[f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name)] as SimpleFeature).unique = true
        }
        SQLPrimaryConstraintDefinition:
        {
          for (name : element.colNames)
            (result.features.findFirst[f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(name)] as SimpleFeature).key = true
        }
        SQLForeignConstraintDefinition:
        {
          if (element.colNames.size > 1)
            result.features.add(factory.createComposedReference(element.colNames, element.refs.ref))
          else
          {
            val feature = result.features.findFirst[f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(element.colNames.head)] as SimpleFeature
            feature.type = factory.createSimpleRef(element.refs.ref, "&", feature.type as PrimitiveType)
          }
        }
      }
    }

    return result
  }

  private def PrimitiveType sqlTypeToAthenaType(SQLType sql)
  {
    switch (sql.typename.toLowerCase)
    {
      case "tinyint", case "boolean", case "bool":                                            return factory.createUnrestrictedPrimitiveType("Boolean")
      case "int", case "integer", case "smallint", case "mediumint":                          return factory.createUnrestrictedPrimitiveType("Integer")
      case "dec", case "fixed", case "decimal", case "numeric", case "bit":                   return factory.createUnrestrictedPrimitiveType("Number")
      case "float", case "real":                                                              return factory.createUnrestrictedPrimitiveType("Float")
      case "double precision", case "double":                                                 return factory.createUnrestrictedPrimitiveType("Double")
      case "date", case "time", case "datetime", case "timestamp", case "year":               return factory.createUnrestrictedPrimitiveType("Timestamp")
      case "char", case "varchar", case "binary", case "varbinary", case "blob", case "text": return factory.createUnrestrictedPrimitiveType("String")
    }
  }
}
