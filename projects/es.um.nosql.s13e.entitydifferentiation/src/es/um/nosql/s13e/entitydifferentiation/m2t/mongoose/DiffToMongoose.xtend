package es.um.nosql.s13e.entitydifferentiation.m2t.mongoose

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation
import java.io.File
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff
import java.util.List
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType
import es.um.nosql.s13e.NoSQLSchema.Attribute
import es.um.nosql.s13e.NoSQLSchema.PTuple
import es.um.nosql.s13e.NoSQLSchema.Reference
import es.um.nosql.s13e.NoSQLSchema.Aggregate
import es.um.nosql.s13e.NoSQLSchema.EntityType
import java.util.Map
import java.util.Comparator
import es.um.nosql.s13e.NoSQLSchema.Property
import es.um.nosql.s13e.util.ModelLoader
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage
import es.um.nosql.s13e.NoSQLSchema.Association
import java.util.ArrayList
import es.um.nosql.s13e.entitydifferentiation.m2t.commons.Commons
import es.um.nosql.s13e.entitydifferentiation.m2t.commons.DependencyAnalyzer
import es.um.nosql.s13e.entitydifferentiation.m2t.config.ConfigMongoose
import java.util.HashMap
import es.um.nosql.s13e.NoSQLSchema.PList
import es.um.nosql.s13e.NoSQLSchema.PSet

/**
 * Class designed to perform the Mongoose code generation: Javascript
 */
class DiffToMongoose
{
  private static class Label
  {
    var label = ""
    new(String l) {label = l}
    override toString() {label}
  }

  File outputDir;

  DependencyAnalyzer analyzer;

  MongooseIndexValGen indexValGen;

  /**
   * Method used to start the generation process from a diff model file
   */
  def void m2t(File modelFile, File outputFolder, File configFile)
  {
    val loader = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    val diff = loader.load(modelFile, EntityDifferentiation);

    m2t(diff, outputFolder, configFile);
  }

  /**
   * Method used to start the generation process from an EntityDifferentiation object
   */
  def void m2t(EntityDifferentiation diff, File outputFolder, File configFile)
  {
    if (outputFolder.toPath.resolve("app/models/").toFile.exists)
      outputDir = outputFolder.toPath.resolve("app/models/").toFile
    else
      outputDir = outputFolder

    // Process the configuration file
    indexValGen = new MongooseIndexValGen(Commons.PARSE_CONFIG_FILE(ConfigMongoose, configFile, diff))

    // Calc dependencies between entities
    analyzer = new DependencyAnalyzer();
    analyzer.performAnalysis(diff);
    analyzer.getTopOrderEntities().forEach[e | Commons.WRITE_TO_FILE(outputDir, schemaFileName(e) + ".js", genSchema(e))]
  }

  /**
   * This method generates the basic structure of the Javascript class.
   */
  private def genSchema(EntityType e) '''
    'use strict'

    var mongoose = require('mongoose');
    «genIncludes(e, analyzer.getDiffByEntity().get(e))»

    var «e.name» = new mongoose.Schema({
      «genSpecs(e, analyzer.getDiffByEntity().get(e))»
    }, { versionKey: false, «IF (e.isRoot)»collection: '«e.name.toFirstLower»'«ELSE»_id : false«ENDIF»});

    «indexValGen.genIndexesForEntity(e)»

    module.exports = «e.name»;
  '''

  /**
   * To generate imports, we just recreate the routes of the imports to be used.
   */
  private def genIncludes(EntityType entity, EntityDiff spec) '''
    «FOR e : analyzer.getEntityDeps().get(entity).sortWith(Comparator.comparing[e | analyzer.getTopOrderEntities().indexOf(e)])»
      var «e.name» = require('./«schemaFileName(e)»');
    «ENDFOR»
    «IF spec.commonProps.exists[cp | cp.needsTypeCheck] || spec.variationDiffs.exists[ev | ev.propertySpecs.exists[ps | ps.needsTypeCheck]]»
      var UnionType = require('./util/UnionType.js');
    «ENDIF»
  '''

  private def schemaFileName(EntityType e)
  {
    e.name + "Schema"
  }

  /**
   * For each property of any variation of an entity, generate code.
   * s.key stores a PropertySpec
   * s.value stores "required" or not
   */
  private def genSpecs(EntityType e, EntityDiff spec)
  '''
    «FOR s : (spec.commonProps.map[cp | cp -> true] + spec.specificProps.map[sp | sp -> false])
      .reject[p | p.key.property.name.startsWith("_") && !p.key.property.name.equals("_id")]
      .sortBy[p | p.key.property.name] SEPARATOR ','»
      «s.key.property.name»: «toJSONString(mongooseOptionsForPropertySpec(e,s.key, s.value))»
    «ENDFOR»
  '''

  private def specificProps(EntityDiff spec)
  {
    spec.variationDiffs.map[propertySpecs].fold(<PropertySpec>newHashSet(),
      [result, neew |
        val names = result.map[p | p.property.name].toSet
        result.addAll(neew.filter[p | !names.contains(p.property.name)])
        result
      ])
  }

  private def mongooseOptionsForPropertySpec(EntityType e, PropertySpec spec, boolean required)
  {
    val props = new HashMap<String,Object>()

    props.putAll(genPropSpec(e, spec))

    // Also: The 'required' clause on mongoose requires a field to exist and also, if it is an array, to NOT be empty
    // This may cause problems when an association has a lowerBound = 0. To shortcut this, for the moment we wont add the required option to a field
    // if it is an association w lowerBound == 0. Proof: https://stackoverflow.com/questions/27268172/mongoose-schema-to-require-array-that-can-be-empty
    if (required && (!(spec.property instanceof Association) || (spec.property as Association).lowerBound != 0))
      props.put('required', true)
    else if ((spec.property instanceof Attribute && isAttrTypeClassifier(spec.property as Attribute)) ||
      (spec.property instanceof Association && ((spec.property as Association).upperBound !== 1 || (spec.property as Association).lowerBound !== 1)))
      props.put('default', label("undefined"))
    // This last condition is used because empty optional arrays are stored in Mongoose. This shouldn't be a thing.
    // If the user doesnt want to store an optional array field, that field wont appear on the object.
    // To prevent this, when an array field is not required, it will be labeled as default: () => undefined.
    for (Pair<String, String> p : indexValGen.genValidatorsForField(e, spec.property.name))
      props.put(p.key, label(p.value))
    props
  }

  private def boolean isAttrTypeClassifier(Attribute attribute)
  {
    val type = attribute.type
    return type instanceof PTuple || type instanceof PList || type instanceof PSet
  }

  private def CharSequence toJSONString(Object o)
  {
    switch o
    {
      Map<String, Object>: toJSONMaybeSimplified(o)
      String: stringify(o)
      default: o.toString
    }
  }

  // Maybe simplify output when the map has only one element (the type)
  private def toJSONMaybeSimplified(Map<String, Object> m)
  {
    val keySet = m.keySet;
    if (keySet.length == 1 && keySet.get(0).equals("type"))
      toJSONString(m.values.get(0))
    else
      '''{«FOR k : keySet SEPARATOR ', '»«k»: «toJSONString(m.get(k))»«ENDFOR»}'''
  }

  private def stringify(String string)
  '''
    "«string.replace("\"", "\\\"")»"
  '''

  private def label(String s)
  {
    new Label(s)
  }

  /**
   * Method used to check if a property needs type check, and call the neccesary method.
   */
  private def genPropSpec(EntityType e, PropertySpec ps)
  {
    if (ps.needsTypeCheck)
      genCodeForTypeCheckProperty(e, ps.property)
    else
      genCodeForProperty(ps.property)
  }

  /**
   * Method used to try to reduce a Property Union to a single property.
   * This is sometimes possible, for example when a Union is composed of a Reference [String] and a Tuple [String].
   * If the reduction is possible, we generate the property as any other.
   * If not, a Union is generated.
   */
  private def genCodeForTypeCheckProperty(EntityType e, Property property)
  {
    val typeList = analyzer.getTypeListByPropertyName().get(e).get(property.name)
    // On uniqueTypeList we removed duplicated property types, such as a String PrimitiveType and a Reference w originalType String.
    val uniqueTypeList = new ArrayList<Property>();
    // Just a shortcut list so we don't have to access every time to the type field of a property (and all its casts...)
    val typeShortcutList = new ArrayList<String>();

    // We try to reduce Unions. For example, a Union of type Reference.String and a PrimitiveType.String should be reduced to a String field.
    for (PropertySpec ps : typeList)
      reduceUnionProperty(ps.property, uniqueTypeList, typeShortcutList)

    // We reduced the union to a single type!
    if (uniqueTypeList.size == 1)
    {
      genCodeForProperty(uniqueTypeList.head);
    }
    else
    {
      #{ "type" -> label(genUnion(uniqueTypeList))}
    }
  }

  private def dispatch reduceUnionProperty(Aggregate aggr, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    addToReduceLists(aggr, (aggr.aggregates.get(0).container as EntityType).name, uniqueTypeList, typeShortcutList);
  }

  private def dispatch reduceUnionProperty(Reference ref, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    addToReduceLists(ref, ref.originalType, uniqueTypeList, typeShortcutList);
  }

  private def dispatch reduceUnionProperty(Attribute attr, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    if (attr.type instanceof PrimitiveType)
      addToReduceLists(attr, (attr.type as PrimitiveType).name, uniqueTypeList, typeShortcutList);
    if (attr.type instanceof PTuple)
    {
      val typeTuple = (attr.type as PTuple).elements;
      if (typeTuple.size == 1)
      {
        addToReduceLists(attr, "[" + (typeTuple.get(0) as PrimitiveType).name + "]", uniqueTypeList, typeShortcutList);
      }
      else if (typeTuple.size > 1)
        addToReduceLists(attr, "[Mixed]", uniqueTypeList, typeShortcutList);
    }
    else if (attr.type instanceof PList)
    {
      val type = (attr.type as PList).elementType;
      addToReduceLists(attr, "[" + (type as PrimitiveType).name + "]", uniqueTypeList, typeShortcutList);
    }
    else if (attr.type instanceof PSet)
    {
      val type = (attr.type as PSet).elementType;
      addToReduceLists(attr, "[" + (type as PrimitiveType).name + "]", uniqueTypeList, typeShortcutList);
    }
  }

  private def addToReduceLists(Property p, String name, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    if (!typeShortcutList.exists[type | type.equals(name)])
    {
      uniqueTypeList.add(p);
      typeShortcutList.add(name);
    }
  }
  /** End of the Union reduction process */

  /**
   * Method used to generate Union code. In Javascript this is performed by creating a new Union object
   * using a function generated in the Mongoose.Commons part.
   * The attribute type will look like "U_Type1_Type2...TypeN"
   */
  private def String genUnion(Iterable<Property> list)
  {
    // Concatenate each type of the union removing the Schema.schema from the name if neccesary
    val unionName = "U_" + list.map[p | genCodeForProperty(p).values.get(0)]
                                .map[o | if (o.toString.equals("mongoose.Schema.Types.ObjectId")) "ObjectId" else o]
                                .join('_');

    // Now, for the Union itself, concatenate each type of the union but with quotation marks and a different join character.
    '''UnionType("«unionName»", «list.map[p | genCodeForProperty(p).values.get(0)]
                                      .map[o | "\"" + if (o.toString.equals("mongoose.Schema.Types.ObjectId")) "ObjectId" else o + "\""]
                                      .join(', ')»)'''
  }

  /**
   * Generate code attribute for Aggregation
   */
  private def dispatch genCodeForProperty(Aggregate aggr) 
  {
    #{ 'type' -> aggregateType(aggr) }
  }

  /**
   * Shortcut method to generate an Aggregate type.
   */
  private def aggregateType(Aggregate aggr)
  {
    val entityName = (aggr.aggregates.get(0).eContainer as EntityType).name

    // Lower bound might be 0 or 1. In any of those cases we only need a value, not an array
    if (aggr.upperBound == 1 && aggr.lowerBound == 1)
      '''«entityName»'''
    else
    // Upper bound might be 2, 3, 4...-1. We need an array.
      '''[«entityName»]'''
  }

  /**
   * Generate code attribute for Reference
   */
  private def dispatch genCodeForProperty(Reference ref)
  {
    val refComps = Commons.EXPAND_REF(ref)

    // DBRef
    if (refComps.length == 2)
      #{ 'type' -> genTypeForPrimitiveString(refComps.get(1)), 'ref' -> label(ref.refsTo.name)}
    else
      #{ 'type' -> referenceType(ref), 'ref' -> label("\"" + ref.refsTo.name + "\"")}
  }

  /**
   * Shortcut method to generate a Reference type.
   */
  private def referenceType(Reference ref)
  {
    // If originalType is empty, suppose String
    var theType = "";
    if (ref.originalType === null || ref.originalType.empty)
    {
      theType = "String";
    }
    else
    {
      theType = ref.originalType;
    }

    if (ref.upperBound == 1 && ref.lowerBound == 1)
      '''«genTypeForPrimitiveString(theType)»'''
    else
      '''[«genTypeForPrimitiveString(theType)»]'''
  }

  /**
   * Generate code attribute for Attribute
   */
  private def dispatch genCodeForProperty(Attribute a) 
  {
    genAttributeType(a.type)
  }

  /**
   * Generate code attribute for a Tuple Attribute
   */
  private def dispatch genAttributeType(PTuple type)
  {
    #{'type' -> genType(type)}
  }

  /**
   * Generate code attribute for a PList Attribute
   */
  private def dispatch genAttributeType(PList type)
  {
    #{'type' -> genType(type)}
  }

  /**
   * Generate code attribute for a PSet Attribute
   */
  private def dispatch genAttributeType(PSet type)
  {
    #{'type' -> genType(type)}
  }

  /**
   * Generate code attribute for PrimitiveType Attribute
   */
  private def dispatch genAttributeType(PrimitiveType type)
  {
    #{'type' -> genTypeForPrimitiveString(type.name)}
  }

  /**
   * Shortcut method to generate a Primitive type.
   */
  private def dispatch genType(PrimitiveType type)
  {
    genTypeForPrimitiveString(type.name)
  }

  /**
   * Shortcut method to generate a Tuple type.
   */
  private def dispatch CharSequence genType(PTuple tuple)
  {
    if (tuple.elements.size == 1)
      '''[«genType(tuple.elements.get(0))»]'''
    else
      // Heterogeneous arrays. Too complex for now...
      '''[Mixed]'''
  }

  /**
   * Shortcut method to generate a PList type.
   */
  private def dispatch CharSequence genType(PList list)
  {
    '''[«genType(list.elementType)»]'''
  }

  /**
   * Shortcut method to generate a PSet type.
   */
  private def dispatch CharSequence genType(PSet set)
  {
    '''[«genType(set.elementType)»]'''
  }

  private def genTypeForPrimitiveString(String type)
  {
    label
    (
      switch typeName : type.toLowerCase
      {
        case Commons.IS_STRING(typeName) : "String"
        case Commons.IS_INT(typeName) : "Number"
        case Commons.IS_DOUBLE(typeName) :  "Number"
        case Commons.IS_BOOLEAN(typeName) : "Boolean"
        case Commons.IS_OBJECTID(typeName) : "mongoose.Schema.Types.ObjectId"
        default: ""
      }
    )
  }
}