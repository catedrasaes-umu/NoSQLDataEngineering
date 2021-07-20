package es.um.unosql.xtext.athena.utils

import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.OptionPrimitiveType
import es.um.unosql.xtext.athena.athena.AthenaFactory
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.utils.compare.CompareSimpleFeature
import java.util.List
import es.um.unosql.xtext.athena.athena.FeatureSet
import org.eclipse.xtext.EcoreUtil2

class FeatureUtils
{
  static def dispatch getName(ComposedReference feat) { feat.names }
  static def dispatch getName(SimpleFeature feat)     { feat.name  }

  static def List<SimpleFeature> getKeys(FeatureSet fSet)
  {
    return fSet.features.filter(SimpleFeature).filter[feat | feat.key].toList
  }

  static def SimpleFeature mergeFeaturesWithSameName(SimpleFeature sFeat1, SimpleFeature sFeat2)
  {
    if (new CompareSimpleFeature().compare(sFeat1, sFeat2))
      return sFeat1

    val type1 = sFeat1.type
    val type2 = sFeat2.type

    val result = if (type1 !== null) EcoreUtil2.copy(sFeat1) else EcoreUtil2.copy(sFeat2)
    result.key = sFeat1.isKey || sFeat2.isKey
    result.optional = sFeat1.optional && sFeat2.optional

    if (type1 === null || type2 === null)
      return result

    if (!(type1 instanceof PrimitiveType) || !(type2 instanceof PrimitiveType))
      throw new IllegalAthenaOperationException("Feature \"" +  getName(sFeat1) + "\": Can't merge \"" + type1.class.simpleName + "\" and \"" + type2.class.simpleName + "\"")

    // type1 and type2 must be a PrimitiveType, either SinglePrimitiveType or OptionPrimitiveType
    val mergedType = mergePrimitiveTypes(type1 as PrimitiveType, type2 as PrimitiveType) 
    if (mergedType === null)
      return null
    else
    {
      (result as SimpleFeature).type = mergedType
      return result
    }
  }

  static private def OptionPrimitiveType mergePrimitiveTypes(PrimitiveType type1, PrimitiveType type2) 
  {
    val option = AthenaFactory.eINSTANCE.createOptionPrimitiveType()

    if (type1 instanceof SinglePrimitiveType && type2 instanceof SinglePrimitiveType)
    {
      option.options.add(EcoreUtil2.copy(type1 as SinglePrimitiveType))
      option.options.add(EcoreUtil2.copy(type2 as SinglePrimitiveType))
    }
    else
    {
      // At least one of them is an Option. Create one new to accomodate all of them
      // Add all types, either if they are single or option
      mergeTypes(option, type1)
      mergeTypes(option, type2)
    }

    return option
  }

  static private def dispatch mergeTypes(OptionPrimitiveType optType, SinglePrimitiveType sType)
  {
    optType.options.add(EcoreUtil2.copy(sType))
  }

  static private dispatch def mergeTypes(OptionPrimitiveType optType1, OptionPrimitiveType optType2)
  {
    optType1.options.addAll(optType2.options.map[t | EcoreUtil2.copy(t)])
  }

  static def SimpleFeature subtractFeaturesWithSameName(SimpleFeature sFeat1, SimpleFeature sFeat2)
  {
    if (new CompareSimpleFeature().compare(sFeat1, sFeat2))
    {
      sFeat1.type = null;
      return sFeat1
    }

    val type1 = sFeat1.type
    val type2 = sFeat2.type

    if (!(type1 instanceof PrimitiveType) || !(type2 instanceof PrimitiveType))
      throw new IllegalAthenaOperationException("Feature \"" +  sFeat1.name + "\": Can't subtract \"" + type1.class.simpleName + "\" and \"" + type2.class.simpleName + "\"")

    val subtractedType = removeTypes(type1 as PrimitiveType, type2 as PrimitiveType)
    sFeat1.type = subtractedType

    return sFeat1
  }

  static private def dispatch removeTypes(SinglePrimitiveType candidateType, SinglePrimitiveType sType)
  {
    if (candidateType.typename.equals(sType.typename))
      return null
    else
      return candidateType
  }

  static private def dispatch removeTypes(OptionPrimitiveType candidateType, SinglePrimitiveType sType)
  {
    candidateType.options.removeIf(opt | opt.typename.equals(sType.typename))

    if (candidateType.options.empty)
      return null
    else
      return candidateType
  }

  static private def dispatch removeTypes(SinglePrimitiveType candidateType, OptionPrimitiveType featType)
  {
    if (featType.options.exists[opt | opt.typename.equals(candidateType.typename)])
      return null

    return candidateType
  }

  static private def dispatch removeTypes(OptionPrimitiveType candidateType, OptionPrimitiveType featType)
  {
    candidateType.options.removeIf(opt | featType.options.exists[opt2 | opt.typename.equals(opt2.typename)])

    if (candidateType.options.empty)
      return null
    else
      return candidateType
  }

  static def SimpleFeature intersectFeaturesWithSameName(SimpleFeature sFeat1, SimpleFeature sFeat2)
  {
    if (new CompareSimpleFeature().compare(sFeat1, sFeat2))
      return sFeat1

    val type1 = sFeat1.type
    val type2 = sFeat2.type

    if (!(type1 instanceof PrimitiveType) || !(type2 instanceof PrimitiveType))
      throw new IllegalAthenaOperationException("Feature \"" +  getName(sFeat1) + "\": Can't intersect \"" + type1.class.simpleName + "\" and \"" + type2.class.simpleName + "\"")

    // type1 and type2 must be a PrimitiveType, either SinglePrimitiveType or OptionPrimitiveType
    val retFeature = EcoreUtil2.copy(sFeat1)

    retFeature.key = sFeat1.isKey && sFeat2.isKey
    retFeature.optional = sFeat1.optional || sFeat2.optional

    val intersectedType = intersectPrimitiveTypes(type1 as PrimitiveType, type2 as PrimitiveType) 
    sFeat1.type = intersectedType

    return sFeat1
  }

  static private def dispatch intersectPrimitiveTypes(SinglePrimitiveType candidateType, SinglePrimitiveType featType)
  {
    if (candidateType.typename.equals(featType.typename))
      return candidateType
    else
      return null
  }

  static private def dispatch intersectPrimitiveTypes(OptionPrimitiveType candidateType, SinglePrimitiveType featType)
  {
    candidateType.options.removeIf(opt | !opt.typename.equals(featType.typename))

    if (candidateType.options.empty)
      return null
    else
      return candidateType
  }

  static private def dispatch intersectPrimitiveTypes(SinglePrimitiveType candidateType, OptionPrimitiveType featType)
  {
    if (!featType.options.exists[opt | opt.typename.equals(candidateType.typename)])
      return null

    return candidateType
  }

  static private def dispatch intersectPrimitiveTypes(OptionPrimitiveType candidateType, OptionPrimitiveType featType)
  {
    candidateType.options.removeIf(opt | !featType.options.exists[opt2 | opt.typename.equals(opt2.typename)])

    if (candidateType.options.empty)
      return null
    else
      return candidateType
  }
}
