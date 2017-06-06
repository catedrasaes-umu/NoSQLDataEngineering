package es.um.nosql.schemainference.m2t.gen.mongoose

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.util.emf.ResourceManager
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage
import java.io.File
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import java.util.List
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp
import java.io.PrintStream
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec
import org.eclipse.xtext.xbase.lib.Functions.Function1
import es.um.nosql.schemainference.NoSQLSchema.Property
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.Type
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.Reference
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import es.um.nosql.schemainference.NoSQLSchema.Entity
import java.util.Collections
import java.util.HashMap
import java.util.Set

class DiffToMongoose
{
	var modelName = "";

	final val EXACT_TYPE = true
	final val DUCK_TYPE = !EXACT_TYPE

	final val SPECIAL_TYPE_IDENTIFIER = "type"
	
	// list of entities
	List<Entity> entities
	
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
		val order = calcDeps(diff)

		order.forEach[e | writeToFile(schemaFileName(e), generateSchema(e))]
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
		«FOR e : entityDeps.get(entity)»
			var «e.name»Schema = require('./«schemaFileName(e)»');
		«ENDFOR»
	'''
	
	def schemaFileName(Entity e) {
		e.name + "-schema.js"
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
		var order = topOrder()
		
		//order.forEach[e | System.out.println(e.name)]
		order
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
	«FOR s : spec.commonProps SEPARATOR ','»
	«s.property.name» : { type: «genType(s)», required: true}
	«ENDFOR»
	'''
	
	def genType(PropertySpec ps) {
		genTypeForProperty(ps.property)
	}
	
	def dispatch genTypeForProperty(Property property) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	def dispatch CharSequence genTypeForProperty(PrimitiveType type) {
		switch typeName : type.name.toLowerCase {
			case "string" : '''String'''
			case typeName.isInt : '''Number'''
			case typeName.isFloat :  '''Number'''
			case typeName.isBoolean : '''Boolean'''
			default: ''''''
		}
	}

	private def isInt(String type) { #["int", "integer", "number"].contains(type) }
	private def isFloat(String type) { #["float", "double"].contains(type) }
	private def isBoolean(String type) { #["boolean", "bool"].contains(type) }



//	def dispatch CharSequence genTypeCheckLowLevel(Tuple type, String name) {
//	    '''(«name».constructor === Array) && («name».length === «type.elements.size»)
//	    «IF type.elements.size != 0»
//	    &&
//	    «var i = 0»
//	    «FOR t : type.elements SEPARATOR " && "»
//	    «genTypeCheckLowLevel(t, name + '[' + i++ + ']')»
//	    «ENDFOR»
//	    «ENDIF»'''
//	}
}
