package es.um.nosql.schemainference.m2t.gen.mongoose

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.util.emf.ResourceManager
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage
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
import java.util.HashMap
import java.util.Set
import java.util.regex.Pattern
import java.util.Map
import org.eclipse.collections.impl.block.factory.Comparators
import java.util.Comparator

class DiffToMongoose
{
	static class Label
	{
		var label = ""

		new(String l) {
			label = l
		}

		override toString() {
			label
		}
	}
	
	var modelName = "";

	final val EXACT_TYPE = true
	final val DUCK_TYPE = !EXACT_TYPE

	final val SPECIAL_TYPE_IDENTIFIER = "type"
	
	// list of entities
	List<Entity> entities
	List<Entity> topOrderEntities
	
	HashMap<Entity, Set<Entity>> entityDeps
	HashMap<Entity, Set<Entity>> inverseEntityDeps
	HashMap<Entity, EntityDiffSpec> diffByEntity
	
	static File outputDir

	def static void main(String[] args)
    {
		if (args.length < 1)
		{
			System.out.println("Usage: DiffToMongoose model [outdir]")
			System.exit(-1)
		}

        val inputModel = new File(args.head)
        val ResourceManager rm = new ResourceManager(EntitydifferentiationPackage.eINSTANCE,
        	NoSQLSchemaPackage.eINSTANCE)
        rm.loadResourcesAsStrings(inputModel.getPath())
        val EntityDifferentiation td = rm.resources.head.contents.head as EntityDifferentiation

		outputDir = new File(if (args.length > 1) args.get(1) else ".")
								.toPath().resolve(td.name).toFile()
		// Create destination directory if it does not exist
		outputDir.mkdirs()
        System.out.println("Generating Javascript for "
        					+ inputModel.getPath()
        					+ " in "
        					+ outputDir.getPath())

		val diff_to_mongoose = new DiffToMongoose()
		diff_to_mongoose.generate(td)
		
        System.exit(0)
    }

	def static void writeToFile(String filename, CharSequence toWrite)
	{
		val outFile = outputDir.toPath().resolve(filename).toFile()
		val outFileWriter = new PrintStream(outFile)
        outFileWriter.print(toWrite)
        outFileWriter.close()
	}

	/**
	 * Method used to generate an Inclusive/Exclusive differences file for a NoSQLDifferences object.
	 */
	def generate(EntityDifferentiation diff)
	{
		modelName = diff.name;
		diffByEntity = newHashMap(diff.entityDiffSpecs.map[ed | ed.entity -> ed])

		// Calc dependencies between entities
		topOrderEntities = calcDeps(diff)

		fillTypeCompatibilityMatrix(diff)
	
		topOrderEntities.forEach[e | writeToFile(schemaFileName(e), generateSchema(e))]
	}
	
	// Fill, for each property of each entity that appear in more than 
	// one entity version *with different type* (those that hold the needsTypeCheck
	// boolean attribute), the set of types, to check possible type folding in
	// a latter pass
	def fillTypeCompatibilityMatrix(EntityDifferentiation differentiation)
	{
	}

	def generateSchema(Entity e) '''
		'use strict'

		var mongoose = require('mongoose');
		«genIncludes(e)»

		var «e.name»Schema = new mongoose.Schema({
			«genSpecs(diffByEntity.get(e))»
		});

		module.exports = «e.name»Schema;
	'''
	
	def genIncludes(Entity entity) '''
		«FOR e : entityDeps.get(entity).sortWith(Comparator.comparing[e | topOrderEntities.indexOf(e)])»
			var «e.name»Schema = require('./«schemaFileName(e)»');
		«ENDFOR»
	'''
	
	def schemaFileName(Entity e) {
		e.name + "Schema.js"
	}
		
	def calcDeps(EntityDifferentiation diff) 
	{
		entities = diff.entityDiffSpecs.map[entity]

		entityDeps = newHashMap(entities.map[e | e -> depListFor(e)])
		inverseEntityDeps = newHashMap(entities.map[e | 
			e -> entities.filter[e2 | entityDeps.get(e2).contains(e)].toSet
		])
		
		// Implement a topological order, Khan's algorithm
		// https://en.wikipedia.org/wiki/Topological_sorting#Kahn.27s_algorithm
		topOrder()
	}
	
	// Get the first level of dependencies for an Entity
	def depListFor(Entity entity)
	{
		entity.entityversions.map[ev | 
			ev.properties.filter[p |
				p instanceof Aggregate
			].map[p | 
				(p as Aggregate).refTo.map[ev2 | ev2.eContainer as Entity]
			].flatten
		].flatten.toSet
	}

	def List<Entity> topOrder()
	{
		depListRec(
			entityDeps.filter[k, v| v.empty].keySet,
			newLinkedList(),
			newHashSet()
		)
	}

	def List<Entity> depListRec(Set<Entity> to_consider, List<Entity> top_order, Set<Entity> seen)
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
				dependent.filter[ d | 
					seen.containsAll(entityDeps.get(d))
				])			
			
			depListRec(to_consider_, top_order, seen)
		}
	}

	def genSpecs(EntityDiffSpec spec) '''
	«FOR s : spec.commonProps.map[cp | cp -> true] + spec.specificProps.map[sp | sp -> false] SEPARATOR ','»
	«s.key.property.name»: «toJSONString(mongooseOptionsForPropertySpec(s.key, s.value))»
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
	
	def mongooseOptionsForPropertySpec(PropertySpec spec, boolean required)
	{
		val props = <String,Object>newHashMap()

		props.putAll(genTypeForPropertySpec(spec))
		if (required)
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
		switch o {
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
	
	def genTypeForPropertySpec(PropertySpec ps) {
		if (ps.needsTypeCheck)
			#{ "type" -> label("mongoose.Schema.Types.Mixed") }
		else
			genTypeForProperty(ps.property)
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

	def dispatch genTypeForProperty(Reference ref) {
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
			#{ 'type' -> genTypeForPrimitiveString(ref.originalType)}
	}
	
	val pat = Pattern.compile("DBRef\\((.+?)\\)")
	
	def expandRef(Reference reference) 
	{
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
	
	def genTypeForPrimitiveString(String type) {
		label(
			switch typeName : type.toLowerCase {
				case "string" : "String"
				case typeName.isInt : 'Number'
				case typeName.isFloat :  'Number'
				case typeName.isBoolean : 'Boolean'
				case typeName.isObjectId : 'mongoose.Schema.Types.ObjectId'
				default: ''
			}
		)
	}

	private def isInt(String type) { #["int", "integer", "number"].contains(type) }
	private def isFloat(String type) { #["float", "double"].contains(type) }
	private def isBoolean(String type) { #["boolean", "bool"].contains(type) }
	private def isObjectId(String type) { #["objectid"].contains(type) }
}
