package es.um.nosql.schemainference.m2t.mongoose

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import java.io.File
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import java.util.List
import java.io.PrintStream
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

public class DiffToMongoose
{
  static class Label
  {
    var label = ""

    new(String l)
    {
      label = l
    }

    override toString()
    {
      label
    }
  }

  var modelName = "";
  static File outputDir
//  final val EXACT_TYPE = true
//  final val DUCK_TYPE = !EXACT_TYPE
//  final val SPECIAL_TYPE_IDENTIFIER = "type"

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
    topOrderEntities.forEach[e | writeToFile(schemaFileName(e), generateSchema(e))]
  }

  // Fill, for each property of each entity that appear in more than 
  // one entity version *with different type* (those that hold the needsTypeCheck
  // boolean attribute), the list of types, to check possible type folding in
  // a latter pass
  def calcTypeListMatrix(List<Entity> entities)
  {
    entities.toInvertedMap[e |
    	diffByEntity.get(e).entityVersionProps
        .map[propertySpecs]
        .flatten
        .filter[needsTypeCheck].groupBy[property.name]
    ]
  }

  def generateSchema(Entity e) '''
    'use strict'

    var mongoose = require('mongoose');
    «genIncludes(e)»

    var «e.name»Schema = new mongoose.Schema({
      «genSpecs(e, diffByEntity.get(e))»
    }«genCollectionName(e)»);

    module.exports = «e.name»Schema;
  '''

  def genIncludes(Entity entity) '''
    «FOR e : entityDeps.get(entity).sortWith(Comparator.comparing[e | topOrderEntities.indexOf(e)])»
      var «e.name»Schema = require('./«schemaFileName(e)»');
    «ENDFOR»
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
    // A type should never be a 'required' property. Since we are working w mongoDB, objects won't usually have a type, because its type is the collection name.
    // Also: The 'required' clause on mongoose requires a field to exist and also, if it is an array, to NOT be empty
    // This may cause problems when an association has a lowerBound = 0. To shortcut this, for the moment we wont add the required option to a field
    // if it is an association w lowerBound == 0. Proof: https://stackoverflow.com/questions/27268172/mongoose-schema-to-require-array-that-can-be-empty
    if (required && (!spec.property.name.equals("type") && (!(spec.property instanceof Association) || (spec.property as Association).lowerBound != 0)))
      props.put('required', true)
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
    // TODO: First version, Mixed type. In the future, explore
    // complex types for Mongoose:
    // http://mongoosejs.com/docs/customschematypes.html
    // We have to register and generate all these types beforehand
    // so maybe this function should be performed before, when
    // the typeListByPropertyName variable is created

    // If any property type is a primitive, we always produce Mixed
    // (see the TODO above)

    // TODO: The ps.property instanceof Attribute is TENTATIVE. It will crash if not compared.
    // Several cases:
    // Two primitives types (String, Number, Boolean) => Create union type
    // Primitive Type and tuple => Create union type
    // Something and Aggregate => ???
    // Reference (considering originalType may be String, Number or RefId) and Primitive Type or tuple => Create union type
    val typeList = typeListByPropertyName.get(e).get(property.name)
    if (typeList.forall[ps | (ps.property instanceof Attribute) && (ps.property as Attribute)?.type instanceof PrimitiveType ])
      #{ "type" -> label("mongoose.Schema.Types.Mixed") }
    else
      #{ "type" -> label("mongoose.Schema.Types.Mixed") }
  }

  def aggregateType(Aggregate agg)
  {
    val entityName = (agg.refTo.get(0).eContainer as Entity).name

    if (agg.lowerBound == 1 && agg.upperBound == 1)
      '''«entityName»Schema'''
    else
      '''[«entityName»Schema]'''
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
  	// If originalType is empty, suppose String
  	if (ref.originalType == null || ref.originalType.empty)
      return #{ 'type' -> label('String')}

    val refComps = expandRef(ref)

    // DBRef
    if (refComps.length == 2)
      #{	'type' -> genTypeForPrimitiveString(refComps.get(1)),
        'ref' -> label(ref.refTo.name)
      }
    else
      #{ 'type' -> referenceType(ref)}
  }

  def referenceType(Reference reference)
  {
    if (reference.lowerBound != 1 || reference.upperBound != 1)
      '''[«genTypeForPrimitiveString(reference.originalType)»]'''
    else
      '''«genTypeForPrimitiveString(reference.originalType)»'''    
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
    // Generate only the first type
    '''[«genType(tuple.elements.get(0))»]'''
    //'''[«FOR t : tuple.elements SEPARATOR ', '»«genType(t)»«ENDFOR»]'''

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
  	label(
      switch typeName : type.toLowerCase
      {
        case "string" : "String"
        case typeName.isInt : 'Number'
        case typeName.isFloat :  'Number'
        case typeName.isBoolean : 'Boolean'
        case typeName.isObjectId : 'mongoose.Schema.Types.ObjectId'
        default: ''
      }
    )
  }

  private def isInt(String type) { #["int", "integer", "number"].contains(type)}
  private def isFloat(String type) { #["float", "double"].contains(type)}
  private def isBoolean(String type) { #["boolean", "bool"].contains(type)}
  private def isObjectId(String type) { #["objectid"].contains(type)}

  /**
   * Method used to write a generated CharSequence to a file
   */
  private def writeToFile(String filename, CharSequence toWrite)
  {
    val outFile = outputDir.toPath().resolve(filename).toFile()
    val outFileWriter = new PrintStream(outFile)
    outFileWriter.print(toWrite)
    outFileWriter.close()
  }

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
