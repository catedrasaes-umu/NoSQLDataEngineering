package es.um.nosql.schemainference.m2t.mongoose

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import java.io.File
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import java.util.List
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.Reference
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import es.um.nosql.schemainference.NoSQLSchema.Entity
import java.util.Set
import java.util.regex.Pattern
import java.util.Map
import java.util.Comparator
import es.um.nosql.schemainference.NoSQLSchema.Property
import es.um.nosql.schemainference.util.emf.ModelLoader
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.NoSQLSchema.Association
import java.util.ArrayList
import java.io.PrintStream
import es.um.nosql.schemainference.m2t.commons.Commons

public class DiffToMongoose
{
  static class Label
  {
    var label = ""
    new(String l) {label = l}
    override toString() {label}
  }

  static class LambdaNullFunction
  {
    override toString() {"() => undefined"}
  }

  var modelName = "";
  static File outputDir

  // List of dependencies
  List<Entity> topOrderEntities
  Map<Entity, Set<Entity>> entityDeps
  Map<Entity, Set<Entity>> inverseEntityDeps
  Map<Entity, EntityDiffSpec> diffByEntity
  Map<Entity, Map<String, List<PropertySpec>>> typeListByPropertyName

  /**
   * Method used to start the generation process from a diff model file
   */
  def void m2t(File modelFile, File outputFolder)
  {
    val loader = new ModelLoader(EntitydifferentiationPackage.eINSTANCE);
    val diff = loader.load(modelFile, EntityDifferentiation);

    m2t(diff, outputFolder);
  }

  /**
   * Method used to start the generation process from an EntityDifferentiation object
   */
  def void m2t(EntityDifferentiation diff, File outputFolder)
  {
    if (outputFolder.toPath.resolve("app/models/").toFile.exists)
      outputDir = outputFolder.toPath.resolve("app/models/").toFile
    else
      outputDir = outputFolder

    modelName = diff.name;

    diffByEntity = newHashMap(diff.entityDiffSpecs.map[ed | ed.entity -> ed])
    val entities = diff.entityDiffSpecs.map[entity]

    // Calc dependencies between entities
    topOrderEntities = calculateDeps(entities)

    typeListByPropertyName = calcTypeListMatrix(entities)
    topOrderEntities.forEach[e | Commons.WRITE_TO_FILE(outputDir, schemaFileName(e), genSchema(e))]
  }

  // Fill, for each property of each entity that appear in more than one entity version *with different type* (those that hold the needsTypeCheck
  // boolean attribute), the list of types, to check possible type folding in a latter pass
  def calcTypeListMatrix(List<Entity> entities)
  {
    entities.toInvertedMap[e |
      diffByEntity.get(e).entityVersionProps
        .map[propertySpecs]
        .flatten
        .filter[needsTypeCheck].groupBy[property.name]
    ]
  }

  def genSchema(Entity e) '''
    'use strict'

    var mongoose = require('mongoose');
    «genIncludes(e, diffByEntity.get(e))»

    var «e.name»Schema = new mongoose.Schema({
      «genSpecs(e, diffByEntity.get(e))»
    }«genCollectionName(e)»);

    module.exports = mongoose.model('«e.name»', «e.name»Schema);
  '''

  def genIncludes(Entity entity, EntityDiffSpec spec) '''
    «FOR e : entityDeps.get(entity).sortWith(Comparator.comparing[e | topOrderEntities.indexOf(e)])»
      var «e.name»Schema = require('./«schemaFileName(e)»');
    «ENDFOR»
    «IF spec.commonProps.exists[cp | cp.needsTypeCheck] || spec.entityVersionProps.exists[ev | ev.propertySpecs.exists[ps | ps.needsTypeCheck]]»var UnionType = require('./util/UnionType.js');«ENDIF»
  '''

  def schemaFileName(Entity e)
  {
    e.name + "Schema.js"
  }

  def genCollectionName(Entity e) '''
    «IF (e.entityversions.exists[ev | ev.isRoot])», {collection: '«e.name»'}«ENDIF»'''

  def genSpecs(Entity e, EntityDiffSpec spec) '''
  «FOR s : spec.commonProps.map[cp | cp -> true] + spec.specificProps.map[sp | sp -> false] SEPARATOR ','»
  «s.key.property.name»: «toJSONString(mongooseOptionsForPropertySpec(e,s.key, s.value))»
  «ENDFOR»
  '''

  def specificProps(EntityDiffSpec spec)
  {
    spec.entityVersionProps.map[propertySpecs].fold(<PropertySpec>newHashSet(),
      [result, neew |
        val names = result.map[p | p.property.name].toSet
        result.addAll(neew.filter[p | !names.contains(p.property.name)])
        result
      ])
  }

  def mongooseOptionsForPropertySpec(Entity e, PropertySpec spec, boolean required)
  {
    val props = <String,Object>newHashMap()

    props.putAll(genTypeForPropertySpec(e, spec))

    // Careful with this....see Test1.java
    if (required && (!spec.property.name.equals("type") && (!(spec.property instanceof Association) || (spec.property as Association).lowerBound != 0)))
      props.put('required', true)
    else if ((spec.property instanceof Attribute && (spec.property as Attribute).type instanceof Tuple) ||
      (spec.property instanceof Association && (spec.property as Association).upperBound !== 1))
      props.put('default', new LambdaNullFunction())
    // This last condition is used because empty optional arrays are stored in Mongoose. This shouldn't be a thing.
    // If the user doesnt want to store an optional array field, that field wont appear on the object.
    // To prevent this, when an array field is not required, it will be labeled as default: () => undefined.
    props
  }

  // Maybe simplify output when the map has only one element (the type)
  def toJSONMaybeSimplified(Map<String, Object> m)
  {
    val keySet = m.keySet;
    if (keySet.length == 1 && keySet.get(0).equals("type"))
      toJSONString(m.values.get(0))
    else
      '''{«FOR k : keySet SEPARATOR ', '»«k»: «toJSONString(m.get(k))»«ENDFOR»}'''
  }

  def CharSequence toJSONString(Object o)
  {
    switch o
    {
      Map<String, Object>: toJSONMaybeSimplified(o)
      String: stringify(o)
      default: o.toString
    }
  }

  private def stringify(String string)
    '''"«string.replace("\"", "\\\"")»"'''

  private def label(String s)
  {
    new Label(s)
  }

  def genTypeForPropertySpec(Entity e, PropertySpec ps)
  {
    if (ps.needsTypeCheck)
      genTypeForTypeCheckProperty(e, ps.property)
    else
      genTypeForProperty(ps.property)
  }

  // As it is a type check property, it occurs in the 
  def genTypeForTypeCheckProperty(Entity e, Property property)
  {
    val typeList = typeListByPropertyName.get(e).get(property.name)
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
      genTypeForProperty(uniqueTypeList.head);
    }
    else
    {
      #{ "type" -> label(genUnion(uniqueTypeList))}
    }
  }

  def dispatch reduceUnionProperty(Aggregate aggr, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    addToReduceLists(aggr, (aggr.refTo.get(0).eContainer as Entity).name, uniqueTypeList, typeShortcutList);
  }

  def dispatch reduceUnionProperty(Reference ref, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    addToReduceLists(ref, ref.originalType, uniqueTypeList, typeShortcutList);
  }

  def dispatch reduceUnionProperty(Attribute attr, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    if (attr.type instanceof PrimitiveType)
      addToReduceLists(attr, (attr.type as PrimitiveType).name, uniqueTypeList, typeShortcutList);
    if (attr.type instanceof Tuple)
    {
      val typeTuple = (attr.type as Tuple).elements;
      if (typeTuple.size == 1)
      {
        uniqueTypeList.add(attr);
        typeShortcutList.add((typeTuple.get(0) as PrimitiveType).name);
      }
      else if (typeTuple.size > 1)
        addToReduceLists(attr, "[Mixed]", uniqueTypeList, typeShortcutList);
    }
  }

  def addToReduceLists(Property p, String name, List<Property> uniqueTypeList, List<String> typeShortcutList)
  {
    if (!typeShortcutList.exists[type | type.equals(name)])
    {
      uniqueTypeList.add(p);
      typeShortcutList.add(name);
    }
  }

  def String genUnion(Iterable<Property> list)
  {
    // Concatenate each type of the union removing the Schema.schema from the name if neccesary
    val unionName = "U_" + list.map[p | genTypeForProperty(p).values.get(0)]
                                .map[o | if (o.toString.endsWith("Schema.schema")) o.toString.substring(0, o.toString.indexOf("Schema.schema")) else o]
                                .join('_');

    // Now, for the Union itself, concatenate each type of the union but with quotation marks and a different join character.
    '''UnionType("«unionName»", «list.map[p | genTypeForProperty(p).values.get(0)]
                                      .map[o | "\"" + (if (o.toString.endsWith("Schema.schema")) o.toString.substring(0, o.toString.indexOf("Schema.schema")) else o) + "\""]
                                      .join(', ')»)'''
  }

  def aggregateType(Aggregate agg)
  {
    val entityName = (agg.refTo.get(0).eContainer as Entity).name

    // Lower bound might be 0 or 1. In any of those cases we only need a value, not an array
    if (agg.upperBound == 1)
      '''«entityName»Schema.schema'''
    else
    // Upper bound might be 2, 3, 4...-1. We need an array.
      '''[«entityName»Schema.schema]'''
  }

  def dispatch genTypeForProperty(Aggregate agg) 
  {
    #{ 'type' -> aggregateType(agg) }
  }

  def dispatch genTypeForProperty(Attribute att) 
  {
    genAttributeType(att.type)
  }

  def dispatch genTypeForProperty(Reference ref)
  {
    val refComps = expandRef(ref)

    // DBRef
    if (refComps.length == 2)
      #{  'type' -> genTypeForPrimitiveString(refComps.get(1)),
        'ref' -> label(ref.refTo.name)
      }
    else
      #{ 'type' -> referenceType(ref)}
  }

  def referenceType(Reference reference)
  {
    // If originalType is empty, suppose String
    var theType = "";
    if (reference.originalType === null || reference.originalType.empty)
    {
      theType = "String";
    }
    else
    {
      theType = reference.originalType;
    }

    if (reference.upperBound == 1)
      '''«genTypeForPrimitiveString(theType)»'''
    else
      '''[«genTypeForPrimitiveString(theType)»]'''
  }

  def expandRef(Reference reference) 
  {
    val pat = Pattern.compile("DBRef\\((.+?)\\)")
    val m = pat.matcher(reference.originalType)
    if (m.matches)
      #["dbref", m.group(0)]
    else
      #[reference.originalType]
  }

  def dispatch genAttributeType(Tuple type)
  {
    #{'type' -> genType(type)}
  }

  def dispatch Object genType(Tuple tuple)
  {
    if (tuple.elements.size == 1)
      '''[«genType(tuple.elements.get(0))»]'''
    else
      // Heterogeneous arrays. Too complex for now...
      '''[Mixed]'''
  }

  def dispatch genType(PrimitiveType type)
  {
    genTypeForPrimitiveString(type.name)
  }

  def dispatch genAttributeType(PrimitiveType type)
  {
    #{'type' -> genTypeForPrimitiveString(type.name)}
  }

  def genTypeForPrimitiveString(String type)
  {
    label
    (
      switch typeName : type.toLowerCase
      {
        case "string" : "String"
        case typeName.isInt : "Number"
        case typeName.isFloat :  "Number"
        case typeName.isBoolean : "Boolean"
        case typeName.isObjectId : "ObjectId"
        default: ""
      }
    )
  }

  private def isInt(String type) { #["int", "integer", "number"].contains(type)}
  private def isFloat(String type) { #["float", "double"].contains(type)}
  private def isBoolean(String type) { #["boolean", "bool"].contains(type)}
  private def isObjectId(String type) { #["objectid"].contains(type)}

  /**
   * Method used to calculate the dependencies between entities, and reorder them in the correct order
   */
  private def calculateDeps(List<Entity> entities) 
  { 
    entityDeps = newHashMap(entities.map[e | e -> getDepsFor(e)])
    inverseEntityDeps = newHashMap(entities.map[e | 
      e -> entities.filter[e2 | entityDeps.get(e2).contains(e)].toSet
    ])

    // Implement a topological order, Khan's algorithm
    // https://en.wikipedia.org/wiki/Topological_sorting#Kahn.27s_algorithm
    topologicalOrder()
  }

  // Get the first level of dependencies for an Entity
  private def getDepsFor(Entity entity)
  {
    entity.entityversions.map[ev | 
      ev.properties.filter[p | p instanceof Aggregate]
      .map[p | (p as Aggregate).refTo.map[ev2 | ev2.eContainer as Entity]]
      .flatten
    ].flatten.toSet
  }

  private def List<Entity> topologicalOrder()
  {
    depListRec(
      entityDeps.filter[k, v| v.empty].keySet,
      newLinkedList(),
      newHashSet()
    )
  }

  private def List<Entity> depListRec(Set<Entity> to_consider, List<Entity> top_order, Set<Entity> seen)
  {
    // End condition
    if (to_consider.isEmpty)
      top_order
    else
    {
      // Recursive
      val e = to_consider.head
      val to_consider_ = to_consider.tail.toSet

      // Add current node (no dependencies to cover)
      top_order.add(e)
      seen.add(e)

      val dependent = inverseEntityDeps.get(e)
      to_consider_.addAll(
        dependent.filter[ d | seen.containsAll(entityDeps.get(d))
      ])

      depListRec(to_consider_, top_order, seen)
    }
  }
}
